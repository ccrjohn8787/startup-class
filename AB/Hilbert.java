package Airbnb;

import org.junit.Test;

public class HilbertCurve {
    public int getNumber(int x, int y, int iter) {
        if (iter == 1) {
            System.out.println("iteration 1: (" + x + ", " + y + ")");
            if (x == 0 && y == 0) {
                return 1;
            }
            if (x == 0 && y == 1) {
                return 2;
            }
            if (x == 1 && y == 1) {
                return 3;
            }
            if (x == 1 && y == 0) {
                return 4;
            }
            return 0;
        }

        // calculate which plane it's in
        int plane = 0;
        int prevX = 0;
        int prevY = 0;
        if (x < iter && y < iter) {
            // in plane 0: 原图先顺时针转90, 再上下翻转
            // 要恢复原图坐标: 先上下翻转,再逆时针转90
            plane = 0;
            prevX = iter - (iter - y - 1) - 1;
            prevY = x;
        } else if (x < iter && y >= iter) {
            plane = 1;
            prevX = x;
            prevY = y - iter;
        } else if (x >= iter && y >= iter) {
            plane = 2;
            prevX = x - iter;
            prevY = y - iter;
        } else {
            // in plane 3: 原图先逆时针转90, 再上下翻转
            // 要恢复原图坐标: 先上下翻转,再顺时针转90
            plane = 3;
            prevX = (iter - y - 1);
            prevY = iter - (x - iter) - 1;
        }

        System.out.println("PrevX: " + prevX + " PrevY: " + prevY);
        return plane * getPoints(iter - 1) + getNumber(prevX, prevY, iter - 1);
    }

    public int getPoints(int iter) {
        if (iter <= 0) {
            return 0;
        }
        if (iter == 1) {
            return 4;
        }
        return getPoints(iter - 1) * 4;
    }

    @Test
    public void test() {
        System.out.println(getNumber(2, 2, 2));
    }
}
