package com.example.alec.movieapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> movies;
    public static ArrayList<String> ids;
    private static final String[] movies2 = {"Baby Driver", "Deadpool", "Airplane!", "Fight Club", "Inception"};
    private static final String[] id2 = {"tt3890160", "tt1431045", "tt0080339", "tt0137523", "tt1375666" };
    private ListView listview;
    private RelativeLayout layout;
    private Button button;
    private String addmovie;
    private String addid;
    private AlertDialog dialog;
    private AdapterView.OnItemLongClickListener RemoveMovie2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        movies = new ArrayList<String>();
        ids = new ArrayList<String>();
        for(int i=0; i<movies2.length; i++){
            movies.add(movies2[i]);
        }
        for(int x = 0; x< id2.length; x++){
            ids.add(id2[x]);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Would you like to remove the selected movie?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                movies.remove(i);
                ids.remove(i);
            }
        });
        builder.setNegativeButton("Naw", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog = builder.create();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        layout = findViewById(R.id.layout);
        listview = findViewById(R.id.movie_list);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, movies);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/" + ids.get(position)));
                        startActivity(i);
                    }
                }
        );


       listview.setOnItemLongClickListener(
               new AdapterView.OnItemLongClickListener() {
                   @Override
                   public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dialog.show();
                       return true;
                   }
               }
       );

    }



        public void buttonPressed(View v){
        Intent i = new Intent(this, AddActivity.class);
        startActivityForResult(i, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        addid = data.getStringExtra("id");
        addmovie = data.getStringExtra("movie");
        movies.add(addmovie);
        ids.add(addid);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, movies);
        listview.setAdapter(adapter);
    }
}
