import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugabelkaDAOCode {

	 private Connection dbConnection;

	 public DrugabelkaDAOCode (Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Drugabelka selectById (long id) {
		Drugabelka obj = new Drugabelka();
		PreparedStatement stmt = null;

		String selectStatement = "select * from Drugabelka where id = ?";

		try {
			stmt = dbConnection.prepareStatement(selectStatement);
			stmt.setLong(1,id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			obj.setId(rs.getInt("Id"));
			obj.setTrzecitring(rs.getString("Trzecitring"));
			obj.setCzwartytring(rs.getString("Czwartytring"));
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

	public void insert (Drugabelka obj) {

		String insertStatement = "insert into Drugabelka( Id, Trzecitring, Czwartytring) values ( ?, ?, ?)";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getId());
			stmt.setObject(2, obj.getTrzecitring());
			stmt.setObject(3, obj.getCzwartytring());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update (Drugabelka obj) {

		String insertStatement = "update Drugabelka set Id=?,Trzecitring=?,Czwartytring=?  where id=?";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getTrzecitring());
			stmt.setObject(2, obj.getCzwartytring());
			stmt.setObject(3, obj.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete (Drugabelka obj) {

		String deleteStatement = "delete from Drugabelka where id=?";

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