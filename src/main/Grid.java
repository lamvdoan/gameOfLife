import java.util.ArrayList;
import java.util.List;

public class Grid {
    static final Integer MAX_ROWS = 15;
    static final Integer MAX_COLS = MAX_ROWS;

    static final String X = "X";
    static final String O = "O";

    List<List<Cell>> rows = new ArrayList<>();
    Integer displayCount = 1;

    private static Grid instance = new Grid();

    public static Grid getInstance() {
        if(instance == null) {
            instance = new Grid();
        }

        return instance;
    }

    private Grid() {
        for(int row = 0; row < MAX_ROWS; row++) {
            List<Cell> cols = new ArrayList<>();

            for(int col = 0; col < MAX_COLS; col++) {
                Cell cell = new Cell(new Coordinate(row, col));
                cols.add(cell);
            }

            rows.add(cols);
        }
    }

    public void displayGrid() {
        initializeDisplay();

        for(int row = 0; row < MAX_ROWS; row++) {
            displayNumberRows(row);
            for(int col = 0; col < MAX_COLS; col++) {
                rows.get(row).get(col).calculateNeighborsLife();
                printLife(row, col);
            }

            Functions.println();
        }
    }

    public void displayGridDontCalculateLife() {
        initializeDisplay();

        for(int row = 0; row < MAX_ROWS; row++) {
            displayNumberRows(row);
                for(int col = 0; col < MAX_COLS; col++) {
                printLife(row, col);
            }

            Functions.println();
        }
    }

    private void initializeDisplay() {
        Functions.println();
        Functions.println("Display #" + displayCount++);
        Functions.println();

        if(Grid.MAX_ROWS == 10) {
            Functions.println("  0 1 2 3 4 5 6 7 8 9");
        }
    }

    private void displayNumberRows(Integer row) {
        if(Grid.MAX_ROWS == 10) {
            Functions.print(row + " ");
        }
    }

    public Cell getCell(Coordinate coordinate) {
        return rows.get(coordinate.getRow()).get(coordinate.getCol());
    }

    private void printLife(int row, int col) {
        if(rows.get(row).get(col).isAlive) {
            Functions.print(O + " ");
        } else {
            Functions.print(X + " ");
        }
    }
}
