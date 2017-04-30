package com.example.kishore.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.GONE;

/**
 * Created by KISHORE on 30-03-2017.
 */

public class LoginActivity extends AppCompatActivity {
    LinearLayout root;
    EditText userName;
    EditText pasword;
    Button submit;
    TextInputLayout emailContainer;
    EditText email;
    TextView registerHere;
    enum Mode{
        REGISTER,LOGIN
    }
    Mode viewMode = Mode.LOGIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        root = (LinearLayout)findViewById(R.id.root);

        userName = (EditText) findViewById(R.id.user_name);
        pasword = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        registerHere = (TextView)findViewById(R.id.register_here);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewMode();
            }
        });
    }
    private void onSubmit(){
      if(viewMode == Mode.LOGIN){
        doLogin();
      }else {
        doRegister();
      }
    }
    private void doLogin(){
        NetworkConnection.getInstance(getApplicationContext()).sendGetRequest(
                "http://10.0.2.2:80/sentiment/login.php?uname="+userName.getText().toString()+"&pas="+
                        pasword.getText().toString(),
                new NetworkHandler() {
                    @Override
                    public void onSuccess(String response) {

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }

    private void doRegister(){
        NetworkConnection.getInstance(getApplicationContext()).sendGetRequest(
                "http://10.0.2.2:80/sentiment/reg.php?uname="+userName.getText().toString()+"&pas="+
                        pasword.getText().toString()+"&email="+email.getText().toString(),
                new NetworkHandler() {
                    @Override
                    public void onSuccess(String response) {

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }
    private void setViewMode(){
        if(viewMode == Mode.LOGIN){
            viewMode = Mode.REGISTER;
            emailContainer = addEmailContainer();
            email = addEmailEditText(emailContainer);
            registerHere.setText("Already Registered ? Login");
            submit.setText("Register");
        }else {
            viewMode = Mode.LOGIN;
            registerHere.setText("Register Here");
            submit.setText("Login");
            removeView(emailContainer);
        }
    }

    private TextInputLayout addEmailContainer(){
        TextInputLayout textInputLayout = new TextInputLayout(this);
        textInputLayout.setHint("Email");
        textInputLayout.setHintAnimationEnabled(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,Util.dpToPx(10,getResources()),0,0);
        textInputLayout.setLayoutParams(layoutParams);
        root.addView(textInputLayout,2);
        return textInputLayout;
    }
    private EditText addEmailEditText(TextInputLayout textInputLayout){
        EditText email = new EditText(this);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setPadding(Util.dpToPx(15,getResources()),email.getPaddingTop(),email.getPaddingRight(),email.getPaddingBottom());
        textInputLayout.addView(email);
        return email;
    }
    private void removeView(View v){
        v.setVisibility(GONE);
    }

}

