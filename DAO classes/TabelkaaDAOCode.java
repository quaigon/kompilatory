import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TabelkaaDAOCode {

	 private Connection dbConnection;

	 public TabelkaaDAOCode (Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Tabelkaa selectById (long id) {
		Tabelkaa obj = new Tabelkaa();
		PreparedStatement stmt = null;

		String selectStatement = "select * from Tabelkaa where id = ?";

		try {
			stmt = dbConnection.prepareStatement(selectStatement);
			stmt.setLong(1,id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			obj.setId(rs.getInt("Id"));
			obj.setNazwy(rs.getInt("Nazwy"));
			obj.setJakisdoubel(rs.getDouble("Jakisdoubel"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
		 } catch (SQLException e) {
				e.printStackTrace();
			 }
		 }
		return obj;
	}

	public void insert (Tabelkaa obj) {

		String insertStatement = "insert into Tabelkaa( Id, Nazwy, Jakisdoubel) values ( ?, ?, ?)";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getId());
			stmt.setObject(2, obj.getNazwy());
			stmt.setObject(3, obj.getJakisdoubel());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update (Tabelkaa obj) {

		String insertStatement = "update Tabelkaa set Id=?,Nazwy=?,Jakisdoubel=?  where id=?";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getNazwy());
			stmt.setObject(2, obj.getJakisdoubel());
			stmt.setObject(3, obj.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete (Tabelkaa obj) {

		String deleteStatement = "delete from Tabelkaa where id=?";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(deleteStatement);
			stmt.setLong(1, obj.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}