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
	private String _exprTemp;
	private String _exprContent;
	private String _exprDecision;
	private String _exprRepetition;
	private String _tempLoopValue;
	private RawrSymbolTable symbolTable = new RawrSymbolTable();
	private RawrSymbol symbol;
	private RawrProgram program = new RawrProgram();
	private ArrayList <AbstractCommand> curThread;
	private ArrayList<AbstractCommand> loopList;
	private ArrayList<AbstractCommand> listTrue;
	private ArrayList<AbstractCommand> listFalse = new ArrayList<AbstractCommand>();
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
		if (id != null) {
			int type = ((RawrVariable) symbolTable.get(id)).getType();
			if(type!=type_enum){
				String type_name = "";
				if(type_enum==0){
					type_name += "double";
				}
				else if(type_enum==1){
					type_name += "string";
				}
				else if(type_enum==2){
					type_name += "int";
				}
				throw new RawrSemanticException ("Variable "+id+" is not assigned to type "+ type_name);
			}
		}
	}
	
	public void isNumber(String id){
		if (id != null) {
			int type = ((RawrVariable) symbolTable.get(id)).getType();
			if(type != 0 && type != 2){
				throw new RawrSemanticException ("Variable "+id+" is not a number");
			}
		}
	}
	
	public boolean variableValidateRead(String id){
		
		for(AbstractCommand command: curThread) {
			if(command instanceof ast.CommandRead){
				if (id.equals(((ast.CommandRead) command).getId())){
					return true;
				}
			}
		}
		
		return false;
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


prog 			: 	'start:' 
						decl? 
						code? 
					'end'  
				{	
					program.setVarTable(symbolTable);
					if (stack.size() != 0) {
						program.setCommands(stack.pop());
					}
				}
				;

 
decl			: 		(decl_var)+
				;


decl_var 		: 		type 
           				ID 
           				{
							_varName = _input.LT(-1).getText();
							_varValue = null;
							symbol = new RawrVariable(_varName, _type, _varValue);
					
							if(!symbolTable.exists(_varName)) {
								symbolTable.add(symbol);
							}
							else {
								throw new RawrSemanticException("Variable "+ _varName + " already declared");
							}
		   				}
		   				(VIR 
		   				ID 
		   				{
							_varName = _input.LT(-1).getText();
							_varValue = null;
							symbol = new RawrVariable(_varName, _type,  _varValue);
					
							if(!symbolTable.exists(_varName)) {
								symbolTable.add(symbol);
							} 
							else {
								throw new RawrSemanticException("Variable "+ _varName + " already declared");
							}
						}
						)*
						SC?
				;


type			: 	'double' 
						{
							_type = RawrVariable.DOUBLE;
						}
	      			|'text' 
	      				{
	      					_type = RawrVariable.TEXT;
	      				}
	      			|'int'
	      				{
	      					_type = RawrVariable.INT;
	      				}
	      		;


code 			: 		{ 
		  					curThread = new ArrayList<AbstractCommand>();
		  					stack.push(curThread);
	   					}
	   					(cmd)+
	   			;


cmd				: 		cmd_read
	  					|cmd_write
	 					|cmd_attrib 
	  					|cmd_conditional
	  					|cmd_loop
	  			;

cmd_loop 		: 		cmdloop1
						|cmdloop2
						|cmdloop3
				;


cmdloop1		: 	'while'
						AP
						{
			  				_exprContent = "";
			  			}
						(ID
						{
					 		if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
						}
						|DOUBLE
						{
							variableValidateType(_exprId, 0);
						}
						|INT
						{
							variableValidateType(_exprId, 2);
						}
						|expr)
						{ 
			  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
			  			}
						OPREL
						{
							_exprRepetition += _input.LT(-1).getText();					
			  				_exprContent = "";
						}
						(ID 
						{
					 		if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
						}
						|DOUBLE
						{
							variableValidateType(_exprId, 0);
						}
						|INT
						{
							variableValidateType(_exprId, 2);
						}
						|expr
						)
						{
							_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
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


cmdloop2		: 	'do'	
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
						}
			  
			  		'while'
			  			AP
			  			{
			  				_exprContent = "";
			  			}
					 	(ID
					 	{
					 		if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
					 	}
					 	|DOUBLE
					 	{
							variableValidateType(_exprId, 0);
						}
						|INT
					 	{
							variableValidateType(_exprId, 2);
						}
						| expr)
					 	{ 
			  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
			  			}
					 	OPREL
					 	{
					 		_exprRepetition += _input.LT(-1).getText();
					 		_exprContent = "";
					 	}
					 	(ID 
					 	{
					 		if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
					 	}
					 	|DOUBLE
					 	{
							variableValidateType(_exprId, 0);
						}
						|INT
						{
							variableValidateType(_exprId, 2);
						}
						|expr
					 	)
						{ 
			  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
			  			}
					 	FP
					 	SC?
					 	{
					 		CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 2);
							stack.peek().add(cmd);
					 	}
				;

