package jennie.umn.ac.week06_27184;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jennie.umn.ac.week06_27184.anim_drawable.DrawableActivity;
import jennie.umn.ac.week06_27184.anim_physic.PhysicActivity;
import jennie.umn.ac.week06_27184.anim_properti.PropertyAvtivity;

public class MainActivity extends AppCompatActivity {

    Button btnAnim1, btnAnim2, btnAnim3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAnim1 = findViewById(R.id.animasi_1);
        btnAnim2 = findViewById(R.id.animasi_2);
        btnAnim3 = findViewById(R.id.animasi_3);

        btnAnim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animPropInt = new Intent(MainActivity.this, PropertyAvtivity.class);
                startActivity(animPropInt);
            }
        });

        btnAnim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animDrawableInt = new Intent(MainActivity.this, DrawableActivity.class);
                startActivity(animDrawableInt);
            }
        });

        btnAnim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animPhysicInt = new Intent(MainActivity.this, PhysicActivity.class);
                startActivity(animPhysicInt);
            }
        });
    }
}