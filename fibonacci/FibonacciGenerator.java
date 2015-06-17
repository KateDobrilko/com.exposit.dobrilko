import java.util.ArrayList;

public class FibonacciGenerator{
	private static FibonacciGenerator instance;
	
	private FibonacciGenerator(){}
	
	public static FibonacciGenerator getInstance(){
		if(instance==null){
			instance = new FibonacciGenerator();
		}
		return instance;
	}
	
	public ArrayList<Integer> generate(Integer number){
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(0);
		array.add(1);
		for(int i=2;i<number;i++){
			Integer result = array.get(i-2)+array.get(i-1);
			if(result>=0){
				array.add(result);
			}
		    else{
				array = null;
				break;
			}
		}
		return array;
	}
}