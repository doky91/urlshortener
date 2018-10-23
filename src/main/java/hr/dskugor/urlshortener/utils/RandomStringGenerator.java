package hr.dskugor.urlshortener.utils;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class RandomStringGenerator {

	public RandomStringGenerator() {
	}

	public String generateString() {

		final String allPossibleValues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++)
			sb.append(allPossibleValues.charAt(rnd.nextInt(allPossibleValues.length())));

		return sb.toString();

	}
}
