package com.company;

import java.util.ArrayList;
import java.util.List;

public class FireStation implements FireStationRegister {

    private List<FireObserver> fireObservers=new ArrayList<>();

    @Override
    public void registerFireStation(FireObserver fo) {
        if(fo!=null)
            fireObservers.add(fo);
    }

    @Override
    public void notifyFireStation(String adress) {
        if(adress!=null)
            for(FireObserver f:fireObservers)
                f.fire(adress);
    }
}
