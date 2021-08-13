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
		ICR=19, DCR=20, ACH=21, FCH=22, OPREL=23, VIR=24, ID=25, INT=26, DOUBLE=27, 
		TEXT=28, CM=29, WS=30;
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
			null, "'start:'", "'end'", "'double'", "'text'", "'int'", "'while'", 
			"'do'", "'for'", "'read'", "'write'", "'if'", "'else if'", "'else'", 
			"'('", "')'", "';'", null, "'='", "'++'", "'--'", "'{'", "'}'", null, 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "AP", "FP", "SC", "OP", "ATTR", "ICR", "DCR", "ACH", "FCH", 
			"OPREL", "VIR", "ID", "INT", "DOUBLE", "TEXT", "CM", "WS"
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
			setState(32);
			match(T__0);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
				{
				setState(33);
				decl();
				}
			}

			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0)) {
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
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
			setState(67);
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

					      					_type = RawrVariable.TEXT;
					      				
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				match(T__4);

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
				   					
			setState(71); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(70);
				cmd();
				}
				}
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
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
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				cmd_read();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				cmd_write();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				cmd_attrib();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				cmd_conditional();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(79);
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
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				cmdloop1();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				cmdloop2();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
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
			setState(87);
			match(T__5);
			setState(88);
			match(AP);

						  				_exprContent = "";
						  			
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(90);
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
				setState(92);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(94);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(96);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(100);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();					
						  				_exprContent = "";
									
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(102);
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
				setState(104);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(106);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(108);
				expr();
				}
				break;
			}

										_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(112);
			match(FP);
			setState(113);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(116); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(115);
				cmd();
				}
				}
				setState(118); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(120);
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
			setState(123);
			match(T__6);
			setState(124);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(126);
				cmd();
				}
				}
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(131);
			match(FCH);

										loopList = new ArrayList<AbstractCommand>();
										loopList = stack.pop();
									
			setState(133);
			match(T__5);
			setState(134);
			match(AP);

						  				_exprContent = "";
						  			
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
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
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(140);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(142);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(146);
			match(OPREL);

								 		_exprRepetition += _input.LT(-1).getText();
								 		_exprContent = "";
								 	
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(148);
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
				setState(150);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(152);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(154);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(158);
			match(FP);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(159);
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
		public TerminalNode ICR() { return getToken(RawrLangParser.ICR, 0); }
		public TerminalNode DCR() { return getToken(RawrLangParser.DCR, 0); }
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
			setState(164);
			match(T__7);
			setState(165);
			match(AP);
			setState(166);
			cmd_attrib();

										_exprRepetition = _exprTemp;
									
			setState(168);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(170);
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
				setState(172);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(174);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(176);
				expr();
				}
				break;
			}
			 
						  				_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(180);
			match(OPREL);

										_exprRepetition += _input.LT(-1).getText();
										_exprContent = "";
									
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(182);
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
				setState(184);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
										
				}
				break;
			case 3:
				{
				setState(186);
				match(INT);

											variableValidateType(_exprId, 2);
										
				}
				break;
			case 4:
				{
				setState(188);
				expr();
				}
				break;
			}

										_exprRepetition += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(192);
			match(SC);

										_exprRepetition += _input.LT(-1).getText();
									
			setState(203);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(194);
				cmd_attrib();
				}
				break;
			case 2:
				{
				setState(195);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
											}
											_exprRepetition += _input.LT(-1).getText();
										
				setState(197);
				match(ICR);

											_exprRepetition += _input.LT(-1).getText();
										
				}
				break;
			case 3:
				{
				setState(199);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
											}
											_exprRepetition += _input.LT(-1).getText();
										
				setState(201);
				match(DCR);

											_exprRepetition += _input.LT(-1).getText();
										
				}
				break;
			}

										_exprRepetition += _exprTemp;
									
			setState(206);
			match(FP);
			setState(207);
			match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
			setState(210); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(209);
				cmd();
				}
				}
				setState(212); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(214);
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
			setState(217);
			match(T__8);
			setState(218);
			match(AP);
			setState(219);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_readId = _input.LT(-1).getText();
									
			setState(221);
			match(FP);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(222);
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
		public TerminalNode ICR() { return getToken(RawrLangParser.ICR, 0); }
		public TerminalNode DCR() { return getToken(RawrLangParser.DCR, 0); }
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
			setState(227);
			match(T__9);
			setState(228);
			match(AP);

						  				_exprContent = "";
						  			
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(230);
				match(ID);

											if (!variableValidateRead(_input.LT(-1).getText())){
												variableValidate(_input.LT(-1).getText());
												variableValidateValue(_input.LT(-1).getText());
											}
										
				}
				break;
			case 2:
				{
				setState(232);
				match(DOUBLE);
				}
				break;
			case 3:
				{
				setState(233);
				match(INT);
				}
				break;
			case 4:
				{
				setState(234);
				match(TEXT);
				}
				break;
			case 5:
				{
				setState(235);
				expr();
				}
				break;
			case 6:
				{
				setState(236);
				match(ID);
				setState(237);
				match(ICR);
					
											if (!variableValidateRead(_exprId)){
												variableValidateValue(_exprId);
												variableValidateType(_exprId, 0);
											}
											_exprContent = _exprId + " + 1";
										
				}
				break;
			case 7:
				{
				setState(239);
				match(ID);
				setState(240);
				match(DCR);
					
											if (!variableValidateRead(_exprId)){
												variableValidateValue(_exprId);
												variableValidateType(_exprId, 0);
											}
											_exprContent = _exprId + " - 1";
										
				}
				break;
			}
				
						  				_writeId = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									
			setState(245);
			match(FP);
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SC) {
				{
				setState(246);
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
			setState(251);
			match(ID);

										variableValidate(_input.LT(-1).getText());
										_exprId = _input.LT(-1).getText();
									
			setState(260);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTR:
				{
				setState(253);
				match(ATTR);

											_exprContent = "";
										
				setState(255);
				expr();
				}
				break;
			case ICR:
				{
				setState(256);
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
				setState(258);
				match(DCR);
					
											if (!variableValidateRead(_exprId)){
												variableValidateValue(_exprId);
												variableValidateType(_exprId, 0);
											}
											_exprContent = _exprId + " - 1";
										
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(262);
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
			setState(267);
			match(T__10);
			setState(268);
			match(AP);

						  				_exprContent = "";
						  			
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(270);
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
				setState(272);
				match(DOUBLE);

							  				variableValidateType(_exprId, 0);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(274);
				match(INT);

							  				variableValidateType(_exprId, 2);
							  				
							  			
				}
				break;
			case 4:
				{
				setState(276);
				expr();
				}
				break;
			}
			 
						  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(280);
			match(OPREL);
			 
						  				
						  				_exprDecision += _input.LT(-1).getText();
						  				_exprContent = "";
						  			
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(282);
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
				setState(284);
				match(DOUBLE);

							  				variableValidateType(_exprId, 0);
							  				
							  			
				}
				break;
			case 3:
				{
				setState(286);
				match(INT);

							  				variableValidateType(_exprId, 2);
							  				
							  			
				}
				break;
			case 4:
				{
				setState(288);
				expr();
				}
				break;
			}
			 
						  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
						  			
			setState(292);
			match(FP);
			setState(293);
			match(ACH);
			 
						  				curThread = new ArrayList<AbstractCommand>();
							  			stack.push(curThread);
							   		
			setState(296); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(295);
				cmd();
				}
				}
				setState(298); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(300);
			match(FCH);
			 
						  				listTrue = stack.pop();
						  			
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(302);
				match(T__11);
				setState(303);
				match(AP);

							  				_exprContent = "";
							  			
				setState(329);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(305);
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
					setState(314);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						setState(307);
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
						setState(309);
						match(DOUBLE);

									  					variableValidateType(_exprId, 0);
									  				
									  				
						}
						break;
					case 3:
						{
						setState(311);
						match(INT);

									  					variableValidateType(_exprId, 2);
									  				
									  				
						}
						break;
					case 4:
						{
						setState(313);
						expr();
						}
						break;
					}
					 
									  				_exprDecision = _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									  			
					setState(317);
					match(OPREL);
					 
									  				
									  				_exprDecision += _input.LT(-1).getText();
									  				_exprContent = "";
									  			
					setState(326);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						setState(319);
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
						setState(321);
						match(DOUBLE);

									  					variableValidateType(_exprId, 0);
									  				
									  				
						}
						break;
					case 3:
						{
						setState(323);
						match(INT);

									  					variableValidateType(_exprId, 2);
									  				
									  				
						}
						break;
					case 4:
						{
						setState(325);
						expr();
						}
						break;
					}
					 
									  				_exprDecision += _exprContent == "" ? _input.LT(-1).getText() : _exprContent;
									  			
					}
					break;
				}
				setState(331);
				match(FP);
				setState(332);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
									 	
				setState(335); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(334);
					cmd();
					}
					}
					setState(337); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(339);
				match(FCH);
				 
							   	 	     	listTrue = stack.pop();
						   	 	    	
				}
			}

			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(344);
				match(T__12);
				setState(345);
				match(ACH);

							   	 	   		curThread = new ArrayList<AbstractCommand>();
									  		stack.push(curThread);
									 	
				setState(348); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(347);
					cmd();
					}
					}
					setState(350); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(352);
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
			setState(359);
			term();
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(360);
				match(OP);

					   						_exprContent += _input.LT(-1).getText();
					   					
				setState(362);
				term();
				}
				}
				setState(367);
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
			setState(376);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(368);
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
				setState(370);
				match(DOUBLE);

											variableValidateType(_exprId, 0);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(372);
				match(TEXT);
					
											variableValidateType(_exprId, 1);
											_exprContent += _input.LT(-1).getText();
										
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(374);
				match(INT);

											variableValidateType(_exprId, 2);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u017d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\5"+
		"\2%\n\2\3\2\5\2(\n\2\3\2\3\2\3\2\3\3\6\3.\n\3\r\3\16\3/\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\48\n\4\f\4\16\4;\13\4\3\4\5\4>\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5F\n\5\3\6\3\6\6\6J\n\6\r\6\16\6K\3\7\3\7\3\7\3\7\3\7\5\7S\n\7\3"+
		"\b\3\b\3\b\5\bX\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\td\n\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tp\n\t\3\t\3\t\3\t\3\t\3\t\6"+
		"\tw\n\t\r\t\16\tx\3\t\3\t\3\t\3\n\3\n\3\n\3\n\6\n\u0082\n\n\r\n\16\n\u0083"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0092\n\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009e\n\n\3\n\3\n\3\n\5\n\u00a3\n"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00b4\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u00c0\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00ce\n\13\3\13\3\13\3\13\3\13\3\13\6\13\u00d5\n\13\r\13\16"+
		"\13\u00d6\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e2\n\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00f5"+
		"\n\r\3\r\3\r\3\r\5\r\u00fa\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\5\16\u0107\n\16\3\16\5\16\u010a\n\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0118\n\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0124\n\17\3\17\3\17\3\17\3\17"+
		"\3\17\6\17\u012b\n\17\r\17\16\17\u012c\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u013d\n\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0149\n\17\3\17\5\17\u014c\n"+
		"\17\3\17\3\17\3\17\3\17\6\17\u0152\n\17\r\17\16\17\u0153\3\17\3\17\3\17"+
		"\5\17\u0159\n\17\3\17\3\17\3\17\3\17\6\17\u015f\n\17\r\17\16\17\u0160"+
		"\3\17\3\17\3\17\5\17\u0166\n\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u016e"+
		"\n\20\f\20\16\20\u0171\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5"+
		"\21\u017b\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2"+
		"\2\u01b3\2\"\3\2\2\2\4-\3\2\2\2\6\61\3\2\2\2\bE\3\2\2\2\nG\3\2\2\2\fR"+
		"\3\2\2\2\16W\3\2\2\2\20Y\3\2\2\2\22}\3\2\2\2\24\u00a6\3\2\2\2\26\u00db"+
		"\3\2\2\2\30\u00e5\3\2\2\2\32\u00fd\3\2\2\2\34\u010d\3\2\2\2\36\u0169\3"+
		"\2\2\2 \u017a\3\2\2\2\"$\7\3\2\2#%\5\4\3\2$#\3\2\2\2$%\3\2\2\2%\'\3\2"+
		"\2\2&(\5\n\6\2\'&\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\7\4\2\2*+\b\2\1\2+\3"+
		"\3\2\2\2,.\5\6\4\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\5\3\2"+
		"\2\2\61\62\5\b\5\2\62\63\7\33\2\2\639\b\4\1\2\64\65\7\32\2\2\65\66\7\33"+
		"\2\2\668\b\4\1\2\67\64\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:=\3\2\2"+
		"\2;9\3\2\2\2<>\7\22\2\2=<\3\2\2\2=>\3\2\2\2>\7\3\2\2\2?@\7\5\2\2@F\b\5"+
		"\1\2AB\7\6\2\2BF\b\5\1\2CD\7\7\2\2DF\b\5\1\2E?\3\2\2\2EA\3\2\2\2EC\3\2"+
		"\2\2F\t\3\2\2\2GI\b\6\1\2HJ\5\f\7\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3"+
		"\2\2\2L\13\3\2\2\2MS\5\26\f\2NS\5\30\r\2OS\5\32\16\2PS\5\34\17\2QS\5\16"+
		"\b\2RM\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2S\r\3\2\2\2TX\5"+
		"\20\t\2UX\5\22\n\2VX\5\24\13\2WT\3\2\2\2WU\3\2\2\2WV\3\2\2\2X\17\3\2\2"+
		"\2YZ\7\b\2\2Z[\7\20\2\2[c\b\t\1\2\\]\7\33\2\2]d\b\t\1\2^_\7\35\2\2_d\b"+
		"\t\1\2`a\7\34\2\2ad\b\t\1\2bd\5\36\20\2c\\\3\2\2\2c^\3\2\2\2c`\3\2\2\2"+
		"cb\3\2\2\2de\3\2\2\2ef\b\t\1\2fg\7\31\2\2go\b\t\1\2hi\7\33\2\2ip\b\t\1"+
		"\2jk\7\35\2\2kp\b\t\1\2lm\7\34\2\2mp\b\t\1\2np\5\36\20\2oh\3\2\2\2oj\3"+
		"\2\2\2ol\3\2\2\2on\3\2\2\2pq\3\2\2\2qr\b\t\1\2rs\7\21\2\2st\7\27\2\2t"+
		"v\b\t\1\2uw\5\f\7\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2yz\3\2\2\2"+
		"z{\7\30\2\2{|\b\t\1\2|\21\3\2\2\2}~\7\t\2\2~\177\7\27\2\2\177\u0081\b"+
		"\n\1\2\u0080\u0082\5\f\7\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7\30"+
		"\2\2\u0086\u0087\b\n\1\2\u0087\u0088\7\b\2\2\u0088\u0089\7\20\2\2\u0089"+
		"\u0091\b\n\1\2\u008a\u008b\7\33\2\2\u008b\u0092\b\n\1\2\u008c\u008d\7"+
		"\35\2\2\u008d\u0092\b\n\1\2\u008e\u008f\7\34\2\2\u008f\u0092\b\n\1\2\u0090"+
		"\u0092\5\36\20\2\u0091\u008a\3\2\2\2\u0091\u008c\3\2\2\2\u0091\u008e\3"+
		"\2\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\b\n\1\2\u0094"+
		"\u0095\7\31\2\2\u0095\u009d\b\n\1\2\u0096\u0097\7\33\2\2\u0097\u009e\b"+
		"\n\1\2\u0098\u0099\7\35\2\2\u0099\u009e\b\n\1\2\u009a\u009b\7\34\2\2\u009b"+
		"\u009e\b\n\1\2\u009c\u009e\5\36\20\2\u009d\u0096\3\2\2\2\u009d\u0098\3"+
		"\2\2\2\u009d\u009a\3\2\2\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\b\n\1\2\u00a0\u00a2\7\21\2\2\u00a1\u00a3\7\22\2\2\u00a2\u00a1\3"+
		"\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\b\n\1\2\u00a5"+
		"\23\3\2\2\2\u00a6\u00a7\7\n\2\2\u00a7\u00a8\7\20\2\2\u00a8\u00a9\5\32"+
		"\16\2\u00a9\u00aa\b\13\1\2\u00aa\u00ab\7\22\2\2\u00ab\u00b3\b\13\1\2\u00ac"+
		"\u00ad\7\33\2\2\u00ad\u00b4\b\13\1\2\u00ae\u00af\7\35\2\2\u00af\u00b4"+
		"\b\13\1\2\u00b0\u00b1\7\34\2\2\u00b1\u00b4\b\13\1\2\u00b2\u00b4\5\36\20"+
		"\2\u00b3\u00ac\3\2\2\2\u00b3\u00ae\3\2\2\2\u00b3\u00b0\3\2\2\2\u00b3\u00b2"+
		"\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\b\13\1\2\u00b6\u00b7\7\31\2\2"+
		"\u00b7\u00bf\b\13\1\2\u00b8\u00b9\7\33\2\2\u00b9\u00c0\b\13\1\2\u00ba"+
		"\u00bb\7\35\2\2\u00bb\u00c0\b\13\1\2\u00bc\u00bd\7\34\2\2\u00bd\u00c0"+
		"\b\13\1\2\u00be\u00c0\5\36\20\2\u00bf\u00b8\3\2\2\2\u00bf\u00ba\3\2\2"+
		"\2\u00bf\u00bc\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2"+
		"\b\13\1\2\u00c2\u00c3\7\22\2\2\u00c3\u00cd\b\13\1\2\u00c4\u00ce\5\32\16"+
		"\2\u00c5\u00c6\7\33\2\2\u00c6\u00c7\b\13\1\2\u00c7\u00c8\7\25\2\2\u00c8"+
		"\u00ce\b\13\1\2\u00c9\u00ca\7\33\2\2\u00ca\u00cb\b\13\1\2\u00cb\u00cc"+
		"\7\26\2\2\u00cc\u00ce\b\13\1\2\u00cd\u00c4\3\2\2\2\u00cd\u00c5\3\2\2\2"+
		"\u00cd\u00c9\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\b\13\1\2\u00d0\u00d1"+
		"\7\21\2\2\u00d1\u00d2\7\27\2\2\u00d2\u00d4\b\13\1\2\u00d3\u00d5\5\f\7"+
		"\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7"+
		"\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\7\30\2\2\u00d9\u00da\b\13\1\2"+
		"\u00da\25\3\2\2\2\u00db\u00dc\7\13\2\2\u00dc\u00dd\7\20\2\2\u00dd\u00de"+
		"\7\33\2\2\u00de\u00df\b\f\1\2\u00df\u00e1\7\21\2\2\u00e0\u00e2\7\22\2"+
		"\2\u00e1\u00e0\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4"+
		"\b\f\1\2\u00e4\27\3\2\2\2\u00e5\u00e6\7\f\2\2\u00e6\u00e7\7\20\2\2\u00e7"+
		"\u00f4\b\r\1\2\u00e8\u00e9\7\33\2\2\u00e9\u00f5\b\r\1\2\u00ea\u00f5\7"+
		"\35\2\2\u00eb\u00f5\7\34\2\2\u00ec\u00f5\7\36\2\2\u00ed\u00f5\5\36\20"+
		"\2\u00ee\u00ef\7\33\2\2\u00ef\u00f0\7\25\2\2\u00f0\u00f5\b\r\1\2\u00f1"+
		"\u00f2\7\33\2\2\u00f2\u00f3\7\26\2\2\u00f3\u00f5\b\r\1\2\u00f4\u00e8\3"+
		"\2\2\2\u00f4\u00ea\3\2\2\2\u00f4\u00eb\3\2\2\2\u00f4\u00ec\3\2\2\2\u00f4"+
		"\u00ed\3\2\2\2\u00f4\u00ee\3\2\2\2\u00f4\u00f1\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f7\b\r\1\2\u00f7\u00f9\7\21\2\2\u00f8\u00fa\7\22\2\2\u00f9"+
		"\u00f8\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\b\r"+
		"\1\2\u00fc\31\3\2\2\2\u00fd\u00fe\7\33\2\2\u00fe\u0106\b\16\1\2\u00ff"+
		"\u0100\7\24\2\2\u0100\u0101\b\16\1\2\u0101\u0107\5\36\20\2\u0102\u0103"+
		"\7\25\2\2\u0103\u0107\b\16\1\2\u0104\u0105\7\26\2\2\u0105\u0107\b\16\1"+
		"\2\u0106\u00ff\3\2\2\2\u0106\u0102\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0109"+
		"\3\2\2\2\u0108\u010a\7\22\2\2\u0109\u0108\3\2\2\2\u0109\u010a\3\2\2\2"+
		"\u010a\u010b\3\2\2\2\u010b\u010c\b\16\1\2\u010c\33\3\2\2\2\u010d\u010e"+
		"\7\r\2\2\u010e\u010f\7\20\2\2\u010f\u0117\b\17\1\2\u0110\u0111\7\33\2"+
		"\2\u0111\u0118\b\17\1\2\u0112\u0113\7\35\2\2\u0113\u0118\b\17\1\2\u0114"+
		"\u0115\7\34\2\2\u0115\u0118\b\17\1\2\u0116\u0118\5\36\20\2\u0117\u0110"+
		"\3\2\2\2\u0117\u0112\3\2\2\2\u0117\u0114\3\2\2\2\u0117\u0116\3\2\2\2\u0118"+
		"\u0119\3\2\2\2\u0119\u011a\b\17\1\2\u011a\u011b\7\31\2\2\u011b\u0123\b"+
		"\17\1\2\u011c\u011d\7\33\2\2\u011d\u0124\b\17\1\2\u011e\u011f\7\35\2\2"+
		"\u011f\u0124\b\17\1\2\u0120\u0121\7\34\2\2\u0121\u0124\b\17\1\2\u0122"+
		"\u0124\5\36\20\2\u0123\u011c\3\2\2\2\u0123\u011e\3\2\2\2\u0123\u0120\3"+
		"\2\2\2\u0123\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\b\17\1\2\u0126"+
		"\u0127\7\21\2\2\u0127\u0128\7\27\2\2\u0128\u012a\b\17\1\2\u0129\u012b"+
		"\5\f\7\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012a\3\2\2\2\u012c"+
		"\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\7\30\2\2\u012f\u0158\b"+
		"\17\1\2\u0130\u0131\7\16\2\2\u0131\u0132\7\20\2\2\u0132\u014b\b\17\1\2"+
		"\u0133\u0134\7\33\2\2\u0134\u014c\b\17\1\2\u0135\u0136\7\33\2\2\u0136"+
		"\u013d\b\17\1\2\u0137\u0138\7\35\2\2\u0138\u013d\b\17\1\2\u0139\u013a"+
		"\7\34\2\2\u013a\u013d\b\17\1\2\u013b\u013d\5\36\20\2\u013c\u0135\3\2\2"+
		"\2\u013c\u0137\3\2\2\2\u013c\u0139\3\2\2\2\u013c\u013b\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u013f\b\17\1\2\u013f\u0140\7\31\2\2\u0140\u0148\b\17\1"+
		"\2\u0141\u0142\7\33\2\2\u0142\u0149\b\17\1\2\u0143\u0144\7\35\2\2\u0144"+
		"\u0149\b\17\1\2\u0145\u0146\7\34\2\2\u0146\u0149\b\17\1\2\u0147\u0149"+
		"\5\36\20\2\u0148\u0141\3\2\2\2\u0148\u0143\3\2\2\2\u0148\u0145\3\2\2\2"+
		"\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014c\b\17\1\2\u014b\u0133"+
		"\3\2\2\2\u014b\u013c\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e\7\21\2\2"+
		"\u014e\u014f\7\27\2\2\u014f\u0151\b\17\1\2\u0150\u0152\5\f\7\2\u0151\u0150"+
		"\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154"+
		"\u0155\3\2\2\2\u0155\u0156\7\30\2\2\u0156\u0157\b\17\1\2\u0157\u0159\3"+
		"\2\2\2\u0158\u0130\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u0165\3\2\2\2\u015a"+
		"\u015b\7\17\2\2\u015b\u015c\7\27\2\2\u015c\u015e\b\17\1\2\u015d\u015f"+
		"\5\f\7\2\u015e\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u015e\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\7\30\2\2\u0163\u0164\b"+
		"\17\1\2\u0164\u0166\3\2\2\2\u0165\u015a\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0168\b\17\1\2\u0168\35\3\2\2\2\u0169\u016f\5 \21"+
		"\2\u016a\u016b\7\23\2\2\u016b\u016c\b\20\1\2\u016c\u016e\5 \21\2\u016d"+
		"\u016a\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2"+
		"\2\2\u0170\37\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173\7\33\2\2\u0173\u017b"+
		"\b\21\1\2\u0174\u0175\7\35\2\2\u0175\u017b\b\21\1\2\u0176\u0177\7\36\2"+
		"\2\u0177\u017b\b\21\1\2\u0178\u0179\7\34\2\2\u0179\u017b\b\21\1\2\u017a"+
		"\u0172\3\2\2\2\u017a\u0174\3\2\2\2\u017a\u0176\3\2\2\2\u017a\u0178\3\2"+
		"\2\2\u017b!\3\2\2\2\'$\'/9=EKRWcox\u0083\u0091\u009d\u00a2\u00b3\u00bf"+
		"\u00cd\u00d6\u00e1\u00f4\u00f9\u0106\u0109\u0117\u0123\u012c\u013c\u0148"+
		"\u014b\u0153\u0158\u0160\u0165\u016f\u017a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}