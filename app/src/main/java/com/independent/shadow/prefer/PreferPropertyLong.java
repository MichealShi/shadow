package com.independent.shadow.prefer;



public class PreferPropertyLong extends GetSetter<Long> {
	private String key;
	private PreferenceUtil sp;

	public PreferPropertyLong(PreferenceUtil prefer, String key, long defValue) {
		super(defValue);
		this.key = key;
		sp = prefer;
	}

	@Override
	protected Long onInit(Long defValue) {
		return sp.getLong(key, defValue);
	}

	@Override
	protected void onChange(Long newValue) {
		sp.putLong(key, newValue);
	}
}