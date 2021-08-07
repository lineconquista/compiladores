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
		return id + "= _key."+ (var.getType()==RawrVariable.NUMBER? "nextDouble();":"nextLine();") ;
	}
	
	public String getId() {
		return id;
	}
	
}
