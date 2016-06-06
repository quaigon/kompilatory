// Generated from Database.g4 by ANTLR 4.5.3
package parser;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DatabaseParser}.
 */


public interface DatabaseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DatabaseParser#tables}.
	 * @param ctx the parse tree
	 */
	void enterTables(DatabaseParser.TablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DatabaseParser#tables}.
	 * @param ctx the parse tree
	 */
	void exitTables(DatabaseParser.TablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DatabaseParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(DatabaseParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link DatabaseParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(DatabaseParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link DatabaseParser#tablename}.
	 * @param ctx the parse tree
	 */
	void enterTablename(DatabaseParser.TablenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DatabaseParser#tablename}.
	 * @param ctx the parse tree
	 */
	void exitTablename(DatabaseParser.TablenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DatabaseParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(DatabaseParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link DatabaseParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(DatabaseParser.ColumnContext ctx);
}