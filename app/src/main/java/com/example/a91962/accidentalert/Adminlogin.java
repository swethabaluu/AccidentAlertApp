package com.example.a91962.accidentalert;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {

    EditText e1,e2;
    int fg=0,fg1=0;
    String user,pass;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        b3 = (Button)findViewById(R.id.button3);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Adminlogin.this,MainActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fg=0;
                if(e1.length()==0) {
                    fg = 1;
                    e1.setHint("Username");
                    e1.setHintTextColor(Color.RED);


                }
                fg1=0;
                if(e2.length()==0) {
                    fg1 = 1;
                    e2.setHint("Password");
                    e2.setHintTextColor(Color.RED);


                }
                if(fg==0 && fg1==0)
                {
                    user=e1.getText().toString();
                    pass=e2.getText().toString();
                    if(user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin"))
                    {
                        Toast.makeText(getApplicationContext(),"Login successfull.",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Adminlogin.this,Adminpage.class);
                        startActivity(i);
                    }
                    else
                    {
                        e1.setHint("Username ");
                        e2.setHint("Password");
                        fg=1;
                        Toast.makeText(getApplicationContext(),"Invalid username/password.",Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter the user name and password",Toast.LENGTH_LONG).show();
                }
    
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            Toast.makeText(this, "Back key pressed", Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
