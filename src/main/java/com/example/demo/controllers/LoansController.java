package com.example.demo.controllers;

import com.example.demo.models.Loan;
import com.example.demo.models.Status;
import com.example.demo.repository.LoanRepository;
import com.example.demo.response.Response;
import com.example.demo.response.Result;
import com.example.demo.utils.Utils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("api/loans")
public class LoansController {

    private final
    EntityManager entityManager;
    private final LoanRepository loanRepository;

    public LoansController(EntityManager entityManager, LoanRepository loanRepository) {
        this.entityManager = entityManager;
        this.loanRepository = loanRepository;
    }

    @PostMapping("/create")
    public Response createLoan(Loan loan) {
        Response response;
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
            response = Result.OK.getResponse(loanRequestStatus);
        } catch (Exception ign) {
            response = Result.ERROR.getResponse();
        }
        return response;
    }

    @GetMapping("/delete")
    @Transactional
    public Response deleteLoan(long loanId) {
        com.example.demo.entity.Loan currentLoan = entityManager.find(com.example.demo.entity.Loan.class, loanId);

        if (currentLoan == null) {
            return Result.ERROR.getResponse();
        }
        entityManager.remove(currentLoan);
        entityManager.flush();
        entityManager.clear();

        return Result.OK.getResponse();

    }


    @GetMapping("/getLoans")
    public Response getLoans() {
        return Result.OK.getResponse(loanRepository.findAll());
    }

}
