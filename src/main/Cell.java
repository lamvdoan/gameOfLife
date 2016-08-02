
public class Cell {
    Coordinate coordinate;
    Boolean isAlive;
    Integer countOfLiveNeighbors;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.isAlive = false;
        this.countOfLiveNeighbors = 0;
    }

    public void calculateSelfLife() {
        calculateNeighborsLife();

        if(this.isAlive) {
            if(countOfLiveNeighbors < 2 || countOfLiveNeighbors > 3) {
                isAlive = false;
            }
        } else {
            if(countOfLiveNeighbors == 3) {
                isAlive = true;
            }
        }
    }

    public void calculateNeighborsLife() {
        Grid grid = Grid.getInstance();
        this.countOfLiveNeighbors = 0;

        // CHECK Corners
        if(coordinate.getRow() == 0 && coordinate.getCol() == 0) {
            calculateCellsOfTopLeftCornerCell(grid);
        } else if(coordinate.getRow() == 0 && coordinate.getCol() == Grid.MAX_COLS - 1) {
            calculateCellsOfTopRightCornerCell(grid);
        } else if(coordinate.getRow() == Grid.MAX_ROWS  - 1 && coordinate.getCol() == 0) {
            calculateCellsOfBottomLeftCornerCell(grid);
        } else if(coordinate.getRow() == Grid.MAX_ROWS  - 1 && coordinate.getCol() == Grid.MAX_COLS - 1) {
            calculateCellsOfBottomRightCornerCell(grid);

        // CHECK Top Middle
        } else if(coordinate.getRow() == 0) {
            calculateCellsOfTopMiddleRangeOfCells(grid);

        // CHECK Bottom Middle
        } else if (coordinate.getRow() == Grid.MAX_ROWS - 1) {
            calculateCellsOfBottomMiddleRangeOfCells(grid);

        // CHECK Left Middle
        } else if (coordinate.getCol() == 0) {
            calculateCellsOfLeftMiddleRangeOfCells(grid);

        // CHECK Right Middle
        } else if (coordinate.getCol() == Grid.MAX_COLS - 1) {
            calculateCellsOfRightMiddleRangeOfCells(grid);

        // CHECK Middle Middle
        } else {
            calculateCellsOfMiddleMiddleRangeOfCells(grid);
        }
    }

    private void calculateCellsOfMiddleMiddleRangeOfCells(Grid grid) {
        topLeftCell(grid);
        topMiddleCell(grid);
        topRightCell(grid);

        middleLeftCell(grid);
        middleRightCell(grid);

        bottomLeftCell(grid);
        bottomMiddleCell(grid);
        bottomRightCell(grid);
    }

    private void calculateCellsOfRightMiddleRangeOfCells(Grid grid) {
        topMiddleCell(grid);
        bottomMiddleCell(grid);

        topLeftCell(grid);
        middleLeftCell(grid);
        bottomLeftCell(grid);
    }

    private void calculateCellsOfLeftMiddleRangeOfCells(Grid grid) {
        topMiddleCell(grid);
        bottomMiddleCell(grid);

        topRightCell(grid);
        middleRightCell(grid);
        bottomRightCell(grid);
    }

    private void calculateCellsOfBottomMiddleRangeOfCells(Grid grid) {
        topLeftCell(grid);
        topMiddleCell(grid);
        topRightCell(grid);

        middleLeftCell(grid);
        middleRightCell(grid);
    }

    private void calculateCellsOfTopMiddleRangeOfCells(Grid grid) {
        middleLeftCell(grid);
        middleRightCell(grid);

        bottomLeftCell(grid);
        bottomMiddleCell(grid);
        bottomRightCell(grid);
    }

    private void calculateCellsOfBottomRightCornerCell(Grid grid) {
        topLeftCell(grid);
        topMiddleCell(grid);
        middleLeftCell(grid);
    }

    private void calculateCellsOfBottomLeftCornerCell(Grid grid) {
        topMiddleCell(grid);
        topRightCell(grid);
        middleRightCell(grid);
    }

    private void calculateCellsOfTopRightCornerCell(Grid grid) {
        middleLeftCell(grid);
        bottomLeftCell(grid);
        bottomMiddleCell(grid);
    }

    private void calculateCellsOfTopLeftCornerCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(0, 1)));
        calculateOneNeighbor(grid.getCell(new Coordinate(1, 1)));
        calculateOneNeighbor(grid.getCell(new Coordinate(1, 0)));
    }

    private void topRightCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow() - 1, coordinate.getCol() + 1)));
    }

    private void topMiddleCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow() - 1, coordinate.getCol())));
    }

    private void topLeftCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow() - 1, coordinate.getCol() - 1)));
    }

    private void middleLeftCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow(), coordinate.getCol() - 1)));
    }

    private void middleRightCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow(), coordinate.getCol() + 1)));
    }

    private void bottomLeftCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow() + 1, coordinate.getCol() - 1)));
    }

    private void bottomMiddleCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow() + 1, coordinate.getCol())));
    }

    private void bottomRightCell(Grid grid) {
        calculateOneNeighbor(grid.getCell(new Coordinate(coordinate.getRow() + 1, coordinate.getCol() + 1)));
    }

    private void calculateOneNeighbor(Cell cell) {
        if(cell.isAlive) {
            countOfLiveNeighbors++;
        }
    }

    public void setAlive() {
        this.isAlive = true;
    }
}
