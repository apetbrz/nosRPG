package UIElements;

import Enums.Direction;
import Main.GameRenderer;
import World.Room;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private GridBagConstraints c;
    private static final int INFILL = 10;
    private static final int EDGE_THICKNESS = 4;
    private static final int DOOR_LENGTH = 15;
    private static final int PLAYER_SIZE = 25;
    private static final int ROOM_SIZE = 100;
    private static final Color ROOM_COLOR = Color.gray;
    private static final Color EDGE_ROOM_COLOR = Color.darkGray.darker();
    private static final Color FOG_ROOM_COLOR = Color.darkGray.brighter();
    private static final Color UNKNOWN_ROOM_COLOR = Color.darkGray;
    private static final Color PLAYER_ROOM_COLOR = Color.green;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Font MAP_FONT = new Font("MONOSPACED",Font.PLAIN, 10);
    private Room[][] _map;
    private int squareSize;
    private int totalWidth;
    private int totalHeight;


    public MapPanel(){
        super();
        this.setBackground(GameRenderer.MAIN_BACKGROUND);
        initializeConstraints();
    }
    public MapPanel(Room[][] map){
        this();
        this.setMap(map);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        //System.out.println("paint called");
        if(_map == null){
            System.out.println("null map");
            return;
        }

        g2D.setFont(MAP_FONT);
        g2D.setStroke(new BasicStroke(EDGE_THICKNESS));

        //background
        g2D.setColor(ROOM_COLOR);
        g2D.fillRect(INFILL, INFILL, totalWidth, totalHeight);

        //paint each room
        drawBackgrounds(g2D);

        g2D.setColor(EDGE_ROOM_COLOR);
        g2D.drawRect(INFILL, INFILL, totalWidth, totalHeight);

        drawLines(g2D);
        drawMapText(g2D);
        drawPlayer(g2D);
    }
    private void drawBackgrounds(Graphics2D g2D){
        for(int row = 0; row < _map.length; row++) {
            for (int col = 0; col < _map[row].length; col++) {
                Room currentRoom = _map[row][col];
                boolean nullRoom = currentRoom == null;

                //get room location
                int squareX = INFILL + (col * squareSize);
                int squareY = INFILL + (row * squareSize);

                //null or not-visible room
                if(nullRoom){
                    g2D.setColor(FOG_ROOM_COLOR);
                }else if(!currentRoom.isDiscovered()){
                    g2D.setColor(UNKNOWN_ROOM_COLOR);
                }else if(!currentRoom.isVisible()){
                    g2D.setColor(FOG_ROOM_COLOR);
                }else{
                    g2D.setColor(ROOM_COLOR);
                }
                g2D.fillRect(squareX,squareY,squareSize,squareSize);
            }
        }
    }
    private void drawLines(Graphics2D g2D){
        for(int row = 0; row < _map.length; row++) {
            for (int col = 0; col < _map[row].length; col++) {
                //get room location
                int squareX = INFILL + (col * squareSize);
                int squareY = INFILL + (row * squareSize);
                int endX = squareX + squareSize;
                int endY = squareY + squareSize;

                Room currentRoom = _map[row][col];
                boolean nullRoom = currentRoom == null;
                byte roomConnections = !nullRoom ? currentRoom.getConnections() : 0;

                //paint room edges
                if(currentRoom.isDiscovered()) {
                    g2D.setColor(EDGE_ROOM_COLOR);
                    g2D.drawRect(squareX, squareY, squareSize, squareSize);    //room edge
                }

                //doors
                if(!currentRoom.isVisible()) {
                    g2D.setColor(FOG_ROOM_COLOR);
                }else {
                    g2D.setColor(ROOM_COLOR);
                }
                //TODO: doors (using highest 4 bits in connections byte)
                if(currentRoom.isDiscovered()) {
                    if ((roomConnections & Direction.NORTH.directionByte) != 0) {
                        g2D.drawLine(squareX + DOOR_LENGTH, squareY, endX - DOOR_LENGTH, squareY);
                    }
                    if ((roomConnections & Direction.EAST.directionByte) != 0) {
                        g2D.drawLine(endX, squareY + DOOR_LENGTH, endX, endY - DOOR_LENGTH);
                    }
                    if ((roomConnections & Direction.SOUTH.directionByte) != 0) {
                        g2D.drawLine(squareX + DOOR_LENGTH, endY, endX - DOOR_LENGTH, endY);
                    }
                    if ((roomConnections & Direction.WEST.directionByte) != 0) {
                        g2D.drawLine(squareX, squareY + DOOR_LENGTH, squareX, endY - DOOR_LENGTH);
                    }
                }
            }
        }
    }
    private void drawMapText(Graphics2D g2D){
        for(int row = 0; row < _map.length; row++){
            for(int col = 0; col < _map[row].length; col++){
                Room currentRoom = _map[row][col];
                boolean nullRoom = currentRoom == null;
                int squareX = INFILL + (col * squareSize);
                int squareY = INFILL + (row * squareSize);

                //paint room name
                g2D.setColor(TEXT_COLOR);
                int textX = squareX + EDGE_THICKNESS;
                int textY = squareY + squareSize - EDGE_THICKNESS;
                g2D.drawString(!nullRoom ? currentRoom.toString() : "NULL",textX,textY);
            }
        }
    }
    private void drawPlayer(Graphics2D g2D){
        for(int row = 0; row < _map.length; row++) {
            for (int col = 0; col < _map[row].length; col++) {
                Room currentRoom = _map[row][col];
                boolean nullRoom = currentRoom == null;
                int squareX = INFILL + (col * squareSize);
                int squareY = INFILL + (row * squareSize);
                int playerX = squareX + squareSize/2 - PLAYER_SIZE/2;
                int playerY = squareY + squareSize/2 - PLAYER_SIZE/2;

                //check for player
                if (nullRoom) {
                    //null room
                    return;
                } else if (currentRoom.hasPlayer()) {
                    g2D.setColor(PLAYER_ROOM_COLOR);
                    g2D.fillOval(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
                }
            }
        }
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }

    public void setMap(Room[][] map) {
        _map = map;
        squareSize = (this.getHeight() - INFILL *2) / _map.length;
        totalWidth = squareSize * _map[0].length;
        totalHeight = squareSize * _map.length;

    }

    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,10,5,5);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
    }

    public GridBagConstraints getC() {
        return c;
    }
}
