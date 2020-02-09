package com.example.demo;

import com.example.demo.models.Loan;
import com.example.demo.models.Status;
import com.example.demo.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
class ApplicationTests {

    private final DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    @Test
    void contextLoads() {
    }

    @Test
    void loanScoreTest() throws ParseException {
        Loan loan = new Loan();
        loan.setPersonalId("123");
        loan.setFirstName("Mikheil");
        loan.setLastName("Zhghenti");
        String dDate = "Wed Jun 12 00:00:00 GEST 1996";
        loan.setBirthday(df.parse(dDate));
        loan.setEmployer("asd");
        loan.setSalary(new BigDecimal(4500));
        loan.setLiability(new BigDecimal(1400));
        loan.setRequestedAmount(new BigDecimal(3000));
        loan.setRequestedTerm(8);

        assert Utils.verifyLoanRequest(loan) == Status.MANUAL;
        loan.setLiability(new BigDecimal(180));
        assert Utils.verifyLoanRequest(loan) == Status.APPROVED;
        loan.setLiability(new BigDecimal(2000));
        assert Utils.verifyLoanRequest(loan) == Status.REJECTED;
    }

}
