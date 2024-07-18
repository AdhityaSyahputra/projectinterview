package Order_Service.feature.service;

import Order_Service.Repository.OrderRepository;
import Order_Service.feature.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        kafkaTemplate.send("orders", "Order created: " + savedOrder.getId());
        return savedOrder;
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getOrderDetailsByUserId(Long userId) {
        return orderRepository.findOrderDetailsByUserId(userId);
    }

    public Map<String, Object> getOrderStatistics(Date startDate, Date endDate) {
        return orderRepository.findOrderStatistics(startDate, endDate);
    }
}
