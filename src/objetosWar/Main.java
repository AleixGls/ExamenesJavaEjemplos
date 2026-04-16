package objetosWar;

public class Main {
	public static void main(String[] args){
		System.out.println(header("=",60,"Title"));
	}
	
	public static String header(String fillchar ,int total_size,String string) {
		String result = string;
		
		for (int i= 0; i < (total_size - string.length())/2 ; i++) {
			result = fillchar+result+fillchar;
		}
		
		return result;
	}
}
