package generator;

import generator.model.Column;
import generator.model.MetaModel;
import generator.model.Table;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Klasa genererujaca pliki .java tabelek na podstawie metaModelu
 */
public class ClassCodeGenerator {

	/** MetaModel bazy danych ktorej maja zostac wygenerowane pliki */
	MetaModel metaModel;

	/**
	 * Konstruktor generatora plikow .java
	 * 
	 * @param metaModel
	 *            MetaModel bazy danych ktorej maja zostac wygenerowane pliki
	 */
	public ClassCodeGenerator(MetaModel metaModel) {
		this.metaModel = metaModel;
	}

	/**
	 * Generuje nazwy pol klasy
	 * 
	 * @param table
	 *            tabelka ktorej pola maja zostac wygenerowana
	 * @return pola klasy
	 */
	private String generateFields(Table table) {
		StringBuilder fields = new StringBuilder();
		List<Column> columnList = table.getColumns();

		for (Column c : columnList) {
			fields.append("\t")
					.append(rename(c.getType().toString()) + " " + c.getName()
							+ ";").append(System.lineSeparator());
		}
		fields.append(System.lineSeparator());
		return fields.toString();
	}

	/**
	 * Generuje konstruktor klasy
	 * 
	 * @param tabelka
	 *            ktorej konstruktor ma zostac wygenerowany
	 * @return konstruktor klasy
	 */
	private String generateConstructor(Table table) {
		StringBuilder constructor = new StringBuilder();
		List<Column> columnList = table.getColumns();

		constructor.append("\tpublic " + rename(table.getTableName()) + "(){")
				.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\tpublic " + rename(table.getTableName()) + "(");
		for (int i = 0; i < columnList.size(); i++) {
			if (i < columnList.size() - 1)
				constructor.append(rename(columnList.get(i).getType()
						.toString())
						+ " " + columnList.get(i).getName() + ", ");
			else
				constructor.append(
						rename(columnList.get(i).getType().toString()) + " "
								+ columnList.get(i).getName() + ") {").append(
						System.lineSeparator());
		}
		for (Column c : columnList) {
			constructor.append(
					"\t\tthis." + c.getName() + " = " + c.getName() + ";")
					.append(System.lineSeparator());
		}
		constructor.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator());

		return constructor.toString();
	}

	/**
	 * Tworzy setter danej kolumny
	 * 
	 * @param column
	 *            kolumna ktorej setter ma zostac utworzony
	 * @return setter danej kolumny
	 */
	private String generateSetter(Column column) {
		StringBuilder setter = new StringBuilder();

		setter.append("\tpublic void set")
				.append(column.getName().substring(0, 1).toUpperCase())
				.append(column.getName().substring(1)).append("(")
				.append(rename(column.getType().toString()) + " ")
				.append(column.getName()).append(") {")
				.append(System.lineSeparator()).append("\t\tthis.")
				.append(column.getName()).append(" = ")
				.append(column.getName() + ";").append(System.lineSeparator())
				.append("\t}").append(System.lineSeparator())
				.append(System.lineSeparator());

		return setter.toString();
	}

	/**
	 * Tworzy getter danej kolumny
	 * 
	 * @param column
	 *            kolumna ktorej getter ma zostac utworzony
	 * @return getter danej kolumny
	 */
	private String generateGetter(Column column) {
		StringBuilder getter = new StringBuilder();

		getter.append("\tpublic ").append(rename(column.getType().toString()))
				.append(" get")
				.append(column.getName().substring(0, 1).toUpperCase())
				.append(column.getName().substring(1)).append("(")
				.append(") {").append(System.lineSeparator())
				.append("\t\treturn ").append(column.getName() + ";")
				.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator());

		return getter.toString();
	}

	/**
	 * Generuje caly kod klasy danej tabelki
	 * 
	 * @param table
	 *            tabelka ktorej kod ma zostac wygenerowany
	 * @return kod klasy opisujacy dana tabelke
	 */
	private String generateClass(Table table) {
		StringBuilder tableClass = new StringBuilder();
		tableClass.append("public class ").append(rename(table.getTableName()))
				.append(" {").append(System.lineSeparator())
				.append(System.lineSeparator()).append(generateFields(table))
				.append(generateConstructor(table));

		List<Column> columns = table.getColumns();

		for (Column c : columns) {
			tableClass.append(generateGetter(c));
			tableClass.append(generateSetter(c));
		}
		tableClass.append(System.lineSeparator()).append("}");

		return tableClass.toString();
	}

	/**
	 * Funkcja zmieniajaca pierwsza litere stringu na wielka
	 * 
	 * @param str
	 *            String ktory ma zostac zmieniony
	 * @return zmieniony string
	 */
	private String rename(String str) {
		String strr = str.toLowerCase();

		strr = strr.substring(0, 1).toUpperCase() + strr.substring(1);

		return strr;
	}

	/**
	 * Generuje pliki na podstawie podanego metaModelu
	 */
	public void generateTableFiles() {
		for (Table t : metaModel.getTables()) {
			try {
				PrintWriter writer = new PrintWriter("Classes\\"
						+ rename(t.getTableName()) + ".java");
				System.out.println("Classes\\" + t.getTableName() + ".java");
				writer.append(generateClass(t));
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
