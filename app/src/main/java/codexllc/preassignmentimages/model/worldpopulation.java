package codexllc.preassignmentimages.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP on 13-03-2018.
 */

public class worldpopulation {
    @SerializedName("worldpopulation")
    private List<photo> photos;

    public List<photo> getPhotos()
    {
        return  photos;
    }

}
