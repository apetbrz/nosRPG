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
    private static final String HEALTH_STRING = "[%d/%d] Health";
    private GridBagConstraints c;
    private static final Font MAIN_FONT = new Font("MONOSPACED", Font.BOLD, 24);
    private static final Color BACKGROUND_COLOR = Color.lightGray;

    public PlayerHUDPanel(){
        super(new GridLayout(1,3,5,10));
        _name = new JLabel("NAME");
        _name.setFont(MAIN_FONT);
        _name.setHorizontalAlignment(JLabel.CENTER);
        _health = new JLabel("HEALTH");
        _health.setFont(MAIN_FONT);
        _health.setHorizontalAlignment(JLabel.CENTER);
        _mana = new JLabel("MANA");
        _mana.setFont(MAIN_FONT);
        _mana.setHorizontalAlignment(JLabel.CENTER);
        this.add(_name);
        this.add(_health);
        this.add(_mana);

        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        initializeConstraints();
    }

    public void update(){
        if(_player != null) {
            String healthString = String.format(HEALTH_STRING, _player.getCurrentHealth(), _player.getMaxHealth());
            _health.setText(healthString);
        }
    }
    public void initialize(Player player){
        _player = player;
        _name.setText(_player.toString());
    }

    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10,10,10,10);
    }
    public GridBagConstraints getC(){
        return c;
    }
}
