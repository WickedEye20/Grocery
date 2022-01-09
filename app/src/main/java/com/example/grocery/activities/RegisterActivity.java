package com.example.grocery.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.R;
import com.example.grocery.Users;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText regName, regUser, regEmail, regPhone, regPass;
    TextView login;
    Button regBtn;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase grocery;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://grocery-store-22-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regName = (TextInputEditText) findViewById(R.id.regN);
        regUser = (TextInputEditText) findViewById(R.id.user);
        regEmail = (TextInputEditText) findViewById(R.id.email);
        regPass = (TextInputEditText) findViewById(R.id.pass);
        regPhone = (TextInputEditText) findViewById(R.id.phone);
        regBtn = (Button) findViewById(R.id.regBtn);
        login = (TextView) findViewById(R.id.login);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//
//        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                //Intent is used to switch from one activity to another.
                startActivity(i);
                //invoke the SecondActivity.


            }
        });

//        regBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = regEmail.getText().toString().trim();
//                String pass = regPass.getText().toString().trim();
//
//                if (!validateName() || !validateEmail() || !validatePhoneNumber() || !validatePassword()) {
//                    return;
//                }
//
//                //User Registration
//                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                        }
//                        else {
//                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
        regUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String val = regUser.getText().toString();
                String spaces = "^[a-zA-Z0-9._]+$"; //check spaces and 5 characters

                if (val.isEmpty()) {
                    regUser.setError("Field can not be empty");

                } else if (val.length() > 20) {
                    regUser.setError("Username is too large!");

                } else if (!val.matches(spaces)) {
                    regUser.setError("Please enter username without spaces and should be of 5 characters!");

                } else {
                    regUser.setError(null);
                }

            }
        });
        regName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String val = regName.getText().toString().trim();
                if (val.isEmpty()) {
                    regName.setError("Field can not be empty");

                } else {
                    regName.setError(null);
                }

            }
        });
        regEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = regEmail.getText().toString().trim();
                String checkEmail = "^[a-zA-Z0-9+_.-]+@[a-zA-Z.-]+$"; //email regex
                String e = "^[a-zA-Z0-9+_.-]+@[@a-zA-Z.-]+$";

                if (email.isEmpty()) {
                    regEmail.setError("Field can not be empty");

                } else if (email.matches(checkEmail)) {
                    regEmail.setError(null);
                } else if (email.matches(e)) {
                    regEmail.setError("invalid");
                } else {
                    regEmail.setError(null);
                }

            }
        });
        regPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pass = regPass.getText().toString().trim();
                String checkPassword = "^(?=.*[0-9])"
                        + "(?=.*[a-zA-Z])"
                        + "(?=\\S+$).{4,}$"; //regPass should contain 4 alphanumeric

                if (pass.isEmpty()) {
                    regPass.setError("Field can not be empty");

                } else if (!pass.matches(checkPassword)) {
                    regPass.setError("Password should contain 4 alphanumeric characters!");

                } else {
                    regPass.setError(null);
                }

            }
        });
        regPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String val = regPhone.getText().toString().trim();
                String spaces = "^[0-9]{10,13}$";
                if (val.isEmpty()) {
                    regPhone.setError("Enter valid phone number");

                } else if (!val.matches(spaces)) {
                    regPhone.setError("No White spaces are allowed!");

                } else {
                    regPhone.setError(null);
                }

            }
        });


        //Store users
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                grocery = FirebaseDatabase.getInstance();
//                databaseReference = grocery.getReference("users");

                String name = regName.getText().toString();
                String username = regUser.getText().toString();
                String email = regEmail.getText().toString();
                String phoneNo = regPhone.getText().toString();
                String password = regPass.getText().toString();

                if (!validateName() || !validateUsername() || !validateEmail() || !validatePhoneNumber() || !validatePassword()) {
                    return;
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //to check phone number already exist in database
                            if (snapshot.hasChild(phoneNo)) {
                                Toast.makeText(RegisterActivity.this, "Phone Number Already Registered", Toast.LENGTH_SHORT).show();
                            } else if (snapshot.hasChild(username)) {
                                Toast.makeText(RegisterActivity.this, "Use different username", Toast.LENGTH_SHORT).show();
                            } else {
                                Users user = new Users(name, username, email, phoneNo, password);

                                //Store by Unique name
                                databaseReference.child("users").child(username).setValue(user);

                                //Show message
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
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

    //Validations
    private boolean validateName() {
        String val = regName.getText().toString().trim();
        if (val.isEmpty()) {
            regName.setError("Field can not be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateUsername() {
        String val = regUser.getText().toString();
        String spaces = "^[a-z0-9\\\\._]{5,20}$"; //check spaces and 5 characters

        if (val.isEmpty()) {
            regUser.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            regUser.setError("Username is too large!");
            return false;
        } else if (!val.matches(spaces)) {
            regUser.setError("Please enter username without spaces and should be of 5 characters!");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail() {
        String email = regEmail.getText().toString().trim();
        String checkEmail = "^[a-zA-Z0-9+_.-]+@[a-zA-Z.-]+$"; //email regex

        if (email.isEmpty()) {
            regEmail.setError("Field can not be empty");
            return false;
        } else if (!email.matches(checkEmail)) {
            regEmail.setError("Invalid Email!");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhoneNumber() {
        String val = regPhone.getText().toString().trim();
        String spaces = "^[0-9]{10,13}$";
        if (val.isEmpty()) {
            regPhone.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(spaces)) {
            regPhone.setError("No White spaces are allowed!");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        String pass = regPass.getText().toString().trim();
        String checkPassword = "^(?=.*[0-9])"
                + "(?=.*[a-zA-Z])"
                + "(?=\\S+$).{4,}$"; //regPass should contain 4 alphanumeric

        if (pass.isEmpty()) {
            regPass.setError("Field can not be empty");
            return false;
        } else if (!pass.matches(checkPassword)) {
            regPass.setError("Password should contain 4 alphanumeric characters!");
            return false;
        } else {
            return true;
        }
    }


}


