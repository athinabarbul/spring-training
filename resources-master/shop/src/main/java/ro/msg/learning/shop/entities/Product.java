package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"stockList"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;

    @JsonProperty
    @ManyToOne
    private ProductCategory category;

    @JsonProperty
    @ManyToOne
    private Supplier supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Stock> stockList;

}
