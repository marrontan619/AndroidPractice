package com.example.android.skeletonapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SkeletonSubActivity extends Activity {
    
    private View outerLayout;
    private AutoCompleteTextView textView;
    private SeekBar rBar;
    private SeekBar gBar;
    private SeekBar bBar;
    private SharedPreferences sp;
    private Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skelton_sub_activity);
        
        if ((savedInstanceState == null) || (savedInstanceState.isEmpty())) {
            String str = getIntent().getExtras().getString("INTENT_PARAM");
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT)
                .show();
        } 
        textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        
        ((Button) findViewById(R.id.subButton)).setOnClickListener(sbListener);
        ((Button) findViewById(R.id.enableButton)).setOnClickListener(enListener);
        rBar = ((SeekBar) findViewById(R.id.redBar));
        rBar.setOnSeekBarChangeListener(rBarListener);
        gBar = ((SeekBar) findViewById(R.id.greenBar));
        gBar.setOnSeekBarChangeListener(gBarListener);
        bBar = ((SeekBar) findViewById(R.id.blueBar));
        bBar.setOnSeekBarChangeListener(bBarListener);
        outerLayout = findViewById(R.id.outerLayout);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();
    }
    
    OnClickListener sbListener = new OnClickListener() {
        String[] str = {"one", "two", "three", "four"};
        int i = 0;
        
        @Override
        public void onClick(View v) {
            textView.setText(str[i++%4]);
        }
    };
    
    OnClickListener enListener = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            textView.setEnabled(true);
        }
    };
    
    public void setBgColor() {
        textView.setText(sp.getString("bgRed", "00"));
        outerLayout.setBackgroundColor(Color.parseColor("#" +
                                                        sp.getString("bgRed", "00") +
                                                        sp.getString("bgGreen", "00") +
                                                        sp.getString("bgBlue", "00")));
    }
    
    OnSeekBarChangeListener rBarListener = new OnSeekBarChangeListener() {
        
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            String red = Integer.toString(rBar.getProgress(), 16);
            red = (red.length() < 2) ? "0" + red : red;
            editor.putString("bgRed", red);
            editor.commit();
            setBgColor();
        }
        
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            
        }
        
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
            
        }
    };
    OnSeekBarChangeListener gBarListener = new OnSeekBarChangeListener() {
        
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            String green = Integer.toString(gBar.getProgress(), 16);
            green = (green.length() < 2) ? "0" + green : green;
            editor.putString("bgGreen", green);
            editor.commit();
            setBgColor();
        }
        
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            
        }
        
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
            
        }
    };
    OnSeekBarChangeListener bBarListener = new OnSeekBarChangeListener() {
        
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            String blue = Integer.toString(bBar.getProgress(), 16);
            blue = (blue.length() < 2) ? "0" + blue : blue;
            editor.putString("bgBlue", blue);
            editor.commit();
            setBgColor();
        }
        
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            
        }
        
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
            
        }
    };
}
