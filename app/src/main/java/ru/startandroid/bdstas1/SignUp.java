package ru.startandroid.bdstas1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText login, pass1, pass2, email;

    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        btnSignUp = findViewById(R.id.signUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(login.getText().toString()) || !TextUtils.isEmpty(pass1.getText().toString()) || !TextUtils.isEmpty(pass2.getText().toString())|| !TextUtils.isEmpty(email.getText().toString())){
                    //if(pass1.getText().toString() == pass2.getText().toString()){
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setUsername(login.getText().toString());
                    registerRequest.setPassword(pass1.getText().toString());
                    registerRequest.setEmail(email.getText().toString());
                    signUpUser(registerRequest);
                    //}
                    //else{
                    //    Toast.makeText(SignUp.this, "Passwords are not equals", Toast.LENGTH_LONG).show();
                    //}
                }
                else{
                    Toast.makeText(SignUp.this, "Fields are empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void init(){
        login = findViewById(R.id.loginUp);
        pass1 = findViewById(R.id.passOneUp);
        pass2 = findViewById(R.id.passTwoUp);
        email = findViewById(R.id.email);
    }
    public void onClickGoSignIn(View view){
        startActivity(new Intent(SignUp.this, SignIn.class));
    }
    public void signUpUser(RegisterRequest registerRequest){

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful()){
                    String message = "Successful ...";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();

                    startActivity(new Intent(SignUp.this, MainActivity.class));
                    finish();

                }
                else{
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
