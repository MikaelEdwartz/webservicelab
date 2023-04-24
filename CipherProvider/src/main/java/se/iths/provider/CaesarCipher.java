package se.iths.provider;

import se.iths.service.Cipher;

public class CaesarCipher implements Cipher {
    private static final int SECRET_SHIFTER = 7;
    private static final int LETTERS_IN_ALPHABET = 26;

    @Override
    public String cipher(String stringToCipher) {
        var cipheredString = new StringBuilder();

        for (var letter : stringToCipher.toLowerCase().toCharArray())
            cipherLetter(cipheredString, letter);

        return cipheredString.toString();

    }

    private static void cipherLetter(StringBuilder cipheredString, char letter) {
        if (isLetter(letter))
            appendModifiedLetter(cipheredString, letter);
        else
            cipheredString.append(letter);
    }

    private static void appendModifiedLetter(StringBuilder cipheredString, char letter) {
        var originalPosition = letter - 'a';
        var newPosition = (originalPosition + SECRET_SHIFTER) % LETTERS_IN_ALPHABET;
        var newChar = (char) ('a' + newPosition);
        cipheredString.append(newChar);
    }

    private static boolean isLetter(char letter) {
        return letter != ' ';
    }
}
