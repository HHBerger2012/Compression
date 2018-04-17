public class CustomHashSet {
	
	static CustomLinkedList[] words = null;
	private static int tabelSize;
	
	public CustomHashSet (int size) {
		words = new CustomLinkedList[size];
		tabelSize = size;
		
		for (int i=0;i<=size-1;i++)
	    {
	    	if (words[i]==null)
	    	{
	    		words[i] = new CustomLinkedList();
	    	}
	    }
		
		 for(int i = 32; i < 126; i++){
	        	char current = (char)i;
	        	Dictionary currentDict = new Dictionary(String.valueOf(current), (short)(i - 32));
	        	int index = currentDict.getIntValue() % size;
	        	words[index].add(currentDict);
	        }
		 
	        char current1 = (char)10;
			Dictionary currentDict1 = new Dictionary(String.valueOf(current1), (short)93);
			int index = getValue(String.valueOf(current1), tabelSize);
			words[index].add(currentDict1);
	}
	
	public static int getValue(String x, int size){
		
		int total = 0;
		
		for(int i = 0; i < x.length(); ++i) {
			total += x.charAt(i);
		}
		
		return total % size;
	}
	public static boolean arrayContains(String x) {
		int index = getValue(x, tabelSize);
		
		if (words[index].contains(x) == -1){
			return false;
		}else {
			return true;
		}	
	}
	
	public static short keyOf(String x) {
		int index = getValue(x, tabelSize);
		return words[index].contains(x);
	}
	
	public static double howFull() {
		int numberOfFull = 0;
		for(int i = 0; i < tabelSize; ++i) {
			if(!(words[i].isEmpty())){
				++ numberOfFull;
			}
		}
		
		return (numberOfFull/tabelSize) * 100;
	}
	public static void add(String x, short y) {
		words[getValue(x, tabelSize)].add(new Dictionary(x,y));
	}
	
	public static int longestLength() {
		int large = 0;
		
		for(int i = 0; i < tabelSize; ++i) {
			if(words[i].size() > large) {
				large = words[i].size();
			}
		}	
		return large;	
	}
	
	public static int averageLength() {
		return total()/ tabelSize;
	}

	public static int total() {
		int total = 0;
		
		for(int i = 0; i < tabelSize; ++i) {
				total += words[i].size();
		}
			 return total;
	}
	
	public static CustomLinkedList[] getwords(){
		return words;
	}
}
