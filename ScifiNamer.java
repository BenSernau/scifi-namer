import java.util.*;
import java.lang.*;

class ScifiNamer {
	
	private static Boolean useSpecificLetter = false;

	private static char reqLetter = ' ';

	private static int maxSyllables = 4;
	private static int nameNum = 10;
	private static int syllables = 0;

	private static Random rand = new Random();

	private static String consonants = "bcdfghjklmnpqrstvwxyz";
	private static String vowels = "aeiou";
	private static String requisiteLetters = "";
	private static String retStr = "";

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		passParameters(reader);
		System.out.println("\nHere is/are " + nameNum + " random name(s):\n");
		generateNames();

		for (int i = 0; i < requisiteLetters.length(); i++) {
			System.out.println("This name/Each of these " + nameNum + " names includes \'" + requisiteLetters.charAt(i) + "\' at least once:\n");
			reqLetter = requisiteLetters.charAt(i);
			generateNames();
		}
	}

	private static void passParameters(Scanner s) {
		System.out.println("Welcome to the Science Fiction Name Generator!\n\nPlease note that invalid inputs will provide error messages instead of names!\n\nHow many names would you like?\n");
		nameNum = Math.max(1, s.nextInt());
		System.out.println("\nWhat is the maximum number of syllables for each name?\n");
		maxSyllables = Math.max(1, s.nextInt() + 1);
		System.out.println("\nWould you like to generate names that include a particular letter (pass true or false)?\n");
		useSpecificLetter = s.nextBoolean();
		if (useSpecificLetter) {
			System.out.println ("\nPlease provide a string whose characters you want to include in the following names.\n");
			requisiteLetters = s.next();
		}
	}

	private static void generateNames() {
		for (int i = 0; i < nameNum; i++) {
			syllables = Math.max(1, rand.nextInt(maxSyllables));
			System.out.println(createName());
			retStr = "";
		}

		System.out.println("\n");
	}

	private static String addVowels(char _vowel, int _index) {
		_vowel = vowels.charAt(rand.nextInt(vowels.length() - 1));

		if (retStr.indexOf(reqLetter) < 0 && _index == syllables - 1 && vowels.indexOf(reqLetter) > -1) {
			retStr = retStr + reqLetter;
		}
		
		else {
			retStr = retStr + _vowel;

			if  (rand.nextBoolean()) {
				_vowel = vowels.charAt(rand.nextInt(4));
				retStr = retStr + _vowel;
			}
		}

		return retStr;
	}

	private static String optionalVowel(char _vowel) {
			_vowel = vowels.charAt(rand.nextInt(4));
			retStr = retStr + _vowel;

			if  (rand.nextBoolean()) {
				_vowel = vowels.charAt(rand.nextInt(4));
				retStr = retStr + _vowel;
			}

			return retStr;
	}

	private static String addConsonants(char _consonant, int _index) {
		_consonant = consonants.charAt(rand.nextInt(20));

		if (retStr.indexOf(reqLetter) < 0 && _index == syllables - 1 && consonants.indexOf(reqLetter) > -1) {
			retStr = retStr + reqLetter;
		}

		else {
			retStr = retStr + _consonant;

			if  (rand.nextBoolean() && (_consonant == 'c' || _consonant == 'k' || _consonant == 's' || _consonant == 'z')) {
				retStr = retStr + "h";
			}
		}

		return retStr;
	}

	private static String optionalConsonant(char _consonant) {
		_consonant = consonants.charAt(rand.nextInt(20));
		retStr = retStr + _consonant;

		if  (rand.nextBoolean() && (_consonant == 'c' || _consonant == 'k' || _consonant == 's' || _consonant == 'z')) {
			retStr = retStr + "h";
		}

		return retStr;
	}

	private static String createName() {
		char currConsonant = ' ';
		char currVowel = ' ';

		if (rand.nextBoolean()) {
			for (int i = 0; i < syllables; i++) {
				retStr = addVowels(currVowel, i);
				retStr = addConsonants(currConsonant, i);
			}

			if (rand.nextBoolean() && syllables < maxSyllables - 1) {
				retStr = optionalVowel(currVowel);
			}
		}

		else {
			for (int i = 0; i < syllables; i++) {
				retStr = addConsonants(currConsonant, i);
				retStr = addVowels(currVowel, i);
			}

			if (rand.nextBoolean() && syllables < maxSyllables - 1) {
				retStr = optionalConsonant(currConsonant);
			}
		}
		
		return retStr;
	}
}