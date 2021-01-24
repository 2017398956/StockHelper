package personal.nfl.doublecolorball;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DoubleColorBall {

    private static int[] prizeRedBallNumbers = new int[]{4, 19, 20, 22, 28, 33};
    private static int prizeBlueBallNumber = 6;
    private static float money = 0;
    private static int tickets = 100000 ;

    public static void main(String[] args) throws IOException {
//        File file = new File("F:\\redblue.txt") ;
//        FileWriter fileWriter = new FileWriter(file) ;
        BallNumberCreator redBallNumberCreator = new BallNumberCreator(Arrays.asList(1, 5, 11, 22, 23, 26 ), 6, 33);
        BallNumberCreator blueBallNumberCreator = new BallNumberCreator(Arrays.asList(15, 10, 4, 9, 3), 1, 16);
//
//        BallNumberCreator redBallNumberCreator = new BallNumberCreator(null , 6, 33);
//        BallNumberCreator blueBallNumberCreator = new BallNumberCreator(null , 1, 16);

        for (int i = 0; i < tickets ; i++) {
            redBallNumberCreator.createBallNumberList();
            blueBallNumberCreator.createBallNumberList();
            System.out.println(redBallNumberCreator.getBallNumbers() + " " + blueBallNumberCreator.getBallNumbers());
            sumMoney(redBallNumberCreator.getBallNumbers(), blueBallNumberCreator.getBallNumbers());
//            fileWriter.append(redBallNumberCreator.getBallNumbers() + " " + blueBallNumberCreator.getBallNumbers()) ;
//            fileWriter.append("\n") ;
//            fileWriter.flush();
            redBallNumberCreator.reset();
            blueBallNumberCreator.reset();
        }


        System.out.println("获利：" + (money - tickets * 2));
    }

    private static void sumMoney(List<Integer> redBallNumbers, List<Integer> blueBallNumbers) {
        int num = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (redBallNumbers.get(i) == prizeRedBallNumbers[j]) {
                    num++;
                }
            }
        }

        if (num < 3) {
            if (prizeBlueBallNumber == blueBallNumbers.get(0)) {
                money += 5;
            }
        } else {
            num += prizeBlueBallNumber == blueBallNumbers.get(0) ? 1 : 0;
            if (num == 4) {
                money += 10;
            } else if (num == 5) {
                money += 200;
            } else if (num == 6) {
                if (prizeBlueBallNumber == blueBallNumbers.get(0)) {
                    money += 3000;
                } else {
                    money += 5000000;
                }
            } else if (num == 7) {
                money += 10000000;
            }
        }
    }
}
