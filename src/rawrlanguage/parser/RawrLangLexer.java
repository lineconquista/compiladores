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
		FCH=19, OPREL=20, VIR=21, ID=22, NUMBER=23, TEXT=24, CM=25, WS=26;
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
			"OPREL", "VIR", "ID", "NUMBER", "TEXT", "CM", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00b8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\5\25\u0089\n\25\3\26\3\26\3\27\3\27\7\27"+
		"\u008f\n\27\f\27\16\27\u0092\13\27\3\30\6\30\u0095\n\30\r\30\16\30\u0096"+
		"\3\30\3\30\6\30\u009b\n\30\r\30\16\30\u009c\5\30\u009f\n\30\3\31\3\31"+
		"\7\31\u00a3\n\31\f\31\16\31\u00a6\13\31\3\31\3\31\3\32\3\32\7\32\u00ac"+
		"\n\32\f\32\16\32\u00af\13\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3"+
		"\u00ad\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2"+
		"\t\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\3\2$$\5\2\13\f\17"+
		"\17\"\"\2\u00c1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\5"+
		">\3\2\2\2\7B\3\2\2\2\tI\3\2\2\2\13N\3\2\2\2\rT\3\2\2\2\17W\3\2\2\2\21"+
		"[\3\2\2\2\23`\3\2\2\2\25f\3\2\2\2\27i\3\2\2\2\31n\3\2\2\2\33p\3\2\2\2"+
		"\35r\3\2\2\2\37t\3\2\2\2!v\3\2\2\2#x\3\2\2\2%{\3\2\2\2\'}\3\2\2\2)\u0088"+
		"\3\2\2\2+\u008a\3\2\2\2-\u008c\3\2\2\2/\u0094\3\2\2\2\61\u00a0\3\2\2\2"+
		"\63\u00a9\3\2\2\2\65\u00b4\3\2\2\2\678\7u\2\289\7v\2\29:\7c\2\2:;\7t\2"+
		"\2;<\7v\2\2<=\7<\2\2=\4\3\2\2\2>?\7g\2\2?@\7p\2\2@A\7f\2\2A\6\3\2\2\2"+
		"BC\7p\2\2CD\7w\2\2DE\7o\2\2EF\7d\2\2FG\7g\2\2GH\7t\2\2H\b\3\2\2\2IJ\7"+
		"v\2\2JK\7g\2\2KL\7z\2\2LM\7v\2\2M\n\3\2\2\2NO\7y\2\2OP\7j\2\2PQ\7k\2\2"+
		"QR\7n\2\2RS\7g\2\2S\f\3\2\2\2TU\7f\2\2UV\7q\2\2V\16\3\2\2\2WX\7h\2\2X"+
		"Y\7q\2\2YZ\7t\2\2Z\20\3\2\2\2[\\\7t\2\2\\]\7g\2\2]^\7c\2\2^_\7f\2\2_\22"+
		"\3\2\2\2`a\7y\2\2ab\7t\2\2bc\7k\2\2cd\7v\2\2de\7g\2\2e\24\3\2\2\2fg\7"+
		"k\2\2gh\7h\2\2h\26\3\2\2\2ij\7g\2\2jk\7n\2\2kl\7u\2\2lm\7g\2\2m\30\3\2"+
		"\2\2no\7*\2\2o\32\3\2\2\2pq\7+\2\2q\34\3\2\2\2rs\7=\2\2s\36\3\2\2\2tu"+
		"\t\2\2\2u \3\2\2\2vw\7?\2\2w\"\3\2\2\2xy\7-\2\2yz\7-\2\2z$\3\2\2\2{|\7"+
		"}\2\2|&\3\2\2\2}~\7\177\2\2~(\3\2\2\2\177\u0089\t\3\2\2\u0080\u0081\7"+
		"@\2\2\u0081\u0089\7?\2\2\u0082\u0083\7>\2\2\u0083\u0089\7?\2\2\u0084\u0085"+
		"\7?\2\2\u0085\u0089\7?\2\2\u0086\u0087\7#\2\2\u0087\u0089\7?\2\2\u0088"+
		"\177\3\2\2\2\u0088\u0080\3\2\2\2\u0088\u0082\3\2\2\2\u0088\u0084\3\2\2"+
		"\2\u0088\u0086\3\2\2\2\u0089*\3\2\2\2\u008a\u008b\7.\2\2\u008b,\3\2\2"+
		"\2\u008c\u0090\t\4\2\2\u008d\u008f\t\5\2\2\u008e\u008d\3\2\2\2\u008f\u0092"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091.\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0093\u0095\t\6\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u009e\3\2\2\2\u0098"+
		"\u009a\7\60\2\2\u0099\u009b\t\6\2\2\u009a\u0099\3\2\2\2\u009b\u009c\3"+
		"\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u0098\3\2\2\2\u009e\u009f\3\2\2\2\u009f\60\3\2\2\2\u00a0\u00a4\t\7\2"+
		"\2\u00a1\u00a3\n\7\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2"+
		"\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7"+
		"\u00a8\t\7\2\2\u00a8\62\3\2\2\2\u00a9\u00ad\7&\2\2\u00aa\u00ac\13\2\2"+
		"\2\u00ab\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ad\u00ab"+
		"\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\7&\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b3\b\32\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\t\b\2"+
		"\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\b\33\2\2\u00b7\66\3\2\2\2\13\2\u0088"+
		"\u008e\u0090\u0096\u009c\u009e\u00a4\u00ad\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}