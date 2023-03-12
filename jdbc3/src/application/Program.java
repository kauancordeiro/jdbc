package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO  seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "Values "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1, "Cristofer J.");
			st.setString(2, "cristofer@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("25/07/1986").getTime()));
			st.setDouble(4,3000.0);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! id = " + id);
				}
				
			}else {
				System.out.println("No rows affected!");
			}
					
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		catch (ParseException e ) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
