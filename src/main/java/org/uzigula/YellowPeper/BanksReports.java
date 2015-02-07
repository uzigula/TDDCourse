package org.uzigula.YellowPeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umamani on 06/02/2015.
 */
public class BanksReports {

    private final ICardsStore _cardsStore;

    public BanksReports(ICardsStore cardsStore) {
      _cardsStore = cardsStore;
    }

    public List<BanksDto> banksAssociated(String accountId) {
        List<BanksDto> banks =new ArrayList<BanksDto>() ;
        List<Cards> cards = _cardsStore.getCardsAssociated(accountId);
        for(Cards card:cards){
            //TODO implementar un mejor metodo de busqueda
            if (!isDuplicated(banks,card.bank.Name) && card.showBankName) banks.add(new BanksDto(card.bank));
        }
        return banks;
    }

    private boolean isDuplicated(List<BanksDto> banks, String name) {
        for(BanksDto bank:banks){
            if (bank.Name.equals(name)) return true;
        }
        return false;
    }


}
