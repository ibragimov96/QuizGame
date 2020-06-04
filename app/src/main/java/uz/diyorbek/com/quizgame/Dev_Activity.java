package uz.diyorbek.com.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Dev_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);

        Toast.makeText(this, "Greetings, here you can find out about me!", Toast.LENGTH_SHORT).show();
    }
}
