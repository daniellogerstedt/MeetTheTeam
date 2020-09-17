package com.cmbchallenge.meettheteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TeamDetailsActivity extends AppCompatActivity {

    private ImageView profilePicture;
    private TextView idText, name, personality, interests, datingPreferences;
    private Parcelable[] teamMembers;
    private TeamMember teamMember;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        profilePicture = findViewById(R.id.detailsImageView);
        idText = findViewById(R.id.idDetailsTextView);
        name = findViewById(R.id.nameDetailTextView);
        personality = findViewById(R.id.personalityDetailsTextView);
        interests = findViewById(R.id.interestsDetailsTextView);
        datingPreferences = findViewById(R.id.datingDetailsTextView);

        getData();
        setData();

        Button next = findViewById(R.id.nextButton);
        Button prev = findViewById(R.id.prevButton);
        Button home = findViewById(R.id.homeButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                int nextMemberPosition = position == teamMembers.length - 1? 0 : position + 1;
                Intent intent = new Intent(context, TeamDetailsActivity.class);
                intent.putExtra("teamMembers", teamMembers);
                intent.putExtra("position", nextMemberPosition);
                context.startActivity(intent);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                int nextMemberPosition = position == 0 ? teamMembers.length - 1 : position - 1;
                Intent intent = new Intent(context, TeamDetailsActivity.class);
                intent.putExtra("teamMembers", teamMembers);
                intent.putExtra("position", nextMemberPosition);
                context.startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

    }

    /**
     * Gets the data for the specific team member details that will be displayed based on the provided intent extras and assigns that TeamMember instance to a variable.
     */
    private void getData() {
        if(getIntent().hasExtra("teamMembers") && getIntent().hasExtra("position")) {
            teamMembers = getIntent().getParcelableArrayExtra("teamMembers");
            position = getIntent().getIntExtra("position", 0);
            teamMember = (TeamMember) teamMembers[position];
        }else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sets the values of the fields to the team member data in need of being displayed.
     */
    private void setData() {
        Glide.with(this).load(teamMember.getProfile_image()).placeholder(R.drawable.no_picture_available).dontAnimate().into(profilePicture);
        idText.setText(teamMember.getId());
        name.setText(teamMember.getName());
        personality.setText(teamMember.getPersonality());
        interests.setText(teamMember.getInterests());
        datingPreferences.setText((teamMember.getDating_preferences()));
    }


}