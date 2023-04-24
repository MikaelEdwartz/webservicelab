package se.iths.client;

import se.iths.provider.CaesarCipher;
import se.iths.provider.AtbashCipher;
import se.iths.service.Cipher;

public class Main {

    public static void main(String[] args) {
        Cipher cipher = new CaesarCipher();
        Cipher cipher1 = new AtbashCipher();
        System.out.println(cipher.cipher("What are you doing"));
        System.out.println(cipher1.cipher("what are you doing"));
    }
}
