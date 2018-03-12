package codexllc.preassignmentimages.network;

/**
 * Created by HP on 11-03-2018.
 */
import codexllc.preassignmentimages.model.photo;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("/jsonparsetutorial.txt")
    Observable<List<photo>> register();
}