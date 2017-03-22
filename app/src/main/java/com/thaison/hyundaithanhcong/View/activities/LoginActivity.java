package com.thaison.hyundaithanhcong.View.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thaison.hyundaithanhcong.Model.HttpHandler;
import com.thaison.hyundaithanhcong.Model.LoadData;
import com.thaison.hyundaithanhcong.R;
import com.thaison.hyundaithanhcong.controler.item.CarColor;
import com.thaison.hyundaithanhcong.controler.item.CarFeature;
import com.thaison.hyundaithanhcong.controler.item.CarItem;
import com.thaison.hyundaithanhcong.controler.item.CarLogo;
import com.thaison.hyundaithanhcong.controler.item.CarParameter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String URL = "http://htcmobile.netdeal.vn/services/login";
    private static final String CAR_ID = "CAR ID";
    private static final String LIST_CAR = "LIST CAR";
    private static final String LIST_CAR_LOGO = "LIST CAR LOGO";
    private static final String LIST_CAR_COLOR = "LIST CAR COLOR";
    private static final String LIST_CAR_FEATURE = "LIST CAR FEATURE";
    private static final String LIST_CAR_PARAMETER = "LIST CAR PARAMETERS";
//    private static final String URL = "http://api.androidhive.info/contacts/";

    private String username;
    private String password;

    private EditText edtLoginUsername;
    private EditText edtLoginPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private Intent intent;
    private Bundle bundle;

    private ArrayList<CarItem> cars;
    private ArrayList<CarLogo> carLogos;
    private ArrayList<CarColor> carColors;
    private ArrayList<CarFeature> carFeatures;
    private ArrayList<CarParameter> carParameters;
    private LoadData loadData;
    private boolean isLoadCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        initViews();
    }

    private void initData() {
        loadData = new LoadData(this);
    }

    private void initViews() {
        edtLoginUsername = (EditText) findViewById(R.id.edt_login_username);
        edtLoginPassword = (EditText) findViewById(R.id.edt_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        username = edtLoginUsername.getText().toString();
        password = edtLoginPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and password cannot be empty.", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject jSonObj = new JSONObject();
            try {
                jSonObj.put("user name", username);
                jSonObj.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new GetLoginData().execute(jSonObj);

            // start next activity

            cars = loadData.getCars();
            carLogos = loadData.getCarLogos();
            carColors = loadData.getCarColors();
            carFeatures = loadData.getCarFeatures();
            carParameters = loadData.getCarParameters();
            bundle = new Bundle();
            bundle.putSerializable(LIST_CAR, cars);
            bundle.putSerializable(LIST_CAR_LOGO, carLogos);
            bundle.putSerializable(LIST_CAR_COLOR, carColors);
            bundle.putSerializable(LIST_CAR_FEATURE, carFeatures);
            bundle.putSerializable(LIST_CAR_PARAMETER, carParameters);
            intent = new Intent(this, HomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private class GetLoginData extends AsyncTask<JSONObject, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Đang đăng nhập");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(JSONObject... params) {
            HttpHandler handler = new HttpHandler();

            String jsonString = handler.makeServiceCall(URL);
            Log.i(TAG, "JSON String:\n" + jsonString);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
