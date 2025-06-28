package com.web3.web3.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Point bean.
 */
/*@ManagedBean*/
@SessionScoped
@Named
public class PointBean implements Serializable{
    /**
     *
     */
    private double x;
    /**
     *
     */
    private double y;
    /**
     *
     */
    private double r = 1;
    /**
     *
     */
    private boolean result;
    /**
     *
     */
    private long calculationTime;
    /**
     *
     */
    private LocalDateTime calculatedAt;

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets r.
     *
     * @return the r
     */
    public double getR() {
        return r;
    }

    /**
     * Sets r.
     *
     * @param r the r
     */
    public void setR(double r) {
        this.r = r;
    }

    /**
     * Check point.
     */
    public void checkPoint() {
        this.result = AreaResultChecker.getResult(x,y,r);
        ResultDataBean nRes = new ResultDataBean();
        nRes.setR(r);
        nRes.setX(x);
        nRes.setY(y);
        nRes.setResult(result);

        /*if(res != null) {
            res.addResult(nRes);
        }else{
            res.addResult(nRes);
        }*/
    }

    /**
     * Is result boolean.
     *
     * @return the boolean
     */
    public boolean isResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * Gets calculation time.
     *
     * @return the calculation time
     */
    public long getCalculationTime() {
        return calculationTime;
    }

    /**
     * Sets calculation time.
     *
     * @param calculationTime the calculation time
     */
    public void setCalculationTime(long calculationTime) {
        this.calculationTime = calculationTime;
    }

    /**
     * Gets calculated at.
     *
     * @return the calculated at
     */
    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    /**
     * Sets calculated at.
     *
     * @param calculatedAt the calculated at
     */
    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }



    // Геттеры и сеттеры
}


// new content
// new content1
