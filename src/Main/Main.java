package Main;

public class Main {
    //TODO: MENU
    public static void main(String[] args) {
        GameMaster game = new GameMaster();
        game.play();
    }
}
/*
* ARCHITECTURE:
*
* The game is split into 3 main objects:
*
* GameModel, where the world and all its inhabitants are stored and interact.
* GameRenderer, where everything is displayed to the screen and where input is taken from the player.
* GameMaster, which handles communication between these two, translating inputs into game actions.
*
* Each game takes place in a dungeon.
* A dungeon contains a 2D array of Rooms called a DungeonMap.
* Each Room is a contained environment with Units and Interactable objects.
* The player is a Unit, directly controlled by the player.
*
*
*/