package sg.edu.rp.c346.id22004686.mysongdb;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lv = findViewById(R.id.lv);

        DBHelper db = new DBHelper(SongList.this);

        ArrayList<Song> al = db.getSongs();
        db.close();
        ArrayAdapter adapter = new ArrayAdapter(SongList.this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(adapter);
    }
}
