package generator;

import generator.model.Column;
import generator.model.ColumnType;
import generator.model.MetaModel;
import generator.model.Table;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Implementacja interfejsu tworzacego mMeta Model
 * Klasa tworza Meta Model na podstawie pliku z baza danych
 */
public class FileMetaModelCreator implements MetaModelCreator {

	/** Zawartosc pliku z opisem bazy danych */
	private String fileContent;

	/**
	 * Tworzenie kreatora meta modelu
	 *
	 * @param filePath the file path
	 */
	public FileMetaModelCreator(String filePath) {
		this.fileContent = readFile(filePath);
	}

	/* (non-Javadoc)
	 * @see MetaModelCreator#create()
	 */
	@Override
	public MetaModel create() {
		return new MetaModel(generateTables());
	}

	/**
	 * Generuje liste tabelek w postaci string
	 * To znaczy dzieli plik z opisem bazy danych na tabelki
	 *
	 * @return lista z opisanymi tabelkami w string
	 */
	private List<String> generateStringTables() {
		// System.out.println(fileContent);
		List<String> tables = new ArrayList<String>(Arrays.asList(fileContent
				.split("---")));

		// for (String c : tables) {
		// System.out.print(c);
		// }
		return tables;
	}

	/**
	 * Odczytuje plik z opisem bazy danych
	 *
	 * @param filePath sciezka do pliku
	 * @return zawartosc pliku
	 */
	private String readFile(String filePath) {
		StringBuilder fileContent = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();

			while (line != null) {
				fileContent.append(line).append(System.lineSeparator());

				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileContent.toString();
	}

	/**
	 * Generuje kolumny danej tabelki
	 *
	 * @param stringTable opis tabelki ktorej maja zostac utworzone kolumny
	 * @return lista kolumn tabelek
	 */
	private List<Column> generateColumns(String stringTable) {

		List<Column> columnList = new ArrayList<Column>();
		Scanner scanner = new Scanner(stringTable);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (!line.startsWith("Table: ") && !line.equals("")) {
				String[] columnInfo = line.split(" ");
				columnList.add(new Column(cutLastLetter(columnInfo[1]), ColumnType
						.lookup(columnInfo[0])));
			}
		}
		scanner.close();

		return columnList;
	}

	/**
	 * Zwraca nazwe tabelki na podstawie opisu tabelki
	 *
	 * @param stringTable opis tabelki
	 * @return nazwa tabelki
	 */
	private String getTableName(String stringTable) {
		Scanner scanner = new Scanner(stringTable);
		String tableName = scanner.nextLine();
		if (tableName.equals("")) {
			tableName = scanner.nextLine();
		}
		tableName = tableName.replace("Table: ", "");
		scanner.close();
		return cutLastLetter(tableName);
	}

	/**
	 * Generuje tabelki do metamodelu
	 *
	 * @return lista z tabelkami metamodelu
	 */
	private List<Table> generateTables() {
		List<Table> tables = new ArrayList<Table>();
		List<String> stringTables = generateStringTables();
		for (String s : stringTables) {
			tables.add(new Table(getTableName(s), generateColumns(s)));
		}
		return tables;
	}

	/**
	 * Wycina ostatnia litere ze stringa
	 *
	 * @param str string z ktorego ma byc wycieta litera
	 * @return string z wycieta litera
	 */
	private String cutLastLetter(String str) {
	    if (str.length() > 0 && str.charAt(str.length()-1)==';') {
	      str = str.substring(0, str.length()-1);
	    }
	    return str;
	}
	
}
