package it.beije.ananke;

public class Bank{
	public static void main(String[] args) {
		Account account = new Account("Stefano", "Bestetti", "stefano.bestett94@gmail.com", 1000);
		account.deposit(1000);
		account.deposit(1000);
		account.withdraw(2500);
	}
}