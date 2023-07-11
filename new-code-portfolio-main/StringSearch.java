import java.util.Arrays;



public class StringSearch{
	public static void main(String[] args){


		String[] string = new String[]{"c","l","a","i","r","e"};

		String[] string2 = new String[]{"a","i","r"};


		for(int i = 0; i<string.length;i++){
			for(int j = 0;j<string2.length;j++){
				if(string[i].equals(string2[j])){
					System.out.println(string[i] + " The indexes where they are found are " + i);
				}
			}
		}
	}
}