package home.multimeida.mmconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FFmpegService {
	
	public static void convertToMTS(File input, File target) {
	    try {
	        // Generate output path with the current date appended to the filename
	        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
	        String outputPath = target.getParent() + File.separator + input.getName().replaceFirst("\\.[^.]+$", "_" + currentDate + ".mts");

	        File output = new File(outputPath);

	        // Notify user about the process start
	        System.out.println("Starting conversion to MTS...");
	        System.out.println("Input file: " + input.getAbsolutePath());
	        System.out.println("Output file: " + output.getAbsolutePath());

	        String ffmpegPath = FFmpegUtils.extractFFmpegBinary();

	        // Retrieve video dimensions
	        String[] dimensions = getVideoDimensions(input, ffmpegPath);

	        if (dimensions == null) {
	            throw new Exception("Unable to retrieve video dimensions from the source file.");
	        }

	        int width = Integer.parseInt(dimensions[0]);
	        int height = Integer.parseInt(dimensions[1]);
	        double aspectRatio = (double) width / height;

	        // Target dimensions
	        int targetWidth = 1920;
	        int targetHeight = 1080;

	        String scaleFilter;

	        if (width > targetWidth || height > targetHeight) {
	            // Scale to fit within 1920x1080 while maintaining aspect ratio
	            if (aspectRatio > 1) { // Landscape
	                scaleFilter = "scale=1920:-2"; // Fit width, adjust height (divisible by 2)
	            } else { // Portrait or square
	                scaleFilter = "scale=-2:1080"; // Fit height, adjust width (divisible by 2)
	            }
	        } else {
	            // No scaling needed, just pad to fit 1920x1080
	            scaleFilter = "scale=" + width + ":" + height;
	        }

	        // Add padding to ensure output is exactly 1920x1080
	        String padFilter = "pad=1920:1080:(ow-iw)/2:(oh-ih)/2";
	        String videoFilter = scaleFilter + "," + padFilter;

	        // Build FFmpeg command with scaling and padding filter
	        ProcessBuilder processBuilder = new ProcessBuilder(
	            ffmpegPath,
	            "-i", input.getAbsolutePath(),
	            "-vf", videoFilter, // Video filter for scaling and padding
	            "-c:v", "libx264", // H.264 codec for video
	            "-preset", "fast", // Encoding preset for faster processing
	            "-c:a", "ac3", // AC3 codec for audio
	            "-b:a", "192k", // Audio bitrate
	            "-bsf:v", "h264_mp4toannexb", // Annex B formatting
	            "-f", "mpegts", // MTS/M2TS format
	            output.getAbsolutePath()
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
	            
	            input = new File(output.getAbsoluteFile().toString());
	            output = new File("E:/fixedfile.mts");
	            fixAspectRatioAndRotation(input, output);
	            
	        } else {
	            System.err.println("Conversion failed with exit code: " + exitCode);
	        }

	    } catch (Exception e) {
	    	 e.printStackTrace();
	    }
	}
	 private static String[] getVideoDimensions(File input, String ffmpegPath) {
	        try {
	            ProcessBuilder processBuilder = new ProcessBuilder(
	                ffmpegPath,
	                "-i", input.getAbsolutePath()
	            );

	            Process process = processBuilder.start();
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    if (line.contains("Video:")) {
	                        // Extract dimensions from the "Video:" metadata line
	                        String[] parts = line.split(",");
	                        for (String part : parts) {
	                            part = part.trim();
	                            if (part.matches("\\d+x\\d+")) { // Matches "1920x1080"
	                                return part.split("x");
	                            }
	                        }
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null; // Return null if dimensions cannot be retrieved
	    }
	 
	 public static void fixAspectRatioAndRotation(File input, File output) {
		    try {
		        String ffmpegPath = FFmpegUtils.extractFFmpegBinary();

		        // Define scaling and padding filters for portrait video
		        String videoFilter = "scale=ih*9/16:ih,pad=1920:1080:(ow-iw)/2:(oh-ih)/2";

		        // Build FFmpeg command
		        ProcessBuilder processBuilder = new ProcessBuilder(
		            ffmpegPath,
		            "-i", input.getAbsolutePath(),
		            "-vf", videoFilter, // Scale and pad to 1920x1080
		            "-metadata:s:v", "rotate=0", // Reset rotation metadata
		            "-c:v", "libx264", // H.264 codec for video
		            "-preset", "fast", // Encoding preset for faster processing
		            "-c:a", "copy", // Copy audio stream
		            output.getAbsolutePath()
		        );

		        // Redirect error stream
		        processBuilder.redirectErrorStream(true);

		        // Start the process
		        Process process = processBuilder.start();

		        // Capture output (for debugging)
		        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
		            String line;
		            while ((line = reader.readLine()) != null) {
		                System.out.println(line);
		            }
		        }

		        // Wait for the process to complete
		        int exitCode = process.waitFor();
		        if (exitCode == 0) {
		            System.out.println("Aspect ratio fixed and black borders added successfully!");
		        } else {
		            System.err.println("Failed to fix aspect ratio with exit code: " + exitCode);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}


}
