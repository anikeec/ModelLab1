/**
 * 
 */
package com.apu.modellab1.example.market;

import java.util.List;
import java.util.Random;
import java.util.function.BooleanSupplier;

import process.Actor;
import rnd.Randomable;

/**
 * @author 44
 * 
 */
public class Client extends Actor {
	
	private MarketGUI gui;
	private MarketModel model;
	private  double finishTime;
	private  Randomable advertisingRnd;
	private  Randomable contactRnd;
	private  List<Client> allClientList;
	private boolean ready = false;
	Random rnd=new Random();

	public Client(String name, MarketGUI g,MarketModel m) {
		if(m==null || g == null){
			System.out.println("Не визначено model або gui для Client!?");
			System.exit(0);
		}
		setNameForProtocol(name);
		model = m;
		gui=g;
		advertisingRnd = gui.getChooseRandomAdvertising().getRandom();
		contactRnd = gui.getChooseRandomContact().getRandom();
		finishTime = gui.getChooseDataFinishTime().getDouble();
		allClientList = model.getAllClientList();
		
	}
	protected void rule() {
		// Початок правил дії клієнта
		// Спочатку він затримується на час впливу реклами,
		// або доти поки хтось його зустріне і не залучить до покупців,
		// викликавши метод setReady
		waitForConditionOrHoldForTime(()-> ready," може хтось загітує", advertisingRnd.next());
		if (!ready) {
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " піддався впливу реклама.");
			setReady();
		}
		// Тепер клієнт починає рекламувати товар, зустрічаючи інших клієнтів
		while (getDispatcher().getCurrentTime() < finishTime
				& model.getNumberOfReady() < allClientList.size()) {
			// затримка до зустрічи з одним із кліентів
			holdForTime(contactRnd.next());
			// формуємо випадовий номер зустрінутого клієнта
			int someClientNumber = rnd.nextInt(allClientList.size());
			Client someClient = (Client) allClientList.get(someClientNumber);
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " зустрів "
							+ someClient.getNameForProtocol());
			if (someClient.ready)
				getDispatcher().printToProtocol(
						"  " + someClient.getNameForProtocol()
								+ " вже купує товар");
			else {
				getDispatcher().printToProtocol(
						"  " + someClient.getNameForProtocol() + " повірив "
								+ getNameForProtocol());
				someClient.setReady();
			}
		}
	}

	private void setReady() {
		if (!this.ready) {
			this.ready = true;
			model.incNumberOfReady();
		}
	}


}
