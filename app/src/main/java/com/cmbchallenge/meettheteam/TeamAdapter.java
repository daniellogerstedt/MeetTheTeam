package com.cmbchallenge.meettheteam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context context;
    private TeamMember[] teamMembers;

    public TeamAdapter (Context con, TeamMember[] team) {
        context = con;
        teamMembers = team;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.team_member_row, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, final int pos) {
        holder.nameText.setText(teamMembers[pos].getName());
        holder.posText.setText(teamMembers[pos].getPosition());
        Glide.with(context).load(teamMembers[pos].getProfile_image()).placeholder(R.drawable.no_picture_available).dontAnimate().into(holder.image);
        holder.teamAdapterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeamDetailsActivity.class);
                intent.putExtra("teamMembers", teamMembers);
                intent.putExtra("position", pos);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamMembers.length;
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, posText;
        ImageView image;
        ConstraintLayout teamAdapterLayout;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameTextView);
            posText = itemView.findViewById(R.id.posTextView);
            image = itemView.findViewById(R.id.photoImageView);
            teamAdapterLayout = itemView.findViewById(R.id.teamAdapterLayout);
        }
    }

}
