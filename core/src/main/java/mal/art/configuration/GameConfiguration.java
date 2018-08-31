package mal.art.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import mal.art.annotations.GuessCount;
import mal.art.annotations.MaxNumber;
import mal.art.annotations.MinNumber;

@Configuration
@ComponentScan(basePackages = "mal.art")
@PropertySource("classpath:config/game.properties")
public class GameConfiguration {

	@Value("${game.maxNumber}")
	private int maxNumber;

	@Value("${game.guessCount}")
	private int guessCount;
	
	@Value("${game.minNumber}")
	private int minNumber;
	
	@Bean
	@MaxNumber
	public int getMaxNumber() {
		return maxNumber;
	}
	
	@Bean
	@GuessCount
	public int  guessCount() {
		return guessCount;
	}
	
	@Bean
	@MinNumber
	public int  minNumber() {
		return minNumber;
	}
	
}
