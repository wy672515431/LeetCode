package LeetCode;

public class 到达终点 {
    /**
     * 给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。

     从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     * 反向考虑,对于(tx,ty)这一点，上一状态为
     * 如果tx = ty 不存在上一状态
     * tx > ty (tx - ty, ty)
     * tx < ty (tx, ty - tx)
     * 
     * 满足 tx > sx, ty > sy
     * 当反向操作不存在时
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx = tx % ty;
            } else if (tx < ty) {
                ty = ty % tx;
            }
        }
        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx && (ty - sy) % tx == 0) {
            return ty > sy && true;
        } else if (ty == sy && (tx - sx) % ty == 0) {
            return tx > sx && true;
        } else {
            return false;
        }
    }
}