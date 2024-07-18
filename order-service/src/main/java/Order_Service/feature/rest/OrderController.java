package Order_Service.feature.rest;

import Order_Service.feature.model.Order;
import Order_Service.feature.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.findOrdersByUserId(userId);
    }

    @GetMapping("/details/user/{userId}")
    public List<Map<String, Object>> getOrderDetailsByUserId(@PathVariable Long userId) {
        return orderService.getOrderDetailsByUserId(userId);
    }

    @GetMapping("/statistics")
    public Map<String, Object> getOrderStatistics(@RequestParam Date startDate, @RequestParam Date endDate) {
        return orderService.getOrderStatistics(startDate, endDate);
    }
}
