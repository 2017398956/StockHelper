package com.nfl.doublecolorball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallNumberCreator {

    private List<Integer> ballNumbers = new ArrayList<>();
    private List<Integer> filterNumbers = null , filterNumbersOrgin = new ArrayList<>();
    private int length, max;

    private BallNumberCreator() {
    }

    public BallNumberCreator(List<Integer> filterNumbers, int length, int max) {
        this.filterNumbers = filterNumbers == null ? new ArrayList<>() : filterNumbers;
        this.filterNumbersOrgin.addAll(this.filterNumbers) ;
        this.length = length;
        this.max = max;
    }

    public void createBallNumberList() {
        if (ballNumbers.size() < length) {
            int selectNumber = new Random().nextInt(max) + 1;
            if (filterNumbers.size() > 0) {
                for (int number : filterNumbers) {
                    if (selectNumber == number) {
                        createBallNumberList();
                        return;
                    }
                }
            }
            ballNumbers.add(selectNumber);
            if (ballNumbers.size() < length) {
                createBallNumberList();
            }
        }
    }

    public List<Integer> getBallNumbers() {
        return ballNumbers;
    }

    public void reset(){
        ballNumbers.clear();
    }
}
