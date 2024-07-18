package Order_Service.Repository;

import Order_Service.feature.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o.id as orderId, u.username as username, p.name as productName, o.total_amount as totalAmount, o.order_date as orderDate " +
            "FROM orders o " +
            "JOIN users u ON o.user_id = u.id " +
            "JOIN order_products op ON o.id = op.order_id " +
            "JOIN products p ON op.product_id = p.id " +
            "WHERE o.user_id = :userId", nativeQuery = true)
    List<Map<String, Object>> findOrderDetailsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT COUNT(o.id) as totalOrders, SUM(o.total_amount) as totalRevenue, AVG(o.total_amount) as averageOrderValue " +
            "FROM orders o " +
            "WHERE o.order_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    Map<String, Object> findOrderStatistics(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
