public class MyRandom {
    public static int getRandomIntNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
