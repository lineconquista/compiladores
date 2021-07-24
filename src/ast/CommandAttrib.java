package ast;

import datastructures.RawrSymbolTable;
import datastructures.RawrVariable;

public class CommandAttrib extends AbstractCommand {
	
	private String id;
	private String expr;
	
	public CommandAttrib (String id, String expr, RawrSymbolTable symbolTable) {
			this.id = id;
			this.expr = expr;
			((RawrVariable) symbolTable.get(id)).setValue(expr);
	}
	
	@Override
	public String generateJavaCode() {
		return id + " =" +expr+";" ;
	}
	
}
