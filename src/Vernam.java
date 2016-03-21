import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Vernam {
	public static void main(String[] args) throws IOException {
		try {					
			if (args[0].equals("g") && Integer.parseInt(args[2])>=0) generateKey(args[1], Integer.parseInt(args[2]));
			else if (args[0].equals("e") && Integer.parseInt(args[3])>=0) encryption(args[1], args[2], Integer.parseInt(args[3]));
			else if (args[0].equals("d") && Integer.parseInt(args[3])>=0) decryption(args[1], args[2], Integer.parseInt(args[3]));
			else System.err.println("Something wrong!");
		} catch (Exception e) {
			System.err.println("ERROR: "+ e.toString());
		}
	}

	private static void decryption(String filein, String filekey, int offset) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(filein);
			out = new FileOutputStream("Vernam_plain.txt");
			String key = readKey(filekey);
			String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
			int i; int start_at = offset; char c;
			// read cipher text
			while ((i=in.read())!=-1) {
				c = (char) i;
				int temp1 = -1, temp2 = -1;
				// get index of cipher char on the alp map
				for (int j = 0; j < alp.length(); j++) {
					if (alp.charAt(j)==c) temp1 = j;
				}
				// find index of cipher char that map on the alp map
				for (int k = 0; k < alp.length(); k++) {
					if (alp.charAt(k)==key.charAt(start_at)) temp2=k;
				}
				// actually do addition, and write cipher char to output file
				if (temp1 != -1 && temp2 != -1) {
					if(temp1>=temp2) {
						out.write(alp.charAt((temp1-temp2)%alp.length()));
						System.out.print(alp.charAt((temp1-temp2)%alp.length()));
					}
					else {
						// must do this because java does not return 1 for (-5 % 6)
						out.write(alp.charAt(alp.length()-(temp2-temp1)%alp.length()));
						System.out.print(alp.charAt(alp.length()-(temp2-temp1)%alp.length()));
					}
					start_at++;
				}
				
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}

	private static void encryption(String filein, String filekey, int offset) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(filein);
			out = new FileOutputStream("Vernam_cipher.txt");
			String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
			String key = readKey(filekey);
			int i; int start_at = offset; char c;
			while ((i=in.read())!=-1) {
				c = (char) i;
				int temp1 = -1, temp2 = -1;
				// find index of plain char in alp to return its index value
				for (int j = 0; j < alp.length(); j++) {
					if (alp.charAt(j)==c) temp1=j; 
				}
				// find index of char that map to plain char on the key map
				for (int k = 0; k < alp.length(); k++) {
					if (alp.charAt(k)==key.charAt(start_at)) temp2=k;
				}
				// actually do addition, and write cipher char to output file
				if (temp1 != -1 && temp2 != -1) {
					out.write(alp.charAt((temp1+temp2)%alp.length()));
					System.out.print(alp.charAt((temp1+temp2)%alp.length()));
					start_at++;
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}

	private static void generateKey(String fileout, int key_length) throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileout);
			String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
			Random rand = new Random();
			for (int i = 0; i < key_length; i++) {
				out.write(alp.charAt(rand.nextInt(alp.length())));
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (out != null) out.close();
		}
	}
	
	private static String readKey(String filekey) throws IOException {
		FileInputStream in = null;
		StringBuilder vernam_key = new StringBuilder();
		try {
			in = new FileInputStream(filekey);
			int i;
			while ((i = in.read())!=-1) {
				vernam_key.append((char)i);
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null) in.close();
		}
		return vernam_key.toString();
	}
}
