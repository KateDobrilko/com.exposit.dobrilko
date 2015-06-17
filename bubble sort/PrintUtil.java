public class PrintUtil{
	private static PrintUtil instance;
	
	private PrintUtil(){}
	
	public static PrintUtil getInstance(){
		if(instance==null){
			instance = new PrintUtil();
		}
		return instance;
	}
	public void print(int [] array){
	    StringBuilder sb = new StringBuilder();
		   for(int element: array){
			   sb.append(element);
			   sb.append(", ");
		    }
        System.out.println(sb.toString());
    }
}