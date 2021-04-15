package com.example.lab_3;
import javax.annotation.ManagedBean;
import javax.annotation.ManagedBean.*;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.Session;
import javax.persistence.*;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListInformation implements Serializable {
    private Information information;
    private List<Information> informationList;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    private void connection () {
        entityManagerFactory = Persistence.createEntityManagerFactory("@localhost");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    private void loadInformationList() {
        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT e FROM Information e");
            informationList = query.getResultList();
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }
    public ListInformation() {
        information = new Information();
        informationList = new ArrayList<>();
        connection();
        loadInformationList();
    }

    public void add() {
        try {
            transaction.begin();
            information.setResult(information.chekPenetration(information.getCoordinateX(), information.getCoordinateY(), information.getValueR()));
            entityManager.persist(information);
            informationList.add(information);
            information = new Information();
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
////        return "redirect";
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("@localhost");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(information);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
//        information = new Information();
    }

    public void deleteList() {
        try {
            transaction.begin();
            Query query = entityManager.createQuery("DELETE FROM Information");
            query.executeUpdate();
            informationList.clear();
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Information getInformation() {
        return information;
    }

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }
}