package com.example.android.skeletonapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SkeletonSubActivity extends Activity {
    
    private LinearLayout outerLayout;
    private AutoCompleteTextView textView;
    private SeekBar rBar;
    private SeekBar gBar;
    private SeekBar bBar;
    private SharedPreferences sp;
    private Editor editor;
    
    private String br = System.getProperty("line.separator");

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
        ((Button) findViewById(R.id.addLayoutButton)).setOnClickListener(addListener);
        outerLayout = (LinearLayout) findViewById(R.id.outerLayout);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();
        rBar = ((SeekBar) findViewById(R.id.redBar));
        rBar.setOnSeekBarChangeListener(bgBarListener);
        rBar.setProgress(Integer.parseInt(sp.getString(Integer.toString(R.id.redBar), "00"), 16));
        gBar = ((SeekBar) findViewById(R.id.greenBar));
        gBar.setOnSeekBarChangeListener(bgBarListener);
        gBar.setProgress(Integer.parseInt(sp.getString(Integer.toString(R.id.greenBar), "00"), 16));
        bBar = ((SeekBar) findViewById(R.id.blueBar));
        bBar.setOnSeekBarChangeListener(bgBarListener);
        bBar.setProgress(Integer.parseInt(sp.getString(Integer.toString(R.id.blueBar), "00"), 16));
        setBgColor();
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
        outerLayout.setBackgroundColor(Color.parseColor("#" +
                                                        sp.getString(Integer.toString(R.id.redBar), "00") +
                                                        sp.getString(Integer.toString(R.id.greenBar), "00") +
                                                        sp.getString(Integer.toString(R.id.blueBar), "00")));
    }
    
    OnSeekBarChangeListener bgBarListener = new OnSeekBarChangeListener() {
        
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            String bgProgress = Integer.toString(seekBar.getProgress(), 16);
            bgProgress = (bgProgress.length() < 2) ? "0" + bgProgress : bgProgress;
            editor.putString(Integer.toString(seekBar.getId()), bgProgress);
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
    
    OnClickListener addListener = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            View inflate = getLayoutInflater().inflate(R.layout.hidden_layout, null);
            outerLayout.addView(inflate);
        }
    };
    
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), getIntent().toString(), Toast.LENGTH_LONG).show();
    };
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.skelton_sub_menu, menu);
        return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.removeItem(R.id.menuItem2);
        menu.findItem(R.id.menuItem4).setEnabled(false);
        menu.findItem(R.id.subMenu1).setCheckable(true);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        String text = item.getTitle() + br + String.valueOf(item.hasSubMenu());
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        if (item.getItemId() == R.id.menuItem7 || item.getItemId() == R.id.subMenu2) {
            finish();
        } else if (item.getItemId() == R.id.subMenu1) {
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
        }
        return false;
        
    }
}
