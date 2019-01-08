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
    private QueueForTransactions<Passenger> queue;
    private Histo histoQueue;
    private Histo histoService;
    private boolean serviceDone;

    public Passenger(AirportModel model) {
        this.queue = model.getQueue();
        this.histoQueue = model.getHistoTransactionWaitInQueue();
        this.histoService = model.getHistoTransactionServiceTime();
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
        queue.add(this);
        waitForCondition(() -> !queue.contains(this), "мають забрати на обслуговування");
        histoQueue.add(dispatcher.getCurrentTime() - createTime);
        waitForCondition(() -> serviceDone, "мають завершити обслуговування");
        histoService.add(dispatcher.getCurrentTime() - createTime);
    }

}
