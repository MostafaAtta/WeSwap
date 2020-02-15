package com.atta.weswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.atta.weswap.model.User;
import com.atta.weswap.presenters.RegisterContract;
import com.atta.weswap.presenters.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener {

    // login button
    Button register;

    EditText emailText, passwordText, nameText, phoneText, confirmPasswordText;

    ProgressBar progressBar;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this, this);

        initiateViews();
    }

    private void initiateViews() {

        // National ID, Password input text
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        nameText = findViewById(R.id.name);
        phoneText = findViewById(R.id.phone);
        confirmPasswordText = findViewById(R.id.password_confirm);
        // Register button
        register = findViewById(R.id.button);
        register.setOnClickListener(this);

        progressBar = findViewById(R.id.progress_bar);

    }


    @Override
    public void showMessage(String error) {

        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showViewError(String view, String oldView, String error) {

        int id = getResources().getIdentifier(view, "id", this.getPackageName());
        EditText editText = findViewById(id);
        if (oldView != null) {
            int oldIdd = getResources().getIdentifier(oldView, "id", this.getPackageName());
            EditText oldEditText = findViewById(oldIdd);
            oldEditText.setError(null);
        }
        editText.requestFocus();
        editText.setError(error);
    }

    @Override
    public void navigateToMain() {

        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public boolean validate(String name, String email, String password, String passwordConfirm, String phone) {
        boolean valid = true;

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (name.isEmpty()) {
            showViewError("name",null,"Enter your name");
            valid = false;
        } else if (!email.matches(emailPattern) || email.isEmpty()) {
            showViewError("email","name","Invalid email address");
            valid = false;

        }else if (phone.length() != 11) {
            showViewError("phone","email","Enter a valid Phone number");
            valid = false;
        }else if (password.isEmpty() || password.length() < 4 || password.length() > 10) {

            showViewError("password","phone","password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else if (passwordConfirm.isEmpty() || passwordConfirm.length() < 4 || passwordConfirm.length() > 10 ) {

            showViewError("password_confirm","phone","password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else if (!password.equals(passwordConfirm)){

            showViewError("password","phone","passwords not Matched");
            valid = false;
        }



        return valid;
    }
    @Override
    public void onClick(View view) {
        if (view == register) {
            if (!validate(nameText.getText().toString(), emailText.getText().toString(), passwordText.getText().toString(),
                    confirmPasswordText.getText().toString(), phoneText.getText().toString())) {
                register.setEnabled(true);
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //Defining the user object as we need to pass it with the call
            User user = new User(nameText.getText().toString(), emailText.getText().toString(), passwordText.getText().toString(),
                    phoneText.getText().toString());


            registerPresenter.register(user);


        }
    }
}
