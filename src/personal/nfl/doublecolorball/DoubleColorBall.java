package personal.nfl.doublecolorball;

import java.util.Arrays;
import java.util.List;

/**
 * 最新中奖号码公布后，根据自定义的规则，做测试
 */
public class DoubleColorBall {

    // 本期中奖的红球号码
    private static int[] prizeRedBallNumbers = new int[]{2, 4, 7, 24, 25, 32};
    // 本期中奖的蓝球号码
    private static int prizeBlueBallNumber = 13;
    // 要过滤掉的红球号码
    private static List<Integer> redBallFilter = Arrays.asList(2, 3, 13, 24, 33, 25);
    // 要过滤掉的蓝球号码
    private static List<Integer> blueBallFilter = Arrays.asList(11, 1, 7, 3, 9);
    // 预测收益
    private static float money = 0;
    // 购买票数
    private static int tickets = 100;

    public static void main(String[] args) {

        BallNumberCreator redBallNumberCreator = new BallNumberCreator(redBallFilter, 6, 33);
        BallNumberCreator blueBallNumberCreator = new BallNumberCreator(blueBallFilter, 1, 16);

        for (int i = 0; i < tickets; i++) {
            redBallNumberCreator.reCreateBallNumberList();
            blueBallNumberCreator.reCreateBallNumberList();
            sumMoney(redBallNumberCreator.getBallNumbers(), blueBallNumberCreator.getBallNumbers());
        }
        System.out.println("共购买了 " + tickets + " 张票，获利：" + (money - tickets * 2));
    }

    private static void sumMoney(List<Integer> redBallNumbers, List<Integer> blueBallNumbers) {
        // 与红球相同的个数
        int num = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (redBallNumbers.get(i) == prizeRedBallNumbers[j]) {
                    num++;
                }
            }
        }

        if (num < 3) {
            // 如果红球的个数少于 3 ，当篮球中了的时候收益 5 元
            if (prizeBlueBallNumber == blueBallNumbers.get(0)) {
                // 六等奖是1个蓝色球号码相符，单注奖金额固定为5元。
                money += 5;
            }
        } else {
            num += ((prizeBlueBallNumber == blueBallNumbers.get(0)) ? 1 : 0);
            if (num == 4) {
                // 五等奖是4个红色球号码或3个红色球号码和1个蓝色球号码相符，单注奖金额固定为10元；
                money += 10;
            } else if (num == 5) {
                // 四等奖是5个红色球号码或4个红色球号码和1个蓝色球号码相符，单注奖金额固定为200元
                money += 200;
            } else if (num == 6) {
                if (prizeBlueBallNumber == blueBallNumbers.get(0)) {
                    // 三等奖是5个红色球号码和1个蓝色球号码相符，单注奖金额固定为3000
                    money += 3000;
                } else {
                    // 二等奖是6个红色球号码相符，单注最高限额封顶500万元；
                    money += 5000000;
                }
            } else if (num == 7) {
                // 一等奖是7个号码相符，单注最高限额封顶1000万元；
                money += 10000000;
            }
        }
    }
}
