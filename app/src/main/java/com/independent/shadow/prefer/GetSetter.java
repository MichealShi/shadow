package com.independent.shadow.prefer;


public class GetSetter<T> extends Getter<T>{

	public GetSetter(T defValue) {
		super(defValue);
	}
	
	protected void onChange(T newValue) {
		
	}

	public void set(T value) {
		inited = true;
		if(!equal(this.value, value)) {
			this.value = value;
			onChange(this.value);
		}
	}

	public static boolean equal(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null) {
			return false;
		}
		return o1.equals(o2);
	}
}