// Generated from RawrLang.g4 by ANTLR 4.9.2
package rawrlanguage.parser;

	import datastructures.RawrSymbol;
	import datastructures.RawrVariable;
	import datastructures.RawrSymbolTable;
	import exceptions.RawrSemanticException;
	import java.util.ArrayList;
	import java.util.Stack;
	import ast.AbstractCommand;
	import ast.RawrProgram;
	import ast.CommandRead;
	import ast.CommandWrite;
	import ast.CommandAttrib;
	import ast.CommandConditional;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, AP=9, 
		FP=10, SC=11, OP=12, ATTR=13, ACH=14, FCH=15, OPREL=16, VIR=17, ID=18, 
		NUMBER=19, TEXT=20, WS=21;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_decl_var = 2, RULE_type = 3, RULE_code = 4, 
		RULE_cmd = 5, RULE_cmd_read = 6, RULE_cmd_write = 7, RULE_cmd_attrib = 8, 
		RULE_cmd_conditional = 9, RULE_expr = 10, RULE_term = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "decl_var", "type", "code", "cmd", "cmd_read", "cmd_write", 
			"cmd_attrib", "cmd_conditional", "expr", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'start:'", "'end'", "'number'", "'text'", "'read'", "'write'", 
			"'if'", "'else'", "'('", "')'", "';'", null, "'='", "'{'", "'}'", null, 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "AP", "FP", "SC", 
			"OP", "ATTR", "ACH", "FCH", "OPREL", "VIR", "ID", "NUMBER", "TEXT", "WS"
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
		private RawrSymbolTable symbolTable = new RawrSymbolTable();
		private RawrSymbol symbol;
		private RawrProgram program = new RawrProgram();
		private ArrayList <AbstractCommand> curThread;
		private ArrayList<AbstractCommand> listTrue;
		private ArrayList<AbstractCommand> listFalse;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack <ArrayList<AbstractCommand>>();

		
		public void variableValidate(String id){
			if (!symbolTable.exists(id)){
				throw new RawrSemanticException ("Variable "+id+" not declared");
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
			setState(24);
			match(T__0);
			setState(25);
			decl();
			setState(26);
			code();
			setState(27);
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
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(30);
				decl_var();
				}
				}
				setState(33); 
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
			setState(35);
			type();
			setState(36);
			match(ID);

								_varName = _input.LT(-1).getText();
								_varValue = null;
								symbol = new RawrVariable(_varName, _type, _varValue);
								
								if(!symbolTable.exists(_varName)){
									symbolTable.add(symbol);
									
								} else {
									throw new RawrSemanticException("Variable "+ _varName + " already declared");
								}
					   		
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(38);
				match(VIR);
				setState(39);
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
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(46);
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
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				match(T__2);
				_type = RawrVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
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
				   
			setState(57); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(56);
				cmd();
				}
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << ID))) != 0) );
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
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				cmd_read();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				cmd_write();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				cmd_attrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				cmd_conditional();
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
		enterRule(_localctx, 12, RULE_cmd_read);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(T__4);
			setState(68);
			match(AP);
			setState(69);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_readId = _input.LT(-1).getText();
									 
			setState(71);
			match(FP);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(72);
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
		public TerminalNode ID() { return getToken(RawrLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
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
		enterRule(_localctx, 14, RULE_cmd_write);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__5);
			setState(78);
			match(AP);
			setState(79);
			match(ID);

											variableValidate(_input.LT(-1).getText());
											_writeId = _input.LT(-1).getText();
									   
			setState(81);
			match(FP);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(82);
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
		enterRule(_localctx, 16, RULE_cmd_attrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ID);

								variableValidate(_input.LT(-1).getText());
								_exprId = _input.LT(-1).getText();
							
			setState(89);
			match(ATTR);
			_exprContent = "";
			setState(91);
			expr();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(92);
				match(SC);
				}
			}


							CommandAttrib cmd = new CommandAttrib (_exprId, _exprContent);
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
		public List<TerminalNode> ID() { return getTokens(RawrLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RawrLangParser.ID, i);
		}
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
		public TerminalNode NUMBER() { return getToken(RawrLangParser.NUMBER, 0); }
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
		enterRule(_localctx, 18, RULE_cmd_conditional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(T__6);
			setState(98);
			match(AP);
			setState(99);
			match(ID);
			 _exprDecision = _input.LT(-1).getText(); 
			setState(101);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(103);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprDecision += _input.LT(-1).getText(); 
			setState(105);
			match(FP);
			setState(106);
			match(ACH);
			 
						  			curThread = new ArrayList<AbstractCommand>();
							  		stack.push(curThread);
							   
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				cmd();
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << ID))) != 0) );
			setState(113);
			match(FCH);
			 listTrue = stack.pop(); 
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(115);
				match(T__7);
				setState(116);
				match(ACH);

							   	 	   	curThread = new ArrayList<AbstractCommand>();
									  	stack.push(curThread);
									 
				{
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(118);
					cmd();
					}
					}
					setState(121); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << ID))) != 0) );
				}
				setState(123);
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
		enterRule(_localctx, 20, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			term();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(129);
				match(OP);
				_exprContent += _input.LT(-1).getText();
				setState(131);
				term();
				}
				}
				setState(136);
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
		enterRule(_localctx, 22, RULE_term);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(ID);

								variableValidate(_input.LT(-1).getText());
								_exprContent += _input.LT(-1).getText();
						   
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(NUMBER);
				_exprContent += _input.LT(-1).getText();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				match(TEXT);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u0094\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3\"\n\3\r\3\16\3#"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\4\5\4\62\n\4\3\5\3"+
		"\5\3\5\3\5\5\58\n\5\3\6\3\6\6\6<\n\6\r\6\16\6=\3\7\3\7\3\7\3\7\5\7D\n"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\5\bL\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\tV\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n`\n\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13p\n\13\r\13\16\13q\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\6\13z\n\13\r\13\16\13{\3\13\3\13\3\13\5\13"+
		"\u0081\n\13\3\f\3\f\3\f\3\f\7\f\u0087\n\f\f\f\16\f\u008a\13\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u0092\n\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\2\3\3\2\24\25\2\u0098\2\32\3\2\2\2\4!\3\2\2\2\6%\3\2\2\2\b\67\3\2\2\2"+
		"\n9\3\2\2\2\fC\3\2\2\2\16E\3\2\2\2\20O\3\2\2\2\22Y\3\2\2\2\24c\3\2\2\2"+
		"\26\u0082\3\2\2\2\30\u0091\3\2\2\2\32\33\7\3\2\2\33\34\5\4\3\2\34\35\5"+
		"\n\6\2\35\36\7\4\2\2\36\37\b\2\1\2\37\3\3\2\2\2 \"\5\6\4\2! \3\2\2\2\""+
		"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\5\3\2\2\2%&\5\b\5\2&\'\7\24\2\2\'-\b\4"+
		"\1\2()\7\23\2\2)*\7\24\2\2*,\b\4\1\2+(\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3"+
		"\2\2\2.\61\3\2\2\2/-\3\2\2\2\60\62\7\r\2\2\61\60\3\2\2\2\61\62\3\2\2\2"+
		"\62\7\3\2\2\2\63\64\7\5\2\2\648\b\5\1\2\65\66\7\6\2\2\668\b\5\1\2\67\63"+
		"\3\2\2\2\67\65\3\2\2\28\t\3\2\2\29;\b\6\1\2:<\5\f\7\2;:\3\2\2\2<=\3\2"+
		"\2\2=;\3\2\2\2=>\3\2\2\2>\13\3\2\2\2?D\5\16\b\2@D\5\20\t\2AD\5\22\n\2"+
		"BD\5\24\13\2C?\3\2\2\2C@\3\2\2\2CA\3\2\2\2CB\3\2\2\2D\r\3\2\2\2EF\7\7"+
		"\2\2FG\7\13\2\2GH\7\24\2\2HI\b\b\1\2IK\7\f\2\2JL\7\r\2\2KJ\3\2\2\2KL\3"+
		"\2\2\2LM\3\2\2\2MN\b\b\1\2N\17\3\2\2\2OP\7\b\2\2PQ\7\13\2\2QR\7\24\2\2"+
		"RS\b\t\1\2SU\7\f\2\2TV\7\r\2\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\b\t\1\2"+
		"X\21\3\2\2\2YZ\7\24\2\2Z[\b\n\1\2[\\\7\17\2\2\\]\b\n\1\2]_\5\26\f\2^`"+
		"\7\r\2\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\b\n\1\2b\23\3\2\2\2cd\7\t\2\2"+
		"de\7\13\2\2ef\7\24\2\2fg\b\13\1\2gh\7\22\2\2hi\b\13\1\2ij\t\2\2\2jk\b"+
		"\13\1\2kl\7\f\2\2lm\7\20\2\2mo\b\13\1\2np\5\f\7\2on\3\2\2\2pq\3\2\2\2"+
		"qo\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7\21\2\2t\u0080\b\13\1\2uv\7\n\2\2vw"+
		"\7\20\2\2wy\b\13\1\2xz\5\f\7\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2"+
		"|}\3\2\2\2}~\7\21\2\2~\177\b\13\1\2\177\u0081\3\2\2\2\u0080u\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\25\3\2\2\2\u0082\u0088\5\30\r\2\u0083\u0084\7\16"+
		"\2\2\u0084\u0085\b\f\1\2\u0085\u0087\5\30\r\2\u0086\u0083\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\27\3\2\2"+
		"\2\u008a\u0088\3\2\2\2\u008b\u008c\7\24\2\2\u008c\u0092\b\r\1\2\u008d"+
		"\u008e\7\25\2\2\u008e\u0092\b\r\1\2\u008f\u0090\7\26\2\2\u0090\u0092\b"+
		"\r\1\2\u0091\u008b\3\2\2\2\u0091\u008d\3\2\2\2\u0091\u008f\3\2\2\2\u0092"+
		"\31\3\2\2\2\20#-\61\67=CKU_q{\u0080\u0088\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}