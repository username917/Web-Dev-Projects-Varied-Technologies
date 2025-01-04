package home.multimeida.mmconverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FFmpegUtils {
	
	public static String extractFFmpegBinary() throws IOException {
        // Determine the operating system
        String os = System.getProperty("os.name").toLowerCase();
        String ffmpegExecutable;

        if (os.contains("win")) {
            ffmpegExecutable = "ffmpeg/ffmpeg.exe"; // Windows
        } else if (os.contains("mac")) {
            ffmpegExecutable = "ffmpeg/ffmpeg"; // MacOS
        } else {
            ffmpegExecutable = "ffmpeg/ffmpeg"; // Linux
        }

        // Extract FFmpeg binary from resources to a temporary directory
        InputStream ffmpegStream = FFmpegUtils.class.getClassLoader().getResourceAsStream(ffmpegExecutable);
        if (ffmpegStream == null) {
            throw new FileNotFoundException("FFmpeg binary not found in resources!");
        }

        File tempFFmpegFile = new File(System.getProperty("java.io.tmpdir"), "ffmpeg");
        try (FileOutputStream outputStream = new FileOutputStream(tempFFmpegFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = ffmpegStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // Make the file executable
        tempFFmpegFile.setExecutable(true);

        return tempFFmpegFile.getAbsolutePath();
    }

}
