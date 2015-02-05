package org.uzigula;

/**
 * Created by umamani on 05/02/2015.
 */
public class LoanSimulator {
    public Loan GetLoan(LoanRequest loanRequest) {
        Loan loan = new Loan(loanRequest);
        loan.GenerateSchedule();
        return loan;
    }
}
