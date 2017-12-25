package decoding;

import java.util.List;
import java.util.Iterator;
import java.io.*;

public class BinaryDecodingEffect implements DecodingEffect {
	private int decodingSleepTime = 50;
	private int textChangeSleepTime = 1500;
	private TextSettingBehavior guiText;
	
	public BinaryDecodingEffect(TextSettingBehavior guiText, int decodingSleepTime, int textChangeSleepTime) {
		this.guiText = guiText;
		this.decodingSleepTime = decodingSleepTime;
		this.textChangeSleepTime = textChangeSleepTime;
	}
	
	public BinaryDecodingEffect(TextSettingBehavior guiText) {
		this.guiText = guiText;
	}
	
	public void setTextGUI (TextSettingBehavior guiText) {
		this.guiText = guiText;
	}
	
	public void setDecodingSleepTime(int sleepTime) {
		decodingSleepTime = sleepTime;
	}
	
	public int getDecodingSleepTime() {
		return decodingSleepTime;
	}
	
	public void setTextChangeSleepTime(int sleepTime) {
		textChangeSleepTime = sleepTime;
	}
	
	public int getTextChangeSleepTime() {
		return textChangeSleepTime;
	}
	
	public void listDecoding(List<String> list) throws InterruptedException {
		String s = "";
		try {
			Iterator<String> iterator = list.iterator();
			
			while(iterator.hasNext()) {
				s = iterator.next();
				decoding(s);
				sleep();
			}
			
		} catch (InterruptedException e) {
			throw new InterruptedException();
		}
	}
	
	public void decoding(String text) throws InterruptedException {
		try {
			for (int i = 0; i < text.length(); i++) {
				guiText.setText(getSubstring(text, i) + getBinaryString(text.length()-i-1));
				Thread.sleep(decodingSleepTime);
			}
		} catch (InterruptedException e) {
			throw new InterruptedException();
		} 
	}
	
	private String getBinaryString(int length) {
		String s = "";
		for (int i = 0; i < length; i++)
			s += ((int)(Math.random()*2)%2);
		return s;
	}
	
	private String getSubstring(String s, int length) {
		return s.substring(0, length);
	}
	
	private void sleep() throws InterruptedException {
		try {
			Thread.sleep(textChangeSleepTime);
		} catch (InterruptedException e) {
			throw new InterruptedException();
		} 
	}
}