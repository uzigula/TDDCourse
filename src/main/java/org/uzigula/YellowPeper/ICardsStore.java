package org.uzigula.YellowPeper;

import java.util.List;

/**
 * Created by umamani on 06/02/2015.
 */
public interface ICardsStore {
    List<Cards> getCardsAssociated(String accountId);
}
