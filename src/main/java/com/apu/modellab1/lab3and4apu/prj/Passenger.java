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
    private QueueForTransactions<Passenger> queueToRailway;
    private Histo histoQueueToTicketbox;
    private Histo histoQueueToStewardess;
    private Histo histoPassengerService;
    private boolean serviceDone;
    //queue max size
    private Integer queueMaxSize;

    public Passenger(AirportModel model) {
        this.queueToTicketbox = model.getQueueToTicketbox();
        this.queueToStewardess = model.getQueueToStewardess();
        this.queueToRailway = model.getQueueToRailway();
        this.histoQueueToTicketbox = model.getHistoPassengerWaitInQueueToTicketbox();
        this.histoQueueToStewardess = model.getHistoPassengerWaitInQueueToStewardess();
        this.histoPassengerService = model.getHistoPassengerServiceTime();
        this.queueMaxSize = model.getQueueMaxSize();
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
        if(queueToTicketbox.size() < queueMaxSize) {
            queueToTicketbox.add(this);
            waitForCondition(() -> !queueToTicketbox.contains(this), "wait for ticketbox service");
            histoQueueToTicketbox.add(dispatcher.getCurrentTime() - createTime);
            waitForCondition(() -> serviceDone, "finish ticketbox service");
            histoPassengerService.add(dispatcher.getCurrentTime() - createTime);
            return;
        }
        if(queueToStewardess.size() < queueMaxSize) {
            queueToStewardess.add(this);
            waitForCondition(() -> !queueToStewardess.contains(this), "wait for stewardess service");
            histoQueueToStewardess.add(dispatcher.getCurrentTime() - createTime);
            waitForCondition(() -> serviceDone, "finish stewardess service");
            histoPassengerService.add(dispatcher.getCurrentTime() - createTime);
            return;
        }
        queueToRailway.add(this);
    }

}
