package generator;

import generator.model.Column;
import generator.model.MetaModel;
import generator.model.Table;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Klasa generujaca pliki pozwalajace na obsluge bazy danych na podstawie Meta Modelu
 */
public class DAOCodeGenerator {

	/** Meta model bazy danych */
	private MetaModel metaModel;
	
	/** Kreator zapytan do bazy danych */
	private QueryBuilder qb;

	/**
	 * Konstruktor generatora
	 *
	 * @param metaModel  meta model bazy danych ktorej kod chcemy wygenerowac
	 */
	public DAOCodeGenerator(MetaModel metaModel) {
		this.metaModel = metaModel;
		this.qb = new QueryBuilder(metaModel);
	}

	/**
	 * Generuje metoda ktora pozwala na wybieranie z bazy danych obiektu o danym id
	 *
	 * @param tableName nazwa tabelki z ktorej chcemy pobrac zawatosc
	 * @return kod metody ktora pozwala na wybieranie z bazy danych obiektu o danym id
	 */
	private String generateSelectCode(String tableName) {
		StringBuilder selectCode = new StringBuilder();

		selectCode
				.append("\tpublic ")
				.append(rename(tableName))
				.append(" selectById (long id) {")
				.append(System.lineSeparator())
				.append("\t\t")
				.append(rename(tableName))
				.append(" obj = new ")
				.append(rename(tableName))
				.append("();")
				.append(System.lineSeparator())
				.append("\t\tPreparedStatement stmt = null;")
				.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append("\t\tString selectStatement = \"")
				.append(qb.buildSelectByIdStatement(tableName))
				.append("\";")
				.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append("\t\ttry {")
				.append(System.lineSeparator())
				.append("\t\t\tstmt = dbConnection.prepareStatement(selectStatement);")
				.append(System.lineSeparator())
				.append("\t\t\tstmt.setLong(1,id);")
				.append(System.lineSeparator())
				.append("\t\t\tResultSet rs = stmt.executeQuery();")
				.append(System.lineSeparator()).append("\t\t\trs.next();")
				.append(System.lineSeparator());
		List<Column> columnList = metaModel.getTable(tableName).getColumns();

		for (Column c : columnList) {
			selectCode.append("\t\t\tobj.set").append(rename(c.getName()))
					.append("(rs.get").append(c.getType().getJavaType())
					.append("(\"").append(c.getName()).append("\"));")
					.append(System.lineSeparator());
		}

		selectCode.append("\t\t} catch (SQLException e) {")
				.append(System.lineSeparator())
				.append("\t\t\te.printStackTrace();")
				.append(System.lineSeparator()).append("\t\t} finally {")
				.append(System.lineSeparator()).append("\t\t\ttry {")
				.append(System.lineSeparator()).append("\t\t\t\tstmt.close();")
				.append(System.lineSeparator())
				.append("\t\t } catch (SQLException e) {")
				.append(System.lineSeparator())
				.append("\t\t\t\te.printStackTrace();")
				.append(System.lineSeparator()).append("\t\t\t }")
				.append(System.lineSeparator()).append("\t\t }")
				.append(System.lineSeparator()).append("\t\treturn obj;")
				.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator());

