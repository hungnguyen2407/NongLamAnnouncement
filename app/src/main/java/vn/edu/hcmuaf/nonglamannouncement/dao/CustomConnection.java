package vn.edu.hcmuaf.nonglamannouncement.dao;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;

public class CustomConnection {

    //    private static final String URL = "http://192.168.1.3:8080/NongLamAnnounceService/webresources/";
    private static final String URL = "http://nguyentuesdd.ddns.net:8080/NongLamAnnounceService/service/";

    public static void makeGETConnection(final Activity activity, URLPostfix postfix, final String nameOfResources) {
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        String url = URL + postfix.getPostfix();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences sp = activity.getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(nameOfResources, response);
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

    public static void jsonGETConnection(final Activity activity, URLPostfix postfix, final String nameOfResources) {
        String url = URL + postfix.getPostfix();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        SharedPreferences sp = activity.getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(nameOfResources, response.toString());
                        editor.apply();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
    }

    public static void makeGETConnectionWithParameter(final Activity activity, URLPostfix postfix, final String nameOfResources, String... parameters) {

        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        String url = URL + postfix.getPostfix();
        for (String i : parameters) {
            url += "/" + i;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences sp = activity.getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(nameOfResources, response);
                        editor.apply();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(), activity.getText(R.string.error_connection), Toast.LENGTH_LONG);
            }
        });
        queue.add(stringRequest);

        Volley.newRequestQueue(activity.getApplicationContext());
    }

    public static void makeDELETEConnection(final Activity activity, URLPostfix postfix, final String nameOfResources) {
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        String url = URL + postfix.getPostfix();
        new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public static void makePOSTConnectionWithParamter(final Activity activity, URLPostfix postfix, final String nameOfResources, String... parameters) {
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        String url = URL + postfix.getPostfix();
        for (String i : parameters) {
            url += "/" + i;
        }
        new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public enum URLPostfix {
        ANNOUNCE_RECENT("announce/recent"),
        GROUP("group"), LOGIN("user/login"),
        ANNOUNCE_ALL("announce/all"),
        FIND_NAME_BY_ID("findname"),
        GROUP_JOIN("group/join"),
        GROUP_LIST("user/dsgroup"),
        POST_ANNOUNCE("announce/add"),
        USER_INFO("user/info");

        URLPostfix(String postfix) {
            this.postfix = postfix;
        }

        private String postfix;

        public String getPostfix() {
            return postfix;
        }
    }
}

