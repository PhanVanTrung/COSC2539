import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ceasar { 
	public static void main (String [] args) throws FileNotFoundException, IOException{
		int key; String filename;
		try {
			key = Integer.parseInt(args[2]);
			filename = args[1];
			if (args[0].equals("e")) encryption(key, filename);
			else if (args[0].equals("d")) decryption(key, filename);
			else System.err.println("Something wrong!");
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		}
	}

	public static String encryption(int key, String filename) throws FileNotFoundException, IOException{
		FileInputStream in = null;
		FileOutputStream out = null;
		String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
		try {
			in = new FileInputStream(filename);
			out = new FileOutputStream("Ceasar_cipher.txt");
			// reading file
			int i; char c;
			// read file till the end
			while ((i = in.read()) != -1){
				// read, convert int to char
				c = (char) i;
				// find the char in alp, return index in alp
				for (int j = 0; j < alp.length(); j++) {
					if (c==alp.charAt(j)) {
						// when found --> 
						out.write(alp.charAt((j+key)%alp.length()));
						System.out.print(alp.charAt((j+key)%alp.length()));
					}
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null){
				in.close();
			}
			if (out != null){
				out.close();
			}
		}
		return "";
	}
	public static String decryption(int key, String filename) throws FileNotFoundException, IOException{
		FileInputStream in = null;
		FileOutputStream out = null;
		String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
		try {
			in = new FileInputStream(filename);
			out = new FileOutputStream("Ceasar_plain.txt");
			// reading file
			int i;
			char c;

			// read file till the end
			while ((i = in.read()) != -1){
				// read, convert int to char
				c = (char) i;
				// find the char in alp, return index in alp
				for (int j = 0; j < alp.length(); j++) {
					if (c==alp.charAt(j)) {
						// when found --> 
						if (j >= key) {
							out.write(alp.charAt((j-key)%alp.length()));
							System.out.print(alp.charAt((j-key)%alp.length()));
						} else {	// j < key
							out.write(alp.charAt(alp.length() + (j-key)));
							System.out.print(alp.charAt(alp.length() + (j-key)));
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null){
				in.close();
			}
			if (out != null){
				out.close();
			}
		}
		return "";
	}
}

