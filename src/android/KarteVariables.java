package io.karte.cordova;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.karte.android.variables.FetchCompletion;
import io.karte.android.variables.Variable;
import io.karte.android.variables.Variables;

final class KarteVariables {
    private Map<String, Variable> variables = new HashMap<>();

    void fetch(final FetchCompletion completion) {
        Variables.fetch(new FetchCompletion() {
            @Override
            public void onComplete(boolean success) {
                if (success) {
                    KarteVariables.this.variables.clear();
                }
                completion.onComplete(success);
            }
        });
    }

    Variable variable(String key) {
        Variable variable = Variables.get(key);
        variables.put(key, variable);
        return variable;
    }

    void trackOpen(JSONArray keys, JSONObject values) throws JSONException {
        Variables.trackOpen(toVariables(keys), toValues(values));
    }

    void trackClick(JSONArray keys, JSONObject values) throws JSONException {
        Variables.trackClick(toVariables(keys), toValues(values));
    }

    String getString(String key, String defaultValue) {
        Variable var = variables.get(key);
        if (var != null) {
            return var.getString(defaultValue);
        } else {
            return defaultValue;
        }
    }

    Long getLong(String key, Long defaultValue) {
        Variable var = variables.get(key);
        if (var != null) {
            return var.getLong(defaultValue);
        } else {
            return defaultValue;
        }
    }

    Double getDouble(String key, Double defaultValue) {
        Variable var = variables.get(key);
        if (var != null) {
            return var.getDouble(defaultValue);
        } else {
            return defaultValue;
        }
    }

    Boolean getBoolean(String key, Boolean defaultValue) {
        Variable var = variables.get(key);
        if (var != null) {
            return var.getBoolean(defaultValue);
        } else {
            return defaultValue;
        }
    }

    JSONArray getJSONArray(String key, JSONArray defaultValue) {
        Variable var = variables.get(key);
        if (var != null) {
            return var.getJSONArray(defaultValue);
        } else {
            return defaultValue;
        }
    }

    JSONObject getJSONObject(String key, JSONObject defaultValue) {
        Variable var = variables.get(key);
        if (key != null) {
            return var.getJSONObject(defaultValue);
        } else {
            return defaultValue;
        }
    }

    private List<Variable> toVariables(JSONArray keys) throws JSONException {
        List<Variable> vars = new ArrayList<>();
        for (Object object : Converter.toList(keys)) {
            vars.add(variables.get(object));
        }
        return vars;
    }

    private Map<String, Object> toValues(JSONObject values) throws JSONException {
        Map<String, Object> map = null;
        if (values != null) {
            map = Converter.toMap(values);
        }
        return map;
    }
}