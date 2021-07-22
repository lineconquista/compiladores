package ast;

public class CommandAttrib extends AbstractCommand {
	
	private String id;
	private String expr;
	
	public CommandAttrib (String id, String expr) {
			this.id = id;
			this.expr = expr;
	}
	
	@Override
	public String generateJavaCode() {
		return id + " =" +expr+";" ;
	}
	
}
