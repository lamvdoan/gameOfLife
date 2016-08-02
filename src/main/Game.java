/**
 *  Implement game of life...
 *
 *  https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 */

public class Game {

    public static void main(String[] args) {
        Grid grid = Grid.getInstance();

        grid.getCell(new Coordinate(0,1)).setAlive(true);
        grid.getCell(new Coordinate(0,2)).setAlive(true);
        grid.getCell(new Coordinate(1,1)).setAlive(true);
        grid.getCell(new Coordinate(2,4)).setAlive(true);
        grid.getCell(new Coordinate(3,3)).setAlive(true);
        grid.displayGridDontCalculateLife();

        for(int i=0; i<4; i++) {
            grid.displayGrid();
        }
    }
}
