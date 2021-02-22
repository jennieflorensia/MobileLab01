package umn.ac.id;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText angka1, angka2;
    TextView result;
    Button btnTambah, btnKurang, btnKali, btnBagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        angka1 = (EditText) this.findViewById(R.id.angka1);
        angka2 = (EditText) this.findViewById(R.id.angka2);
        result = (TextView) this.findViewById(R.id.result);
        btnTambah = (Button) this.findViewById(R.id.btnTambah);
        btnKurang = (Button) this.findViewById(R.id.btnKurang);
        btnKali = (Button) this.findViewById(R.id.btnKali);
        btnBagi = (Button) this.findViewById(R.id.btnBagi);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(angka1.getText().toString().matches("") || angka2.getText().toString().matches("")){
                    result.setText("All fields must be filled!");
                }else{
                    hitung('+');
                }
            }
        });

        btnKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(angka1.getText().toString().matches("") || angka2.getText().toString().matches("")){
                    result.setText("All fields must be filled!");
                }else{
                    hitung('-');
                }
            }
        });

        btnKali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(angka1.getText().toString().matches("") || angka2.getText().toString().matches("")){
                    result.setText("All fields must be filled!");
                }else{
                    hitung('*');
                }
            }
        });

        btnBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(angka1.getText().toString().matches("") || angka2.getText().toString().matches("")){
                    result.setText("All fields must be filled!");
                }else{
                    hitung('/');
                }
            }
        });
    }

    protected void hitung(char operator)
    {
        double num1 = Double.parseDouble(angka1.getText().toString());
        double num2 = Double.parseDouble(angka2.getText().toString());

        double hasil = 0.0;


        switch (operator){
            case('+') : hasil = num1 + num2; break;
            case('-') : hasil = num1 - num2; break;
            case('*') : hasil = num1 * num2; break;
            case('/') : hasil = num1 / num2; break;
        }
        result.setText(String.valueOf(hasil));
    }
}