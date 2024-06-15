package com.example.a91962.accidentalert;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class uploadAdddonorDetails extends AppCompatActivity implements View.OnClickListener{

    EditText e1,e2,e3,e4,e5,e6,e7;
    Spinner s1,s2;
    String dbrno,sr1,sr2,sr3,sr4;
    SmsManager sms;
    Random rand = new Random();
    int dbflag=0;
    Cursor c;
    Button b1;
    String rno,name,phone,classs,address,deptinfo;
    int fg=0;
    private DatePickerDialog fromDatePickerDialog2;
    Calendar newCalendar;
    Calendar newDate;
    int fg2=0;
    int xyr,xday,xmonth;
    String t1,t2;
    private SimpleDateFormat dateFormatter;
    ArrayAdapter sp,sp1;
    List gend=new ArrayList();

    List dept=new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_modify);

        e1=(EditText)findViewById(R.id.e1);

        e6=(EditText)findViewById(R.id.e6);

        e2=(EditText)findViewById(R.id.e2);
        e4=(EditText)findViewById(R.id.e4);

        e7=(EditText)findViewById(R.id.editText7);



        b1=(Button)findViewById(R.id.b1);


        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), "get details", Toast.LENGTH_SHORT).show();


                SQLiteDatabase db=openOrCreateDatabase("Alert", Context.MODE_PRIVATE,null);




                try{

                    if(db!=null){

                        c=db.rawQuery("SELECT * from donorinfo where(s4='"+e4.getText().toString()+"')",null);
                        if(c.moveToFirst()){
                            do{



                                sr1=c.getString(c.getColumnIndexOrThrow("s1"));
                                sr2=c.getString(c.getColumnIndexOrThrow("s3"));
                                sr3=c.getString(c.getColumnIndexOrThrow("s2"));

                                sr4=c.getString(c.getColumnIndexOrThrow("s6"));





                                // e5.setText(ser_phone);



                            }while(c.moveToNext());

                        }
                    }
                }
                catch (Exception e){
                    // Toast.makeText(getApplicationContext(),"Exception in extracting "+e,Toast.LENGTH_SHORT).show();
                }






                e1.setText(sr1);
                e6.setText(sr2);
                e2.setText(sr3);
                e7.setText(sr4);


            }
        });




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                SQLiteDatabase db=openOrCreateDatabase("Alert", Context.MODE_PRIVATE,null);
                try{

                    String q1="CREATE TABLE donorinfo(s1 TEXT ,s2 TEXT,s3 TEXT,day INTEGER,month INTEGER,year INTEGER,s4 TEXT,s5 TEXT,s6 TEXT);";
                    db.execSQL(q1);
                }
                catch (Exception e)
                {
                    //Toast.makeText(getApplicationContext(),"Exception in creating table "+e,Toast.LENGTH_SHORT).show();
                }


                SQLiteDatabase mydb=openOrCreateDatabase("Alert",Context.MODE_PRIVATE,null);


                String insert=" UPDATE donorinfo SET  s1='"+e1.getText().toString()+"',s3='"+e6.getText().toString()+"',s2='"+e2.getText().toString()+"',s6='"+e7.getText().toString()+"',s4='"+e4.getText().toString()+"' where s4='"+e4.getText().toString()+"'";
                try{
                    mydb.execSQL(insert);
                    Toast.makeText(getApplicationContext(),"Donor Details upload success ",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(uploadAdddonorDetails.this,Adminpage.class);

                    startActivity(i);


                }
                catch (Exception e )
                {
                    Toast.makeText(getApplicationContext(),"Exception in creating table "+e,Toast.LENGTH_SHORT).show();
                }




            }
        });

    }


    @Override
    public void onClick(View v) {

        if(v==e3)
            fromDatePickerDialog2.show();
    }
}
