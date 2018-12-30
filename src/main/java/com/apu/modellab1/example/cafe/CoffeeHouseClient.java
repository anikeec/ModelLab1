package com.apu.modellab1.example.cafe;

import process.QueueForTransactions;
import rnd.Randomable;

public class CoffeeHouseClient extends process.Actor {
	/**
	 * —сылка на очередь, котора¤ моделирует кафе
	 */
	private QueueForTransactions cafe;

	/**
	 * —сылка на генератор случайных чисел, который будет задавать врем¤
	 * пербывани¤ клиента в кафе.
	 */
	private Randomable random;

	/**
	 * CoffeeHouseClient constructor.
	 */
	public CoffeeHouseClient() {
		super();
	}

	/**
	 * ћетод служит дл¤ передачи ссылки на очередь, котора¤ моделирует кафе.
	 * Creation date: (02.03.2006 18:33:19)
	 * 
	 * @param newQueue
	 *            qusystem.QueueForTransactions
	 */
	public void setCafe(process.QueueForTransactions newQueue) {
		cafe = newQueue;
	}

	/**
	 * ћетод служит дл¤ передачи ссылки на генератор случайных чисел, который
	 * будет задавать врем¤ пербывани¤ клиента в кафе. Creation date:
	 * (02.03.2006 18:33:19)
	 * 
	 */
	public void setRandom(Randomable random) {
		this.random = random;
	}

	/**
	 * ћетод обеспечивает доступ к очереди, котора¤ моделирует кафе. Creation
	 * date: (02.03.2006 18:33:19)
	 * 
	 * @return qusystem.QueueForTransactions
	 */
	public process.QueueForTransactions getCafe() {
		if (cafe == null)
			System.out
					.print(" лиенту кафе не передана ссылка на очередь, моделирующую кафе.");
		return cafe;
	}

	/**
	 * ћетод обеспечивает доступ к генератору случайных чисел, который будет
	 * задавать врем¤ пербывани¤ клиента в кафе. Creation date: (02.03.2006
	 * 18:33:19)
	 * 
	 * @return rnd.Randomable
	 */

	private Randomable getRandom() {
		if (random == null)
			System.out
					.println(" лиенту кафе не передана ссылка на генератор случайных чисел, который будет задавать врем¤ пербывани¤ клиента в кафе.");

		return random;
	}

	/**
	 * ѕравила действи¤ клиента кафе. Creation date: (02.03.2006 18:33:19)
	 * 
	 */
	public void rule() {
		//  лиент заходит в кафе.
		// ‘актически добавл¤ет себ¤ в очередь-кафе
		getCafe().add(this);
		//  лиент задерживаетс¤ в кафе на врем¤,
		// полученное от генератора случайных чисел
		holdForTime(getRandom().next());
		//  лиент покидает кафе.
		// ‘актически удал¤ет себ¤ из очереди-кафе
		getCafe().remove(this);
	}

}
