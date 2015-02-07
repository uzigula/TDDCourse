package org.uzigula.Loans;

/**
 * Created by umamani on 05/02/2015.
 */
public class LoanSimulator {

    private IScheduleEngine _scheduleEngine;

    public LoanSimulator (IScheduleEngine scheduleEngine) {
        _scheduleEngine=scheduleEngine;
    }
    public Loan GetLoan(LoanRequest loanRequest) {
        Loan loan = new Loan(loanRequest, _scheduleEngine);
        loan.GenerateSchedule();
        return loan;
    }
}
