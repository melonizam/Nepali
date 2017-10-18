package com.tutorials.hp.navviewrecyclerview.mFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tutorials.hp.navviewrecyclerview.Datacontainer;
import com.tutorials.hp.navviewrecyclerview.DataloadEnglish_11;
import com.tutorials.hp.navviewrecyclerview.R;
import com.tutorials.hp.navviewrecyclerview.Recycleradapter.Recycleradapter;
import com.tutorials.hp.navviewrecyclerview.Recycleradapter.Recycleradapter.Clicklistener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.ProgressDialog.STYLE_SPINNER;

public class English11 extends android.support.v4.app.Fragment implements Clicklistener {

    RecyclerView recycle;
    View view;
    String url;
    ProgressDialog progressDialog;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_1, container, false);
        this.recycle = (RecyclerView) this.view.findViewById(R.id.recycle);
        return this.view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //progressDialog initializing start
        progressDialog = new ProgressDialog(getContext(), STYLE_SPINNER);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        //progressDialog initializing end

        String courseID = getArguments().getString("COURSEID");
        switch (courseID){
            case "phy11":
                url = "https://notes.tyrocity.com/notes/physics-xi-hseb-notes/";
                break;
            case "che11":
                url = "https://notes.tyrocity.com/notes/chemistry-xi-hseb-notes/";
                break;
            case "bio11":
                url = "https://notes.tyrocity.com/notes/biology-xi-hseb-notes/";
                break;
            case "bus11":
                url = "https://notes.tyrocity.com/notes/business-studies-xi-hseb-notes/";
                break;
            case "eco11":
                url = "https://notes.tyrocity.com/notes/economics-xi-hseb-notes/";
                break;
            case "acc11":
                url = "https://notes.tyrocity.com/notes/accountancy-xi-hseb-notes/";
                break;
            case "tt11":
                url = "https://notes.tyrocity.com/notes/travel-tourism-xi-hseb-notes/";
                break;
            case "eng11":
                url = "https://notes.tyrocity.com/notes/english-xi-hseb-notes/";
                break;
            case "cse11":
                url = "https://notes.tyrocity.com/notes/computer-xi-hseb-notes/";
                break;
            case "men11":
                url = "https://notes.tyrocity.com/notes/major-english-xi-hseb-notes/";
                break;
            case "soc11":
                url = "https://notes.tyrocity.com/notes/sociology-xi-hseb-notes/";
                break;
            case "mc11":
                url = "https://notes.tyrocity.com/notes/mass-communication-xi-hseb-notes/";
                break;
            case "phy12":
                url = "https://notes.tyrocity.com/notes/physics-xii-hseb-notes/";
                break;
            case "che12":
                url = "https://notes.tyrocity.com/notes/chemistry-xii-hseb-notes/";
                break;
            case "bio12":
                url = "https://notes.tyrocity.com/notes/biology-xii-hseb-notes/";
                break;
            case "nap11":
                url = "https://notes.tyrocity.com/notes/nepali-hseb-notes/";
                break;
            case "eco12":
                url = "https://notes.tyrocity.com/notes/economics-xii-hseb-notes/";
                break;
            case "bus12":
                url = "https://notes.tyrocity.com/notes/business-studies-xii-hseb-notes/";
                break;
            case "hom12":
                url = "https://notes.tyrocity.com/notes/hotel-management-xii-hseb-notes/";
                break;
            case "eng12":
                url = "https://notes.tyrocity.com/notes/english-xii-hseb-notes/";
                break;
            case "men12":
                url = "https://notes.tyrocity.com/notes/major-english-xii-hseb-notes/";
                break;
            case "engbba11":
                url = "https://notes.tyrocity.com/english-bba-notes/";
                break;
            case "phy00":
                url = "https://notes.tyrocity.com/physics-old-is-gold/";
                break;
            case "che00":
                url = "https://notes.tyrocity.com/chemistry-old-is-gold/";
                break;
            case "bio00":
                url = "https://notes.tyrocity.com/biology-old-is-gold/";
                break;
            case "bil":
                url = "https://notes.tyrocity.com/bachelors-in-law/";
                break;

        }

        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final ArrayList <Datacontainer> data = new ArrayList<Datacontainer>();

                try {
                    Document doc = Jsoup.connect(url).get();
                    String title = doc.title();
                    Elements content = doc.select("div.entry-content");
                    Elements links = content.select("a[href]");
                    builder.append(title).append("\n");

                    for (Element link : links) {
                        data.add(new Datacontainer(link.text(), link.attr("href")));
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            setupRecycler(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                progressDialog.cancel();
            }
        }).start();

    }

    private void setupRecycler(ArrayList<Datacontainer> data) {
        Recycleradapter recycleradapter = new Recycleradapter(getActivity(), data);
        recycleradapter.setClicklistener(this);
        this.recycle.setHasFixedSize(true);
        this.recycle.setAdapter(recycleradapter);
        this.recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    public void itemclick(View v, String link) {
        DataloadEnglish_11 fragment = new DataloadEnglish_11();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Bundle bundle = new Bundle();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        bundle.putString("key", link);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static android.support.v4.app.Fragment newInstance(String idText) {
        android.support.v4.app.Fragment fr = new English11();
        Bundle bn = new Bundle();
        bn.putString("COURSEID", idText);
        fr.setArguments(bn);
        return fr;
    }
}
