package mal.art.numberGenerator;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import mal.art.annotations.MaxNumber;
import mal.art.annotations.MinNumber;

@Getter
@Component
public class NumberGeneratorImpl implements NumberGenerator {

	@Getter(AccessLevel.NONE)
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
}
