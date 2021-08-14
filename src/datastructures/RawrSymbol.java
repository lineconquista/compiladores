package datastructures;

public abstract class RawrSymbol {
	
	protected String name;
	protected boolean used = false;
	
	public abstract String generateJavaCode();
	
	public RawrSymbol(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
	
	public void used () {
		this.used = true;
	}
	
	public boolean getUsed () {
		return this.used;
	}
}
