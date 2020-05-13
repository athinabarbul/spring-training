package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderDetailList"})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private String addressCountry;
    private String addressCounty;
    private String addressCity;
    private String addressStreet;

    @JsonProperty
    @ManyToOne
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetailList;
}
