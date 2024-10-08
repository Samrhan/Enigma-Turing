package org.carbon;

import org.carbon.controller.EnigmaController;
import org.carbon.service.EnigmaServiceImpl;
import org.carbon.view.SwingEnigmaView;

public class EnigmaApplication {
    public static void main(String[] args) {
        EnigmaServiceImpl enigmaService = new EnigmaServiceImpl();
        SwingEnigmaView view = new SwingEnigmaView(); // View
        EnigmaController controller = new EnigmaController(enigmaService, view); // Controller
        view.setController(controller);
    }
}