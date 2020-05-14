package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
@ToString(exclude = {"stockList","orderDetailList","revenueList"})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String addressCountry;
    private String addressCounty;
    private String addressCity;
    private String addressStreet;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<Stock> stockList;

    @JsonIgnore
    @OneToMany(mappedBy = "shippedFrom")
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<Revenue> revenueList;

}
