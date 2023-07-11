package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_tema {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "vic1", "vic1")) {
			if (conn != null) {
				System.out.println("Connection successful");
			}

			Statement statement = conn.createStatement();
			statement.executeUpdate("insert into articol values(1, 'pantofi')");
			statement.executeUpdate("insert into articol values(2, 'tricou')");

			statement.executeUpdate("insert into magazin values(1, 'Tom Ford')");
			statement.executeUpdate("insert into magazin values(2, 'Dior')");

			statement.executeUpdate("insert into compari values(1, 2, 457)");
			statement.executeUpdate("insert into compari values(1, 1, 1500)");
			statement.executeUpdate("insert into compari values(2, 1, 340)");
			statement.executeUpdate("insert into compari values(2, 2, 790)");

			ResultSet resultSet = statement.executeQuery("select articol.nume, magazin.nume from articol "
					+ "JOIN compari ON articol.id = compari.articol_id "
					+ "JOIN magazin ON magazin.id = compari.magazin_id "
					+ "WHERE compari.pret = (select min(compari.pret) from compari where articol.id = compari.articol_id) "
					+ "order by magazin.nume ");

			while (resultSet.next()) {
				System.out.println(//
						resultSet.getString(1) + " " + //
								resultSet.getString(2) + " " //
				);
			}

			resultSet.close();
		}

	}
}
