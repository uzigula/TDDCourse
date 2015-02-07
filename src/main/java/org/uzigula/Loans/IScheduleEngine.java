package org.uzigula.Loans;

import java.util.List;

/**
 * Created by umamani on 05/02/2015.
 */
public interface IScheduleEngine {
    List<Quota> generateSchedule(Loan loan);
}
