package com.apu.modellab1.toLab8_testGradient;

/**
 * This is the event multicaster class to support the
 * toLab8_testGradient.StepTimerViewListenerEventMulticaster interface.
 */
public class StepTimerViewListenerEventMulticaster extends
		java.awt.AWTEventMulticaster implements
		com.apu.modellab1.toLab8_testGradient.StepTimerViewListener {
	/**
	 * Constructor to support multicast events.
	 * 
	 * @param a
	 *            java.util.EventListener
	 * @param b
	 *            java.util.EventListener
	 */
	protected StepTimerViewListenerEventMulticaster(java.util.EventListener a,
			java.util.EventListener b) {
		super(a, b);
	}

	/**
	 * Add new listener to support multicast events.
	 * 
	 * @return toLab8_testGradient.StepTimerViewListener
	 * @param a
	 *            toLab8_testGradient.StepTimerViewListener
	 * @param b
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	public static com.apu.modellab1.toLab8_testGradient.StepTimerViewListener add(
			com.apu.modellab1.toLab8_testGradient.StepTimerViewListener a,
			com.apu.modellab1.toLab8_testGradient.StepTimerViewListener b) {
		return (com.apu.modellab1.toLab8_testGradient.StepTimerViewListener) addInternal(a, b);
	}

	/**
	 * Add new listener to support multicast events.
	 * 
	 * @return java.util.EventListener
	 * @param a
	 *            java.util.EventListener
	 * @param b
	 *            java.util.EventListener
	 */
	protected static java.util.EventListener addInternal(
			java.util.EventListener a, java.util.EventListener b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		return new StepTimerViewListenerEventMulticaster(a, b);
	}

	/**
	 * 
	 * @param newEvent
	 *            java.util.EventObject
	 */
	public void FinishTime_caretUpdate(java.util.EventObject newEvent) {
		((com.apu.modellab1.toLab8_testGradient.StepTimerViewListener) a)
				.FinishTime_caretUpdate(newEvent);
		((com.apu.modellab1.toLab8_testGradient.StepTimerViewListener) b)
				.FinishTime_caretUpdate(newEvent);
	}

	/**
	 * 
	 * @return java.util.EventListener
	 * @param oldl
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	protected java.util.EventListener remove(
			com.apu.modellab1.toLab8_testGradient.StepTimerViewListener oldl) {
		if (oldl == a)
			return b;
		if (oldl == b)
			return a;
		java.util.EventListener a2 = removeInternal(a, oldl);
		java.util.EventListener b2 = removeInternal(b, oldl);
		if (a2 == a && b2 == b)
			return this;
		return addInternal(a2, b2);
	}

	/**
	 * Remove listener to support multicast events.
	 * 
	 * @return toLab8_testGradient.StepTimerViewListener
	 * @param l
	 *            toLab8_testGradient.StepTimerViewListener
	 * @param oldl
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	public static com.apu.modellab1.toLab8_testGradient.StepTimerViewListener remove(
			com.apu.modellab1.toLab8_testGradient.StepTimerViewListener l,
			com.apu.modellab1.toLab8_testGradient.StepTimerViewListener oldl) {
		if (l == oldl || l == null)
			return null;
		if (l instanceof StepTimerViewListenerEventMulticaster)
			return (com.apu.modellab1.toLab8_testGradient.StepTimerViewListener) ((com.apu.modellab1.toLab8_testGradient.StepTimerViewListenerEventMulticaster) l)
					.remove(oldl);
		return l;
	}
}
