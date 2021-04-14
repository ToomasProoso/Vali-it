package ee.bcs.valiit;

import ee.bcs.valiit.solution.SolutionLesson1;
import ee.bcs.valiit.tasks.Lesson2;
import ee.bcs.valiit.tasks.Lesson2b;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    //http://localhost:8080/sample/hello-world/John/?action=Hey&action2=tere
    @GetMapping("sample/hello-world/{nameInUrl}") // selle järgi tuleb minna -> http://localhost:8080/sample/hello-world
    public String helloWorld(@PathVariable("nameInUrl") String name, @RequestParam("action") String a,
                             @RequestParam("action2") String b) {
        return a + " " + name + " " + b;

    }

    //http://localhost:8080/min/1/5
    @GetMapping("min/{a}/{b}")
    public int min(@PathVariable("a") int a, @PathVariable("b") int b) {
        return SolutionLesson1.min(a, b);
    }

    //http://localhost:8080/min?a=1&b=5
    @GetMapping("min")
    public int min2(@RequestParam("a") int a, @RequestParam("b") int b) {
        return SolutionLesson1.min(a, b);

    }

    //http://localhost:8080/fibonacci/20
    @GetMapping("fibonacci/{n}")
    public int fibonacci(@PathVariable("n") int n) {
        return Lesson2.fibonacci(n);
    }

    // http://localhost:8080/sampleArray/
    @GetMapping("sampleArray")
    public int[] sampleArray() {
        return Lesson2b.sampleArray();
    }

    //http://localhost:8080/sum/10,5,6,8,9
    @GetMapping("sum/{array}") //annad palju numbreid array ("array") int[] array)
    public int sum(@PathVariable("array") int[] array) {
        return Lesson2.sum(array);
    }


    // http://localhost:8080/generateArray/5
    @GetMapping("generateArray/{n}") //public int [] generateArray annad ühe numbri
    public int [] generateArray(@PathVariable("n") int n) {
        return Lesson2b.generateArray(n);
    }

//    @GetMapping("multiplyTable/{x}/{y}")
//    public int multiplyTable(@PathVariable("i") int i, @PathVariable("j") int j) {
//        return Lesson2.multiplyTable(i, j);
}

