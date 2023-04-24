package se.iths.provider;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AtbashCipherTest {
    AtbashCipher cipher = new AtbashCipher();

    @Test
    void abcShouldReturnzyx() {
        var result = cipher.cipher("a b c");
        var expectedResult = "z y x";
        assertThat(result).isEqualTo(expectedResult);
    }

}
