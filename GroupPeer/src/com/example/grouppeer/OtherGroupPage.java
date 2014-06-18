package com.example.grouppeer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

import com.example.peerbased.Packet;

import QuizPackets.QuestionPacket;
import StaticAttributes.PacketSequenceNos;
import StaticAttributes.QuestionAttributes;
import StaticAttributes.QuizAttributes;
import StaticAttributes.Utilities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


class OtherGroupListener extends Thread
{
	DatagramSocket sock;
	
	public OtherGroupListener () {
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
				System.out.println("verified packet");
				QuestionPacket qp = (QuestionPacket)Utilities.deserialize(recvpack.data);
				if( !QuizAttributes.groupName.equals(qp.groupName) )
				{
					QuestionAttributes.question = qp.question;
					QuestionAttributes.answer = qp.correctAnswerOption;
					QuestionAttributes.options = qp.options;
					QuestionAttributes.level = qp.level;
					QuestionAttributes.questionSeqNo = qp.questionSeqNo;
					QuestionAttributes.questionType = qp.questionType;
					if(  qp.questionType == 1 )
					{
						Intent i = new Intent(OtherGroupPage.staticAct, Answer_multiple_choice.class);
						OtherGroupPage.staticAct.startActivity(i);
					}
					else if(  qp.questionType == 2 )
					{
						Intent i = new Intent(OtherGroupPage.staticAct, Answer_true_false.class);
						OtherGroupPage.staticAct.startActivity(i);
					}
					else if(  qp.questionType == 3 )
					{
						Intent i = new Intent(OtherGroupPage.staticAct, Answer_one_word.class);
						OtherGroupPage.staticAct.startActivity(i);
					}
					break;
				}
				else
				{
					continue;
				}
			}
			else
			{
				continue;
			}
		} 
	}
}

public class OtherGroupPage extends Activity{
	
	TextView tv;
	public static OtherGroupPage staticAct;
	public void onCreate(Bundle savedInstanceState) 
	{
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.other_group_page);
	        tv = (TextView) findViewById(R.id.ogp_tv);
	        tv.setText("You will receive a Question in a moment. Be ready!");
	        /*
	         * Go to answer pages accordingly after getting the packet
	         */
	        staticAct = this;
	        OtherGroupListener l = new OtherGroupListener();
	        l.start();
	}
}
