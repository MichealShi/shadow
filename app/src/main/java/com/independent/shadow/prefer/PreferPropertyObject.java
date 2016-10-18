package com.independent.shadow.prefer;



public class PreferPropertyObject extends GetSetter<Object> {
	private String key;
	private PreferenceUtil sp;

	public PreferPropertyObject(PreferenceUtil prefer, String key) {
		super(null);
		this.key = key;
		sp = prefer;
	}

	@Override
	protected Object onInit(Object defValue) {
		return sp.getObject(key);
	}

	@Override
	protected void onChange(Object newValue) {
		sp.setObject(key, newValue);
	}
}