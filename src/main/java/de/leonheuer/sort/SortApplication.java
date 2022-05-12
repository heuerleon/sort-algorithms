package de.leonheuer.sort;

import org.jetbrains.annotations.NotNull;

public class SortApplication {

    public static void main(String @NotNull [] args) {
        if (args.length != 3) {
            System.out.println("Missing arguments. <" + Algorithm.getAlgorithms("|") + "> <array length> <max integer>");
            return;
        }

        Algorithm algorithm;
        try {
            algorithm = Algorithm.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Possible algorithms: " + Algorithm.getAlgorithms(", "));
            return;
        }

        int length;
        int max;
        try {
            length = Integer.parseInt(args[1]);
            max = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid integers.");
            return;
        }

        Sortable sortable = new Sortable(generateList(length, max));
        System.out.println("Generated list: " + sortable + "\n\n");
        long begin = System.currentTimeMillis();
        sortable.sort(algorithm);
        long timeTook = System.currentTimeMillis() - begin;
        System.out.println("Sorted list: " + sortable + "\n\n");
        System.out.println("Took " + timeTook + "ms to finish. Used Algorithm: " + args[0]);
    }

    private static int @NotNull [] generateList(int length, int max) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int) Math.floor(Math.random() * max + 1);
        }
        return result;
    }

}
