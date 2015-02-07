package org.uzigula;

import org.junit.Before;
import org.junit.Test;
import org.uzigula.YellowPeper.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by umamani on 06/02/2015.
 */
public class BanksReportsTest {

    private ICardsStore cardsStoreStub;
    private BanksReports reportBank;
    private String accountId;

    @Before
    public void setUp() throws Exception {
        cardsStoreStub = mock(ICardsStore.class);
        reportBank = new BanksReports(cardsStoreStub);
        accountId = "9876453120";
    }

    private void verifyCallCardStore(String accountId) {
        verify(cardsStoreStub).getCardsAssociated(accountId);
    }

    @Test
    public void BanksAssociated_GivenAnAccountWithNoCardsAssociated_ShouldEmptyListOfBanks(){
        when(cardsStoreStub.getCardsAssociated(accountId)).thenReturn(new ArrayList<Cards>());
        List<BanksDto> banks = reportBank.banksAssociated(accountId);
        assertEquals(0,banks.size());
        verifyCallCardStore(accountId);
    }

    @Test
    public void BanksAssociated_GivenAnAccountWithOneCardAssociated_ShouldReturnAListOfOneBank(){
        List<Cards>  cards = new ArrayList<Cards>();
        cards.add(new Cards(){{showBankName=true; bank = new Bank(){{Name="Banco de los Andes";}};}});
        when(cardsStoreStub.getCardsAssociated(accountId)).thenReturn(cards);
        List<BanksDto> banks = reportBank.banksAssociated(accountId);
        assertEquals(1, banks.size());
        verifyCallCardStore(accountId);
    }

    @Test
    public void BanksAssociated_GivenAnAccountWithTwoCardsAssociated_ShouldReturnAListOfOneBankNamedBancoDelosAndes(){
        List<Cards>  cards = new ArrayList<Cards>();
        cards.add(new Cards(){{showBankName=true; bank = new Bank(){{Name="Banco de los Andes";}};}});
        cards.add(new Cards(){{showBankName=true; bank = new Bank(){{Name="Banco de los Andes";}};}});
        when(cardsStoreStub.getCardsAssociated(accountId)).thenReturn(cards);
        List<BanksDto> banks = reportBank.banksAssociated(accountId);
        assertEquals(1, banks.size());
        assertEquals("Should be Banco de los Andes","Banco de los Andes",banks.get(0).Name);
        verifyCallCardStore(accountId);
    }

    @Test
    public void BanksAssociated_GivenAnAccountWithThreeCardsAssociated_ShouldReturnAListOfTwoBanks(){
        List<Cards>  cards = new ArrayList<Cards>();
        cards.add(new Cards(){{showBankName=true; bank = new Bank(){{Name="Banco de los Andes";}};}});
        cards.add(new Cards(){{showBankName=true; bank = new Bank(){{Name="Banco de los Andes";}};}});
        cards.add(new Cards(){{showBankName=true; bank = new Bank(){{Name="Banco Wiesse";}};}});
        when(cardsStoreStub.getCardsAssociated(accountId)).thenReturn(cards);
        List<BanksDto> banks = reportBank.banksAssociated(accountId);
        assertEquals(2, banks.size());
        assertEquals("Should be Banco de los Andes","Banco de los Andes",banks.get(0).Name);
        assertEquals("Should be Banco Wiesse","Banco Wiesse",banks.get(1).Name);
        verifyCallCardStore(accountId);
    }

    @Test
    public void BanksAssociated_GivenAnAccountWithThreeCardsAssociatedWhichNoAllowShowBankName_ShouldReturnEmptyBanks(){
        List<Cards>  cards = new ArrayList<Cards>();
        cards.add(new Cards(){{showBankName=false; bank = new Bank(){{Name="Banco de los Andes";}};}});
        cards.add(new Cards(){{showBankName=false;bank = new Bank(){{Name="Banco de los Andes";}};}});
        cards.add(new Cards(){{showBankName=false;bank = new Bank(){{Name="Banco Wiesse";}};}});
        when(cardsStoreStub.getCardsAssociated(accountId)).thenReturn(cards);
        List<BanksDto> banks = reportBank.banksAssociated(accountId);
        assertEquals(0, banks.size());
        verifyCallCardStore(accountId);
    }
}
