package UIElements;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CombatTextPanel extends JTextArea {
    private GridBagConstraints c;
    public CombatTextPanel(){
        super("THIS WILL BE COMBAT TEXT",15,25);
        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        this.setEditable(false);
        initializeConstraints();
    }
    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0,5,0,10);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridheight = 1;
        c.gridwidth = 1;
    }
    public GridBagConstraints getC(){
        return c;
    }
    public void write(String str){
        this.append("\n"+str);
    }
    public void clear(){
        this.setText("");
    }
}
