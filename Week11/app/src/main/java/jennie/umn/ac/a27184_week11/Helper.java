package jennie.umn.ac.a27184_week11;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Helper {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();
}