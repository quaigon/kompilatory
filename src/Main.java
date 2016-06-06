import generator.*;
import generator.model.ColumnType;
import generator.model.MetaModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.DatabaseLexer;
import parser.DatabaseListenerImpl;
import parser.DatabaseParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.generateModel(args[0]);

    }

    public void generateModel(String filepath) {
        try {
            initParser(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initParser(String filepath) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(new BufferedReader(new FileReader(filepath)));

        DatabaseLexer databaseLexer = new DatabaseLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(databaseLexer);
        DatabaseParser databaseParser = new DatabaseParser(commonTokenStream);
        DatabaseListenerImpl databaseListener = new DatabaseListenerImpl(new DatabaseListenerImpl.TablesGenaratedCallback() {
            @Override
            public void onTablesGenerated(MetaModel metamodel) {
                genarateCode(metamodel);
            }
        });
        databaseParser.addParseListener(databaseListener);
        DatabaseParser.TablesContext tablesContext = databaseParser.tables();
    }

    private void genarateCode(MetaModel metaModel) {
        ColumnTypeMapper columnTypeMappper = new H2ColumnTypeMapper();

        SQLFileGenerator sqlFileGenerator = new SQLFileGenerator(metaModel, columnTypeMappper);
        ClassCodeGenerator classCodeGenerator = new ClassCodeGenerator(metaModel);
        DAOCodeGenerator daoCodeGenerator = new DAOCodeGenerator(metaModel);


        sqlFileGenerator.generateFile("sql.sql");
        classCodeGenerator.generateTableFiles();
        daoCodeGenerator.generateDaoFiles();

    }


}
