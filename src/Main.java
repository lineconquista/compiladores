import org.antlr.v4.runtime.CommonTokenStream;
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
		}catch(Exception ex){
			System.out.print("errooo: "+ex.getMessage());
		}

	}

}
