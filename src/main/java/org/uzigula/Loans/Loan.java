package org.uzigula.Loans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umamani on 05/02/2015.
 */
public class Loan {
    private final double rate;
    private final int term;
    private double capital;
    public List<Quota> PaymentSchedule;
    private IScheduleEngine _scheduleEngine;

    public Loan(LoanRequest loanRequest, IScheduleEngine scheduleEngine) {
        capital = loanRequest.capital;
        rate = loanRequest.rate;
        term = loanRequest.term;
        PaymentSchedule = new ArrayList<Quota>();
        _scheduleEngine = scheduleEngine;
    }

    public void GenerateSchedule()  {
        PaymentSchedule = _scheduleEngine.generateSchedule(this);
    }


    public double TotalRate(){return capital*rate;};
    public double TotalCapital(){return capital;};

    public double getRate() {
        return rate;
    }

    public int getTerm() {
        return term;
    }

    public double getCapital() {
        return capital;
    }
}
