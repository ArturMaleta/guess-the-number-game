package mal.art.messageGenerator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mal.art.game.Game;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

	private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);
	
	private final Game game;
	
	@Autowired
	public MessageGeneratorImpl(Game game) {
		this.game = game;
	}

	@PostConstruct
	public void init() {
		log.info("game = {}", game);
	}
	
	@Override
	public String getMainMessage() {
		return "Number is between " + game.getSmallest() + " and " + game.getBiggest() + ". Can you guess it?";
	}

	@Override
	public String getResultMessage() {

		String checkRemainingGuesses;
		
		if(game.isGameWon()) {
			return "You guess it! The number was " + game.getNumber();
		} else if (game.isGameLost()) {
			return "You lost. The number was " + game.getNumber();
		} else if (!game.isValidNumberRange()) {
			if(game.getRemainingGuesses() == 1) {
				checkRemainingGuesses = game.getRemainingGuesses() + " guess left.";
			} else
				checkRemainingGuesses = game.getRemainingGuesses() + " guesses left.";
			
			return "Invalid number range. You have " + checkRemainingGuesses;
			
		} else if(game.getRemainingGuesses() == game.getGuessCount()) {
			return "What is your first guess?";
		} else {
			String direction = "lower";
			
			if(game.getGuess() < game.getNumber()) {
				direction = "Higher";
			}
			
			if(game.getRemainingGuesses() == 1) {
				checkRemainingGuesses = game.getRemainingGuesses() + " guess left.";
			} else
				checkRemainingGuesses = game.getRemainingGuesses() + " guesses left.";
			
			return direction + "! You have " + checkRemainingGuesses;
		}
	}

}
