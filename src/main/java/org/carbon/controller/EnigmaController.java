package org.carbon.controller;

import org.carbon.service.EnigmaService;
import org.carbon.view.EnigmaView;

import java.util.List;

public class EnigmaController {
    private final EnigmaService enigmaService;
    private final EnigmaView view;

    public EnigmaController(EnigmaService enigmaService, EnigmaView view) {
        this.enigmaService = enigmaService;
        this.view = view;
        initializeView();
    }

    private void initializeView() {
        view.initializeRotorsAndReflector(enigmaService.getRotorWirings(), enigmaService.getReflectorWiring());
    }

    public void encrypt(String plaintext) {
        String ciphertext = enigmaService.encrypt(plaintext);
        view.setOutputText(ciphertext);
        updateRotorPositions();
    }

    public void decrypt(String ciphertext) {
        String plaintext = enigmaService.decrypt(ciphertext);
        view.setInputText(plaintext);
        updateRotorPositions();
    }

    public void turingDecrypt(String ciphertext, String crib) {
        String plaintext = enigmaService.turingDecrypt(ciphertext, crib);
        view.setInputText(plaintext);
        updateRotorPositions();
    }

    private void updateRotorPositions() {
        List<Integer> rotations = enigmaService.getRotorRotations();
        view.updateRotorPositions(rotations);
    }
}