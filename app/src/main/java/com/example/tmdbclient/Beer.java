package com.example.tmdbclient;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



@Entity(tableName = "cart_table")
public class Beer extends BaseObservable implements Parcelable
{

    @SerializedName("abv")
    @Expose
    @ColumnInfo(name = "abv")
    private String abv;

    @SerializedName("ounces")
    @Expose
    @ColumnInfo(name = "ounces")
    private String ounces;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("style")
    @Expose
    @ColumnInfo(name = "style")
    private String style;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @SerializedName("ibu")
    @Expose
    @ColumnInfo(name = "ibu")
    private String ibu;

    public Beer() {
    }

    protected Beer(Parcel in) {
        abv = in.readString();
        ounces = in.readString();
        name = in.readString();
        style = in.readString();
        id = in.readString();
        ibu = in.readString();
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    public String getAbv ()
    {
        if(abv.isEmpty())
            return "0";
        return abv;
    }

    public void setAbv (String abv)
    {
        this.abv = abv;
    }
    @Bindable
    public String getOunces ()
    {
        return ounces;
    }

    public void setOunces (String ounces)
    {
        this.ounces = ounces;
    }
    @Bindable
    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
    @Bindable
    public String getStyle ()
    {
        return style;
    }

    public void setStyle (String style)
    {
        this.style = style;
    }
    @Bindable
    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }
    @Bindable
    public String getIbu ()
    {
        if(ibu.isEmpty())
            return "0";
        return ibu;
    }

    public void setIbu (String ibu)
    {
        this.ibu = ibu;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [abv = "+abv+", ounces = "+ounces+", name = "+name+", style = "+style+", id = "+id+", ibu = "+ibu+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(abv);
        dest.writeString(ounces);
        dest.writeString(name);
        dest.writeString(style);
        dest.writeString(id);
        dest.writeString(ibu);
    }
}
