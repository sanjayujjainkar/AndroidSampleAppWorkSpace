package com.example.sampleapplication.pojo;

import dagger.Component;

@Component(modules = BraavosModule.class)
public interface BattleComponent {
    War getWar();
    Cash getCash();
    Soldiers getSoldiers();
}
