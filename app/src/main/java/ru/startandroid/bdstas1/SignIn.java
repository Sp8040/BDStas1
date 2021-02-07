package ru.startandroid.bdstas1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    TextView login, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        login = findViewById(R.id.loginIn);
        pass = findViewById(R.id.passIn);

    }
    public void onClickGoSignUp(View view){
        startActivity(new Intent(SignIn.this, SignUp.class));
    }
    public void onClickSignIn(View view){
        if(!TextUtils.isEmpty(login.getText().toString()) || !TextUtils.isEmpty(pass.getText().toString())){
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPassword(pass.getText().toString());
            loginRequest.setUsername(login.getText().toString());
            loginUser(loginRequest);
        }
        else{
            Toast.makeText(SignIn.this, "Fields are empty", Toast.LENGTH_LONG).show();
        }
    }
    public void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(SignIn.this, MainActivity.class).putExtra("data", loginResponse));
                    finish();
                }
                else{
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(SignIn.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(SignIn.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
