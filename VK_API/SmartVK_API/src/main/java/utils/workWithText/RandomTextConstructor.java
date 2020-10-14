package utils.workWithText;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class RandomTextConstructor {
    private static final Logger logger = LoggerFactory.getLogger(RandomTextConstructor.class);

    private static final String SYMBOLS_EN_LOW = "abcdefghijklmnopqrstuvwxyz";
    private static final String SYMBOLS_EN_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SYMBOLS_RU_LOW = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String SYMBOLS_RU_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String SYMBOLS_DIGITS = "1234567890";
    private static final int LENGTH_SYMBOLS = 3;

    public static String getRandomText() {
        logger.info("Trying to get random text");
        return getSymbolsRandom(SYMBOLS_EN_LOW) + getSymbolsRandom(SYMBOLS_EN_UPPER) + getSymbolsRandom(SYMBOLS_RU_LOW) +
                getSymbolsRandom(SYMBOLS_RU_UPPER) + getSymbolsRandom(SYMBOLS_DIGITS);
    }

    private static String getSymbolsRandom(String symbols) {
        logger.info("Trying to get random symbols");
        StringBuffer randomSymbols = new StringBuffer();
        for (int i = 0; i < LENGTH_SYMBOLS; i++) {
            randomSymbols.append(symbols.charAt(new Random().nextInt(symbols.length())));
        }
        return randomSymbols.toString();
    }
}
