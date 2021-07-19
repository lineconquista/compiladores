grammar RawrLang; 
@header{
	import datastructures.RawrSymbol;
	import datastructures.RawrVariable;
	import datastructures.RawrSymbolTable;
	import exceptions.RawrSemanticException;
	import java.util.ArrayList;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private RawrSymbolTable symbolTable = new RawrSymbolTable();
	private RawrSymbol symbol;
	
	public void variableValidate(String id){
	
			if (!symbolTable.exists(id)){
				throw new RawrSemanticException ("Variable "+id+" not declared");
			}
	}
}

prog : 'start:' decl bloco  'end'  
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
					ID {variableValidate(_input.LT(-1).getText());}
					FP 
					SC?
           ;
 
cmdescrita : 'write' AP 
					 ID {variableValidate(_input.LT(-1).getText());}
					 FP 
					 SC?
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