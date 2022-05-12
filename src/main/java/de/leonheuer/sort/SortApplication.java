package de.leonheuer.sort;

import org.jetbrains.annotations.NotNull;

public class SortApplication {

    public static void main(String @NotNull [] args) {
        if (args.length < 3) {
            System.out.println("Missing arguments. <" + Algorithm.getAlgorithms("|") + "> <array length> <max integer> [--silent]");
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

        boolean silent = args.length >= 4 && args[3].equalsIgnoreCase("--silent");

        Sortable sortable = new Sortable(generateList(length, max));
        if (!silent) System.out.println("Generated list: " + sortable + "\n\n");
        long begin = System.currentTimeMillis();
        sortable.sort(algorithm);
        long timeTook = System.currentTimeMillis() - begin;
        if (!silent) System.out.println("Sorted list: " + sortable + "\n\n");
        System.out.println("Took " + timeTook + "ms to finish. Used algorithm: " + args[0]);
    }

    private static int @NotNull [] generateList(int length, int max) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int) Math.floor(Math.random() * max + 1);
        }
        return result;
    }

}
