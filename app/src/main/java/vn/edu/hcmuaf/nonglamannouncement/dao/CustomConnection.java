package vn.edu.hcmuaf.nonglamannouncement.dao;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import vn.edu.hcmuaf.nonglamannouncement.R;

public class CustomConnection {

    public enum URLPostfix {
        ANNOUNCE_RECENT("announce/recent"),GROUP("group"), LOGIN("user/login"), ANNOUNCE_ALL("announce/all");

        URLPostfix(String postfix) {
            this.postfix = postfix;
        }

        private String postfix;

        public String getPostfix() {
            return postfix;
        }
    }

    public static void makeGETConnection(final Activity activity, URLPostfix postfix, final String nameOfResult) {
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        String url = "http://10.0.2.2:8080/NongLamAnnounceService/webresources/" + postfix.getPostfix();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences sp = activity.getSharedPreferences("temp_data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(nameOfResult, response);
                        editor.apply();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(), activity.getText(R.string.error_connection), Toast.LENGTH_LONG);
            }
        });
        queue.add(stringRequest);


    }

    public static void jsonGETConnection(final Activity activity, URLPostfix postfix, final String nameOfResult)
    {
        String url = "http://192.168.1.24:8080/NongLamAnnounceService/webresources/"+postfix.getPostfix();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        SharedPreferences sp = activity.getSharedPreferences("temp_data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(nameOfResult, response.toString());
                        editor.apply();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
    }

}
