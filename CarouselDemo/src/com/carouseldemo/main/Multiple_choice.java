package com.carouseldemo.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Multiple_choice extends Activity implements android.view.View.OnClickListener
{
	
	EditText c1,c2,c3,c4,question;
	Button btn;
	String options[] = {"option1", "option2", "option3", "option4"};
	boolean status[] = new boolean[options.length];
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_choice);
        c1=(EditText)findViewById(R.id.option1);
        c2=(EditText)findViewById(R.id.option2);
        c3=(EditText)findViewById(R.id.option3);
        c4=(EditText)findViewById(R.id.option4);
        btn=(Button)findViewById(R.id.submit);
        question=(EditText)findViewById(R.id.question);
        btn.setOnClickListener(this);
        
    }
	public void onClick(View v)     //actions performed after change password button is clicked.
	{   
		final String question1,choice1,choice2,choice3,choice4;
		 
		
		 
		 question1 = question.getText().toString(); //obtaining the questions.
		 choice1=c1.getText().toString();           //choices given by the leader.
		 choice2=c2.getText().toString();
		 choice3=c3.getText().toString();
		 choice4=c4.getText().toString();
		 
		 options[0]=choice1;
		 options[1]=choice2;
		 options[2]=choice3;
		 options[3]=choice4;
		
		
		 AlertDialog.Builder ad = new AlertDialog.Builder(this);
			ad.setTitle("Choose an option");
			
			ad.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() 
			{
				
				public void onClick(DialogInterface dialog, int which) 
				{
					Toast t = Toast.makeText(getBaseContext(), "You selected "+options[which], 2000);
					t.show();
					if(options[which].equals(choice1))
					{
						final int correct=1;
					}
					else if(options[which].equals(choice2))
					{
						final int correct=2;
					}
					else if(options[which].equals(choice3))
					{
						final int correct=3;
					}
					else if(options[which].equals(choice4))
					{
						final int correct=4;
											
					}
					
					dialog.dismiss();					
				}

			
			
			});
			ad.show();
			
			 Toast t = Toast.makeText(this, "Question submitted successfully", 2000);
			 t.show();
			 
			    Intent i;
			    i=new Intent(this,Non_leader_question.class);
			    startActivity(i);
		
		
	}
	


}
