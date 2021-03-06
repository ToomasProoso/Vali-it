package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController

public class Lesson5 {

    private static Map<String, Account> accountMap = new HashMap<>();

    // http://localhost:8080/account
    @PostMapping("bankAccount/account")
    public void Account(@RequestBody Account request) {
        accountMap.put(request.getAccountNumber(), request);
    }

//    @GetMapping("bankAccount/getBalance/{iban}")
//    public String getBalance(@PathVariable("iban") String iban) {
//        if (accountMap.get(iban).isLocked() == false) {
//            return "Thers on account";
//        }
//    }

    // TODO täienda oma BankControllerit nii, et sa hoiad Map-is konto balanssi asemel konto objekti
    //  1)
    // Selleks loo uus konto objekt, mis sisaldab minimaalselt
    // * kontoNr
    // * kontoOmanikuNimi
    // * balanss
    // * kas konto on lukustatud
    // 2)
    // täienda konto loomis teenust nii, et sa salvestad ka konto omaniku nime
    // 3)
    // loo 2 uut teenust: lockAccount ja unlockAccount
    // 4)
    // juhul kui konto on lukustatud ei tohi saada sellele kontole raha juurde kande ega kontolt raha ära võtta
    // ehk siis withdrawMoney, depositMoney ja transferMoney teenused ei tohi töötada
}
