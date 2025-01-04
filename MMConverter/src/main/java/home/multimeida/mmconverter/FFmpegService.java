package home.multimeida.mmconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class FFmpegService {
	
	public static void convertToMTS(File input) {
        try {
        	
        	String outputPath = input.getAbsolutePath().replace(".mpegts", ".mts");
            File output = new File(outputPath);
            
            String ffmpegPath = FFmpegUtils.extractFFmpegBinary();
            
            // Notify user about the process start
            System.out.println("Starting conversion to MTS...");
            System.out.println("Input file: " + input.getAbsolutePath());
            System.out.println("Output file: " + output.getAbsolutePath());

            // Build FFmpeg command
            ProcessBuilder processBuilder = new ProcessBuilder(
                ffmpegPath,
                "-i", input.getAbsolutePath(), // Input file
                "-c:v", "copy", // Copy video stream
                "-c:a", "copy", // Copy audio stream
                "-bsf:v", "h264_mp4toannexb", // Annex B formatting
                "-f", "mpegts", // MTS/M2TS format
                output.getAbsolutePath() // Output file
            );

            // Redirect error stream
            processBuilder.redirectErrorStream(true);

            // Start process
            Process process = processBuilder.start();

            // Capture output (for debugging)
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Wait for process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Conversion to MTS completed successfully!");
            } else {
                System.err.println("Conversion failed with exit code: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
