package com.example.sampleapplication.pojo;

import javax.inject.Inject;

public class Boltons implements House {

    @Inject
    public Boltons(){
    }

    @Override
    public void prepareForWar() {
        System.out.println(this.getClass().getSimpleName()+" prepared for war");
    }

    @Override
    public void reportForWar() {
        System.out.println(this.getClass().getSimpleName()+" report for war");
    }
}
