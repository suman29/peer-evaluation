package com.carouseldemo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class True_false extends Activity  implements OnClickListener 
{
	
	RadioButton t1,f1;
	EditText new_password,old_password,confirm_password;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.true_false);
        t1=(RadioButton)findViewById(R.id.radioButton1);
        f1=(RadioButton)findViewById(R.id.radioButton2);
       
        t1.setOnClickListener(this);
        f1.setOnClickListener(this);
        
    }
	public void onClick(View v)     //actions performed after change password button is clicked.
	{   
		
		 Toast t = Toast.makeText(this, "Question submitted successfully", 2000);
		 t.show();
		 Intent i;
		 i=new Intent(this,Non_leader_question.class);
		 startActivity(i);
		
		
	}
	
}
