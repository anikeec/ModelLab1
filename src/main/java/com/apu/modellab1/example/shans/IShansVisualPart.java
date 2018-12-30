package com.apu.modellab1.example.shans;

import rnd.Randomable;

public interface IShansVisualPart {

	Randomable getBusRandom();

	Randomable getPassengerRandom();

	Randomable getSlotRandom();

	double getPrizeSize();

}
