package io.karte.cordova;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class Converter {

    static Map<String, Object> toMap(@NonNull JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = object.get(key);
            if (value instanceof JSONObject) {
                map.put(key, toMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                map.put(key, toList((JSONArray) value));
            } else if (value instanceof String) {
                map.put(key, value);
            } else if (value instanceof Integer) {
                map.put(key, value);
            } else if (value instanceof Double) {
                map.put(key, value);
            } else if (value instanceof Boolean) {
                map.put(key, value);
            } else {
                map.put(key, value.toString());
            }
        }
        return map;
    }

    static List<Object> toList(@NonNull JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONObject) {
                list.add(toMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                list.add(toList((JSONArray) value));
            } else if (value instanceof String) {
                list.add(value);
            } else if (value instanceof Integer) {
                list.add(value);
            } else if (value instanceof Double) {
                list.add(value);
            } else if (value instanceof Boolean) {
                list.add(value);
            } else {
                list.add(value.toString());
            }
        }
        return list;
    }
}
