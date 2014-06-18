package com.example.grouppeer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import com.example.peerbased.Packet;
import StaticAttributes.PacketSequenceNos;
import StaticAttributes.Utilities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


class ActiveGroupWaiter extends Thread
{
	DatagramSocket sock;
	
	public ActiveGroupWaiter () {
		sock = StaticAttributes.SocketHandler.normalSocket;	
	}
	public void run()
	{
		while( true )
		{
			byte[] by = new byte[Utilities.MAX_BUFFER_SIZE];
			DatagramPacket packy = new DatagramPacket(by, by.length);
			try
			{
				sock.receive(packy);
			}
			catch( SocketTimeoutException e )
			{
				continue;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("WAHHH!");
			
			 /*
			  *  Packet is received from Teacher (Server)
			  */
			System.out.println("I got a packet");
			Packet recvpack = (Packet)Utilities.deserialize(by);
			if( recvpack.seq_no == PacketSequenceNos.QUIZ_QUESTION_BROADCAST_SERVER_SEND && recvpack.quizPacket == true)
			{
				Intent i= new Intent(ActiveTeamQuesWait.staticVar,ActiveTeamAnsWait.class);
				ActiveTeamQuesWait.staticVar.startActivity(i);
				break;
			}
			else
			{
				continue;
			}
		} 
	}
}


public class ActiveTeamQuesWait extends Activity{
	TextView tv;
	public static ActiveTeamQuesWait staticVar;
	public void onCreate(Bundle savedInstanceState) 
	{
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.active_team_ques_wait);
	        staticVar = this;
	        tv = (TextView)findViewById(R.id.atqw_tv);
	        tv.setText("Please wait untill your leader forms the question");
	        /*
	         * If question broadcast packet is received, then change the page to ActiveTeamAnswait page
	         */
	        new ActiveGroupWaiter().start();        
	}
}
