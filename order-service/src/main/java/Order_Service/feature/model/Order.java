package Order_Service.feature.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "ORDERSERVICE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double totalAmount;
    private Date orderDate;

    @ElementCollection
    private List<Long> productIds;
}
