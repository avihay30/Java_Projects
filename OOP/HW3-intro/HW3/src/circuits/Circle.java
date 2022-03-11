//package circuits;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Coordinates {
//    public class Coordinate {
//        private int x;
//        private int y;
//
//        public Coordinate(int x, int y){
//            this.x = x;
//            this.y = y;
//        }
//
//        public int getX() { return x; }
//        public int getY() { return this.y; }
//        public void setX(int x) { this.x = x; }
//        public void setY(int y) { this.y = y; }
//            public String toString() { return "(" + this.x + "," + this.y + ")"; }
//    }
//
//    private static ArrayList<Coordinate> coordinates;
//    private int bound;
//
//    public Coordinates(int bound) {
//        coordinates = new ArrayList<Coordinate>();
//        this.bound = bound;
//    }
//
//
//
//    public static void transpose(int x, int y) {
//        coordinates.stream().map(coordinate -> {
//            if (coordinate.x == x && coordinate.y == y) {
//                coordinate.setX(y);
//                coordinate.setY(x);
//            }
//        });
//        coordinates.stream().filter(coordinate.x == x && coordinate.y == y)
//    }
//
//
//    @Override
//    public String toString() {
//        StringBuilder returnCircle = new StringBuilder();
//        for (Coordinate coordinate : coordinates)
//            for()
//
//        return "";
//    }
//
//
//    public static void main(String[] args) {
//
//
//    }
//}
