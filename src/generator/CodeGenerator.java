package generator;

import generator.model.MetaModel;

public class CodeGenerator {

	private static final String filePath = "C:\\dev\\Java\\workspace\\CodeGenerator\\baseExample.txt";

	public static void main(String[] args) {
		FileMetaModelCreator mmc = new FileMetaModelCreator(filePath);
		MetaModel mm = mmc.create();
		H2ColumnTypeMapper mapper = new H2ColumnTypeMapper();
		QueryBuilder qb = new QueryBuilder(mm);
		
		System.out.println(qb.buildUpdateStatement("table2"));

		SQLFileGenerator fg = new SQLFileGenerator(mm, mapper);
		ClassCodeGenerator cg = new ClassCodeGenerator(mm);
		DAOCodeGenerator dcg = new DAOCodeGenerator(mm);
		
		fg.generateFile("sql.sql");
		cg.generateTableFiles();
		dcg.generateDaoFiles();
		

	}
}
