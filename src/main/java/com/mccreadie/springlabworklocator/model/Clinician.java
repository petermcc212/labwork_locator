package com.mccreadie.springlabworklocator.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "clinician")
public class Clinician extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String firstName;
    private String lastName;
    private String clinicianEmail;
    private String clinicianPhoneNumber;




    @OneToMany(mappedBy = "clinician")
    private List<Prosthesis> prostheses;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @OneToMany(mappedBy = "clinician")
    private List<Patient> patients;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getClinicianEmail() {        return clinicianEmail;
    }

    public void setClinicianEmail(String clinicianEmail) {
        this.clinicianEmail = clinicianEmail;
    }

    public String getClinicianPhoneNumber() {
        return clinicianPhoneNumber;
    }

    public void setClinicianPhoneNumber(String clinicianPhoneNumber) {
        this.clinicianPhoneNumber = clinicianPhoneNumber;
    }

    public List<Prosthesis> getProstheses() {
        return prostheses;
    }

    public void setProstheses(List<Prosthesis> prostheses) {
        this.prostheses = prostheses;
    }


}
