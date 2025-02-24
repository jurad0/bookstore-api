package com.adobe.bookstore.service;

import com.adobe.bookstore.dto.OrderDTO;
import com.adobe.bookstore.dto.OrderItemDTO;
import com.adobe.bookstore.entity.BookStock;
import com.adobe.bookstore.repository.BookStockRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final BookStockRepository bookStockRepository;

    public StockService(BookStockRepository bookStockRepository) {
        this.bookStockRepository = bookStockRepository;
    }

    @Async
    public void updateStockAsync(OrderDTO orderDTO) {
        try {
            for (OrderItemDTO item : orderDTO.getItems()) {
                BookStock book = bookStockRepository.findById(item.getBookId()).orElse(null);
                if (book != null) {
                    book.setQuantity(book.getQuantity() - item.getQuantity());
                    bookStockRepository.save(book);
                }
            }
        } catch (Exception e) {
            System.err.println("Error updating stock: " + e.getMessage());
        }
    }
}
