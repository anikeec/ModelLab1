package com.apu.modellab1.buldo2018;

import java.util.HashMap;
import java.util.Map;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import process.Store;
import stat.DiscretHisto;
import stat.Histo;
import stat.IHisto;
import widgets.experiments.IExperimentable;
import widgets.stat.IStatisticsable;
import widgets.trans.ITransMonitoring;
import widgets.trans.ITransProcesable;

/**
 * Клас моделі Модель складається з БУЛЬДОЗЕРА, НАВАНТАЖУВАЧА та кількох
 * САМОСКИДІВ. Бульдозер порціями згрібає ґрунт у КУПУ. На одну порцію ґрунту
 * він витрачає випадковий час. Навантажувач починає працювати коли у купі є
 * ґрунт і є самоскид, що чекає завантаження. Він порціями насипає ґрунт з купи
 * у кузов самоскиду. Місткість кузову самоскида декілька порцій ґрунту.
 * Самоскиди виїжджають із автопарку и стають у ЧЕРГУ до навантажувача. Після
 * завантаження самоскиди відвозять ґрунт замовнику, розвантажуються, і
 * повертаються до навантажувача.
 * 
 * @author Павел
 *
 */
public class BuldModel implements IExperimentable, ITransProcesable,
		IStatisticsable { 
	/**
	 * Посилання на диспетчера
	 * 
	 */
	private Dispatcher dispatcher;

	/**
	 * Посилання на візуальну частину
	 * 
	 */
	private BuldGUI gui;

	/**
	 * Бульдозер
	 * 
	 */
	private Buldo buldo;

	/**
	 * Навантажувач
	 * 
	 */
	private Loader loader;

	/**
	 * Зразок самоскида
	 * 
	 */
	private Lorry lorry;

	/**
	 * Бригада самоскидів
	 * 
	 */
	private MultiActor multiLorry;

	/**
	 * Купа грунту
	 * 
	 */
	private Store heap;

	/**
	 * Черга самоскидів до навантажувача
	 * 
	 */
	private QueueForTransactions<Lorry> queueToLoader;

	/**
	 * Уявна черга для самоскидів у дорозі
	 * 
	 */
	private QueueForTransactions<Lorry> queueLorryOnRoad;

	/**
	 * Гістограма для довжини черги до навантажувача
	 * 
	 */
	private DiscretHisto histoForQueueToLoader;

	/**
	 * Гістограма для часу простою бульдозера
	 * 
	 */
	private Histo histoBuldo;

	/**
	 * Гістограма для часу простою навантажувача
	 * 
	 */
	private Histo histoLoader;

	/**
	 * Гістограма для часу простою самоскида
	 * 
	 */
	private Histo histoLorry;

	/**
	 * Гістограма для розмірів купи
	 * 
	 */
	private Histo histoHeap;

	// ////////////////////////////////////////
	// Єдиний спосіб створити модель, це викликати цей конструктор
	// Він гарантовано передає посилання на диспетчера та GUI
	// ////////////////////////////////////////

	public BuldModel(Dispatcher d, BuldGUI g) {
		if (d == null || g == null) {
			System.out.println("Не визначено диспетчера або GUI для RgrModel");
			System.out.println("Подальша робота неможлива");
			System.exit(0);
		}
		dispatcher = d;
		gui = g;
		// Передаємо акторів до стартового списку диспетчера
		componentsToStartList();
	}

	/**
	 * Передача акторів диспетчеру
	 */
	private void componentsToStartList() {
		// Передаємо акторів диспетчеру
		dispatcher.addStartingActor(getBuldo());
		dispatcher.addStartingActor(getLoader());
		dispatcher.addStartingActor(getMultiLorry());
	}

	// ////////////////////////////////////////
	// Методи відкладеного створення акторів моделі,
	// ////////////////////////////////////////

	/**
	 * Meтод створення бульдозера
	 */
	public Buldo getBuldo() {
		if (buldo == null) {
			buldo = new Buldo("Бульдозер", gui, this);
		}
		return buldo;
	}

	/**
	 * Meтод створення навантажувача
	 */
	public Loader getLoader() {
		if (loader == null)
			loader = new Loader("Навантажувач", gui, this);
		return loader;
	}

	/**
	 * Meтод створення зразка самоскида
	 */
	public Lorry getLorry() {
		if (lorry == null)
			lorry = new Lorry("Самоскид", gui, this);
		return lorry;
	}

	/**
	 * Meтод створення бригади самоскидів
	 */
	public MultiActor getMultiLorry() {
		if (multiLorry == null) {
			multiLorry = new MultiActor("MultiActor для бригади самоскидів",
					getLorry(), gui.getChooseDataNLorry().getInt());
		}
		return multiLorry;
	}

	// ////////////////////////////////////////
	// Методи відкладеного створення черг
	// ////////////////////////////////////////

	/**
	 * Meтод створення купи грунту
	 */
	public Store getHeap() {
		if (heap == null) {
			heap = new Store("Купа грунту", dispatcher, getHistoHeap());
		}
		return heap;
	}

	/**
	 * Meтод створення черги до навантажувачау
	 */
	public QueueForTransactions<Lorry> getQueueToLoader() {
		if (queueToLoader == null) {
			queueToLoader = new QueueForTransactions<Lorry>(
					"Черга до навантажувача", dispatcher, getHistoForQueueToLoader());
		}
		return queueToLoader;
	}

	/**
	 * Meтод створення уявної черги "самоскиди у дорозі"
	 */
	public QueueForTransactions<Lorry> getQueueLorryOnRoad() {
		if (queueLorryOnRoad == null) {
			queueLorryOnRoad = new QueueForTransactions<Lorry>(
					"Cамоскиди, що у дорозі", dispatcher);
		}
		return queueLorryOnRoad;
	}

	// //////////////////////////////////////////////////////
	// Методи відкладеного створення накопичувачів статистики
	// //////////////////////////////////////////////////////

	/**
	 * Метод доступу до гістограми для розміру купи
	 * 
	 */
	private Histo getHistoHeap() {
		if (histoHeap == null)
			histoHeap = new Histo();
		return histoHeap;
	}

	/**
	 * Метод доступу до гістограми для довжини черги до навантажувача
	 * 
	 */
	public DiscretHisto getHistoForQueueToLoader() {
		if (histoForQueueToLoader == null) {
			histoForQueueToLoader = new DiscretHisto();
		}
		return histoForQueueToLoader;
	}

	/**
	 * Метод доступу до гістограми для часу простою булдозера
	 * 
	 */
	public Histo getHistoBuldo() {
		if (histoBuldo == null) {
			histoBuldo = new Histo();
		}
		return histoBuldo;
	}

	/**
	 * Метод доступу до гістограми для часу простою навантажувача
	 * 
	 */
	public Histo getHistoLoader() {
		if (histoLoader == null) {
			histoLoader = new Histo();
		}
		return histoLoader;
	}

	/**
	 * Метод доступу до гістограми для часу простою самоскида
	 * 
	 */
	public Histo getHistoLorry() {
		if (histoLorry == null) {
			histoLorry = new Histo();
		}
		return histoLorry;
	}

	// ///////////////////////////////////////////////////////////
	// Методи ініціалізації моделі та реалізація інтерфейсів
	// //////////////////////////////////////////////////////////////

	/**
	 * Ініціалізація для режиму "Тест"
	 * 
	 */
	public void initForTest() {
		// Передаємо чергам painter-ів для динамічної індикації
		getHeap().setPainter(gui.getDiagramHeepSize().getPainter());
		getQueueToLoader().setPainter(gui.getDiagramQueueToLoader().getPainter());
		getQueueLorryOnRoad()
				.setPainter(gui.getDiagramLorryOnRoad().getPainter());
		if (gui.getJCheckBox().isSelected())
			dispatcher.setProtocolFileName("Console");
	}

	// /////////////////////////////////////
	// Реалізація інтерфейсу IStatisticsable
	// /////////////////////////////////////
	/**
	 * Ініціалізація для режиму "Статистика"
	 * 
	 */
	@Override
	public void initForStatistics() {

	}

	@Override
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("Гістограма для довжини черги до навантажувача",
				getHistoForQueueToLoader());
		map.put("Гістограма для розмірів купи", getHistoHeap());
		map.put("Гістограма для часу простою бульдозера", getHistoBuldo());
		map.put("Гістограма для часу простою самоскида", getHistoLorry());
		map.put("Гістограма для часу простою навантажувача", getHistoLoader());
		return map;
	}

	// /////////////////////////////////////
	// Реалізація інтерфейсу IExperimentable
	// /////////////////////////////////////
	public void initForExperiment(double factor) {
		multiLorry.setNumberOfClones((int) factor);
	}

	public Map<String, Double> getResultOfExperiment() {
		Map<String, Double> resultMap = new HashMap<>();
		resultMap.put("Час простою авто від їх кількості", getHistoLorry()
				.getAverage());
		resultMap.put("Розмір купи від кількості авто", getHistoHeap().average());
		resultMap.put("Час простою бульдозера від кількості авто",
				getHistoBuldo().getAverage());
		resultMap.put("Час простою навантажувача від кількості авто",
				getHistoLoader().getAverage());
		return resultMap;
	}

	// /////////////////////////////////////
	// Реалізація інтерфейсу ITransprocesable
	// /////////////////////////////////////

	public void initForTrans(double finishTime) {
		getBuldo().setFinishTime(finishTime);
		getLoader().setFinishTime(finishTime);
		getLorry().setFinishTime(finishTime);
		gui.getChooseDataFinishTime().setDouble(finishTime);

	}



	@Override
	public Map<String, ITransMonitoring> getMonitoringObjects() {
		Map<String, ITransMonitoring> transMap = new HashMap<>();
		transMap.put("Купа грунту", getHeap());
		transMap.put("Черга до навантажувача", getQueueToLoader());
		transMap.put("Самоскиди на шляхах", getQueueLorryOnRoad());
		return transMap;
	}
}
