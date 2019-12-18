package com.atta.weswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.atta.weswap.presenters.LoginContract;
import com.atta.weswap.presenters.LoginPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {


    TextInputEditText emailText, passwordText;

    TextView newAccount;

    Button login;

    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initiateViews();

        loginPresenter = new LoginPresenter(this, this);


    }

    private void initiateViews() {

        emailText = findViewById(R.id.email);
        passwordText  = findViewById(R.id.password);
        login  = findViewById(R.id.login_btn);
        newAccount  = findViewById(R.id.to_register);

        newAccount.setOnClickListener(this);
        login.setOnClickListener(this);

    }


    @Override
    public void showError(String error) {

        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        login.setEnabled(true);
    }

    @Override
    public void showViewError(String view, String error) {

        int id = getResources().getIdentifier(view, "id", this.getPackageName());
        EditText editText = findViewById(id);
        editText.setError(error);
    }

    @Override
    public void showMessage() {

        Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToMain() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToRegister() {

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean validate(String email, String password) {


        boolean valid = true;
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!email.matches(emailPattern) || email.isEmpty())
        {
            showError("Invalid email address");
            showViewError("email","Invalid email address");
            valid = false;

        }else {

            showViewError("email",null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            showError("password must be between 4 and 10 alphanumeric characters");
            showViewError("password","password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            showViewError("password",null);
        }
        return valid;
    }

    @Override
    public void onClick(View view) {
        if (view == login) {
            if (!validate(emailText.getText().toString().trim(), passwordText.getText().toString())) {
                showError("Invalid Login details");
                return;
            }

            //progressDialog.show();
            loginPresenter.login(emailText.getText().toString(), passwordText.getText().toString());
        } else if (view == newAccount) {

            navigateToRegister();

        }
    }
}
