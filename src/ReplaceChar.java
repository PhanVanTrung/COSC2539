import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ReplaceChar {

	public static void main(String[] args) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		PrintStream ps = null;
		try {
			in = new FileInputStream("E:\\Eclipse Workspace\\COSC2539\\bin\\msg4.enc");
			out = new FileOutputStream("E:\\Eclipse Workspace\\COSC2539\\bin\\7_1.dec");
			ps = new PrintStream(out);
			
			StringBuilder s = new StringBuilder();
			int i; char c;
			while( (i=in.read()) != -1 ) {
				c = (char) i;
				if (c == '>' || c == '<'){continue;}
//				if (c == '\n') System.out.println("new line !!!");
				s.append(c);
			}
			String str = s.toString();
			
//			// ----------------- Use for cipher 7 ----------------
//			str = str.replace(",", "F");
//			str = str.replace("K", ",");
//			str = str.replace("T", "K");
//			str = str.replace("-", "T");
//			str = str.replace("Z", "V");
//			str = str.replace("R", "A");
//			str = str.replace(")", "R");
//			str = str.replace("I", "O");
//			str = str.replace("L", "S");
//			str = str.replace("N", "L");
//			str = str.replace("\n", "N");
//			str = str.replace("Y", "\n");
//			str = str.replace("J", "Y");
//			str = str.replace("H", "J");
//			str = str.replace("'", "U");
//			str = str.replace("5", "H");
//			str = str.replace("E", "\'");
//			str = str.replace("B", "E");
//			str = str.replace("$", "B");
//			str = str.replace("\"", "I");
//			str = str.replace("M", "Q");
//			str = str.replace("P", "M");
//			str = str.replace("!", "P");
//			str = str.replace("W", "G");
//			str = str.replace("9", "W");
//			str = str.replace("0", ".");
//			str = str.replace("X", "D");
//			str = str.replace("1", "C");
//			str = str.replace("(", "X");
			
			// ----------------- Use for cipher 4 ----------------
			str = str.replace(";", "R");
			str = str.replace("9", "1");
			str = str.replace("G", "'");
			str = str.replace(".", "G");
			str = str.replace(":", "V");
			str = str.replace("Y", "B");
			str = str.replace("K", ".");
			str = str.replace("3", "K");
			str = str.replace("F", "M");
			str = str.replace("E", "F");
			str = str.replace("J", "W");
			str = str.replace("D", "Y");
			str = str.replace("C", "D");
			str = str.replace("U", "C");
			str = str.replace("6", "I");
			str = str.replace(")", "U");
			str = str.replace("0", "T");
			str = str.replace("X", "0");
			str = str.replace(" ", "S");
			// need to add replace("\r", "") otherwise it won't change from "\n" to " "
			str = str.replace("\n", " ").replace("\r", "");
			
			str = str.replace("N", "\n");
			str = str.replace("H", "O");
			str = str.replace("L", "H");
			str = str.replace("A", "L");
			str = str.replace("(", "A");
			str = str.replace("P", "E");
			str = str.replace("5", "P");
			str = str.replace("2", "N");
			str = str.replace("?", "Y");
			
			ps.print(str);
//			System.out.println(str);
			
		} catch (Exception e) {
			System.err.println("ERROR with files!" + e.getMessage().toString());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
			if (ps != null) ps.close();
		}
	}

}
