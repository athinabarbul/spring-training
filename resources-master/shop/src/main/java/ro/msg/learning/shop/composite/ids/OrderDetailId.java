package ro.msg.learning.shop.composite.ids;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailId implements Serializable {
	private Integer ordersId;
	private Integer productId;

}
