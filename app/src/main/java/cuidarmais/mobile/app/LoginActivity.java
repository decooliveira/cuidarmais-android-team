package cuidarmais.mobile.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cuidarmais.mobile.service.API;
import cuidarmais.mobile.service.TokenSaver;
import cuidarmais.mobile.service.Connector;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    private JsonObjectRequest requestObject;
    private RequestQueue requestQueue;


    // UI references.
    private TextView usernameView;
    private EditText passwordView;


    Map<String, String> jsonParams = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        String token = TokenSaver.getToken(getApplicationContext());
        Log.v(" STORED TOKEN", token);

        if(validateToken(token)){
            openApp();
        }
        requestQueue = Volley.newRequestQueue(this);

        //method that hides soft keyboard when it loses focus
        findViewById(R.id.login_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                return true;
            }
        });


        // Set up the login form.
        usernameView = (TextView) findViewById(R.id.username);

        usernameView.setSelected(false);
        passwordView= (EditText) findViewById(R.id.password);
        passwordView.setSelected(false);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    authenticate();
                    return true;
                }
                return false;
            }
        });

        Button loginBtn = (Button) findViewById(R.id.email_sign_in_button);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();
            }
        });


    }







    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void authenticate() {

        String url = API.url+"authenticate";

        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        jsonParams.put("username", username);
        jsonParams.put("rememberMe","true");
        jsonParams.put("password", password);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String token = response.getString("id_token");
                            TokenSaver.setToken(getApplicationContext(),token);
                            openApp();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Login fail: ",error.toString());
                Toast.makeText(LoginActivity.this, "Verifique seu nome de usuário senha ", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };

        requestQueue.add(request);


        //Intent intent = new Intent(this,MainActivity.class);
        //startActivity(intent);


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean validateToken(String token){
        if(!token.isEmpty()){

            Context context = getApplicationContext();
            Connector connector = new Connector(context);
            return connector.validateToken();


        }
        return false;
    }

    private void openApp(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }






}

