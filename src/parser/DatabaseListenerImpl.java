package parser;

import generator.model.Column;
import generator.model.ColumnType;
import generator.model.MetaModel;
import generator.model.Table;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 06.06.2016.
 */
public class DatabaseListenerImpl implements DatabaseListener {

    private List<Table> tableList = new ArrayList<>();

    private List<Column> columnList = new ArrayList<>();

    private TablesGenaratedCallback callback;

    private Table table;

    private MetaModel metaModel;

    String text;

    public DatabaseListenerImpl(TablesGenaratedCallback callback) {
        this.callback = callback;
    }

    @Override
    public void enterTables(DatabaseParser.TablesContext ctx) {
    }

    @Override
    public void exitTables(DatabaseParser.TablesContext ctx) {
        MetaModel metaModel = new MetaModel(tableList);
        if (callback != null) {
            callback.onTablesGenerated(metaModel);
        }
    }

    @Override
    public void enterTable(DatabaseParser.TableContext ctx) {
        table = new Table();
        columnList = new ArrayList<>();
    }

    @Override
    public void exitTable(DatabaseParser.TableContext ctx) {
        ParseTree parseTree = ctx;
        table.setColumns(columnList);
        tableList.add(table);
    }

    @Override
    public void enterTablename(DatabaseParser.TablenameContext ctx) {
    }

    @Override
    public void exitTablename(DatabaseParser.TablenameContext ctx) {
        ParseTree parseTree = ctx;
        table.setTableName(parseTree.getChild(1).toString());
    }

    @Override
    public void enterColumn(DatabaseParser.ColumnContext ctx) {

    }

    @Override
    public void exitColumn(DatabaseParser.ColumnContext ctx) {
        generateColumn(ctx);
    }

    private void generateColumn (ParseTree parseTree ) {
        Column column = new Column();
        parseTree.getText();
        parseTree.getChild(0);

        column.setName(parseTree.getChild(1).toString());
        column.setType(ColumnType.lookup(parseTree.getChild(0).toString()));

        columnList.add(column);

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

    public interface TablesGenaratedCallback {
        void onTablesGenerated(MetaModel metamodel);
    }
}
