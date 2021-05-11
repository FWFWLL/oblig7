public final class Tuple {
    int x;
    int y;

    Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {return "(" + x + ", " + y + ")";}
}