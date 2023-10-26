package UIElements;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CombatTextPanel extends JTextArea {
    private GridBagConstraints c;
    private static final int SIZE = 50;
    private static final Font MAIN_FONT = new Font("MONOSPACED", Font.PLAIN, 18);

    public CombatTextPanel(){
        super("THIS WILL BE COMBAT TEXT",SIZE,SIZE);
        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        //this.setMinimumSize(new Dimension(Main.Main.WIDTH/2,Main.Main.HEIGHT/2));
        this.setFont(MAIN_FONT);
        initializeConstraints();
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
    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0,5,5,10);
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
}
