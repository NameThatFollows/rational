package edu.gatech.cs2340.gtrational.rational.controller;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.cs2340.gtrational.rational.R;
import edu.gatech.cs2340.gtrational.rational.model.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Verifies if the login information the user inputs is valid.
     * @param view The View object.
     */
    public void verifyLogin(View view) {
        EditText usernameField = (EditText) findViewById((R.id.editTextUsername));
        EditText passwordField = (EditText) findViewById((R.id.editTextPassword));

        EditText[] requiredFields = {usernameField, passwordField};
        String[] messages = {"Please enter username", "Please enter password"};

        //Display toast if one of the required fields is left blank
        for (int i = 0; i < requiredFields.length; i++) {
            EditText field = requiredFields[i];
            if (field.getText().toString().length() == 0) {
                int[] loc = new int[2];
                field.getLocationOnScreen(loc);

                Toast t = Toast.makeText(getApplicationContext(), messages[i], Toast.LENGTH_SHORT);

                //Display toast on right of screen at the y value of the input field
                t.setGravity(Gravity.TOP | Gravity.RIGHT, 0, loc[1] - field.getHeight() / 2 - 20);
                t.show();
                return;
            }
        }

        //Hide keyboard if open
        View focused = getCurrentFocus();
        if (focused != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(focused.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        User user = new User(username, password);
        if (user.login()) {
            Intent intent = new Intent(this, MainDashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Log.d("Tag", "YOU DUN MESSED UP AY AY RON");
            Snackbar snackbar = Snackbar.make(view, "Login Failed", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    /**
     * If user cancels login, login is terminated and activity finishes.
     * @param view The View object
     */
    public void cancelLogin(View view) {
        finish();
    }
}