		return selectCode.toString();
	}

	/**
	 * Generuje metoda ktora pozwala na wstawianie obiektu do bazy danych
	 *
	 * @param tableName nazwa tabelki do ktorej chcemy wstawic obiekt
	 * @return kod metody ktora pozwala na wstawienie obiektu do danej tabelki
	 */
	private String generateInsertCode(String tableName) {
		StringBuilder insertCode = new StringBuilder();

		int i = 1;

		insertCode.append("\tpublic void").append(" insert (")
				.append(rename(tableName)).append(" obj) {")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\t\tString insertStatement = \"")
				.append(qb.buildCreateStatement(tableName)).append("\";")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\t\ttry {").append(System.lineSeparator())
				.append("\t\t\tPreparedStatement stmt = dbConnection")
				.append(System.lineSeparator())
				.append("\t\t\t\t.prepareStatement(insertStatement);")
				.append(System.lineSeparator());

		List<Column> columnList = metaModel.getTable(tableName).getColumns();

		for (Column c : columnList) {
			insertCode.append("\t\t\tstmt.setObject(").append(i)
					.append(", obj.get").append(rename(c.getName()))
					.append("()").append(");").append(System.lineSeparator());
			i++;
		}

		insertCode.append("\t\t\tstmt.executeUpdate();")
				.append(System.lineSeparator()).append("\t\t}")
				.append(" catch (SQLException e) {")
				.append(System.lineSeparator())
				.append("\t\t\te.printStackTrace();")
				.append(System.lineSeparator()).append("\t\t}")
				.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator());

		return insertCode.toString();
	}

	/**
	 * Generuje metode pozwalajaca na zaktualizowanie obiektu w bazie danych
	 *
	 * @param tableName nazwa tabelki ktora chcemy zaktualizowac
	 * @return kod metody ktora pozwala na zaktualizowanie obietku w bazie danych
	 */
	private String generateUpdateCode(String tableName) {
		StringBuilder updateCode = new StringBuilder();

		updateCode.append("\tpublic void").append(" update (")
				.append(rename(tableName)).append(" obj) {")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\t\tString insertStatement = \"")
				.append(qb.buildUpdateStatement(tableName)).append("\";")
				.append(System.lineSeparator()).append(System.lineSeparator())

				.append("\t\ttry {").append(System.lineSeparator())
				.append("\t\t\tPreparedStatement stmt = dbConnection")
				.append(System.lineSeparator())
				.append("\t\t\t\t.prepareStatement(insertStatement);")
				.append(System.lineSeparator());

		List<Column> columnList = metaModel.getTable(tableName).getColumns();


		for (int i = 1; i < columnList.size(); i++) {
			updateCode.append("\t\t\tstmt.setObject(").append(i)
					.append(", obj.get")
					.append(rename(columnList.get(i).getName())).append("()")
					.append(");").append(System.lineSeparator());
		}

		updateCode.append("\t\t\tstmt.setObject(").append(columnList.size())
				.append(", obj.getId());").append(System.lineSeparator())
				.append("\t\t\tstmt.executeUpdate();")
				.append(System.lineSeparator()).append("\t\t}")
				.append(" catch (SQLException e) {")
				.append(System.lineSeparator())
				.append("\t\t\te.printStackTrace();")
				.append(System.lineSeparator()).append("\t\t}")
				.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator());

		return updateCode.toString();
	}

	/**
	 * Generuje metode ktora pozwala na usuniecie obiektu z bazy danych
	 *
	 * @param tableName nazwa tabelki z ktorej chcemy usunac obiekt
	 * @return kod metody ktora pozwala na usuniecie obiektu z bazy danych
	 */
	private String generateDeleteCode(String tableName) {
		StringBuilder deleteCode = new StringBuilder();

		deleteCode.append("\tpublic void").append(" delete (")
				.append(rename(tableName)).append(" obj) {")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\t\tString deleteStatement = \"")
				.append(qb.buildDeleteStatement(tableName)).append("\";")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\t\ttry {").append(System.lineSeparator())
				.append("\t\t\tPreparedStatement stmt = dbConnection")
				.append(System.lineSeparator())
				.append("\t\t\t\t.prepareStatement(deleteStatement);")
				.append(System.lineSeparator())
				.append("\t\t\tstmt.setLong(1, obj.getId());")
				.append(System.lineSeparator())
				.append("\t\t\tstmt.executeUpdate();")
				.append(System.lineSeparator()).append("\t\t}")
				.append(" catch (SQLException e) {")
				.append(System.lineSeparator())
				.append("\t\t\te.printStackTrace();")
				.append(System.lineSeparator()).append("\t\t}")
				.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator());

		return deleteCode.toString();
	}

	/**
	 * Generuje importy potrzebne do obslugi bazy danych
	 *
	 * @return importy potrzebny do obslugi bazy danych
	 */
	private String generateImportsCode() {
		StringBuilder importsCode = new StringBuilder();

		importsCode.append("import java.sql.Connection;")
				.append(System.lineSeparator())
				.append("import java.sql.DriverManager;")
				.append(System.lineSeparator())
				.append("import java.sql.PreparedStatement;")
				.append(System.lineSeparator())
				.append("import java.sql.ResultSet;")
				.append(System.lineSeparator())
				.append("import java.sql.SQLException;")
				.append(System.lineSeparator()).append(System.lineSeparator());

		return importsCode.toString();
	}

	/**
	 * Generuje kod klasy ktora pozwala na obsluge danej tabelki w bazie danych
	 *
	 * @param tableName nazwa tabelki ktora chcemy obsluzyc
	 * @return kod klasy ktora pozwala na obsluge danej tabelki w bazie danych
	 */
	private String generateDaoCode(String tableName) {
		StringBuilder daoCode = new StringBuilder();

		daoCode.append(generateImportsCode()).append("public class ")
				.append(rename(tableName)).append("DAOCode {")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\t private Connection dbConnection;")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append("\t public ").append(rename(tableName))
				.append("DAOCode (Connection dbConnection) {")
				.append(System.lineSeparator())
				.append("\t\tthis.dbConnection = dbConnection;")
				.append(System.lineSeparator()).append("\t}")
				.append(System.lineSeparator()).append(System.lineSeparator())
				.append(generateSelectCode(tableName))
				.append(generateInsertCode(tableName))
				.append(generateUpdateCode(tableName))
				.append(generateDeleteCode(tableName)).append("}");

		return daoCode.toString();
	}

	/**
	 * Tworzy pliki .java pozwalajace na obsluge danych
	 */
	public void generateDaoFiles() {
		List<Table> tables = metaModel.getTables();

		for (Table t : tables) {
			try {
				PrintWriter writer = new PrintWriter("DAO classes\\"
						+ rename(t.getTableName()) + "DAOCode.java");
				writer.append(generateDaoCode(t.getTableName()));
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

}
