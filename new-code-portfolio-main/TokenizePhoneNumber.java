
/**
	@author Jarnell Hayes
	@version 1.1

*/


public class TokenizePhoneNumber{
	public static String convertPhoneNumber(String phoneNumber){
		String removeCharFromAreaC = splitAreaCodeFromNumber(phoneNumber);

		String[] nums = removeCharFromAreaC.split("-");
		String result = nums[0] + nums[1];

		return result;
	}

public static String splitAreaCodeFromNumber(String number){
	String[] values = number.split(" ");
	String areaCode = values[0].replace("(", "");
	areaCode = areaCode.replace(")", "");

	String result = areaCode+values[1];

	return result;
}
}