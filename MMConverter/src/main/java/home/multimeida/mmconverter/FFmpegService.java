package home.multimeida.mmconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FFmpegService {
	
	public void convertToMTS(File input, File target) {
	    try {
	        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
	        String outputPath = target.getParent() + File.separator + input.getName().replaceFirst("\\.[^.]+$", "_" + currentDate + ".mts");

	        File output = new File(outputPath);

	        System.out.println("Starting conversion to MTS...");
	        System.out.println("Input file: " + input.getAbsolutePath());
	        System.out.println("Output file: " + output.getAbsolutePath());

	        String ffmpegPath = FFmpegUtils.extractFFmpegBinary();

	        String[] dimensions = getVideoDimensions(input, ffmpegPath);
	        if (dimensions == null) throw new Exception("Unable to retrieve video dimensions.");

	        int width = Integer.parseInt(dimensions[0]);
	        int height = Integer.parseInt(dimensions[1]);
	        double aspectRatio = (double) width / height;

	        String scaleFilter = (aspectRatio > 16.0 / 9.0) ? "scale=1920:-2" : "scale=-2:1080";
	        String padFilter = "pad=1920:1080:(ow-iw)/2:(oh-ih)/2";
	        String videoFilter = scaleFilter + "," + padFilter;

	        ProcessBuilder processBuilder = new ProcessBuilder(
	            ffmpegPath,
	            "-i", input.getAbsolutePath(),
	            "-vf", videoFilter,
	            "-c:v", "libx264",
	            "-preset", "fast",
	            "-c:a", "ac3",
	            "-b:a", "192k",
	            "-bsf:v", "h264_mp4toannexb",
	            "-f", "mpegts",
	            output.getAbsolutePath()
	        );

	        processBuilder.redirectErrorStream(true);

	        Process process = processBuilder.start();
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) System.out.println(line);
	        }
	        
	        boolean completed = process.waitFor(60, TimeUnit.SECONDS); // Wait for up to 60 seconds
	        if (!completed) {
	            process.destroy(); // Force terminate the process
	            throw new RuntimeException("FFmpeg process timed out.");
	        }
	        
	        int exitCode = process.waitFor();
	        if (exitCode != 0) {
	            System.err.println("FFmpeg failed with exit code: " + exitCode);
	            return; // Exit the function
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

		        System.out.println("Starting aspect ratio and rotation fix...");
		        System.out.println("FFmpeg Path: " + ffmpegPath);
		        System.out.println("Input File: " + input.getAbsolutePath());
		        System.out.println("Output File: " + output.getAbsolutePath());
		        System.out.println("Input file exists: " + input.exists());
		        System.out.println("Output file exists (before process): " + output.exists());

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

		        processBuilder.redirectErrorStream(true);

		        // Start the process
		        Process process = processBuilder.start();

		        // Capture output (for debugging)
		        Thread outputThread = new Thread(() -> {
		            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
		                String line;
		                while ((line = reader.readLine()) != null) {
		                    System.out.println("[FFmpeg Output] " + line);
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        });

		        Thread errorThread = new Thread(() -> {
		            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
		                String line;
		                while ((line = reader.readLine()) != null) {
		                    System.err.println("[FFmpeg Error] " + line);
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        });

		        outputThread.start();
		        errorThread.start();

		        // Wait for threads and process to complete
		        outputThread.join();
		        errorThread.join();
		        
		        boolean completed = process.waitFor(60, TimeUnit.SECONDS); // Wait for up to 60 seconds
		        if (!completed) {
		            process.destroy(); // Force terminate the process
		            throw new RuntimeException("FFmpeg process timed out.");
		        }

		      
		        int exitCode = process.waitFor();
		        if (exitCode != 0) {
		            System.err.println("FFmpeg failed with exit code: " + exitCode);
		            return; // Exit the function
		        }



		    } catch (Exception e) {
		        System.err.println("Exception during aspect ratio fix:");
		        e.printStackTrace();
		    }
		}



}
