package com.independent.shadow.prefer;



public class PreferPropertyString  extends GetSetter<String> {
	private String key;
	private PreferenceUtil sp;

	public PreferPropertyString(PreferenceUtil prefer, String key, String defValue) {
		super(defValue);
		this.key = key;
		sp = prefer;
	}

	@Override
	protected String onInit(String defValue) {
		return sp.getString(key, defValue);
	}

	@Override
	protected void onChange(String newValue) {
		sp.putString(key, newValue);
	}
}