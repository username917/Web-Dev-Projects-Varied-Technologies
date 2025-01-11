package home.multimeida.mmconverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FFmpegUtils {
	
	private static final String FFMPEG_BINARY_NAME = "ffmpeg.exe";

	public static String extractFFmpegBinary() throws IOException {
		// Path to the resource in the JAR
		String ffmpegResourcePath = "ffmpeg/ffmpeg.exe"; // Adjusted to include the correct path

		// Extract binary to a temp file
		File tempBinary = new File(System.getProperty("java.io.tmpdir"), FFMPEG_BINARY_NAME);

		if (!tempBinary.exists()) {
			try (InputStream inputStream = FFmpegUtils.class.getClassLoader().getResourceAsStream(ffmpegResourcePath);
				 FileOutputStream outputStream = new FileOutputStream(tempBinary)) {

				if (inputStream == null) {
					throw new FileNotFoundException("FFmpeg binary not found in resources at: " + ffmpegResourcePath);
				}

				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				tempBinary.setExecutable(true);
			}
		}

		return tempBinary.getAbsolutePath();
	}
}
