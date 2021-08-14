package ast;

import java.util.ArrayList;

public class CommandConditional extends AbstractCommand {
	
	private String condition;
	private ArrayList<AbstractCommand> listTrue;
	private ArrayList<AbstractCommand> listFalse;
	private Boolean conditionalElseIf;
	private Boolean conditionalElse;
	
	public CommandConditional (String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf, Boolean conditionalElseIf, Boolean conditionalElse) {
			this.condition = condition;
			this.listTrue = lt;
			this.listFalse = lf;
			this.conditionalElseIf = conditionalElseIf;
			this.conditionalElse = conditionalElse;
	}
	
	@Override
	public String generateJavaCode() {
		
		StringBuilder str = new StringBuilder();
		
		System.out.println(this.conditionalElseIf);
		System.out.println(this.conditionalElse);
		
		
		if (this.conditionalElseIf && listTrue.size() > 0) {
			str.append("else if ("+condition+") {");
		
			for(AbstractCommand cmd: listTrue) {
				str.append(cmd.generateJavaCode());
			}
			
			str.append("}");
		
		} else if (this.conditionalElse && listFalse.size() > 0) {
			str.append("else {");
		
			for(AbstractCommand cmd: listFalse) {
				str.append(cmd.generateJavaCode());
			}
			
			str.append("}");
			
		} else {
			str.append("if ("+condition+") {");
			
			for(AbstractCommand cmd: listTrue) {
				str.append(cmd.generateJavaCode());
			}
			
			str.append("}");
			
		}
		

		return str.toString();
	}
	
}
