package com.ssn.practica.work.Store;

import java.util.Scanner;

public class BasicOperations {

	private Scanner scan = new Scanner(System.in);

	public String stringGetter(String input) {
		System.out.print(input);
		return scan.nextLine();
	}

	public int intGetter(String input) {
		System.out.print(input);
		return Integer.parseInt(scan.nextLine());
	}
}
