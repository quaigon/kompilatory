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
			obj.setInnanazwa(rs.getString("Innanazwa"));
			obj.setNazwa(rs.getString("Nazwa"));
			obj.setDoub(rs.getInt("Doub"));
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

		String insertStatement = "insert into Tabelka( Id, Innanazwa, Nazwa, Doub) values ( ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getId());
			stmt.setObject(2, obj.getInnanazwa());
			stmt.setObject(3, obj.getNazwa());
			stmt.setObject(4, obj.getDoub());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update (Tabelka obj) {

		String insertStatement = "update Tabelka set Id=?,Innanazwa=?,Nazwa=?,Doub=?  where id=?";

		try {
			PreparedStatement stmt = dbConnection
				.prepareStatement(insertStatement);
			stmt.setObject(1, obj.getInnanazwa());
			stmt.setObject(2, obj.getNazwa());
			stmt.setObject(3, obj.getDoub());
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