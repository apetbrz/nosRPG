package Main;

import javax.swing.*;
import java.awt.*;
import UIElements.*;
import World.Creatures.Player;

public class GameRenderer extends JFrame{
    private static final String TITLE = "nosRPG Pre-Alpha 0.0.1";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Font MAIN_FONT = new Font("Segoe print", Font.BOLD, 18);
    private GameModel _gameModel;
    private JFrame _window;
    private GridBagLayout _layout;

    private CombatTextPanel _combatPanel;
    private MapPanel _mapPanel;
    private PlayerHUDPanel _hudPanel;
    private PlayerInputPanel _inputPanel;
    public GameRenderer(){
        _layout = new GridBagLayout();
        _window = new JFrame(TITLE);
        _window.setSize(WIDTH,HEIGHT);
        _window.setResizable(false);
        _window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        _window.getContentPane().setBackground(new Color(100,100,100));

        _window.setLayout(_layout);

        _combatPanel = new CombatTextPanel();
        _mapPanel = new MapPanel();
        _hudPanel = new PlayerHUDPanel();
        _inputPanel = new PlayerInputPanel();

        _window.add(_combatPanel, _combatPanel.getC());
        _window.add(_mapPanel, _mapPanel.getC());
        _window.add(_hudPanel, _hudPanel.getC());
        _window.add(_inputPanel, _inputPanel.getC());
        _window.setLocationRelativeTo(null);
        _window.setVisible(true);

        _combatPanel.write("TEST");
    }
    public void update(GameModel game){

    }
    public void initialize(GameModel game){
        _gameModel = game;
    }

    public void setPlayer(Player player){

    }
}