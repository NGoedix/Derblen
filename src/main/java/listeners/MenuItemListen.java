package listeners;

import external.Arduino;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuItemListen implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(e.getSource() instanceof JMenuItem)) return;

        JMenuItem item = (JMenuItem) e.getSource();
        switch(item.getText()) {
            case "Exit":
                System.exit(0);
                break;
            case "Disconnect Arduino":
                if (Arduino.closePort()) item.setEnabled(false);
                break;
        }
    }
}
