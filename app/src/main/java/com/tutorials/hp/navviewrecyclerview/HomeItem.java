package com.tutorials.hp.navviewrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.tutorials.hp.navviewrecyclerview.mFragments.English11;
import com.tutorials.hp.navviewrecyclerview.mFragments.Home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by kme on 9/20/17.
 */

public class HomeItem extends Fragment {
    private View view;
    private HashMap<String,String> listElements;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_items, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String catagory = getArguments().getString("COURSEID");

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayOut);
        final ListView listView = new ListView(view.getContext());

        if(catagory.equals(getString(R.string.bachelors_in_law))){
            listElements = new HashMap<>();
            listElements.put("Bachelors In Law","bil");

        }
        else if(catagory.equals(getString(R.string.bba))){
            listElements = new HashMap<>();
            listElements.put("English–I","engbba11");

        }
        else if(catagory.equals(getString(R.string.civil_engineering))){
            listElements = new HashMap<>();
            listElements.put("","");

        }
        else if(catagory.equals(getString(R.string.humanities_11))){
                listElements = new HashMap<>();
            listElements.put("Major English – 11 Notes","eng11");
            listElements.put("Sociology – 11 Notes", "soc11");
            listElements.put("Mass Communication – 11 Notes","mc11");

        }
        else if(catagory.equals(getString(R.string.humanities_12))){
        listElements = new HashMap<>();
            listElements.put("MajorEnglish–12Notes","men12");

        }
        else if(catagory.equals(getString(R.string.it))){
        listElements = new HashMap<>();
            listElements.put("","");


        }
        else if(catagory.equals(getString(R.string.management_11))){
        listElements = new HashMap<>();

            listElements.put("Economics–11 Notes","eco11");
            listElements.put("Business Studies – 11 Notes","bus11");
            listElements.put("Accountancy – 11 Notes","acc11");
            listElements.put("Travel and Tourism – 11 Notes","tt11");
            listElements.put("English Notes","eng11");
            listElements.put("Computer – 11 Notes","cse11");
            listElements.put("Nepali Notes","nap11");


        }
        else if(catagory.equals(getString(R.string.management_12))){
        listElements = new HashMap<>();
            listElements.put("Economics–12 Notes","eco12");
            listElements.put("Business Studies – 12 Notes","bus12");
            listElements.put("Hotel Management – 12 Notes","hom12");
            listElements.put("English Notes","eng12");

        }
        else if(catagory.equals(getString(R.string.old_is_gold))){
        listElements = new HashMap<>();
            listElements.put("Physics Old is Gold","phy00");
            listElements.put("Chemistry Old is Gold","che00");
            listElements.put("Biology Old is Gold","bio00");

        }
        else if(catagory.equals(getString(R.string.science_11))){
        listElements = new HashMap<>();

            listElements.put("Physics – 11 Notes","phy11");
            listElements.put("Chemistry – 11 Notes","che11");
            listElements.put("Biology – 11 Notes","bio11");
            listElements.put("Nepali Notes","nap11");
            listElements.put("English Notes","eng11");

        }
        else if(catagory.equals(getString(R.string.science_12))){
        listElements = new HashMap<>();
            listElements.put("Physics – 12 Notes","phy12");
            listElements.put("Chemistry – 12 Notes","che12");
            listElements.put("Biology – 12 Notes","bio12");
            listElements.put("Computer – 11 Notes","cse11");
            listElements.put("English Notes","eng12");
        }
        Set<String> names = listElements.keySet();
        final List list = new ArrayList(names);

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (getContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        linearLayout.addView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                Toast.makeText(getContext(), (CharSequence) list.get(position), Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).getSupportActionBar().setTitle((CharSequence) list.get(position));

                //PERFORM TRANSACTION TO REPLACE CONTAINER WITH FRAGMENT
                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.containerID, English11.newInstance(listElements.get((CharSequence) list.get(position)))).commit();
            }


        });
    }

    public static Fragment newInstance(String catagory, Context ctx) {
        Fragment fr = new HomeItem();
        Bundle bn = new Bundle();
        bn.putString("COURSEID", catagory);
        fr.setArguments(bn);
        return fr;

    }

}
