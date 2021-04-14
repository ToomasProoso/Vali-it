package ee.bcs.valiit;

import ee.bcs.valiit.solution.SolutionLesson1;
import ee.bcs.valiit.tasks.Lesson2;
import ee.bcs.valiit.tasks.Lesson2b;
import ee.bcs.valiit.tasks.MyLessons.Cars;
import ee.bcs.valiit.tasks.MyLessons.Cars1;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    public List<Cars> carsList = new ArrayList<>();

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
    public int[] generateArray(@PathVariable("n") int n) {
        return Lesson2b.generateArray(n);
    }


//localhost:8080/cars
    @GetMapping("Cars")
    public List<Cars> cars(){
        return carsList;
    }
    @GetMapping("Cars/{id}")
    public Cars getCarId(@PathVariable("id") int identify) {
        return (carsList.get(identify));

    }
    @PostMapping ("Cars")
    public void cars(@RequestBody Cars cars){
       carsList.add(cars);
    }
    @PutMapping("Cars/{id}")
    public void replaceCars(@PathVariable ("id") int identify,@RequestBody Cars cars){
        carsList.set(identify,cars);
    }
    @DeleteMapping("Cars/{id}")
    public void deleteCars (@PathVariable("id") int identify){
        carsList.remove(identify);
    }

//@GetMapping("Cars1")
//    public List <Cars1>(){

}

//
//    public String mark;
//    private String number;
//    private int year;


//    @GetMapping("multiplyTable/{x}/{y}")
//    public int multiplyTable(@PathVariable("i") int i, @PathVariable("j") int j) {
//        return Lesson2.multiplyTable(i, j);


//    //localhost:8080/cars
//    @GetMapping("Cars")
//    public Cars cars(){
//        Cars cars = new Cars();
//        cars.setName("Subaru");
//        cars.setMark("Forester");
//        cars.setYear(2018);
//        return cars;
//    }
//    @PostMapping ("Cars")
//    public Cars cars(@RequestBody Cars cars){
//        System.out.println(cars.getMark());
//        return cars;
//    }