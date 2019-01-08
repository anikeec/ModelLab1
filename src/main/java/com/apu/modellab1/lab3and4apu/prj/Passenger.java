/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.modellab1.lab3and4apu.prj;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import stat.Histo;

/**
 *
 * @author apu
 */
public class Passenger extends Actor {
    private double createTime;
    private QueueForTransactions<Passenger> queueToTicketbox;
    private QueueForTransactions<Passenger> queueToStewardess;
    private Histo histoQueueToTicketbox;
    private Histo histoPassengerService;
    private boolean serviceDone;

    public Passenger(AirportModel model) {
        this.queueToTicketbox = model.getQueueToTicketbox();
        this.queueToStewardess = model.getQueueToStewardess();
        this.histoQueueToTicketbox = model.getHistoPassengerWaitInQueueToTicketbox();
        this.histoPassengerService = model.getHistoPassengerServiceTime();
    }

    public double getCreateTime() {
        return createTime;
    }

    public void setServiceDone(boolean b) {
        this.serviceDone = b;
    }

    @Override
    public String toString() {
        return "Passenger " + createTime;
    }

    @Override
    protected void rule() throws DispatcherFinishException {
        createTime = dispatcher.getCurrentTime();
        nameForProtocol = "Passenger " + createTime;
        queueToTicketbox.add(this);
        waitForCondition(() -> !queueToTicketbox.contains(this), "мають забрати на обслуговування");
        histoQueueToTicketbox.add(dispatcher.getCurrentTime() - createTime);
        waitForCondition(() -> serviceDone, "мають завершити обслуговування");
        histoPassengerService.add(dispatcher.getCurrentTime() - createTime);
    }

}
