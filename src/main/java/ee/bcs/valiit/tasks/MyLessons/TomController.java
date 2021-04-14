package ee.bcs.valiit.tasks.MyLessons;


import ee.bcs.valiit.tasks.Lesson3Hard;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class TomController {
    public String Lesson3Hard;
    Random random = new Random();
    int randomNumber = random.nextInt(100);
    @GetMapping("Random")
    public String guessNumber(@RequestParam("guess") int guess ) {
        if (guess < randomNumber) {
            return "It's greater than " + guess + ".";
        } else if (guess>randomNumber){
            return "It's smaller than " + guess + ".";
        }
        return "Correct " + randomNumber;
    }

    @GetMapping("Lesson3Hard")
    public Lesson3Hard getRandom() {
        return getRandom();
    }


}
