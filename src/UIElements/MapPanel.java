package UIElements;

import Enums.Direction;
import World.Room;

import java.awt.*;

public class MapPanel extends Canvas {
    private GridBagConstraints c;
    private static final int MAP_INFILL = 10;
    private static final int EDGE_BUFFER = 2;
    private static final int DOOR_LENGTH = 10;
    private static final int PLAYER_SIZE = 25;
    private static final Color ROOM_COLOR = Color.gray;
    private static final Color EDGE_ROOM_COLOR = Color.darkGray;
    private static final Color PLAYER_ROOM_COLOR = Color.green;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Font MAP_FONT = new Font("MONOSPACED",Font.PLAIN, 10);
    private Room[][] _map;

    public MapPanel(){
        super();
        this.setBackground(Color.black);
        this.setVisible(true);
        initializeConstraints();
    }
    public MapPanel(Room[][] map){
        this();
        this.setMap(map);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;

        System.out.println("paint called");
        if(_map == null){
            System.out.println("null map");
            return;
        }

        g2D.setFont(MAP_FONT);

        int squareSize = (this.getHeight() - MAP_INFILL*2) / _map.length;
        int totalWidth = squareSize * _map[0].length;
        int totalHeight = squareSize * _map.length;
        System.out.println("non-null map: squareSize: " + squareSize);

        g2D.setColor(ROOM_COLOR);
        g2D.fillRect(MAP_INFILL, MAP_INFILL, totalWidth, totalHeight);
        g2D.setStroke(new BasicStroke(EDGE_BUFFER));

        for(int row = 0; row < _map.length; row++){
            for(int col = 0; col < _map[row].length; col++){
                Room currentRoom = _map[row][col];
                byte roomConnections = currentRoom.getConnections();

                //get room location
                int squareX = MAP_INFILL + (col * squareSize);
                int squareY = MAP_INFILL + (row * squareSize);
                int endX = squareX + squareSize;
                int endY = squareY + squareSize;
                int playerX = squareX + squareSize/2 - PLAYER_SIZE/2;
                int playerY = squareY + squareSize/2 - PLAYER_SIZE/2;

                try {
                    if (_map[row][col].hasPlayer()) {   //check for player
                        g2D.setColor(PLAYER_ROOM_COLOR);
                        g2D.fillOval(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
                    }
                }catch(NullPointerException e){
                    //System.out.println("error: room " + row + " " + col + " is null");
                }
                //paint room

                g2D.setColor(EDGE_ROOM_COLOR);
                g2D.drawRect(squareX,squareY,squareSize,squareSize);

                g2D.setColor(ROOM_COLOR);
                //g2D.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));

                if((roomConnections & Direction.NORTH.directionByte) != 0){
                    g2D.drawLine(squareX+DOOR_LENGTH,squareY,endX-DOOR_LENGTH,squareY);
                }
                if((roomConnections & Direction.EAST.directionByte) != 0){
                    g2D.drawLine(endX,squareY+DOOR_LENGTH,endX,endY-DOOR_LENGTH);
                }
                if((roomConnections & Direction.SOUTH.directionByte) != 0){
                    g2D.drawLine(squareX+DOOR_LENGTH,endY,endX-DOOR_LENGTH,endY);
                }
                if((roomConnections & Direction.WEST.directionByte) != 0){
                    g2D.drawLine(squareX,squareY+DOOR_LENGTH,squareX,endY-DOOR_LENGTH);
                }


                //paint room name
                g2D.setColor(TEXT_COLOR);
                try{
                    int textX = squareX + EDGE_BUFFER;
                    int textY = squareY + squareSize - EDGE_BUFFER;
                    g2D.drawString(currentRoom.toString(),textX,textY);
                }catch (NullPointerException e){
                    //null room, again lol
                }
            }
        }
    }
    @Override
    public void update(Graphics g){
        paint(g);
    }

    public void initializeConstraints(){
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,10,5,5);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
    }

    public GridBagConstraints getC() {
        return c;
    }

    public void setMap(Room[][] map) {
        _map = map;
    }
}
