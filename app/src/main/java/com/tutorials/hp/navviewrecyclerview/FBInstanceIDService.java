package com.tutorials.hp.navviewrecyclerview;

import android.app.Service;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tontus on 9/20/17.
 */

public class FBInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG = FBInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://notes.tyrocity.com/pnfw/register/";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("token", refreshedToken);
                params.put("os", "Android");

                return params;
            }
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        Log.d(TAG, "onTokenRefresh: params sent");
    }
}