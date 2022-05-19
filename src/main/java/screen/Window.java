package screen;

import listeners.ComponentListen;
import listeners.WindowStateListen;
import listeners.MenuItemListen;
import listeners.MenuListen;
import external.Arduino;
import windowStyle.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class Window extends JFrame {

    private static final Color colorLineMenuBar = new Color(154, 154, 154);
    private static final Color colorBackgroundMenuBar = new Color(82, 82, 82);

    private static JSplitPane splitPaneHorizontalCanvasSplit;

    private static JMenu menuArduinoConnect;
    private static JMenuItem menuItemArduinoDisconnect;

    private static Render render;

    public Window() {
        // Configuration of the JFrame
        initWindowConfiguration();

        // Configuration of the menu bar
        initMenuBarConfiguration();

        // Configuration of the components contained in the JFrame
        initComponents();

        // Show the window
        setVisible(true);

        // Init the render
        render.start();

        // Window Listeners
        addComponentListener(new ComponentListen());
        addWindowStateListener(new WindowStateListen());
    }

    private void initWindowConfiguration() {
        // Appear light components in front of weight components
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);

        // In the look and feel metal change theme to my custom dark theme
        MetalLookAndFeel.setCurrentTheme(new DarkTheme());
        try {
            // Change the style to the custom MetalLookAndFeel
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Change borders of the JMenuItem top and bottom
        UIManager.put("PopupMenu.border", new LineBorder(colorLineMenuBar));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Button.background", new Color(126, 126, 126));

        SwingUtilities.updateComponentTreeUI(this);

        // General configuration of the window
        setTitle("Derblen 0.0.1a - By Goedix");
        setResizable(true);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setBackground(new Color(99, 99, 99));
    }

    private void initMenuBarConfiguration() {
        // Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(colorBackgroundMenuBar);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(154, 154, 154)));
        setJMenuBar(menuBar);

        // Menu Components
        JMenu menuFile = new CustomJMenu("File", true);
        JMenu menuArduino = new CustomJMenu("Arduino", true);
        menuArduinoConnect = new CustomJMenu("Connect Arduino", false);
        JMenu menuEdit = new CustomJMenu("Edit", true);
        JMenu menuHelp = new CustomJMenu("Help", true);
        JMenu menuImportFile = new CustomJMenu("Import object", false);
        JMenu menuExportFile = new CustomJMenu("Export object", false);
        JMenuItem menuItemFileOpen = new CustomJMenuItem("Open");
        JMenuItem menuItemFileSave = new CustomJMenuItem("Save");
        JMenuItem menuItemFileImportFile = new CustomJMenuItem("Wavefront (.obj)");
        JMenuItem menuItemFileExit = new CustomJMenuItem("Exit");
        JMenuItem menuItemEditUndo = new CustomJMenuItem("Undo");
        JMenuItem menuItemEditRedo = new CustomJMenuItem("Redo");
        JMenuItem menuItemEditHistory = new CustomJMenuItem("History");
        menuItemArduinoDisconnect = new CustomJMenuItem("Disconnect Arduino");
        JMenuItem menuItemHelpManual = new CustomJMenuItem("Manual");

        // Menu File - Save
        menuItemFileSave.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(154, 154, 154)));

        // Menu File - Import File
        Image imgImportFile = new ImageIcon("./src/main/resources/icons/import.png").getImage();
        Image resizedImageImportFile = imgImportFile.getScaledInstance(16, 16,  Image.SCALE_SMOOTH);
        menuImportFile.setIcon(new ImageIcon(resizedImageImportFile));

        // Menu File - Export File
        Image imgExportFile = new ImageIcon("./src/main/resources/icons/export.png").getImage();
        Image resizedImageExportFile = imgExportFile.getScaledInstance(16, 16,  Image.SCALE_SMOOTH);
        menuExportFile.setIcon(new ImageIcon(resizedImageExportFile));

        // Menu File - Import File
        menuImportFile.add(menuItemFileImportFile);

        // Menu File - Exit
        menuItemFileExit.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(154, 154, 154)));
        Image imgFileExit = new ImageIcon("./src/main/resources/icons/exit.png").getImage();
        Image resizedImportExit = imgFileExit.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        menuItemFileExit.setIcon(new ImageIcon(resizedImportExit));
        menuItemFileExit.addActionListener(new MenuItemListen());

        // Menu File
        menuFile.add(menuItemFileOpen);
        menuFile.add(menuItemFileSave);
        menuFile.add(menuImportFile);
        menuFile.add(menuExportFile);
        menuFile.add(menuItemFileExit);

        // Menu Edit - Undo
        Image imgEditUndo = new ImageIcon("./src/main/resources/icons/undo.png").getImage();
        Image resizedEditUndo = imgEditUndo.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        menuItemEditUndo.setIcon(new ImageIcon(resizedEditUndo));

        // Menu Edit - Redo
        Image imgEditRedo = new ImageIcon("./src/main/resources/icons/redo.png").getImage();
        Image resizedEditRedo = imgEditRedo.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        menuItemEditRedo.setIcon(new ImageIcon(resizedEditRedo));

        // Menu Edit - History
        Image imgEditHistory = new ImageIcon("./src/main/resources/icons/history.png").getImage();
        Image resizedEditHistory = imgEditHistory.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        menuItemEditHistory.setIcon(new ImageIcon(resizedEditHistory));
        menuItemEditHistory.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(154, 154, 154)));

        // Menu Edit
        menuEdit.add(menuItemEditUndo);
        menuEdit.add(menuItemEditRedo);
        menuEdit.add(menuItemEditHistory);

        // Menu Arduino
        menuArduino.addMenuListener(new MenuListen());

        // Menu Arduino - Disconnect
        menuItemArduinoDisconnect.setEnabled(false);
        menuItemArduinoDisconnect.addActionListener(new MenuItemListen());

        // Menu Arduino - Connect - Ports
        menuRefreshArduino();

        // Menu Arduino
        menuArduino.add(menuArduinoConnect);
        menuArduino.add(menuItemArduinoDisconnect);

        // Menu Help - Manual
        Image imgHelpManual = new ImageIcon("./src/main/resources/icons/manual.png").getImage();
        Image resizedHelpManual = imgHelpManual.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        menuItemHelpManual.setIcon(new ImageIcon(resizedHelpManual));
        menuItemHelpManual.setPreferredSize(new Dimension(100, 20));

        // Menu Help
        menuHelp.add(menuItemHelpManual);

        // Menu
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuArduino);
        menuBar.add(menuHelp);
    }

    public void initComponents() {
        // Container
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // Components
        splitPaneHorizontalCanvasSplit = new CustomJSplitPane();
        JSplitPane splitPaneVerticalPanelPanel = new CustomJSplitPane();
        JTree objectsTree = new CustomJTree();
        JScrollPane scrollPaneTree = new JScrollPane(objectsTree);
        render = new Render();

        // Split - Vertical - Panel Panel
        splitPaneVerticalPanelPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPaneVerticalPanelPanel.setDividerLocation(250);
        splitPaneVerticalPanelPanel.setTopComponent(scrollPaneTree);

        // Split - Horizontal - Canvas Split
        splitPaneHorizontalCanvasSplit.setDividerLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 250);
        splitPaneHorizontalCanvasSplit.setRightComponent(splitPaneVerticalPanelPanel);
        splitPaneHorizontalCanvasSplit.setLeftComponent(render);
        splitPaneHorizontalCanvasSplit.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

        contentPane.add(splitPaneHorizontalCanvasSplit);
    }

    public static void resizeComponents(int width, int height) {
        splitPaneHorizontalCanvasSplit.setSize(new Dimension(width, height));
        splitPaneHorizontalCanvasSplit.setDividerLocation(width - 250);
    }

    public static void menuRefreshArduino() {
        menuArduinoConnect.removeAll();
        Arduino.refreshArduinoPorts(menuArduinoConnect, menuItemArduinoDisconnect, menuArduinoConnect.getBackground(), menuArduinoConnect.getForeground());
    }

    public static void main(String[] args) {
        new Window();
    }
}