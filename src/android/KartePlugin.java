package io.karte.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.karte.android.KarteApp;
import io.karte.android.core.library.Library;
import io.karte.android.core.usersync.UserSync;
import io.karte.android.inappmessaging.InAppMessaging;
import io.karte.android.notifications.Notifications;
import io.karte.android.tracking.Tracker;
import io.karte.android.variables.FetchCompletion;
import io.karte.android.variables.Variable;

public final class KartePlugin extends CordovaPlugin implements Library {
    private KarteVariables variables = new KarteVariables();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        try {
            if ("visitorId".equals(action)) {
                return this.visitorId(callbackContext);
            } else if ("isOptOut".equals(action)) {
                return this.isOptOut(callbackContext);
            } else if ("optIn".equals(action)) {
                return this.optIn(callbackContext);
            } else if ("optOut".equals(action)) {
                return this.optOut(callbackContext);
            } else if ("renewVisitorId".equals(action)) {
                return this.renewVisitorId(callbackContext);
            } else if ("appendingQueryParameter".equals(action)) {
                return this.appendingQueryParameter(callbackContext, args.getString(0));
            } else if ("track".equals(action)) {
                return this.track(callbackContext, args.getString(0), args.optJSONObject(1));
            } else if ("identify".equals(action)) {
                return this.identify(callbackContext, args.optJSONObject(0));
            } else if ("view".equals(action)) {
                return this.view(callbackContext, args.getString(0), args.optString(1), args.optJSONObject(2));
            } else if ("isPresenting".equals(action)) {
                return this.isPresenting(callbackContext);
            } else if ("dismiss".equals(action)) {
                return this.dismiss(callbackContext);
            } else if ("suppress".equals(action)) {
                return this.suppress(callbackContext);
            } else if ("unsuppress".equals(action)) {
                return this.unsuppress(callbackContext);
            } else if ("registerFCMToken".equals(action)) {
                return this.registerFCMToken(callbackContext, args.getString(0));
            } else if ("fetch".equals(action)) {
                return this.fetch(callbackContext);
            } else if ("variable".equals(action)) {
                return  this.variable(callbackContext, args.getString(0));
            } else if ("trackOpen".equals(action)) {
                return this.trackOpen(callbackContext, args.getJSONArray(0), args.optJSONObject(1));
            } else if ("trackClick".equals(action)) {
                return this.trackClick(callbackContext, args.getJSONArray(0), args.optJSONObject(1));
            } else if ("string".equals(action)) {
                return this.getString(callbackContext, args.getString(0), args.optString(1));
            } else if ("integer".equals(action)) {
                return this.getInteger(callbackContext, args.getString(0), args.getLong(1));
            } else if ("double".equals(action)) {
                return this.getDouble(callbackContext, args.getString(0), args.getDouble(1));
            } else if ("bool".equals(action)) {
                return this.getBoolean(callbackContext, args.getString(0), args.getBoolean(1));
            } else if ("array".equals(action)) {
                return this.getArray(callbackContext, args.getString(0), args.optJSONArray(1));
            } else if ("object".equals(action)) {
                return this.getObject(callbackContext, args.getString(0), args.optJSONObject(1));
            }
        } catch (Exception e) {
            handleException(e, callbackContext);
        }
        return false;
    }

    private boolean visitorId(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callbackContext.success(KarteApp.getVisitorId());
            }
        });
        return true;
    }

    private boolean isOptOut(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PluginResult pluginResult = new PluginResult(Status.OK, KarteApp.isOptOut());
                callbackContext.sendPluginResult(pluginResult);
            }
        });
        return true;
    }

    private boolean optIn(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                KarteApp.optIn();
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean optOut(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                KarteApp.optOut();
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean renewVisitorId(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                KarteApp.renewVisitorId();
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean appendingQueryParameter(final CallbackContext callbackContext, final String url) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callbackContext.success(UserSync.appendUserSyncQueryParameter(url));
            }
        });
        return true;
    }

    private boolean track(final CallbackContext callbackContext, final String name, final JSONObject values) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Tracker.track(name, values);
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean identify(final CallbackContext callbackContext, final JSONObject values) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Tracker.identify(values);
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean view(final CallbackContext callbackContext, final String viewName, final String title, final JSONObject values) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Tracker.view(viewName, title, values);
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean isPresenting(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PluginResult pluginResult = new PluginResult(Status.OK, InAppMessaging.isPresenting());
                callbackContext.sendPluginResult(pluginResult);
            }
        });
        return true;
    }

    private boolean dismiss(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InAppMessaging.dismiss();
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean suppress(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InAppMessaging.suppress();
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean unsuppress(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InAppMessaging.unsuppress();
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean registerFCMToken(final CallbackContext callbackContext, final String fcmToken) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Notifications.registerFCMToken(fcmToken);
                callbackContext.success();
            }
        });
        return true;
    }

    private boolean fetch(final CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                KartePlugin.this.variables.fetch(new FetchCompletion() {
                    @Override
                    public void onComplete(boolean success) {
                        if (success) {
                            callbackContext.success();
                        } else {
                            callbackContext.error("Failed to fetch.");
                        }
                    }
                });
            }
        });
        return true;
    }

    private boolean variable(final CallbackContext callbackContext, final String key) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Variable variable = variables.variable(key);
                callbackContext.success(variable.getName());
            }
        });
        return true;
    }

    private boolean trackOpen(final CallbackContext callbackContext, final JSONArray keys, final JSONObject values) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    KartePlugin.this.variables.trackOpen(keys, values);
                    callbackContext.success();
                } catch (JSONException e) {
                    handleException(e, callbackContext);
                }
            }
        });
        return true;
    }

    private boolean trackClick(final CallbackContext callbackContext, final JSONArray keys, final JSONObject values) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    KartePlugin.this.variables.trackClick(keys, values);
                    callbackContext.success();
                } catch (JSONException e) {
                    handleException(e, callbackContext);
                }
            }
        });
        return true;
    }

    private boolean getString(final CallbackContext callbackContext, final String key, final String defaultValue) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String value = KartePlugin.this.variables.getString(key, defaultValue);
                callbackContext.success(value);
            }
        });
        return true;
    }

    private boolean getInteger(final CallbackContext callbackContext, final String key, final Long defaultValue) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int value = (int)(long)KartePlugin.this.variables.getLong(key, defaultValue);
                callbackContext.success(value);
            }
        });
        return true;
    }

    private boolean getDouble(final CallbackContext callbackContext, final String key, final Double defaultValue) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                float value  =(float)(double)KartePlugin.this.variables.getDouble(key, defaultValue);
                PluginResult pluginResult = new PluginResult(Status.OK, value);
                callbackContext.sendPluginResult(pluginResult);
            }
        });
        return true;
    }

    private boolean getBoolean(final CallbackContext callbackContext, final String key, final Boolean defaultValue) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Boolean value = KartePlugin.this.variables.getBoolean(key, defaultValue);
                PluginResult pluginResult = new PluginResult(Status.OK, value);
                callbackContext.sendPluginResult(pluginResult);
            }
        });
        return true;
    }

    private boolean getArray(final CallbackContext callbackContext, final String key, final JSONArray defaultValue) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray value = KartePlugin.this.variables.getJSONArray(key, defaultValue);
                callbackContext.success(value);
            }
        });
        return true;
    }

    private boolean getObject(final CallbackContext callbackContext, final String key, final JSONObject defaultValue) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject value = KartePlugin.this.variables.getJSONObject(key, defaultValue);
                callbackContext.success(value);
            }
        });
        return true;
    }

    private static void handleException(Exception e, CallbackContext callbackContext) {
        String message = e.toString();
        callbackContext.error(message);
    }

    // implements Library
    @Override
    public boolean isPublic() {
        return true;
    }

    @NotNull
    @Override
    public String getName() {
        return "cordova";
    }

    @NotNull
    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public void configure(@NotNull KarteApp karteApp) {
    }

    @Override
    public void unconfigure(@NotNull KarteApp karteApp) {
    }
}
