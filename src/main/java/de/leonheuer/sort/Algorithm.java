package de.leonheuer.sort;

import java.util.StringJoiner;

public enum Algorithm {

    QUICK,
    RADIX,
    MERGE,
    HEAP;

    public static String getAlgorithms(String delimiter) {
        StringJoiner algorithms = new StringJoiner(delimiter);
        for (Algorithm algorithm : Algorithm.values()) {
            algorithms.add(algorithm.name().toLowerCase());
        }
        return algorithms.toString();
    }

}
