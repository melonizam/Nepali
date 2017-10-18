package com.tutorials.hp.navviewrecyclerview;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class DataloadEnglish_11 extends Fragment {
    WebView webView;
    Gson gson;
    String mapContent;
    String url;
    Button savebtn;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dataload, container, false);
        this.webView = (WebView) v.findViewById(R.id.webview);
        savebtn = (Button)v.findViewById(R.id.save);
        return v;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        url = getArguments().getString("key");
        url = url.replace(".com/",".com/?json=get_post&post_slug=");

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String s) {
                final DatabaseHandler databaseHandler = new DatabaseHandler(getContext());

                gson = new Gson();
                final Test mapPost = gson.fromJson(s, Test.class);
                mapContent = mapPost.getPost().getContent();

                MainActivity ased = (MainActivity)getActivity();
                ased.getSupportActionBar().setTitle(mapPost.getPost().getTitle());
                ased.invalidateOptionsMenu();
                Document doc = Jsoup.parse(mapContent);
                doc.select("div.yarpp-related").first().remove();
                mapContent = doc.toString();
                webView.loadData(mapContent,"text/html","UTF-8");

//                if (databaseHandler.getValidation(mapPost.getPost().getId())){
//                    savebtn.setVisibility(View.GONE);
//                }




                savebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OfflinePost offlinePost = new OfflinePost(mapPost.getPost().getId(),mapPost.getPost().getTitle(),mapPost.getPost().getContent());


                        databaseHandler.addOffilePost(offlinePost);

                        List<OfflinePost> posts = databaseHandler.getAllPosts();

                        for (OfflinePost post : posts) {
                            String log = "Id: " + post.get_id() + " ,Name: " + post.get_title() + " ,Address: " + post.get_content();
                            // Writing shops  to log
                            Log.d("Shop: : ", log);
                        }
                        savebtn.setVisibility(View.GONE);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

    }
}
