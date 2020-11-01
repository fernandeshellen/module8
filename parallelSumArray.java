public class parallelSumArray extends Thread {

    private int[] array;
    private int minimum;
    private int maximum;
    private int middle;

    public parallelSumArray(int[] array, int minimum, int maximum) {
        this.array = array;
        this.minimum = minimum;
        this.maximum = Math.min(maximum, array.length);
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    public int getMiddle() {
        return middle;
    }

    public void run() {
        middle = sum(array, minimum, maximum);
    }
    public static int sum(int[] array, int minimum, int maximum) {
        int result = 0;

        for (int i = minimum; i < maximum; i++) {
            result += array[i];
        }

        return result;
    }

    public static int sum(int[] array) {
        return sum(array, 0, array.length);
    }

    public static int parallelSum(int[] array) {
        return parallelSum(array, Runtime.getRuntime().availableProcessors());
    }

    public static int parallelSum(int[] array, int threads) {
        int size = (int) Math.ceil(array.length * 1.0 / threads);

        parallelSumArray[] thread = new parallelSumArray[threads];
        int result = 0;

        for (int i = 0; i < threads; i++) {
            thread[i] = new parallelSumArray(array, i * size, (i + 1) * size);
            thread[i].start();
        }

        try {
            for (parallelSumArray sumTotal : thread) {
                sumTotal.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        for (parallelSumArray sumTotal : thread) {
            result += sumTotal.getMiddle();
        }
        return result;
    }
}
