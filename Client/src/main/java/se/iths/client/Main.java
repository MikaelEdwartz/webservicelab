package se.iths.client;

import se.iths.service.Cipher;
import se.iths.service.EncryptStrategy;

import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

public class Main {
    private static final String CAESAR = "caesar";
    private static final String ATBASH = "atbash";
    private static final Scanner scanner = new Scanner(System.in);
    private static String input;

    public static void main(String[] args) {
        printGreeting();
        input = scanner.nextLine();

        while (true) {
            printMenuOptions();
            var encryptStrategy = scanner.nextInt();

            switch (encryptStrategy) {
                case 0 -> System.exit(0);
                case 1 -> encryptMessage(ATBASH, input);
                case 2 -> encryptMessage(CAESAR, input);
                case 9 -> getInput();
                default -> System.out.println("Invalid command!");
            }
        }

    }

    private static void printGreeting() {
        System.out.println("What message would you like to encrypt?");
    }

    private static void printMenuOptions() {
        System.out.println("""
                |================================================================ |
                | Which of the following cipher strategies would you like to use? |
                | 0. To exit program                                              |  
                | 1. Cipher with the Atbash algorithm.                            |
                | 2. Cipher with the Caesar algorithm                             |
                | 9. To cipher another word                                       |
                |================================================================ |
                """);
    }

    private static void getInput() {
        input = scanner.next();
    }

    private static void encryptMessage(String strategy, String input) {
        for (var cipher : getCipher(strategy))
            System.out.println("Your encrypted message is : " + cipher.cipher(input));
    }

    private static List<Cipher> getCipher(String annotationType) {
        return ServiceLoader.load(Cipher.class)
                .stream()
                .filter(c -> c.type().isAnnotationPresent(EncryptStrategy.class)
                        && c.type().getAnnotation(EncryptStrategy.class)
                        .value()
                        .equals(annotationType))
                .map(ServiceLoader.Provider::get)
                .toList();
    }
}
