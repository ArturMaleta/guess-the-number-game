package mal.art.game;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mal.art.annotations.GuessCount;
import mal.art.numberGenerator.NumberGenerator;

@Component
public class GameImpl implements Game {
	
	private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

	private final NumberGenerator numberGenerator;
	
	private final int guessCount;
	private int number;
	private int guess;
	private int smallest;
	private int biggest;
	private int remainingGuesse;
	private boolean validNumberRagne = true;

	@Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
		this.numberGenerator = numberGenerator;
		this.guessCount = guessCount;
	}

	@PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
    }
	
	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int getGuess() {
		return guess;
	}

	@Override
	public void setGuess(int guess) {
		this.guess = guess;
	}

	@Override
	public int getSmallest() {
		return smallest;
	}

	@Override
	public int getBiggest() {
		return biggest;
	}

	@Override
	public int getRemainingGuesses() {
		return remainingGuesse;
	}

	public int getGuessCount() {
		return guessCount;
	}

	@PostConstruct
	@Override
	public void reset() {
		smallest = numberGenerator.getMinNumber();
		guess = numberGenerator.getMinNumber();
		remainingGuesse = guessCount;
		biggest = numberGenerator.getMaxNumber();
		number = numberGenerator.next();
		log.debug("the number is {}", number);
	}

	@Override
	public void check() {
		checkValidNumberRange();
		
		if(validNumberRagne) {
			if(guess > number) {
				biggest = guess -1;
			}
			if (guess < number) {
				smallest = guess +1;
			}
		}
		
		remainingGuesse--;
	}

	@Override
	public boolean isValidNumberRange() {
		return validNumberRagne;
	}

	@Override
	public boolean isGameWon() {
		return guess == number;
	}

	@Override
	public boolean isGameLost() {
		return !isGameWon() && remainingGuesse <= 0;
	}

	private void checkValidNumberRange() {
		validNumberRagne = (guess >= smallest) && (guess <= biggest);
	}
}
