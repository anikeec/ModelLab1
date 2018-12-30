package com.apu.modellab1.buldo2018;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.Store;
import rnd.Randomable;

/**
 * Клас для абстракції "Бульдозер". Абстракція «бульдозер» моделює робочу
 * машину, що додає до купи порції ґрунту, збільшуючи таким чином її розмір.
 * Одноразова порція ґрунту в реальній системі, звичайно, є випадковою
 * величиною. Але купа ґрунту накопичує ці порції, тобто інтегрує їх, і таким
 * чином зменшує вплив коливань розміру порції. Тому, для спрощення моделі
 * будемо вважати, що порція ґрунту має стале значення і дорівнює одиниці. Тобто
 * одиницею виміру кількості ґрунту у системі буде середнє значення порції
 * ґрунту, що бульдозер додає до купи за один раз. На видобування порції ґрунту
 * бульдозер витрачає деякий час, що є випадковою величиною. Найбільш вірогідно,
 * що ця величина підпорядковується закону Ерланга. Бульдозер припиняє свою
 * роботу, якщо розмір купи збільшується до деякого критичного розміру і
 * відновлює роботу тільки після того, як розмір купи стає удвічі меншим за
 * критичний розмір.
 * 
 *
 */

public class Buldo extends Actor {
	/**
	 * Посилання на купу грунту, до якої бульдозер додаає грунт
	 * 
	 */
	private Store heap;

	/**
	 * Критичний розмір купи земли, при якому бульдозер припиняє роботу
	 * 
	 */
	private double heapMaxSize;

	/**
	 * Тривлістьсть роботи бульдозера
	 * 
	 */
	private double finishTime;

	/**
	 * Генератор часу, що витрачає бульдозер на одну порцію грунту
	 * 
	 */
	private Randomable rnd;

	/**
	 * Умова, після виконання якої бульдозер відновлює роботу.
	 * У загальному випадку створювати у конструкторі не можна,
	 * бо умови можуть використовувати посилання на об'єкт,
	 * який ще не створено. 
	 * Доцільно ініціалізувати на початку методу rule.
	 * Якщо умов декілька і вони складні
	 * то доцільно створити метод initConditions()
	 */
	private BooleanSupplier heapHalfSize;

	/**
	 * Коструктор, у якому ініціалізуються поля об'єкту 
	 * через посилання на модель  та візуальну частину
	 * 
	 */
	public Buldo(String name, BuldGUI gui, BuldModel model) {
		setNameForProtocol(name);
		heap = model.getHeap();
		heapMaxSize = gui.getChooseDataHeapMaxSize().getDouble();
		finishTime = gui.getChooseDataFinishTime().getDouble();
		rnd = gui.getRndBuldo();
		setHistoForActorWaitingTime(model.getHistoBuldo());
	}

	/**
	 * Правила дії бульдозера. Бульдозер видобує порції ґрунту, витрачаючи на це
	 * деякий випадковий час, і додає ґрунт до купи. Якщо розмір купи
	 * збільшується до деякого критичного розміру, бульдозер зупиняється, і
	 * відновлює роботу після того, як розмір купи стає удвічі меншим за
	 * критичний розмір.
	 * @throws DispatcherFinishException 
	 */
	protected void rule() throws DispatcherFinishException {
		// Ініціалізація умов
		heapHalfSize = () -> heap.getSize() <= heapMaxSize / 2;
		// Цикл правил дії бульдозера
		while (getDispatcher().getCurrentTime() <= finishTime) {
			// Затримка на час формування порції грунту
			holdForTime(rnd.next());
			// Збільшення розміру купи на одну порцію
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " додає порцію грунту.");
			heap.add(1);
			// Зупинка,якщо купа досягла критичного розміру
			if (heap.getSize() >= heapMaxSize) {
					waitForCondition(heapHalfSize,
							"поки купа зменшіться удвічи");

			}
		}
	}


	/**
	 * Цей метод потрібен для корегування тривалості моделювання
	 * під час дослідження перехідних процесі
	 * @param finishTime - час моделювання, що сформує TransProcessManager
	 */
		public void setFinishTime(double finishTime) {
			this.finishTime = finishTime;

		}
}