cmdloop3		: 	'for'	
						AP
						cmd_attrib
						{
							_exprRepetition = _exprTemp;
						}
						SC
						{
							_exprRepetition += _input.LT(-1).getText();
			  				_exprContent = "";
			  			}
						(ID
						{
							if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
						}
						|DOUBLE
						{
							variableValidateType(_exprId, 0);
						}
						|INT
						{
							variableValidateType(_exprId, 2);
						}
						|expr)
						{ 
			  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
			  			}
						OPREL
						{
							_exprRepetition += _input.LT(-1).getText();
							_exprContent = "";
						}
						(ID 
						{
							if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
						} 
						|DOUBLE
						{
							variableValidateType(_exprId, 0);
						}
						|INT
						{
							variableValidateType(_exprId, 2);
						}
						|expr)
						{
							_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						}
						SC
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						(cmd_attrib
						|ID
						{
							if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
							}
							_exprRepetition += _input.LT(-1).getText();
						} 
						ICR
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						|ID
						{
							if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
							}
							_exprRepetition += _input.LT(-1).getText();
						} 
						DCR
						{
							_exprRepetition += _input.LT(-1).getText();
						}
						)
						{
							_exprRepetition += _exprTemp;
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


cmd_read		: 	'read' 
						AP 
				 		ID 
				 		{
							variableValidate(_input.LT(-1).getText());
							_readId = _input.LT(-1).getText();
						}
						FP 
						SC?
						{
			 				RawrVariable var = (RawrVariable) symbolTable.get(_readId);
			 				CommandRead cmd = new CommandRead(_readId, var);
							stack.peek().add(cmd);
						}
				;

 
cmd_write 		:	'write' 
						AP 
						{
			  				_exprContent = "";
			  			}
						(ID 
						{
							if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
							}
						}
						|DOUBLE
						|INT
						|TEXT
						|expr
						|ID ICR  
						{	
							if (!variableValidateRead(_exprId)){
								variableValidateValue(_exprId);
								variableValidateType(_exprId, 0);
							}
							_exprContent = _exprId + " + 1";
						}
						|ID DCR  
						{	
							if (!variableValidateRead(_exprId)){
								variableValidateValue(_exprId);
								variableValidateType(_exprId, 0);
							}
							_exprContent = _exprId + " - 1";
						})
						{	
			  				_writeId = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						}
			 			FP 
			 			SC?
			 			{
			 				CommandWrite cmd = new CommandWrite(_writeId);
							stack.peek().add(cmd);
			 			}
			 	;


cmd_attrib 		: 		ID 
						{
							variableValidate(_input.LT(-1).getText());
							_exprId = _input.LT(-1).getText();
						} 
						(ATTR
						{
							_exprContent = "";
						} 
						expr 
						|ICR  
						{	
							if (!variableValidateRead(_exprId)){
								variableValidateValue(_exprId);
								isNumber(_exprId);
							}
							_exprContent = _exprId + " + 1";
						}
						|DCR  
						{	
							if (!variableValidateRead(_exprId)){
								variableValidateValue(_exprId);
								variableValidateType(_exprId, 0);
							}
							_exprContent = _exprId + " - 1";
						}
						)
						SC?
						{
							CommandAttrib cmd = new CommandAttrib (_exprId, _exprContent, symbolTable);
							stack.peek().add(cmd);
							_exprTemp = _exprId + " = " + _exprContent;
						}
				;
				

