public class DateFormatConverter{
	public static final String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    

    /**
    	The  following method will convert the date from a number format to a word format
		@param date a string argument of the date
		@return the date format as a string monthname date, year 
    */

	public static String numbersToWords(String date){
		//Here we want to convert from the all numbers format to the monthname date, year format. 
		//04/25/1955 -> April 25, 1955

		String[] dateParts = date.split("/");
		int monthNumber = Integer.parseInt(dateParts[0]);
		String monthNameString = monthName[monthNumber - 1];

		String result = monthNameString+" "+Integer.parseInt(dateParts[1])+", "+dateParts[2];


		return result;

    }

    /**
    	The following method will convert the date from word format to number format
		@param date a string argument of the date 
		@return the number representation of the date in the month/date/year format

    */

    public static String wordsToNumbers(String date){
		//Here we want to convert from the all numbers format to the monthname date, year format. 
		// April 25, 1955 -> 04/25/1955

    	String rmComma = date.replace(",", "");

    	String[] splitDate = rmComma.split(" ");
    	String monthString = "";

        

        //This for loop will iterate through the array until the value is true and will save a parsed result of the output
    	for(int i = 0; i<monthName.length; i++){
    		if(splitDate[0].equals(monthName[i])){
    			int y = i+1;
    			String temp = y+"";
    			monthString = String.format("%02d", Integer.parseInt(temp));
    		}

    	}

        //The result string prints the complete formatted version of the output onto the screen.
    	String result = monthString+"/"+String.format("%02d", Integer.parseInt(splitDate[1]))+"/"+splitDate[2];

    	return result;


    }

}
