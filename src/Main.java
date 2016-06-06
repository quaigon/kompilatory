import generator.model.MetaModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.DatabaseLexer;
import parser.DatabaseListenerImpl;
import parser.DatabaseParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

           generateModel();

    }

    public static void generateModel ()  {


        try {
            initParser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initParser () throws IOException {
        String text = "Table Tabelka\n" +
                "Int Id\n" +
                "String Lol\n" +
                "String Lolw\n" +
                "String Lole\n" +
                "\n" +
                "Table Tabelkaa\n" +
                "Int Id\n" +
                "String Lolg\n" +
                "String Lolc\n" +
                "String Lolg\n";

        ANTLRInputStream antlrInputStream = new ANTLRInputStream(new BufferedReader(new StringReader(text)));

        DatabaseLexer databaseLexer = new DatabaseLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(databaseLexer);
        DatabaseParser databaseParser = new DatabaseParser(commonTokenStream);
        DatabaseListenerImpl databaseListener = new DatabaseListenerImpl(new DatabaseListenerImpl.TablesGenaratedCallback() {
            @Override
            public void onTablesGenerated(MetaModel metamodel) {
                System.out.print(metamodel.toString());
            }
        });
        databaseParser.addParseListener(databaseListener);
        DatabaseParser.TablesContext tablesContext = databaseParser.tables();
    }


    
}
