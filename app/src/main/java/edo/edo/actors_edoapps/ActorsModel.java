
package edo.edo.actors_edoapps;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorsModel {

    @SerializedName("actors")
    @Expose
    private List<Actor> actors = null;

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

}
