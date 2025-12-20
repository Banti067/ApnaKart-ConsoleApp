package com.apnakart.util;

import java.util.Scanner;

public class ScannerUtil {
    
	private static final Scanner scanner = new Scanner(System.in);

    private ScannerUtil() {}

    public static Scanner getScanner() {
        return scanner;
    }
}
