package com.angelis.tera.common.utils;

public class Rnd {
    
    private static final MTRandom random = new MTRandom();

    /**
     * Gets a random number from 0 to 1
     * @return
     */
    public static float get() {
        return random.nextFloat();
    }

    /**
     * Gets a random number from 0(inclusive) to n(exclusive)
     * 
     * @param n The superior limit (exclusive)
     * @return 
     */
    public static int get(int n) {
        return (int) Math.floor(random.nextDouble() * n);
    }

    /**
     * Gets a random number betwen {min} and {max} inclusive !
     * 
     * @param min
     * @param max
     * @return
     */
    public static int get(int min, int max) {
        return min + (int) Math.floor(random.nextDouble() * (max - min + 1));
    }
    
    public static int get(int min, int max, boolean maxInclusive) {
        if (maxInclusive) {
            return get(min, max);
        } else {
            return (int) Math.floor(random.nextDouble() * max) + min;
        }
    }

    /**
     * Gets random number from min to max inclusive
     * @param min
     * @param max
     * @return
     */
    public static long get(long min, long max) {
        return min + (long) Math.floor(random.nextDouble() * (max - min + 1));
    }

    public static int nextInt() {
        return random.nextInt();
    }

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static double nextGaussian() {
        return random.nextGaussian();
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    public static boolean chance(int chance) {
        return chance < 1 ? false : chance > 99 ? true : random.nextInt(99) + 1 <= chance;
    }

    public static boolean chance(double chance) {
        return random.nextDouble() <= chance / 100.;
    }
}