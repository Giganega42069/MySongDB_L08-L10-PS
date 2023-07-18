package sg.edu.rp.c346.id22004686.mysongdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    Button btn5Stars,resetz;
    ListView lv;
    ArrayList<Song> al;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lv = findViewById(R.id.lv);
        btn5Stars = findViewById(R.id.btn5Stars);
        resetz = findViewById(R.id.resetz);

        DBHelper db = new DBHelper(SongList.this);

        ArrayList<Song> al = db.getSongs();
        db.close();
        ArrayAdapter adapter = new ArrayAdapter(SongList.this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(adapter);

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Song> newSongs = new ArrayList<>();
                for (int i = 0; i < db.getSongs().size(); i++) {
                    if (al.get(i).getStars() == 5) {
                        newSongs.add(al.get(i));
                    }
                    CustomAdapter adapter = new CustomAdapter(SongList.this,R.layout.row,newSongs);
                    lv.setAdapter(adapter);
                }
            }
        });

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

        resetz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SongList.this);
                al.clear();
                al.addAll(dbh.getSongs());
                CustomAdapter adapter = new CustomAdapter(SongList.this,R.layout.row,al);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        resetz.performClick();
        }
    }
