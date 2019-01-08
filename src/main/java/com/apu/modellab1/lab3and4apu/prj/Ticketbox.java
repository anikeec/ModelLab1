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

// Клас для обслуговуючого пристрою
public class Ticketbox extends Actor {

    // Черга для тразакцій
    private QueueForTransactions<Passenger> queue;
    // Генератор часу, що витрачає прилад на обслуговування транзакції
    private Randomable rnd;
    // Час роботи генератора
    private double finishTime;

    // Конструктор, у якому ініціалізуються усі поля класу
    // через доступ до моделі та візуальної частини
    public Ticketbox(String name, AirportGUI gui, AirportModel model) {
            setNameForProtocol(name);
            finishTime = gui.getChooseModellingFinishTime().getDouble();
            rnd = gui.getChooseRandomTicketboxHandleTime();
            queue = model.getQueue();
    }

    public void rule() throws DispatcherFinishException {
            // Створюємо умову, виконання якої буде чекати актор
            BooleanSupplier queueSize = () -> queue.size() > 0;
            // цикл виконання правил дії
            while (getDispatcher().getCurrentTime() <= finishTime) {
                    // Перевірка наявності транзакції та чекання на її появу
                    waitForCondition(queueSize, "we wait for Passenger in queue");
                    Passenger passenger = queue.removeFirst();
                    // Імітація обробки транзакції
                    holdForTime(rnd.next());
                    passenger.setServiceDone(true);
            }
    }
}
