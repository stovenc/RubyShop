package me.stovenc.rubyshop.listeners;

public class Entry {
	
	Integer rub = 0;
	Boolean turn = true;
	
	public Entry(Integer amount, Boolean cycle) {
		rub = amount; //amount of rubies in sendmessage
		turn = cycle; //no spam
	}
	
	public Integer getAmount() {
		return rub;
	}
	
	public Boolean getCycle() {
		return turn;
	}
	
	public void incrementRubyByOne() {
		rub += 1;
	}
	
	public void setTurnToTrue() {
		turn = true;
	}
	
	public void setTurnToFalse() {
		turn = false;
	}
	
	public void setRubyToZero() {
		rub = 0;
	}
}
