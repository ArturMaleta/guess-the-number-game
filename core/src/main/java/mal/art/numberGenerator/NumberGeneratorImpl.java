package mal.art.numberGenerator;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mal.art.annotations.MaxNumber;
import mal.art.annotations.MinNumber;

@Component
public class NumberGeneratorImpl implements NumberGenerator {

	private final Random random = new Random();
	
	private final int maxNumber;
	
	private final int minNumber;

	@Autowired
	public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
		this.maxNumber = maxNumber;
		this.minNumber = minNumber;
	}

	@Override
	public int next() {
		// range of numbers in game -> 5 - 100
		return random.nextInt(maxNumber - minNumber) + minNumber;
	}

	@Override
	public int getMaxNumber() {
		return maxNumber;
	}

	@Override
	public int getMinNumber() {
		return minNumber;
	}

}
