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

    // Important colors
    private static final Color colorLineMenuBar = new Color(154, 154, 154);
    private static final Color colorBackgroundMenuBar = new Color(82, 82, 82);

    // Main component
    private static JSplitPane splitPaneHorizontalCanvasSplit;

    // Arduino
    private static JMenu menuArduinoConnect;
    private static JMenuItem menuItemArduinoDisconnect;

    // Render class
    private static Render render;

    // Tree List
    private static CustomJTree objectsTree;

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
        JMenu menuFile = new CustomJMenu("File", true, null);
        JMenu menuArduino = new CustomJMenu("Arduino", true, null);
        menuArduinoConnect = new CustomJMenu("Connect Arduino", false, null);
        JMenu menuEdit = new CustomJMenu("Edit", true, null);
        JMenu menuHelp = new CustomJMenu("Help", true, null);
        JMenu menuImportFile = new CustomJMenu("Import object", false, "icons/import.png");
        JMenu menuExportFile = new CustomJMenu("Export object", false, "icons/export.png");
        JMenuItem menuItemFileOpen = new CustomJMenuItem("Open", null);
        JMenuItem menuItemFileSave = new CustomJMenuItem("Save", null);
        JMenuItem menuItemFileImportFile = new CustomJMenuItem("Wavefront (.obj)", null);
        JMenuItem menuItemFileExit = new CustomJMenuItem("Exit", "icons/exit.png");
        JMenuItem menuItemEditUndo = new CustomJMenuItem("Undo", "icons/undo.png");
        JMenuItem menuItemEditRedo = new CustomJMenuItem("Redo", "icons/redo.png");
        JMenuItem menuItemEditHistory = new CustomJMenuItem("History", "icons/history.png");
        menuItemArduinoDisconnect = new CustomJMenuItem("Disconnect Arduino", null);
        JMenuItem menuItemHelpManual = new CustomJMenuItem("Manual", "icons/manual.png");

        // Menu File - Save
        menuItemFileSave.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(154, 154, 154)));

        // Menu File - Import File
        menuImportFile.add(menuItemFileImportFile);

        // Menu File - Exit
        menuItemFileExit.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(154, 154, 154)));
        menuItemFileExit.addActionListener(new MenuItemListen());

        // Menu File
        menuFile.add(menuItemFileOpen);
        menuFile.add(menuItemFileSave);
        menuFile.add(menuImportFile);
        menuFile.add(menuExportFile);
        menuFile.add(menuItemFileExit);

        // Menu Edit - History
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
        objectsTree = new CustomJTree();
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
        // Resize main Component
        splitPaneHorizontalCanvasSplit.setSize(new Dimension(width, height));
        splitPaneHorizontalCanvasSplit.setDividerLocation(width - 250);
    }

    public static void menuRefreshArduino() {
        // Remove ports and get ports
        menuArduinoConnect.removeAll();
        Arduino.refreshArduinoPorts(menuArduinoConnect, menuItemArduinoDisconnect, menuArduinoConnect.getBackground(), menuArduinoConnect.getForeground());
    }

    public static double getCanvasWidth() {
        return splitPaneHorizontalCanvasSplit.getDividerLocation() - splitPaneHorizontalCanvasSplit.getDividerSize();
    }

    public static double getCanvasHeight() {
        return splitPaneHorizontalCanvasSplit.getSize().getHeight();
    }

    public static CustomJTree getObjectsTree() {
        return objectsTree;
    }

    public static void main(String[] args) {
        new Window();
    }
}