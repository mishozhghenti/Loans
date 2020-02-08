package com.example.demo.controllers;

import com.example.demo.models.Loan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loans")
public class LoansController {



    @GetMapping("/create")
    public String createLoan(Loan loan){
        return "Created";
    }

    @GetMapping("/delete")
    public String deleteLoan(){
        return "Deleted";
    }


    @GetMapping("/getLoans")
    public String getLoans(){
        return "All Loans";
    }

}
