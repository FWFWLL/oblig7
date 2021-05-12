import java.util.ArrayList;

public class Path extends Tile {
    Path(int row, int column, Labyrinth labyrinth) {super(row, column, labyrinth);}

    public char toChar() {return '.';}

    public void walk(Tile excludedTile, ArrayList<Tuple> path) {
        ArrayList<Tuple> newPath = new ArrayList<Tuple>(path);
        boolean visited = false;
        for(Tuple tuple : newPath) if(tuple.x == this.column && tuple.y == this.row) visited = true;
        if(!visited) {
            newPath.add(new Tuple(column, row));
            try {if(!north.equals(excludedTile)) north.walk(this, newPath);}
            catch(NullPointerException e) {}
            try {if(!east.equals(excludedTile)) east.walk(this, newPath);}
            catch(NullPointerException e) {}
            try {if(!south.equals(excludedTile)) south.walk(this, newPath);}
            catch(NullPointerException e) {}
            try {if(!west.equals(excludedTile)) west.walk(this, newPath);}
            catch(NullPointerException e) {}
        }
    }
}