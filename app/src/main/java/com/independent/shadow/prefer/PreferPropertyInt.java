package com.independent.shadow.prefer;



public class PreferPropertyInt extends GetSetter<Integer> {
	private String key;
	private PreferenceUtil sp;

	public PreferPropertyInt(PreferenceUtil prefer, String key, int defValue) {
		super(defValue);
		this.key = key;
		sp = prefer;
	}

	@Override
	protected Integer onInit(Integer defValue) {
		return sp.getInt(key, defValue);
	}

	@Override
	protected void onChange(Integer newValue) {
		sp.putInt(key, newValue);
	}
}