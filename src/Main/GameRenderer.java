package Main;

import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JFrame{
    private static final String TITLE = "nosRPG Pre-Alpha 0.0.1";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JFrame _window;
    private Canvas _canvas;
    public GameRenderer(){
        _window = new JFrame(TITLE);
        _window.setSize(WIDTH,HEIGHT);
        _window.setResizable(false);
        _window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        _window.setLayout(new GridBagLayout());

        _canvas = new Canvas();

        _window.add(_canvas);
        _window.setVisible(true);
    }
}
