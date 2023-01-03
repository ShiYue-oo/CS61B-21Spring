package game2048;

/** Symbolic names for the four sides of a board.
 *  @author P. N. Hilfinger */
public enum Side {
    /** The parameters (COL0, ROW0, DCOL, and DROW) for each of the
      symbolic directions, D, below are to be interpreted as follows:
          The board's standard orientation has the top of the board
          as NORTH, and rows and columns (see Model) are numbered
          from its lower-left corner. Consider the board oriented
          so that side D of the board is farthest from you. Then
              (COL0*s, ROW0*s) are the standard coordinates of the
               lower-left corner of the reoriented board (where s is the
               board size), and
              If (c, r) are the standard coordinates of a certain
               square on the reoriented board, then (c+DCOL, r+DROW)
               are the standard coordinates of the squares immediately
              above it on the reoriented board.
       The idea behind going to this trouble is that by using the
       col() and row() methods below to translate from reoriented to
       standard coordinates, one can arrange to use exactly the same code
       to compute the result of tilting the board in any particular
       direction.
     下面每个符号方向 D 的参数（COL0、ROW0、DCOL 和 DROW）应解释如下：
     电路板的标准方向为板的顶部为北，行和列（请参阅模型）从其左下角编号。
     考虑电路板的方向，以便电路板的 D 面离您最远。然后 （COL0*s， ROW0*s） ---------这是转换后的坐标系的左下角在原来标准坐标系下的坐标
     是重新定向板左下角的标准坐标（其中 s 是板大小），如果 （c， r）
     是重新定向板上某个正方形的标准坐标，则 （c+DCOL， r+DROW） 是
     重新定向板上紧靠其上方的正方形的标准坐标。解决这个麻烦背后的想法是，
     通过使用下面的 col（） 和 row（） 方法从重定向到标准坐标转换，可以
     安排使用完全相同的代码来计算在任何特定方向上倾斜电路板的结果。

     */

    NORTH(0, 0, 0, 1), EAST(0, 1, 1, 0), SOUTH(1, 1, 0, -1),
    WEST(1, 0, -1, 0);

    /** The side that is in the direction (DCOL, DROW) from any square
       of the board.  Here, "direction (DCOL, DROW) means that to
       move one space in the direction of this Side increases the row
       by DROW and the colunn by DCOL.  (COL0, ROW0) are the row and
       column of the lower-left square when sitting at the board facing
       towards this Side. */
    Side(int col0, int row0, int dcol, int drow) {
        this.row0 = row0;
        this.col0 = col0;
        this.drow = drow;
        this.dcol = dcol;
    }

    /** Returns the side opposite of side S. */
    static Side opposite(Side s) {
        if (s == NORTH) {
            return SOUTH;
        } else if (s == SOUTH) {
            return NORTH;
        } else if (s == EAST) {
            return WEST;
        } else {
            return EAST;
        }
    }

    /** Return the standard column number for square (C, R) on a board
     *  of size SIZE oriented with this Side on top. 将虚拟地址转换成实际地址*/
    public int col(int c, int r, int size) {
        return col0 * (size - 1) + c * drow + r * dcol;
    }

    /** Return the standard row number for square (C, R) on a board
     *  of size SIZE oriented with this Side on top. */
    public int row(int c, int r, int size) {
        return row0 * (size - 1) - c * dcol + r * drow;
    }

    /** Parameters describing this Side, as documented in the comment at the
     *  start of this class. */
    private int row0, col0, drow, dcol;

};
