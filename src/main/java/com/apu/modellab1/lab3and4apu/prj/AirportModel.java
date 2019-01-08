package com.apu.modellab1.lab3and4apu.prj;

import java.util.HashMap;
import java.util.Map;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import stat.DiscretHisto;
import stat.Histo;
import stat.IHisto;
import widgets.stat.IStatisticsable;

//Клас моделі
public class AirportModel implements IStatisticsable {
	
	// Посилання на диспетчера
	private Dispatcher dispatcher;
	// Посилання на візуальну частину
	private AirportGUI gui;
	
	////////Актори\\\\\\\\\
	// Генератор транзакцій
	private PassengersGenerator generator;
	// Обслуговуючий прилад
	private Ticketbox ticketbox;		
	//Бригада обслуговуючих пристроїв
	private MultiActor multiDevice;
	
	/////////Черги\\\\\\\\\
	// Черга транзакцій
	private QueueForTransactions<Passenger> queue;
	
	/////////Гістограми\\\\\\\\\\\\
	// Гістограма для довжини черги
	private DiscretHisto discretHistoQueue;
	// Гістограма для часу перебування у черзі
	private Histo histoTransactionWaitInQueue;
	// Гістограма для часу обслуговування
	private Histo histoTransactionServiceTime;
	// Гістограма для часу чекання Device
	private Histo histoWaitDevice;

	// ////////////////////////////////////////
	// Єдиний спосіб створити модель, це викликати цей конструктор
	// Він гарантовано передає посилання на диспетчера та TransGUI
	// ////////////////////////////////////////

	public AirportModel(Dispatcher d, AirportGUI g) {
		if (d == null || g == null) {
			System.out
					.println("Не визначено диспетчера або TransGUI для BuldModel");
			System.out.println("Подальша робота неможлива");
			System.exit(0);
		}
		dispatcher = d;
		gui = g;
		// Передаємо акторів до стартового списку диспетчера
		componentsToStartList();
	}

	private void componentsToStartList() {
		dispatcher.addStartingActor(getGenerator());
		dispatcher.addStartingActor(getMultiDevice());

	}

	public PassengersGenerator getGenerator() {
		if (generator == null) {
			generator = new PassengersGenerator("PassengersGenerator", gui, this);
		}
		return generator;
	}

	public QueueForTransactions<Passenger> getQueue() {
		if (queue == null) {
			queue = new QueueForTransactions<>("Queue", dispatcher,
					getDiscretHistoQueue());
		}
		return queue;
	}

	public Ticketbox getTicketbox() {
		if (ticketbox == null) {
			ticketbox = new Ticketbox("Ticketbox", gui, this);
			ticketbox.setHistoForActorWaitingTime(getHistoWaitDevice());
		}
		return ticketbox;
	}
	
	
	public MultiActor getMultiDevice() {
		if(multiDevice == null){
			multiDevice = new MultiActor();
			multiDevice.setOriginal(getTicketbox());
			multiDevice.setNumberOfClones(gui.getChooseTicketboxAmount().getInt());
			multiDevice.setNameForProtocol("Створення бригади обслуговуючих пристроїв");
		}
		return multiDevice;
	}


	public DiscretHisto getDiscretHistoQueue() {
		if (discretHistoQueue == null) {
			discretHistoQueue = new DiscretHisto();
		}
		return discretHistoQueue;
	}

	public Histo getHistoTransactionWaitInQueue() {
		if (histoTransactionWaitInQueue == null) {
			histoTransactionWaitInQueue = new Histo();
		}
		return histoTransactionWaitInQueue;
	}

	public Histo getHistoWaitDevice() {
		if (histoWaitDevice == null) {
			histoWaitDevice = new Histo();
		}
		return histoWaitDevice;
	}

	public void initForTest() {
		getQueue().setPainter(gui.getDiagramQueue().getPainter());
		if(gui.getJCheckBox().isSelected()){
			dispatcher.setProtocolFileName("Console");
		}

	}

	public Histo getHistoTransactionServiceTime() {
		if( histoTransactionServiceTime== null)
			histoTransactionServiceTime = new Histo();
		return histoTransactionServiceTime;
	}

	// Реалізація інтерфейсу IStatisticsable
	@Override
	public void initForStatistics() {

	}

	@Override
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("Гістограма для довжини черги", getDiscretHistoQueue());
		map.put("Гістограма для часу чекання у черзі",
				getHistoTransactionWaitInQueue());
		map.put("Гістограма для часу простою обслуговуючого пристрою",
				getTicketbox().getWaitingTimeHisto());
		map.put("Гістограма для часу обслуговування",
				getHistoTransactionServiceTime());

		return map;
	}
}
