package ee.bcs.valiit.tasks.MyLessons.BankAccount.BankSecurity;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RequestMapping("api")
@RestController
public class BankSQLLoginController {

    @Autowired BankSqlLoginService bankSqlLoginService;


    @PutMapping("/api/public/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return bankSqlLoginService.login(loginRequest);


    }
//    @PutMapping("")
//    public String register(@RequestBody RegisterRequest registerRequest){
//        registerRequest.getUsername().equals()
//    }


}
