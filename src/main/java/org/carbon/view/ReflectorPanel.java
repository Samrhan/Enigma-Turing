package org.carbon.view;

import javax.swing.*;
import java.awt.*;

class ReflectorPanel extends JPanel {

    public ReflectorPanel(String wiring) {
        JLabel label = new JLabel("<html>" + wiring.replaceAll("(.{1})", "$1<br>") + "</html>");
        label.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(label);
    }
}
