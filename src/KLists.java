import java.util.Arrays;

public class KLists {

    static double [][] input = new double[][] {{1.1, 4.4, 5.5}, {1.1, 3.3, 4.4}, {2.2, 6.6}};
    static double [][] input2 = new double[][] {{}};
    static double [] target;
    static int size;
    static  double [] singleArray;


    /**
     * function that takes in unsorted array of sorted subarrays and is the initial step of the program
     * @param outerArray
     * @return singleArray - 2d array copied into 1d array
     */
    public static double[] mergeKLists(double[][] outerArray) {
        if (outerArray.length > 1) {
            size = 0;
            for (double [] inner : input) {
                size += inner.length;
            }
            getSingleArray(outerArray);

            target = new double[size];
            mergesort(singleArray);
        }else{
            return new double[0];
        }
        return singleArray;
    }

    /**
     * function that recursively calls itself to get the left and right and merge
     * @param arr
     */
    public static void mergesort(double [] arr) {
        if (arr.length > 1) {
           double [] left = getLeft(arr);
           double [] right = getRight(arr);
            mergesort(left);
            mergesort(right);
            merge(arr, left, right);
        }
    }

    /**
     * function that takes original 2d array and copies each value into 1d array to simplify
     * @param outerArray
     */
    public static void getSingleArray(double[][] outerArray){
       singleArray = new double [size];
        int index = 0;
        for(double [] array : outerArray){
            for(int i =0; i < array.length;i++){
                singleArray[index] = array[i];
                index++;
            }
        }
    }

    /**
     * function that gets left half of unsorted array
     * @param singleArray
     * @return left half
     */
    public static double [] getLeft (double [] singleArray){
        int size = singleArray.length/2;
        double left[];
        left = new double[size];
        for(int i = 0; i < left.length; i++){
            left[i] = singleArray[i];
        }
        return left;
    }

    /**
     * function that gets right half of unsorted array
     * @param singleArray
     * @return right half
     */
    public static double [] getRight (double [] singleArray){
        double [] right;
        int size = singleArray.length/2;
        right = new double[size];
        for(int i = 0; i < right.length; i++){
            right[i] = singleArray[size++];
        }
        return right;
    }

    /**
     * function that will update the target array by checking values in left & right
     * @param target
     * @param left
     * @param right
     */
    public static void merge(double [] target,double [] left, double [] right){
        int i = 0, j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                target[k] = left[i];
                i++;
            }
            else {
                target[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            target[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            target[k] = right[j];
            j++;
            k++;
        }
    }


    public static void main(String[] args) {
        KLists klist = new KLists();
        System.out.println(Arrays.toString(mergeKLists(input)));
        System.out.println(Arrays.toString(mergeKLists(input2)));
    }

}
