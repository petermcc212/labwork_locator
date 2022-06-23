package com.mccreadie.springlabworklocator.model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Entity
@Indexed
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, min = 1, message = "First name length incorrect. Please try again ")
    @Pattern(regexp = "^[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçč" +
            "šžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message = "Unsupported characters in first name. Please re-enter the first name")
    private String firstName;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, min = 1, message = "Last name length incorrect. Please try again")
    @Pattern(regexp = "^[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçč" +
            "šžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message = "Unsupported characters in last name. Please re-enter the first name")
    private String lastName;
    @NotNull(message = "Invalid date of birth. Please check and try again ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @OneToMany(mappedBy = "patient")
    private List<Prosthesis> prostheses;


    @ManyToOne
    private Clinician clinician;




    public Clinician getClinician() {
        return clinician;
    }

    public void setClinician(Clinician clinician) {
        this.clinician = clinician;
    }


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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Prosthesis> getProstheses() {
        return prostheses;
    }

    public void setProstheses(List<Prosthesis> prostheses) {
        this.prostheses = prostheses;
    }


}
