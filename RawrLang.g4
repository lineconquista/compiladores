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
}

prog : 'start:' decl bloco  'end'  
	 ;

decl: (declaravar)+
	;
	
declaravar : tipo ID {
			_varName = _input.LT(-1).getText();
			_varValue = null;
			symbol = new RawrVariable(_varName, _tipo, _varValue);
			System.out.println("simbolo adicionado " + symbol);
			symbolTable.add(symbol);
			}
		(VIR ID{
			_varName = _input.LT(-1).getText();
			_varValue = null;
			symbol = new RawrVariable(_varName, _tipo,  _varValue);
			System.out.println("simbolo adicionado " + symbol);
			symbolTable.add(symbol);
			})* SC?;

tipo	: 'numero' {_tipo = RawrVariable.NUMBER;}
	    | 'texto' {_tipo = RawrVariable.TEXT;}
	    ;

bloco : (cmd)+
      ;

cmd	: cmdleitura {System.out.println("Comando de leitura");}
	| cmdescrita {System.out.println("Comando de escrita");}
	| cmdattrib {System.out.println("Comando de atribuicao");}
    ;
    
cmdleitura : 'read' AP 
					ID {System.out.println("ID="+_input.LT(-1).getText());}
					FP 
					SC?
           ;
 
cmdescrita : 'write' AP ID FP SC?
           ;
 

cmdattrib : ID ATTR expr SC?
          ;
          
expr : termo ( OP termo )*
	 ;
	 
termo: ID | NUMBER
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
     
VIR: ','
   ;
     
ID : [a-z]([a-z]|[A-Z]|[0-9])*
   ;
   
NUMBER: [0-9]+ ('.' [0-9]+)?
      ;
      
WS: (' ' | '\t' | '\n' | '\r') -> skip;