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
		OP=18, ATTR=19, ICR=20, DCR=21, ACH=22, FCH=23, OPREL=24, VIR=25, BOOLEAN=26, 
		FALSE=27, TRUE=28, ID=29, INT=30, DOUBLE=31, TEXT=32, CM=33, WS=34;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_decl_var = 2, RULE_type = 3, RULE_code = 4, 
		RULE_cmd = 5, RULE_cmd_loop = 6, RULE_cmdloop1 = 7, RULE_cmdloop2 = 8, 
		RULE_cmdloop3 = 9, RULE_cmdloop4 = 10, RULE_cmd_read = 11, RULE_cmd_write = 12, 
		RULE_cmd_attrib = 13, RULE_cmd_conditional = 14, RULE_cmd_conditional1 = 15, 
		RULE_cmd_conditional2 = 16, RULE_expr = 17, RULE_term = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "decl_var", "type", "code", "cmd", "cmd_loop", "cmdloop1", 
			"cmdloop2", "cmdloop3", "cmdloop4", "cmd_read", "cmd_write", "cmd_attrib", 
			"cmd_conditional", "cmd_conditional1", "cmd_conditional2", "expr", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'start:'", "'end'", "'double'", "'boolean'", "'text'", "'int'", 
			"'while'", "'do'", "'for'", "'read'", "'write'", "'if'", "'else if'", 
			"'else'", "'('", "')'", "';'", null, "'='", "'++'", "'--'", "'{'", "'}'", 
			null, "','", null, "'false'", "'true'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "AP", "FP", "SC", "OP", "ATTR", "ICR", "DCR", "ACH", 
			"FCH", "OPREL", "VIR", "BOOLEAN", "FALSE", "TRUE", "ID", "INT", "DOUBLE", 
			"TEXT", "CM", "WS"
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
			setState(38);
			match(T__0);
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) {
				{
				setState(39);
				decl();
				}
			}

			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0)) {
				{
				setState(42);
				code();
				}
			}

			setState(45);
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
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				decl_var();
				}
				}
				setState(51); 
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
			setState(53);
			type();
			setState(54);
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
					   				
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(56);
				match(VIR);
				setState(57);
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
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(64);
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
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				match(T__2);

											_type = RawrVariable.DOUBLE;
										
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__3);

					      					_type = RawrVariable.BOOLEAN;
					      				
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				match(T__4);

					      					_type = RawrVariable.TEXT;
					      				
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
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
				   					
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				cmd();
				}
				}
				setState(81); 
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
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				cmd_read();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				cmd_write();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				cmd_attrib();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(86);
				cmd_conditional();
				}
				break;
			case T__6:
			case T__7:
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(87);
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
		public Cmdloop4Context cmdloop4() {
			return getRuleContext(Cmdloop4Context.class,0);
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
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				cmdloop1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				cmdloop2();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				cmdloop3();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				cmdloop4();
				}
				break;
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
			setState(96);
			match(T__6);
			setState(97);
			match(AP);

						  				_exprContent = "";
						  			
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(99);
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
				setState(101);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(103);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(105);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(109);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();					
						  				_exprContent = "";
									
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(111);
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
				setState(113);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(115);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(117);
				expr();
				}
				break;
			}

										_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(121);
			match(FP);
			setState(122);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				cmd();
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(129);
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
			setState(132);
			match(T__7);
			setState(133);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(136); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135);
				cmd();
				}
				}
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(140);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
									
			setState(142);
			match(T__6);
			setState(143);
			match(AP);

						  				_exprContent = "";
						  			
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(145);
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
				setState(147);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(149);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(151);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(155);
			match(OPREL);

								 		_exprRepetition += _input.LT(-1).getText();
								 		_exprContent = "";
								 	
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(157);
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
				setState(159);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(161);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(163);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(167);
			match(FP);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(168);
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
			setState(173);
			match(T__8);
			setState(174);
			match(AP);
			setState(175);
			cmd_attrib();

										_exprRepetition = _exprTemp;
									
			setState(177);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(179);
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
				setState(181);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(183);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(185);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(189);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();
										_exprContent = "";
									
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(191);
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
				setState(193);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(195);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(197);
				expr();
				}
				break;
			}

										_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(201);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
										_despair = true;
									
			setState(203);
			cmd_attrib();

										_exprRepetition += _exprTemp;
									
			setState(205);
			match(FP);
			setState(206);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(209); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(208);
				cmd();
				}
				}
				setState(211); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(213);
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

	public static class Cmdloop4Context extends ParserRuleContext {
		public TerminalNode AP() { return getToken(RawrLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(RawrLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(RawrLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(RawrLangParser.FCH, 0); }
		public TerminalNode ID() { return getToken(RawrLangParser.ID, 0); }
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
		public Cmdloop4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdloop4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmdloop4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmdloop4(this);
		}
	}

	public final Cmdloop4Context cmdloop4() throws RecognitionException {
		Cmdloop4Context _localctx = new Cmdloop4Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdloop4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(T__6);
			setState(217);
			match(AP);

						  				_exprContent = "";
						  			
			setState(224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(219);
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
				setState(221);
				match(BOOLEAN);

											variableValidateType(_exprId, 3);
										
				}
				break;
			case 3:
				{
				setState(223);
				expr();
				}
				break;
			}
			setState(226);
			match(FP);
			setState(227);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(230); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(229);
				cmd();
				}
				}
				setState(232); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(234);
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
		enterRule(_localctx, 22, RULE_cmd_read);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__9);
			setState(238);
			match(AP);
			setState(239);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_readId = _input.LT(-1).getText();
									
			setState(241);
			match(FP);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(242);
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
		enterRule(_localctx, 24, RULE_cmd_write);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__10);
			setState(248);
			match(AP);

						  				_exprContent = "";
						  			
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(250);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
											}
										
				}
				break;
			case 2:
				{
				setState(252);
				match(DOUBLE);
				}
				break;
			case 3:
				{
				setState(253);
				match(INT);
				}
				break;
			case 4:
				{
				setState(254);
				match(TEXT);
				}
				break;
			case 5:
				{
				setState(255);
				expr();
				}
				break;
			case 6:
				{

											_despair = true;
										
				setState(257);
				cmd_attrib();
					
											_exprContent = _exprTemp;
										
				}
				break;
			}
				
						  				_writeId = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(263);
			match(FP);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(264);
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
		enterRule(_localctx, 26, RULE_cmd_attrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_exprId = _input.LT(-1).getText();
									
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTR:
				{
				setState(271);
				match(ATTR);

											_exprContent = "";
										
				setState(273);
				expr();
				}
				break;
			case ICR:
				{
				setState(274);
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
				setState(276);
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
			setState(281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(280);
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
		public Cmd_conditional1Context cmd_conditional1() {
			return getRuleContext(Cmd_conditional1Context.class,0);
		}
		public Cmd_conditional2Context cmd_conditional2() {
			return getRuleContext(Cmd_conditional2Context.class,0);
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
		enterRule(_localctx, 28, RULE_cmd_conditional);
		try {
			setState(287);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(285);
				cmd_conditional1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(286);
				cmd_conditional2();
				}
				break;
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

	public static class Cmd_conditional1Context extends ParserRuleContext {
		public List<TerminalNode> AP() { return getTokens(RawrLangParser.AP); }
		public TerminalNode AP(int i) {
			return getToken(RawrLangParser.AP, i);
		}
		public List<TerminalNode> OPREL() { return getTokens(RawrLangParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(RawrLangParser.OPREL, i);
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
		public List<TerminalNode> FALSE() { return getTokens(RawrLangParser.FALSE); }
		public TerminalNode FALSE(int i) {
			return getToken(RawrLangParser.FALSE, i);
		}
		public List<TerminalNode> TRUE() { return getTokens(RawrLangParser.TRUE); }
		public TerminalNode TRUE(int i) {
			return getToken(RawrLangParser.TRUE, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public Cmd_conditional1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_conditional1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd_conditional1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd_conditional1(this);
		}
	}

	public final Cmd_conditional1Context cmd_conditional1() throws RecognitionException {
		Cmd_conditional1Context _localctx = new Cmd_conditional1Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_cmd_conditional1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			match(T__11);
			setState(290);
			match(AP);

						  				_exprContent = "";
						  			
			setState(301);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(292);
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
				setState(294);
				match(DOUBLE);

							  				variableValidateType(_exprId, 0);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(296);
				match(INT);

							  				variableValidateType(_exprId, 2);
							  				
							  			
				}
				break;
			case 4:
				{
				setState(298);
				match(BOOLEAN);

							  				variableValidateType(_exprId, 3);
							  				
							  			
				}
				break;
			case 5:
				{
				setState(300);
				expr();
				}
				break;
			}
			 
						  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(304);
			match(OPREL);
			 
						  				
						  				_exprDecision += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(306);
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
				setState(308);
				match(DOUBLE);

							  				variableValidateType(_exprId, 0);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(310);
				match(INT);

							  				variableValidateType(_exprId, 2);
							  				
							  			
				}
				break;
			case 4:
				{
				setState(312);
				match(BOOLEAN);

							  				variableValidateType(_exprId, 3);
							  				
							  			
				}
				break;
			case 5:
				{
				setState(314);
				match(FALSE);
				}
				break;
			case 6:
				{
				setState(315);
				match(TRUE);
				}
				break;
			case 7:
				{
				setState(316);
				expr();
				}
				break;
			}
			 
						  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(320);
			match(FP);
			setState(321);
			match(ACH);
			 
						  				curThread = new ArrayList<AbstractCommand>();
							  			stack.push(curThread);
							   		
			setState(324); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(323);
				cmd();
				}
				}
				setState(326); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(328);
			match(FCH);
			 
						  				listTrue = stack.pop();
						  			
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(330);
				match(T__12);
				setState(331);
				match(AP);

							  				_exprContent = "";
							  			
				setState(363);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(333);
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
					setState(344);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						setState(335);
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
						setState(337);
						match(DOUBLE);

									  					variableValidateType(_exprId, 0);
									  				
									  				
						}
						break;
					case 3:
						{
						setState(339);
						match(INT);

									  					variableValidateType(_exprId, 2);
									  				
									  				
						}
						break;
					case 4:
						{
						setState(341);
						match(BOOLEAN);

									  				variableValidateType(_exprId, 3);
									  				
									  				
						}
						break;
					case 5:
						{
						setState(343);
						expr();
						}
						break;
					}
					 
									  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									  			
					setState(347);
					match(OPREL);
					 
									  				
									  				_exprDecision += _input.LT(-1).getText();
									  				_exprContent = "";
									  			
					setState(360);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
					case 1:
						{
						setState(349);
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
						setState(351);
						match(DOUBLE);

									  					variableValidateType(_exprId, 0);
									  				
									  				
						}
						break;
					case 3:
						{
						setState(353);
						match(INT);

									  					variableValidateType(_exprId, 2);
									  				
									  				
						}
						break;
					case 4:
						{
						setState(355);
						match(BOOLEAN);

									  					variableValidateType(_exprId, 3);
									  				
									  				
						}
						break;
					case 5:
						{
						setState(357);
						match(FALSE);
						}
						break;
					case 6:
						{
						setState(358);
						match(TRUE);
						}
						break;
					case 7:
						{
						setState(359);
						expr();
						}
						break;
					}
					 
									  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									  			
					}
					break;
				}
				setState(365);
				match(FP);
				setState(366);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
									 	
				setState(369); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(368);
					cmd();
					}
					}
					setState(371); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(373);
				match(FCH);
				 
							   	 	     	listTrue = stack.pop();
						   	 	    	
				}
			}

			setState(389);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(378);
				match(T__13);
				setState(379);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
									 	
				setState(382); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(381);
					cmd();
					}
					}
					setState(384); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(386);
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

	public static class Cmd_conditional2Context extends ParserRuleContext {
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
		public Cmd_conditional2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_conditional2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).enterCmd_conditional2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RawrLangListener ) ((RawrLangListener)listener).exitCmd_conditional2(this);
		}
	}

	public final Cmd_conditional2Context cmd_conditional2() throws RecognitionException {
		Cmd_conditional2Context _localctx = new Cmd_conditional2Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_cmd_conditional2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(T__11);
			setState(394);
			match(AP);

						  				_exprContent = "";
						  			
			setState(401);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(396);
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
				setState(398);
				match(BOOLEAN);

							  				variableValidateType(_exprId, 3);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(400);
				expr();
				}
				break;
			}
			 
						  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(404);
			match(FP);
			setState(405);
			match(ACH);
			 
						  				curThread = new ArrayList<AbstractCommand>();
							  			stack.push(curThread);
							   		
			setState(408); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(407);
				cmd();
				}
				}
				setState(410); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(412);
			match(FCH);
			 
						  				listTrue = stack.pop();
						  			
			setState(436);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(414);
				match(T__12);
				setState(415);
				match(AP);

							  				_exprContent = "";
							  			
				setState(422);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(417);
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
					setState(419);
					match(BOOLEAN);

								  					variableValidateType(_exprId, 3);
								  				
								  				
					}
					break;
				case 3:
					{
					setState(421);
					expr();
					}
					break;
				}
				 
								  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
								  			
				setState(425);
				match(FP);
				setState(426);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
									 	
				setState(429); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(428);
					cmd();
					}
					}
					setState(431); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(433);
				match(FCH);
				 
							   	 	     	listTrue = stack.pop();
						   	 	    	
				}
			}

			setState(449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(438);
				match(T__13);
				setState(439);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
									 	
				setState(442); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(441);
					cmd();
					}
					}
					setState(444); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(446);
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
		enterRule(_localctx, 34, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			term();
			setState(459);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(454);
				match(OP);

					   						_exprContent += _input.LT(-1).getText();
					   					
				setState(456);
				term();
				}
				}
				setState(461);
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
		enterRule(_localctx, 36, RULE_term);
		try {
			setState(472);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(462);
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
				setState(464);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(466);
				match(TEXT);
					
											variableValidateType(_exprId, 1);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(468);
				match(INT);

											variableValidateType(_exprId, 2);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 5);
				{
				setState(470);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u01dd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\5\2+\n\2\3\2\5\2.\n\2\3\2\3\2\3\2\3\3\6\3"+
		"\64\n\3\r\3\16\3\65\3\4\3\4\3\4\3\4\3\4\3\4\7\4>\n\4\f\4\16\4A\13\4\3"+
		"\4\5\4D\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5N\n\5\3\6\3\6\6\6R\n\6"+
		"\r\6\16\6S\3\7\3\7\3\7\3\7\3\7\5\7[\n\7\3\b\3\b\3\b\3\b\5\ba\n\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tm\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\ty\n\t\3\t\3\t\3\t\3\t\3\t\6\t\u0080\n\t\r\t\16\t\u0081"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\6\n\u008b\n\n\r\n\16\n\u008c\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009b\n\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\5\n\u00a7\n\n\3\n\3\n\3\n\5\n\u00ac\n\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00bd"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00c9\n\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u00d4\n\13\r\13\16"+
		"\13\u00d5\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e3\n\f"+
		"\3\f\3\f\3\f\3\f\6\f\u00e9\n\f\r\f\16\f\u00ea\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\5\r\u00f6\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0107\n\16\3\16\3\16\3\16\5\16\u010c"+
		"\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0119"+
		"\n\17\3\17\5\17\u011c\n\17\3\17\3\17\3\20\3\20\5\20\u0122\n\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0130\n\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u0140\n\21\3\21\3\21\3\21\3\21\3\21\6\21\u0147\n\21\r\21\16\21\u0148"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u015b\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21\u016b\n\21\3\21\5\21\u016e\n\21\3\21\3"+
		"\21\3\21\3\21\6\21\u0174\n\21\r\21\16\21\u0175\3\21\3\21\3\21\5\21\u017b"+
		"\n\21\3\21\3\21\3\21\3\21\6\21\u0181\n\21\r\21\16\21\u0182\3\21\3\21\3"+
		"\21\5\21\u0188\n\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u0194\n\22\3\22\3\22\3\22\3\22\3\22\6\22\u019b\n\22\r\22\16\22\u019c"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01a9\n\22\3\22"+
		"\3\22\3\22\3\22\3\22\6\22\u01b0\n\22\r\22\16\22\u01b1\3\22\3\22\3\22\5"+
		"\22\u01b7\n\22\3\22\3\22\3\22\3\22\6\22\u01bd\n\22\r\22\16\22\u01be\3"+
		"\22\3\22\3\22\5\22\u01c4\n\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u01cc"+
		"\n\23\f\23\16\23\u01cf\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\5\24\u01db\n\24\3\24\2\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&\2\2\2\u0225\2(\3\2\2\2\4\63\3\2\2\2\6\67\3\2\2\2\bM\3\2\2"+
		"\2\nO\3\2\2\2\fZ\3\2\2\2\16`\3\2\2\2\20b\3\2\2\2\22\u0086\3\2\2\2\24\u00af"+
		"\3\2\2\2\26\u00da\3\2\2\2\30\u00ef\3\2\2\2\32\u00f9\3\2\2\2\34\u010f\3"+
		"\2\2\2\36\u0121\3\2\2\2 \u0123\3\2\2\2\"\u018b\3\2\2\2$\u01c7\3\2\2\2"+
		"&\u01da\3\2\2\2(*\7\3\2\2)+\5\4\3\2*)\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\5"+
		"\n\6\2-,\3\2\2\2-.\3\2\2\2./\3\2\2\2/\60\7\4\2\2\60\61\b\2\1\2\61\3\3"+
		"\2\2\2\62\64\5\6\4\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3"+
		"\2\2\2\66\5\3\2\2\2\678\5\b\5\289\7\37\2\29?\b\4\1\2:;\7\33\2\2;<\7\37"+
		"\2\2<>\b\4\1\2=:\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@C\3\2\2\2A?\3\2"+
		"\2\2BD\7\23\2\2CB\3\2\2\2CD\3\2\2\2D\7\3\2\2\2EF\7\5\2\2FN\b\5\1\2GH\7"+
		"\6\2\2HN\b\5\1\2IJ\7\7\2\2JN\b\5\1\2KL\7\b\2\2LN\b\5\1\2ME\3\2\2\2MG\3"+
		"\2\2\2MI\3\2\2\2MK\3\2\2\2N\t\3\2\2\2OQ\b\6\1\2PR\5\f\7\2QP\3\2\2\2RS"+
		"\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\13\3\2\2\2U[\5\30\r\2V[\5\32\16\2W[\5\34"+
		"\17\2X[\5\36\20\2Y[\5\16\b\2ZU\3\2\2\2ZV\3\2\2\2ZW\3\2\2\2ZX\3\2\2\2Z"+
		"Y\3\2\2\2[\r\3\2\2\2\\a\5\20\t\2]a\5\22\n\2^a\5\24\13\2_a\5\26\f\2`\\"+
		"\3\2\2\2`]\3\2\2\2`^\3\2\2\2`_\3\2\2\2a\17\3\2\2\2bc\7\t\2\2cd\7\21\2"+
		"\2dl\b\t\1\2ef\7\37\2\2fm\b\t\1\2gh\7!\2\2hm\b\t\1\2ij\7 \2\2jm\b\t\1"+
		"\2km\5$\23\2le\3\2\2\2lg\3\2\2\2li\3\2\2\2lk\3\2\2\2mn\3\2\2\2no\b\t\1"+
		"\2op\7\32\2\2px\b\t\1\2qr\7\37\2\2ry\b\t\1\2st\7!\2\2ty\b\t\1\2uv\7 \2"+
		"\2vy\b\t\1\2wy\5$\23\2xq\3\2\2\2xs\3\2\2\2xu\3\2\2\2xw\3\2\2\2yz\3\2\2"+
		"\2z{\b\t\1\2{|\7\22\2\2|}\7\30\2\2}\177\b\t\1\2~\u0080\5\f\7\2\177~\3"+
		"\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\u0084\7\31\2\2\u0084\u0085\b\t\1\2\u0085\21\3\2\2"+
		"\2\u0086\u0087\7\n\2\2\u0087\u0088\7\30\2\2\u0088\u008a\b\n\1\2\u0089"+
		"\u008b\5\f\7\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2"+
		"\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\7\31\2\2\u008f"+
		"\u0090\b\n\1\2\u0090\u0091\7\t\2\2\u0091\u0092\7\21\2\2\u0092\u009a\b"+
		"\n\1\2\u0093\u0094\7\37\2\2\u0094\u009b\b\n\1\2\u0095\u0096\7!\2\2\u0096"+
		"\u009b\b\n\1\2\u0097\u0098\7 \2\2\u0098\u009b\b\n\1\2\u0099\u009b\5$\23"+
		"\2\u009a\u0093\3\2\2\2\u009a\u0095\3\2\2\2\u009a\u0097\3\2\2\2\u009a\u0099"+
		"\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\b\n\1\2\u009d\u009e\7\32\2\2"+
		"\u009e\u00a6\b\n\1\2\u009f\u00a0\7\37\2\2\u00a0\u00a7\b\n\1\2\u00a1\u00a2"+
		"\7!\2\2\u00a2\u00a7\b\n\1\2\u00a3\u00a4\7 \2\2\u00a4\u00a7\b\n\1\2\u00a5"+
		"\u00a7\5$\23\2\u00a6\u009f\3\2\2\2\u00a6\u00a1\3\2\2\2\u00a6\u00a3\3\2"+
		"\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\b\n\1\2\u00a9"+
		"\u00ab\7\22\2\2\u00aa\u00ac\7\23\2\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3"+
		"\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b\n\1\2\u00ae\23\3\2\2\2\u00af"+
		"\u00b0\7\13\2\2\u00b0\u00b1\7\21\2\2\u00b1\u00b2\5\34\17\2\u00b2\u00b3"+
		"\b\13\1\2\u00b3\u00b4\7\23\2\2\u00b4\u00bc\b\13\1\2\u00b5\u00b6\7\37\2"+
		"\2\u00b6\u00bd\b\13\1\2\u00b7\u00b8\7!\2\2\u00b8\u00bd\b\13\1\2\u00b9"+
		"\u00ba\7 \2\2\u00ba\u00bd\b\13\1\2\u00bb\u00bd\5$\23\2\u00bc\u00b5\3\2"+
		"\2\2\u00bc\u00b7\3\2\2\2\u00bc\u00b9\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00bf\b\13\1\2\u00bf\u00c0\7\32\2\2\u00c0\u00c8\b"+
		"\13\1\2\u00c1\u00c2\7\37\2\2\u00c2\u00c9\b\13\1\2\u00c3\u00c4\7!\2\2\u00c4"+
		"\u00c9\b\13\1\2\u00c5\u00c6\7 \2\2\u00c6\u00c9\b\13\1\2\u00c7\u00c9\5"+
		"$\23\2\u00c8\u00c1\3\2\2\2\u00c8\u00c3\3\2\2\2\u00c8\u00c5\3\2\2\2\u00c8"+
		"\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\b\13\1\2\u00cb\u00cc\7"+
		"\23\2\2\u00cc\u00cd\b\13\1\2\u00cd\u00ce\5\34\17\2\u00ce\u00cf\b\13\1"+
		"\2\u00cf\u00d0\7\22\2\2\u00d0\u00d1\7\30\2\2\u00d1\u00d3\b\13\1\2\u00d2"+
		"\u00d4\5\f\7\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7\31\2\2\u00d8"+
		"\u00d9\b\13\1\2\u00d9\25\3\2\2\2\u00da\u00db\7\t\2\2\u00db\u00dc\7\21"+
		"\2\2\u00dc\u00e2\b\f\1\2\u00dd\u00de\7\37\2\2\u00de\u00e3\b\f\1\2\u00df"+
		"\u00e0\7\34\2\2\u00e0\u00e3\b\f\1\2\u00e1\u00e3\5$\23\2\u00e2\u00dd\3"+
		"\2\2\2\u00e2\u00df\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\u00e5\7\22\2\2\u00e5\u00e6\7\30\2\2\u00e6\u00e8\b\f\1\2\u00e7\u00e9\5"+
		"\f\7\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\7\31\2\2\u00ed\u00ee\b"+
		"\f\1\2\u00ee\27\3\2\2\2\u00ef\u00f0\7\f\2\2\u00f0\u00f1\7\21\2\2\u00f1"+
		"\u00f2\7\37\2\2\u00f2\u00f3\b\r\1\2\u00f3\u00f5\7\22\2\2\u00f4\u00f6\7"+
		"\23\2\2\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u00f8\b\r\1\2\u00f8\31\3\2\2\2\u00f9\u00fa\7\r\2\2\u00fa\u00fb\7\21\2"+
		"\2\u00fb\u0106\b\16\1\2\u00fc\u00fd\7\37\2\2\u00fd\u0107\b\16\1\2\u00fe"+
		"\u0107\7!\2\2\u00ff\u0107\7 \2\2\u0100\u0107\7\"\2\2\u0101\u0107\5$\23"+
		"\2\u0102\u0103\b\16\1\2\u0103\u0104\5\34\17\2\u0104\u0105\b\16\1\2\u0105"+
		"\u0107\3\2\2\2\u0106\u00fc\3\2\2\2\u0106\u00fe\3\2\2\2\u0106\u00ff\3\2"+
		"\2\2\u0106\u0100\3\2\2\2\u0106\u0101\3\2\2\2\u0106\u0102\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0109\b\16\1\2\u0109\u010b\7\22\2\2\u010a\u010c\7"+
		"\23\2\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010e\b\16\1\2\u010e\33\3\2\2\2\u010f\u0110\7\37\2\2\u0110\u0118\b\17"+
		"\1\2\u0111\u0112\7\25\2\2\u0112\u0113\b\17\1\2\u0113\u0119\5$\23\2\u0114"+
		"\u0115\7\26\2\2\u0115\u0119\b\17\1\2\u0116\u0117\7\27\2\2\u0117\u0119"+
		"\b\17\1\2\u0118\u0111\3\2\2\2\u0118\u0114\3\2\2\2\u0118\u0116\3\2\2\2"+
		"\u0119\u011b\3\2\2\2\u011a\u011c\7\23\2\2\u011b\u011a\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\b\17\1\2\u011e\35\3\2\2\2\u011f"+
		"\u0122\5 \21\2\u0120\u0122\5\"\22\2\u0121\u011f\3\2\2\2\u0121\u0120\3"+
		"\2\2\2\u0122\37\3\2\2\2\u0123\u0124\7\16\2\2\u0124\u0125\7\21\2\2\u0125"+
		"\u012f\b\21\1\2\u0126\u0127\7\37\2\2\u0127\u0130\b\21\1\2\u0128\u0129"+
		"\7!\2\2\u0129\u0130\b\21\1\2\u012a\u012b\7 \2\2\u012b\u0130\b\21\1\2\u012c"+
		"\u012d\7\34\2\2\u012d\u0130\b\21\1\2\u012e\u0130\5$\23\2\u012f\u0126\3"+
		"\2\2\2\u012f\u0128\3\2\2\2\u012f\u012a\3\2\2\2\u012f\u012c\3\2\2\2\u012f"+
		"\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\b\21\1\2\u0132\u0133\7"+
		"\32\2\2\u0133\u013f\b\21\1\2\u0134\u0135\7\37\2\2\u0135\u0140\b\21\1\2"+
		"\u0136\u0137\7!\2\2\u0137\u0140\b\21\1\2\u0138\u0139\7 \2\2\u0139\u0140"+
		"\b\21\1\2\u013a\u013b\7\34\2\2\u013b\u0140\b\21\1\2\u013c\u0140\7\35\2"+
		"\2\u013d\u0140\7\36\2\2\u013e\u0140\5$\23\2\u013f\u0134\3\2\2\2\u013f"+
		"\u0136\3\2\2\2\u013f\u0138\3\2\2\2\u013f\u013a\3\2\2\2\u013f\u013c\3\2"+
		"\2\2\u013f\u013d\3\2\2\2\u013f\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"\u0142\b\21\1\2\u0142\u0143\7\22\2\2\u0143\u0144\7\30\2\2\u0144\u0146"+
		"\b\21\1\2\u0145\u0147\5\f\7\2\u0146\u0145\3\2\2\2\u0147\u0148\3\2\2\2"+
		"\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b"+
		"\7\31\2\2\u014b\u017a\b\21\1\2\u014c\u014d\7\17\2\2\u014d\u014e\7\21\2"+
		"\2\u014e\u016d\b\21\1\2\u014f\u0150\7\37\2\2\u0150\u016e\b\21\1\2\u0151"+
		"\u0152\7\37\2\2\u0152\u015b\b\21\1\2\u0153\u0154\7!\2\2\u0154\u015b\b"+
		"\21\1\2\u0155\u0156\7 \2\2\u0156\u015b\b\21\1\2\u0157\u0158\7\34\2\2\u0158"+
		"\u015b\b\21\1\2\u0159\u015b\5$\23\2\u015a\u0151\3\2\2\2\u015a\u0153\3"+
		"\2\2\2\u015a\u0155\3\2\2\2\u015a\u0157\3\2\2\2\u015a\u0159\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015c\u015d\b\21\1\2\u015d\u015e\7\32\2\2\u015e\u016a\b"+
		"\21\1\2\u015f\u0160\7\37\2\2\u0160\u016b\b\21\1\2\u0161\u0162\7!\2\2\u0162"+
		"\u016b\b\21\1\2\u0163\u0164\7 \2\2\u0164\u016b\b\21\1\2\u0165\u0166\7"+
		"\34\2\2\u0166\u016b\b\21\1\2\u0167\u016b\7\35\2\2\u0168\u016b\7\36\2\2"+
		"\u0169\u016b\5$\23\2\u016a\u015f\3\2\2\2\u016a\u0161\3\2\2\2\u016a\u0163"+
		"\3\2\2\2\u016a\u0165\3\2\2\2\u016a\u0167\3\2\2\2\u016a\u0168\3\2\2\2\u016a"+
		"\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016e\b\21\1\2\u016d\u014f\3"+
		"\2\2\2\u016d\u015a\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\7\22\2\2\u0170"+
		"\u0171\7\30\2\2\u0171\u0173\b\21\1\2\u0172\u0174\5\f\7\2\u0173\u0172\3"+
		"\2\2\2\u0174\u0175\3\2\2\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176"+
		"\u0177\3\2\2\2\u0177\u0178\7\31\2\2\u0178\u0179\b\21\1\2\u0179\u017b\3"+
		"\2\2\2\u017a\u014c\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u0187\3\2\2\2\u017c"+
		"\u017d\7\20\2\2\u017d\u017e\7\30\2\2\u017e\u0180\b\21\1\2\u017f\u0181"+
		"\5\f\7\2\u0180\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0180\3\2\2\2\u0182"+
		"\u0183\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0185\7\31\2\2\u0185\u0186\b"+
		"\21\1\2\u0186\u0188\3\2\2\2\u0187\u017c\3\2\2\2\u0187\u0188\3\2\2\2\u0188"+
		"\u0189\3\2\2\2\u0189\u018a\b\21\1\2\u018a!\3\2\2\2\u018b\u018c\7\16\2"+
		"\2\u018c\u018d\7\21\2\2\u018d\u0193\b\22\1\2\u018e\u018f\7\37\2\2\u018f"+
		"\u0194\b\22\1\2\u0190\u0191\7\34\2\2\u0191\u0194\b\22\1\2\u0192\u0194"+
		"\5$\23\2\u0193\u018e\3\2\2\2\u0193\u0190\3\2\2\2\u0193\u0192\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195\u0196\b\22\1\2\u0196\u0197\7\22\2\2\u0197\u0198\7"+
		"\30\2\2\u0198\u019a\b\22\1\2\u0199\u019b\5\f\7\2\u019a\u0199\3\2\2\2\u019b"+
		"\u019c\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2"+
		"\2\2\u019e\u019f\7\31\2\2\u019f\u01b6\b\22\1\2\u01a0\u01a1\7\17\2\2\u01a1"+
		"\u01a2\7\21\2\2\u01a2\u01a8\b\22\1\2\u01a3\u01a4\7\37\2\2\u01a4\u01a9"+
		"\b\22\1\2\u01a5\u01a6\7\34\2\2\u01a6\u01a9\b\22\1\2\u01a7\u01a9\5$\23"+
		"\2\u01a8\u01a3\3\2\2\2\u01a8\u01a5\3\2\2\2\u01a8\u01a7\3\2\2\2\u01a9\u01aa"+
		"\3\2\2\2\u01aa\u01ab\b\22\1\2\u01ab\u01ac\7\22\2\2\u01ac\u01ad\7\30\2"+
		"\2\u01ad\u01af\b\22\1\2\u01ae\u01b0\5\f\7\2\u01af\u01ae\3\2\2\2\u01b0"+
		"\u01b1\3\2\2\2\u01b1\u01af\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\3\2"+
		"\2\2\u01b3\u01b4\7\31\2\2\u01b4\u01b5\b\22\1\2\u01b5\u01b7\3\2\2\2\u01b6"+
		"\u01a0\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01c3\3\2\2\2\u01b8\u01b9\7\20"+
		"\2\2\u01b9\u01ba\7\30\2\2\u01ba\u01bc\b\22\1\2\u01bb\u01bd\5\f\7\2\u01bc"+
		"\u01bb\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01bc\3\2\2\2\u01be\u01bf\3\2"+
		"\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1\7\31\2\2\u01c1\u01c2\b\22\1\2\u01c2"+
		"\u01c4\3\2\2\2\u01c3\u01b8\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\3\2"+
		"\2\2\u01c5\u01c6\b\22\1\2\u01c6#\3\2\2\2\u01c7\u01cd\5&\24\2\u01c8\u01c9"+
		"\7\24\2\2\u01c9\u01ca\b\23\1\2\u01ca\u01cc\5&\24\2\u01cb\u01c8\3\2\2\2"+
		"\u01cc\u01cf\3\2\2\2\u01cd\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce%\3"+
		"\2\2\2\u01cf\u01cd\3\2\2\2\u01d0\u01d1\7\37\2\2\u01d1\u01db\b\24\1\2\u01d2"+
		"\u01d3\7!\2\2\u01d3\u01db\b\24\1\2\u01d4\u01d5\7\"\2\2\u01d5\u01db\b\24"+
		"\1\2\u01d6\u01d7\7 \2\2\u01d7\u01db\b\24\1\2\u01d8\u01d9\7\34\2\2\u01d9"+
		"\u01db\b\24\1\2\u01da\u01d0\3\2\2\2\u01da\u01d2\3\2\2\2\u01da\u01d4\3"+
		"\2\2\2\u01da\u01d6\3\2\2\2\u01da\u01d8\3\2\2\2\u01db\'\3\2\2\2\60*-\65"+
		"?CMSZ`lx\u0081\u008c\u009a\u00a6\u00ab\u00bc\u00c8\u00d5\u00e2\u00ea\u00f5"+
		"\u0106\u010b\u0118\u011b\u0121\u012f\u013f\u0148\u015a\u016a\u016d\u0175"+
		"\u017a\u0182\u0187\u0193\u019c\u01a8\u01b1\u01b6\u01be\u01c3\u01cd\u01da";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}