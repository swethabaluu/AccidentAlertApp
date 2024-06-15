package com.example.a91962.accidentalert;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class Adminpage extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7;

    String staffid,dept;
    Random rand = new Random();

    String phone="";
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        b1=(Button)findViewById(R.id.b1);

        b3=(Button)findViewById(R.id.button2);

        b4=(Button)findViewById(R.id.button8);

        b2=(Button)findViewById(R.id.button4);

        b5=(Button)findViewById(R.id.button5);

        b6=(Button)findViewById(R.id.button);

        b7=(Button)findViewById(R.id.button7);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //generate code

                Intent i=new Intent(Adminpage.this,AddvehicleDetails.class);

               startActivity(i);







            }
        });



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Adminpage.this,AdddonorDetails.class);
                startActivity(i);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Adminpage.this,MainActivity.class);
                startActivity(i);
            }
        });




        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Adminpage.this,uploadAdddonorDetails.class);
                startActivity(i);
            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Adminpage.this,uploadvehicleDetails.class);
                startActivity(i);
            }
        });







        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Adminpage.this,viewvehicleinfo.class);
                startActivity(i);
            }
        });



        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Adminpage.this,viewdonorinfo.class);
                startActivity(i);
            }
        });












    }
}
