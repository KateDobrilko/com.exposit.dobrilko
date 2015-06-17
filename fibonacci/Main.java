import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main{
	public static void main(String [] args){
		System.out.println("Input number of iterations:");
		
		try{
			Scanner scanner = new Scanner(System.in);
			
		    ArrayList<Integer> array = FibonacciGenerator.getInstance().generate(scanner.nextInt());
		    if( array == null ){
			    PrintUtil.getInstance().print("Integer type overflow.");
		    }
		    else{
		        PrintUtil.getInstance().print(array);
		    }
		}
		catch(InputMismatchException e){
			e.printStackTrace();
		}
		
	}
}