/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.modellab1.lab3and4apu.prj;

import process.Actor;
import rnd.Randomable;

/**
 *
 * @author apu
 */
public class PassengersGenerator extends Actor {
    //Посилання на модель системи
    private AirportModel model;
    // Генератор випадкового часу створення транзакції
    private Randomable rnd;
    // Тривалість роботи генератора
    private double finishTime;


    // Конструктор
    public PassengersGenerator(String name, AirportGUI gui, AirportModel model) {
        setNameForProtocol(name);
        this.model = model;
        finishTime = gui.getChooseModellingFinishTime().getDouble();
        rnd = gui.getChooseRandomPassInterval();
    }

    // Правила дії
    public void rule() {
        while (getDispatcher().getCurrentTime() <= finishTime) {
            holdForTime(rnd.next());
            getDispatcher().printToProtocol(
                            "  " + getNameForProtocol() + " create passenger.");
            Passenger passenger = new Passenger(model);
            dispatcher.addStartingActor(passenger);
        }
    }
}
