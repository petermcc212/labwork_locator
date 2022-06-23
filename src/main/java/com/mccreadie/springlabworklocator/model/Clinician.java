package com.mccreadie.springlabworklocator.model;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "clinician")
public class Clinician extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, min = 1, message = "First name length incorrect.")
    @Pattern(regexp = "^[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçč" +
            "šžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$",
            message = "Unsupported characters in first name.")
    private String firstName;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, min = 1, message = "Last name length incorrect.")
    @Pattern(regexp = "^[a-zA-Zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçč" +
            "šžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$",
            message = "Unsupported characters in last name.")
    private String lastName;
    @NotBlank
    @Email(message = "Invalid email address")
    private String clinicianEmail;
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Invalid phone number")
    @Size(max = 11)
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
