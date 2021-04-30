package ee.bcs.valiit.tasks.MyLessons.BankAccount.BankSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BankSqlLoginRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public String getPasswordByUser(String username) {
        String sql = "SELECT password From bank_login Where username= :username";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public void registerUser(LoginRequest loginRequest) {
        String sql = "INSERT INTO bank_login(username, password) VALUES(:dbUsername, :dbPassword)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbUsername", loginRequest.getUsername());
        paraMap.put("dbPassword", loginRequest.getPassword());
        jdbcTemplate.update(sql, paraMap);
    }

    public boolean doesUserExists(String username) {
        String sql = "SELECT count(*) FROM bank_login WHERE username = :username";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class) >0;
    }

//    public String getUserByPassword(String password) {
//        String sql = "SELECT username FROM bankLogin Where password= : password";
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("password", password);
//        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
//    }

}


