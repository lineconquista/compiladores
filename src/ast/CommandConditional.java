package ast;

import java.util.ArrayList;

public class CommandConditional extends AbstractCommand {
	
	private String condition;
	private ArrayList<AbstractCommand> listTrue;
	private ArrayList<AbstractCommand> listFalse;
	
	public CommandConditional (String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf ) {
			this.condition = condition;
			this.listTrue = lt;
			this.listFalse = lf;
	}
	
	@Override
	public String generateJavaCode() {
		
		StringBuilder str = new StringBuilder();
		
		str.append("if ("+condition+") {");
		
		for(AbstractCommand cmd: listTrue) {
			str.append(cmd.generateJavaCode());
		}
		
		str.append("}");
		
		if (listFalse.size() > 0) {
			str.append("else {");
			
			for(AbstractCommand cmd: listFalse) {
				str.append(cmd.generateJavaCode());
			}
			
			str.append("}");
		}
		
		return str.toString();
	}
	
}