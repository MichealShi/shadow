package com.independent.shadow.prefer;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.independent.shadow.util.XLog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class PreferenceUtil {

    private SharedPreferences sp = null;

    public void init(Context c, String name) {
        sp = c.getSharedPreferences(name, 0);
    }

    public void putBool(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public boolean getBool(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public void putInt(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public void putLong(String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    public long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    public void putString(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public void setObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            String objString = new String(Base64.encode(baos
                    .toByteArray(), Base64.DEFAULT));
            sp.edit().putString(key, objString);
        } catch (IOException e) {
            XLog.e(e);
        } finally {
            closeOutStream(baos);
            closeOutStream(oos);
        }
    }

    public void closeInStream(InputStream inputStream){
        if (inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                XLog.e(e);
            }
            inputStream = null;
        }
    }

    public void closeOutStream(OutputStream outputStream){
        if (outputStream!=null){
            try {
                outputStream.close();
            } catch (IOException e) {
                XLog.e(e);
            }
            outputStream = null;
        }
    }
    public Object getObject(String key) {
        Object object = null;
        String productBase64 = sp.getString(key, "");
        //读取字节
        byte[] base64 = Base64.decode(productBase64.getBytes(), Base64.DEFAULT);

        //封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        ObjectInputStream bis = null;
        try {
            //再次封装
            bis = new ObjectInputStream(bais);
            try {
                //读取对象
                object = (Object) bis.readObject();
            } catch (ClassNotFoundException e) {
                XLog.e(e);
            }
        } catch (IOException e) {
            XLog.e(e);
        }finally {
            closeInStream(bais);
            closeInStream(bis);
        }
        return object;
    }

}
