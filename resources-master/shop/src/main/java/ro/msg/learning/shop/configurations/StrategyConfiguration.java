package ro.msg.learning.shop.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.strategies.SingleStrategy;
import ro.msg.learning.shop.strategies.Strategy;

@Configuration
public class StrategyConfiguration {
	@Value("${strategy-used}")
	private String strategyUsed;

	@Bean
	public Strategy chooseStrategy() {
		if (strategyUsed.equals("single-location")) {
			return new SingleStrategy();
		} else {
			return new MostAbundantStrategy();
		}
	}

}
