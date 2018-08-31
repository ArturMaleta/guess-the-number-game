package mal.art.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mal.art.configuration.GameConfiguration;

public class Main {
	
	public static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		log.info("Guess the number game");
		
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfiguration.class);

		context.close();
	}

}
