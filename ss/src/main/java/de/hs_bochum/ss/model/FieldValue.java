package de.hs_bochum.ss.model;

import de.hs_bochum.ss.exception.IsLockedException;

public class FieldValue {

	private byte value;
	
	private final boolean lock;
	
	public FieldValue(boolean lock) {
		if(lock)
			this.value = -1;
		this.lock = lock;
	}
	
	public FieldValue(boolean lock, byte value){
		this.lock = lock;
		this.value = value;
	}
	
	public FieldValue(byte value){
		this.lock = true;
		this.value = value;
	}
	
	public FieldValue(){
		this.lock = false;
		this.value = 0;
	}
	
	public void setValue(byte value) throws IsLockedException{
		if(!this.lock || this.value == -1){
			this.value = value;
		}
		else{
			throw new IsLockedException("This field is locked");
		}
	}
	
	public byte getValue(){
		return this.value;
	}
	
}
