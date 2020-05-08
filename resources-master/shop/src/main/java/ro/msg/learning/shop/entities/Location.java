package ro.msg.learning.shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String addressCountry;
    private String addressCounty;
    private String addressCity;
    private String addressStreet;

    @OneToMany(mappedBy = "location")
    private List<Stock> stockList;

    @OneToMany(mappedBy = "shippedFrom")
    private List<Orders> ordersList;

    @OneToMany(mappedBy = "location")
    private List<Revenue> revenueList;

}
