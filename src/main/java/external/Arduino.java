package external;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Arduino {

    private static String data = "";
    private static boolean comienza = false;

    private static boolean isConnected = false;

    private static SerialPort[] ports;
    private static SerialPort activePort;

    public static boolean connectArduino(JMenuItem menuItem, JMenuItem[] menuItems) {
        if (isConnected) {
            JOptionPane.showMessageDialog(null, "You are already connected.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        for (int i = 0; i < menuItems.length; i++) {
            if (menuItems[i] == menuItem) {
                int finalI = i;
                Thread t = new Thread(() -> {
                    if (setPort(finalI)) {
                        JOptionPane.showMessageDialog(null, "Connected correctly.", "Connection started", JOptionPane.INFORMATION_MESSAGE);
                        isConnected = true;
                    }
                });
                t.start();
            }
        }
        return true;
    }

    private static boolean setPort(int indexPort) {
        activePort = ports[indexPort];

        if (activePort.openPort())
            System.out.println(activePort.getPortDescription() + " puerto abierto.");
        else
            return false;

        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                int size = serialPortEvent.getSerialPort().bytesAvailable();
                byte[] buffer = new byte[size];
                serialPortEvent.getSerialPort().readBytes(buffer, size);
                for (byte b : buffer) {
                    control((char) b);
                }
            }
        });

        return true;
    }

    public static boolean closePort() {
        if (activePort == null || !isConnected) return false;
        activePort.closePort();
        isConnected = false;
        JOptionPane.showMessageDialog(null, "Disconnected correctly", "Connection closed", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    private static void control(char c) {
        if ((c == 'X' || c == 'Y') && !comienza) {
            data += c;
            comienza = true;
        } else if (c == 'X' || c == 'Y') {
            double cant = 0;
            try {
                cant = Double.parseDouble(data.substring(data.indexOf(':') + 1, data.length() - 2));
            } catch (NumberFormatException ignored) {}

            /*if (cant != 0) {
                double rY = Pantalla.getCamera().getrY();
                double rX = Pantalla.getCamera().getrX();

                double rXX = Math.abs(Pantalla.getCamera().getrX());
                Pantalla.getCamera().setIsToRight(rXX % 360 > 90 && rXX % 360 < 270);

                if (data.charAt(0) == 'Y') {
                    Pantalla.getCamera().setrX(rX - (float) (cant * 10));
                } else {
                    Pantalla.getCamera().setrY(rY + (Pantalla.getCamera().getIsToRight() ? -(float) (cant * 10) : (float) (cant * 10)));
                }
                Pantalla.getInstance().render();
            }*/

            data = "";
            data += c;
        }

        if (comienza && !(c == 'X' || c == 'Y')) {
            data += c;
        }
    }

    public static ArrayList<String> getPorts() {
        ports = SerialPort.getCommPorts();
        ArrayList<String> p = new ArrayList<>();
        for (SerialPort port : ports) {
            p.add(port.getDescriptivePortName());
        }

        return p;
    }

    public static void refreshArduinoPorts(JMenu container, JMenuItem disconnect, Color background, Color foreground) {
        JMenuItem[] menuItems;
        ArrayList<String> portsList = Arduino.getPorts();
        menuItems = new JMenuItem[portsList.size()];
        for (int i = 0; i < portsList.size(); i++) {
            menuItems[i] = new JMenuItem(portsList.get(i));
            menuItems[i].setBackground(background);
            menuItems[i].setForeground(foreground);
            menuItems[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
            menuItems[i].addActionListener(e -> {
                if (Arduino.connectArduino((JMenuItem) e.getSource(), menuItems)) {
                    disconnect.setEnabled(true);
                }
            });
            container.add(menuItems[i]);
        }
    }
}
