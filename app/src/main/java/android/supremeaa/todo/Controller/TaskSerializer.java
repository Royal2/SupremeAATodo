package android.supremeaa.todo.Controller;

import android.content.Context;
import android.supremeaa.todo.Model.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/26/2015.
 */
public final class TaskSerializer {
    /**
     * This methood is used to parse JSONArrays from json files.
     * @param c this gets the applications context, which allows the function to access the
     *          assets folder.
     * @return JSONArray jsonArray parsed from taskdata.json file.
     */
    public static JSONArray parseJSONFromAsset(Context c) {
        JSONArray jsonArray = null;
        InputStream is = null;

        try {
            is = c.getAssets().open("JSON/taskdata.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();

        String string;

        try {
            while((string = reader.readLine()) !=null) stringBuilder.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonArray = new JSONArray(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
    /**
     * This method populates a List with Task Objects using data from a JSONArray
     * @param jsonArray gets JSONArray parsed from parseJSONFromAsset method.
     * @return taskList List of Task Objects
     * @see android.supremeaa.todo.Model.Task
     */
    public static List<Task> returnList(JSONArray jsonArray){
        List<Task> taskList = new ArrayList<Task>();

        try {
            for(int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String date = jsonObject.getString("date");
                String priority = jsonObject.getString("priority");

                taskList.add(new Task(title, date, priority));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taskList;
    }
}
