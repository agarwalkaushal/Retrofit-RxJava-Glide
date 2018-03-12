package codexllc.preassignmentimages.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;

/**
 * Created by HP on 11-03-2018.
 */

public class photo {

    @SerializedName("flag")
    @Expose
    private String url;


    public String getUrl(){
        return url;
    }

    public void setUrl(String imageurl) {
        url = imageurl;
    }

}
