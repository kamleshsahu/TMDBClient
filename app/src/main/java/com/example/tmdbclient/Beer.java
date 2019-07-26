package com.example.tmdbclient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Beer
{

    @SerializedName("abv")
    @Expose
    private String abv;

    @SerializedName("ounces")
    @Expose
    private String ounces;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("style")
    @Expose
    private String style;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("ibu")
    @Expose
    private String ibu;

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

    public String getOunces ()
    {
        return ounces;
    }

    public void setOunces (String ounces)
    {
        this.ounces = ounces;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getStyle ()
    {
        return style;
    }

    public void setStyle (String style)
    {
        this.style = style;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIbu ()
    {
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
}
