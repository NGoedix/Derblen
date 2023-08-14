package windowStyle;

import objects.internal.DerblenObject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CustomJTree extends JTree {

    Map<String, DerblenObject> treeItems = new HashMap<>();
    Map<String, DefaultMutableTreeNode> treeCollections = new HashMap<>();

    public CustomJTree() {
        initTree();
        setBackground(new Color(110, 110, 110));
        setForeground(new Color(215, 215, 215));
        setEditable(true);
        setCellRenderer(new CustomTreeCellRenderer());
    }

    private void initTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Scene");
        DefaultMutableTreeNode parent;

        parent = new DefaultMutableTreeNode("Collection");
        root.add(parent);

        treeCollections.put("Collection", parent);

        setModel(new DefaultTreeModel(root));
    }

    public void addObject(DerblenObject object, String name) {
        treeItems.put(name, object);
        if (treeCollections.get("Collection") == null) {
            DefaultTreeModel model = (DefaultTreeModel) getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            root.add(new DefaultMutableTreeNode(name));
        } else {
            treeCollections.get("Collection").add(new DefaultMutableTreeNode(name));
        }
        ((DefaultTreeModel) getModel()).reload();
    }
}
