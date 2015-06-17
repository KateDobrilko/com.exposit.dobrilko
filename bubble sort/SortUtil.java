public class SortUtil{
	private static SortUtil instance;
	
	private SortUtil(){}
	
	public static SortUtil getInstance(){
		if(instance==null){
			instance = new SortUtil();
		}
		return instance;
	}
	public void sort(int[] array){
		for(int j=0;j<array.length-1; j++){
		    for(int i = 0; i < array.length-1; i++){
			    if(array[i] > array[i+1]){
				    int temp = array[i];
		            array[i]=array[i+1];
		            array[i+1]=temp;
			    }
		    }
		}
	}
}