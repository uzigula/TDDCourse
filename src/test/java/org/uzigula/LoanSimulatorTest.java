package org.uzigula;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by umamani on 05/02/2015.
 */
public class LoanSimulatorTest {

    private LoanSimulator loanSimulator;
    private LoanRequest loanRequest;

    @Before
public void setup(){
        loanSimulator = new LoanSimulator();
        loanRequest = new LoanRequest();
}
@Test           //metodo_input_escenario_comportamientoesperado
    public void GetLoan_GivenACreditRequestFor5000Usd10Months10Rate_ThenGetCreditWith10Quota() {
    //Arrange
    loanRequest.capital = 5000.00;
    loanRequest.rate =0.10;
    loanRequest.term = 10;
    // Act
    Loan loan = loanSimulator.GetLoan(loanRequest);

    Quota quota = loan.PaymentSchedule.get(1);
    // Assertion
    assertTrue("Should be a Quota collection", quota instanceof Quota);
    assertEquals(10, loan.PaymentSchedule.size());

}
    @Test
    public void GetLoan_GivenACreditRequestFor5000Usd10Months10Rate_ThenTotalRateShouldBe500(){
        //Arrange
        loanRequest.capital = 5000.00;
        loanRequest.rate = 0.10;
        loanRequest.term = 10;
        // Act
        Loan loan = loanSimulator.GetLoan(loanRequest);
        // Assertion
        assertEquals(500.00, loan.TotalRate(),0.0001);
        assertEquals(5000.00, loan.TotalCapital(),0.001);
    }

    @Test
    public void GetLoan_GivenACreditRequestFor3000Usd12Months20Rate_ThenTotalRateShouldBe600AndShouldGet12Quotas(){
        //Arrange
        loanRequest.capital = 3000.00;
        loanRequest.rate = 0.20;
        loanRequest.term = 12;
        // Act
        Loan loan = loanSimulator.GetLoan(loanRequest);
        // Assertion
        assertEquals(600.00, loan.TotalRate(),0.0001);
        assertEquals(3000.00, loan.TotalCapital(),0.001);
        assertEquals(12, loan.PaymentSchedule.size());
    }
    }

