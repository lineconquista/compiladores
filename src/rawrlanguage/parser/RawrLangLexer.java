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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, AP=15, FP=16, SC=17, 
		OP=18, ATTR=19, ICR=20, DCR=21, ACH=22, FCH=23, OPREL=24, OPRELNUM=25, 
		OPRELBOOL=26, VIR=27, BOOLEAN=28, FALSE=29, TRUE=30, ID=31, INT=32, DOUBLE=33, 
		TEXT=34, CM=35, WS=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "AP", "FP", "SC", "OP", "ATTR", 
			"ICR", "DCR", "ACH", "FCH", "OPREL", "OPRELNUM", "OPRELBOOL", "VIR", 
			"BOOLEAN", "FALSE", "TRUE", "ID", "INT", "DOUBLE", "TEXT", "CM", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u010f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\5\31\u00af\n\31\3\32"+
		"\3\32\3\32\3\32\3\32\5\32\u00b6\n\32\3\33\3\33\3\33\3\33\5\33\u00bc\n"+
		"\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u00c9"+
		"\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \7 "+
		"\u00d8\n \f \16 \u00db\13 \3!\5!\u00de\n!\3!\6!\u00e1\n!\r!\16!\u00e2"+
		"\3\"\5\"\u00e6\n\"\3\"\6\"\u00e9\n\"\r\"\16\"\u00ea\3\"\3\"\6\"\u00ef"+
		"\n\"\r\"\16\"\u00f0\5\"\u00f3\n\"\3#\3#\7#\u00f7\n#\f#\16#\u00fa\13#\3"+
		"#\3#\3$\3$\3$\3$\7$\u0102\n$\f$\16$\u0105\13$\3$\3$\3$\3$\3$\3%\3%\3%"+
		"\3%\3\u0103\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
		"\67\359\36;\37= ?!A\"C#E$G%I&\3\2\t\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62"+
		";C\\c|\3\2\62;\3\2$$\5\2\13\f\17\17\"\"\2\u011c\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2"+
		"\2\5R\3\2\2\2\7V\3\2\2\2\t]\3\2\2\2\13e\3\2\2\2\rj\3\2\2\2\17n\3\2\2\2"+
		"\21t\3\2\2\2\23w\3\2\2\2\25{\3\2\2\2\27\u0080\3\2\2\2\31\u0086\3\2\2\2"+
		"\33\u0089\3\2\2\2\35\u0091\3\2\2\2\37\u0096\3\2\2\2!\u0098\3\2\2\2#\u009a"+
		"\3\2\2\2%\u009c\3\2\2\2\'\u009e\3\2\2\2)\u00a0\3\2\2\2+\u00a3\3\2\2\2"+
		"-\u00a6\3\2\2\2/\u00a8\3\2\2\2\61\u00ae\3\2\2\2\63\u00b5\3\2\2\2\65\u00bb"+
		"\3\2\2\2\67\u00bd\3\2\2\29\u00c8\3\2\2\2;\u00ca\3\2\2\2=\u00d0\3\2\2\2"+
		"?\u00d5\3\2\2\2A\u00dd\3\2\2\2C\u00e5\3\2\2\2E\u00f4\3\2\2\2G\u00fd\3"+
		"\2\2\2I\u010b\3\2\2\2KL\7u\2\2LM\7v\2\2MN\7c\2\2NO\7t\2\2OP\7v\2\2PQ\7"+
		"<\2\2Q\4\3\2\2\2RS\7g\2\2ST\7p\2\2TU\7f\2\2U\6\3\2\2\2VW\7f\2\2WX\7q\2"+
		"\2XY\7w\2\2YZ\7d\2\2Z[\7n\2\2[\\\7g\2\2\\\b\3\2\2\2]^\7d\2\2^_\7q\2\2"+
		"_`\7q\2\2`a\7n\2\2ab\7g\2\2bc\7c\2\2cd\7p\2\2d\n\3\2\2\2ef\7v\2\2fg\7"+
		"g\2\2gh\7z\2\2hi\7v\2\2i\f\3\2\2\2jk\7k\2\2kl\7p\2\2lm\7v\2\2m\16\3\2"+
		"\2\2no\7y\2\2op\7j\2\2pq\7k\2\2qr\7n\2\2rs\7g\2\2s\20\3\2\2\2tu\7f\2\2"+
		"uv\7q\2\2v\22\3\2\2\2wx\7h\2\2xy\7q\2\2yz\7t\2\2z\24\3\2\2\2{|\7t\2\2"+
		"|}\7g\2\2}~\7c\2\2~\177\7f\2\2\177\26\3\2\2\2\u0080\u0081\7y\2\2\u0081"+
		"\u0082\7t\2\2\u0082\u0083\7k\2\2\u0083\u0084\7v\2\2\u0084\u0085\7g\2\2"+
		"\u0085\30\3\2\2\2\u0086\u0087\7k\2\2\u0087\u0088\7h\2\2\u0088\32\3\2\2"+
		"\2\u0089\u008a\7g\2\2\u008a\u008b\7n\2\2\u008b\u008c\7u\2\2\u008c\u008d"+
		"\7g\2\2\u008d\u008e\7\"\2\2\u008e\u008f\7k\2\2\u008f\u0090\7h\2\2\u0090"+
		"\34\3\2\2\2\u0091\u0092\7g\2\2\u0092\u0093\7n\2\2\u0093\u0094\7u\2\2\u0094"+
		"\u0095\7g\2\2\u0095\36\3\2\2\2\u0096\u0097\7*\2\2\u0097 \3\2\2\2\u0098"+
		"\u0099\7+\2\2\u0099\"\3\2\2\2\u009a\u009b\7=\2\2\u009b$\3\2\2\2\u009c"+
		"\u009d\t\2\2\2\u009d&\3\2\2\2\u009e\u009f\7?\2\2\u009f(\3\2\2\2\u00a0"+
		"\u00a1\7-\2\2\u00a1\u00a2\7-\2\2\u00a2*\3\2\2\2\u00a3\u00a4\7/\2\2\u00a4"+
		"\u00a5\7/\2\2\u00a5,\3\2\2\2\u00a6\u00a7\7}\2\2\u00a7.\3\2\2\2\u00a8\u00a9"+
		"\7\177\2\2\u00a9\60\3\2\2\2\u00aa\u00ab\7?\2\2\u00ab\u00af\7?\2\2\u00ac"+
		"\u00ad\7#\2\2\u00ad\u00af\7?\2\2\u00ae\u00aa\3\2\2\2\u00ae\u00ac\3\2\2"+
		"\2\u00af\62\3\2\2\2\u00b0\u00b6\t\3\2\2\u00b1\u00b2\7@\2\2\u00b2\u00b6"+
		"\7?\2\2\u00b3\u00b4\7>\2\2\u00b4\u00b6\7?\2\2\u00b5\u00b0\3\2\2\2\u00b5"+
		"\u00b1\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\64\3\2\2\2\u00b7\u00b8\7(\2\2"+
		"\u00b8\u00bc\7(\2\2\u00b9\u00ba\7~\2\2\u00ba\u00bc\7~\2\2\u00bb\u00b7"+
		"\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\66\3\2\2\2\u00bd\u00be\7.\2\2\u00be"+
		"8\3\2\2\2\u00bf\u00c0\7h\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7n\2\2\u00c2"+
		"\u00c3\7u\2\2\u00c3\u00c9\7g\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7t\2\2"+
		"\u00c6\u00c7\7w\2\2\u00c7\u00c9\7g\2\2\u00c8\u00bf\3\2\2\2\u00c8\u00c4"+
		"\3\2\2\2\u00c9:\3\2\2\2\u00ca\u00cb\7h\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd"+
		"\7n\2\2\u00cd\u00ce\7u\2\2\u00ce\u00cf\7g\2\2\u00cf<\3\2\2\2\u00d0\u00d1"+
		"\7v\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7w\2\2\u00d3\u00d4\7g\2\2\u00d4"+
		">\3\2\2\2\u00d5\u00d9\t\4\2\2\u00d6\u00d8\t\5\2\2\u00d7\u00d6\3\2\2\2"+
		"\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da@\3"+
		"\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00de\7/\2\2\u00dd\u00dc\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00e1\t\6\2\2\u00e0\u00df\3\2"+
		"\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"B\3\2\2\2\u00e4\u00e6\7/\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e8\3\2\2\2\u00e7\u00e9\t\6\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2"+
		"\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00f2\3\2\2\2\u00ec"+
		"\u00ee\7\60\2\2\u00ed\u00ef\t\6\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3"+
		"\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2"+
		"\u00ec\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3D\3\2\2\2\u00f4\u00f8\t\7\2\2"+
		"\u00f5\u00f7\n\7\2\2\u00f6\u00f5\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6"+
		"\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb"+
		"\u00fc\t\7\2\2\u00fcF\3\2\2\2\u00fd\u00fe\7u\2\2\u00fe\u00ff\7\64\2\2"+
		"\u00ff\u0103\3\2\2\2\u0100\u0102\13\2\2\2\u0101\u0100\3\2\2\2\u0102\u0105"+
		"\3\2\2\2\u0103\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0106\3\2\2\2\u0105"+
		"\u0103\3\2\2\2\u0106\u0107\7u\2\2\u0107\u0108\7\64\2\2\u0108\u0109\3\2"+
		"\2\2\u0109\u010a\b$\2\2\u010aH\3\2\2\2\u010b\u010c\t\b\2\2\u010c\u010d"+
		"\3\2\2\2\u010d\u010e\b%\2\2\u010eJ\3\2\2\2\21\2\u00ae\u00b5\u00bb\u00c8"+
		"\u00d7\u00d9\u00dd\u00e2\u00e5\u00ea\u00f0\u00f2\u00f8\u0103\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}