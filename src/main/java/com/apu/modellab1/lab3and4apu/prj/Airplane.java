/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.modellab1.lab3and4apu.prj;

import java.util.function.BooleanSupplier;
import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;

/**
 *
 * @author apu
 */
public class Airplane extends Actor {

    // Черга для тразакцій
    private QueueForTransactions<Passenger> queueToAirplane;
    // Fly time
    private double airplainFlyTime = 50;
    // Passengers per fly
    private double passengersPerFly;
    // Час роботи генератора
    private double finishTime;
    private Stewardess stewardess;

    // Конструктор, у якому ініціалізуються усі поля класу
    // через доступ до моделі та візуальної частини
    public Airplane(String name, AirportGUI gui, AirportModel model) {
            setNameForProtocol(name);
            finishTime = gui.getChooseModellingFinishTime().getDouble();
//            rnd = gui.getChooseRandomTicketboxHandleTime();
            queueToAirplane = model.getQueueToAirplane();
            stewardess = model.getStewardess();
            passengersPerFly = model.getPassengersPerFly();
            airplainFlyTime = model.getAirplaneFlyTime().next();
    }

    public void rule() throws DispatcherFinishException {
            // Створюємо умову, виконання якої буде чекати актор
            BooleanSupplier queueSize = () -> queueToAirplane.size() >= 100;
            BooleanSupplier stewardessBusy = () -> stewardess.isBuzyFlag() == false;
            // цикл виконання правил дії
            while (getDispatcher().getCurrentTime() <= finishTime) {
                    // Перевірка наявності транзакції та чекання на її появу
                    waitForCondition(queueSize, "for Passengers in queue to airplane");
                    waitForCondition(stewardessBusy, "for Stewardess become unbusy");
                    stewardess.setBuzyFlag(true);
                    Passenger passenger;
//                    int amount = queueToAirplane.size();
                    for(int i=0; i< passengersPerFly; i++) {
                        passenger = queueToAirplane.removeFirst();
                        passenger.setServiceDone(true);
                    }
                    stewardess.setBuzyFlag(false);
                    // Імітація обробки транзакції
                    holdForTime(airplainFlyTime);                    
            }
    }
}
