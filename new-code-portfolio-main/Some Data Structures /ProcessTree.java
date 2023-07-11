import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

/**

@version 1.1
@author  Jarnell Hayes

This program will read the data from a text file and create a process tree 
with levels labeled along with the process ID
*/

public class ProcessTree{

	public static void main(String[] args) throws Exception{
		String locate = "ProcessRecords.txt";
		String line = " ";
		BufferedReader buffreader = new BufferedReader(new FileReader(locate));
		String[] vals = {};
		String[] vals2 = {};

		List<String> lines = new ArrayList<String>();
		int i = 0;
		int j = 0;
	
		while((line = buffreader.readLine()) != null){
			vals = line.split(",");
			lines.add(vals[0]);

		}

		String[] doubleA = new String[lines.size()];

		lines.toArray(doubleA);
		Set<String> tree = new TreeSet<>();

		while( i != doubleA.length-1 ){
			tree.add(doubleA[i]);
			i++;
		}
		int level = 0;

		int multi = 2;
		int nodecount = 0;
		for(String value : tree){
			int ref = 4;

			if(nodecount == 0){
				level = 0;
			}
			if(nodecount != 0 && nodecount <= 2){
				level = 1;
			}else if (nodecount > 2){
				level = 2;
				
				if(nodecount > ref + multi ){
					level = level + 1;
				}
			}

			System.out.println(value + " Level: " + level);

			nodecount++;
		
		}
			
	
}

}