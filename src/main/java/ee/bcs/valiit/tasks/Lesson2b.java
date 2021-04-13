package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2b {
    public static void main(String[] args) {
        exercise1(5);
        System.out.println(sampleArray());
        System.out.println(Arrays.toString(generateArray(5)));

    }


    // TODO trüki välja n arvu
    // näiteks
    // sisend: 5
    // trüki välja: 1 2 3 4 5
    public static void exercise1(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i + " ");
        }

    }


    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public static int[] sampleArray() {
        int[] x = {1, 2, 3, 4, 5};

        return x;
    }

    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public static int[] generateArray(int n) {
        int[] resultArray = new int[n];
        for (int i = 0; i < n; i++) {
            resultArray[i] = i +1;
        }
        return resultArray;
    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0
    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {
        return new int[0];
    }

    // TODO
    // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
    public static int[] yl3(int n) {
        int[] resultArray = new int[n];
        for (int i = 0; i < n; i++) {
            resultArray[i] = 3;
        }
        return resultArray;
    }
}
