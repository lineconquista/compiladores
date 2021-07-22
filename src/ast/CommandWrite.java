package ast;


public class CommandWrite extends AbstractCommand {
	
	private String id;
	
	public CommandWrite (String id) {
			this.id = id;
	}
	
	@Override
	public String generateJavaCode() {
		return "System.out.println("+id+");";
	}
}
