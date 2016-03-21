import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Subst {

	public static void main(String[] args) throws IOException {
		if (args[0].equals("g")) generateKey(args[1]);
		else if (args[0].equals("e")) encryption(args[1], args[2]);
		else if (args[0].equals("d")) decryption(args[1], args[2]);
		else System.err.println("Something wrong!");
	}

	private static void generateKey(String outfile) throws IOException {
		FileOutputStream key = null;
		try {
			key = new FileOutputStream(outfile);
			String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
			String subst_map = shuflle_string(alp);
			// write key to key file
			for (int i = 0; i < subst_map.length(); i++) {
				key.write(subst_map.charAt(i));
			}
		} finally {
			if (key != null) key.close();
		}
	}
	
	private static String readKey(String filekey) throws IOException {
		StringBuilder subst_map = new StringBuilder();
		FileInputStream in = null;
		try {
			in = new FileInputStream(filekey);
			int i; char c;
			// read file till file end
			while ((i = in.read()) != -1) {
				// convert back to char
				c = (char) i;
				subst_map.append(c);
			}
		} finally {
			if (in != null) in.close();
		}
		return subst_map.toString();
	}

	private static void encryption(String filein, String filekey) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			in = new FileInputStream(filein);
			out = new FileOutputStream("Subst_cipher.txt");
			String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
			// get key map
			String subst_map = readKey(filekey);
			
			// reading input file (plain text)
			int i; char c;
			// read till file end
			while ((i=in.read()) != -1) {
				// convert from int back to char
				c = (char) i;
				// start mapping plain char to subst_map
				for (int k = 0; k < alp.length(); k++) {
					if(alp.charAt(k)==c) {
						out.write(subst_map.charAt(k));	// write cipher to output
						System.out.print(subst_map.charAt(k));
					}
					
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}

	private static void decryption(String filein, String filekey) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(filein);
			out = new FileOutputStream("Subst_plain.txt");
			String alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,:;()-!?$'\"\n0123456789";
			String subst_map = readKey(filekey);
			int i; char c;
			// read file till file end
			while ((i=in.read())!=-1) {
				// convert back to char
				c = (char) i;
				// map cipher char to alp map
				for (int j = 0; j < alp.length(); j++) {
					if (subst_map.charAt(j)==c) {
						out.write(alp.charAt(j));
						System.out.print(alp.charAt(j));
					}
					
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
	
	private static String shuflle_string(String alp) {
		ArrayList<Character> cl = new ArrayList<Character>();
		for (int i = 0; i < alp.length(); i++) {
			cl.add(alp.charAt(i));
		}
		// shuffle the array list;
		Collections.shuffle(cl);
		// convert back to string
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < cl.size(); i++) {
			s.append(cl.get(i));
		}
		return s.toString();
	}
}
