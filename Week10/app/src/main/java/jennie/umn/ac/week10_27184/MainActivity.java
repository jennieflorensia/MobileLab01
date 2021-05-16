package jennie.umn.ac.week10_27184;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jennie.umn.ac.week10_27184.Tuto1.AsynctaskActivity;
import jennie.umn.ac.week10_27184.Tuto2.AsyncTaskLoaderActivity;
import jennie.umn.ac.week10_27184.Tuto3.IntentServiceActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsynctaskActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncTaskLoaderActivity.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentServiceActivity.class));
            }
        });

        //startActivity(new Intent(this, AsynctaskActivity.class));
        //startActivity(new Intent(this, AsyncTaskLoaderActivity.class));
        //startActivity(new Intent(this, IntentServiceActivity.class));
    }
}