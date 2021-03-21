package jennie.umn.ac.musicplayer.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jennie.umn.ac.musicplayer.ListLagu.LaguActivity;
import jennie.umn.ac.musicplayer.MainActivity;
import jennie.umn.ac.musicplayer.R;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsername.getText().toString().equals("uasmobile") && etPassword.getText().toString().equals("uasmobilegenap")){
                    //bikin Intent baru kalo pass ama unamenya keterima
                    Intent laguInt = new Intent(LoginActivity.this, LaguActivity.class);
                    startActivity(laguInt);
                }
                else{
                    //toast kasih tau salah
                    Log.e("Uname : " + etUsername.getText().toString(), "Pass : " + etPassword.getText().toString());
                    Context context = getApplicationContext();
                    CharSequence text = "Wrong Username/Password!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}