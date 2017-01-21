package tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class HashMapsMemoryComparisonMain {

    public static void main(String[] args) throws InterruptedException {

        MemoryUsageThread hmCode = new MemoryUsageThread("hm code",
                                                   HashMapsMemoryComparisonMain::hasMapTest);
        MemoryUsageThread whmCode = new MemoryUsageThread("whm code",
                                                   HashMapsMemoryComparisonMain::weakHasMapTest);

        hmCode.start();
        whmCode.start();

        hmCode.join();

        Thread.sleep(1000);

        whmCode.join();

        Thread summary = new Thread(() -> System.out.format(
                             "WeakHashMap based code memory usage is " +
                             "greater than HashMap's is %s%n" +
                             "Difference of memory used is %d bytes%n",
                              hmCode.getUsage() < whmCode.getUsage(),
                              whmCode.getUsage() - hmCode.getUsage()));

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

    private static class MemoryUsageThread extends Thread {

        private String codeName;
        private Runnable code;
        private long usage;

        public long getUsage() {
            return usage;
        }

        public MemoryUsageThread(String codeName, Runnable code) {
            this.codeName = codeName;
            this.code = code;
        }

        @Override
        public void run() {
            long start = Runtime.getRuntime().freeMemory();

            code.run();

            long end = Runtime.getRuntime().freeMemory();

            usage = end - start;
            System.out.format("%s used memory is %d bytes%n%n",
                              codeName, usage);
        }
    }
}
