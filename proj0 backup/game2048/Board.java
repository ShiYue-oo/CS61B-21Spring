package game2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author hug
 */
public class Board implements Iterable<Tile> {
    /** Current contents of the board. */
    private Tile[][] values;
    /** Side that the board currently views as north. */
    private Side viewPerspective;  //  ？？

    public Board(int size) {
        values = new Tile[size][size];
        viewPerspective = Side.NORTH;
    }

    /** Shifts the view of the board such that the board behaves as if side S is north.
     * 移动电路板的视图，使电路板的行为就像 S 侧为北。 s是一个参数，可以传四个方向，之前搞错了*/
    public void setViewingPerspective(Side s) {
        viewPerspective = s;
    }

    /** Create a board where RAWVALUES hold the values of the tiles on the board 
     * (0 is null) with a current score of SCORE and the viewing perspective set to north. */
//    把rawvalues矩阵的数字转换成tile左右倒个个儿存在values里面
    public Board(int[][] rawValues, int score) {
        int size = rawValues.length;
        values = new Tile[size][size];
        viewPerspective = Side.NORTH;
        for (int col = 0; col < size; col += 1) {
            for (int row = 0; row < size; row += 1) {
                int value = rawValues[size - 1 - row][col];
                Tile tile;
                if (value == 0) {
                    tile = null;
                } else {
                    tile = Tile.create(value, col, row);
                }
                values[col][row] = tile;
            }
        }
    }

    /** Returns the size of the board. */
//    size是二维矩阵有多少行
    public int size() {
        return values.length;
    }

    /** Shifts the view of the Board. 起始 */
    public void startViewingFrom(Side s) {
        viewPerspective = s;
    }

    /** Return the current Tile at (COL, ROW), when sitting with the board
     *  oriented so that SIDE is at the top (farthest) from you. */
    private Tile vtile(int col, int row, Side side) { //
        return values[side.col(col, row, size())][side.row(col, row, size())];
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there. */
    public Tile tile(int col, int row) {
        return vtile(col, row, viewPerspective);
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        for (Tile[] column : values) {
            Arrays.fill(column, null);
        }
    }

    /** Adds the tile T to the board */
    public void addTile(Tile t) {
        values[t.col()][t.row()] = t;
    }

    /** Places the Tile TILE at column COL, row ROW where COL and ROW are
     * treated as coordinates with respect to the current viewPerspective.
     *
     * 将瓷砖Tile放置在列COL，行row上，其中COL和row被视为相对于当前视图透视图的坐标。
     * Returns whether or not this move is a merge.
     * */
    public boolean move(int col, int row, Tile tile) {   //tile是要进行移动的tile col row是虚拟坐标
        int pcol = viewPerspective.col(col, row, size()),   // 转换为实际坐标
                prow = viewPerspective.row(col, row, size());
        if (tile.col() == pcol && tile.row() == prow) {
            return false; //说明根本没有移动
        }
        Tile tile1 = vtile(col, row, viewPerspective);    //得到要移动到的终点位置的tile
        values[tile.col()][tile.row()] = null;  //要把本tile移走，先清空这个位置

        if (tile1 == null) {
            values[pcol][prow] = tile.move(pcol, prow);
            return false;
        } else {
            values[pcol][prow] = tile.merge(pcol, prow, tile1);
            return true;
        }
    }

    @Override
    /** Returns the board as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        return out.toString();
    }

    /** Iterates through teach tile in the board. */
    private class AllTileIterator implements Iterator<Tile>, Iterable<Tile> {
        int r, c;

        AllTileIterator() {
            r = 0;
            c = 0;
        }

        public boolean hasNext() {
            return r < size();
        }

        public Tile next() {
            Tile t = tile(c, r);
            c = c + 1;
            if (c == size()) {
                c = 0;
                r = r + 1;
            }
            return t;
        }

        public Iterator<Tile> iterator() {
            return this;
        }
    }

    public Iterator<Tile> iterator() {
        return new AllTileIterator();
    }

}
