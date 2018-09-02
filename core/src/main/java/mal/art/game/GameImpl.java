package mal.art.game;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mal.art.annotations.GuessCount;
import mal.art.numberGenerator.NumberGenerator;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {
	
	@Getter(AccessLevel.NONE)
	private final NumberGenerator numberGenerator;
	@Setter
	private int guess;
	private final int guessCount;
	private int number;
	private int smallest;
	private int biggest;
	private int remainingGuesses;
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

	@PostConstruct
	@Override
	public void reset() {
		smallest = numberGenerator.getMinNumber();
		guess = numberGenerator.getMinNumber();
		remainingGuesses = guessCount;
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
		
		remainingGuesses--;
	}

	@Override
	public boolean isGameWon() {
		return guess == number;
	}

	@Override
	public boolean isGameLost() {
		return !isGameWon() && remainingGuesses <= 0;
	}

	private void checkValidNumberRange() {
		validNumberRagne = (guess >= smallest) && (guess <= biggest);
	}

	@Override
	public boolean isValidNumberRange() {
		return validNumberRagne;
	}
}
