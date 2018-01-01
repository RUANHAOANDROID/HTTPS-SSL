package com.ssl.mina;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = -940833727168119141L;

	private int value;

	public Message() {
		value =  (int)(100*Math.random());
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// it is a good practice to create toString() method on message classes.
		return getValue() + ":ADD(" + value + ')';
	}
}