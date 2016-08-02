
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
            calculateOneNeighbor(grid.getCell(new Coordinate(0, 1)));
            calculateOneNeighbor(grid.getCell(new Coordinate(1, 1)));
            calculateOneNeighbor(grid.getCell(new Coordinate(1, 0)));
        } else if(coordinate.getRow() == 0 && coordinate.getCol() == Grid.MAX_COLS - 1) {
            middleLeftCell(grid);
            bottomLeftCell(grid);
            bottomMiddleCell(grid);
        } else if(coordinate.getRow() == Grid.MAX_ROWS  - 1 && coordinate.getCol() == 0) {
            topMiddleCell(grid);
            topRightCell(grid);
            middleRightCell(grid);
        } else if(coordinate.getRow() == Grid.MAX_ROWS  - 1 && coordinate.getCol() == Grid.MAX_COLS - 1) {
            topLeftCell(grid);
            topMiddleCell(grid);
            middleLeftCell(grid);

        // CHECK Top Middle
        } else if(coordinate.getRow() == 0) {
            middleLeftCell(grid);
            middleRightCell(grid);

            bottomLeftCell(grid);
            bottomMiddleCell(grid);
            bottomRightCell(grid);

        // CHECK Bottom Middle
        } else if (coordinate.getRow() == Grid.MAX_ROWS - 1) {
            topLeftCell(grid);
            topMiddleCell(grid);
            topRightCell(grid);

            middleLeftCell(grid);
            middleRightCell(grid);

        // CHECK Left Middle
        } else if (coordinate.getCol() == 0) {
            topMiddleCell(grid);
            bottomMiddleCell(grid);

            topRightCell(grid);
            middleRightCell(grid);
            bottomRightCell(grid);

        // CHECK Right Middle
        } else if (coordinate.getCol() == Grid.MAX_COLS - 1) {
            topMiddleCell(grid);
            bottomMiddleCell(grid);

            topLeftCell(grid);
            middleLeftCell(grid);
            bottomLeftCell(grid);

        // CHECK Middle Middle
        } else {
            topLeftCell(grid);
            topMiddleCell(grid);
            topRightCell(grid);

            middleLeftCell(grid);
            middleRightCell(grid);

            bottomLeftCell(grid);
            bottomMiddleCell(grid);
            bottomRightCell(grid);
        }

        calculateSelfLife();
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

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }
}
