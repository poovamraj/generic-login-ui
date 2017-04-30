package com.example.kishore.afinal;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by KISHORE on 30-03-2017.
 */

public class NetworkConnection{
    static NetworkConnection networkConnection;
    private static RequestQueue requestQueue;

    public static NetworkConnection getInstance(Context context){
        if(networkConnection==null){
            requestQueue = Volley.newRequestQueue(context);
            return new NetworkConnection();
        }
        return networkConnection;
    }
    public void sendGetRequest(String url, final NetworkHandler networkHandler){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG",response);
                networkHandler.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
                networkHandler.onError(error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }
}
