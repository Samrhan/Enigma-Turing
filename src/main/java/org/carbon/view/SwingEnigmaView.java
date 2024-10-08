package org.carbon.view;

import org.carbon.controller.EnigmaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SwingEnigmaView extends JFrame implements EnigmaView {

    private List<RotorPanel> rotorPanels;
    private JTextArea inputField, outputField;
    private JTextField cribField;

    private JPanel rotorPanel;
    private JPanel ioPanel;
    private JPanel turingPanel;

    private EnigmaController controller;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(580, 580);
    }

    public SwingEnigmaView() {
        super("Machine Enigma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createRotorPanels();
        createIOPanel();
        createTuringPanel();

        add(rotorPanel, BorderLayout.WEST);
        add(ioPanel, BorderLayout.CENTER);
        add(turingPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void setController(EnigmaController controller) {
        this.controller = controller;
    }

    private void createRotorPanels() {
        rotorPanels = new ArrayList<>();
        rotorPanel = new JPanel();
        rotorPanel.setLayout(new BoxLayout(rotorPanel, BoxLayout.X_AXIS));
    }

    private void createIOPanel() {
        ioPanel = new JPanel();
        ioPanel.setLayout(new GridLayout(3, 2));
        JLabel inputLabel = new JLabel("Texte en clair :");
        JLabel outputLabel = new JLabel("Texte chiffré :");

        inputField = new JTextArea();
        inputField.setLineWrap(true);
        inputField.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        outputField = new JTextArea();
        outputField.setLineWrap(true);
        outputField.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JButton encryptButton = new JButton("Chiffrer");
        JButton decryptButton = new JButton("Déchiffrer");

        encryptButton.addActionListener(new EncryptCommand());
        decryptButton.addActionListener(new DecryptCommand());

        ioPanel.add(inputLabel);
        ioPanel.add(inputField);
        ioPanel.add(outputLabel);
        ioPanel.add(outputField);
        ioPanel.add(encryptButton);
        ioPanel.add(decryptButton);
    }

    private void createTuringPanel() {
        turingPanel = new JPanel();
        turingPanel.setLayout(new FlowLayout());
        turingPanel.add(new JLabel("Crib:"));

        cribField = new JTextField(10);
        turingPanel.add(cribField);

        JButton turingButton = new JButton("Bombe Turing");

        turingButton.addActionListener(new TuringDecryptCommand());
        turingPanel.add(turingButton);
    }

    @Override
    public void initializeRotorsAndReflector(List<String> rotorWirings, String reflectorWiring) {
        for (String wiring : rotorWirings) {
            rotorPanels.add(new RotorPanel(wiring));
        }

        for (RotorPanel panel : rotorPanels) {
            rotorPanel.add(panel);
        }

        ReflectorPanel reflectorPanel = new ReflectorPanel(reflectorWiring);
        rotorPanel.add(reflectorPanel);
    }

    @Override
    public void setInputText(String text) {
        inputField.setText(text);
    }

    @Override
    public void setOutputText(String text) {
        outputField.setText(text);
    }

    @Override
    public void updateRotorPositions(List<Integer> rotations) {
        for (int i = 0; i < rotations.size(); i++) {
            rotorPanels.get(i).rotate(rotations.get(i));
        }
    }

    private class EncryptCommand implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.encrypt(inputField.getText().toUpperCase());
        }
    }

    private class DecryptCommand implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.decrypt(outputField.getText().toUpperCase());
        }
    }

    private class TuringDecryptCommand implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.turingDecrypt(outputField.getText().toUpperCase(), cribField.getText().toUpperCase());
        }
    }

}