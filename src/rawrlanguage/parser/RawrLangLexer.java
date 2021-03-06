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
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, OP=17, ATTR=18, 
		ICR=19, DCR=20, ACH=21, FCH=22, OPREL=23, OPRELNUM=24, OPRELBOOL=25, VIR=26, 
		BOOLEAN=27, FALSE=28, TRUE=29, ID=30, INT=31, DOUBLE=32, TEXT=33, CM=34, 
		WS=35;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "AP", "FP", "SC", "OP", "ATTR", "ICR", 
			"DCR", "ACH", "FCH", "OPREL", "OPRELNUM", "OPRELBOOL", "VIR", "BOOLEAN", 
			"FALSE", "TRUE", "ID", "INT", "DOUBLE", "TEXT", "CM", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'start:'", "'end'", "'double'", "'boolean'", "'text'", "'int'", 
			"'while'", "'do'", "'for'", "'read'", "'write'", "'if'", "'else'", "'('", 
			"')'", "';'", null, "'='", "'++'", "'--'", "'{'", "'}'", null, null, 
			null, "','", null, "'false'", "'true'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "AP", "FP", "SC", "OP", "ATTR", "ICR", "DCR", "ACH", "FCH", 
			"OPREL", "OPRELNUM", "OPRELBOOL", "VIR", "BOOLEAN", "FALSE", "TRUE", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2%\u0105\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\5\30\u00a5\n\30\3\31\3\31\3\31\3\31\3\31\5\31\u00ac\n\31\3\32\3\32"+
		"\3\32\3\32\5\32\u00b2\n\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\5\34\u00bf\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\7\37\u00ce\n\37\f\37\16\37\u00d1\13\37\3 \5 \u00d4"+
		"\n \3 \6 \u00d7\n \r \16 \u00d8\3!\5!\u00dc\n!\3!\6!\u00df\n!\r!\16!\u00e0"+
		"\3!\3!\6!\u00e5\n!\r!\16!\u00e6\5!\u00e9\n!\3\"\3\"\7\"\u00ed\n\"\f\""+
		"\16\"\u00f0\13\"\3\"\3\"\3#\3#\3#\3#\7#\u00f8\n#\f#\16#\u00fb\13#\3#\3"+
		"#\3#\3#\3#\3$\3$\3$\3$\3\u00f9\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%\3\2\t\5\2,-//\61\61\4\2"+
		">>@@\3\2c|\5\2\62;C\\c|\3\2\62;\3\2$$\5\2\13\f\17\17\"\"\2\u0112\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3I"+
		"\3\2\2\2\5P\3\2\2\2\7T\3\2\2\2\t[\3\2\2\2\13c\3\2\2\2\rh\3\2\2\2\17l\3"+
		"\2\2\2\21r\3\2\2\2\23u\3\2\2\2\25y\3\2\2\2\27~\3\2\2\2\31\u0084\3\2\2"+
		"\2\33\u0087\3\2\2\2\35\u008c\3\2\2\2\37\u008e\3\2\2\2!\u0090\3\2\2\2#"+
		"\u0092\3\2\2\2%\u0094\3\2\2\2\'\u0096\3\2\2\2)\u0099\3\2\2\2+\u009c\3"+
		"\2\2\2-\u009e\3\2\2\2/\u00a4\3\2\2\2\61\u00ab\3\2\2\2\63\u00b1\3\2\2\2"+
		"\65\u00b3\3\2\2\2\67\u00be\3\2\2\29\u00c0\3\2\2\2;\u00c6\3\2\2\2=\u00cb"+
		"\3\2\2\2?\u00d3\3\2\2\2A\u00db\3\2\2\2C\u00ea\3\2\2\2E\u00f3\3\2\2\2G"+
		"\u0101\3\2\2\2IJ\7u\2\2JK\7v\2\2KL\7c\2\2LM\7t\2\2MN\7v\2\2NO\7<\2\2O"+
		"\4\3\2\2\2PQ\7g\2\2QR\7p\2\2RS\7f\2\2S\6\3\2\2\2TU\7f\2\2UV\7q\2\2VW\7"+
		"w\2\2WX\7d\2\2XY\7n\2\2YZ\7g\2\2Z\b\3\2\2\2[\\\7d\2\2\\]\7q\2\2]^\7q\2"+
		"\2^_\7n\2\2_`\7g\2\2`a\7c\2\2ab\7p\2\2b\n\3\2\2\2cd\7v\2\2de\7g\2\2ef"+
		"\7z\2\2fg\7v\2\2g\f\3\2\2\2hi\7k\2\2ij\7p\2\2jk\7v\2\2k\16\3\2\2\2lm\7"+
		"y\2\2mn\7j\2\2no\7k\2\2op\7n\2\2pq\7g\2\2q\20\3\2\2\2rs\7f\2\2st\7q\2"+
		"\2t\22\3\2\2\2uv\7h\2\2vw\7q\2\2wx\7t\2\2x\24\3\2\2\2yz\7t\2\2z{\7g\2"+
		"\2{|\7c\2\2|}\7f\2\2}\26\3\2\2\2~\177\7y\2\2\177\u0080\7t\2\2\u0080\u0081"+
		"\7k\2\2\u0081\u0082\7v\2\2\u0082\u0083\7g\2\2\u0083\30\3\2\2\2\u0084\u0085"+
		"\7k\2\2\u0085\u0086\7h\2\2\u0086\32\3\2\2\2\u0087\u0088\7g\2\2\u0088\u0089"+
		"\7n\2\2\u0089\u008a\7u\2\2\u008a\u008b\7g\2\2\u008b\34\3\2\2\2\u008c\u008d"+
		"\7*\2\2\u008d\36\3\2\2\2\u008e\u008f\7+\2\2\u008f \3\2\2\2\u0090\u0091"+
		"\7=\2\2\u0091\"\3\2\2\2\u0092\u0093\t\2\2\2\u0093$\3\2\2\2\u0094\u0095"+
		"\7?\2\2\u0095&\3\2\2\2\u0096\u0097\7-\2\2\u0097\u0098\7-\2\2\u0098(\3"+
		"\2\2\2\u0099\u009a\7/\2\2\u009a\u009b\7/\2\2\u009b*\3\2\2\2\u009c\u009d"+
		"\7}\2\2\u009d,\3\2\2\2\u009e\u009f\7\177\2\2\u009f.\3\2\2\2\u00a0\u00a1"+
		"\7?\2\2\u00a1\u00a5\7?\2\2\u00a2\u00a3\7#\2\2\u00a3\u00a5\7?\2\2\u00a4"+
		"\u00a0\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\60\3\2\2\2\u00a6\u00ac\t\3\2"+
		"\2\u00a7\u00a8\7@\2\2\u00a8\u00ac\7?\2\2\u00a9\u00aa\7>\2\2\u00aa\u00ac"+
		"\7?\2\2\u00ab\u00a6\3\2\2\2\u00ab\u00a7\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac"+
		"\62\3\2\2\2\u00ad\u00ae\7(\2\2\u00ae\u00b2\7(\2\2\u00af\u00b0\7~\2\2\u00b0"+
		"\u00b2\7~\2\2\u00b1\u00ad\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\64\3\2\2\2"+
		"\u00b3\u00b4\7.\2\2\u00b4\66\3\2\2\2\u00b5\u00b6\7h\2\2\u00b6\u00b7\7"+
		"c\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7u\2\2\u00b9\u00bf\7g\2\2\u00ba\u00bb"+
		"\7v\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7w\2\2\u00bd\u00bf\7g\2\2\u00be"+
		"\u00b5\3\2\2\2\u00be\u00ba\3\2\2\2\u00bf8\3\2\2\2\u00c0\u00c1\7h\2\2\u00c1"+
		"\u00c2\7c\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7u\2\2\u00c4\u00c5\7g\2\2"+
		"\u00c5:\3\2\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7w\2"+
		"\2\u00c9\u00ca\7g\2\2\u00ca<\3\2\2\2\u00cb\u00cf\t\4\2\2\u00cc\u00ce\t"+
		"\5\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0>\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d4\7/\2\2\u00d3"+
		"\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d7\t\6"+
		"\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9@\3\2\2\2\u00da\u00dc\7/\2\2\u00db\u00da\3\2\2\2\u00db"+
		"\u00dc\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00df\t\6\2\2\u00de\u00dd\3\2"+
		"\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e8\3\2\2\2\u00e2\u00e4\7\60\2\2\u00e3\u00e5\t\6\2\2\u00e4\u00e3\3"+
		"\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u00e2\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9B\3\2\2\2"+
		"\u00ea\u00ee\t\7\2\2\u00eb\u00ed\n\7\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0"+
		"\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f1\u00f2\t\7\2\2\u00f2D\3\2\2\2\u00f3\u00f4\7u\2\2\u00f4"+
		"\u00f5\7\64\2\2\u00f5\u00f9\3\2\2\2\u00f6\u00f8\13\2\2\2\u00f7\u00f6\3"+
		"\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00fa\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa"+
		"\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fd\7u\2\2\u00fd\u00fe\7\64"+
		"\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\b#\2\2\u0100F\3\2\2\2\u0101\u0102"+
		"\t\b\2\2\u0102\u0103\3\2\2\2\u0103\u0104\b$\2\2\u0104H\3\2\2\2\21\2\u00a4"+
		"\u00ab\u00b1\u00be\u00cd\u00cf\u00d3\u00d8\u00db\u00e0\u00e6\u00e8\u00ee"+
		"\u00f9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}