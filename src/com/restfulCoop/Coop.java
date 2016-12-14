package com.restfulCoop;

public class Coop {
	
	private String name;
	private int temp;
	private boolean heat;
	
	Coop(){
		
	}
	Coop(String name, int temp, boolean heat){
		this.name = name;
		this.temp = temp;
		this.heat = heat;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setTemp(int temp){
		this.temp = temp;
	}
	public String getTemp(){
		return String.valueOf(this.temp);
	}
	public void setHeat(boolean heat){
		this.heat = heat;
	}
	public boolean isHeatOn(){
		return this.heat;
	}
}
