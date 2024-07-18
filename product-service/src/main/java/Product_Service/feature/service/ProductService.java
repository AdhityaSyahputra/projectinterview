package Product_Service.feature.service;

import Product_Service.feature.model.Product;
import Product_Service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String PRODUCT_CACHE = "PRODUCT_CACHE";
    private static final String SEARCH_CACHE = "SEARCH_CACHE";

    public Product saveProduct(Product product) {
        redisTemplate.opsForHash().put(PRODUCT_CACHE, product.getId(), product);
        return productRepository.save(product);
    }

    public Iterable<Product> findAllProducts() {
        Map<Object, Object> productMap = redisTemplate.opsForHash().entries(PRODUCT_CACHE);
        if (productMap.isEmpty()) {
            Iterable<Product> products = productRepository.findAll();
            products.forEach(product -> redisTemplate.opsForHash().put(PRODUCT_CACHE, product.getId(), product));
            return products;
        } else {
            return productMap.values().stream().map(o -> (Product) o).collect(Collectors.toList());
        }
    }

    public List<Product> searchProducts(String keyword) {
        // Check cache first
        Map<Object, Object> cachedResults = redisTemplate.opsForHash().entries(SEARCH_CACHE);
        if (cachedResults.containsKey(keyword)) {
            return ((List<?>) cachedResults.get(keyword)).stream()
                    .map(obj -> (Product) obj)
                    .collect(Collectors.toList());
        }

        // If not in cache, search in repository
        List<Product> results = StreamSupport.stream(productRepository.findByNameContaining(keyword).spliterator(), false)
                .collect(Collectors.toList());

        // Cache the search results
        redisTemplate.opsForHash().put(SEARCH_CACHE, keyword, results);

        return results;
    }
}
