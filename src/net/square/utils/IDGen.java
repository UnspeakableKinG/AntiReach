package net.square.utils;

import java.util.Random;

/**
 * Copyright © SquareCode 2018
 * created on: 03.12.2018 / 18:55
 * Project: AntiReach
 */
public class IDGen {

    public static IDGen instance;

    public void setInstance() {
        IDGen.instance = this;
    }

    public String generateRandom(final int length, final boolean lowerUpperCase, final boolean includeNumbers) {
        final Random RANDOM = new Random();
        final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        final char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            final boolean number = includeNumbers && RANDOM.nextBoolean();
            final boolean lower = lowerUpperCase && RANDOM.nextBoolean();
            final char c = number ? numbers[RANDOM.nextInt(numbers.length)] : alphabet[RANDOM.nextInt(alphabet.length)];
            builder.append(lower ? String.valueOf(c).toLowerCase() : String.valueOf(c));
        }
        return builder.toString();
    }
}
