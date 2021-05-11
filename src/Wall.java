import java.util.ArrayList;

public final class Wall extends Tile {
    Wall(int row, int column, Labyrinth labyrinth) {super(row, column, labyrinth);}

    public char toChar() {return '#';}

    public void walk(Tile excludedTile, ArrayList<Tuple> path) {/* Does nothing */}
}