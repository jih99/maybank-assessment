package com.maybank.assessment.entities;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(
            allocationSize = 1,
            name = "ProductIdSequenceGenerator",
            sequenceName = "s_product"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ProductIdSequenceGenerator"
    )
    @Column(name = "oid")
    private Long oid;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_price")
    private double bookPrice;

    @Column(name = "book_qty")
    private int bookQty;


    public Product() {
        this.oid = 0L;
        this.bookTitle = "";
        this.bookPrice = 0.0;
        this.bookQty = 0;
    }



}
