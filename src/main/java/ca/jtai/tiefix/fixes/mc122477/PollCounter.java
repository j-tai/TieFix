package ca.jtai.tiefix.fixes.mc122477;

public class PollCounter {
    private static long count = 0;

    public static void increment() {
        count++;
    }

    public static long get() {
        return count;
    }
}
