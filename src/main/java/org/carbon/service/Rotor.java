package org.carbon.service;

import java.util.List;
import java.util.stream.Collectors;

public class Rotor {
    private final List<Character> wiring;
    private int rotation;

    public Rotor(List<Character> wiring, int initialRotation) {
        this.wiring = wiring;
        this.rotation = initialRotation;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public List<Character> getWiring() {
        return wiring;
    }

    public String wiringAsString() {
        return wiring.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}