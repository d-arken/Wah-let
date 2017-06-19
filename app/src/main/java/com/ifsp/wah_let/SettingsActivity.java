package com.ifsp.wah_let;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by MAMUTE on 19/06/2017.
 */

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).commit();
    }

}