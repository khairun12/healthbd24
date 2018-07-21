package com.peoplentech.devkh.healthbd24;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by User on 3/24/2018.
 */

public class RegisterFragment extends Fragment {

    EditText usrName, usrEmail, usrPassword, usrBlood, usrPhone, userAddress;
    Button regAsUser;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Progress dialog
        pDialog = new ProgressDialog(this.getActivity().getWindow().getContext());
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Toast.makeText(getActivity().getApplicationContext(), "Already Logged In", Toast.LENGTH_SHORT).show();
        }

        usrName = (EditText) getView().findViewById(R.id.userName);
        usrEmail = (EditText) getView().findViewById(R.id.userEmail);
        usrPassword = (EditText) getView().findViewById(R.id.userPassword);
        userAddress = (EditText) getView().findViewById(R.id.userAddress);
        usrPhone = (EditText) getView().findViewById(R.id.userMobile);
        usrBlood = (EditText) getView().findViewById(R.id.userBlood);
        regAsUser = (Button) getView().findViewById(R.id.btnRegister);

        regAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = usrName.getText().toString().trim();
                String address = userAddress.getText().toString().trim();
                String phone = usrPhone.getText().toString().trim();
                String blood = usrBlood.getText().toString().trim();
                String email = usrEmail.getText().toString().trim();
                String password = usrPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !address.isEmpty()
                        && !phone.isEmpty() && !blood.isEmpty()) {
                    registerUser(name, email, address, phone, blood, password);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }


            }
        });
    }

    private void registerUser(final String name, final String email, final String address, final String phone,
                              final String blood, final String password ) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);

                        Toast.makeText(getActivity().getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                getActivity().getApplicationContext(),
                                ShowDoctorActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getActivity().getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("address", address);
                params.put("number", phone);
                params.put("blood_group", blood);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}