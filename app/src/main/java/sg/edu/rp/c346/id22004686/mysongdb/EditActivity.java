package sg.edu.rp.c346.id22004686.mysongdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate,btnDelete,btnCancel;
    TextView tvID;
    EditText title,singer,year;
    RadioGroup rating;
    Song data;
    RadioButton B1,B2,B3,B4,B5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        title = findViewById(R.id.title);
        singer = findViewById(R.id.singer);
        year = findViewById(R.id.year);
        rating = findViewById(R.id.rating);
        tvID = findViewById(R.id.tvID);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("datas");
        tvID.setText("ID: " + data.getId());

        title.setText(data.getTitle());
        singer.setText(data.getSingers());
        String yearvalue = String.valueOf(data.getYear());
        year.setText(yearvalue);
        if (data.getStars() == 1) {
            B1 = findViewById(R.id.R1);
            B1.setChecked(true);
        } else if (data.getStars() == 2) {
            B2 = findViewById(R.id.R2);
            B2.setChecked(true);
        } else if (data.getStars() == 3) {
            B3 = findViewById(R.id.R3);
            B3.setChecked(true);
        } else if (data.getStars() == 4) {
            B4 = findViewById(R.id.R4);
            B4.setChecked(true);
        } else if (data.getStars() == 5) {
            B5 = findViewById(R.id.R5);
            B5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(title.getText().toString());
                data.setSingers(singer.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                int ratings = rating.getCheckedRadioButtonId();
                int starz = 0;
                if (ratings == R.id.R1){
                    starz = 1;
                } else if (ratings == R.id.R2) {
                    starz = 2;
                } else if (ratings == R.id.R3) {
                    starz = 3;
                } else if (ratings == R.id.R4) {
                    starz = 4;
                } else if (ratings == R.id.R5) {
                    starz = 5;
                }
                data.setStars(starz);
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}