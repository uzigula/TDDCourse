package org.uzigula.Loans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umamani on 05/02/2015.
 */
public class SimpleScheduleEngine implements IScheduleEngine {

    @Override
    public List<Quota> generateSchedule(final Loan loan) {
            List<Quota> schedule = new ArrayList<>();
            for(int i=1;i<= loan.getTerm();i++)
                schedule.add(new Quota(){{Capital = loan.getCapital()/loan.getTerm();
                    RateAmount =(loan.getCapital()/loan.getTerm())*loan.getRate();}});
            return schedule;
        }
}
