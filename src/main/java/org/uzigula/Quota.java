package org.uzigula;

/**
 * Created by umamani on 05/02/2015.
 */
public class Quota {
    public double Capital;
    public double RateAmount;

    public double getTotal() {
        return Capital+RateAmount;
    }
}
