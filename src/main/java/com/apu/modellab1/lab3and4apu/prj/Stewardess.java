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
import rnd.Randomable;

/**
 *
 * @author apu
 */
public class Stewardess extends Actor {

    // Черга для тразакцій
    private QueueForTransactions<Passenger> queueToStewardess;
    private QueueForTransactions<Passenger> queueToAirplane;
    // Генератор часу, що витрачає прилад на обслуговування транзакції
    private Randomable rnd;
    // Час роботи генератора
    private double finishTime;
    private boolean buzyFlag;
    private double steardessRestTime = 0.1;

    // Конструктор, у якому ініціалізуються усі поля класу
    // через доступ до моделі та візуальної частини
    public Stewardess(String name, AirportGUI gui, AirportModel model) {
            setNameForProtocol(name);
            finishTime = gui.getChooseModellingFinishTime().getDouble();
            rnd = gui.getChooseRandomTicketboxHandleTime();
            queueToStewardess = model.getQueueToStewardess();
            queueToAirplane = model.getQueueToAirplane();
    }

    public void rule() throws DispatcherFinishException {
            // Створюємо умову, виконання якої буде чекати актор
            BooleanSupplier queueSize = () -> queueToStewardess.size() > 0;
            BooleanSupplier stewardessBusy = () -> this.isBuzyFlag() == false;
            // цикл виконання правил дії
            while (getDispatcher().getCurrentTime() <= finishTime) {
                    // Перевірка наявності транзакції та чекання на її появу
                    waitForCondition(queueSize, "for Passenger in queue to stewardesses");
                    waitForCondition(stewardessBusy, "for Stewardess become unbusy");
                    this.setBuzyFlag(true);
                    Passenger passenger = queueToStewardess.removeFirst();
                    queueToAirplane.add(passenger);
                    // Імітація обробки транзакції
                    holdForTime(rnd.next());
                    passenger.setServiceDone(true);
                    this.setBuzyFlag(false);
                    holdForTime(steardessRestTime);
            }
    }

    public boolean isBuzyFlag() {
        return buzyFlag;
    }

    public void setBuzyFlag(boolean buzyFlag) {
        this.buzyFlag = buzyFlag;
    }
    
}
