import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TabelkaDAOCode {

	 private Connection dbConnection;

	 public TabelkaDAOCode (Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Tabelka selectById (long id) {
		Tabelka obj = new Tabelka();
		PreparedStatement stmt = null;

		String selectStatement = "select * from Tabelka where id = ?";

		try {
			stmt = dbConnection.prepareStatement(selectStatement);
			stmt.setLong(1,id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			obj.setId(rs.getInt("Id"));
			obj.setPierwszydouble(rs.getDouble("Pierwszydouble"));
			obj.setPierwszystring(rs.getString("Pierwszystring"));
			obj.setDrugistring(rs.getString("Drugistring"));
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

	public void insert (Tabelka obj) {

		String insertStatement = "insert into Tabelka( Id, Pierwszydouble, Pierwszystring, Drugistring) values ( ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getId());
			stmt.setObject(2, obj.getPierwszydouble());
			stmt.setObject(3, obj.getPierwszystring());
			stmt.setObject(4, obj.getDrugistring());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update (Tabelka obj) {

		String insertStatement = "update Tabelka set Id=?,Pierwszydouble=?,Pierwszystring=?,Drugistring=?  where id=?";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getPierwszydouble());
			stmt.setObject(2, obj.getPierwszystring());
			stmt.setObject(3, obj.getDrugistring());
			stmt.setObject(4, obj.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete (Tabelka obj) {

		String deleteStatement = "delete from Tabelka where id=?";

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