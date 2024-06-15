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

public class uploadvehicleDetails extends AppCompatActivity implements View.OnClickListener{
    EditText e1, e2, e3, e4, e5, e6,deptinfo;
    Spinner s1,s2;
    String dbrno,sr1,sr2,sr3,sr4,sr5;
    int dbflag = 0;
    Cursor c;
    Button b1;
    SmsManager sms;
    String rno, name, phone, classs, address;
    int fg = 0;
    private DatePickerDialog fromDatePickerDialog2;
    Calendar newCalendar;
    Calendar newDate;
    int fg2 = 0;
    int xyr, xday, xmonth;
    String t1,t2;

    List dept=new ArrayList();
    private SimpleDateFormat dateFormatter;
    ArrayAdapter sp,sp1;
    List gend = new ArrayList();
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_vehicle_details);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);

        e5 = (EditText) findViewById(R.id.e5);

        e6 = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.b1);




        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Toast.makeText(getApplicationContext(), "get details", Toast.LENGTH_SHORT).show();


                SQLiteDatabase db=openOrCreateDatabase("Alert", Context.MODE_PRIVATE,null);

                try{

                    if(db!=null){

                        c=db.rawQuery("SELECT * from vehicleinfo where(vehid='"+e1.getText().toString()+"')",null);
                        if(c.moveToFirst()){
                            do{



                                sr1=c.getString(c.getColumnIndexOrThrow("name"));
                                sr2=c.getString(c.getColumnIndexOrThrow("gender"));

                                // e5.setText(ser_phone);



                            }while(c.moveToNext());

                        }
                    }
                }
                catch (Exception e){
                    // Toast.makeText(getApplicationContext(),"Exception in extracting "+e,Toast.LENGTH_SHORT).show();
                }






                e2.setText(sr1);
                e6.setText(sr2);


            }
        });






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    SQLiteDatabase db=openOrCreateDatabase("Alert", Context.MODE_PRIVATE,null);
                    try{

                        String q1="CREATE TABLE vehicleinfo(vehid TEXT PRIMARY KEY,name TEXT,gender TEXT,day INTEGER,month INTEGER,year INTEGER,phone TEXT,classs TEXT,address TEXT,password TEXT,dept TEXT);";
                        db.execSQL(q1);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Exception in creating table "+e,Toast.LENGTH_SHORT).show();
                    }


                SQLiteDatabase mydb=openOrCreateDatabase("Alert",Context.MODE_PRIVATE,null);


                String insert=" UPDATE vehicleinfo SET  name='"+e2.getText().toString()+"',address='"+e5.getText().toString()+"',gender='"+e6.getText().toString()+"' where vehid='"+e1.getText().toString()+"'";
                try{
                    mydb.execSQL(insert);
                    Toast.makeText(getApplicationContext(),"Vehicle  Details update success ",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(uploadvehicleDetails.this,Adminpage.class);

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
