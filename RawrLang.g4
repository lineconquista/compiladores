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
	import ast.CommandLeitura;
	import ast.CommandEscrita;
	import ast.CommandAtribuicao;
	import ast.CommandDecisao;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private RawrSymbolTable symbolTable = new RawrSymbolTable();
	private RawrSymbol symbol;
	private RawrProgram program = new RawrProgram();
	private ArrayList <AbstractCommand> curThread;
	private String _readId;
	private String _writeId;
	private String _exprId;
	private String _exprContent;
	private String _exprDecision;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack <ArrayList<AbstractCommand>>();
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	
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
		{program.setComandos(stack.pop());
		 }
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

bloco : { curThread = new ArrayList<AbstractCommand>();
		  stack.push(curThread);
		}
		(cmd)+
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
				stack.peek().add(cmd);
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
				stack.peek().add(cmd);
			 }
           ;
 

cmdattrib : ID {variableValidate(_input.LT(-1).getText());
				_exprId = _input.LT(-1).getText();
			} 
			ATTR {
				_exprContent = "";
			}
			expr 
			SC?{
				CommandAtribuicao cmd = new CommandAtribuicao (_exprId, _exprContent);
				stack.peek().add(cmd);
			}
          ;
          
cmdselecao : 'se' AP 
				  ID { _exprDecision = _input.LT(-1).getText(); }
				  OPREL  { _exprDecision += _input.LT(-1).getText(); }
				  (ID | NUMBER)  { _exprDecision += _input.LT(-1).getText(); }
				  FP
				  ACH 
				  { curThread = new ArrayList<AbstractCommand>();
				  	stack.push(curThread);
				  }
				  (cmd)+
				  FCH
				  { listaTrue = stack.pop(); }
		   	 	  (
		   	 	   'senao' 
		   	 	   ACH 
		   	 	   {
		   	 	   	curThread = new ArrayList<AbstractCommand>();
				  	stack.push(curThread);
		   	 	   }
		   	 	   (cmd+) 
		   	 	   FCH
		   	 	   { 
		   	 	     listaFalse = stack.pop(); 
		   	 	   	 CommandDecisao cmd = new CommandDecisao (_exprDecision, listaTrue, listaFalse);
		   	 	   	 stack.peek().add(cmd);
		   	 	   }
		   	 	  )?
		   ;
          
expr : termo ( 
		OP {
			_exprContent += _input.LT(-1).getText();
		} 
		termo )*
	 ;
	 
termo:  ID {variableValidate(_input.LT(-1).getText());
			_exprContent += _input.LT(-1).getText();
		} 
		| NUMBER{
			_exprContent += _input.LT(-1).getText();
		}
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