package home.multimeida.mmconverter;

import java.awt.EventQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import home.multimeida.mmconverter.MMConverterInterface;


@SpringBootApplication
public class DemoApplication {

	 public static void main(String[] args) {
	        // Disable headless mode
	        System.setProperty("java.awt.headless", "false");

	        SpringApplication.run(DemoApplication.class, args);

	        // Launch the GUI
	        EventQueue.invokeLater(() -> {
	            try {
	            	MMConverterInterface frame = new MMConverterInterface();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }

}
