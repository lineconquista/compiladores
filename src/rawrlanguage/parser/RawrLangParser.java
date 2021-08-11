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
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, OP=17, ATTR=18, 
		ICR=19, ACH=20, FCH=21, OPREL=22, VIR=23, ID=24, INT=25, DOUBLE=26, BOOLEAN=27, 
		TEXT=28, CM=29, WS=30;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_decl_var = 2, RULE_type = 3, RULE_code = 4, 
		RULE_cmd = 5, RULE_cmdloop = 6, RULE_cmdloop1 = 7, RULE_cmdloop2 = 8, 
		RULE_cmdloop3 = 9, RULE_cmd_read = 10, RULE_cmd_write = 11, RULE_cmd_attrib = 12, 
		RULE_cmd_conditional = 13, RULE_expr = 14, RULE_term = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "decl_var", "type", "code", "cmd", "cmdloop", "cmdloop1", 
			"cmdloop2", "cmdloop3", "cmd_read", "cmd_write", "cmd_attrib", "cmd_conditional", 
			"expr", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'start:'", "'end'", "'double'", "'int'", "'boolean'", "'text'", 
			"'while'", "'do'", "'for'", "'read'", "'write'", "'if'", "'else'", "'('", 
			"')'", "';'", null, "'='", "'++'", "'{'", "'}'", null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "AP", "FP", "SC", "OP", "ATTR", "ICR", "ACH", "FCH", "OPREL", 
			"VIR", "ID", "INT", "DOUBLE", "BOOLEAN", "TEXT", "CM", "WS"
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
			setState(43);
			type();
			setState(44);
			match(ID);

								_varName = _input.LT(-1).getText();
								_varValue = null;
								symbol = new RawrVariable(_varName, _type, _varValue);
								
								if(!symbolTable.exists(_varName)){
									symbolTable.add(symbol);
									
								} else {
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
									
										if(!symbolTable.exists(_varName)){
											symbolTable.add(symbol);
											
										} else {
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
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(T__2);
				_type = RawrVariable.DOUBLE;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(T__3);
				_type = RawrVariable.INT;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(T__4);
				_type = RawrVariable.BOOLEAN;
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				match(T__5);
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
				   
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				cmd();
				}
				}
				setState(71); 
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
		public CmdloopContext cmdloop() {
			return getRuleContext(CmdloopContext.class,0);
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
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				cmd_read();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				cmd_write();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				cmd_attrib();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(76);
				cmd_conditional();
				}
				break;
			case T__6:
			case T__7:
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(77);
				cmdloop();
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

	public static class CmdloopContext extends ParserRuleContext {
		public Cmdloop1Context cmdloop1() {
			return getRuleContext(Cmdloop1Context.class,0);
		}
		public Cmdloop2Context cmdloop2() {
			return getRuleContext(Cmdloop2Context.class,0);
		}
		public Cmdloop3Context cmdloop3() {
			return getRuleContext(Cmdloop3Context.class,0);
		}
		public CmdloopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdloop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmdloop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmdloop(this);
		}
	}

	public final CmdloopContext cmdloop() throws RecognitionException {
		CmdloopContext _localctx = new CmdloopContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdloop);
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				cmdloop1();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				cmdloop2();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
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
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public TerminalNode INT() { return getToken(RawrLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(RawrLangParser.DOUBLE, 0); }
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
			setState(85);
			match(T__6);
			setState(86);
			match(AP);
			setState(87);
			match(ID);

										variableValidateValue(_input.LT(-1).getText());
										_exprRepetition = _input.LT(-1).getText();
										
									
			setState(89);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(91);
				match(ID);
				variableValidateValue(_input.LT(-1).getText());
				}
				break;
			case INT:
				{
				setState(93);
				match(INT);
				}
				break;
			case DOUBLE:
				{
				setState(94);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

										_exprRepetition += _input.LT(-1).getText();
									
			setState(98);
			match(FP);
			setState(99);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(102); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101);
				cmd();
				}
				}
				setState(104); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(106);
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
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(RawrLangParser.SC, 0); }
		public TerminalNode INT() { return getToken(RawrLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(RawrLangParser.DOUBLE, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
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
			setState(109);
			match(T__7);
			setState(110);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				cmd();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(117);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
									
			setState(119);
			match(T__6);
			setState(120);
			match(AP);
			setState(121);
			match(ID);

								 		variableValidateValue(_input.LT(-1).getText());
								 		_exprRepetition = _input.LT(-1).getText();
								 		
								 	
			setState(123);
			match(OPREL);

								 		_exprRepetition += _input.LT(-1).getText();
								 	
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(125);
				match(ID);
				variableValidateValue(_input.LT(-1).getText());
				}
				break;
			case INT:
				{
				setState(127);
				match(INT);
				}
				break;
			case DOUBLE:
				{
				setState(128);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

								 		_exprRepetition += _input.LT(-1).getText();
								 	
			setState(132);
			match(FP);
			setState(133);
			match(SC);

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
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
		public List<TerminalNode> ATTR() { return getTokens(RawrLangParser.ATTR); }
		public TerminalNode ATTR(int i) {
			return getToken(RawrLangParser.ATTR, i);
		}
		public List<TerminalNode> SC() { return getTokens(RawrLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(RawrLangParser.SC, i);
		}
		public TerminalNode OPREL() { return getToken(RawrLangParser.OPREL, 0); }
		public TerminalNode OP() { return getToken(RawrLangParser.OP, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public List<TerminalNode> INT() { return getTokens(RawrLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RawrLangParser.INT, i);
		}
		public List<TerminalNode> DOUBLE() { return getTokens(RawrLangParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(RawrLangParser.DOUBLE, i);
		}
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
			setState(136);
			match(T__8);
			setState(137);
			match(AP);
			setState(138);
			match(ID);

										variableValidateValue(_input.LT(-1).getText());
										_exprRepetition = _input.LT(-1).getText();
										
									
			setState(140);
			match(ATTR);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(142);
				match(ID);
				variableValidateValue(_input.LT(-1).getText());
				}
				break;
			case INT:
				{
				setState(144);
				match(INT);
				}
				break;
			case DOUBLE:
				{
				setState(145);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

										_exprRepetition += _input.LT(-1).getText();
									
			setState(149);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(151);
			match(ID);

										variableValidateValue(_input.LT(-1).getText());
										_exprRepetition += _input.LT(-1).getText();
									
			setState(153);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(155);
				match(ID);
				variableValidateValue(_input.LT(-1).getText());
				}
				break;
			case INT:
				{
				setState(157);
				match(INT);
				}
				break;
			case DOUBLE:
				{
				setState(158);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

										_exprRepetition += _input.LT(-1).getText();
									
			setState(162);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(164);
			match(ID);

										variableValidateValue(_input.LT(-1).getText());
										_exprRepetition += _input.LT(-1).getText();
									
			setState(166);
			match(ATTR);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(168);
			match(ID);

										variableValidateValue(_input.LT(-1).getText());
										_exprRepetition += _input.LT(-1).getText();
									
			setState(170);
			match(OP);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(172);
				match(ID);
				variableValidateValue(_input.LT(-1).getText());
				}
				break;
			case INT:
				{
				setState(174);
				match(INT);
				}
				break;
			case DOUBLE:
				{
				setState(175);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

										_exprRepetition += _input.LT(-1).getText();
									
			setState(179);
			match(FP);
			setState(180);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182);
				cmd();
				}
				}
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(187);
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
			setState(190);
			match(T__9);
			setState(191);
			match(AP);
			setState(192);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_readId = _input.LT(-1).getText();
									 
			setState(194);
			match(FP);
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(195);
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
		public TerminalNode INT() { return getToken(RawrLangParser.INT, 0); }
		public TerminalNode TEXT() { return getToken(RawrLangParser.TEXT, 0); }
		public TerminalNode DOUBLE() { return getToken(RawrLangParser.DOUBLE, 0); }
		public TerminalNode BOOLEAN() { return getToken(RawrLangParser.BOOLEAN, 0); }
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
			setState(200);
			match(T__10);
			setState(201);
			match(AP);
			setState(212);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(202);
				match(ID);

										variableValidate(_input.LT(-1).getText());
										variableValidateValue(_input.LT(-1).getText());
										_writeId = _input.LT(-1).getText();
									
				}
				break;
			case INT:
				{
				setState(204);
				match(INT);

										_writeId = _input.LT(-1).getText();
									
				}
				break;
			case TEXT:
				{
				setState(206);
				match(TEXT);

										_writeId = _input.LT(-1).getText();
									
				}
				break;
			case DOUBLE:
				{
				setState(208);
				match(DOUBLE);

										_writeId = _input.LT(-1).getText();
									
				}
				break;
			case BOOLEAN:
				{
				setState(210);
				match(BOOLEAN);

										_writeId = _input.LT(-1).getText();
									
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(214);
			match(FP);
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(215);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(ID);

								variableValidate(_input.LT(-1).getText());
								_exprId = _input.LT(-1).getText();
							
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTR:
				{
				setState(222);
				match(ATTR);
				_exprContent = "";
				setState(224);
				expr();
				}
				break;
			case T__1:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case SC:
			case FCH:
			case ID:
				{
				}
				break;
			case ICR:
				{
				setState(226);
				match(ICR);
				variableValidateValue(_exprId); _exprContent = _exprId + " + 1";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(230);
				match(SC);
				}
			}


							CommandAttrib cmd = new CommandAttrib (_exprId, _exprContent, symbolTable);
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
		public List<TerminalNode> INT() { return getTokens(RawrLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RawrLangParser.INT, i);
		}
		public List<TerminalNode> DOUBLE() { return getTokens(RawrLangParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(RawrLangParser.DOUBLE, i);
		}
		public TerminalNode TEXT() { return getToken(RawrLangParser.TEXT, 0); }
		public TerminalNode BOOLEAN() { return getToken(RawrLangParser.BOOLEAN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
			setState(235);
			match(T__11);
			setState(236);
			match(AP);
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(237);
				match(ID);
				variableValidateValue(_input.LT(-1).getText()); 
				}
				break;
			case 2:
				{
				setState(239);
				match(INT);
				}
				break;
			case 3:
				{
				setState(240);
				match(DOUBLE);
				}
				break;
			case 4:
				{
				setState(241);
				match(TEXT);
				}
				break;
			case 5:
				{
				setState(242);
				match(BOOLEAN);
				}
				break;
			case 6:
				{
				setState(243);
				expr();
				}
				break;
			}
			 _exprDecision = _input.LT(-1).getText(); 
			setState(247);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(253);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(249);
				match(ID);
				variableValidateValue(_input.LT(-1).getText());
				}
				break;
			case INT:
				{
				setState(251);
				match(INT);
				}
				break;
			case DOUBLE:
				{
				setState(252);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprDecision += _input.LT(-1).getText(); 
			setState(256);
			match(FP);
			setState(257);
			match(ACH);
			 
						  			curThread = new ArrayList<AbstractCommand>();
							  		stack.push(curThread);
							   
			setState(260); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(259);
				cmd();
				}
				}
				setState(262); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(264);
			match(FCH);
			 listTrue = stack.pop(); 
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(266);
				match(T__12);
				setState(267);
				match(ACH);

							   	 	   	curThread = new ArrayList<AbstractCommand>();
									  	stack.push(curThread);
									 
				{
				setState(270); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(269);
					cmd();
					}
					}
					setState(272); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				}
				setState(274);
				match(FCH);
				 
							   	 	     listFalse = stack.pop(); 
							   	 	   	 CommandConditional cmd = new CommandConditional (_exprDecision, listTrue, listFalse);
							   	 	   	 stack.peek().add(cmd);
						   	 	    
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
			setState(279);
			term();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(280);
				match(OP);
				_exprContent += _input.LT(-1).getText();
				setState(282);
				term();
				}
				}
				setState(287);
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
		public TerminalNode INT() { return getToken(RawrLangParser.INT, 0); }
		public TerminalNode TEXT() { return getToken(RawrLangParser.TEXT, 0); }
		public TerminalNode DOUBLE() { return getToken(RawrLangParser.DOUBLE, 0); }
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
			setState(298);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(ID);

								variableValidate(_input.LT(-1).getText());
								variableValidateValue(_input.LT(-1).getText());
								variableValidateType(_input.LT(-1).getText(),((RawrVariable) symbolTable.get(_exprId)).getType());
								_exprContent += _input.LT(-1).getText();
						   
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(INT);
					variableValidateType(_exprId, 2);
							_exprContent += _input.LT(-1).getText();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(292);
				match(TEXT);
					variableValidateType(_exprId, 1);
							_exprContent += _input.LT(-1).getText();
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(294);
				match(DOUBLE);
					variableValidateType(_exprId, 0);
							_exprContent += _input.LT(-1).getText();
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 5);
				{
				setState(296);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u012f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\6\3*\n\3\r\3\16\3+\3\4\3\4\3\4\3\4\3\4\3\4\7\4\64\n"+
		"\4\f\4\16\4\67\13\4\3\4\5\4:\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5D"+
		"\n\5\3\6\3\6\6\6H\n\6\r\6\16\6I\3\7\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b\3"+
		"\b\5\bV\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tb\n\t\3\t\3\t\3"+
		"\t\3\t\3\t\6\ti\n\t\r\t\16\tj\3\t\3\t\3\t\3\n\3\n\3\n\3\n\6\nt\n\n\r\n"+
		"\16\nu\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0084\n\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5"+
		"\13\u0095\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u00a2\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u00b3\n\13\3\13\3\13\3\13\3\13\3\13\6\13\u00ba"+
		"\n\13\r\13\16\13\u00bb\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c7"+
		"\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00d7"+
		"\n\r\3\r\3\r\5\r\u00db\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u00e7\n\16\3\16\5\16\u00ea\n\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\5\17\u00f7\n\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\5\17\u0100\n\17\3\17\3\17\3\17\3\17\3\17\6\17\u0107\n\17\r"+
		"\17\16\17\u0108\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u0111\n\17\r\17\16"+
		"\17\u0112\3\17\3\17\3\17\5\17\u0118\n\17\3\20\3\20\3\20\3\20\7\20\u011e"+
		"\n\20\f\20\16\20\u0121\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\5\21\u012d\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \2\2\2\u0150\2\"\3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\bC\3\2\2\2\nE\3"+
		"\2\2\2\fP\3\2\2\2\16U\3\2\2\2\20W\3\2\2\2\22o\3\2\2\2\24\u008a\3\2\2\2"+
		"\26\u00c0\3\2\2\2\30\u00ca\3\2\2\2\32\u00de\3\2\2\2\34\u00ed\3\2\2\2\36"+
		"\u0119\3\2\2\2 \u012c\3\2\2\2\"#\7\3\2\2#$\5\4\3\2$%\5\n\6\2%&\7\4\2\2"+
		"&\'\b\2\1\2\'\3\3\2\2\2(*\5\6\4\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2"+
		"\2\2,\5\3\2\2\2-.\5\b\5\2./\7\32\2\2/\65\b\4\1\2\60\61\7\31\2\2\61\62"+
		"\7\32\2\2\62\64\b\4\1\2\63\60\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66"+
		"\3\2\2\2\669\3\2\2\2\67\65\3\2\2\28:\7\22\2\298\3\2\2\29:\3\2\2\2:\7\3"+
		"\2\2\2;<\7\5\2\2<D\b\5\1\2=>\7\6\2\2>D\b\5\1\2?@\7\7\2\2@D\b\5\1\2AB\7"+
		"\b\2\2BD\b\5\1\2C;\3\2\2\2C=\3\2\2\2C?\3\2\2\2CA\3\2\2\2D\t\3\2\2\2EG"+
		"\b\6\1\2FH\5\f\7\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\13\3\2\2\2"+
		"KQ\5\26\f\2LQ\5\30\r\2MQ\5\32\16\2NQ\5\34\17\2OQ\5\16\b\2PK\3\2\2\2PL"+
		"\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\r\3\2\2\2RV\5\20\t\2SV\5\22\n"+
		"\2TV\5\24\13\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\17\3\2\2\2WX\7\t\2\2XY\7"+
		"\20\2\2YZ\7\32\2\2Z[\b\t\1\2[\\\7\30\2\2\\a\b\t\1\2]^\7\32\2\2^b\b\t\1"+
		"\2_b\7\33\2\2`b\7\34\2\2a]\3\2\2\2a_\3\2\2\2a`\3\2\2\2bc\3\2\2\2cd\b\t"+
		"\1\2de\7\21\2\2ef\7\26\2\2fh\b\t\1\2gi\5\f\7\2hg\3\2\2\2ij\3\2\2\2jh\3"+
		"\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7\27\2\2mn\b\t\1\2n\21\3\2\2\2op\7\n\2\2"+
		"pq\7\26\2\2qs\b\n\1\2rt\5\f\7\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2"+
		"\2vw\3\2\2\2wx\7\27\2\2xy\b\n\1\2yz\7\t\2\2z{\7\20\2\2{|\7\32\2\2|}\b"+
		"\n\1\2}~\7\30\2\2~\u0083\b\n\1\2\177\u0080\7\32\2\2\u0080\u0084\b\n\1"+
		"\2\u0081\u0084\7\33\2\2\u0082\u0084\7\34\2\2\u0083\177\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\b\n\1\2\u0086"+
		"\u0087\7\21\2\2\u0087\u0088\7\22\2\2\u0088\u0089\b\n\1\2\u0089\23\3\2"+
		"\2\2\u008a\u008b\7\13\2\2\u008b\u008c\7\20\2\2\u008c\u008d\7\32\2\2\u008d"+
		"\u008e\b\13\1\2\u008e\u008f\7\24\2\2\u008f\u0094\b\13\1\2\u0090\u0091"+
		"\7\32\2\2\u0091\u0095\b\13\1\2\u0092\u0095\7\33\2\2\u0093\u0095\7\34\2"+
		"\2\u0094\u0090\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096\u0097\b\13\1\2\u0097\u0098\7\22\2\2\u0098\u0099\b\13\1"+
		"\2\u0099\u009a\7\32\2\2\u009a\u009b\b\13\1\2\u009b\u009c\7\30\2\2\u009c"+
		"\u00a1\b\13\1\2\u009d\u009e\7\32\2\2\u009e\u00a2\b\13\1\2\u009f\u00a2"+
		"\7\33\2\2\u00a0\u00a2\7\34\2\2\u00a1\u009d\3\2\2\2\u00a1\u009f\3\2\2\2"+
		"\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\13\1\2\u00a4\u00a5"+
		"\7\22\2\2\u00a5\u00a6\b\13\1\2\u00a6\u00a7\7\32\2\2\u00a7\u00a8\b\13\1"+
		"\2\u00a8\u00a9\7\24\2\2\u00a9\u00aa\b\13\1\2\u00aa\u00ab\7\32\2\2\u00ab"+
		"\u00ac\b\13\1\2\u00ac\u00ad\7\23\2\2\u00ad\u00b2\b\13\1\2\u00ae\u00af"+
		"\7\32\2\2\u00af\u00b3\b\13\1\2\u00b0\u00b3\7\33\2\2\u00b1\u00b3\7\34\2"+
		"\2\u00b2\u00ae\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4"+
		"\3\2\2\2\u00b4\u00b5\b\13\1\2\u00b5\u00b6\7\21\2\2\u00b6\u00b7\7\26\2"+
		"\2\u00b7\u00b9\b\13\1\2\u00b8\u00ba\5\f\7\2\u00b9\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\u00be\7\27\2\2\u00be\u00bf\b\13\1\2\u00bf\25\3\2\2\2\u00c0"+
		"\u00c1\7\f\2\2\u00c1\u00c2\7\20\2\2\u00c2\u00c3\7\32\2\2\u00c3\u00c4\b"+
		"\f\1\2\u00c4\u00c6\7\21\2\2\u00c5\u00c7\7\22\2\2\u00c6\u00c5\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b\f\1\2\u00c9\27\3\2\2"+
		"\2\u00ca\u00cb\7\r\2\2\u00cb\u00d6\7\20\2\2\u00cc\u00cd\7\32\2\2\u00cd"+
		"\u00d7\b\r\1\2\u00ce\u00cf\7\33\2\2\u00cf\u00d7\b\r\1\2\u00d0\u00d1\7"+
		"\36\2\2\u00d1\u00d7\b\r\1\2\u00d2\u00d3\7\34\2\2\u00d3\u00d7\b\r\1\2\u00d4"+
		"\u00d5\7\35\2\2\u00d5\u00d7\b\r\1\2\u00d6\u00cc\3\2\2\2\u00d6\u00ce\3"+
		"\2\2\2\u00d6\u00d0\3\2\2\2\u00d6\u00d2\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00da\7\21\2\2\u00d9\u00db\7\22\2\2\u00da\u00d9\3"+
		"\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\b\r\1\2\u00dd"+
		"\31\3\2\2\2\u00de\u00df\7\32\2\2\u00df\u00e6\b\16\1\2\u00e0\u00e1\7\24"+
		"\2\2\u00e1\u00e2\b\16\1\2\u00e2\u00e7\5\36\20\2\u00e3\u00e7\3\2\2\2\u00e4"+
		"\u00e5\7\25\2\2\u00e5\u00e7\b\16\1\2\u00e6\u00e0\3\2\2\2\u00e6\u00e3\3"+
		"\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00ea\7\22\2\2\u00e9"+
		"\u00e8\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\b\16"+
		"\1\2\u00ec\33\3\2\2\2\u00ed\u00ee\7\16\2\2\u00ee\u00f6\7\20\2\2\u00ef"+
		"\u00f0\7\32\2\2\u00f0\u00f7\b\17\1\2\u00f1\u00f7\7\33\2\2\u00f2\u00f7"+
		"\7\34\2\2\u00f3\u00f7\7\36\2\2\u00f4\u00f7\7\35\2\2\u00f5\u00f7\5\36\20"+
		"\2\u00f6\u00ef\3\2\2\2\u00f6\u00f1\3\2\2\2\u00f6\u00f2\3\2\2\2\u00f6\u00f3"+
		"\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8"+
		"\u00f9\b\17\1\2\u00f9\u00fa\7\30\2\2\u00fa\u00ff\b\17\1\2\u00fb\u00fc"+
		"\7\32\2\2\u00fc\u0100\b\17\1\2\u00fd\u0100\7\33\2\2\u00fe\u0100\7\34\2"+
		"\2\u00ff\u00fb\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u00fe\3\2\2\2\u0100\u0101"+
		"\3\2\2\2\u0101\u0102\b\17\1\2\u0102\u0103\7\21\2\2\u0103\u0104\7\26\2"+
		"\2\u0104\u0106\b\17\1\2\u0105\u0107\5\f\7\2\u0106\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a\3\2"+
		"\2\2\u010a\u010b\7\27\2\2\u010b\u0117\b\17\1\2\u010c\u010d\7\17\2\2\u010d"+
		"\u010e\7\26\2\2\u010e\u0110\b\17\1\2\u010f\u0111\5\f\7\2\u0110\u010f\3"+
		"\2\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0115\7\27\2\2\u0115\u0116\b\17\1\2\u0116\u0118\3"+
		"\2\2\2\u0117\u010c\3\2\2\2\u0117\u0118\3\2\2\2\u0118\35\3\2\2\2\u0119"+
		"\u011f\5 \21\2\u011a\u011b\7\23\2\2\u011b\u011c\b\20\1\2\u011c\u011e\5"+
		" \21\2\u011d\u011a\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120\37\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\7\32\2"+
		"\2\u0123\u012d\b\21\1\2\u0124\u0125\7\33\2\2\u0125\u012d\b\21\1\2\u0126"+
		"\u0127\7\36\2\2\u0127\u012d\b\21\1\2\u0128\u0129\7\34\2\2\u0129\u012d"+
		"\b\21\1\2\u012a\u012b\7\35\2\2\u012b\u012d\b\21\1\2\u012c\u0122\3\2\2"+
		"\2\u012c\u0124\3\2\2\2\u012c\u0126\3\2\2\2\u012c\u0128\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012d!\3\2\2\2\35+\659CIPUaju\u0083\u0094\u00a1\u00b2\u00bb\u00c6"+
		"\u00d6\u00da\u00e6\u00e9\u00f6\u00ff\u0108\u0112\u0117\u011f\u012c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}