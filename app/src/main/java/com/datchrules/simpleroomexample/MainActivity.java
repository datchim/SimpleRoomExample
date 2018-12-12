package com.datchrules.simpleroomexample;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String DATABASE_NAME = "movies_db";
        final MovieDatabase movieDatabase;
        movieDatabase = Room.databaseBuilder(getApplicationContext(),
                MovieDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
        final TextView myAwesomeTextView = (TextView)findViewById(R.id.testtext);
        Thread initBkgdThread = new Thread(new Runnable() {
            @Override
            public void run() {
                 Movies movie =new Movies();
                 movie.setMovieId("5");
                 movie.setMovieName("The Kurňašopa");
                 movieDatabase.daoAccess().insertOnlySingleMovie(movie);
                 Movies movietest = movieDatabase.daoAccess().fetchOneMoviesbyMovieId ("5");
                 myAwesomeTextView.setText(movietest.getMovieName());
            }
        });
        initBkgdThread.start();

    }
}
