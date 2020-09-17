package com.cmbchallenge.meettheteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.teamRecyclerView);

        String json = getJson();
        Gson gson = new Gson();
        TeamMember[] teamMembers = gson.fromJson(json, TeamMember[].class);

        TeamAdapter teamAdapter = new TeamAdapter(this, teamMembers);
        recyclerView.setAdapter(teamAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Gets Json from assets and returns it in string format for use with GSON. Private to the main activity as it is not used elsewhere.
     * @return String Json from Assets.
     */
    private String getJson() {
        String json = null;
        try {
            InputStream is = getAssets().open("team.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            return json;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}