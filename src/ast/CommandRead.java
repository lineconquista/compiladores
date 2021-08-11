package ast;

import datastructures.RawrVariable;

public class CommandRead extends AbstractCommand {
	
	private String id;
	private RawrVariable var;
	
	public CommandRead (String id, RawrVariable var) {
			this.id = id;
			this.var = var;
	}
	
	@Override
	public String generateJavaCode() {
		String str = id + "= _key.";
		if (var.getType()==RawrVariable.DOUBLE) {
			str += "nextDouble();";
		} else if (var.getType()==RawrVariable.TEXT) {
			str += "nextLine();";
		} else if (var.getType()==RawrVariable.BOOLEAN) {
			str += "nextBoolean();";
		} else {
			str += "nextInt();";
		}
		return str;
	}
	
	public String getId() {
		return id;
	}
	
}
