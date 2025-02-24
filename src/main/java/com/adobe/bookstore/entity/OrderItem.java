package com.adobe.bookstore.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String bookId;
    private int quantity;

    public OrderItem() {}

    public OrderItem(String bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public String getBookId() { return bookId; }
    public int getQuantity() { return quantity; }
}
