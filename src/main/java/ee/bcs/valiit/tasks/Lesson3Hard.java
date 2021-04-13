package ee.bcs.valiit.tasks;

import java.util.Random;
public class Lesson3Hard {
    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
    static Random random = new Random();
    static int randomNumber = random.nextInt(100);

    public static String guessNumber(int guess) {

        while (guess != randomNumber) {
            if (guess < randomNumber) {
                return "It's greater than " + guess + ".";
            } else {
                return "It's smaller than " + guess + ".";
            }
        }
return "Correct " + randomNumber;
    }
}
