package com.apu.modellab1.example.competition;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import rnd.Randomable;

/**
 * Класс определяет свойства и поведение члена команды. Задача объекта этого
 * класса выполнить свою часть очередного задания и ждать, пока все игроки
 * команды закончат выполнение своих частей этого задания и команда выполнит
 * сборку и тестирование задания После этого член команды приступает к
 * выполнению своей части нового задания и так до тех пор, пока не будут сделаны
 * все задания. Всю необходимую информацию объект получает от команды(team), в
 * которой он состоит, через интерфейс ITeam, и интерфейса пользователя через
 * интерфейс IVisualPart.
 * 
 * @author P.Byvoino 24.01.2008 16:36:20
 * 
 */

public class TeamMember extends Actor {

	private int n; // Число заданий, которое сделал объект

	private boolean completeStage; // Признак завершения этапа

	private Team team; // Команда, в которой состоит объект

	private VisualPart ui; // Ссылка на интерфейс пользователя

	public TeamMember() {
		super();
	}

	/**
	 * Правила действия члена команды. Creation date: (08.05.2005 21:19:53)
	 * 
	 * @see process.Actor#rule()
	 */
	public void rule() {
		n = 0;
		int nTask = ui.nTask(); // Общее число заданий
		Randomable random = ui.random(); // Генератор случайного времени
		BooleanSupplier allReady = () -> completeStage; // выполнения этапа
		while (n < nTask) {
			completeStage = false;
			double taskTime = random.next();
			holdForTime(taskTime);
			n = n + 1;
			ui.drawGamerTaskReady(this, taskTime);
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " виконав " + n + " з "
							+ nTask);
			try {
				waitForCondition(allReady,
						"поки не буде завершено об'єднання та тестування завдання");
			} catch (DispatcherFinishException e) {
				return;
			}
		}
	}

	// ///////////////////////////////////////////////////////////////////
	// Методы доступа к полям объектов

	public int getN() {
		return n;
	}

	public String getNameForProtocol() {

		return "Член команди №" + String.valueOf(team.getTeamNumber())
				+ String.valueOf(getNumber());
	}

	/**
	 * Returns a String that represents the value of this object.
	 * 
	 * @return String
	 */

	public Team getTeam() {
		return team;
	}

	public int getNumber() {
		return team.getMemberList().indexOf(this) + 1;
	}

	// Сылка на команду, которой принадлежит объект
	public void setTeam(Team team) {
		this.team = team;
	}

	// Сcылка на интерфейс пользователя
	public void setUI(VisualPart ui) {
		this.ui = ui;
	}

	public void setCompleteStage(boolean b) {
		completeStage = b;

	}

	public void setN(int n) {
		this.n = n;
	}

}
