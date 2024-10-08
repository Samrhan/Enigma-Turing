package org.carbon.service;

import java.util.List;
import java.util.stream.Collectors;

public class Reflector {
    private final List<Character> wiring;

    public Reflector(List<Character> wiring) {
        this.wiring = wiring;
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