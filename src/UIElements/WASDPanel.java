package UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WASDPanel extends JPanel {
    private PlayerInputPanel host;
    private JButton N;
    private JButton E;
    private JButton S;
    private JButton W;
    private boolean _enabled;
    public WASDPanel(PlayerInputPanel playerInputPanel){
        this.setLayout(new GridLayout(2,3,2,2));
        this.setBackground(Color.darkGray);
        host = playerInputPanel;

        N = new JButton("W");
        E = new JButton("D");
        S = new JButton("S");
        W = new JButton("A");

        enable();

        InputMap inputMap = N.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("W"), "KEY_W");
        N.getActionMap().put("KEY_W", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(enabled) host.buttonPressed("north");
            }
        });

        inputMap = E.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("D"), "KEY_D");
        E.getActionMap().put("KEY_D", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(enabled) host.buttonPressed("east");
            }
        });

        inputMap = S.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("S"), "KEY_S");
        S.getActionMap().put("KEY_S", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(enabled) host.buttonPressed("south");
            }
        });

        inputMap = W.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("A"), "KEY_A");
        W.getActionMap().put("KEY_A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(enabled) host.buttonPressed("west");
            }
        });

        this.add(new JPanel());
        this.add(N);
        this.add(new JPanel());
        this.add(W);
        this.add(S);
        this.add(E);

    }
    public void enable(){
        _enabled = true;
        N.setBackground(Color.WHITE);
        E.setBackground(Color.WHITE);
        S.setBackground(Color.WHITE);
        W.setBackground(Color.WHITE);
    }
    public void disable(){
        _enabled = true;
        N.setBackground(Color.darkGray);
        E.setBackground(Color.darkGray);
        S.setBackground(Color.darkGray);
        W.setBackground(Color.darkGray);
    }
}
