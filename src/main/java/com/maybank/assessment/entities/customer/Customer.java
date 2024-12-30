package com.maybank.assessment.entities.customer;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(
            allocationSize = 1,
            name = "CustomerIdSequenceGenerator",
            sequenceName = "s_customer"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CustomerIdSequenceGenerator"
    )
    @Column(name = "oid")
    private Long oid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "office_email")
    private String officeEmail;


    public Customer() {
        this.oid = 0L;
        this.lastName = "";
        this.firstName = "";
        this.personalEmail = "";
        this.officeEmail = "";
    }


    public Customer(String firstName, String lastName, String personalEmail, String officeEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalEmail = personalEmail;
        this.officeEmail = officeEmail;
    }
}
