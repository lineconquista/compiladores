grammar RawrLang; 

prog : 'start:' bloco  'end'  
	 ;

bloco : (cmd)+
      ;

cmd	: cmdleitura | cmdescrita | cmdattrib
    ;
    
cmdleitura : 'read' AP ID FP SC?
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
     
ID : [a-z]([a-z]|[A-Z]|[0-9])*
   ;
   
NUMBER: [0-9]+ ('.' [0-9]+)?
      ;
      
WS: (' ' | '\t' | '\n' | '\r') -> skip;