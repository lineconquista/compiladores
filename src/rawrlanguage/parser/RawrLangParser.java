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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, AP=15, FP=16, SC=17, 
		OP=18, ATTR=19, ICR=20, DCR=21, ACH=22, FCH=23, OPREL=24, OPRELNUM=25, 
		OPRELBOOL=26, VIR=27, BOOLEAN=28, FALSE=29, TRUE=30, ID=31, INT=32, DOUBLE=33, 
		TEXT=34, CM=35, WS=36;
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
			null, "'start:'", "'end'", "'double'", "'boolean'", "'text'", "'int'", 
			"'while'", "'do'", "'for'", "'read'", "'write'", "'if'", "'else if'", 
			"'else'", "'('", "')'", "';'", null, "'='", "'++'", "'--'", "'{'", "'}'", 
			null, null, null, "','", null, "'false'", "'true'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "AP", "FP", "SC", "OP", "ATTR", "ICR", "DCR", "ACH", 
			"FCH", "OPREL", "OPRELNUM", "OPRELBOOL", "VIR", "BOOLEAN", "FALSE", "TRUE", 
			"ID", "INT", "DOUBLE", "TEXT", "CM", "WS"
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
		private Boolean _conditionalElseIf = false;
		private Boolean _conditionalElse = false;
		private Boolean _despair = false;
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
					else if(type_enum==3){
						type_name += "boolean";
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
		
		public void isBoolean(String id){
			if (id != null) {
				int type = ((RawrVariable) symbolTable.get(id)).getType();
				if(type != 3){
					throw new RawrSemanticException ("Variable "+id+" is not a boolean");
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(T__0);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) {
				{
				setState(33);
				decl();
				}
			}

			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0)) {
				{
				setState(36);
				code();
				}
			}

			setState(39);
			match(T__1);
				
								program.setVarTable(symbolTable);
								if (stack.size() != 0) {
									program.setCommands(stack.pop());
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
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				decl_var();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0) );
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
			setState(47);
			type();
			setState(48);
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
					   				
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(50);
				match(VIR);
				setState(51);
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
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(58);
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
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(T__2);

											_type = RawrVariable.DOUBLE;
										
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				match(T__3);

					      					_type = RawrVariable.BOOLEAN;
					      				
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				match(T__4);

					      					_type = RawrVariable.TEXT;
					      				
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				match(T__5);

					      					_type = RawrVariable.INT;
					      				
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
				   					
			setState(73); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(72);
				cmd();
				}
				}
				setState(75); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
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
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				cmd_read();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				cmd_write();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				cmd_attrib();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(80);
				cmd_conditional();
				}
				break;
			case T__6:
			case T__7:
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(81);
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
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				cmdloop1();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				cmdloop2();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
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
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> DOUBLE() { return getTokens(RawrLangParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(RawrLangParser.DOUBLE, i);
		}
		public List<TerminalNode> INT() { return getTokens(RawrLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RawrLangParser.INT, i);
		}
		public List<TerminalNode> BOOLEAN() { return getTokens(RawrLangParser.BOOLEAN); }
		public TerminalNode BOOLEAN(int i) {
			return getToken(RawrLangParser.BOOLEAN, i);
		}
		public List<TerminalNode> FALSE() { return getTokens(RawrLangParser.FALSE); }
		public TerminalNode FALSE(int i) {
			return getToken(RawrLangParser.FALSE, i);
		}
		public List<TerminalNode> TRUE() { return getTokens(RawrLangParser.TRUE); }
		public TerminalNode TRUE(int i) {
			return getToken(RawrLangParser.TRUE, i);
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
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode OPRELNUM() { return getToken(RawrLangParser.OPRELNUM, 0); }
		public TerminalNode OPRELBOOL() { return getToken(RawrLangParser.OPRELBOOL, 0); }
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
			setState(89);
			match(T__6);
			setState(90);
			match(AP);

						  				_exprContent = "";
						  			
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(92);
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
				setState(94);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(96);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(98);
				match(BOOLEAN);

											variableValidateType(_exprId, 3);
										
				}
				break;
			case 5:
				{
				setState(100);
				match(FALSE);
				}
				break;
			case 6:
				{
				setState(101);
				match(TRUE);
				}
				break;
			case 7:
				{
				setState(102);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPREL) | (1L << OPRELNUM) | (1L << OPRELBOOL))) != 0)) {
				{
				setState(111);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPREL:
					{
					setState(106);
					match(OPREL);
					}
					break;
				case OPRELNUM:
					{
					setState(107);
					match(OPRELNUM);

												isNumber(_exprId);
											
					}
					break;
				case OPRELBOOL:
					{
					setState(109);
					match(OPRELBOOL);

												isBoolean(_exprId);
											
											
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 
							  				_exprRepetition += _input.LT(-1).getText();
							  				_exprContent = "";
							  			
				setState(125);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(114);
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
					setState(116);
					match(DOUBLE);

												variableValidateType(_exprId, 0);
											
					}
					break;
				case 3:
					{
					setState(118);
					match(INT);

												variableValidateType(_exprId, 2);
											
					}
					break;
				case 4:
					{
					setState(120);
					match(BOOLEAN);

												variableValidateType(_exprId, 3);
											
					}
					break;
				case 5:
					{
					setState(122);
					match(TRUE);
					}
					break;
				case 6:
					{
					setState(123);
					match(FALSE);
					}
					break;
				case 7:
					{
					setState(124);
					expr();
					}
					break;
				}

											_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
										
				}
			}

			setState(130);
			match(FP);
			setState(131);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(134); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(133);
				cmd();
				}
				}
				setState(136); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(138);
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
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> DOUBLE() { return getTokens(RawrLangParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(RawrLangParser.DOUBLE, i);
		}
		public List<TerminalNode> INT() { return getTokens(RawrLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RawrLangParser.INT, i);
		}
		public List<TerminalNode> BOOLEAN() { return getTokens(RawrLangParser.BOOLEAN); }
		public TerminalNode BOOLEAN(int i) {
			return getToken(RawrLangParser.BOOLEAN, i);
		}
		public List<TerminalNode> FALSE() { return getTokens(RawrLangParser.FALSE); }
		public TerminalNode FALSE(int i) {
			return getToken(RawrLangParser.FALSE, i);
		}
		public List<TerminalNode> TRUE() { return getTokens(RawrLangParser.TRUE); }
		public TerminalNode TRUE(int i) {
			return getToken(RawrLangParser.TRUE, i);
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
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode OPRELNUM() { return getToken(RawrLangParser.OPRELNUM, 0); }
		public TerminalNode OPRELBOOL() { return getToken(RawrLangParser.OPRELBOOL, 0); }
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
			setState(141);
			match(T__7);
			setState(142);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(145); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(144);
				cmd();
				}
				}
				setState(147); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(149);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
									
			setState(151);
			match(T__6);
			setState(152);
			match(AP);

						  				_exprContent = "";
						  			
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(154);
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
				setState(156);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(158);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(160);
				match(BOOLEAN);

											variableValidateType(_exprId, 3);
										
				}
				break;
			case 5:
				{
				setState(162);
				match(FALSE);
				}
				break;
			case 6:
				{
				setState(163);
				match(TRUE);
				}
				break;
			case 7:
				{
				setState(164);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPREL) | (1L << OPRELNUM) | (1L << OPRELBOOL))) != 0)) {
				{
				setState(173);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPREL:
					{
					setState(168);
					match(OPREL);
					}
					break;
				case OPRELNUM:
					{
					setState(169);
					match(OPRELNUM);

												isNumber(_exprId);
											
					}
					break;
				case OPRELBOOL:
					{
					setState(171);
					match(OPRELBOOL);

												isBoolean(_exprId);
											
											
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 
							  				_exprRepetition += _input.LT(-1).getText();
							  				_exprContent = "";
							  			
				setState(187);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(176);
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
					setState(178);
					match(DOUBLE);

												variableValidateType(_exprId, 0);
											
					}
					break;
				case 3:
					{
					setState(180);
					match(INT);

												variableValidateType(_exprId, 2);
											
					}
					break;
				case 4:
					{
					setState(182);
					match(BOOLEAN);

												variableValidateType(_exprId, 3);
											
					}
					break;
				case 5:
					{
					setState(184);
					match(FALSE);
					}
					break;
				case 6:
					{
					setState(185);
					match(TRUE);
					}
					break;
				case 7:
					{
					setState(186);
					expr();
					}
					break;
				}
				 
							  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
							  			
				}
			}

			setState(192);
			match(FP);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(193);
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
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> DOUBLE() { return getTokens(RawrLangParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(RawrLangParser.DOUBLE, i);
		}
		public List<TerminalNode> INT() { return getTokens(RawrLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RawrLangParser.INT, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode OPRELNUM() { return getToken(RawrLangParser.OPRELNUM, 0); }
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
			setState(198);
			match(T__8);
			setState(199);
			match(AP);
			setState(200);
			cmd_attrib();

										_exprRepetition = _exprTemp;
									
			setState(202);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(204);
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
				setState(206);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(208);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(210);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPREL:
				{
				setState(214);
				match(OPREL);
				}
				break;
			case OPRELNUM:
				{
				setState(215);
				match(OPRELNUM);

											isNumber(_exprId);
										
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 
						  				_exprRepetition += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(220);
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
				setState(222);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(224);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(226);
				expr();
				}
				break;
			}

										_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(230);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
										_despair = true;
									
			setState(232);
			cmd_attrib();

										_exprRepetition += _exprTemp;
									
			setState(234);
			match(FP);
			setState(235);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(238); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(237);
				cmd();
				}
				}
				setState(240); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(242);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
										CommandRepetition cmd = new CommandRepetition(_exprRepetition, loopList, 3);
										stack.peek().add(cmd);
										_despair = false;
									
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
			setState(245);
			match(T__9);
			setState(246);
			match(AP);
			setState(247);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_readId = _input.LT(-1).getText();
									
			setState(249);
			match(FP);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(250);
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
		public TerminalNode DOUBLE() { return getToken(RawrLangParser.DOUBLE, 0); }
		public TerminalNode INT() { return getToken(RawrLangParser.INT, 0); }
		public TerminalNode TEXT() { return getToken(RawrLangParser.TEXT, 0); }
		public TerminalNode BOOLEAN() { return getToken(RawrLangParser.BOOLEAN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Cmd_attribContext cmd_attrib() {
			return getRuleContext(Cmd_attribContext.class,0);
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
			setState(255);
			match(T__10);
			setState(256);
			match(AP);

						  				_exprContent = "";
						  			
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(258);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
											}
										
				}
				break;
			case 2:
				{
				setState(260);
				match(DOUBLE);
				}
				break;
			case 3:
				{
				setState(261);
				match(INT);
				}
				break;
			case 4:
				{
				setState(262);
				match(TEXT);
				}
				break;
			case 5:
				{
				setState(263);
				match(BOOLEAN);
				}
				break;
			case 6:
				{
				setState(264);
				expr();
				}
				break;
			case 7:
				{

											_despair = true;
										
				setState(266);
				cmd_attrib();
					
											_exprContent = _exprTemp;
										
				}
				break;
			}
				
						  				_writeId = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(272);
			match(FP);
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(273);
				match(SC);
				}
			}


						 				CommandWrite cmd = new CommandWrite(_writeId);
										stack.peek().add(cmd);
										_despair = false;
						 			
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
		public TerminalNode DCR() { return getToken(RawrLangParser.DCR, 0); }
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
			setState(278);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_exprId = _input.LT(-1).getText();
									
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTR:
				{
				setState(280);
				match(ATTR);

											_exprContent = "";
										
				setState(282);
				expr();
				}
				break;
			case ICR:
				{
				setState(283);
				match(ICR);
					
											if (!variableValidateRead(_exprId)){
												variableValidateValue(_exprId);
												isNumber(_exprId);
											}
											_exprContent = _exprId + " + 1";
										
				}
				break;
			case DCR:
				{
				setState(285);
				match(DCR);
					
											if (!variableValidateRead(_exprId)){
												variableValidateValue(_exprId);
												isNumber(_exprId);
											}
											_exprContent = _exprId + " - 1";
										
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(289);
				match(SC);
				}
				break;
			}

										if(_despair==false){
											CommandAttrib cmd = new CommandAttrib (_exprId, _exprContent, symbolTable);
											stack.peek().add(cmd);
										}
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
		public List<TerminalNode> AP() { return getTokens(RawrLangParser.AP); }
		public TerminalNode AP(int i) {
			return getToken(RawrLangParser.AP, i);
		}
		public List<TerminalNode> FP() { return getTokens(RawrLangParser.FP); }
		public TerminalNode FP(int i) {
			return getToken(RawrLangParser.FP, i);
		}
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
		public List<TerminalNode> DOUBLE() { return getTokens(RawrLangParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(RawrLangParser.DOUBLE, i);
		}
		public List<TerminalNode> INT() { return getTokens(RawrLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RawrLangParser.INT, i);
		}
		public List<TerminalNode> BOOLEAN() { return getTokens(RawrLangParser.BOOLEAN); }
		public TerminalNode BOOLEAN(int i) {
			return getToken(RawrLangParser.BOOLEAN, i);
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
		public List<TerminalNode> OPREL() { return getTokens(RawrLangParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(RawrLangParser.OPREL, i);
		}
		public List<TerminalNode> OPRELNUM() { return getTokens(RawrLangParser.OPRELNUM); }
		public TerminalNode OPRELNUM(int i) {
			return getToken(RawrLangParser.OPRELNUM, i);
		}
		public List<TerminalNode> OPRELBOOL() { return getTokens(RawrLangParser.OPRELBOOL); }
		public TerminalNode OPRELBOOL(int i) {
			return getToken(RawrLangParser.OPRELBOOL, i);
		}
		public List<TerminalNode> FALSE() { return getTokens(RawrLangParser.FALSE); }
		public TerminalNode FALSE(int i) {
			return getToken(RawrLangParser.FALSE, i);
		}
		public List<TerminalNode> TRUE() { return getTokens(RawrLangParser.TRUE); }
		public TerminalNode TRUE(int i) {
			return getToken(RawrLangParser.TRUE, i);
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
			setState(294);
			match(T__11);
			setState(295);
			match(AP);

						  				_exprContent = "";
						  			
			setState(306);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(297);
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
				setState(299);
				match(DOUBLE);

							  				variableValidateType(_exprId, 0);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(301);
				match(INT);

							  				variableValidateType(_exprId, 2);
							  				
							  			
				}
				break;
			case 4:
				{
				setState(303);
				match(BOOLEAN);

							  				variableValidateType(_exprId, 3);
							  				
							  			
				}
				break;
			case 5:
				{
				setState(305);
				expr();
				}
				break;
			}
			 
						  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPREL) | (1L << OPRELNUM) | (1L << OPRELBOOL))) != 0)) {
				{
				setState(314);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPREL:
					{
					setState(309);
					match(OPREL);
					}
					break;
				case OPRELNUM:
					{
					setState(310);
					match(OPRELNUM);

												isNumber(_exprId);
											
					}
					break;
				case OPRELBOOL:
					{
					setState(312);
					match(OPRELBOOL);

												isBoolean(_exprId);
											
											
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 
							  				_exprDecision += _input.LT(-1).getText();
							  				_exprContent = "";
							  			
				setState(328);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(317);
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
					setState(319);
					match(DOUBLE);

								  				variableValidateType(_exprId, 0);
								  				
								  			
					}
					break;
				case 3:
					{
					setState(321);
					match(INT);

								  				variableValidateType(_exprId, 2);
								  				
								  			
					}
					break;
				case 4:
					{
					setState(323);
					match(BOOLEAN);

								  				variableValidateType(_exprId, 3);
								  				
								  			
					}
					break;
				case 5:
					{
					setState(325);
					match(FALSE);
					}
					break;
				case 6:
					{
					setState(326);
					match(TRUE);
					}
					break;
				case 7:
					{
					setState(327);
					expr();
					}
					break;
				}
				 
							  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
							  			
				}
			}

			setState(333);
			match(FP);
			setState(334);
			match(ACH);
			 
						  				curThread = new ArrayList<AbstractCommand>();
							  			stack.push(curThread);
							   		
			setState(337); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(336);
				cmd();
				}
				}
				setState(339); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(341);
			match(FCH);
			 
						  				listTrue = stack.pop();
						  			
			setState(395);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(343);
				match(T__12);
				setState(344);
				match(AP);

							  				_exprContent = "";
							  				_conditionalElseIf = true;
							  			
				setState(355);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(346);
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
					setState(348);
					match(DOUBLE);

								  				variableValidateType(_exprId, 0);
								  				
								  			
					}
					break;
				case 3:
					{
					setState(350);
					match(INT);

								  				variableValidateType(_exprId, 2);
								  				
								  			
					}
					break;
				case 4:
					{
					setState(352);
					match(BOOLEAN);

								  				variableValidateType(_exprId, 3);
								  				
								  			
					}
					break;
				case 5:
					{
					setState(354);
					expr();
					}
					break;
				}
				 
							  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
							  			
				setState(380);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPREL) | (1L << OPRELNUM) | (1L << OPRELBOOL))) != 0)) {
					{
					setState(363);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OPREL:
						{
						setState(358);
						match(OPREL);
						}
						break;
					case OPRELNUM:
						{
						setState(359);
						match(OPRELNUM);

													isNumber(_exprId);
												
						}
						break;
					case OPRELBOOL:
						{
						setState(361);
						match(OPRELBOOL);

													isBoolean(_exprId);
												
												
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					 
								  				_exprDecision += _input.LT(-1).getText();
								  				_exprContent = "";
								  			
					setState(377);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
					case 1:
						{
						setState(366);
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
						setState(368);
						match(DOUBLE);

									  				variableValidateType(_exprId, 0);
									  				
									  			
						}
						break;
					case 3:
						{
						setState(370);
						match(INT);

									  				variableValidateType(_exprId, 2);
									  				
									  			
						}
						break;
					case 4:
						{
						setState(372);
						match(BOOLEAN);

									  				variableValidateType(_exprId, 3);
									  				
									  			
						}
						break;
					case 5:
						{
						setState(374);
						match(FALSE);
						}
						break;
					case 6:
						{
						setState(375);
						match(TRUE);
						}
						break;
					case 7:
						{
						setState(376);
						expr();
						}
						break;
					}
					 
								  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
								  			
					}
				}

				setState(382);
				match(FP);
				setState(383);
				match(ACH);
				 
							  				curThread = new ArrayList<AbstractCommand>();
								  			stack.push(curThread);
								   		
				setState(386); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(385);
					cmd();
					}
					}
					setState(388); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(390);
				match(FCH);
				 
							  				listTrue = stack.pop();
							  			
				}
				}
				setState(397);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(398);
				match(T__13);
				setState(399);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
							  				_conditionalElse = true;
									 	
				setState(402); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(401);
					cmd();
					}
					}
					setState(404); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(406);
				match(FCH);
				 
							   	 	     	listFalse = stack.pop();
						   	 	    	
				}
			}


					   	 			CommandConditional cmd = new CommandConditional (_exprDecision, listTrue, listFalse, _conditionalElseIf, _conditionalElse);
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
			setState(413);
			term();
			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(414);
				match(OP);

					   						_exprContent += _input.LT(-1).getText();
					   					
				setState(416);
				term();
				}
				}
				setState(421);
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
		public TerminalNode DOUBLE() { return getToken(RawrLangParser.DOUBLE, 0); }
		public TerminalNode TEXT() { return getToken(RawrLangParser.TEXT, 0); }
		public TerminalNode INT() { return getToken(RawrLangParser.INT, 0); }
		public TerminalNode BOOLEAN() { return getToken(RawrLangParser.BOOLEAN, 0); }
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
			setState(432);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(422);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
												variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
											}
											
											_exprContent += _input.LT(-1).getText();
						   				
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(424);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(426);
				match(TEXT);
					
											variableValidateType(_exprId, 1);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(428);
				match(INT);

											variableValidateType(_exprId, 2);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 5);
				{
				setState(430);
				match(BOOLEAN);
					
											variableValidateType(_exprId, 3);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u01b5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\5"+
		"\2%\n\2\3\2\5\2(\n\2\3\2\3\2\3\2\3\3\6\3.\n\3\r\3\16\3/\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\48\n\4\f\4\16\4;\13\4\3\4\5\4>\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\5\5H\n\5\3\6\3\6\6\6L\n\6\r\6\16\6M\3\7\3\7\3\7\3\7\3\7\5\7"+
		"U\n\7\3\b\3\b\3\b\5\bZ\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tj\n\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tr\n\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\t\5\t\u0083\n\t\3\t\3"+
		"\t\3\t\3\t\6\t\u0089\n\t\r\t\16\t\u008a\3\t\3\t\3\t\3\n\3\n\3\n\3\n\6"+
		"\n\u0094\n\n\r\n\16\n\u0095\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a8\n\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00b0"+
		"\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00be\n\n\3\n"+
		"\5\n\u00c1\n\n\3\n\3\n\5\n\u00c5\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00d6\n\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00dc\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00e6"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u00f1\n\13\r\13"+
		"\16\13\u00f2\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00fe\n\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0110"+
		"\n\r\3\r\3\r\3\r\5\r\u0115\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\5\16\u0122\n\16\3\16\5\16\u0125\n\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0135\n\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\5\17\u013d\n\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u014b\n\17\3\17\5\17\u014e\n\17\3"+
		"\17\3\17\3\17\3\17\6\17\u0154\n\17\r\17\16\17\u0155\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0166\n\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\5\17\u016e\n\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u017c\n\17\3\17\5\17\u017f\n\17\3"+
		"\17\3\17\3\17\3\17\6\17\u0185\n\17\r\17\16\17\u0186\3\17\3\17\3\17\7\17"+
		"\u018c\n\17\f\17\16\17\u018f\13\17\3\17\3\17\3\17\3\17\6\17\u0195\n\17"+
		"\r\17\16\17\u0196\3\17\3\17\3\17\5\17\u019c\n\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\7\20\u01a4\n\20\f\20\16\20\u01a7\13\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\5\21\u01b3\n\21\3\21\2\2\22\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \2\2\2\u020b\2\"\3\2\2\2\4-\3\2\2\2\6\61\3\2"+
		"\2\2\bG\3\2\2\2\nI\3\2\2\2\fT\3\2\2\2\16Y\3\2\2\2\20[\3\2\2\2\22\u008f"+
		"\3\2\2\2\24\u00c8\3\2\2\2\26\u00f7\3\2\2\2\30\u0101\3\2\2\2\32\u0118\3"+
		"\2\2\2\34\u0128\3\2\2\2\36\u019f\3\2\2\2 \u01b2\3\2\2\2\"$\7\3\2\2#%\5"+
		"\4\3\2$#\3\2\2\2$%\3\2\2\2%\'\3\2\2\2&(\5\n\6\2\'&\3\2\2\2\'(\3\2\2\2"+
		"()\3\2\2\2)*\7\4\2\2*+\b\2\1\2+\3\3\2\2\2,.\5\6\4\2-,\3\2\2\2./\3\2\2"+
		"\2/-\3\2\2\2/\60\3\2\2\2\60\5\3\2\2\2\61\62\5\b\5\2\62\63\7!\2\2\639\b"+
		"\4\1\2\64\65\7\35\2\2\65\66\7!\2\2\668\b\4\1\2\67\64\3\2\2\28;\3\2\2\2"+
		"9\67\3\2\2\29:\3\2\2\2:=\3\2\2\2;9\3\2\2\2<>\7\23\2\2=<\3\2\2\2=>\3\2"+
		"\2\2>\7\3\2\2\2?@\7\5\2\2@H\b\5\1\2AB\7\6\2\2BH\b\5\1\2CD\7\7\2\2DH\b"+
		"\5\1\2EF\7\b\2\2FH\b\5\1\2G?\3\2\2\2GA\3\2\2\2GC\3\2\2\2GE\3\2\2\2H\t"+
		"\3\2\2\2IK\b\6\1\2JL\5\f\7\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2N"+
		"\13\3\2\2\2OU\5\26\f\2PU\5\30\r\2QU\5\32\16\2RU\5\34\17\2SU\5\16\b\2T"+
		"O\3\2\2\2TP\3\2\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2U\r\3\2\2\2VZ\5\20\t"+
		"\2WZ\5\22\n\2XZ\5\24\13\2YV\3\2\2\2YW\3\2\2\2YX\3\2\2\2Z\17\3\2\2\2[\\"+
		"\7\t\2\2\\]\7\21\2\2]i\b\t\1\2^_\7!\2\2_j\b\t\1\2`a\7#\2\2aj\b\t\1\2b"+
		"c\7\"\2\2cj\b\t\1\2de\7\36\2\2ej\b\t\1\2fj\7\37\2\2gj\7 \2\2hj\5\36\20"+
		"\2i^\3\2\2\2i`\3\2\2\2ib\3\2\2\2id\3\2\2\2if\3\2\2\2ig\3\2\2\2ih\3\2\2"+
		"\2jk\3\2\2\2k\u0082\b\t\1\2lr\7\32\2\2mn\7\33\2\2nr\b\t\1\2op\7\34\2\2"+
		"pr\b\t\1\2ql\3\2\2\2qm\3\2\2\2qo\3\2\2\2rs\3\2\2\2s\177\b\t\1\2tu\7!\2"+
		"\2u\u0080\b\t\1\2vw\7#\2\2w\u0080\b\t\1\2xy\7\"\2\2y\u0080\b\t\1\2z{\7"+
		"\36\2\2{\u0080\b\t\1\2|\u0080\7 \2\2}\u0080\7\37\2\2~\u0080\5\36\20\2"+
		"\177t\3\2\2\2\177v\3\2\2\2\177x\3\2\2\2\177z\3\2\2\2\177|\3\2\2\2\177"+
		"}\3\2\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\b\t\1\2\u0082q"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\7\22\2\2"+
		"\u0085\u0086\7\30\2\2\u0086\u0088\b\t\1\2\u0087\u0089\5\f\7\2\u0088\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008d\7\31\2\2\u008d\u008e\b\t\1\2\u008e\21\3\2\2"+
		"\2\u008f\u0090\7\n\2\2\u0090\u0091\7\30\2\2\u0091\u0093\b\n\1\2\u0092"+
		"\u0094\5\f\7\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\7\31\2\2\u0098"+
		"\u0099\b\n\1\2\u0099\u009a\7\t\2\2\u009a\u009b\7\21\2\2\u009b\u00a7\b"+
		"\n\1\2\u009c\u009d\7!\2\2\u009d\u00a8\b\n\1\2\u009e\u009f\7#\2\2\u009f"+
		"\u00a8\b\n\1\2\u00a0\u00a1\7\"\2\2\u00a1\u00a8\b\n\1\2\u00a2\u00a3\7\36"+
		"\2\2\u00a3\u00a8\b\n\1\2\u00a4\u00a8\7\37\2\2\u00a5\u00a8\7 \2\2\u00a6"+
		"\u00a8\5\36\20\2\u00a7\u009c\3\2\2\2\u00a7\u009e\3\2\2\2\u00a7\u00a0\3"+
		"\2\2\2\u00a7\u00a2\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00c0\b\n\1\2\u00aa\u00b0\7\32"+
		"\2\2\u00ab\u00ac\7\33\2\2\u00ac\u00b0\b\n\1\2\u00ad\u00ae\7\34\2\2\u00ae"+
		"\u00b0\b\n\1\2\u00af\u00aa\3\2\2\2\u00af\u00ab\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00bd\b\n\1\2\u00b2\u00b3\7!\2\2\u00b3"+
		"\u00be\b\n\1\2\u00b4\u00b5\7#\2\2\u00b5\u00be\b\n\1\2\u00b6\u00b7\7\""+
		"\2\2\u00b7\u00be\b\n\1\2\u00b8\u00b9\7\36\2\2\u00b9\u00be\b\n\1\2\u00ba"+
		"\u00be\7\37\2\2\u00bb\u00be\7 \2\2\u00bc\u00be\5\36\20\2\u00bd\u00b2\3"+
		"\2\2\2\u00bd\u00b4\3\2\2\2\u00bd\u00b6\3\2\2\2\u00bd\u00b8\3\2\2\2\u00bd"+
		"\u00ba\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf\u00c1\b\n\1\2\u00c0\u00af\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c4\7\22\2\2\u00c3\u00c5\7\23\2\2\u00c4\u00c3\3"+
		"\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\b\n\1\2\u00c7"+
		"\23\3\2\2\2\u00c8\u00c9\7\13\2\2\u00c9\u00ca\7\21\2\2\u00ca\u00cb\5\32"+
		"\16\2\u00cb\u00cc\b\13\1\2\u00cc\u00cd\7\23\2\2\u00cd\u00d5\b\13\1\2\u00ce"+
		"\u00cf\7!\2\2\u00cf\u00d6\b\13\1\2\u00d0\u00d1\7#\2\2\u00d1\u00d6\b\13"+
		"\1\2\u00d2\u00d3\7\"\2\2\u00d3\u00d6\b\13\1\2\u00d4\u00d6\5\36\20\2\u00d5"+
		"\u00ce\3\2\2\2\u00d5\u00d0\3\2\2\2\u00d5\u00d2\3\2\2\2\u00d5\u00d4\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00db\b\13\1\2\u00d8\u00dc\7\32\2\2\u00d9"+
		"\u00da\7\33\2\2\u00da\u00dc\b\13\1\2\u00db\u00d8\3\2\2\2\u00db\u00d9\3"+
		"\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e5\b\13\1\2\u00de\u00df\7!\2\2\u00df"+
		"\u00e6\b\13\1\2\u00e0\u00e1\7#\2\2\u00e1\u00e6\b\13\1\2\u00e2\u00e3\7"+
		"\"\2\2\u00e3\u00e6\b\13\1\2\u00e4\u00e6\5\36\20\2\u00e5\u00de\3\2\2\2"+
		"\u00e5\u00e0\3\2\2\2\u00e5\u00e2\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7"+
		"\3\2\2\2\u00e7\u00e8\b\13\1\2\u00e8\u00e9\7\23\2\2\u00e9\u00ea\b\13\1"+
		"\2\u00ea\u00eb\5\32\16\2\u00eb\u00ec\b\13\1\2\u00ec\u00ed\7\22\2\2\u00ed"+
		"\u00ee\7\30\2\2\u00ee\u00f0\b\13\1\2\u00ef\u00f1\5\f\7\2\u00f0\u00ef\3"+
		"\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f5\7\31\2\2\u00f5\u00f6\b\13\1\2\u00f6\25\3\2"+
		"\2\2\u00f7\u00f8\7\f\2\2\u00f8\u00f9\7\21\2\2\u00f9\u00fa\7!\2\2\u00fa"+
		"\u00fb\b\f\1\2\u00fb\u00fd\7\22\2\2\u00fc\u00fe\7\23\2\2\u00fd\u00fc\3"+
		"\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\b\f\1\2\u0100"+
		"\27\3\2\2\2\u0101\u0102\7\r\2\2\u0102\u0103\7\21\2\2\u0103\u010f\b\r\1"+
		"\2\u0104\u0105\7!\2\2\u0105\u0110\b\r\1\2\u0106\u0110\7#\2\2\u0107\u0110"+
		"\7\"\2\2\u0108\u0110\7$\2\2\u0109\u0110\7\36\2\2\u010a\u0110\5\36\20\2"+
		"\u010b\u010c\b\r\1\2\u010c\u010d\5\32\16\2\u010d\u010e\b\r\1\2\u010e\u0110"+
		"\3\2\2\2\u010f\u0104\3\2\2\2\u010f\u0106\3\2\2\2\u010f\u0107\3\2\2\2\u010f"+
		"\u0108\3\2\2\2\u010f\u0109\3\2\2\2\u010f\u010a\3\2\2\2\u010f\u010b\3\2"+
		"\2\2\u0110\u0111\3\2\2\2\u0111\u0112\b\r\1\2\u0112\u0114\7\22\2\2\u0113"+
		"\u0115\7\23\2\2\u0114\u0113\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\3"+
		"\2\2\2\u0116\u0117\b\r\1\2\u0117\31\3\2\2\2\u0118\u0119\7!\2\2\u0119\u0121"+
		"\b\16\1\2\u011a\u011b\7\25\2\2\u011b\u011c\b\16\1\2\u011c\u0122\5\36\20"+
		"\2\u011d\u011e\7\26\2\2\u011e\u0122\b\16\1\2\u011f\u0120\7\27\2\2\u0120"+
		"\u0122\b\16\1\2\u0121\u011a\3\2\2\2\u0121\u011d\3\2\2\2\u0121\u011f\3"+
		"\2\2\2\u0122\u0124\3\2\2\2\u0123\u0125\7\23\2\2\u0124\u0123\3\2\2\2\u0124"+
		"\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\b\16\1\2\u0127\33\3\2\2"+
		"\2\u0128\u0129\7\16\2\2\u0129\u012a\7\21\2\2\u012a\u0134\b\17\1\2\u012b"+
		"\u012c\7!\2\2\u012c\u0135\b\17\1\2\u012d\u012e\7#\2\2\u012e\u0135\b\17"+
		"\1\2\u012f\u0130\7\"\2\2\u0130\u0135\b\17\1\2\u0131\u0132\7\36\2\2\u0132"+
		"\u0135\b\17\1\2\u0133\u0135\5\36\20\2\u0134\u012b\3\2\2\2\u0134\u012d"+
		"\3\2\2\2\u0134\u012f\3\2\2\2\u0134\u0131\3\2\2\2\u0134\u0133\3\2\2\2\u0135"+
		"\u0136\3\2\2\2\u0136\u014d\b\17\1\2\u0137\u013d\7\32\2\2\u0138\u0139\7"+
		"\33\2\2\u0139\u013d\b\17\1\2\u013a\u013b\7\34\2\2\u013b\u013d\b\17\1\2"+
		"\u013c\u0137\3\2\2\2\u013c\u0138\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u014a\b\17\1\2\u013f\u0140\7!\2\2\u0140\u014b\b\17\1\2"+
		"\u0141\u0142\7#\2\2\u0142\u014b\b\17\1\2\u0143\u0144\7\"\2\2\u0144\u014b"+
		"\b\17\1\2\u0145\u0146\7\36\2\2\u0146\u014b\b\17\1\2\u0147\u014b\7\37\2"+
		"\2\u0148\u014b\7 \2\2\u0149\u014b\5\36\20\2\u014a\u013f\3\2\2\2\u014a"+
		"\u0141\3\2\2\2\u014a\u0143\3\2\2\2\u014a\u0145\3\2\2\2\u014a\u0147\3\2"+
		"\2\2\u014a\u0148\3\2\2\2\u014a\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\u014e\b\17\1\2\u014d\u013c\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\3"+
		"\2\2\2\u014f\u0150\7\22\2\2\u0150\u0151\7\30\2\2\u0151\u0153\b\17\1\2"+
		"\u0152\u0154\5\f\7\2\u0153\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0153"+
		"\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0158\7\31\2\2"+
		"\u0158\u018d\b\17\1\2\u0159\u015a\7\17\2\2\u015a\u015b\7\21\2\2\u015b"+
		"\u0165\b\17\1\2\u015c\u015d\7!\2\2\u015d\u0166\b\17\1\2\u015e\u015f\7"+
		"#\2\2\u015f\u0166\b\17\1\2\u0160\u0161\7\"\2\2\u0161\u0166\b\17\1\2\u0162"+
		"\u0163\7\36\2\2\u0163\u0166\b\17\1\2\u0164\u0166\5\36\20\2\u0165\u015c"+
		"\3\2\2\2\u0165\u015e\3\2\2\2\u0165\u0160\3\2\2\2\u0165\u0162\3\2\2\2\u0165"+
		"\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u017e\b\17\1\2\u0168\u016e\7"+
		"\32\2\2\u0169\u016a\7\33\2\2\u016a\u016e\b\17\1\2\u016b\u016c\7\34\2\2"+
		"\u016c\u016e\b\17\1\2\u016d\u0168\3\2\2\2\u016d\u0169\3\2\2\2\u016d\u016b"+
		"\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u017b\b\17\1\2\u0170\u0171\7!\2\2\u0171"+
		"\u017c\b\17\1\2\u0172\u0173\7#\2\2\u0173\u017c\b\17\1\2\u0174\u0175\7"+
		"\"\2\2\u0175\u017c\b\17\1\2\u0176\u0177\7\36\2\2\u0177\u017c\b\17\1\2"+
		"\u0178\u017c\7\37\2\2\u0179\u017c\7 \2\2\u017a\u017c\5\36\20\2\u017b\u0170"+
		"\3\2\2\2\u017b\u0172\3\2\2\2\u017b\u0174\3\2\2\2\u017b\u0176\3\2\2\2\u017b"+
		"\u0178\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2"+
		"\2\2\u017d\u017f\b\17\1\2\u017e\u016d\3\2\2\2\u017e\u017f\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u0181\7\22\2\2\u0181\u0182\7\30\2\2\u0182\u0184\b"+
		"\17\1\2\u0183\u0185\5\f\7\2\u0184\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\7\31"+
		"\2\2\u0189\u018a\b\17\1\2\u018a\u018c\3\2\2\2\u018b\u0159\3\2\2\2\u018c"+
		"\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u019b\3\2"+
		"\2\2\u018f\u018d\3\2\2\2\u0190\u0191\7\20\2\2\u0191\u0192\7\30\2\2\u0192"+
		"\u0194\b\17\1\2\u0193\u0195\5\f\7\2\u0194\u0193\3\2\2\2\u0195\u0196\3"+
		"\2\2\2\u0196\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198\3\2\2\2\u0198"+
		"\u0199\7\31\2\2\u0199\u019a\b\17\1\2\u019a\u019c\3\2\2\2\u019b\u0190\3"+
		"\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\b\17\1\2\u019e"+
		"\35\3\2\2\2\u019f\u01a5\5 \21\2\u01a0\u01a1\7\24\2\2\u01a1\u01a2\b\20"+
		"\1\2\u01a2\u01a4\5 \21\2\u01a3\u01a0\3\2\2\2\u01a4\u01a7\3\2\2\2\u01a5"+
		"\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\37\3\2\2\2\u01a7\u01a5\3\2\2"+
		"\2\u01a8\u01a9\7!\2\2\u01a9\u01b3\b\21\1\2\u01aa\u01ab\7#\2\2\u01ab\u01b3"+
		"\b\21\1\2\u01ac\u01ad\7$\2\2\u01ad\u01b3\b\21\1\2\u01ae\u01af\7\"\2\2"+
		"\u01af\u01b3\b\21\1\2\u01b0\u01b1\7\36\2\2\u01b1\u01b3\b\21\1\2\u01b2"+
		"\u01a8\3\2\2\2\u01b2\u01aa\3\2\2\2\u01b2\u01ac\3\2\2\2\u01b2\u01ae\3\2"+
		"\2\2\u01b2\u01b0\3\2\2\2\u01b3!\3\2\2\2.$\'/9=GMTYiq\177\u0082\u008a\u0095"+
		"\u00a7\u00af\u00bd\u00c0\u00c4\u00d5\u00db\u00e5\u00f2\u00fd\u010f\u0114"+
		"\u0121\u0124\u0134\u013c\u014a\u014d\u0155\u0165\u016d\u017b\u017e\u0186"+
		"\u018d\u0196\u019b\u01a5\u01b2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}