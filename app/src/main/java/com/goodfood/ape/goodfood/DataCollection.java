package com.goodfood.ape.goodfood;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ape on 24/05/2018.
 */

public interface DataCollection {


    @POST("1o3RzxfL3epQxcloYlxo6281u1keKWT7Djv0xwEAubEY/formResponse")
    @FormUrlEncoded
    Call<Void> sendEngagement(
            @Field("entry.1807650696") String email,
            @Field("entry.1555002673") Boolean code,
            @Field("entry.1545826018") String activity,
            @Field("entry.777766750") String info
    );

    @POST("1NhSrukxM_bpuqqn4Am7wiDqbJLD_unFd65axzWaCDjk/formResponse")
    @FormUrlEncoded
    Call<Void> sendDailyIntake(
            @Field("entry.1490968695") String email,
            @Field("entry.1389886381") String date,
            @Field("entry.63782285") String intake
    );

    @POST("1z_W9W-bsUI5JaCUMLLNo5ZpK5k8s5sniYHBp6SwLdo4/formResponse")
    @FormUrlEncoded
    Call<Void> sendUserInfo(
            @Field("entry.1381769090") String name,
            @Field("entry.1575705471") String surname,
            @Field("entry.129493829") String email,
            @Field("entry.60583996") int streak,
            @Field("entry.1936839258") int intake,
            @Field("entry.2111959000") int recipes,
            @Field("entry.1099430937") int orders,
            @Field("entry.2036134188") int badges,
            @Field("entry.2026806379") String notes
    );


    @POST("1djt_-R4SRAdUw2V2Tj2jnwUSBNsB0FzufVY-YwolK6o/formResponse")
    @FormUrlEncoded
    Call<Void> sendRecipes(
            @Field("entry.252113994") String email,
            @Field("entry.464655268") String title,
            @Field("entry.1356132088") String url,
            @Field("entry.810498609") String category
    );


}
