import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.DatabaseLexer;
import parser.DatabaseListener;
import parser.DatabaseListenerImpl;
import parser.DatabaseParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
           generateModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateModel () throws IOException {
        String text = "Table Tabelka2\n" +
                "Int Id1\n" +
                "String Nazwa3\n" +
                "String Nazwa4\n" +
                "String Nazwa5";

        ANTLRInputStream antlrInputStream = new ANTLRInputStream(new BufferedReader(new StringReader(text)));

        DatabaseLexer databaseLexer = new DatabaseLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(databaseLexer);
        DatabaseParser databaseParser = new DatabaseParser(commonTokenStream);
        DatabaseListenerImpl databaseListener = new DatabaseListenerImpl(null);
        databaseParser.addParseListener(databaseListener);
        DatabaseParser.TablesContext tablesContext = databaseParser.tables();


    }
}
