package com.adobe.bookstore;

import com.adobe.bookstore.dto.OrderDTO;
import com.adobe.bookstore.dto.OrderItemDTO;
import com.adobe.bookstore.entity.BookStock;
import com.adobe.bookstore.entity.Order;
import com.adobe.bookstore.repository.BookStockRepository;
import com.adobe.bookstore.repository.OrderRepository;
import com.adobe.bookstore.service.OrderService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookStockRepository bookStockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder_Success() throws InterruptedException {
        // Add test stock
        bookStockRepository.save(new BookStock("book1", "Java Programming", 10));

        // Create order
        OrderDTO orderDTO = new OrderDTO(List.of(new OrderItemDTO("book1", 2)));
        Order order = orderService.createOrder(orderDTO);


        // Verify order
        assertNotNull(order);
        assertEquals("SUCCESS", order.getStatus());



        // Verify stock
        BookStock updatedBook = bookStockRepository.findById("book1").orElse(null);
        assertNotNull(updatedBook);
        assertEquals(8, updatedBook.getQuantity()); // 10 - 2 = 8
    }

    @Test
    public void testCreateOrder_Fail_InsufficientStock() {
        // Adds insufficient stock
        bookStockRepository.save(new BookStock("book2", "Spring Boot Guide", 1));


        // Tries to buy more than stock
        OrderDTO orderDTO = new OrderDTO(List.of(new OrderItemDTO("book2", 5)));
        Order order = orderService.createOrder(orderDTO);

        // Verify decline order
        assertNull(order);
    }
}
