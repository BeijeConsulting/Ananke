package contatti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersInJdbcManager implements UserManager{

	protected Connection conn;
	Statement statement;
	ResultSet resultSet;
	
	public UsersInJdbcManager() {
		getConnection();
	}
	protected void getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/faheemDB";
			conn = DriverManager.getConnection(url, "root","");
		} catch (SQLException e) {
			throw new Error("Problem", e);
		} finally {
			try {
				if (conn == null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	@Override
	public void setUser(User user) throws IOException, SQLException {
		String query = mapObjectToQueryInsert(user);
		try {
			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public User getUser(String email) {
		//String query = "SELECT * FROM " + "contatti" + " WHERE email =" + email;
		String query = "SELECT * FROM contatti WHERE email = ?";
		try {
			
			statement = conn.createStatement();
			
			resultSet = statement.executeQuery(query);
				return mapResultSetToObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
				String query = "SELECT * FROM contatti ";
				try {
					Statement statement = conn.createStatement();
					resultSet = statement.executeQuery(query);
					while (resultSet.next()) {
						list.add(mapResultSetToObject(resultSet)) ;
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
	}

	
	@Override
	public void removeUser(String email) {
		String query = "DELETE FROM contatti WHERE email =?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, email);
			boolean row = preparedStatement.execute();
            // rows affected
			if(row)
            System.out.println("user removed");
			preparedStatement.close();
			
			//Statement statement = conn.createStatement();
			//statement.executeUpdate(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	
	private User mapResultSetToObject(ResultSet resultSet) throws SQLException {
		
		String firstName;
		String lastName;
		String email;
		String phoneNumber;
		
		firstName = resultSet.getString("firstName");
		lastName = resultSet.getString("lastName");
		email = resultSet.getString("email");
		phoneNumber = resultSet.getString("phoneNumber");
		User user = new User(firstName,lastName,email,phoneNumber);
		return user;
	}
	private String mapObjectToQueryInsert(User user) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String phoneNumber = user.getPhoneNumber();
		String query = "INSERT INTO contatti " + "VALUES ('" + firstName + "', '" + lastName + "','"+email+"','"+phoneNumber+"')";
		return query;
	}
	
	

}
