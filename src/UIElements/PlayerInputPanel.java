package UIElements;

import Enums.Direction;
import Main.GameMaster;
import Main.GameRenderer;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class PlayerInputPanel extends JPanel {
    private GridBagConstraints c;

    private GameRenderer host;
    private JPanel CombatPanel;
    private JPanel MovementPanel;
    private JPanel UnitSelectPanel;
    private JButton AttackButton;
    private JButton InventoryButton;
    private JButton MapButton;
    private WASDPanel DirectionalButtons;
    public PlayerInputPanel(GameRenderer gameRenderer){
        super(new CardLayout());
        host = gameRenderer;
        CombatPanel = new JPanel(new GridLayout(1,4,5,5));
        this.add(CombatPanel,"COMBAT");

        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        this.setBackground(Color.BLACK);

        AttackButton = new JButton("Attack");
        InventoryButton = new JButton("Inventory");
        MapButton = new JButton("Map");
        DirectionalButtons = new WASDPanel(this);

        AttackButton.setBackground(Color.WHITE);
        InventoryButton.setBackground(Color.WHITE);
        MapButton.setBackground(Color.WHITE);

        AttackButton.addActionListener(e -> host.buttonPressed("attack"));

        CombatPanel.add(AttackButton);
        CombatPanel.add(InventoryButton);
        CombatPanel.add(MapButton);
        CombatPanel.add(DirectionalButtons);
        CombatPanel.setBackground(Color.BLACK);

        initializeConstraints();
    }

    public void buttonPressed(String value){
        host.buttonPressed(value);
    }
    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10,10,10,10);
    }
    public GridBagConstraints getC(){
        return c;
    }
}
