package generator;

import generator.model.MetaModel;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// TODO: Auto-generated Javadoc
/**
 * Klasa generujaca plik SQL ze skryptami tworzacymi tabelki w bazie danych
 */
public class SQLFileGenerator {

	/** Meta model bazy danych */
	private MetaModel metaModel;
	
	/** Generator schematu bazy danych */
	DBSchemeCreator schemeCreator;
	
	/** Mapper typow kolumn */
	ColumnTypeMapper mapper;

	/**
	 * Konstruktor SQL file generatora
	 *
	 * @param metaModel bazy danych
	 * @param mapper mapper typow kolumn
	 */
	public SQLFileGenerator(MetaModel metaModel, ColumnTypeMapper mapper) {
		this.metaModel = metaModel;
		this.mapper = mapper;
		schemeCreator = new DBSchemeCreator(metaModel, mapper);
	}

	/**
	 * Tworzy plik (nadpisuje jezli istnieje) ze skryptami sluzacymi do utworzenia tabelek
	 *
	 * @param filePath sciezka gdzie ma zostac utworzony plik
	 */
	public void generateFile(String filePath) {
		try {
			PrintWriter writer = new PrintWriter(filePath);
			for (String f : schemeCreator.getCreates()) {
				writer.println(f);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
