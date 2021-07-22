package datastructures;

public class RawrVariable extends RawrSymbol{
	
	public static final int NUMBER = 0;
	public static final int TEXT = 1;
	private int type;
	private String value;
	
	public RawrVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String generateJavaCode () {
		String str = type==NUMBER? "double" : "String";
		return str + " " + super.name + ";";
	}
	 
}