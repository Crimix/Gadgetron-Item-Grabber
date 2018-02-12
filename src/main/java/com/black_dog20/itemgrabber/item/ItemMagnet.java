package com.black_dog20.itemgrabber.item;

public class ItemMagnet extends ItemBase{

	private int range;
	private double speed;
	
	public ItemMagnet(String name, int range,double speed){
		super(name);
		this.range = range;
		this.speed = speed;
	}
	
	public int getRange(){
		return range;
	}
	
	
	public double getSpeed(){
		return speed;
	}
	
}
