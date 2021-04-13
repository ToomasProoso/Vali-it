package ee.bcs.valiit.tests;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(divide(3));
        System.out.println(divide(7));
        System.out.println(divide(21));
        System.out.println(divide(4));
        System.out.println(Arrays.toString(addToArray(new int[]{4,5,8,7},3)));

        // System.out.println(
    }

    //    ÜL 1
//    Tee funktsioon, mis tagastab boolean väärtuse ja võtab sisse ühe parameetri
//    funktsioon peab tagastama
//		true: kui sisend parameeter jagub 3 või 7 (aga ei jagu mõlema numbriga)
//            false: kui sisend parameeter ei jagu 3 ega 7 või jagub mõlema numbriga
    public static boolean divide (int b) {
        if (b % 3 == 0 && b % 7 != 0) {
            return true;
        } else if (b % 7 == 0 && b % 3 != 0) {
            return true;
        } else {
            return false;
        }
    }
    // ÜL2
    // lisa x igale array elemendile
    // Näiteks
    // sisend [1,2,3], 5
    // vastus [6,7,8]

    public static int[] addToArray(int[] array, int x) {

        int element = array.length;
        for (int i = 0; i <= element; i++) {
             array[i] = array[i] + x;
        }
              return array;
    }



}
