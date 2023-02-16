package edu.msu.mondolsh.cloudhatter.Cloud;

import edu.msu.mondolsh.cloudhatter.Cloud.Models.Catalog;
import edu.msu.mondolsh.cloudhatter.Cloud.Models.LoadResult;
import edu.msu.mondolsh.cloudhatter.Cloud.Models.SaveResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static edu.msu.mondolsh.cloudhatter.Cloud.Cloud.CATALOG_PATH;
import static edu.msu.mondolsh.cloudhatter.Cloud.Cloud.DELETE_PATH;
import static edu.msu.mondolsh.cloudhatter.Cloud.Cloud.LOAD_PATH;
import static edu.msu.mondolsh.cloudhatter.Cloud.Cloud.SAVE_PATH;

import java.util.concurrent.Executor;

public interface HatterService {
    @GET(CATALOG_PATH)
    Call<Catalog> getCatalog(
            @Query("user") String userId,
            @Query("magic") String magic,
            @Query("pw") String password
    );

    @GET(LOAD_PATH)
    Call<LoadResult> loadHat(
            @Query("user") String userId,
            @Query("magic") String magic,
            @Query("pw") String password,
            @Query("id") String idHatToLoad
    );

    @FormUrlEncoded
    @POST(SAVE_PATH)
    Call<SaveResult> saveHat(
            @Field("user") String userId,
            @Field("magic") String magic,
            @Field("pw") String password,
            @Field("xml") String xmlData
    );

    @GET(DELETE_PATH)
    Call<LoadResult> deleteHat(
            @Query("user") String userId,
            @Query("magic") String magic,
            @Query("pw") String password,
            @Query("id") String idHatToDelete
    );

}
