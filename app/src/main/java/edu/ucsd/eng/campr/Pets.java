package edu.ucsd.eng.campr;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Pets implements Parcelable {

    //TODO: Merge this class with the Pet Class
    private String name;
    private String gender;
    private String info;
    private String petId;
    private Bitmap petPic;

    public Pets(String mName, String mGender, String mInfo, String mId, Bitmap mPic) {
        name = mName;
        gender = mGender;
        info = mInfo;
        petId = mId;
        petPic = mPic;
    }
    public Pets(Parcel in) {
        name = in.readString();
        gender = in.readString();
        info = in.readString();
        petId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(info);
        dest.writeString(petId);
    }

    public static final Creator<Pets> CREATOR = new Creator<Pets>() {
        public Pets createFromParcel(Parcel in) {
            return new Pets(in);
        }

        public Pets[] newArray(int size) {
            return new Pets[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getInfo() {
        return info;
    }

    public String getPetId() {
        return petId;
    }

    public Bitmap getPetPic() { return  petPic; }

    @Override
    public boolean equals(Object obj) {
        return (this.petId.equals(((Pets) obj).petId));
    }


}
