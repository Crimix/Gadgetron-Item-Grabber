package com.black_dog20.itemgrabber.capability;

public interface IMagnetHandler extends IBaseCapability<IMagnetHandler> {

	void setHasMagnetOn(boolean hasMagnetOn);
	boolean getHasMagnetOn();
	void setHasBelt(boolean hasBelt);
	boolean getHasBelt();
	void setTier(int tier);
	int getTier();
	void setSneakDeactivate(boolean sneakDeactivate);
	boolean getSneakDeactivate();
	void setTempOff(boolean tempOff);
	boolean getTempOff();
}
