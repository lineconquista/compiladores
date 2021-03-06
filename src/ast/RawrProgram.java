package ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import datastructures.RawrSymbol;
import datastructures.RawrSymbolTable;

public class RawrProgram {	
	
	private RawrSymbolTable varTable;
	private ArrayList<AbstractCommand> commands;
	private String programName;
	
	public void generateTarget() {
		
		StringBuilder str = new StringBuilder();
		
		str.append("import java.util.Scanner; \n");
		str.append("public class MainClass{ \n");
		str.append("	public static void main (String args[]){ \n");
		str.append("	Scanner _key = new Scanner(System.in);\n");
		
		for(RawrSymbol symbol: varTable.getAll()) {
			str.append(symbol.generateJavaCode()+"\n");	
		}
		
		for(AbstractCommand command: commands) {
			str.append(command.generateJavaCode()+"\n");	
		}
		
		str.append("	}");
		str.append("}");
		
		try {
			
			FileWriter fr = new FileWriter (new File ("MainClass.java"));
			fr.write(str.toString());
			fr.close();
			
			varTable.checkAllVarUsed();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public RawrSymbolTable getVarTable() {
		return varTable;
	}
	
	public void setVarTable(RawrSymbolTable varTable) {
		this.varTable = varTable;
	}
	
	public ArrayList<AbstractCommand> getCommands() {
		return commands;
	}
	
	public void setCommands(ArrayList<AbstractCommand> commands) {
		this.commands = commands;
	}
	
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
