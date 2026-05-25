package it.uniroma3.diadia;
import java.util.Scanner;
public class IOConsole implements IO{

	private Scanner scanner;

	public IOConsole(Scanner scanner) {
		this.scanner = scanner;
	}


	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	public String leggiRiga() {
		return this.scanner.nextLine();
	}
}
