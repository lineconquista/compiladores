package datastructures;

public abstract class RawrSymbol {
	protected String name;
	public RawrSymbol(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "RawrSymbol [name="+name+"]";
	}
}
