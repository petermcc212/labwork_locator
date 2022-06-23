package com.mccreadie.springlabworklocator.model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Size(max = 50, min = 1, message = "First name invalid length.")
    @Pattern(regexp = "^[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçč" +
            "šžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$",
            message = "Unsupported characters in first name.")
    private String firstName;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, min = 1, message = "Last name length invalid.")
    @Pattern(regexp = "^[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçč" +
            "šžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$",
            message = "Unsupported characters in last name.")
    private String lastName;
    @NotNull(message = "Invalid date of birth.")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
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
