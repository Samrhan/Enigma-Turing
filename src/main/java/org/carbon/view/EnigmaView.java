package org.carbon.view;

import java.util.List;

public interface EnigmaView {
    void initializeRotorsAndReflector(List<String> rotorWirings, String reflectorWiring);
    void setInputText(String text);
    void setOutputText(String text);
    void updateRotorPositions(List<Integer> rotations);
}