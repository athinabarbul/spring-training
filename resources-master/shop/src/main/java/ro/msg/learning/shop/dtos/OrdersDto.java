package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDto {
	private LocalDateTime createdAt;
	private String addressCountry;
	private String addressCounty;
	private String addressCity;
	private String addressStreet;
	private List<OrderDetailDto> orderDetailDtos;
}
