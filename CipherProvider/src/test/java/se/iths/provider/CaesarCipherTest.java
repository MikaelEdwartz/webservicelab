package se.iths.provider;

import org.junit.jupiter.api.Test;
import se.iths.service.Cipher;

import static org.assertj.core.api.Assertions.assertThat;
class CaesarCipherTest {
    Cipher cipher = new CaesarCipher();

    @Test
    void inputStringABCShouldShiftLettersToHIK(){
        var result = cipher.cipher("ab c");
        var expected = "hi j";

        assertThat(result).isEqualTo(expected);

    }

}
