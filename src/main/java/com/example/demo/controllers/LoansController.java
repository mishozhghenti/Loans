package com.example.demo.controllers;

import com.example.demo.models.Loan;
import com.example.demo.models.RequestStatus;
import com.example.demo.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loans")
public class LoansController {


    @GetMapping("/create")
    public RequestStatus createLoan(Loan loan) {
        return Utils.verifyLoanRequest(loan);
    }

    @GetMapping("/delete")
    public String deleteLoan(long loanId) {
        return "Deleted";
    }


    @GetMapping("/getLoans")
    public String getLoans() {
        return "All Loans";
    }

}
