import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class Grid {
    public static final Integer MAX_ROWS = 15;
    public static final Integer MAX_COLS = MAX_ROWS;

    private static final String X = "X";
    private static final String O = "O";

    private List<List<Cell>> rows = new ArrayList<>();
    private Integer displayGridCount = 1;

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

    public Cell getCell(Coordinate coordinate) {
        return rows.get(coordinate.getRow()).get(coordinate.getCol());
    }

    public void displayGrid(Boolean calculatingLife) {
        initializeDisplay();

        for(int row = 0; row < MAX_ROWS; row++) {
            displayNumberRows(row);
            for(int col = 0; col < MAX_COLS; col++) {
                if(calculatingLife) {
                    rows.get(row).get(col).calculateSelfLife();
                }

                printLife(row, col);
            }

            out.println();
        }
    }

    public void displayGrid() {
        displayGrid(true);
    }

    private void initializeDisplay() {
        out.println();
        out.println("Display #" + displayGridCount++);
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

    private void printLife(int row, int col) {
        if(rows.get(row).get(col).isAlive) {
            out.print(O + " ");
        } else {
            out.print(X + " ");
        }
    }
}
