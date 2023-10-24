package UIElements;

import World.Creatures.Player;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class PlayerHUDPanel extends JPanel {
    Player _player;
    private JLabel _health;
    private JLabel _mana;
    private JLabel _name;
    private GridBagConstraints c;
    private static final Font MAIN_FONT = new Font("MONOSPACED", Font.BOLD, 24);

    public PlayerHUDPanel(){
        super(new FlowLayout(FlowLayout.CENTER,25,0));
        _health = new JLabel("HEALTH");
        _health.setFont(MAIN_FONT);
        _mana = new JLabel("MANA");
        _mana.setFont(MAIN_FONT);
        _name = new JLabel("NAME");
        _name.setFont(MAIN_FONT);
        this.add(_name);
        this.add(_health);
        this.add(_mana);

        this.setBackground(Color.BLUE);
        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        initializeConstraints();
    }
    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10,10,10,10);
        c.anchor = GridBagConstraints.PAGE_START;
    }
    public GridBagConstraints getC(){
        return c;
    }
}
