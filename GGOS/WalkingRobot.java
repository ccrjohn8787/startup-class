package Google;

/**
 * Created by siyuzhan on 5/7/16.
 */
public class WalkingRobot {
    /**
     * 设计乌龟，一开始有两个方法，往前走一步，右转90度。
     * 然后说设计个方法可以执行一串命令：e.g., "FRRF"代表走、转、转、走。然后命令要支持括号和数字：e.g., "F97[RF[F]]"代表走一步，然后执行括号里的命令9*7次。
     */

    int row, col;
    int currDir; // 0 - up, 1 - right, 2 - down, 3 - left

    public WalkingRobot() {
        this.row = this.col = 0;
        this.currDir = 0;
    }

    public void forward() {
        switch(currDir) {
            case 0:
                row++;
                break;
            case 1:
                col++;
                break;
            case 2:
                row--;
                break;
            case 3:
                col--;
                break;
            default:
                break;
        }
    }

    public void turnRight() {
        currDir = (currDir + 1)%4;
    }

    public void execute(String command) {
        //decompress command
        for (char c: command.toCharArray()) {
            if (c == 'F') {
                forward();
            } else if (c == 'R') {
                turnRight();
            }
        }
    }
}
