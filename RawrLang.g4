grammar RawrLang; 
@header{
	import datastructures.RawrSymbol;
	import datastructures.RawrVariable;
	import datastructures.RawrSymbolTable;
	import exceptions.RawrSemanticException;
	import java.util.ArrayList;
	import ast.AbstractCommand;
	import ast.RawrProgram;
	import ast.CommandLeitura;
	import ast.CommandEscrita;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private RawrSymbolTable symbolTable = new RawrSymbolTable();
	private RawrSymbol symbol;
	private RawrProgram program = new RawrProgram();
	private ArrayList <AbstractCommand> curThread = new ArrayList<AbstractCommand>();
	private String _readId;
	private String _writeId;
	
	public void variableValidate(String id){
	
			if (!symbolTable.exists(id)){
				throw new RawrSemanticException ("Variable "+id+" not declared");
			}
	}
	
	public void exibeComandos(){
		for(AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
}

prog : 'start:' decl bloco  'end'  
		{program.setComandos(curThread);}
	 ;

decl: (declaravar)+
	;
	
declaravar : tipo ID {
			_varName = _input.LT(-1).getText();
			_varValue = null;
			symbol = new RawrVariable(_varName, _tipo, _varValue);
			if(!symbolTable.exists(_varName)){
				symbolTable.add(symbol);
			} else {
				throw new RawrSemanticException("Variable "+ symbol + " already declared");
			}
		}
		(VIR ID{
			_varName = _input.LT(-1).getText();
			_varValue = null;
			symbol = new RawrVariable(_varName, _tipo,  _varValue);
			
			if(!symbolTable.exists(_varName)){
				symbolTable.add(symbol);
			} else {
				throw new RawrSemanticException("Variable "+ symbol + " already declared");
			}
			})* SC?;

tipo	: 'numero' {_tipo = RawrVariable.NUMBER;}
	    | 'texto' {_tipo = RawrVariable.TEXT;}
	    ;

bloco : (cmd)+
      ;

cmd	: cmdleitura 
	| cmdescrita 
	| cmdattrib 
	| cmdselecao
    ;
    
cmdleitura : 'read' AP 
					ID {variableValidate(_input.LT(-1).getText());
						_readId = _input.LT(-1).getText();
						}
					FP 
					SC?
			 {
			 	CommandLeitura cmd = new CommandLeitura(_readId);
				curThread.add(cmd);
			 }
           ;
 
cmdescrita : 'write' AP 
					 ID {variableValidate(_input.LT(-1).getText());
					 	_writeId = _input.LT(-1).getText();
					 	}
					 FP 
					 SC?
			{
			 	CommandEscrita cmd = new CommandEscrita(_writeId);
				curThread.add(cmd);
			 }
           ;
 

cmdattrib : ID {variableValidate(_input.LT(-1).getText());} 
			ATTR 
			expr 
			SC?
          ;
          
cmdselecao : 'se' AP ID OPREL (ID | NUMBER) FP ACH (cmd)+ FCH
		   	 ('senao' ACH (cmd+) FCH)?
		   ;
          
expr : termo ( OP termo )*
	 ;
	 
termo:  ID {variableValidate(_input.LT(-1).getText());} 
		| NUMBER
     ;
     
AP: '('
  ;
  
FP: ')'
  ;
  
SC : ';'
   ;
   
OP : '+' | '-' | '*' | '/'
   ;
   
ATTR : '='
     ;
     
ACH : '{'
	;
	
FCH : '}'
	;
     
OPREL: '>' | '<' | '>=' | '<=' | '==' | '!='
	 ;
	 
VIR: ','
   ;
     
ID : [a-z]([a-z]|[A-Z]|[0-9])*
   ;
   
NUMBER: [0-9]+ ('.' [0-9]+)?
      ;
      
WS: (' ' | '\t' | '\n' | '\r') -> skip;