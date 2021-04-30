package ee.bcs.valiit.tasks.MyLessons.BankAccount.BankSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api")
@RestController
public class BankSQLLoginController {

    @Autowired
    BankSqlLoginService bankSqlLoginService;

    @CrossOrigin
    @PutMapping("/public/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return bankSqlLoginService.login(loginRequest);
    }
    @CrossOrigin
    @PutMapping("/public/register")
    public void registerUser(@RequestBody LoginRequest loginRequest) {
        bankSqlLoginService.registerUser(loginRequest);
    }


}
