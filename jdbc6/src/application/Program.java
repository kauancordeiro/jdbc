package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
	
		
		Connection conn = null;	
		Statement st = null;
		
		
	
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false); // não confirmar alterações automaticamente, precisa de confirmação
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			
			
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			
			conn.commit(); // confirmação
					
			System.out.println("Rows1 = " + rows1);
			System.out.println("Rows1 = " + rows2);
		}
		catch (SQLException e ) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! error: " + e.getMessage());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new DbException("Error trying to roll back! error: " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
