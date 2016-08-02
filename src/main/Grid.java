import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class Grid {
    static final Integer MAX_ROWS = 15;
    static final Integer MAX_COLS = MAX_ROWS;

    static final String X = "X";
    static final String O = "O";

    List<List<Cell>> rows = new ArrayList<>();
    Integer displayCount = 1;

    private static Grid instance = null;

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

            out.println();
        }
    }

    public void displayGridDontCalculateLife() {
        initializeDisplay();

        for(int row = 0; row < MAX_ROWS; row++) {
            displayNumberRows(row);
                for(int col = 0; col < MAX_COLS; col++) {
                printLife(row, col);
            }

            out.println();
        }
    }

    private void initializeDisplay() {
        out.println();
        out.println("Display #" + displayCount++);
        out.println();

        if(Grid.MAX_ROWS == 10) {
            out.println("  0 1 2 3 4 5 6 7 8 9");
        }
    }

    private void displayNumberRows(Integer row) {
        if(Grid.MAX_ROWS == 10) {
            out.print(row + " ");
        }
    }

    public Cell getCell(Coordinate coordinate) {
        return rows.get(coordinate.getRow()).get(coordinate.getCol());
    }

    private void printLife(int row, int col) {
        if(rows.get(row).get(col).isAlive) {
            out.print(O + " ");
        } else {
            out.print(X + " ");
        }
    }
}
