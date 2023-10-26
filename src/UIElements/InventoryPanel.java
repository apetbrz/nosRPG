package UIElements;

import Mechanics.Inventory;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private GridBagConstraints c;
    public static final int INFILL = 10;
    private Inventory _inventory;

    public InventoryPanel(){
        super();
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        //initializeConstraints();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.gray);
        g2D.fillRect(INFILL,INFILL,this.getWidth()-INFILL*2,this.getHeight()-INFILL*2);
        g2D.setColor(Color.BLACK);
        g2D.drawString(_inventory.toString(), 0, this.getHeight());
    }

    public Inventory getInventory() {
        return _inventory;
    }
    public void setInventory(Inventory inventory) {
        _inventory = inventory;
    }

    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,10,0,5);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
    }
    public GridBagConstraints getC() {
        return c;
    }
}
