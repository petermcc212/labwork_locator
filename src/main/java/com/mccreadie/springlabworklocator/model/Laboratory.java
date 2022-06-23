package com.mccreadie.springlabworklocator.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "laboratory")
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Please enter laboratory name")
    @Size(max = 50, min = 1, message = "Laboratory name length invalid")
    private String name;
    @NotBlank(message = "Please enter laboratory address")
    @Size(max = 100, min = 1, message = "Laboratory name length invalid")
    private String address;
    @NotBlank(message = "Please enter laboratory phone number")
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Invalid phone number")
    private String phoneNumber;

    @OneToMany
    @JoinColumn(name = "laboratory")
    private List<Laboratory> laboratoryList;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
