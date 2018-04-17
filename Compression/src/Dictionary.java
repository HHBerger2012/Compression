
public class Dictionary {
	
	private String code;
	private short key;
	
	public Dictionary(String code, Short key) {
		this.key = 	key;
		this.code = code;
	}
	
	public String getCode()
	{
		return code;
	}
	
	public short getKey() {
		return key;
	}
	
	public int getIntValue(){
	int total = 0;
	
	for(int i = 0; i<code.length();++i) {
		total = total + (int)code.charAt(i);
		}
	
	return total;
	}
	
	
	
}
