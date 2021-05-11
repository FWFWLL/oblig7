import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;

public final class Labyrinth {
    private Tile[][] tiles;
    private final int rows;
    private final int columns;
    private ArrayList<ArrayList<Tuple>> exitPaths = new ArrayList<ArrayList<Tuple>>();

    /* Standard read from file into 2D-array */
    Labyrinth(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        rows = scanner.nextInt();
        columns = scanner.nextInt();
        tiles = new Tile[rows][columns];
        for(int row = 0; row < rows; row++) {
            char[] charArray = scanner.next().toCharArray();
            for(int column = 0; column < columns; column++) {
                /* Assigns regular tiles */
                switch(charArray[column]) {
                    case '#' -> tiles[row][column] = new Wall(row, column, this);
                    case '.' -> tiles[row][column] = new Path(row, column, this);
                }
                /* Assigns openings */
                if(charArray[column] == '.') if(column == 0 || column == columns - 1 || row == 0 || row == rows - 1) tiles[row][column] = new Opening(row, column, this);
                /* Assigns horizontal neighbours */
                Tile temp = tiles[row][column];
                if(column != 0) {
                    temp.setWest(tiles[row][column - 1]);
                    temp.getWest().setEast(temp);
                }
                /* Assigns vertical neighbours */
                if(row != 0) {
                    temp.setNorth(tiles[row - 1][column]);
                    temp.getNorth().setSouth(temp);
                }
            }
        }
    }

    public void addExitPath(ArrayList<Tuple> path) {exitPaths.add(path);}

    public void findExitFrom(int x, int y) {
        exitPaths = new ArrayList<ArrayList<Tuple>>(); // Clear the paths we had saved
        tiles[y][x].findExit();

        System.out.println("Paths:");
        /* Lambda functions are fun :) */
        exitPaths.forEach(paths -> {paths.forEach(tuple -> System.out.println(tuple)); System.out.println();});
    }

    public String toString() {
        String temp = "";
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {temp += tiles[row][column].toChar();}
            if(row != rows - 1) temp += "\n";
        }
        return temp;
    }

    /* Graphical User Interface Labyrinth */
    public final class Layout {
        private JFrame frame;
        private FButton[][] buttonGrid;

        Layout() {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            buttonGrid = new FButton[rows][columns];
            for(int row = 0; row < rows; row++) {
                for(int column = 0; column < columns; column++) {
                    FButton temp = new FButton(row, column);
                    temp.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {Labyrinth.this.findExitFrom(temp.column, temp.row); getPath();}});
                    temp.setOpaque(true);
                    if(tiles[row][column] instanceof Wall) {
                        temp.setBackground(Color.BLACK);
                        frame.add(temp);
                    } else {
                        temp.setBackground(Color.WHITE);
                        frame.add(temp);
                    }
                    buttonGrid[row][column] = temp;
                }
            }
            frame.setLayout(new GridLayout(rows, columns));
            frame.setSize(666, 666);
            frame.setVisible(true);
        }

        /* Recolors all Paths to white */
        public void cum() {for(int row = 0; row < rows; row++) for(int column = 0; column < columns; column++) if(tiles[row][column] instanceof Path) buttonGrid[row][column].setBackground(Color.WHITE);}

        public void getPath() {
            cum();
            ArrayList<Tuple> temp = null;
            if(exitPaths.size() > 0) {
                for(ArrayList<Tuple> path : exitPaths) {
                    /* Calculating the shortest path */
                    if(temp == null) temp = path;
                    if(path != temp && path.size() == temp.size()) {
                        for(Tuple tuple : path) {
                            buttonGrid[tuple.y][tuple.x].setBackground(Color.RED);
                        }
                        for(Tuple tuple : temp) {
                            buttonGrid[tuple.y][tuple.x].setBackground(Color.RED);
                        }
                    }
                    if(path.size() < temp.size() || exitPaths.size() == 1) {
                        for(Tuple tuple : path) {
                            buttonGrid[tuple.y][tuple.x].setBackground(Color.RED);
                        }
                        System.out.println("Path");
                    }
                    if(path.size() > temp.size()) {
                        for(Tuple tuple : temp) {
                            buttonGrid[tuple.y][tuple.x].setBackground(Color.RED);
                        }
                        System.out.println("Temp");
                    }
                }
            }
        }

        /* Making own FButton class that is almost identical to JButton but can store positionals */
        public final class FButton extends JButton {
            final int row;
            final int column;

            FButton(int row, int column) {
                super();
                this.row = row;
                this.column = column;
            }
        }
    }
}