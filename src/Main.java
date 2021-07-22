import org.antlr.v4.runtime.CommonTokenStream;

import exceptions.RawrSemanticException;
import rawrlanguage.parser.RawrLangLexer;
import rawrlanguage.parser.RawrLangParser;
import org.antlr.v4.runtime.*;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			RawrLangLexer lexer;
			RawrLangParser parser;
			
			lexer = new RawrLangLexer(CharStreams.fromFileName("input.rawr"));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			parser = new RawrLangParser(tokenStream);
			parser.prog();
			System.out.print("compilado!!!");
			parser.exibeComandos();
			parser.generateCode();
			
		}catch(RawrSemanticException ex){
			System.err.print("Semantic error: "+ex.getMessage());
		}catch(Exception ex){
			System.err.print("errooo: "+ex.getMessage());
		}

	}

}
