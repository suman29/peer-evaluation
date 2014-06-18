package com.carouseldemo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class One_word extends Activity implements OnClickListener 
{
	
    Button submit;
  
	EditText question,answer;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fillup);
        submit=(Button)findViewById(R.id.button1);
        question=(EditText)findViewById(R.id.fillupq);
        answer=(EditText)findViewById(R.id.fillupa);
        
        submit.setOnClickListener(this);
        
    }
	public void onClick(View v)     //actions performed after change password button is clicked.
	{   
	      String question1,answer1;
		 question1 = question.getText().toString();
		 answer1 = answer.getText().toString();
		 
		 Toast t = Toast.makeText(this, "Question submitted successfully", 2000);
		 t.show();
		
		 Intent i;
		 i=new Intent(this,Non_leader_question.class);
		 startActivity(i);
		
	}
	


}
