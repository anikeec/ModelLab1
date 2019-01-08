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
	private MultiActor multTicketbox;
	
	/////////Черги\\\\\\\\\
	// Черга транзакцій
	private QueueForTransactions<Passenger> queueToTicketbox;        
        private QueueForTransactions<Passenger> queueToStewardess;
	
	/////////Гістограми\\\\\\\\\\\\
	// Гістограма для довжини черги
	private DiscretHisto discretHistoQueueToTicketbox;
        private DiscretHisto discretHistoQueueToStewardess;
	// Гістограма для часу перебування у черзі
	private Histo histoPassengerWaitInQueueToTicketbox;
        private Histo histoPassengerWaitInQueueToStewardess;
	// Гістограма для часу обслуговування
	private Histo histoPassengerServiceTime;
	// Гістограма для часу чекання Device
	private Histo histoWaitTicketbox;
        private Histo histoWaitStewardess;

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
		dispatcher.addStartingActor(getMultiTicketbox());

	}

	public PassengersGenerator getGenerator() {
		if (generator == null) {
			generator = new PassengersGenerator("PassengersGenerator", gui, this);
		}
		return generator;
	}

	public QueueForTransactions<Passenger> getQueueToTicketbox() {
		if (queueToTicketbox == null) {
			queueToTicketbox = new QueueForTransactions<>("QueueToTicketbox", dispatcher,
					getDiscretHistoQueueToTicketbox());
		}
		return queueToTicketbox;
	}
        
        public QueueForTransactions<Passenger> getQueueToStewardess() {
		if (queueToStewardess == null) {
			queueToStewardess = new QueueForTransactions<>("QueueToStewardess", dispatcher,
					getDiscretHistoQueueToStewardess());
		}
		return queueToStewardess;
	}

	public Ticketbox getTicketbox() {
		if (ticketbox == null) {
			ticketbox = new Ticketbox("Ticketbox", gui, this);
			ticketbox.setHistoForActorWaitingTime(getHistoWaitTicketbox());
		}
		return ticketbox;
	}
	
	
	public MultiActor getMultiTicketbox() {
		if(multTicketbox == null){
			multTicketbox = new MultiActor();
			multTicketbox.setOriginal(getTicketbox());
			multTicketbox.setNumberOfClones(gui.getChooseTicketboxAmount().getInt());
			multTicketbox.setNameForProtocol("Create amount of ticketboxes");
		}
		return multTicketbox;
	}


	public DiscretHisto getDiscretHistoQueueToTicketbox() {
		if (discretHistoQueueToTicketbox == null) {
			discretHistoQueueToTicketbox = new DiscretHisto();
		}
		return discretHistoQueueToTicketbox;
	}
        
        public DiscretHisto getDiscretHistoQueueToStewardess() {
		if (discretHistoQueueToStewardess == null) {
			discretHistoQueueToStewardess = new DiscretHisto();
		}
		return discretHistoQueueToStewardess;
	}

	public Histo getHistoPassengerWaitInQueueToTicketbox() {
		if (histoPassengerWaitInQueueToTicketbox == null) {
			histoPassengerWaitInQueueToTicketbox = new Histo();
		}
		return histoPassengerWaitInQueueToTicketbox;
	}
        
        public Histo getHistoPassengerWaitInQueueToStewardess() {
		if (histoPassengerWaitInQueueToStewardess == null) {
			histoPassengerWaitInQueueToStewardess = new Histo();
		}
		return histoPassengerWaitInQueueToStewardess;
	}

	public Histo getHistoWaitTicketbox() {
		if (histoWaitTicketbox == null) {
			histoWaitTicketbox = new Histo();
		}
		return histoWaitTicketbox;
	}
        
        public Histo getHistoWaitStewardess() {
		if (histoWaitStewardess == null) {
			histoWaitStewardess = new Histo();
		}
		return histoWaitStewardess;
	}

	public void initForTest() {
		getQueueToTicketbox().setPainter(gui.getDiagramTicketboxQueue().getPainter());
		if(gui.getJCheckBox().isSelected()){
			dispatcher.setProtocolFileName("Console");
		}

	}

	public Histo getHistoPassengerServiceTime() {
		if( histoPassengerServiceTime== null)
			histoPassengerServiceTime = new Histo();
		return histoPassengerServiceTime;
	}

	// Реалізація інтерфейсу IStatisticsable
	@Override
	public void initForStatistics() {

	}

	@Override
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("Гістограма для довжини черги", getDiscretHistoQueueToTicketbox());
		map.put("Гістограма для часу чекання у черзі",
				getHistoPassengerWaitInQueueToTicketbox());
		map.put("Гістограма для часу простою обслуговуючого пристрою",
				getTicketbox().getWaitingTimeHisto());
		map.put("Гістограма для часу обслуговування",
				getHistoPassengerServiceTime());

		return map;
	}
}
