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
        String sql = "SELECT password From bankLogin Where username= :username";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }
}


