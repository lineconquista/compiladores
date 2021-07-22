package ast;

import datastructures.RawrVariable;

public class CommandLeitura extends AbstractCommand {
	
	private String id;
	private RawrVariable var;
	
	public CommandLeitura (String id, RawrVariable var) {
			this.id = id;
			this.var = var;
	}
	
	@Override
	public String generateJavaCode() {
		return id + "= _key."+ (var.getType()==RawrVariable.NUMBER? "nextDouble();":"nextLine();") ;
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id="+ id + "]";
	}
}
