package com.adobe.bookstore.service;

import com.adobe.bookstore.dto.OrderDTO;
import com.adobe.bookstore.dto.OrderItemDTO;
import com.adobe.bookstore.entity.BookStock;
import com.adobe.bookstore.entity.Order;
import com.adobe.bookstore.repository.BookStockRepository;
import com.adobe.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookStockRepository bookStockRepository;

    public OrderService(OrderRepository orderRepository, BookStockRepository bookStockRepository) {
        this.orderRepository = orderRepository;
        this.bookStockRepository = bookStockRepository;
    }

    public Order createOrder(OrderDTO orderDTO) {
            // Book stock
        for (OrderItemDTO item : orderDTO.getItems()) {
            BookStock book = bookStockRepository.findById(item.getBookId()).orElse(null);
            if (book == null || book.getQuantity() < item.getQuantity()) {
                return null;
            }
        }


        for (OrderItemDTO item : orderDTO.getItems()) {
            BookStock book = bookStockRepository.findById(item.getBookId()).orElse(null);
            book.setQuantity(book.getQuantity() - item.getQuantity());
            bookStockRepository.save(book);
        }

        // Save order
        OrderItemDTO firstItem = orderDTO.getItems().get(0);
        Order order = new Order(firstItem.getBookId(), firstItem.getQuantity(), "SUCCESS");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
