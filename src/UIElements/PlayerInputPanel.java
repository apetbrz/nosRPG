package UIElements;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Map;

public class PlayerInputPanel extends JPanel {
    private GridBagConstraints c;
    private JPanel CombatPanel;
    private JPanel MovementPanel;
    private JPanel UnitSelectPanel;
    private JButton AttackButton;
    private JButton InventoryButton;
    private JButton MapButton;
    public PlayerInputPanel(){
        super(new CardLayout());
        CombatPanel = new JPanel(new GridLayout(1,3,5,5));
        this.add(CombatPanel,"COMBAT");

        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        this.setBackground(Color.BLACK);

        AttackButton = new JButton("Attack");
        InventoryButton = new JButton("Inventory");
        MapButton = new JButton("Map");
        AttackButton.setBackground(Color.WHITE);
        InventoryButton.setBackground(Color.WHITE);
        MapButton.setBackground(Color.WHITE);
        CombatPanel.add(AttackButton);
        CombatPanel.add(InventoryButton);
        CombatPanel.add(MapButton);
        CombatPanel.setBackground(Color.BLACK);

        initializeConstraints();
    }
    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
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
