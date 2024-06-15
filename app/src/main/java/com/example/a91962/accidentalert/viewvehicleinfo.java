package com.example.a91962.accidentalert;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class viewvehicleinfo extends AppCompatActivity {



        String user;
        String s1,s2,s3,s4,s5;



        Cursor c;
        TableRow tbrow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewattn );

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);



        TableRow tbrow0 = new TableRow(this);
        TextView t0 = new TextView(this);
        t0.setText( " Vehicle No ");
        t0.setTextColor(Color.WHITE);
        tbrow0.addView(t0);

        TextView tv0 = new TextView(this);
        tv0.setText("  Name ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText(" DOB ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);

        final TextView tv2 = new TextView(this);
        tv2.setText("  Address  ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);

        final TextView tv3 = new TextView(this);
        tv3.setText("  B_Group  ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);





        stk.addView(tbrow0);




        SQLiteDatabase db=openOrCreateDatabase("Alert", Context.MODE_PRIVATE,null);
        try{
            if(db!=null)
            {

              //  String q1="CREATE TABLE vehicleinfo(vehid TEXT PRIMARY KEY,name TEXT,gender TEXT,day INTEGER,month INTEGER,year INTEGER,phone TEXT,classs TEXT,address TEXT,password TEXT,dept TEXT);";


                c=db.rawQuery("SELECT * from vehicleinfo",null);
                if(c.moveToFirst())
                {
                    do{
                            s1=c.getString(c.getColumnIndexOrThrow("vehid"));
                            s2=c.getString(c.getColumnIndexOrThrow("name"));
                            s3=c.getString(c.getColumnIndexOrThrow("phone"));
                            s4=c.getString(c.getColumnIndexOrThrow("address"));
                            s5=c.getString(c.getColumnIndexOrThrow("gender"));



                        StringBuilder sb=new StringBuilder();
                            sb.append(" ");

                            tbrow = new TableRow(this);
                            try {
                                TextView tv = new TextView(this);


                                tv.setText(s1);
                                tv.setTextColor(Color.YELLOW);



                                tv.setGravity(Gravity.CENTER);
                                tbrow.addView(tv);

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(this,"Exception row 1"+e,Toast.LENGTH_SHORT).show();
                            }


                            try {
                                TextView t1v = new TextView(this);
                                t1v.setText(s2);
                                t1v.setTextColor(Color.YELLOW);

                                t1v.setGravity(Gravity.CENTER);
                                tbrow.addView(t1v);

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(this,"Exception row 1"+e,Toast.LENGTH_SHORT).show();
                            }
                            try{


                                TextView t2v = new TextView(this);
                                t2v.setText(" "+s3);
                                t2v.setTextColor(Color.YELLOW);
                                t2v.setGravity(Gravity.CENTER);
                                tbrow.addView(t2v);

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(this,"Exception row 2"+e,Toast.LENGTH_SHORT).show();
                            }

                            try{
                                TextView t3v = new TextView(this);
                                t3v.setText(s4);
                                t3v.setTextColor(Color.YELLOW);
                                t3v.setGravity(Gravity.CENTER);
                                tbrow.addView(t3v);

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(this,"Exception row 3"+e,Toast.LENGTH_SHORT).show();
                            }
                        try{
                            TextView t4v = new TextView(this);
                            t4v.setText(s5);
                            t4v.setTextColor(Color.YELLOW);
                            t4v.setGravity(Gravity.CENTER);
                            tbrow.addView(t4v);

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(this,"Exception row 3"+e,Toast.LENGTH_SHORT).show();
                        }








                        try{
                                stk.addView(tbrow);
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(this,"Exception rc"+e,Toast.LENGTH_SHORT).show();
                            }




                    }while(c.moveToNext());
                }

            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Exception in extracting from db",Toast.LENGTH_SHORT).show();
        }
    }
    }

