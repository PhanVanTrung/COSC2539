import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

public class CountFrequency {

	public static void main(String[] args) throws IOException {
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		FileInputStream in = null;
		FileOutputStream out = null;
		PrintStream ps = null;
		try {
			in = new FileInputStream(args[0]);
			out = new FileOutputStream(args[1]);
			ps = new PrintStream(out);
			int i; char c;
//			StringBuilder s = new StringBuilder();
			while ( (i=in.read()) != -1) {
				c = (char) i;
				if (c == '<' || c == '>') continue;
//				s.append(c);
				Integer val = map.get(new Character(c));
				if (val != null) {
					map.put(c, new Integer(val + 1));
				} else {
					map.put(c, 1);
				}
			}
//			int sum = 0;
			for (Character cc : map.keySet()) {
				ps.print(cc + " : " + map.get(cc) + "\n");
//				sum += map.get(cc);
				System.out.println(cc + " : " + map.get(cc));
			}
//			System.out.println("sum: " + sum + " ---- string length: " + s.length());
			
		} catch (Exception e) {
			System.err.println("Error with file!!");
		} finally {
			if (in != null) in.close();
			if (out != null ) out.close();
			if (ps != null) ps.close();
		}
	}

}
