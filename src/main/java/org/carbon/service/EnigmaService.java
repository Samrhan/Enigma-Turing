package org.carbon.service;

import java.util.List;

public interface EnigmaService {
    String encrypt(String plaintext);
    String decrypt(String ciphertext);
    List<String> getRotorWirings();
    String getReflectorWiring();
    List<Integer> getRotorRotations();
    void setRotorPositions(List<Integer> positions);
    String turingDecrypt(String ciphertext, String crib);

}

