package com.example.android.skeletonapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SkeletonSubActivity extends Activity {
    
    private static AutoCompleteTextView textView;

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
    }
    
    protected static OnClickListener sbListener = new OnClickListener() {
        String[] str = {"one", "two", "three", "four"};
        int i = 0;
        
        @Override
        public void onClick(View v) {
            textView.setText(str[i++%4]);
        }
    };
    
    protected static OnClickListener enListener = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            textView.setEnabled(true);
        }
    };
    
}
