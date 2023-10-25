package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import Enums.Direction;
import UIElements.*;
import World.Creatures.Player;

public class GameRenderer extends JFrame {
    private static final String TITLE = "nosRPG Pre-Alpha 0.0.1";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1000;
    public static final Font MAIN_FONT = new Font("Segoe print", Font.BOLD, 18);
    public static final Color MAIN_BACKGROUND = new Color(100,100,100);
    private GameMaster _gameMaster;
    private GridBagLayout _layout;
    private PlayerHUDPanel _hudPanel;
    private JPanel _leftPanel;
    private MapPanel _mapPanel;
    private InventoryPanel _inventoryPanel;
    private CombatTextPanel _combatPanel;
    private PlayerInputPanel _inputPanel;
    public GameRenderer(GameMaster game){
        this();
        this.initialize(game);
    }
    public GameRenderer(){
        super(TITLE);        //initialize

        _layout = new GridBagLayout();
        _layout.columnWeights = new double[]{1,1};

        this.setSize(WIDTH,HEIGHT);      //set size
        this.setLayout(_layout);         //set layout
        this.setResizable(false);        //no resizing
        this.setLocationRelativeTo(null);//center on screen
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.getContentPane().setBackground(new Color(100,100,100));

        _hudPanel = new PlayerHUDPanel();
        _leftPanel = new JPanel(new GridLayout(2,1,5,5));
        _mapPanel = new MapPanel();
        _inventoryPanel = new InventoryPanel();
        _combatPanel = new CombatTextPanel();
        _inputPanel = new PlayerInputPanel(this);

        this.add(_hudPanel, _hudPanel.getC());
        _leftPanel.add(_mapPanel);
        _leftPanel.add(_inventoryPanel);
        _leftPanel.setBackground(MAIN_BACKGROUND);
        _leftPanel.setMinimumSize(new Dimension(600,600));
        this.add(_leftPanel, _mapPanel.getC());
        this.add(_combatPanel, _combatPanel.getC());
        this.add(_inputPanel, _inputPanel.getC());

        this.setVisible(true);

        _combatPanel.write("TEST");
    }
    public void update(GameMaster game){
        _mapPanel.repaint();
        _combatPanel.setText(_gameMaster.getVisibleUnits().toString());
    }
    public void initialize(GameMaster game){
        _gameMaster = game;
        _mapPanel.setMap(_gameMaster.getMap());
        update(game);
    }

    public void buttonPressed(String value) {
        switch(value){
            case "north":
                _gameMaster.input("move", Direction.NORTH);
                break;
            case "east":
                _gameMaster.input("move", Direction.EAST);
                break;
            case "south":
                _gameMaster.input("move", Direction.SOUTH);
                break;
            case "west":
                _gameMaster.input("move", Direction.WEST);
                break;
        }
    }
}