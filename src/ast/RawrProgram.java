package ast;

import java.util.ArrayList;

import datastructures.RawrSymbolTable;

public class RawrProgram {	
	private RawrSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;
	
	public void generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;");
		str.append("public class MainClass{");
		str.append("	public static void main (String args[]){");
		str.append("	}");
		str.append("}");
		
		
	}
	
	public RawrSymbolTable getVarTable() {
		return varTable;
	}
	public void setVarTable(RawrSymbolTable varTable) {
		this.varTable = varTable;
	}
	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}
	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
