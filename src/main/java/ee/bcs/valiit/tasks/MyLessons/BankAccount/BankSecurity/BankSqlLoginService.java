package ee.bcs.valiit.tasks.MyLessons.BankAccount.BankSecurity;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BankSqlLoginService {

    @Autowired
    private BankSqlLoginRepository bankSqlLoginRepository;

    public String login(LoginRequest loginRequest) {

        String password = bankSqlLoginRepository.getPasswordByUser(loginRequest.getUsername());
        if (loginRequest.getPassword().equals(password)) {
            Date today = new Date();
            Date tokenExpirationDate = new Date(today.getTime() + 1000 * 60 * 60 * 25);
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setExpiration(tokenExpirationDate)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                    .claim("username", loginRequest.getUsername());
            return jwtBuilder.compact();
        }else {
            throw new ApplicationContextException("");
        }
    }


}
