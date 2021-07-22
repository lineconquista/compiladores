grammar RawrLang; 

@header{
	import datastructures.RawrSymbol;
	import datastructures.RawrVariable;
	import datastructures.RawrSymbolTable;
	import exceptions.RawrSemanticException;
	import java.util.ArrayList;
	import java.util.Stack;
	import ast.AbstractCommand;
	import ast.RawrProgram;
	import ast.CommandRead;
	import ast.CommandWrite;
	import ast.CommandAttrib;
	import ast.CommandConditional;
}

@members{
	
	private int _type;
	private String _varName;
	private String _varValue;
	private String _readId;
	private String _writeId;
	private String _exprId;
	private String _exprContent;
	private String _exprDecision;
	private RawrSymbolTable symbolTable = new RawrSymbolTable();
	private RawrSymbol symbol;
	private RawrProgram program = new RawrProgram();
	private ArrayList <AbstractCommand> curThread;
	private ArrayList<AbstractCommand> listTrue;
	private ArrayList<AbstractCommand> listFalse;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack <ArrayList<AbstractCommand>>();

	
	public void variableValidate(String id){
		if (!symbolTable.exists(id)){
			throw new RawrSemanticException ("Variable "+id+" not declared");
		}
	}
	
	public void exibeComandos(){
		for(AbstractCommand c: program.getCommands()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTarget();
	}
	
}

prog : 'start:' decl code  'end'  
		{	
			program.setVarTable(symbolTable);
			program.setCommands(stack.pop());
		};
	 
decl: (decl_var)+;
	
decl_var : type 
           ID {
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new RawrVariable(_varName, _type, _varValue);
					
					if(!symbolTable.exists(_varName)){
						symbolTable.add(symbol);
						
					} else {
						throw new RawrSemanticException("Variable "+ _varName + " already declared");
					}
		   		}
		
		   (VIR ID {
						_varName = _input.LT(-1).getText();
						_varValue = null;
						symbol = new RawrVariable(_varName, _type,  _varValue);
					
						if(!symbolTable.exists(_varName)){
							symbolTable.add(symbol);
							
						} else {
							throw new RawrSemanticException("Variable "+ _varName + " already declared");
						}
					}
			)* SC?;

type	: 'number' {_type = RawrVariable.NUMBER;}
	      |'text' {_type = RawrVariable.TEXT;};

code : { 
		  curThread = new ArrayList<AbstractCommand>();
		  stack.push(curThread);
	   }
	   (cmd)+;

cmd	: cmd_read
	  |cmd_write
	  |cmd_attrib 
	  |cmd_conditional;
    
cmd_read: 'read' AP ID {
							variableValidate(_input.LT(-1).getText());
							_readId = _input.LT(-1).getText();
						 }
			FP SC?
			{
			 	RawrVariable var = (RawrVariable) symbolTable.get(_readId);
			 	CommandRead cmd = new CommandRead(_readId, var);
				stack.peek().add(cmd);
			};
 
cmd_write : 'write' AP ID {
								variableValidate(_input.LT(-1).getText());
								_writeId = _input.LT(-1).getText();
						   }
			 FP SC?
			 {
			 	CommandWrite cmd = new CommandWrite(_writeId);
				stack.peek().add(cmd);
			 };
 
cmd_attrib : ID {
					variableValidate(_input.LT(-1).getText());
					_exprId = _input.LT(-1).getText();
				} 
			ATTR {_exprContent = "";}
			expr SC?
			{
				CommandAttrib cmd = new CommandAttrib (_exprId, _exprContent);
				stack.peek().add(cmd);
			};
          
cmd_conditional : 'if' 
			  AP 
			  ID { _exprDecision = _input.LT(-1).getText(); }
			  OPREL  { _exprDecision += _input.LT(-1).getText(); }
			  (ID | NUMBER)  { _exprDecision += _input.LT(-1).getText(); }
			  FP 
			  ACH { 
			  			curThread = new ArrayList<AbstractCommand>();
				  		stack.push(curThread);
				   }
			  (cmd)+
			  FCH { listTrue = stack.pop(); }
		   	  ('else' 
		   	    ACH {
			   	 	   	curThread = new ArrayList<AbstractCommand>();
					  	stack.push(curThread);
					 }
		   	    (cmd+) 
		   	 	FCH { 
			   	 	     listFalse = stack.pop(); 
			   	 	   	 CommandConditional cmd = new CommandConditional (_exprDecision, listTrue, listFalse);
			   	 	   	 stack.peek().add(cmd);
		   	 	    }
		   	 	)?;
          
expr : term ( 
	   OP {_exprContent += _input.LT(-1).getText();} 
	   term )*;
	 
term:  ID {
				variableValidate(_input.LT(-1).getText());
				_exprContent += _input.LT(-1).getText();
		   } 
		| NUMBER{_exprContent += _input.LT(-1).getText();};
     
AP: '(';
  
FP: ')';
  
SC : ';';
   
OP : '+' | '-' | '*' | '/';
   
ATTR : '=';
     
ACH : '{';
	
FCH : '}';
     
OPREL: '>' | '<' | '>=' | '<=' | '==' | '!=';
	 
VIR: ',';
     
ID : [a-z]([a-z]|[A-Z]|[0-9])*;
   
NUMBER: [0-9]+ ('.' [0-9]+)?;
      
WS: (' ' | '\t' | '\n' | '\r') -> skip;