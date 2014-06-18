package com.example.grouppeer;

import java.util.Locale;

import StaticAttributes.QuizAttributes;
import StaticAttributes.SocketHandler;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class index extends Activity implements OnClickListener  {
	
      Button login;
	
	 public void onCreate(Bundle savedInstanceState) 
	    {
	    	super.onCreate(savedInstanceState);
			setContentView(R.layout.index);
			
			
			// Create a thread to listen for the server's error messages;
			
			
	    	
			login=(Button)findViewById(R.id.button1);
			login.setOnClickListener(this);
			
			
			
			
			
	    }
		

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main, menu);
	        return super.onCreateOptionsMenu(menu);
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle presses on the action bar items
	        switch (item.getItemId()) {
	            case R.id.action_hindi :
	                openHindi();
	                return true;
	            case R.id.action_english:
	                 openEnglish();
	                return true;
	            case R.id.action_marathi:
	                openMarathi();
	               return true;
	            case R.id.aboutus :
	            	openAboutus();
	            	return true;
	            case R.id.Help :
	            	openHelp();
	            	return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }

	    
		private void openHelp() {
			// TODO Auto-generated method stub
			Intent i;
			i= new Intent(this,Help.class);
			startActivity(i);
			
			
		}


		private void openAboutus() {
			// TODO Auto-generated method stub
			Intent i;
			i= new Intent(this,Aboutus.class);
			startActivity(i);
			
		}


		private void openEnglish() {
			// TODO Auto-generated method stub
			Locale locale = new Locale("en"); 
	        Locale.setDefault(locale);
	        Configuration config = new Configuration();
	        config.locale = locale;
	        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
		     System.out.println("bbkhvdlvfvl");
	        
		}


		private void openHindi() {
			// TODO Auto-generated method stub
			Locale locale = new Locale("hi"); 
	        Locale.setDefault(locale);
	        Configuration config = new Configuration();
	        config.locale = locale;
	        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	        System.out.println("bbkhvdlvfvl");
		}
		
		
		private void openMarathi() {
			// TODO Auto-generated method stub
			Locale locale = new Locale("mr"); 
	        Locale.setDefault(locale);
	        Configuration config = new Configuration();
	        config.locale = locale;
	        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	        System.out.println("bbkhvdlvfvl");
		}

		
		
		
		public void onClick(View v) 
		{
		Intent i=new Intent(this,Login.class);
		startActivity(i);
		
			
		}
			  

}
