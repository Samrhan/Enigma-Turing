package org.carbon.service;

import org.carbon.service.EnigmaService;
import org.carbon.service.EnigmaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnigmaServiceImplTest {

    private EnigmaService enigmaService;

    @BeforeEach
    void setUp() {
        enigmaService = new EnigmaServiceImpl();
    }

    @Nested
    class EncryptionTests {

        @Test
        void encrypt_singleLetter() {
            assertEquals("V", enigmaService.encrypt("A"));
        }

        @Test
        void encrypt_word() {
            assertEquals("OGAY", enigmaService.encrypt("TEST"));
        }

        @Test
        void encrypt_sentenceWithSpaces() {
            assertEquals("OGAY NKAO", enigmaService.encrypt("TEST TEST"));
        }

        @Test
        void encrypt_sentenceWithPunctuation() {
            assertEquals("OGAY NKAO", enigmaService.encrypt("TEST, TEST!"));
        }

        @Test
        void encrypt_lowercase() {
            assertEquals("OGAY", enigmaService.encrypt("test"));
        }
    }

    @Nested
    class DecryptionTests {

        @Test
        void decrypt_singleLetter() {
            assertEquals("A", enigmaService.decrypt("V"));
        }

        @Test
        void decrypt_word() {
            assertEquals("TEST", enigmaService.decrypt("OGAY"));
        }

        @Test
        void decrypt_sentenceWithSpaces() {
            assertEquals("TEST TEST", enigmaService.decrypt("OGAY NKAO"));
        }
    }

    @Nested
    class TuringDecryptTests {

        @Test
        void turingDecrypt_success() {
            String ciphertext = "BJQQ JCGJ GLRT SJIR XPEN YUYF M";
            String crib = "HELL OWOR LD";
            String expectedPlaintext = "HELL OWOR LDXK JQPW CAIY FNFI O";
            assertEquals(expectedPlaintext, enigmaService.turingDecrypt(ciphertext, crib));
        }

        @Test
        void turingDecrypt_noMatch() {
            String ciphertext = "BJQQ JCGJ GLRT SJIR XPEN YUYF M";
            String crib = "INVA LIDC RIB";
            String expectedResult = "Aucune combinaison";
            assertEquals(expectedResult, enigmaService.turingDecrypt(ciphertext, crib));
        }
    }
}