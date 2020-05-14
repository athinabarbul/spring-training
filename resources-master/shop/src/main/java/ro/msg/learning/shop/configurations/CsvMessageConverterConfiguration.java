package ro.msg.learning.shop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import ro.msg.learning.shop.helpers.CsvMessageConverter;

@Configuration
public class CsvMessageConverterConfiguration {

	@Bean
	public AbstractHttpMessageConverter csvConverter() {
		return new CsvMessageConverter();
	}
}
