public class Main
{	
	
	public static void main(String [] args){
		int [] array = {2,-1,0,4,5,2,6,9};
		SortUtil.getInstance().sort(array);
		PrintUtil.getInstance().print(array);		
	}
}