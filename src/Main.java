import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/* Friendly reminder below: */
/* Rows: Vertical */
/* Columns: Horizontal */
/* Array: [row][column] */
/* Maths: (y, x) */ // IMPORTANT: This is the confusing part and requires getting used to
/* Labyrinth.findExitFrom(x, y) */
/* Displayed: (x, y) */ // Holy shit this is getting out of hand, 20.04.21 13:21
/* I'm still cryin' 2021/05/11 19:01 */

public final class Main {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.showOpenDialog(null);
        Labyrinth labyrinth = null;
        try {labyrinth = new Labyrinth(fileChooser.getSelectedFile());}
        catch(FileNotFoundException e) {e.printStackTrace();}
        Labyrinth.Layout layout = labyrinth.new Layout();
    }
}