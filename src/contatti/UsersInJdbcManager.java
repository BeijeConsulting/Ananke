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
	public boolean setUser(User user) throws IOException, SQLException {
		if( isUserAlreadyExist(user.getEmail()) ) {
			return false;
		}
		else {
		String query = "INSERT INTO contatti (firstName,lastName,email,phoneNumber) VALUES (?,?,?,?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPhoneNumber());
			if(preparedStatement.execute()) {
				System.out.println("user added successfully");
			}
			preparedStatement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		}
	}
	@Override
	public User getUser(String email) {
		User user = null;
		String query = "SELECT * FROM contatti WHERE email =?;";
		try {
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				if(resultSet.getString("email").equals(email)) {
					user = new User(resultSet.getInt("id"), resultSet.getString("firstName"),resultSet.getString("lastName")
							,resultSet.getString("email"),resultSet.getString("phoneNumber") );
					preparedStatement.close();
					return user;
				}
			}
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
					ResultSet resultSet = statement.executeQuery(query);
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
			int row = preparedStatement.executeUpdate();
            System.out.println(row +" rows updated");
			preparedStatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	
	private User mapResultSetToObject(ResultSet resultSet) throws SQLException {
		int id;
		String firstName;
		String lastName;
		String email;
		String phoneNumber;
		
		id = resultSet.getInt("id");
		firstName = resultSet.getString("firstName");
		lastName = resultSet.getString("lastName");
		email = resultSet.getString("email");
		phoneNumber = resultSet.getString("phoneNumber");
		User user = new User(id,firstName,lastName,email,phoneNumber);
		return user;
	}
	boolean isUserAlreadyExist(String email) {
	  String query = "SELECT * FROM contatti WHERE email = ?;";
	try {
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1, email);
		ResultSet resultSet= preparedStatement.executeQuery();
		while(resultSet.next()) {
			if(resultSet.getString("email").equals(email)) {
      		  preparedStatement.close();
      		  return true;
      	  }
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return false;
		
	}

}
