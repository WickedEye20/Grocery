package com.example.grocery.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.R;
import com.example.grocery.SessionsManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    TextView register;
    Button btnLogin;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://grocery-store-22-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.custId);
        password = (EditText) findViewById(R.id.custPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        register = (TextView) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                //Intent is used to switch from one activity to another.
                startActivity(i);
                //invoke the SecondActivity.
                finish();

            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String val = username.getText().toString();
                String spaces = "^[a-z0-9._-]+$"; //check spaces and 5 characters

                if (val.isEmpty()) {
                    username.setError("Field can not be empty");

                } else if (val.length() > 20) {
                    username.setError("Username is too large!");

                } else if (!val.matches(spaces)) {
                    username.setError("Please enter username without spaces and should be of 5 characters!");

                } else {
                    username.setError(null);
                }

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String val = password.getText().toString().trim();
                String checkPassword = "^(?=.*[0-9])"
                        + "(?=.*[a-zA-Z])"
                        + "(?=\\S+$).{4,}$"; //password should contain 4 alphanumeric

                if (val.isEmpty()) {
                    password.setError("Field can not be empty");

                } else if (!val.matches(checkPassword)) {
                    password.setError(null);

                } else {
                    password.setError(null);
                }

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();

                String pass = password.getText().toString();

                if (!validateUsername() || !validatePassword()) {
                    return;
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //to check if user exist in database
                            if (snapshot.hasChild(user)){

                                String getPass = snapshot.child(user).child("password").getValue(String.class);
//                                String getEPass = snapshot.child(email).child("password").getValue(String.class);

                                if (getPass.equals(pass)){

                                    SessionsManager sessionsManager = new SessionsManager(LoginActivity.this);
                                    sessionsManager.createLoginSession(user, pass);

                                    Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Incorrect Username", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    private boolean validateUsername() {
        String val = username.getText().toString();
        String spaces = "^[a-z0-9\\\\._-]{5,20}$"; //check spaces and 5 characters

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too large!");
            return false;
        } else if (!val.matches(spaces)) {
            username.setError("Please enter username without spaces and should be of 5 characters!");
            return false;
        } else {
            return true;
        }
    }




    private boolean validatePassword() {
        String val = password.getText().toString().trim();
        String checkPassword = "^(?=.*[0-9])"
                + "(?=.*[a-zA-Z])"
                + "(?=\\S+$).{4,}$"; //password should contain 4 alphanumeric

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 alphanumeric characters!");
            return false;
        } else {
            return true;
        }
    }

}
