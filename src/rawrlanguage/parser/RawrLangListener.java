// Generated from RawrLang.g4 by ANTLR 4.9.2
package rawrlanguage.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RawrLangParser}.
 */
public interface RawrLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(RawrLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(RawrLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(RawrLangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(RawrLangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(RawrLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(RawrLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(RawrLangParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(RawrLangParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(RawrLangParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(RawrLangParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdattrib(RawrLangParser.CmdattribContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdattrib(RawrLangParser.CmdattribContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(RawrLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(RawrLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(RawrLangParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(RawrLangParser.TermoContext ctx);
}