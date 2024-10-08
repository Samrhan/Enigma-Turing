package org.carbon.view;

import javax.swing.*;
import java.awt.*;

class RotorPanel extends JPanel {
    private final JLabel label;
    private int rotation = 0;

    public RotorPanel(String wiring) {
        label = new JLabel("<html>" + wiring.replaceAll("(.{1})", "$1<br>") + "</html>");
        label.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(label);
        setPreferredSize(new Dimension(50, 200));
    }

    public void rotate(int rotation) {
        if (this.rotation != rotation) {
            this.rotation = rotation;
            String wiring = label.getText().replaceAll("<html>", "").replaceAll("<br>", "").replaceAll("</html>", "");
            label.setText("<html>" + wiring.substring(rotation).replaceAll("(.{1})", "$1<br>")
                    + wiring.substring(0, rotation).replaceAll("(.{1})", "$1<br>") + "</html>");
            repaint();
        }
    }
}
