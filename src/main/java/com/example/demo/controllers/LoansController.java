package com.example.demo.controllers;

import com.example.demo.models.Loan;
import com.example.demo.models.Status;
import com.example.demo.repository.LoanRepository;
import com.example.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoansController {

    @Autowired
    EntityManager entityManager;
    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/create")
    public Object createLoan(Loan loan) {
        com.example.demo.entity.Loan item =
                new com.example.demo.entity.Loan();
        item.setPersonalId(loan.getPersonalId());
        item.setFirstName(loan.getFirstName());
        item.setLastName(loan.getLastName());
        item.setBirthday(loan.getBirthday());
        item.setEmployer(loan.getEmployer());
        item.setSalary(loan.getSalary());
        item.setLiability(loan.getLiability());
        item.setRequestedAmount(loan.getRequestedAmount());
        item.setRequestedTerm(loan.getRequestedTerm());
        Status loanRequestStatus = Utils.verifyLoanRequest(loan);
        item.setStatus(loanRequestStatus);

        try {
            loanRepository.save(item);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "OK";
    }

    @GetMapping("/delete")
    @Transactional
    public String deleteLoan(long loanId) {
        com.example.demo.entity.Loan currentLoan = entityManager.find(com.example.demo.entity.Loan.class, loanId);

        if (currentLoan == null) {
            return "No such record";
        }
        entityManager.remove(currentLoan);
        entityManager.flush();
        entityManager.clear();

        return "OK";
    }


    @GetMapping("/getLoans")
    public  List<com.example.demo.entity.Loan> getLoans() {
        List<com.example.demo.entity.Loan> s = loanRepository.findAll();
        return s;
    }

}
