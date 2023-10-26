package UIElements;

import Enums.Command;
import Lang.Toolbox;
import Main.GameRenderer;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class PlayerInputPanel extends JPanel {
    private GridBagConstraints c;
    private CardLayout _layout;
    private GameRenderer host;
    private JPanel CombatPanel;
    private JPanel UnitSelectPanel;
    private JPanel MovementPanel;
    private JButton AttackButton;
    private JButton InventoryButton;
    private JButton MapButton;
    private WASDPanel DirectionalButtons;
    private boolean isSelectingUnit = false;
    public PlayerInputPanel(GameRenderer gameRenderer){
        super();
        _layout = new CardLayout();
        this.setLayout(_layout);
        host = gameRenderer;
        CombatPanel = new JPanel(new GridLayout(1,4,5,5));
        UnitSelectPanel = new JPanel(new FlowLayout());
        this.add(CombatPanel,"COMBAT");
        this.add(UnitSelectPanel, "UNIT_SELECT");

        this.setBorder(new MatteBorder(5,5,5,5,Color.BLACK));
        this.setBackground(Color.BLACK);

        AttackButton = new JButton("Attack");
        InventoryButton = new JButton("Inventory");
        MapButton = new JButton("Map");
        DirectionalButtons = new WASDPanel(this);

        AttackButton.setBackground(Color.WHITE);
        InventoryButton.setBackground(Color.WHITE);
        MapButton.setBackground(Color.WHITE);

        AttackButton.addActionListener(e -> buttonPressed(Command.ATTACK, ""));

        CombatPanel.add(AttackButton);
        CombatPanel.add(InventoryButton);
        CombatPanel.add(MapButton);
        CombatPanel.add(DirectionalButtons);
        CombatPanel.setBackground(Color.BLACK);

        initializeConstraints();
    }

    public void update(){

    }

    public void promptUnitSelection(int unitCount){
        if(unitCount == 0) return;

        isSelectingUnit = true;
        UnitSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        for(int i = 0; i < unitCount; i++){
            JButton button = new JButton("" + i+1);
            button.setBackground(Color.WHITE);
            int buttonIndex = i;
            button.addActionListener(e -> buttonPressed(Command.SELECTION, ""+buttonIndex));
            UnitSelectPanel.add(button);
        }
        Toolbox.print("UNIT SELECTION PROMPT HEARD");
        _layout.show(this, "UNIT_SELECT");
    }

    //TODO: COMBAT SHOULD ENABLE/DISABLE SOME BUTTONS

    public void buttonPressed(Command command, String data){
        if(isSelectingUnit && command != Command.SELECTION){
            Toolbox.print("COMMAND " + command + " DENIED DUE TO UNIT SELECTION");
            return;
        }
        if(isSelectingUnit) {
            Toolbox.print("UNIT SELECTION ACCEPTED, REMOVING BUTTONS");
            isSelectingUnit = false;
            _layout.show(this, "COMBAT");
            UnitSelectPanel.removeAll();
        }

        Toolbox.print("COMMAND " + command + " HEARD BY INPUT PANEL");
        host.buttonPressed(command, data);

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
