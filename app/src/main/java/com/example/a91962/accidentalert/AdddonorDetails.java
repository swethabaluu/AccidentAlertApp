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

public class AdddonorDetails extends AppCompatActivity implements View.OnClickListener{

    EditText e1,e2,e3,e4,e5,e6;
    Spinner s1,s2;
    String dbrno;
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
        setContentView(R.layout.activity_donor_register);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);
        s1=(Spinner)findViewById(R.id.s1);



        s2=(Spinner)findViewById(R.id.spinner);



        b1=(Button)findViewById(R.id.b1);
        gend.add("Male");
        gend.add("Female");
        sp=new ArrayAdapter(this,android.R.layout.simple_spinner_item,gend);
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(sp);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t1=(String)s1.getSelectedItem();
                Toast.makeText(getApplicationContext(),"Gender:"+t1,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dept.add("B+");
        dept.add("A+");
        dept.add("AB+");
        dept.add("B-");
        dept.add("A-");
        dept.add("AB+");
        dept.add("O+");
        dept.add("AB+");
        dept.add("AB-");





        sp1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept);
        sp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(sp1);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t2=(String)s2.getSelectedItem();
                Toast.makeText(getApplicationContext(),"BLOOD GROUP:"+t2,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        fg2=0;
        try{

            e3.setInputType(InputType.TYPE_NULL);
            e3.requestFocus();
            e3.setOnClickListener((View.OnClickListener) this);
            newCalendar = Calendar.getInstance();

            fromDatePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);

                    e3.setText(dateFormatter.format(newDate.getTime()));
                    fg2=1;
                    xyr = newDate.get(Calendar.YEAR);
                    xday=newDate.get(Calendar.DATE);
                    xmonth=newDate.get(Calendar.MONTH)+1;
                    // Toast.makeText(getApplicationContext(), "Year "+xyr+" "+xday+" "+xmonth,Toast.LENGTH_SHORT).show();


                }

            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        }
        catch (Exception e)
        {
            Toast.makeText(this,"Exception"+e,Toast.LENGTH_SHORT).show();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    rno=e1.getText().toString();
                    name=e2.getText().toString();
                    phone=e4.getText().toString();
                    classs=e5.getText().toString();
                    address=e6.getText().toString();


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
                String insert="INSERT INTO donorinfo(s1,s2,s3,day,month,year,s4,s5,s6) VALUES('"+e1.getText().toString()+"','"+e2.getText().toString()+"','"+t2+"','"+xday+"','"+xmonth+"','"+xyr+"','"+e4.getText().toString()+"','"+e5.getText().toString()+"','"+e6.getText().toString()+"')";
                try{
                    mydb.execSQL(insert);
                    Toast.makeText(getApplicationContext(),"Donor Details Entry success ",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(AdddonorDetails.this,Adminpage.class);

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
