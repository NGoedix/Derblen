package windowStyle;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CustomJTree extends JTree {

    public CustomJTree() {
        updateTree();
        setBackground(new Color(110, 110, 110));
        setForeground(new Color(215, 215, 215));
        setEditable(true);
        setCellRenderer(new CustomTreeCellRenderer());
    }

    private void updateTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Scene");
        DefaultMutableTreeNode parent;

        parent = new DefaultMutableTreeNode("Collection");
        root.add(parent);

        parent.add(new DefaultMutableTreeNode("Cube"));
        parent.add(new DefaultMutableTreeNode("AWDAWDAWD"));
        parent.add(new DefaultMutableTreeNode("SADAWD"));        parent.add(new DefaultMutableTreeNode("Cube"));
        parent.add(new DefaultMutableTreeNode("AWDAWDAWD"));
        parent.add(new DefaultMutableTreeNode("SADAWD"));        parent.add(new DefaultMutableTreeNode("Cube"));
        parent.add(new DefaultMutableTreeNode("AWDAWDAWD"));
        parent.add(new DefaultMutableTreeNode("SADAWD"));        parent.add(new DefaultMutableTreeNode("Cube"));
        parent.add(new DefaultMutableTreeNode("AWDAWDAWD"));
        parent.add(new DefaultMutableTreeNode("SADAWD"));        parent.add(new DefaultMutableTreeNode("Cube"));
        parent.add(new DefaultMutableTreeNode("AWDAWDAWD"));
        parent.add(new DefaultMutableTreeNode("SADAWD"));

        // TODO First time Cube and Light, link objects
        setModel(new DefaultTreeModel(root));
    }

    // TODO Implement addElement function
}
