package com.tutorials.hp.navviewrecyclerview.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.tutorials.hp.navviewrecyclerview.R;

/**
 * Created by kme on 9/20/17.
 */


public class Home extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.home_grid, container, false);
        return this.view;
    }

    public static Fragment newInstance(String idText) {
        Fragment fr = new Home();
        Bundle bn = new Bundle();
        bn.putString("COURSEID", idText);
        fr.setArguments(bn);
        return fr;
    }
    public void buttonOnClick(View view){
        Toast.makeText(getContext(), ((Button)view).getText(), Toast.LENGTH_SHORT).show();
    }
}
