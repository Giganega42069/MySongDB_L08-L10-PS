package sg.edu.rp.c346.id22004686.mysongdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    ListView lv;
    ArrayList<Song> al;

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(SongList.this,
                        EditActivity.class);
                i.putExtra("datas", data);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int cycle = 0;
        cycle++;
        if (cycle > 1) {
            DBHelper dbh = new DBHelper(SongList.this);
            al.clear();
            al.addAll(dbh.getSongs());
            ArrayAdapter adapter = new ArrayAdapter(SongList.this, android.R.layout.simple_list_item_1, al);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
