package com.mccreadie.springlabworklocator.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="prosthesis")
public class Prosthesis implements Comparable<Prosthesis>{



    public enum Pros_type {
        SENT, RETURNED, COMPLETED, REQUIRES_ATTENTION, VOID
    }

    private Pros_type status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "laboratory")
    private Laboratory laboratory;


    @ManyToOne
    @JoinColumn(name = "clinician")
    private Clinician clinician;

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;

    private String notes;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDue;


    private Date creationDate;

    public Prosthesis(){
        this.creationDate = Calendar.getInstance().getTime();
    }


    @Temporal(TemporalType.DATE)
    public LocalDate getDateDue() {
        System.out.println("THE DATE DUE IS " + this.dateDue);
        System.out.println("THE DATE DUE IS " + this.dateDue);
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String name) {
        this.notes = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }
    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public Clinician getClinician() {
        return clinician;
    }

    public void setClinician(Clinician clinician) {
        this.clinician = clinician;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(Prosthesis o) {
        return this.creationDate.compareTo(o.creationDate);
    }

    public Pros_type getStatus() {
        return status;
    }

    public void setStatus(Pros_type status) {
        this.status = status;
    }

}