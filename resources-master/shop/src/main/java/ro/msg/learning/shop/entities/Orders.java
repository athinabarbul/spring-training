package ro.msg.learning.shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createdAt;
    private String addressCountry;
    private String addressCounty;
    private String addressCity;
    private String addressStreet;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Location shippedFrom;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetailList;
}
