package jennie.umn.ac.w04b_27184;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etNama;
    Button btnHal1, btnHal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNama = findViewById(R.id.nama);
        btnHal1 = findViewById(R.id.hal1);
        btnHal2 = findViewById(R.id.hal2);

        btnHal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainDua = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intentMainDua);
            }
        });

        btnHal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainTiga = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intentMainTiga);
            }
        });
    }
}