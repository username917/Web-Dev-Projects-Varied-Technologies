package home.multimeida.mmconverter;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoSize;

public class MMConverterInterface extends JFrame {
	
	private JLabel sourceFileLabel;
	private JLabel destinationFolderLabel;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MMConverterInterface() {
		
		setTitle("Multimedia Converter\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Multimedia Converter Tool");
		lblNewLabel.setBounds(35, 25, 121, 13);
		getContentPane().add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("HEIC -> JPEG Conversion");
		rdbtnNewRadioButton.setBounds(35, 44, 194, 21);
		getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("MOV -> AVCHD Conversion");
		rdbtnNewRadioButton_1.setBounds(35, 71, 195, 21);
		getContentPane().add(rdbtnNewRadioButton_1);
		
		ButtonGroup group = new ButtonGroup();
		
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Input File:");
		lblNewLabel_1.setBounds(35, 98, 60, 13);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnSelectFileButton = new JButton("Select File...");
		btnSelectFileButton.setBounds(35, 121, 140, 21);
		getContentPane().add(btnSelectFileButton);
		
		JLabel lblNewLabel_2 = new JLabel("Save Location...");
		lblNewLabel_2.setBounds(35, 182, 90, 13);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnSaveDestination = new JButton("Choose Save Location...");
		btnSaveDestination.setBounds(35, 199, 190, 21);
		getContentPane().add(btnSaveDestination);
		
		sourceFileLabel = new JLabel("No file chosen...");
		sourceFileLabel.setPreferredSize(new Dimension(400, 20));
		sourceFileLabel.setBounds(35, 152, 440, 20); // Positioned beneath "Select File..."
		getContentPane().add(sourceFileLabel);
		
		destinationFolderLabel = new JLabel("No folder selected...");
		destinationFolderLabel.setPreferredSize(new Dimension(400, 20));
		destinationFolderLabel.setBounds(35, 232, 440, 20); // Positioned beneath "Choose Save Location..."
		getContentPane().add(destinationFolderLabel);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.setBounds(35, 262, 85, 21);
		getContentPane().add(btnConvert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(130, 262, 85, 21);
		getContentPane().add(btnCancel);
		
		// adding event listeners to the buttons:
		
		// select file button
		
		btnSelectFileButton.addActionListener(e -> {
		    JFileChooser fileChooser = new JFileChooser();
		    int returnValue = fileChooser.showOpenDialog(null);
		    if (returnValue == JFileChooser.APPROVE_OPTION) {
		        File selectedFile = fileChooser.getSelectedFile();
		        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		        sourceFileLabel.setText(selectedFile.getAbsolutePath());
		    }
		});
		
		// save location button:
		
		btnSaveDestination.addActionListener(e -> {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    int returnValue = fileChooser.showSaveDialog(null);
		    if (returnValue == JFileChooser.APPROVE_OPTION) {
		        File selectedDirectory = fileChooser.getSelectedFile();
		        System.out.println("Save location: " + selectedDirectory.getAbsolutePath());
		        destinationFolderLabel.setText(selectedDirectory.getAbsolutePath());
		    }
		});
		
		// convert button
		
		btnConvert.addActionListener(e -> {
			
			try {
				
				if (sourceFileLabel.getText().equals("No file chosen...") || destinationFolderLabel.getText().equals("No folder selected...")) {
		            JOptionPane.showMessageDialog(null, "Please select both an input file and a save location.", "Validation Error", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				 
				File sourceFile = new File(sourceFileLabel.getText());
			    File destinationFile;
				 
			    if (rdbtnNewRadioButton.isSelected()) {
			    	
			    	System.out.println("Converting HEIC to JPG...");
				        
			        String outputFileName = sourceFile.getName().replaceFirst("[.][^.]+$", ".jpg");
			        
			        // Call your conversion logic here
			        
			        destinationFile = new File(destinationFolderLabel.getText(), outputFileName);
			        
			        convertHeicToJpg(sourceFile, destinationFile);
			    	
			    } else if (rdbtnNewRadioButton_1.isSelected()) {
			    	
			    	if (sourceFileLabel.getText().equals("No file chosen...") || destinationFolderLabel.getText().equals("No folder selected...")) {
			            JOptionPane.showMessageDialog(null, "Please select both an input file and a save location.", "Validation Error", JOptionPane.WARNING_MESSAGE);
			            return;
			        }
			    	
			    	 // Validate source file
			        if (!sourceFile.exists() || !sourceFile.canRead()) {
			            JOptionPane.showMessageDialog(null, "Source file does not exist or is not readable.", "File Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        
			        // Validate destination folder
			        String destinationPath = destinationFolderLabel.getText();
			        if (destinationPath == null || destinationPath.isEmpty() || !(new File(destinationPath).isDirectory())) {
			            JOptionPane.showMessageDialog(null, "Invalid destination folder.", "File Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			    	
			    	System.out.println("Converting MOV to AVCHD...");
			    	
			    	String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

			        // Extract the file name without the extension
			        String baseName = sourceFile.getName().replaceFirst("[.][^.]+$", "");

			        // Sanitize the base name (replace invalid characters with '_')
			        baseName = baseName.replaceAll("[^a-zA-Z0-9-_]", "_");
			        
			        String sanitizedFileName = baseName + "_" + currentDate;
			        sanitizedFileName = sanitizedFileName.replaceAll("[^a-zA-Z0-9._-]", "_") + ".mpegts"; // Allow alphanumeric, '-', '_', and '.'

			        destinationFile = new File(destinationPath, sanitizedFileName);
			        
			        System.out.println("Destination ile is: " + destinationFile);
			        
			        convertMovToAvchdWithJave(sourceFile, destinationFile);
			        
			        
			    } else {
			    	
			        JOptionPane.showMessageDialog(null, "Please select a conversion type.");
			        
			    }
				
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Conversion Error", JOptionPane.ERROR_MESSAGE);
		        ex.printStackTrace();
			}
			
			
		});
		
		// cancel button:
		
		btnCancel.addActionListener(e -> {
		    System.out.println("Operation canceled.");
		    System.exit(0); // Close the application
		});

	}
	
	// implementation logic for converting Heic to JPG 
	
	public void convertHeicToJpg(File sourceFile, File destinationFile) {
		
		if (!sourceFile.exists()) {
			JOptionPane.showMessageDialog(null, "Source file does not exist: " + sourceFile.getAbsolutePath(), "File Error", JOptionPane.ERROR_MESSAGE);
		    return;
		} else {
			System.out.println("The file exists.");
		}
		
		if (!sourceFile.canRead()) {
	        JOptionPane.showMessageDialog(null, "Cannot read source file: " + sourceFile.getAbsolutePath(), "File Error", JOptionPane.ERROR_MESSAGE);
		    return;
		} else {
			System.out.println("Sccessfully raeading file conetnts.");
		}
		
		System.out.println("The absolute path of the source file is: " + sourceFile.getAbsolutePath());;
		
		// avutil.av_log_set_level(avutil.AV_LOG_INFO);
		
		 // Get the path to magick.exe from the resources folder
        String magickPath = getClass().getClassLoader().getResource("imagemagick/magick.exe").getPath();
		System.out.println("The magick path is: " + magickPath);
		try {
            // Extract magick.exe from resources to a temporary directory
            File tempMagick = new File(System.getProperty("java.io.tmpdir"), "magick.exe");
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("imagemagick/magick.exe")) {
                if (in == null) {
                    throw new FileNotFoundException("magick.exe not found in resources/imagemagick");
                }
                Files.copy(in, tempMagick.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Use ProcessBuilder to invoke ImageMagick
            ProcessBuilder processBuilder = new ProcessBuilder(
            		tempMagick.getAbsolutePath(),
            	   //  "-debug", "All", // Enable debugging for troubleshooting
            	    // "-configure",
            	    // tempMagick.getParent(), // Point to the temporary directory where delegates.xml exists
            	   //  "convert",
            	    sourceFile.getAbsolutePath(),
            	    destinationFile.getAbsolutePath()
            );

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read the output of the process
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Print each line of output (useful for debugging)
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
            	
            	JOptionPane.showMessageDialog(null, "HEIC to JPG conversion successful!", "Success", JOptionPane.INFORMATION_MESSAGE);            } else {
        		sourceFileLabel.setText("No file chosen...");
                destinationFolderLabel.setText("No folder selected...");
                
        	 } 

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Conversion Error", JOptionPane.ERROR_MESSAGE);        }
		
		}
	
	private void convertMovToAvchdWithJave(File source, File target) {
		// TODO Auto-generated method stub
		
		System.out.println("Source is: " + source.getAbsolutePath() + " and target is: " + target.getAbsolutePath());

		
		 try {
			 	
			 // Generate intermediate MPEGTS file
		        File intermediateFile = new File(target.getParent(), target.getName().replace(".m2ts", ".mpegts"));
			 
			 	// Extract source video properties
		        MultimediaObject multimediaObject = new MultimediaObject(source);
		        int sourceWidth = multimediaObject.getInfo().getVideo().getSize().getWidth();
		        int sourceHeight = multimediaObject.getInfo().getVideo().getSize().getHeight();
		        double aspectRatio = (double) sourceWidth / sourceHeight;
		        
		        System.out.println("Source resolution: " + sourceWidth + "x" + sourceHeight);
		        System.out.println("Aspect Ratio: " + aspectRatio);

		        // Adjust the resolution to ensure compliance with AVCHD standards
		        int targetWidth = 1920; // Default width
		        int targetHeight = (int) (targetWidth / aspectRatio); // Calculate height dynamically

		        if (targetHeight % 2 != 0) {
		            targetHeight++; // Ensure height is divisible by 2
		        }
		        
	            // Set up audio attributes
	            AudioAttributes audio = new AudioAttributes();
	            audio.setCodec("ac3"); // AVCHD typically uses AC-3 audio codec
	            audio.setBitRate(192000); // 192 kbps
	            audio.setChannels(2); // Stereo
	            audio.setSamplingRate(48000); // 48 kHz

	            // Set up video attributes
	            VideoAttributes video = new VideoAttributes();
	            video.setCodec("h264"); // AVCHD uses MPEG-2 video codec "mpeg2video"
	            video.setBitRate(15000000); // 15 Mbps
	            video.setFrameRate(30); //(29.97); // Standard frame rate for AVCHD
	            video.setSize(new VideoSize(1920, 1080)); // Full HD resolution

	            // Set up encoding attributes
	            EncodingAttributes attrs = new EncodingAttributes();
	            attrs.setOutputFormat("mpegts"); // Target format
	            attrs.setAudioAttributes(audio);
	            attrs.setVideoAttributes(video);

	            // Perform the conversion
	            Encoder encoder = new Encoder();
	            encoder.encode(new MultimediaObject(source), target, attrs);
	            
	         // Convert MPEGTS to MTS using FFmpeg
	            FFmpegService.convertToMTS(intermediateFile);

	            JOptionPane.showMessageDialog(null, "MOV to AVCHD conversion successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            
	            sourceFileLabel.setText("No file chosen...");
	            destinationFolderLabel.setText("No folder selected...");
	            
	        } catch (EncoderException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Conversion failed: " + e.getMessage(), "Conversion Error", JOptionPane.ERROR_MESSAGE);	        }
	}
	
	
}

