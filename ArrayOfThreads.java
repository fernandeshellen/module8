import java.util.Random;

public class ArrayOfThreads {
    public static void main(String[] args) {

        Random randomNumber = new Random();
        int[] array = new int[200000000];

        for (int i = 0; i < array.length; i++) {
            array[i] = randomNumber.nextInt(10) + 1;
        }

        long start = System.currentTimeMillis();

        System.out.println("\nSum single thread: " + parallelSumArray.sum(array));
        System.out.println("\nTime: " + (System.currentTimeMillis() - start));
        
        start = System.currentTimeMillis();

        System.out.println("\nSum multiple threads: " + parallelSumArray.parallelSum(array));
        System.out.println("\nTime: " + (System.currentTimeMillis() - start + "\n"));
    }
}
