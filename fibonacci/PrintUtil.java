import java.util.ArrayList;

public class PrintUtil{
	private static PrintUtil instance;
	
	private PrintUtil(){}
	
	public static PrintUtil getInstance(){
		if(instance==null){
			instance = new PrintUtil();
		}
		return instance;
	}
	public void print(ArrayList<Integer> array){
	    StringBuilder sb = new StringBuilder();
		   for(Integer element: array){
			   sb.append(element);
			   sb.append(" ");
		    }
        System.out.println(sb.toString());
    }
	
	public void print(String str){
        System.out.println(str);
    }
}