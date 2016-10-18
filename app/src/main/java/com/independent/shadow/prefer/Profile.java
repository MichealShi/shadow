package com.independent.shadow.prefer;

import android.content.Context;

import java.io.InputStream;
import java.util.Properties;

public class Profile {
	private Properties pro = new Properties();

	public Profile(Context c, String fileInAssets) {
		try {
			InputStream is = c.getAssets().open(fileInAssets);
			pro.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getString(String key, String defValue) {
		String s = pro.getProperty(key);
		if (s != null) {
			return s.trim();
		}
		return defValue;
	}

	public int getInt(String key, int defValue) {
		String s = pro.getProperty(key);
		if (s != null) {
			try {
				return Integer.parseInt(s.trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return defValue;
	}
}
