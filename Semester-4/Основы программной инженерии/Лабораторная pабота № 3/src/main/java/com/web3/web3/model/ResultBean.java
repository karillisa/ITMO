package com.web3.web3.model;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Result bean.
 */
@Named
@SessionScoped
@ManagedBean
public class ResultBean implements Serializable {
    /**
     *
     */
    private LinkedList<ResultDataBean> results;

    /**
     * Instantiates a new Result bean.
     */
    public ResultBean() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("results");
        EntityManager em = emf.createEntityManager();

        this.results = retrieveDataFromDatabase(em);

        em.close();
        emf.close();
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    @Named(value = "resultList")
    public LinkedList<ResultDataBean> getResults() {
        return this.results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(LinkedList<ResultDataBean> results) {
        this.results = results;
    }

    /**
     * Get result result data bean.
     *
     * @param x the x
     * @param y the y
     * @param r the r
     * @return the result data bean
     */
    public ResultDataBean getResult(Double x, Double y, Double r){
        ResultDataBean s = new ResultDataBean();
        s.setR(r);
        s.setY(y);
        s.setX(x);
        PointBean cnt = new PointBean();
        s.setResult(AreaResultChecker.getResult(x, y, r));

        return s;
    }

    /**
     * Add result data.
     *
     * @param res the res
     */
    public void addResultData(ResultDataBean res){
        final long startExec = System.nanoTime();

        if(res != null) {
            if (this.getResults() == null || this.getResults().isEmpty()) {
                this.setResults(new LinkedList<>());
                /*res.setId(1);*/

                final long endExec = System.nanoTime();
                final long executionTime = endExec - startExec;
                final LocalDateTime executedAt = LocalDateTime.now();

                res.setCalculatedAt(executedAt);
                res.setCalculationTime(executionTime);

                this.getResults().addFirst(res);
                System.out.println("hello");
                /*this.getResults().stream().map(p -> p.getId() + " " + p.getX() + " " + p.getY() + " " + p.getR() + " " + p.getResult()).forEach(System.out::println);*/
            } else {
                /*res.setId(this.getResults().getLast().getId() + 1);*/

                final long endExec = System.nanoTime();
                final long executionTime = endExec - startExec;
                final LocalDateTime executedAt = LocalDateTime.now();

                res.setCalculatedAt(executedAt);
                res.setCalculationTime(executionTime);

                this.getResults().addFirst(res);
                this.getResults().stream().map(p -> p.getX() + " " + p.getY() + " " + p.getR() + " " + p.getResult()).forEach(System.out::println);
                System.out.println();
            }

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("results");
            EntityManager em = emf.createEntityManager();

            // Теперь вы можете использовать EntityManager для сохранения и извлечения данных.
            // Например, для сохранения объекта:
            em.getTransaction().begin();
            em.persist(res);
            em.getTransaction().commit();
            /*

            this.results = retrieveDataFromDatabase(em);
*/
            em.close();

        }

    }

    /**
     * Delete.
     */
    public void delete(){
        LinkedList <ResultDataBean> s = new LinkedList<>();
        this.setResults(s);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("results");
        EntityManager em = emf.createEntityManager();

        clearTable(em, "ResultDataBean");

        em.close();
        emf.close();

        System.out.println("deleted!");
    }

    /**
     * Retrieve data from database linked list.
     *
     * @param em the em
     * @return the linked list
     */
    public static LinkedList<ResultDataBean> retrieveDataFromDatabase(EntityManager em) {
        TypedQuery<ResultDataBean> query = em.createQuery("SELECT r FROM ResultDataBean r", ResultDataBean.class);
        List<ResultDataBean> resultDataList = query.getResultList();

        LinkedList<ResultDataBean> resultList = new LinkedList<>();
        for (ResultDataBean resultData : resultDataList) {
            resultList.addFirst(resultData);
        }

        return resultList;
    }

    /**
     * Clear table.
     *
     * @param em        the em
     * @param tableName the table name
     */
    @Transactional
    public static void clearTable(EntityManager em, String tableName) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM " + tableName).executeUpdate();
        em.getTransaction().commit();
    }
}
