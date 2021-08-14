// Generated from RawrLang.g4 by ANTLR 4.9.2
package rawrlanguage.parser;

	import java.util.ArrayList;
	import java.util.Stack;
	import ast.*;
	import datastructures.*;
	import exceptions.*;

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
	 * Enter a parse tree produced by {@link RawrLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(RawrLangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(RawrLangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#decl_var}.
	 * @param ctx the parse tree
	 */
	void enterDecl_var(RawrLangParser.Decl_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#decl_var}.
	 * @param ctx the parse tree
	 */
	void exitDecl_var(RawrLangParser.Decl_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(RawrLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(RawrLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(RawrLangParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(RawrLangParser.CodeContext ctx);
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
	 * Enter a parse tree produced by {@link RawrLangParser#cmd_loop}.
	 * @param ctx the parse tree
	 */
	void enterCmd_loop(RawrLangParser.Cmd_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmd_loop}.
	 * @param ctx the parse tree
	 */
	void exitCmd_loop(RawrLangParser.Cmd_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmdloop1}.
	 * @param ctx the parse tree
	 */
	void enterCmdloop1(RawrLangParser.Cmdloop1Context ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmdloop1}.
	 * @param ctx the parse tree
	 */
	void exitCmdloop1(RawrLangParser.Cmdloop1Context ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmdloop2}.
	 * @param ctx the parse tree
	 */
	void enterCmdloop2(RawrLangParser.Cmdloop2Context ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmdloop2}.
	 * @param ctx the parse tree
	 */
	void exitCmdloop2(RawrLangParser.Cmdloop2Context ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmdloop3}.
	 * @param ctx the parse tree
	 */
	void enterCmdloop3(RawrLangParser.Cmdloop3Context ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmdloop3}.
	 * @param ctx the parse tree
	 */
	void exitCmdloop3(RawrLangParser.Cmdloop3Context ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmd_read}.
	 * @param ctx the parse tree
	 */
	void enterCmd_read(RawrLangParser.Cmd_readContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmd_read}.
	 * @param ctx the parse tree
	 */
	void exitCmd_read(RawrLangParser.Cmd_readContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmd_write}.
	 * @param ctx the parse tree
	 */
	void enterCmd_write(RawrLangParser.Cmd_writeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmd_write}.
	 * @param ctx the parse tree
	 */
	void exitCmd_write(RawrLangParser.Cmd_writeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmd_attrib}.
	 * @param ctx the parse tree
	 */
	void enterCmd_attrib(RawrLangParser.Cmd_attribContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmd_attrib}.
	 * @param ctx the parse tree
	 */
	void exitCmd_attrib(RawrLangParser.Cmd_attribContext ctx);
	/**
	 * Enter a parse tree produced by {@link RawrLangParser#cmd_conditional}.
	 * @param ctx the parse tree
	 */
	void enterCmd_conditional(RawrLangParser.Cmd_conditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#cmd_conditional}.
	 * @param ctx the parse tree
	 */
	void exitCmd_conditional(RawrLangParser.Cmd_conditionalContext ctx);
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
	 * Enter a parse tree produced by {@link RawrLangParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(RawrLangParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link RawrLangParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(RawrLangParser.TermContext ctx);
}