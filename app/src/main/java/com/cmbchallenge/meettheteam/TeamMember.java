package com.cmbchallenge.meettheteam;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamMember implements Parcelable {
    private String id;
    private String name;
    private String position;
    private String profile_image;
    private String personality;
    private String interests;
    private String dating_preferences;

    public TeamMember(String id, String name, String position, String profile_image, String personality, String interests, String dating_preferences) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.profile_image = profile_image;
        this.personality = personality;
        this.interests = interests;
        this.dating_preferences = dating_preferences;
    }

    protected TeamMember(Parcel in) {
        id = in.readString();
        name = in.readString();
        position = in.readString();
        profile_image = in.readString();
        personality = in.readString();
        interests = in.readString();
        dating_preferences = in.readString();
    }

    public static final Creator<TeamMember> CREATOR = new Creator<TeamMember>() {
        @Override
        public TeamMember createFromParcel(Parcel in) {
            return new TeamMember(in);
        }

        @Override
        public TeamMember[] newArray(int size) {
            return new TeamMember[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getPersonality() {
        return personality;
    }

    public String getInterests() {
        return interests;
    }

    public String getDating_preferences() {
        return dating_preferences;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(position);
        dest.writeString(profile_image);
        dest.writeString(personality);
        dest.writeString(interests);
        dest.writeString(dating_preferences);
    }
}
