import java.util.Set;
import java.util.HashSet;

public class Bishops {

  public static final int NORTHWEST = -9;
  public static final int NORTHEAST = -7;
  public static final int SOUTHWEST =  7;
  public static final int SOUTHEAST =  9;

  public static int column(int n) {
    return (n - 1) % 8;
  }

  public static int row(int n) {
    return (n - 1) / 8;
  }

  public static boolean inequality(int x, String operator, int y) {
    if(operator == "<") {
      return x < y;
    } else {
      return x > y;
    }
  }

  public static Set<Integer> tilesToThe(int direction, int n) {
    Set<Integer> set = new HashSet<>();
    String columnOperand = "", rowOperand = "";
    int tile = n + direction;

    switch(direction) {
      case NORTHWEST: columnOperand = "<";
                      rowOperand = "<";
                      break;
      case NORTHEAST: columnOperand = ">";
                      rowOperand = "<";
                      break;
      case SOUTHWEST: columnOperand = "<";
                      rowOperand = ">";
                      break;
      case SOUTHEAST: columnOperand = ">";
                      rowOperand = ">";
                      break;
    }

    for(int i = 1;
        inequality(column(tile), columnOperand, column(n)) &&
        inequality(row(tile), rowOperand, row(n)) &&
        tile > 0 &&
        tile < 65;
        i++, tile += direction)
      set.add(tile);

    return set;
  }

  public static Set<Integer> oneMoveAwayFrom(int n) {

    Set<Integer> set = new HashSet<>();

    set.addAll(tilesToThe(NORTHWEST, n));
    set.addAll(tilesToThe(SOUTHWEST, n));
    set.addAll(tilesToThe(SOUTHEAST, n));
    set.addAll(tilesToThe(NORTHEAST, n));

    return set;
  }

  public static int movesRequired(int start, int end) {
    if(start == end) return 0;
    if(start < 1 || start > 64) return -1;
    if(end < 1 || end > 64) return -1;

    Set<Integer> startSet = Bishops.oneMoveAwayFrom(start);

    if(startSet.contains(end)) return 1;

    Set<Integer> endSet = Bishops.oneMoveAwayFrom(end);

    if(startSet.isEmpty()) return -1;

    return 2;
  }

  public static void main(String[] args) {
    if(args.length != 2) {
      System.out.println("Usage:");
      System.out.println("\tjava Bishops {1..64} {1..64}");
      System.exit(0);
    }

    System.out.println("Moves required:\t" + Bishops.movesRequired(
          Integer.parseInt(args[0]),
          Integer.parseInt(args[1])
    )); 

  }
}
