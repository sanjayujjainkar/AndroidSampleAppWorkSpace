package com.example.sampleapplication.pojo;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class BraavosModule {

    Cash cash;
    Soldiers soldiers;

    @Inject
    public BraavosModule(Cash cash, Soldiers soldiers){
        this.cash=cash;
        this.soldiers=soldiers;
    }

    @Provides //Provides cash dependency
    Cash provideCash(){
        return cash;
    }

    @Provides //provides soldiers dependency
    Soldiers provideSoldiers(){
        return soldiers;
    }

}
