package com.example.nsmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText nsm_id, nsm_pass;
    private Button btn_login;
    private static String URL_LOGIN ="http://192.168.43.238/NSM/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        nsm_id = findViewById(R.id.nsm_id);
        nsm_pass = findViewById(R.id.nsm_pass);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emp_id = nsm_id.getText().toString().trim();
                String emp_pass = nsm_pass.getText().toString().trim();

                if (!emp_id.isEmpty() || !emp_pass.isEmpty()) {
                    Login(emp_id,emp_pass);
                } else {
                    nsm_id.setError("Please insert email");
                    nsm_pass.setError("please insert password");
                }

            }
        });
    }

    private void Login(final String emp_id, final String emp_pass){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for (int i=0; i < jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String empid = object.getString("emp_code").trim();
                                    String empfristname = object.getString("emp_fristname").trim();
                                    String emplastname = object.getString("emp_lastname").trim();
                                    String empemail = object.getString("emp_email").trim();
                                    String emptel = object.getString("emp_tel").trim();
                                    String uselevel = object.getString("userlevel").trim();

                                    sessionManager.createSession(empid, empfristname, emplastname, empemail, emptel, uselevel);
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);

                                }
                            }else {
                                Toast.makeText(MainActivity.this, "รหัสพนักงานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "JSONExceptionError : "+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "VolleyError : "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("emp_id",emp_id);
                params.put("emp_pass",emp_pass);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
