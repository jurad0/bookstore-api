package com.adobe.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String status;

    public Order() {}

    public Order(String bookId, int quantity, String status) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getBookId() { return bookId; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
}


