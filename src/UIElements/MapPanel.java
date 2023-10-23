package UIElements;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;

public class MapPanel extends JTextArea {
    private GridBagConstraints c;

    public MapPanel(){
        super("THIS IS WHERE THE MAP WILL GO",15,25);
        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        this.setEditable(false);
        initializeConstraints();
    }

    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,10,0,5);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridheight = 1;
        c.gridwidth = 1;
    }

    public GridBagConstraints getC() {
        return c;
    }
}
