package org.uzigula;

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

    public Loan(LoanRequest loanRequest) {
        capital = loanRequest.capital;
        rate = loanRequest.rate;
        term = loanRequest.term;
        PaymentSchedule = new ArrayList<Quota>();
    }

    public void GenerateSchedule() {
        for(int i=1;i<=term;i++)
            PaymentSchedule.add(new Quota(){{Capital = capital/term;
                RateAmount =(capital/term)*rate;}});
    }

    public double TotalRate(){return capital*rate;};
    public double TotalCapital(){return capital;};

}