cmd_conditional	:	'if' 
			  			AP 
			  			{
			  				_exprContent = "";
			  			}
			  			(ID 
			  			{
			  				if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
								
							}
			  			}
			  			|DOUBLE
			  			{
			  				variableValidateType(_exprId, 0);
			  				
			  			}
						|INT
			  			{
			  				variableValidateType(_exprId, 2);
			  				
			  			}
			  			|expr) 	
			  			{ 
			  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
			  			}
			  			OPREL
			  			{ 
			  				
			  				_exprDecision += _input.LT(-1).getText();
			  				_exprContent = "";
			  			}
			  			(ID 
			  			{
			  				if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
			  			} 
			  			|DOUBLE
			  			{
			  				variableValidateType(_exprId, 0);
			  				
			  			}
						|INT
			  			{
			  				variableValidateType(_exprId, 2);
			  				
			  			}
			  			|expr)
			  			{ 
			  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
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
			  				listTrue = stack.pop();
			  			}
			  		('else if' 
			  			AP 
			  			{
			  				_exprContent = "";
			  			}
			  			(
			  				ID 
				  			{
				  				if (!variableValidateRead(_input.LT(-1).getText())){
									variableValidate(_input.LT(-1).getText());
									variableValidateValue(_input.LT(-1).getText());
									variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
									
								}
				  			}	
				  			|(ID 
				  			{
				  				if (!variableValidateRead(_input.LT(-1).getText())){
									variableValidate(_input.LT(-1).getText());
									variableValidateValue(_input.LT(-1).getText());
									variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
									
								}
				  			}
				  			|NUMBER
				  			{
				  				variableValidateType(_exprId, 0);
				  				
				  			}
				  			|expr) 	
				  			{ 
				  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
				  			}
				  			OPREL
				  			{ 
				  				
				  				_exprDecision += _input.LT(-1).getText();
				  				_exprContent = "";
				  			}
				  			(ID 
				  			{
				  				if (!variableValidateRead(_input.LT(-1).getText())){
									variableValidate(_input.LT(-1).getText());
									variableValidateValue(_input.LT(-1).getText());
									variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
								}
				  			} 
				  			|NUMBER
				  			{
				  				variableValidateType(_exprId, 0);
				  				
				  			}
				  			|expr)
				  			{ 
				  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
				  			}
				  			)
			  			FP 
		   	    		ACH 
		   	    		{
			   	 	   		curThread = new ArrayList<AbstractCommand>();
					  		stack.push(curThread);
					 	}
		   	    		(cmd)+ 
		   	 			FCH 
		   	 			{ 
			   	 	     	listTrue = stack.pop();
		   	 	    	}
		   	 		)?
		   	  		
		   	  		('else' 
		   	    		ACH 
		   	    		{
			   	 	   		curThread = new ArrayList<AbstractCommand>();
					  		stack.push(curThread);
					 	}
		   	    		(cmd)+ 
		   	 			FCH 
		   	 			{ 
			   	 	     	listFalse = stack.pop();
		   	 	    	}
		   	 		)?
		   	 		{
		   	 			CommandConditional cmd = new CommandConditional (_exprDecision, listTrue, listFalse);
			   	 	   	stack.peek().add(cmd);
		   	 	    }
		   	 	;


expr 			: 		term 
						(OP 
	   					{
	   						_exprContent += _input.LT(-1).getText();
	   					} 
	   					term)*
	   			;


term			:  		ID 
						{
							if (!variableValidateRead(_input.LT(-1).getText())){
								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
							}
							
							_exprContent += _input.LT(-1).getText();
		   				} 
						|DOUBLE
						{
							variableValidateType(_exprId, 0);
							_exprContent += _input.LT(-1).getText();
						}
						|TEXT
						{	
							variableValidateType(_exprId, 1);
							_exprContent += _input.LT(-1).getText();
						}
						|INT
						{
							variableValidateType(_exprId, 2);
							_exprContent += _input.LT(-1).getText();
						}
				;


AP				: 	'('
				;


FP				: 	')'
				;


SC   			: 	';'
				;


OP 				: 	'+' | '-' | '*' | '/'
				;


ATTR 			: 	'='
				;


ICR 			: 	'++'
				;
				
DCR 			: 	'--'
				;


ACH 			: 	'{'
				;


FCH 			: 	'}'
				;


OPREL			: 	'>' | '<' | '>=' | '<=' | '==' | '!='
				;


VIR				: 	','
				;


ID 				: 	[a-z]([a-z]|[A-Z]|[0-9])*
				;


INT				:	[0-9]+
				;


DOUBLE			: 	[0-9]+ ('.' [0-9]+)?
				;


TEXT			:   ["]~["]*["]
				;


CM				:	's2' .*? 's2' -> skip
				;


WS				: 	(' ' | '\t' | '\n' | '\r') -> skip
				;