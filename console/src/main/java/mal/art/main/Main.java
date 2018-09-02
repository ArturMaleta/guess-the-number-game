 package mal.art.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;
import mal.art.configuration.GameConfiguration;

@Slf4j
public class Main {
	
//	public static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		log.info("Guess the number game");
		
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfiguration.class);

		context.close();
	}

}