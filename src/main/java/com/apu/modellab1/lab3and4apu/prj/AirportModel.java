package com.apu.modellab1.lab3and4apu.prj;

import java.util.HashMap;
import java.util.Map;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import rnd.Randomable;
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
        private Stewardess stewardess;
        private Airplane airplane;
	//Бригада обслуговуючих пристроїв
	private MultiActor multTicketbox;
        
        //queue max size
        private Integer queueMaxSize;
        
        //passengers per fly
        private Integer passengersPerFly;
        
        //airplane fly time
        private Randomable airplaneFlyTime;
	
	/////////Черги\\\\\\\\\
	// Черга транзакцій
	private QueueForTransactions<Passenger> queueToTicketbox;        
        private QueueForTransactions<Passenger> queueToStewardess;
        private QueueForTransactions<Passenger> queueToAirplane; 
        private QueueForTransactions<Passenger> queueToRailway; 
	
	/////////Гістограми\\\\\\\\\\\\
	// Гістограма для довжини черги
	private DiscretHisto discretHistoQueueToTicketbox;
        private DiscretHisto discretHistoQueueToStewardess;
        private DiscretHisto discretHistoQueueToAirplane;
        private DiscretHisto discretHistoQueueToRailway;
	// Гістограма для часу перебування у черзі
	private Histo histoPassengerWaitInQueueToTicketbox;
        private Histo histoPassengerWaitInQueueToStewardess;
	// Гістограма для часу обслуговування
	private Histo histoPassengerServiceTime;
	// Гістограма для часу чекання Device
	private Histo histoWaitTicketbox;
        private Histo histoWaitStewardess;
        private Histo histoWaitAirplane;

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
                dispatcher.addStartingActor(getStewardess());
                dispatcher.addStartingActor(getAirplane());
	}

	public PassengersGenerator getGenerator() {
		if (generator == null) {
			generator = new PassengersGenerator("PassengersGenerator", gui, this);
		}
		return generator;
	}
        
        public int getQueueMaxSize() {
            if(queueMaxSize == null)
                queueMaxSize = gui.getChooseQueueMaxSize().getInt();
            return queueMaxSize;
        }
        
        public int getPassengersPerFly() {
            if(passengersPerFly == null)
                passengersPerFly = gui.getChoosePassengersPerFly().getInt();
            return passengersPerFly;
        }
        
        public Randomable getAirplaneFlyTime() {
            if(airplaneFlyTime == null)
                airplaneFlyTime = gui.getChooseRandomFlyInterval();
            return airplaneFlyTime;
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
        
        public QueueForTransactions<Passenger> getQueueToAirplane() {
		if (queueToAirplane == null) {
			queueToAirplane = new QueueForTransactions<>("QueueToAirplane", dispatcher,
					getDiscretHistoQueueToAirplane());
		}
		return queueToAirplane;
	}
        
        public QueueForTransactions<Passenger> getQueueToRailway() {
		if (queueToRailway == null) {
			queueToRailway = new QueueForTransactions<>("QueueToRailway", dispatcher,
					getDiscretHistoQueueToRailway());
		}
		return queueToRailway;
	}

	public Ticketbox getTicketbox() {
		if (ticketbox == null) {
			ticketbox = new Ticketbox("Ticketbox", gui, this);
			ticketbox.setHistoForActorWaitingTime(getHistoWaitTicketbox());
		}
		return ticketbox;
	}
        
        public Stewardess getStewardess() {
		if (stewardess == null) {
			stewardess = new Stewardess("Stewardess", gui, this);
			stewardess.setHistoForActorWaitingTime(getHistoWaitStewardess());
		}
		return stewardess;
	}
	
        public Airplane getAirplane() {
		if (airplane == null) {
			airplane = new Airplane("Airplane", gui, this);
			airplane.setHistoForActorWaitingTime(getHistoWaitAirplane());
		}
		return airplane;
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
        
        public DiscretHisto getDiscretHistoQueueToAirplane() {
		if (discretHistoQueueToAirplane == null) {
			discretHistoQueueToAirplane = new DiscretHisto();
		}
		return discretHistoQueueToAirplane;
	}
        
        public DiscretHisto getDiscretHistoQueueToRailway() {
		if (discretHistoQueueToRailway == null) {
			discretHistoQueueToRailway = new DiscretHisto();
		}
		return discretHistoQueueToRailway;
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
        
        public Histo getHistoWaitAirplane() {
		if (histoWaitAirplane == null) {
			histoWaitAirplane = new Histo();
		}
		return histoWaitAirplane;
	}

	public void initForTest() {
		getQueueToTicketbox().setPainter(gui.getDiagramTicketboxQueue().getPainter());
                getQueueToStewardess().setPainter(gui.getDiagramStewardessQueue().getPainter());
                getQueueToAirplane().setPainter(gui.getDiagramAirplaneQueue().getPainter());
                getQueueToRailway().setPainter(gui.getDiagramRailwayQueue().getPainter());
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
		map.put("Ticketboxes queue length", 
                        getDiscretHistoQueueToTicketbox());
                map.put("Stewardess queue length", 
                        getDiscretHistoQueueToStewardess());
                map.put("Airplane queue length", 
                        getDiscretHistoQueueToAirplane());
                map.put("Railway queue length", 
                        getDiscretHistoQueueToRailway());
		map.put("Гістограма для часу чекання у черзі to Ticketboxes",
				getHistoPassengerWaitInQueueToTicketbox());
                map.put("Гістограма для часу чекання у черзі to Stewardess",
				getHistoPassengerWaitInQueueToStewardess());
		map.put("Гістограма для часу простою Stewardess",
				getStewardess().getWaitingTimeHisto());
		map.put("Гістограма для часу обслуговування",
				getHistoPassengerServiceTime());

		return map;
	}
}
