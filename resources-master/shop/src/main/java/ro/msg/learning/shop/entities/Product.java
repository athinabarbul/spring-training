package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;

    @JsonIgnore
    @ManyToOne
    private ProductCategory category;

    @JsonIgnore
    @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<Stock> stockList;

}
