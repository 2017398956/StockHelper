package personal.nfl.doublecolorball.bean;

import java.sql.Date;

public class DoubleColorBallBean {

    public int id;
    public Date occur_date;
    public int dateInt;
    public int redBall1;
    public int redBall2;
    public int redBall3;
    public int redBall4;
    public int redBall5;
    public int redBall6;
    public int blueBall;

    public int getRedBall1() {
        return redBall1;
    }

    public void setRedBall1(int redBall1) {
        this.redBall1 = redBall1;
    }

    public int getRedBall2() {
        return redBall2;
    }

    public void setRedBall2(int redBall2) {
        this.redBall2 = redBall2;
    }

    public int getRedBall3() {
        return redBall3;
    }

    public void setRedBall3(int redBall3) {
        this.redBall3 = redBall3;
    }

    public int getRedBall4() {
        return redBall4;
    }

    public void setRedBall4(int redBall4) {
        this.redBall4 = redBall4;
    }

    public int getRedBall5() {
        return redBall5;
    }

    public void setRedBall5(int redBall5) {
        this.redBall5 = redBall5;
    }

    public int getRedBall6() {
        return redBall6;
    }

    public void setRedBall6(int redBall6) {
        this.redBall6 = redBall6;
    }

    public int getBlueBall() {
        return blueBall;
    }

    public void setBlueBall(int blueBall) {
        this.blueBall = blueBall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOccur_date() {
        return occur_date;
    }

    public void setOccur_date(Date occur_date) {
        this.occur_date = occur_date;
    }

    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }

    public int[] getAllBalls() {
        return new int[]{redBall1, redBall2, redBall3, redBall4, redBall5, redBall6, blueBall};
    }
}
