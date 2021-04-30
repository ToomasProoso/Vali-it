package ee.bcs.valiit.tasks.MyLessons.BankAccount.BankSecurity;


import ee.bcs.valiit.solution.exception.SampleApplicationException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BankSqlLoginService {

    @Autowired
    private BankSqlLoginRepository bankSqlLoginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String login(LoginRequest loginRequest) {
        String password = bankSqlLoginRepository.getPasswordByUser(loginRequest.getUsername());
        if (passwordEncoder.matches(loginRequest.getPassword(), password)) {
            Date today = new Date();
            Date tokenExpirationDate = new Date(today.getTime() + 1000 * 60 * 60 * 25);
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setExpiration(tokenExpirationDate)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                    .claim("username", loginRequest.getUsername());
            return jwtBuilder.compact();
        } else {
            throw new SampleApplicationException("Wrong password!!!");
        }
    }

    public void registerUser(LoginRequest loginRequest) {
        if (bankSqlLoginRepository.doesUserExists(loginRequest.getUsername())) {
            throw new SampleApplicationException("Username already exists!!!");
        } else {
        }
        String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
        loginRequest.setPassword(encodedPassword);
        bankSqlLoginRepository.registerUser(loginRequest);
    }


}
