package com.web3.web3.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;
import java.util.LinkedList;

import static com.web3.web3.model.ResultBean.retrieveDataFromDatabase;

public class AreaResultChecker {

    @PostConstruct
    public void init() throws SQLException {
        ResultBean res = new ResultBean();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("results");
        EntityManager em = emf.createEntityManager();

        LinkedList<ResultDataBean> resultList = retrieveDataFromDatabase(em);

        em.close();
        emf.close();
    }

    public static boolean getResult(double x, double y, double r) {
        // Проверка квадрата (верхняя левая часть)
        boolean inSquare = (x >= -r / 2 && x <= 0 && y >= 0 && y <= r);

        // Проверка прямоугольного треугольника (верхняя правая часть)
        boolean inTriangle = (x >= 0 && y >= 0 && x + y <= r);

        // Проверка четверти окружности (нижняя левая часть)
        boolean inCircle = (x <= 0 && y <= 0 && (x * x + y * y <= r * r));

        return inSquare || inTriangle || inCircle;
    }

}
