package datastructures;

public class RawrVariable extends RawrSymbol{
	
	public static final int DOUBLE = 0;
	public static final int TEXT = 1;
	public static final int INT = 2;
	public static final int BOOLEAN = 3;
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
		String str = "";
		if (type==DOUBLE) {
			str = "double";
		} else if (type==TEXT) {
			str = "String";
		} else if (type==INT) {
			str = "int";
		} else if (type==BOOLEAN){
			str = "boolean";
		}
		return str + " " + super.name + ";";
	}
	 
}

