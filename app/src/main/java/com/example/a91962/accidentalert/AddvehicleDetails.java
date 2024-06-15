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

public class AddvehicleDetails extends AppCompatActivity implements View.OnClickListener{
    EditText e1, e2, e3, e4, e5, e6,deptinfo;
    Spinner s1,s2;
    String dbrno;
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
        setContentView(R.layout.activity_add_vehicle_details);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        e6 = (EditText) findViewById(R.id.e6);
        s1 = (Spinner) findViewById(R.id.s1);
        b1 = (Button) findViewById(R.id.b1);
        s2=(Spinner)findViewById(R.id.spinner);

        dept.add("TVS");
        dept.add("Hyundai");
        dept.add("KIA");

        dept.add("Mahindra");

        dept.add("Bajaj");
        dept.add("Hero");




        dept.add("BMW");

        dept.add("Royal Enfield");
        dept.add("yamaha");
        dept.add("Suzuki");
        dept.add("maruthi");
        dept.add("others");



        gend.add("B+");
        gend.add("A+");
        gend.add("AB+");
        gend.add("B-");
        gend.add("A-");
        gend.add("AB+");
        gend.add("O+");
        gend.add("O-");
        gend.add("AB+");
        gend.add("AB-");
        gend.add("A1B+");
        gend.add("A1B-");


        sp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gend);
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(sp);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t1 = (String) s1.getSelectedItem();
                Toast.makeText(getApplicationContext(), "Gender:" + t1, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sp1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept);
        sp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(sp1);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               t2=(String)s2.getSelectedItem();
                Toast.makeText(getApplicationContext(),"Vehicle:"+t2,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        fg2 = 0;
        try {

            e3.setInputType(InputType.TYPE_NULL);
            e3.requestFocus();
            e3.setOnClickListener((View.OnClickListener) this);
            newCalendar = Calendar.getInstance();

            fromDatePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);

                    e3.setText(dateFormatter.format(newDate.getTime()));
                    fg2 = 1;
                    xyr = newDate.get(Calendar.YEAR);
                    xday = newDate.get(Calendar.DATE);
                    xmonth = newDate.get(Calendar.MONTH) + 1;
                    // Toast.makeText(getApplicationContext(), "Year "+xyr+" "+xday+" "+xmonth,Toast.LENGTH_SHORT).show();


                }

            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        } catch (Exception e) {
            Toast.makeText(this, "Exception" + e, Toast.LENGTH_SHORT).show();
        }
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
                String insert="INSERT INTO vehicleinfo(vehid,name,gender,day,month,year,phone,classs,address,password,dept) VALUES('"+e1.getText().toString()+"','"+e2.getText().toString()+"','"+t1+"','"+xday+"','"+xmonth+"','"+xyr+"','"+e3.getText().toString()+"','"+e4.getText().toString()+"','"+e5.getText().toString()+"','"+e6.getText().toString()+"','"+t2+"')";
                try{
                    mydb.execSQL(insert);
                    Toast.makeText(getApplicationContext(),"Vehicle Details Entry success ",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(AddvehicleDetails.this,Adminpage.class);

                    startActivity(i);


                }
                catch (Exception e )
                {
                 //  Toast.makeText(this,"Exeption in inserting "+e,Toast.LENGTH_SHORT).show();
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
