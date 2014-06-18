package com.example.grouppeer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class SimpleCommonPageForLeader extends Activity {

	TextView tv;
	public String s1,s2,s3;
	
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.simplecommonpageforleader);
        tv = (TextView)findViewById(R.id.textViewLeaderSCP);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        s1=this.getString(R.string.please_wait_leader);
        s2=this.getString(R.string.answer_correct);
        s3=this.getString(R.string.answer_wrong);
        
        if( extras == null )
        {
        	/*
        	 * For leader
        	 */
        	tv.setText(s1);
        	return;
        }
        
        
        String result = extras.getString("result");
        if( result.equals("correct"))
        {
        	/* 
        	 * When students give responses
        	 */
        	tv.setText(s2);
        }
        else if( result.equals("wrong"))
        {
        	/*
        	 * When students give responses
        	 */
        	tv.setText(s3);
        }
    }
}
