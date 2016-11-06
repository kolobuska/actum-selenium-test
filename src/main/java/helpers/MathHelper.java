package helpers;

public class MathHelper {

    /**
     * Get random integer period
     *
     * @param min min of period
     * @param max max of period
     * @return random integer
     */
    public static int getRandomInt(int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("Max parameter should be more than min");
        double random = Math.random() * max + min;
        return (int) random;
    }
}
