// Generated from RawrLang.g4 by ANTLR 4.9.2
package rawrlanguage.parser;

	import java.util.ArrayList;
	import java.util.Stack;
	import ast.*;
	import datastructures.*;
	import exceptions.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RawrLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, AP=12, FP=13, SC=14, OP=15, ATTR=16, ICR=17, ACH=18, 
		FCH=19, OPREL=20, VIR=21, ID=22, NUMBER=23, TEXT=24, WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "AP", "FP", "SC", "OP", "ATTR", "ICR", "ACH", "FCH", 
			"OPREL", "VIR", "ID", "NUMBER", "TEXT", "WS"
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
			"ID", "NUMBER", "TEXT", "WS"
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
		


	public RawrLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RawrLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00ab\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\5\25\u0087\n\25\3\26\3\26\3\27\3\27\7\27\u008d\n\27"+
		"\f\27\16\27\u0090\13\27\3\30\6\30\u0093\n\30\r\30\16\30\u0094\3\30\3\30"+
		"\6\30\u0099\n\30\r\30\16\30\u009a\5\30\u009d\n\30\3\31\3\31\7\31\u00a1"+
		"\n\31\f\31\16\31\u00a4\13\31\3\31\3\31\3\32\3\32\3\32\3\32\2\2\33\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\3\2\t\5\2,-//\61\61\4\2"+
		">>@@\3\2c|\5\2\62;C\\c|\3\2\62;\3\2$$\5\2\13\f\17\17\"\"\2\u00b3\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5<\3\2\2\2\7@\3\2\2\2\tG\3\2\2\2\13"+
		"L\3\2\2\2\rR\3\2\2\2\17U\3\2\2\2\21Y\3\2\2\2\23^\3\2\2\2\25d\3\2\2\2\27"+
		"g\3\2\2\2\31l\3\2\2\2\33n\3\2\2\2\35p\3\2\2\2\37r\3\2\2\2!t\3\2\2\2#v"+
		"\3\2\2\2%y\3\2\2\2\'{\3\2\2\2)\u0086\3\2\2\2+\u0088\3\2\2\2-\u008a\3\2"+
		"\2\2/\u0092\3\2\2\2\61\u009e\3\2\2\2\63\u00a7\3\2\2\2\65\66\7u\2\2\66"+
		"\67\7v\2\2\678\7c\2\289\7t\2\29:\7v\2\2:;\7<\2\2;\4\3\2\2\2<=\7g\2\2="+
		">\7p\2\2>?\7f\2\2?\6\3\2\2\2@A\7p\2\2AB\7w\2\2BC\7o\2\2CD\7d\2\2DE\7g"+
		"\2\2EF\7t\2\2F\b\3\2\2\2GH\7v\2\2HI\7g\2\2IJ\7z\2\2JK\7v\2\2K\n\3\2\2"+
		"\2LM\7y\2\2MN\7j\2\2NO\7k\2\2OP\7n\2\2PQ\7g\2\2Q\f\3\2\2\2RS\7f\2\2ST"+
		"\7q\2\2T\16\3\2\2\2UV\7h\2\2VW\7q\2\2WX\7t\2\2X\20\3\2\2\2YZ\7t\2\2Z["+
		"\7g\2\2[\\\7c\2\2\\]\7f\2\2]\22\3\2\2\2^_\7y\2\2_`\7t\2\2`a\7k\2\2ab\7"+
		"v\2\2bc\7g\2\2c\24\3\2\2\2de\7k\2\2ef\7h\2\2f\26\3\2\2\2gh\7g\2\2hi\7"+
		"n\2\2ij\7u\2\2jk\7g\2\2k\30\3\2\2\2lm\7*\2\2m\32\3\2\2\2no\7+\2\2o\34"+
		"\3\2\2\2pq\7=\2\2q\36\3\2\2\2rs\t\2\2\2s \3\2\2\2tu\7?\2\2u\"\3\2\2\2"+
		"vw\7-\2\2wx\7-\2\2x$\3\2\2\2yz\7}\2\2z&\3\2\2\2{|\7\177\2\2|(\3\2\2\2"+
		"}\u0087\t\3\2\2~\177\7@\2\2\177\u0087\7?\2\2\u0080\u0081\7>\2\2\u0081"+
		"\u0087\7?\2\2\u0082\u0083\7?\2\2\u0083\u0087\7?\2\2\u0084\u0085\7#\2\2"+
		"\u0085\u0087\7?\2\2\u0086}\3\2\2\2\u0086~\3\2\2\2\u0086\u0080\3\2\2\2"+
		"\u0086\u0082\3\2\2\2\u0086\u0084\3\2\2\2\u0087*\3\2\2\2\u0088\u0089\7"+
		".\2\2\u0089,\3\2\2\2\u008a\u008e\t\4\2\2\u008b\u008d\t\5\2\2\u008c\u008b"+
		"\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		".\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0093\t\6\2\2\u0092\u0091\3\2\2\2"+
		"\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u009c"+
		"\3\2\2\2\u0096\u0098\7\60\2\2\u0097\u0099\t\6\2\2\u0098\u0097\3\2\2\2"+
		"\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d"+
		"\3\2\2\2\u009c\u0096\3\2\2\2\u009c\u009d\3\2\2\2\u009d\60\3\2\2\2\u009e"+
		"\u00a2\t\7\2\2\u009f\u00a1\n\7\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a6\t\7\2\2\u00a6\62\3\2\2\2\u00a7\u00a8\t\b\2"+
		"\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\32\2\2\u00aa\64\3\2\2\2\n\2\u0086"+
		"\u008c\u008e\u0094\u009a\u009c\u00a2\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}