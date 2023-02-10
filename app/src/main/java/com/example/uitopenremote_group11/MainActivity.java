package com.example.uitopenremote_group11;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.uitopenremote_group11.Helper.LoginHelper;
import com.example.uitopenremote_group11.Helper.Wa1_helper;
import com.example.uitopenremote_group11.Helper.Wa2_helper;
import com.example.uitopenremote_group11.Helper.Wa3_helper;
import com.example.uitopenremote_group11.map.Map;
import com.example.uitopenremote_group11.map.Map_default;
import com.example.uitopenremote_group11.map.Map_options;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.views.MapView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;

    Wa1_helper wa1_db = new Wa1_helper(this);
    Wa2_helper wa2_db = new Wa2_helper(this);
    Wa3_helper wa3_db = new Wa3_helper(this);
    LoginHelper login_db = new LoginHelper(this);
    LoginHelper create_user_db = new LoginHelper(this);

    MapView mapView;
    IMapController mapController;
    Button signinButton;
    Button signupButton;
    EditText username_ET, password_ET, signup_username, signup_password, signup_confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.my_orange));

        signinButton = findViewById(R.id.signinButton);
        signupButton = findViewById(R.id.signupButton);

        username_ET = findViewById(R.id.username);
        password_ET = findViewById(R.id.password);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Map> mapCall = apiInterface.getMap();
        mapCall.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Gson gson = new Gson();
                Map map = response.body();
                String json = gson.toJson(map.options);
                Map_options optionsObj = gson.fromJson(json, Map_options.class);
                json = gson.toJson(optionsObj._default);
                Map_default defaultObj = gson.fromJson(json, Map_default.class);

                float map_longitude = defaultObj.center[0];
                float map_latitude = defaultObj.center[1];

                signinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                        intent.putExtra("map_latitude", map_latitude);
                        intent.putExtra("map_longitude", map_longitude);

                        String username = username_ET.getText().toString();
                        String password = password_ET.getText().toString();

                        intent.putExtra("username", username);

                        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
                            Toast.makeText(MainActivity.this, "You need to enter all of fields", Toast.LENGTH_SHORT).show();
                        else{
                            Boolean checkuserpass = login_db.checkUsernamePassword(username,password);
                            if (checkuserpass==true){
                                Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(MainActivity.this, "Login Failed, username or password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DialogSignup();
            }
        });
    }

    private void DialogSignup(){
        Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_sign_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        signup_username = dialog.findViewById(R.id.signup_username);
        signup_password = dialog.findViewById(R.id.signup_password);
        signup_confirmPassword = dialog.findViewById(R.id.signup_confirmPassword);

        Button createButton = dialog.findViewById(R.id.createButton);
        ImageView closeButton = dialog.findViewById(R.id.closeButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = signup_username.getText().toString();
                String password = signup_password.getText().toString();
                String confirmPassword = signup_confirmPassword.getText().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword))
                    Toast.makeText(MainActivity.this, "You need to enter all of fields", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkusername = create_user_db.checkUsername(username);
                        if(checkusername==false){
                            Boolean insert = create_user_db.inserUserData(username, password);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(MainActivity.this, "Password and Confirm password are not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
}