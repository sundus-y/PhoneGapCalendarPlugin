package com.sundusy.plugin.calendar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;

public class Calendar extends CordovaPlugin {
    public static final String ACTION_ADD_EVENT = "addEvent";
    public static final String ACTION_VIEW_EVENT = "viewEvent";
    public static final String ACTION_EDIT_EVENT = "editEvent";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_ADD_EVENT.equals(action)) {
                JSONObject arg_object = args.getJSONObject(0);
                Intent calIntent = new Intent(Intent.ACTION_EDIT)
                    .setType("vnd.android.cursor.item/event")
                    .putExtra("beginTime", arg_object.getLong("startTimeMillis"))
                    .putExtra("endTime", arg_object.getLong("endTimeMillis"))
                    .putExtra("title", arg_object.getString("title"))
                    .putExtra("description", arg_object.getString("description"))
                    .putExtra("eventLocation", arg_object.getString("eventLocation"));
             
               this.cordova.getActivity().startActivity(calIntent);
               callbackContext.success();
               return true;
            }
            else if(ACTION_VIEW_EVENT.equals(action)) {
                callbackContext.error("Working on view . . .");
                return false;
            }
            else if(ACTION_VIEW_EVENT.equals(action)) {
                callbackContext.error("Working on edit . . .");
                return false;
            }
            callbackContext.error("Unknown Action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        } 
    }
}