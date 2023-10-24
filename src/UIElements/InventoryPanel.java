package UIElements;

import java.awt.*;

public class InventoryPanel extends Canvas {
    private GridBagConstraints c;

    public InventoryPanel(){
        super();
        this.setBackground(Color.MAGENTA);
        this.setVisible(true);
        initializeConstraints();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.ORANGE);
        g2D.fillRect(0,0,250,250);
    }

    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,10,0,5);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
    }

    public GridBagConstraints getC() {
        return c;
    }
}
