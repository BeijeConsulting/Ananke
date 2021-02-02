package it.beije.ananke;
import java.util.*;
import java.io.*;
import java.lang.Math;

public class Account{
	private String firstName;
	private String lastName;
	private String email;
	private int money;
	private Random random = new Random();
	private String pin = ((Integer)random.nextInt(9999)).toString();
	
	public Account(String firstName, String lastName, String email, int money) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.money = money;
		System.out.println("This is your pin: " + getPin() + ". Pay attention");
	}
	
	private boolean login() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insert pin");
		String yourPin = scanner.nextLine();
		scanner.close();
		if(yourPin.equals(pin)){
			System.out.println("Pin correct");
			return true;
		}
		System.out.println("Fuck off");
		return false;
	}
	
	public void withdraw (int cash) {
		boolean log = login();
		if (log){
			if(money >= cash) {
				money -= cash;
				System.out.println("Withdraw succeded");
			} else {
				System.out.println("You are poor");
			}
		}
		return;
	}
	
	public void deposit (int cash) {
		boolean log = login();
		if (log){
			if(cash >= 0) {
				money += cash;
				System.out.println("You deposit " + cash + " money");
			} else {
				System.out.println("You are poor");
			}
		}
		return;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMoney() {
		boolean log = login();
		if(log) {
			return money;
		}
		return -1;
	}

	private String scanMail() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci la tua email");
		String email = scanner.nextLine();
		scanner.close();
		return email;
	}
	
	public String getPin() {
		if(scanMail().equals(email)) {
			return pin;
		}
		return "Wrong email. Fuck off";
	}
	
	public void setPin(String newPin) {
		if(scanMail().equals(email)) {
			this.pin = newPin;
		} else {
			System.out.println("Wrong email. Fuck off");
		}
		return;
	}

	
}
	