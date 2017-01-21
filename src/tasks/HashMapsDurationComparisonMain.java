package tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class HashMapsDurationComparisonMain {

    public static void main(String[] args) throws InterruptedException {

        DurationThread hmCode = new DurationThread("hm code",
                                                   HashMapsDurationComparisonMain::hasMapTest);
        DurationThread whmCode = new DurationThread("whm code",
                                                   HashMapsDurationComparisonMain::weakHasMapTest);

        hmCode.start();
        whmCode.start();

        hmCode.join();
        whmCode.join();

        Thread summary = new Thread(() -> System.out.format(
                             "WeakHashMap based code duration is " +
                             "greater than HashMap's is %s%n%n",
                              hmCode.getDuration() < whmCode.getDuration()));

        summary.start();


    }


    private static void hasMapTest() {
        Map<Integer, String> hm = new HashMap<>();
        for(int i = 0; i < 1_000_000; ++i)
            hm.put(i, "hasMapElement_" + i);
        System.out.println("HashMap filled!");
        hm = null;
        System.gc();
        System.out.println("\tHashMap nilled!\n");
    }

    private static void weakHasMapTest() {
        Map<Integer, String> whm = new WeakHashMap<>();
        for(int i = 0; i < 1_000_000; ++i)
            whm.put(i, "weakHasMapElement_" + i);
        System.out.println("WeakHashMap filled!");
        whm = null;
        System.gc();
        System.out.println("\tWeakHashMap nilled!\n");
    }

    private static class DurationThread extends Thread {

        private String codeName;
        private Runnable code;
        private long duration;

        public long getDuration() {
            return duration;
        }

        public DurationThread(String codeName, Runnable code) {
            this.codeName = codeName;
            this.code = code;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();

            code.run();

            long end = System.currentTimeMillis();

            duration = end - start;
            System.out.format("%s duration is %d milliseconds%n%n",
                              codeName, duration);
        }
    }
}
