package Product_Service.feature.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serial;
import java.io.Serializable;

@Document(indexName = "products")
@Data
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -4832076529528392787L;

    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;

}
