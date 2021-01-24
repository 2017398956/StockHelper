package personal.nfl.doublecolorball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallNumberCreator {

    private List<Integer> ballNumbers = new ArrayList<>();
    private List<Integer> filterNumbers = new ArrayList<>();
    private List<Integer> filterNumbersOrigin = new ArrayList<>();
    private int length;
    private int max;

    public BallNumberCreator(List<Integer> filterNumbers, int length, int max) {
        if (null != filterNumbers) {
            this.filterNumbers.addAll(filterNumbers);
            filterNumbersOrigin = filterNumbers ;
        }
        this.length = length;
        this.max = max;
    }

    private void createBallNumberList() {
        if (ballNumbers.size() < length) {
            int selectNumber = new Random().nextInt(max) + 1;
            if (filterNumbers.size() > 0) {
                for (int number : filterNumbers) {
                    if (selectNumber == number) {
                        createBallNumberList();
                        return;
                    }
                }

                ballNumbers.add(selectNumber);
                filterNumbers.add(selectNumber);
                createBallNumberList();
            } else {
                ballNumbers.add(selectNumber);
                filterNumbers.add(selectNumber);
                createBallNumberList();
            }
        }
    }

    public void reCreateBallNumberList() {
        reset();
        createBallNumberList();
    }

    public List<Integer> getBallNumbers() {
        return ballNumbers;
    }

    private void reset() {
        ballNumbers.clear();
        filterNumbers.clear();
        filterNumbers.addAll(filterNumbersOrigin);
    }
}
