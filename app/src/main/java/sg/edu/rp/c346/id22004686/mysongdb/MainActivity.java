package sg.edu.rp.c346.id22004686.mysongdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button insert,getsong;
    EditText title,singer,year;
    RadioGroup rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        getsong = findViewById(R.id.btnShowList);
        title = findViewById(R.id.title);
        singer = findViewById(R.id.singer);
        year = findViewById(R.id.year);
        rating = findViewById(R.id.rating);

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                int ratings = rating.getCheckedRadioButtonId();

                // Insert a task
                db.insertSong(title.getText().toString(),singer.getText().toString(), Integer.parseInt(year.getText().toString()),ratings);
                db.close();
            }
        });

        getsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, SongList.class);
                startActivity(intent);
                db.close();
            }
        });
    }
}