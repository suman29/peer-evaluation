package com.example.grouppeer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Leader_question extends Activity  implements OnClickListener 
 {
	
	Button mcq,truefalse,fill;
	private String mcq1,truefalse1,fill1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leader_question);
        
        mcq=(Button)findViewById(R.id.button1);
        truefalse=(Button)findViewById(R.id.button2);   
        fill=(Button)findViewById(R.id.button3);
        
        mcq1 = this.getString(R.string.you_selected_mcq);
        truefalse1 = this.getString(R.string.you_selected_tf);
        fill1 = this.getString(R.string.you_selected_oneword);
        
       
        truefalse.setOnClickListener(this);
        mcq.setOnClickListener(this);
        fill.setOnClickListener(this);
    }
	public void onClick(View v)     //actions performed after  buttons are clicked.
	{   
		 Intent i;
			switch (v.getId())
			{
		    case R.id.button1:
		    	Toast.makeText(this,mcq1, 1000).show();
		    	 i = new Intent(this,Multiple_choice.class);
				startActivity(i);
		        break;
		    case R.id.button2:
		    	Toast.makeText(this,truefalse1, 1000).show();
		    	 i = new Intent(this,True_false.class);
				startActivity(i);
				break;
		    case R.id.button3:
		    	Toast.makeText(this,fill1, 1000).show();
		    	 i = new Intent(this,One_word.class);
				startActivity(i);
				break;
			}
	}

}
