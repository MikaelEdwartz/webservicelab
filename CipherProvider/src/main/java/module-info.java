import se.iths.provider.AtbashCipher;
import se.iths.provider.CaesarCipher;
import se.iths.service.Cipher;

module se.iths.provider {
    exports se.iths.provider;
    requires se.iths.service;
    provides Cipher with AtbashCipher, CaesarCipher;
}
