package com.example.grouppeer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

import com.example.peerbased.Packet;

import QuizPackets.QuestionPacket;
import StaticAttributes.PacketSequenceNos;
import StaticAttributes.QuizAttributes;
import StaticAttributes.Utilities;
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
	public String s1,s2,s3,s4,s5;
	Button btn;
	String options[] = {"option1", "option2", "option3", "option4"};
	boolean status[] = new boolean[options.length];
    String correctoption;
    DatagramSocket sock;
    
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
        sock = StaticAttributes.SocketHandler.normalSocket;
        question=(EditText)findViewById(R.id.question);
        
        s1 = this.getString(R.string.choose_an_option);
        s2 = this.getString(R.string.you_selected);
        s3 = this.getString(R.string.question_submitted_successfully);
        s4 = this.getString(R.string.ques_accepted_by_teacher);
        s5 = this.getString(R.string.ques_rejected_by_teacher);
        
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
			ad.setTitle(s1);
			
			ad.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() 
			{
				
				public void onClick(DialogInterface dialog, int which) 
				{
					
					Toast t = Toast.makeText(getBaseContext(), s2+options[which], 2000);
					t.show();
					if(options[which].equals(choice1))
					{
						correctoption = options[0];
					}
					else if(options[which].equals(choice2))
					{
						correctoption = options[1];
					}
					else if(options[which].equals(choice3))
					{
						correctoption = options[2];
					}
					else if(options[which].equals(choice4))
					{
						correctoption = options[3];					
					}
					
					dialog.dismiss();					
				}

			
			
			});
			ad.show();
			
			 Toast t = Toast.makeText(this,s3, 2000);
			 t.show();
			 
				QuestionPacket qp = new QuestionPacket(QuizAttributes.groupName, (byte)1);
				qp.question = question1;
				qp.correctAnswerOption = correctoption;
				qp.options = options;
				
				Packet p = new Packet(PacketSequenceNos.QUIZ_QUESTION_PACKET_CLIENT_SEND, false, false, false, Utilities.serialize(qp));
				p.quizPacket = true;
				
				byte[] bytes = Utilities.serialize(p);
				
				DatagramPacket pack = new DatagramPacket(bytes, bytes.length, Utilities.serverIP, Utilities.servPort);
				
				try {
					System.out.println("Sending!");
					sock.send(pack);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("sent!");
				/*
				 * Now wait for the authentication of the packet
				 */
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
					 * Packet is received from Teacher
					 */
					Packet recvpack = (Packet)Utilities.deserialize(by);
					if( recvpack.seq_no == PacketSequenceNos.QUIZ_QUESTION_PACKET_SERVER_ACK && recvpack.quizPacket == true )
					{
						System.out.println("Its a ques packet");
						QuestionPacket qpack = (QuestionPacket) Utilities.deserialize(recvpack.data);
						
						if( qpack.questionAuthenticated == true )
						{
							 System.out.println("ITS CORRECT");
							/*
							 * Question is accepted by teacher
							 */
							Toast t1 = Toast.makeText(this, s4, 2000);
							t1.show();
							System.out.println("GOINGGGGGGGGGGGGGG");
						    Intent i=new Intent(this,SimpleCommonPage.class);
						    startActivity(i);
						    break;
						}
						else
						{
							/*
							 * Question is rejected by teacher
							 */
							 System.out.println("ITS NOT CORRECT");
							Toast t1 = Toast.makeText(this, s5, 2000);
							t1.show();
							System.out.println("BACK!!!!!!!!!!!!");
							Intent i=new Intent(this,Leader_question.class);
						    startActivity(i);
						    break;
						}
					}
					else
					{
						 System.out.println("Noooooo!");
						continue;
					}
				} 	
	}
}
