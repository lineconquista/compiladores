grammar RawrLang; 

@header{
	import java.util.ArrayList;
	import java.util.Stack;
	import ast.*;
	import datastructures.*;
	import exceptions.*;
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
	private String _exprRepetition;
	private RawrSymbolTable symbolTable = new RawrSymbolTable();
	private RawrSymbol symbol;
	private RawrProgram program = new RawrProgram();
	private ArrayList <AbstractCommand> curThread;
	private ArrayList<AbstractCommand> loopList;
	private ArrayList<AbstractCommand> listTrue;
	private ArrayList<AbstractCommand> listFalse;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack <ArrayList<AbstractCommand>>();

	
	public void variableValidate(String id){
		if (!symbolTable.exists(id)){
			throw new RawrSemanticException ("Variable "+id+" not declared");
		}
	}
	public void variableValidateValue(String id){
		String value = ((RawrVariable) symbolTable.get(id)).getValue();
		if(value==null){
			throw new RawrSemanticException ("Variable "+id+" is not assigned");
		}
	}
	
	public void variableValidateType(String id, int type_enum){
		int type = ((RawrVariable) symbolTable.get(id)).getType();
		if(type!=type_enum){
			throw new RawrSemanticException ("Variable "+id+" is not assigned to type "+ type_enum);
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
	  |cmd_conditional
	  |cmdloop;

cmdloop : cmdloop1
		| cmdloop2
		| cmdloop3
		;

cmdloop1	: 'enquanto'AP
						ID
						{
							_exprRepetition = _input.LT(-1).getText();
						}
						OPREL
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						(ID|NUMBER)
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						FP
						ACH
						{
							curThread = new ArrayList<AbstractCommand>();
							stack.push(curThread);
						}
						(cmd)+
						FCH
						{
							loopList = new ArrayList<AbstractCommand>();
							loopList = stack.pop();
							CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 1);
							stack.peek().add(cmd);
						}
			;

cmdloop2	: 'faca'	ACH
						{
							curThread = new ArrayList<AbstractCommand>();
							stack.push(curThread);
						}
						(cmd)+
						FCH
						{
							loopList = new ArrayList<AbstractCommand>();
							loopList = stack.pop();
						}
			  
			  'enquanto'AP
					 	ID
					 	{
					 		_exprRepetition = _input.LT(-1).getText();
					 	}
					 	OPREL
					 	{
					 		_exprRepetition += _input.LT(-1).getText();
					 	}
					 	(ID|NUMBER)
					 	{
					 		_exprRepetition += _input.LT(-1).getText();
					 	}
					 	FP
					 	SC
					 	{
					 		CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 2);
							stack.peek().add(cmd);
					 	}
			;

cmdloop3	: 'para'	AP
						ID
						{
							_exprRepetition = _input.LT(-1).getText();
						}
						ATTR
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						(ID|NUMBER)
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						SC
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						ID
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						OPREL
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						(ID|NUMBER)
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						SC
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						ID
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						ATTR
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						ID
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						OP
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						(ID|NUMBER)
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						FP
						ACH
						{
							curThread = new ArrayList<AbstractCommand>();
							stack.push(curThread);
						}
						(cmd)+
						FCH
						{
							loopList = new ArrayList<AbstractCommand>();
							loopList = stack.pop();
							CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 3);
							stack.peek().add(cmd);
						}
			;

cmd_read: 'read' AP 
				 ID {
							variableValidate(_input.LT(-1).getText());
							_readId = _input.LT(-1).getText();
						 }
			FP SC?
			{
			 	RawrVariable var = (RawrVariable) symbolTable.get(_readId);
			 	CommandRead cmd = new CommandRead(_readId, var);
				stack.peek().add(cmd);
			};
 
cmd_write : 'write' AP 
					(ID 
					{
						variableValidate(_input.LT(-1).getText());
						variableValidateValue(_input.LT(-1).getText());
						_writeId = _input.LT(-1).getText();
					}
					|NUMBER
					{
						_writeId = _input.LT(-1).getText();
					}
					|TEXT
					{
						_writeId = _input.LT(-1).getText();
					}
					)
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
				CommandAttrib cmd = new CommandAttrib (_exprId, _exprContent, symbolTable);
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
				variableValidateValue(_input.LT(-1).getText());
				variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
				_exprContent += _input.LT(-1).getText();
		   } 
		| NUMBER
		{	variableValidateType(_exprId, 0);
			_exprContent += _input.LT(-1).getText();}
		| TEXT
		{	variableValidateType(_exprId, 1);
			_exprContent += _input.LT(-1).getText();};
     
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
TEXT:   ["]~["]*["];
      
WS: (' ' | '\t' | '\n' | '\r') -> skip;