package it.beije.ananke.database;

public class Config {
	private static final String DB_USER = "root";
	private static final String DB_PWRD = "Beije04";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";
	
	
	public static String getDbPwrd() {
		return DB_PWRD;
	}


	public static String getDbUser() {
		return DB_USER;
	}


	public static String getDbUrl() {
		return DB_URL;
	}
	

}
