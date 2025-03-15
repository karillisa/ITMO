package com.web3.web3.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;

@SessionScoped
@Named
public class PointBean implements Serializable{
    private double x;
    private double y;
    private double r = 1;
    private boolean result;
    private long calculationTime;
    private LocalDateTime calculatedAt;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void checkPoint() {
        this.result = AreaResultChecker.getResult(x,y,r);
        ResultDataBean nRes = new ResultDataBean();
        nRes.setR(r);
        nRes.setX(x);
        nRes.setY(y);
        nRes.setResult(result);


    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public long getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(long calculationTime) {
        this.calculationTime = calculationTime;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }



}

