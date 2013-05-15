/*
 * Author: Sam Backus
 * Date: Feb 7, 2013
 */

package cyrus.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FileUtils {

	public static Collection<String> read(String fileName) {
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines;
	}

	public static void outputToFile( String output, String target) {
		try{ 
			FileWriter fstream = new FileWriter(target);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			out.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static String getListAsString(Collection list) {
		String str="";
		for (Object thing: list){
			str= str+(thing+"\n");
		}
		return str;
	}
}
