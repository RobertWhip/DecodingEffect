import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.util.*;

import decoding.*;

public class Main {
	private static final String TITLE = "test";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 200;
	
	private int fontSize = 24;	
	private static JLabel label; // the label that will show the decoding effect
	private JPanel panel;
	private JFrame frame;
	
	// creating GUI
	Main() {
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.BLACK);
		
		label = new JLabel("", JLabel.CENTER);
		label.setFont(new Font("monospaced", Font.BOLD, fontSize));
		label.setForeground(Color.ORANGE);
		
		panel.add("Center", label);
		
		frame = new JFrame(TITLE);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		for (Window w : Window.getWindows()) {
                    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(w);
        }
	}
	
	//Reading text from a text file and putting it into a list
	private ArrayList<String> read (String file) throws IOException {
		ArrayList<String> list = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s = "";
			while ((s = in.readLine()) != null) {
				list.add(s);
			}
		} catch (IOException e) {
			throw new IOException();
		}
		return list;
	}
	
	public static void main(String... args) throws IOException, InterruptedException {
		ArrayList<String> texts; // the list of texts
		Main main = new Main();
		
		
		BinaryDecodingEffect decode = new BinaryDecodingEffect(
			new TextSettingBehavior() {
				// Overriding TextSettingBehavior.setText() method
				@Override
				public void setText(String text) {
					label.setText(text);
				}
			}
		);
		
		decode.decoding("Some text."); // decoding a String value
		
		decode.setTextChangeSleepTime(1499); // time to sleep between changes
		
		decode.setDecodingSleepTime(49); // decoding speed
		
		texts = main.read("input.txt"); // reading text from a file
		
		decode.listDecoding(texts); // decoding a list of String values
	}
}
