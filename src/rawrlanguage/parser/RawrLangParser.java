// Generated from RawrLang.g4 by ANTLR 4.9.2
package rawrlanguage.parser;

	import java.util.ArrayList;
	import java.util.Stack;
	import ast.*;
	import datastructures.*;
	import exceptions.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RawrLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, AP=12, FP=13, SC=14, OP=15, ATTR=16, ICR=17, ACH=18, 
		FCH=19, OPREL=20, VIR=21, ID=22, NUMBER=23, TEXT=24, CM=25, WS=26;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_decl_var = 2, RULE_type = 3, RULE_code = 4, 
		RULE_cmd = 5, RULE_cmd_loop = 6, RULE_cmdloop1 = 7, RULE_cmdloop2 = 8, 
		RULE_cmdloop3 = 9, RULE_cmd_read = 10, RULE_cmd_write = 11, RULE_cmd_attrib = 12, 
		RULE_cmd_conditional = 13, RULE_expr = 14, RULE_term = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "decl_var", "type", "code", "cmd", "cmd_loop", "cmdloop1", 
			"cmdloop2", "cmdloop3", "cmd_read", "cmd_write", "cmd_attrib", "cmd_conditional", 
			"expr", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'start:'", "'end'", "'number'", "'text'", "'while'", "'do'", "'for'", 
			"'read'", "'write'", "'if'", "'else'", "'('", "')'", "';'", null, "'='", 
			"'++'", "'{'", "'}'", null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"AP", "FP", "SC", "OP", "ATTR", "ICR", "ACH", "FCH", "OPREL", "VIR", 
			"ID", "NUMBER", "TEXT", "CM", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RawrLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		
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
					String type_name = type_enum==0 ? "number" : "string";
					throw new RawrSemanticException ("Variable "+id+" is not assigned to type "+ type_name);
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

	public RawrLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(T__0);
			setState(33);
			decl();
			setState(34);
			code();
			setState(35);
			match(T__1);
				
								program.setVarTable(symbolTable);
								program.setCommands(stack.pop());
							
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<Decl_varContext> decl_var() {
			return getRuleContexts(Decl_varContext.class);
		}
		public Decl_varContext decl_var(int i) {
			return getRuleContext(Decl_varContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				decl_var();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decl_varContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> VIR() { return getTokens(RawrLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(RawrLangParser.VIR, i);
		}
		public TerminalNode SC() { return getToken(RawrLangParser.SC, 0); }
		public Decl_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterDecl_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitDecl_var(this);
		}
	}

	public final Decl_varContext decl_var() throws RecognitionException {
		Decl_varContext _localctx = new Decl_varContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			type();
			setState(44);
			match(ID);

										_varName = _input.LT(-1).getText();
										_varValue = null;
										symbol = new RawrVariable(_varName, _type, _varValue);
								
										if(!symbolTable.exists(_varName)) {
											symbolTable.add(symbol);
										}
										else {
											throw new RawrSemanticException("Variable "+ _varName + " already declared");
										}
					   				
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(46);
				match(VIR);
				setState(47);
				match(ID);

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
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(54);
				match(SC);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(T__2);

											_type = RawrVariable.NUMBER;
										
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(T__3);

					      					_type = RawrVariable.TEXT;
					      				
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 
					  					curThread = new ArrayList<AbstractCommand>();
					  					stack.push(curThread);
				   					
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				cmd();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public Cmd_readContext cmd_read() {
			return getRuleContext(Cmd_readContext.class,0);
		}
		public Cmd_writeContext cmd_write() {
			return getRuleContext(Cmd_writeContext.class,0);
		}
		public Cmd_attribContext cmd_attrib() {
			return getRuleContext(Cmd_attribContext.class,0);
		}
		public Cmd_conditionalContext cmd_conditional() {
			return getRuleContext(Cmd_conditionalContext.class,0);
		}
		public Cmd_loopContext cmd_loop() {
			return getRuleContext(Cmd_loopContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				cmd_read();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				cmd_write();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				cmd_attrib();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				cmd_conditional();
				}
				break;
			case T__4:
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				cmd_loop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_loopContext extends ParserRuleContext {
		public Cmdloop1Context cmdloop1() {
			return getRuleContext(Cmdloop1Context.class,0);
		}
		public Cmdloop2Context cmdloop2() {
			return getRuleContext(Cmdloop2Context.class,0);
		}
		public Cmdloop3Context cmdloop3() {
			return getRuleContext(Cmdloop3Context.class,0);
		}
		public Cmd_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd_loop(this);
		}
	}

	public final Cmd_loopContext cmd_loop() throws RecognitionException {
		Cmd_loopContext _localctx = new Cmd_loopContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmd_loop);
		try {
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				cmdloop1();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				cmdloop2();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				cmdloop3();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmdloop1Context extends ParserRuleContext {
		public TerminalNode AP() { return getToken(RawrLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(RawrLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RawrLangParser.NUMBER, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public Cmdloop1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdloop1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmdloop1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmdloop1(this);
		}
	}

	public final Cmdloop1Context cmdloop1() throws RecognitionException {
		Cmdloop1Context _localctx = new Cmdloop1Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdloop1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__4);
			setState(82);
			match(AP);

						  				_exprContent = "";
						  			
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(84);
				match(ID);

									 		if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
										
				}
				break;
			case 2:
				{
				setState(86);
				match(NUMBER);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(88);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(92);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();					
						  				_exprContent = "";
									
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(94);
				match(ID);

									 		if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
										
				}
				break;
			case 2:
				{
				setState(96);
				match(NUMBER);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(98);
				expr();
				}
				break;
			}

										_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(102);
			match(FP);
			setState(103);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				cmd();
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(110);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
										CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 1);
										stack.peek().add(cmd);
									
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmdloop2Context extends ParserRuleContext {
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public TerminalNode AP() { return getToken(RawrLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(RawrLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RawrLangParser.NUMBER, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode SC() { return getToken(RawrLangParser.SC, 0); }
		public Cmdloop2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdloop2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmdloop2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmdloop2(this);
		}
	}

	public final Cmdloop2Context cmdloop2() throws RecognitionException {
		Cmdloop2Context _localctx = new Cmdloop2Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdloop2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__5);
			setState(114);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(116);
				cmd();
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(121);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
									
			setState(123);
			match(T__4);
			setState(124);
			match(AP);

						  				_exprContent = "";
						  			
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(126);
				match(ID);

									 		if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
									 	
				}
				break;
			case 2:
				{
				setState(128);
				match(NUMBER);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(130);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(134);
			match(OPREL);

								 		_exprRepetition += _input.LT(-1).getText();
								 		_exprContent = "";
								 	
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(136);
				match(ID);

									 		if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
									 	
				}
				break;
			case 2:
				{
				setState(138);
				match(NUMBER);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(140);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(144);
			match(FP);
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(145);
				match(SC);
				}
			}


								 		CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 2);
										stack.peek().add(cmd);
								 	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmdloop3Context extends ParserRuleContext {
		public TerminalNode AP() { return getToken(RawrLangParser.AP, 0); }
		public List<Cmd_attribContext> cmd_attrib() {
			return getRuleContexts(Cmd_attribContext.class);
		}
		public Cmd_attribContext cmd_attrib(int i) {
			return getRuleContext(Cmd_attribContext.class,i);
		}
		public List<TerminalNode> SC() { return getTokens(RawrLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(RawrLangParser.SC, i);
		}
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(RawrLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RawrLangParser.NUMBER, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ICR() { return getToken(RawrLangParser.ICR, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public Cmdloop3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdloop3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmdloop3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmdloop3(this);
		}
	}

	public final Cmdloop3Context cmdloop3() throws RecognitionException {
		Cmdloop3Context _localctx = new Cmdloop3Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdloop3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__6);
			setState(151);
			match(AP);
			setState(152);
			cmd_attrib();

										_exprRepetition = _exprTemp;
									
			setState(154);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(156);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
										
				}
				break;
			case 2:
				{
				setState(158);
				match(NUMBER);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(160);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(164);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();
										_exprContent = "";
									
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(166);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
										
				}
				break;
			case 2:
				{
				setState(168);
				match(NUMBER);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(170);
				expr();
				}
				break;
			}

										_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(174);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(176);
				cmd_attrib();
				}
				break;
			case 2:
				{
				setState(177);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
											}
											_exprRepetition += _input.LT(-1).getText();
										
				setState(179);
				match(ICR);

											_exprRepetition += _input.LT(-1).getText();
										
				}
				break;
			}

										_exprRepetition += _exprTemp;
									
			setState(184);
			match(FP);
			setState(185);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(188); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(187);
				cmd();
				}
				}
				setState(190); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(192);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
										CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 3);
										stack.peek().add(cmd);
									
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_readContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(RawrLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(RawrLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(RawrLangParser.SC, 0); }
		public Cmd_readContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_read; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd_read(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd_read(this);
		}
	}

	public final Cmd_readContext cmd_read() throws RecognitionException {
		Cmd_readContext _localctx = new Cmd_readContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmd_read);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(T__7);
			setState(196);
			match(AP);
			setState(197);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_readId = _input.LT(-1).getText();
									
			setState(199);
			match(FP);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(200);
				match(SC);
				}
			}


						 				RawrVariable var = (RawrVariable) symbolTable.get(_readId);
						 				CommandRead cmd = new CommandRead(_readId, var);
										stack.peek().add(cmd);
									
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_writeContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(RawrLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ID() { return getToken(RawrLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(RawrLangParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(RawrLangParser.TEXT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(RawrLangParser.SC, 0); }
		public Cmd_writeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_write; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd_write(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd_write(this);
		}
	}

	public final Cmd_writeContext cmd_write() throws RecognitionException {
		Cmd_writeContext _localctx = new Cmd_writeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmd_write);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__8);
			setState(206);
			match(AP);

						  				_exprContent = "";
						  			
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(208);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
											}
										
				}
				break;
			case 2:
				{
				setState(210);
				match(NUMBER);
				}
				break;
			case 3:
				{
				setState(211);
				match(TEXT);
				}
				break;
			case 4:
				{
				setState(212);
				expr();
				}
				break;
			}
				
						  				_writeId = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(216);
			match(FP);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(217);
				match(SC);
				}
			}


						 				CommandWrite cmd = new CommandWrite(_writeId);
										stack.peek().add(cmd);
						 			
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_attribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RawrLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(RawrLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ICR() { return getToken(RawrLangParser.ICR, 0); }
		public TerminalNode SC() { return getToken(RawrLangParser.SC, 0); }
		public Cmd_attribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_attrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd_attrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd_attrib(this);
		}
	}

	public final Cmd_attribContext cmd_attrib() throws RecognitionException {
		Cmd_attribContext _localctx = new Cmd_attribContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmd_attrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_exprId = _input.LT(-1).getText();
									
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTR:
				{
				setState(224);
				match(ATTR);

											_exprContent = "";
										
				setState(226);
				expr();
				}
				break;
			case ICR:
				{
				setState(227);
				match(ICR);
					
											if (!variableValidateRead(_exprId)){
												variableValidateValue(_exprId);
												variableValidateType(_exprId, 0);
											}
											_exprContent = _exprId + " + 1";
										
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(231);
				match(SC);
				}
				break;
			}

										CommandAttrib cmd = new CommandAttrib (_exprId, _exprContent, symbolTable);
										stack.peek().add(cmd);
										_exprTemp = _exprId + " = " + _exprContent;
									
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_conditionalContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(RawrLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(RawrLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(RawrLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(RawrLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(RawrLangParser.FCH, i);
		}
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(RawrLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RawrLangParser.NUMBER, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public Cmd_conditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd_conditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd_conditional(this);
		}
	}

	public final Cmd_conditionalContext cmd_conditional() throws RecognitionException {
		Cmd_conditionalContext _localctx = new Cmd_conditionalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_cmd_conditional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(T__9);
			setState(237);
			match(AP);

						  				_exprContent = "";
						  			
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(239);
				match(ID);

							  				if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
												
											}
							  			
				}
				break;
			case 2:
				{
				setState(241);
				match(NUMBER);

							  				variableValidateType(_exprId, 0);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(243);
				expr();
				}
				break;
			}
			 
						  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(247);
			match(OPREL);
			 
						  				
						  				_exprDecision += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(249);
				match(ID);

							  				if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
							  			
				}
				break;
			case 2:
				{
				setState(251);
				match(NUMBER);

							  				variableValidateType(_exprId, 0);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(253);
				expr();
				}
				break;
			}
			 
						  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(257);
			match(FP);
			setState(258);
			match(ACH);
			 
						  				curThread = new ArrayList<AbstractCommand>();
							  			stack.push(curThread);
							   		
			setState(261); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(260);
				cmd();
				}
				}
				setState(263); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(265);
			match(FCH);
			 
						  				listTrue = stack.pop();
						  			
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(267);
				match(T__10);
				setState(268);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
									 	
				setState(271); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(270);
					cmd();
					}
					}
					setState(273); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
				setState(275);
				match(FCH);
				 
							   	 	     	listFalse = stack.pop();
						   	 	    	
				}
			}


					   	 			CommandConditional cmd = new CommandConditional (_exprDecision, listTrue, listFalse);
						   	 	   	stack.peek().add(cmd);
					   	 	    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(RawrLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(RawrLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			term();
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(283);
				match(OP);

					   						_exprContent += _input.LT(-1).getText();
					   					
				setState(285);
				term();
				}
				}
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RawrLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(RawrLangParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(RawrLangParser.TEXT, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_term);
		try {
			setState(297);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
											
											_exprContent += _input.LT(-1).getText();
						   				
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				match(NUMBER);
					
											variableValidateType(_exprId, 0);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(295);
				match(TEXT);
					
											variableValidateType(_exprId, 1);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u012e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\6\3*\n\3\r\3\16\3+\3\4\3\4\3\4\3\4\3\4\3\4\7\4\64"+
		"\n\4\f\4\16\4\67\13\4\3\4\5\4:\n\4\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\6"+
		"\6D\n\6\r\6\16\6E\3\7\3\7\3\7\3\7\3\7\5\7M\n\7\3\b\3\b\3\b\5\bR\n\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\\\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\5\tf\n\t\3\t\3\t\3\t\3\t\3\t\6\tm\n\t\r\t\16\tn\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\6\nx\n\n\r\n\16\ny\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5"+
		"\n\u0086\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0090\n\n\3\n\3\n\3\n"+
		"\5\n\u0095\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\5\13\u00a4\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u00ae\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b8\n\13\3"+
		"\13\3\13\3\13\3\13\3\13\6\13\u00bf\n\13\r\13\16\13\u00c0\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00cc\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\5\r\u00d8\n\r\3\r\3\r\3\r\5\r\u00dd\n\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00e8\n\16\3\16\5\16\u00eb\n\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00f7\n\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\5\17\u0101\n\17\3\17\3\17\3\17\3\17\3\17\6\17"+
		"\u0108\n\17\r\17\16\17\u0109\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u0112"+
		"\n\17\r\17\16\17\u0113\3\17\3\17\3\17\5\17\u0119\n\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\7\20\u0121\n\20\f\20\16\20\u0124\13\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\5\21\u012c\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \2\2\2\u014a\2\"\3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\b?\3\2\2\2\n"+
		"A\3\2\2\2\fL\3\2\2\2\16Q\3\2\2\2\20S\3\2\2\2\22s\3\2\2\2\24\u0098\3\2"+
		"\2\2\26\u00c5\3\2\2\2\30\u00cf\3\2\2\2\32\u00e0\3\2\2\2\34\u00ee\3\2\2"+
		"\2\36\u011c\3\2\2\2 \u012b\3\2\2\2\"#\7\3\2\2#$\5\4\3\2$%\5\n\6\2%&\7"+
		"\4\2\2&\'\b\2\1\2\'\3\3\2\2\2(*\5\6\4\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2"+
		"+,\3\2\2\2,\5\3\2\2\2-.\5\b\5\2./\7\30\2\2/\65\b\4\1\2\60\61\7\27\2\2"+
		"\61\62\7\30\2\2\62\64\b\4\1\2\63\60\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2"+
		"\2\65\66\3\2\2\2\669\3\2\2\2\67\65\3\2\2\28:\7\20\2\298\3\2\2\29:\3\2"+
		"\2\2:\7\3\2\2\2;<\7\5\2\2<@\b\5\1\2=>\7\6\2\2>@\b\5\1\2?;\3\2\2\2?=\3"+
		"\2\2\2@\t\3\2\2\2AC\b\6\1\2BD\5\f\7\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF"+
		"\3\2\2\2F\13\3\2\2\2GM\5\26\f\2HM\5\30\r\2IM\5\32\16\2JM\5\34\17\2KM\5"+
		"\16\b\2LG\3\2\2\2LH\3\2\2\2LI\3\2\2\2LJ\3\2\2\2LK\3\2\2\2M\r\3\2\2\2N"+
		"R\5\20\t\2OR\5\22\n\2PR\5\24\13\2QN\3\2\2\2QO\3\2\2\2QP\3\2\2\2R\17\3"+
		"\2\2\2ST\7\7\2\2TU\7\16\2\2U[\b\t\1\2VW\7\30\2\2W\\\b\t\1\2XY\7\31\2\2"+
		"Y\\\b\t\1\2Z\\\5\36\20\2[V\3\2\2\2[X\3\2\2\2[Z\3\2\2\2\\]\3\2\2\2]^\b"+
		"\t\1\2^_\7\26\2\2_e\b\t\1\2`a\7\30\2\2af\b\t\1\2bc\7\31\2\2cf\b\t\1\2"+
		"df\5\36\20\2e`\3\2\2\2eb\3\2\2\2ed\3\2\2\2fg\3\2\2\2gh\b\t\1\2hi\7\17"+
		"\2\2ij\7\24\2\2jl\b\t\1\2km\5\f\7\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3"+
		"\2\2\2op\3\2\2\2pq\7\25\2\2qr\b\t\1\2r\21\3\2\2\2st\7\b\2\2tu\7\24\2\2"+
		"uw\b\n\1\2vx\5\f\7\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2"+
		"{|\7\25\2\2|}\b\n\1\2}~\7\7\2\2~\177\7\16\2\2\177\u0085\b\n\1\2\u0080"+
		"\u0081\7\30\2\2\u0081\u0086\b\n\1\2\u0082\u0083\7\31\2\2\u0083\u0086\b"+
		"\n\1\2\u0084\u0086\5\36\20\2\u0085\u0080\3\2\2\2\u0085\u0082\3\2\2\2\u0085"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\n\1\2\u0088\u0089\7\26"+
		"\2\2\u0089\u008f\b\n\1\2\u008a\u008b\7\30\2\2\u008b\u0090\b\n\1\2\u008c"+
		"\u008d\7\31\2\2\u008d\u0090\b\n\1\2\u008e\u0090\5\36\20\2\u008f\u008a"+
		"\3\2\2\2\u008f\u008c\3\2\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\b\n\1\2\u0092\u0094\7\17\2\2\u0093\u0095\7\20\2\2\u0094\u0093\3"+
		"\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\b\n\1\2\u0097"+
		"\23\3\2\2\2\u0098\u0099\7\t\2\2\u0099\u009a\7\16\2\2\u009a\u009b\5\32"+
		"\16\2\u009b\u009c\b\13\1\2\u009c\u009d\7\20\2\2\u009d\u00a3\b\13\1\2\u009e"+
		"\u009f\7\30\2\2\u009f\u00a4\b\13\1\2\u00a0\u00a1\7\31\2\2\u00a1\u00a4"+
		"\b\13\1\2\u00a2\u00a4\5\36\20\2\u00a3\u009e\3\2\2\2\u00a3\u00a0\3\2\2"+
		"\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\b\13\1\2\u00a6"+
		"\u00a7\7\26\2\2\u00a7\u00ad\b\13\1\2\u00a8\u00a9\7\30\2\2\u00a9\u00ae"+
		"\b\13\1\2\u00aa\u00ab\7\31\2\2\u00ab\u00ae\b\13\1\2\u00ac\u00ae\5\36\20"+
		"\2\u00ad\u00a8\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\u00b0\b\13\1\2\u00b0\u00b1\7\20\2\2\u00b1\u00b7\b\13\1"+
		"\2\u00b2\u00b8\5\32\16\2\u00b3\u00b4\7\30\2\2\u00b4\u00b5\b\13\1\2\u00b5"+
		"\u00b6\7\23\2\2\u00b6\u00b8\b\13\1\2\u00b7\u00b2\3\2\2\2\u00b7\u00b3\3"+
		"\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\b\13\1\2\u00ba\u00bb\7\17\2\2\u00bb"+
		"\u00bc\7\24\2\2\u00bc\u00be\b\13\1\2\u00bd\u00bf\5\f\7\2\u00be\u00bd\3"+
		"\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c3\7\25\2\2\u00c3\u00c4\b\13\1\2\u00c4\25\3\2"+
		"\2\2\u00c5\u00c6\7\n\2\2\u00c6\u00c7\7\16\2\2\u00c7\u00c8\7\30\2\2\u00c8"+
		"\u00c9\b\f\1\2\u00c9\u00cb\7\17\2\2\u00ca\u00cc\7\20\2\2\u00cb\u00ca\3"+
		"\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\b\f\1\2\u00ce"+
		"\27\3\2\2\2\u00cf\u00d0\7\13\2\2\u00d0\u00d1\7\16\2\2\u00d1\u00d7\b\r"+
		"\1\2\u00d2\u00d3\7\30\2\2\u00d3\u00d8\b\r\1\2\u00d4\u00d8\7\31\2\2\u00d5"+
		"\u00d8\7\32\2\2\u00d6\u00d8\5\36\20\2\u00d7\u00d2\3\2\2\2\u00d7\u00d4"+
		"\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00da\b\r\1\2\u00da\u00dc\7\17\2\2\u00db\u00dd\7\20\2\2\u00dc\u00db\3"+
		"\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\b\r\1\2\u00df"+
		"\31\3\2\2\2\u00e0\u00e1\7\30\2\2\u00e1\u00e7\b\16\1\2\u00e2\u00e3\7\22"+
		"\2\2\u00e3\u00e4\b\16\1\2\u00e4\u00e8\5\36\20\2\u00e5\u00e6\7\23\2\2\u00e6"+
		"\u00e8\b\16\1\2\u00e7\u00e2\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00ea\3"+
		"\2\2\2\u00e9\u00eb\7\20\2\2\u00ea\u00e9\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\b\16\1\2\u00ed\33\3\2\2\2\u00ee\u00ef\7\f\2"+
		"\2\u00ef\u00f0\7\16\2\2\u00f0\u00f6\b\17\1\2\u00f1\u00f2\7\30\2\2\u00f2"+
		"\u00f7\b\17\1\2\u00f3\u00f4\7\31\2\2\u00f4\u00f7\b\17\1\2\u00f5\u00f7"+
		"\5\36\20\2\u00f6\u00f1\3\2\2\2\u00f6\u00f3\3\2\2\2\u00f6\u00f5\3\2\2\2"+
		"\u00f7\u00f8\3\2\2\2\u00f8\u00f9\b\17\1\2\u00f9\u00fa\7\26\2\2\u00fa\u0100"+
		"\b\17\1\2\u00fb\u00fc\7\30\2\2\u00fc\u0101\b\17\1\2\u00fd\u00fe\7\31\2"+
		"\2\u00fe\u0101\b\17\1\2\u00ff\u0101\5\36\20\2\u0100\u00fb\3\2\2\2\u0100"+
		"\u00fd\3\2\2\2\u0100\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\b\17"+
		"\1\2\u0103\u0104\7\17\2\2\u0104\u0105\7\24\2\2\u0105\u0107\b\17\1\2\u0106"+
		"\u0108\5\f\7\2\u0107\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u0107\3\2"+
		"\2\2\u0109\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\7\25\2\2\u010c"+
		"\u0118\b\17\1\2\u010d\u010e\7\r\2\2\u010e\u010f\7\24\2\2\u010f\u0111\b"+
		"\17\1\2\u0110\u0112\5\f\7\2\u0111\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\7\25"+
		"\2\2\u0116\u0117\b\17\1\2\u0117\u0119\3\2\2\2\u0118\u010d\3\2\2\2\u0118"+
		"\u0119\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\b\17\1\2\u011b\35\3\2\2"+
		"\2\u011c\u0122\5 \21\2\u011d\u011e\7\21\2\2\u011e\u011f\b\20\1\2\u011f"+
		"\u0121\5 \21\2\u0120\u011d\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2"+
		"\2\2\u0122\u0123\3\2\2\2\u0123\37\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126"+
		"\7\30\2\2\u0126\u012c\b\21\1\2\u0127\u0128\7\31\2\2\u0128\u012c\b\21\1"+
		"\2\u0129\u012a\7\32\2\2\u012a\u012c\b\21\1\2\u012b\u0125\3\2\2\2\u012b"+
		"\u0127\3\2\2\2\u012b\u0129\3\2\2\2\u012c!\3\2\2\2 +\659?ELQ[eny\u0085"+
		"\u008f\u0094\u00a3\u00ad\u00b7\u00c0\u00cb\u00d7\u00dc\u00e7\u00ea\u00f6"+
		"\u0100\u0109\u0113\u0118\u0122\u012b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}