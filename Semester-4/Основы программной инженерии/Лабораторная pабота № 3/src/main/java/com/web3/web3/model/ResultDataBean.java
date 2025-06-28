package com.web3.web3.model;

import jakarta.annotation.Generated;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * The type Result data bean.
 */
@Entity
@Table(name = "results", schema = "s373432")
public class ResultDataBean implements Serializable {
    /**
     *
     */
    @Id
    @Column
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator"
    )
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "lab3_x_test_table_id_seq",
            allocationSize = 1
    )
    private int id;
    /**
     *
     */
    @Column(name = "x")
    private double x;
    /**
     *
     */
    @Column(name = "y")
    private double y;
    /**
     *
     */
    @Column(name = "r")
    private double r;
    /**
     *
     */
    @Column(name = "result")
    private boolean result;

    /**
     *
     */
    @Column(name = "calculationTime")
    private long calculationTime;

    /**
     *
     */
    @Column(name = "calculatedAt")
    private LocalDateTime calculatedAt;

    /**
     * Gets x.
     *
     * @return the x
     */
    @Column(name = "x")
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
    @Column(name = "y")
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
    @Column(name = "r")
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
     * Gets result.
     *
     * @return the result
     */
    @Column(name = "result")
    public boolean getResult() {
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
     * Gets id.
     *
     * @return the id
     */
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Is result boolean.
     *
     * @return the boolean
     */
    @Column(name = "result")
    public boolean isResult() {
        return result;
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
}
