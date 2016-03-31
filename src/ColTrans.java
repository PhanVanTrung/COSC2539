import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ColTrans {
	public static void main(String[] args) throws IOException {
		int key; String filein;
		try {
			key = Integer.parseInt(args[2]);
			filein = args[1];
			if (args[0].equals("e")&&key>=1) encryption(filein, key);
			else if (args[0].equals("d")&&key>=1) decryption(filein, key);
			else System.err.println("Something wrong!");
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		}

	}

	private static void encryption(String filein, int key) throws IOException {
//		String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
		ArrayList<Character> s = new ArrayList<Character>();
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(filein);
			out = new FileOutputStream("ColTrans_cipher.txt");
			int i; char c ;
			// read until end
			while ((i=in.read()) != -1) {
				// convert int (i) to char (c)
				c = (char) i;
				// store read lines in a list of characters
				s.add(c);
			}
			// compute the length of cipher text (add space if needed)
			if(s.size()%key != 0) {
				// compute how many spaces to add
				int temp = key - s.size()%key;
				for (int k = 0; k < temp; k++) {
					s.add(' ');
				}
			}
			// start re-arrange the characters with the key
			for (int k = 0; k < key; k++) {
				for (int n = k; n < s.size(); n += key ) {
					out.write(s.get(n));
					System.out.print(s.get(n));
				}
			}
			// length of actual cipher text
//			System.out.println(s.size());
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}

	private static void decryption(String filein, int key) throws IOException {
//		String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
		ArrayList<Character> s = new ArrayList<Character>();
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(filein);
			out = new FileOutputStream("ColTrans_plain.txt");
			int i; char c ;
			// read until end
			while ((i=in.read()) != -1) {
				// convert int (i) to char (c)
				c = (char) i;
				// store read lines in a list of characters
				if (c != '>' && c != '<' ) {
					s.add(c);
				}
			}
			// we need to infer from key to "new key" dekey to easily decrypt the cipher text
			int dekey = s.size()/key;
			// start re-arrange the characters with the dekey
			for (int k = 0; k < dekey; k++) {
				for (int n = k; n < s.size(); n += dekey ) {
					out.write(s.get(n));
					System.out.print(s.get(n));
				}
			}
			// length of actual cipher text
//			System.out.println(s.size());
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
}
