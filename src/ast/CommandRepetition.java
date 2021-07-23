package ast;

import java.util.ArrayList;

public class CommandRepetition extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> list;
	private int type;
	
	public CommandRepetition(String condition, ArrayList<AbstractCommand> list, int type) {
		this.condition = condition;
		this.list = list;
		this.type = type;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		if(type==1) {
			str.append("while ("+condition+") {\n");
			for(AbstractCommand cmd: list) {
				str.append(cmd.generateJavaCode());
			}
			str.append("}\n");
			
		}
		
		else if(type==2) {
			str.append("do {\n");
			for(AbstractCommand cmd: list) {
				str.append(cmd.generateJavaCode());
			}
			str.append("} while ("+condition+");\n");
		}
		
		else if(type==3) {
			str.append("for("+condition+") {\n");
			for(AbstractCommand cmd: list) {
				str.append(cmd.generateJavaCode());
			}
			str.append("}\n");
		}
		return str.toString();
	}	
}