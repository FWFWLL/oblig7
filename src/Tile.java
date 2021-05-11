import java.util.ArrayList;

public abstract class Tile {
    protected final int row;
    protected final int column;
    protected final Labyrinth labyrinth; // Reference to the labyrinth it is part off

    /* Neighbouring tiles */
    protected Tile north;
    protected Tile east;
    protected Tile south;
    protected Tile west;

    Tile(int row, int column, Labyrinth labyrinth) {
        this.row = row;
        this.column = column;
        this.labyrinth = labyrinth;
    }
    
    abstract char toChar();
    abstract void walk(Tile excludedTile, ArrayList<Tuple> path);

    /* Neighbour-getters */
    public Tile getNorth() {return north;}
    public Tile getEast() {return east;}
    public Tile getSouth() {return south;}
    public Tile getWest() {return west;}

    /* Neighbour-setters */
    public void setNorth(Tile tile) {north = tile;}
    public void setEast(Tile tile) {east = tile;}
    public void setSouth(Tile tile) {south = tile;}
    public void setWest(Tile tile) {west = tile;}

    public void findExit() {this.walk(null, new ArrayList<Tuple>());}

    public String toString() {return "(" + row + ", " + column + ")";}
}