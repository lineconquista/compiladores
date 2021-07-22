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
	import ast.CommandLeitura;
	import ast.CommandEscrita;
	import ast.CommandAtribuicao;
	import ast.CommandDecisao;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, AP=9, 
		FP=10, SC=11, OP=12, ATTR=13, ACH=14, FCH=15, OPREL=16, VIR=17, ID=18, 
		NUMBER=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "AP", 
			"FP", "SC", "OP", "ATTR", "ACH", "FCH", "OPREL", "VIR", "ID", "NUMBER", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'start:'", "'end'", "'numero'", "'texto'", "'read'", "'write'", 
			"'se'", "'senao'", "'('", "')'", "';'", null, "'='", "'{'", "'}'", null, 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "AP", "FP", "SC", 
			"OP", "ATTR", "ACH", "FCH", "OPREL", "VIR", "ID", "NUMBER", "WS"
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


		private int _tipo;
		private String _varName;
		private String _varValue;
		private RawrSymbolTable symbolTable = new RawrSymbolTable();
		private RawrSymbol symbol;
		private RawrProgram program = new RawrProgram();
		private ArrayList <AbstractCommand> curThread;
		private String _readId;
		private String _writeId;
		private String _exprId;
		private String _exprContent;
		private String _exprDecision;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack <ArrayList<AbstractCommand>>();
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		
		public void variableValidate(String id){
		
				if (!symbolTable.exists(id)){
					throw new RawrSemanticException ("Variable "+id+" not declared");
				}
		}
		
		public void exibeComandos(){
			for(AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u008a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21o\n\21\3\22\3\22\3\23\3\23"+
		"\7\23u\n\23\f\23\16\23x\13\23\3\24\6\24{\n\24\r\24\16\24|\3\24\3\24\6"+
		"\24\u0081\n\24\r\24\16\24\u0082\5\24\u0085\n\24\3\25\3\25\3\25\3\25\2"+
		"\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26\3\2\b\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62"+
		";C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2\u0091\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\3+\3\2\2\2\5\62\3\2\2\2\7\66\3\2\2\2\t=\3\2\2\2\13C\3\2\2"+
		"\2\rH\3\2\2\2\17N\3\2\2\2\21Q\3\2\2\2\23W\3\2\2\2\25Y\3\2\2\2\27[\3\2"+
		"\2\2\31]\3\2\2\2\33_\3\2\2\2\35a\3\2\2\2\37c\3\2\2\2!n\3\2\2\2#p\3\2\2"+
		"\2%r\3\2\2\2\'z\3\2\2\2)\u0086\3\2\2\2+,\7u\2\2,-\7v\2\2-.\7c\2\2./\7"+
		"t\2\2/\60\7v\2\2\60\61\7<\2\2\61\4\3\2\2\2\62\63\7g\2\2\63\64\7p\2\2\64"+
		"\65\7f\2\2\65\6\3\2\2\2\66\67\7p\2\2\678\7w\2\289\7o\2\29:\7g\2\2:;\7"+
		"t\2\2;<\7q\2\2<\b\3\2\2\2=>\7v\2\2>?\7g\2\2?@\7z\2\2@A\7v\2\2AB\7q\2\2"+
		"B\n\3\2\2\2CD\7t\2\2DE\7g\2\2EF\7c\2\2FG\7f\2\2G\f\3\2\2\2HI\7y\2\2IJ"+
		"\7t\2\2JK\7k\2\2KL\7v\2\2LM\7g\2\2M\16\3\2\2\2NO\7u\2\2OP\7g\2\2P\20\3"+
		"\2\2\2QR\7u\2\2RS\7g\2\2ST\7p\2\2TU\7c\2\2UV\7q\2\2V\22\3\2\2\2WX\7*\2"+
		"\2X\24\3\2\2\2YZ\7+\2\2Z\26\3\2\2\2[\\\7=\2\2\\\30\3\2\2\2]^\t\2\2\2^"+
		"\32\3\2\2\2_`\7?\2\2`\34\3\2\2\2ab\7}\2\2b\36\3\2\2\2cd\7\177\2\2d \3"+
		"\2\2\2eo\t\3\2\2fg\7@\2\2go\7?\2\2hi\7>\2\2io\7?\2\2jk\7?\2\2ko\7?\2\2"+
		"lm\7#\2\2mo\7?\2\2ne\3\2\2\2nf\3\2\2\2nh\3\2\2\2nj\3\2\2\2nl\3\2\2\2o"+
		"\"\3\2\2\2pq\7.\2\2q$\3\2\2\2rv\t\4\2\2su\t\5\2\2ts\3\2\2\2ux\3\2\2\2"+
		"vt\3\2\2\2vw\3\2\2\2w&\3\2\2\2xv\3\2\2\2y{\t\6\2\2zy\3\2\2\2{|\3\2\2\2"+
		"|z\3\2\2\2|}\3\2\2\2}\u0084\3\2\2\2~\u0080\7\60\2\2\177\u0081\t\6\2\2"+
		"\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0085\3\2\2\2\u0084~\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"(\3\2\2\2\u0086\u0087\t\7\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\25\2\2"+
		"\u0089*\3\2\2\2\t\2ntv|\u0082\u0084\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}