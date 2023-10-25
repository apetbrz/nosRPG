package UIElements;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CombatTextPanel extends JTextArea {
    private GridBagConstraints c;
    private static final int SIZE = 50;
    public CombatTextPanel(){
        super("THIS WILL BE COMBAT TEXT",SIZE,SIZE);
        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        this.setEditable(false);
        this.setLineWrap(false);
        this.setMinimumSize(new Dimension(600,600));
        initializeConstraints();
    }
    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0,5,5,10);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
    }
    public GridBagConstraints getC(){
        return c;
    }
    public void write(String str){
        this.append("\n"+str);
    }
    public void setText(String str){
        super.setText(str);
    }
    public void clear(){
        this.setText("");
    }
}
