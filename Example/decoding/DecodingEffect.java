package decoding;

import java.util.List;

public interface DecodingEffect {
	public void decoding(String text) throws InterruptedException;
	public void listDecoding(List<String> texts) throws InterruptedException;
}