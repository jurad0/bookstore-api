package com.adobe.bookstore.dto;

import java.util.List;

public class OrderDTO {
    private List<OrderItemDTO> items;

    public OrderDTO() {}

    public OrderDTO(List<OrderItemDTO> items) {
        this.items = items;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
