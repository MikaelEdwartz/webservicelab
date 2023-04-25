package se.iths.client;

import se.iths.service.Cipher;
import se.iths.service.EncryptStrategy;

import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String input;

    public static void main(String[] args) {
        printGreeting();
        input = scanner.nextLine();
        initializeLoopAndEncryptInput(printCipher());
    }

    private static void initializeLoopAndEncryptInput(List<String> list) {
        while (true) {
            printMenuOptions();
            var encryptStrategy = scanner.nextLine();
            executeCommand(list, encryptStrategy);
        }
    }

    private static void executeCommand(List<String> list, String encryptStrategy) {
        if (encryptStrategy.equals("e"))
            System.exit(0);
        else if (Integer.parseInt(encryptStrategy) == 0)
            getInput();
        else
            encryptMessage(list.get(Integer.parseInt(encryptStrategy) - 1), input);
    }

    private static void printGreeting() {
        System.out.println("What message would you like to encrypt?");
    }

    private static void getPrintln() {
        System.out.println("""
                |================================================================
                | Which of the following cipher strategies would you like to use?
                | e. To exit program
                | 0. To cipher another word""");
    }

    private static void printAnnotations() {
        var annotations = printCipher();
        for (int i = 0; i < annotations.size(); i++)
            System.out.println("| " + (i + 1) + ". Cipher with the " + annotations.get(i) + " algorithm");

    }

    private static void printMenuOptions() {
        getPrintln();
        printAnnotations();
        System.out.println("""                                     
                |================================================================
                """);

    }

    private static void getInput() {

        input = scanner.next();
    }

    private static void encryptMessage(String strategy, String input) {
        for (var cipher : getCipher(strategy))
            System.out.println("Your encrypted message is : " + cipher.cipher(input));
    }

    private static List<String> printCipher() {

        return ServiceLoader.load(Cipher.class)
                .stream()
                .filter(c -> c.type().isAnnotationPresent(EncryptStrategy.class))
                .map(c -> c.type().getAnnotation(EncryptStrategy.class).value())
                .toList();
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
