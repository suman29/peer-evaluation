package com.carouseldemo.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Answer_multiple_choice extends Activity implements OnClickListener 
{
	Button btn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_multiple_choice);
        
       
        btn=(Button)findViewById(R.id.button1);
       
        btn.setOnClickListener(this);
    }
	public void onClick(View v)     //actions performed after change password button is clicked.
	{   
	  
		int answer;
		
		RadioGroup g = (RadioGroup) findViewById(R.id.radioGroup1);
		 
		      switch (g.getCheckedRadioButtonId())
		       {
		            case R.id.radio1 :
	
                          answer=1;
		                  break;
		 
		            case R.id.radio2 :
			              answer=2;
		                  break;
		
		             case R.id.radio3 :
			              answer=3;
			              break;
			
		            case R.id.radio4 :
			              answer=4;
			              break;
		        }
		
     }
}