package com.tutorials.hp.navviewrecyclerview;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by borhan on 9/18/17.
 */

public class InterGalactic extends Fragment {
    public static Fragment newInstance(String fjoawief) {
        Fragment jjj = new InterGalactic();
        Bundle jhadsf = new Bundle();
        jhadsf.putString("dfjaosdf", fjoawief);
        jjj.setArguments(jhadsf);
        return jjj;
    }
}
