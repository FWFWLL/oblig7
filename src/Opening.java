import java.util.ArrayList;

public final class Opening extends Path {
    Opening(int row, int column, Labyrinth labyrinth) {super(row, column, labyrinth);}

    public void walk(Tile excludedTile, ArrayList<Tuple> path) {
        ArrayList<Tuple> completePath = new ArrayList<Tuple>(path);
        if(completePath.size() < 1) super.walk(null, new ArrayList<Tuple>());
        completePath.add(new Tuple(column, row));
        labyrinth.addExitPath(completePath);
    }
}