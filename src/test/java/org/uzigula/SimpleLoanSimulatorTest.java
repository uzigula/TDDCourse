package org.uzigula;

import org.junit.Before;
import org.junit.Test;
import org.uzigula.Loans.*;

import static org.junit.Assert.*;

/**
 * Created by umamani on 05/02/2015.
 */
public class SimpleLoanSimulatorTest {

    private LoanSimulator loanSimulator;
    private LoanRequest loanRequest;

    @Before
public void setup(){
        loanSimulator = new LoanSimulator(new SimpleScheduleEngine());
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

    @Test
    public void GetLoan_GivenACreditRequestFor1000Usd10Months1Rate_ThenShouldGet12QuotasWithRightComposition(){
        //Arrange
        loanRequest.capital = 1000.00;
        loanRequest.rate = 0.01;
        loanRequest.term = 10;
        // Act
        Loan loan = loanSimulator.GetLoan(loanRequest);
        // Assertion
        assertEquals(10, loan.PaymentSchedule.size());
        for(Quota quota:loan.PaymentSchedule){
            assertEquals("Quota Capital should be" ,100.00,quota.Capital,0.0001);
            assertEquals("Quota Rate Amount should be", 1.00,quota.RateAmount,0.0001);
            assertEquals("Quota Total should be" ,101.00, quota.getTotal(),0.0001);
        }
    }
    }

