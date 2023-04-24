package se.iths.provider;

import se.iths.service.Cipher;

import java.util.HashMap;
import java.util.Map;

public class AtbashCipher implements Cipher {
    private final Map<Character, Character> table = new HashMap<>();

    @Override
    public String cipher(String stringToCipher) {
        var alphabet = new StringBuilder();
        var cipher = new StringBuilder();
        var alphabetReversed = new StringBuilder();

        getAlphabetAsString(alphabet);
        getReversedAlphabetAsString(alphabet, alphabetReversed);

        matchCharactersInMap(alphabet, alphabetReversed);

        for (char letter : stringToCipher.toLowerCase().toCharArray())
            appendCipheredString(cipher, letter);

        return cipher.toString();
    }

    private void appendCipheredString(StringBuilder cipher, char letter) {
        if (letter != ' ')
            cipher.append(Character.toLowerCase(table.get(letter)));
        else
            cipher.append(' ');
    }

    private void matchCharactersInMap(StringBuilder alphabet, StringBuilder alphabetReversed) {
        for (int i = 0; i < alphabet.length(); i++)
            table.put(alphabet.charAt(i), alphabetReversed.charAt(i));
    }

    private static void getReversedAlphabetAsString(StringBuilder alphabet, StringBuilder alphabetReversed) {
        alphabetReversed.append(alphabet).reverse();
    }

    private static void getAlphabetAsString(StringBuilder alphabet) {
        for (char i = 'a'; i < 'a' + 26; i++)
            alphabet.append(i);
    }
}
