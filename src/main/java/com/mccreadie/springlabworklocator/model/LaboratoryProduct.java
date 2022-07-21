package com.mccreadie.springlabworklocator.model;

import javax.persistence.*;

@Entity
public class LaboratoryProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    private int turnaroundPeriod;

    @ManyToOne
    private Laboratory laboratory;



    public LaboratoryProduct() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurnaroundPeriod() {
        return turnaroundPeriod;
    }

    public void setTurnaroundPeriod(int turnaroundPeriod) {
        this.turnaroundPeriod = turnaroundPeriod;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }
}
