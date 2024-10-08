package org.carbon.service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EnigmaServiceImpl implements EnigmaService {
    private final List<Rotor> rotors;
    private final Reflector reflector;

    public EnigmaServiceImpl() {
        rotors = new ArrayList<>();
        rotors.add(new Rotor(listFromString("EKMFLGDQVZNTOWYHXUSPAIBRCJ"), 1));
        rotors.add(new Rotor(listFromString("AJDKSIRUXBLHWTMCQGZNPYFVOE"), 1));
        reflector = new Reflector(listFromString("YRUHQSLDPXNGOKMIEBFZCWVJAT"));
    }

    private static List<Character> listFromString(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    @Override
    public String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase();
        var ciphertext = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                ciphertext.append(encryptChar(c));
                rotateRotors();
            } else {
                ciphertext.append(c);
            }
        }
        return formatCipher(ciphertext.toString());
    }

    private char encryptChar(char c) {
        int inputIndex = c - 'A';

        // Pass through rotors forward
        for (Rotor rotor : rotors) {
            inputIndex = rotorForward(rotor, inputIndex);
        }

        inputIndex = reflectorReflect(reflector, inputIndex);

        // Pass through rotors backward
        for (int i = rotors.size() - 1; i >= 0; i--) {
            inputIndex = rotorBackward(rotors.get(i), inputIndex);
        }

        return (char) (inputIndex + 'A');
    }

    private void rotateRotors() {
        for (var rotor : rotors) {
            rotorRotate(rotor);
            if (rotor.getRotation() != 0) {
                break;
            }
        }
    }

    private int rotorForward(Rotor rotor, int input) {
        int shiftedIndex = (input + rotor.getRotation()) % 26;
        char outputChar = rotor.getWiring().get(shiftedIndex);
        return outputChar - 'A';
    }

    private int rotorBackward(Rotor rotor, int input) {
        char inputChar = (char) (input + 'A');
        int index = rotor.getWiring().indexOf(inputChar);
        return (index - rotor.getRotation() + 26) % 26;
    }

    private void rotorRotate(Rotor rotor) {
        rotor.setRotation((rotor.getRotation() + 1) % 26);
    }

    private int reflectorReflect(Reflector reflector, int input) {
        char outputChar = reflector.getWiring().get(input);
        return outputChar - 'A';
    }

    private static String removeSpecialCharacters(String ciphertext) {
        return ciphertext.replaceAll("[^A-Z]", "");
    }

    private static String formatCipher(String ciphertext) {
        String formattedCiphertext = removeSpecialCharacters(ciphertext);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < formattedCiphertext.length(); i++) {
            result.append(formattedCiphertext.charAt(i));
            if ((i + 1) % 4 == 0 && i != formattedCiphertext.length() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String ciphertext) {
        return encrypt(ciphertext);
    }

    @Override
    public List<String> getRotorWirings() {
        List<String> wirings = new ArrayList<>();
        for (Rotor rotor : rotors) {
            wirings.add(rotor.wiringAsString());
        }
        return wirings;
    }

    @Override
    public String getReflectorWiring() {
        return reflector.wiringAsString();
    }

    @Override
    public List<Integer> getRotorRotations() {
        List<Integer> rotations = new ArrayList<>();
        for (Rotor rotor : rotors) {
            rotations.add(rotor.getRotation());
        }
        return rotations;
    }

    @Override
    public void setRotorPositions(List<Integer> positions) {
        for (int i = 0; i < rotors.size(); i++) {
            rotors.get(i).setRotation(positions.get(i));
        }
    }

    @Override
    public String turingDecrypt(String ciphertext, String crib) {
        // TODO
        throw new UnsupportedOperationException();
    }

}