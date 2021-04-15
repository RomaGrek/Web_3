package com.example.lab_3;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import javax.persistence.*;
/* обычный класс где будет проверка на попадание и поля для всей инфы */
@Entity
public class Information implements Serializable {

    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private int id;

    private Double coordinateX;
    private Double coordinateY;
    private Integer valueR;
    private String result;

    public Information() {
        this(0.0, 0.0, 0, "");
    }

    public Information(Double coordinateX, Double coordinateY, Integer valueR, String result) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.valueR = valueR;
        this.result = result;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public Integer getValueR() {
        return valueR;
    }

    public String getResult() {
        return result;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setValueR(Integer valueR) {
        this.valueR = valueR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String chekPenetration (double x, double y, double r){
        if (x > 0 && y > 0 ) return areaOne(x, y, r);
        if (x < 0 && y > 0) return "Нету пробития";
        if (x < 0 && y < 0) return areaThree(x, y, r);
        if (x > 0 && y < 0) return areaFour(x, y, r);
        return areaOn(x, y, r);
    }

    public String areaOne (double x, double y, double r){
        if (Math.pow(x/2,2) + Math.pow(y/2, 2) <= Math.pow(r/2, 2)) return "Есть пробитие";
        return "Нету пробития";
    }

    public String areaThree (double x, double y, double r) {
        if (Math.abs(x) <= Math.abs(r) && Math.abs(y) <= Math.abs(r)) return "Есть пробитие";
        return "Нету пробития";
    }

    public String areaFour (double x, double y, double r) {
        if (y >= (x - r)) return "Есть пробитие";
        return "Нету пробития";
    }

    public String areaOn (double x, double y, double r) {
        if (x == 0) {
            if (y <= r/2 && y >= (r * (-1))) return "Есть пробитие";
            return "Нету пробития";
        }
        if (Math.abs(x) <= r) return "Есть пробитие";
        return "Нету пробития";
    }


}