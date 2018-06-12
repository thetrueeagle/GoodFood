package com.goodfood.ape.goodfood;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ape on 24/05/2018.
 */

public interface DataCollection {


    @POST("1FAIpQLSf00Xkw2EdplPjf6oVuuS3ysMxaHGHyCfDumTV9GE5HEWKk4Q/formResponse")
    @FormUrlEncoded
    Call<Void> sendEngagement(
            @Field("entry.1083588543") String email,
            @Field("entry.1887351760") Boolean code,
            @Field("entry.202177462") String activity,
            @Field("entry.521407862") String info
    );

    @POST("1FAIpQLScIsWZYI0HTbClZCTl_VTfMJay9N9EdL1tI1PBIzV1j4JUglA/formResponse")
    @FormUrlEncoded
    Call<Void> sendDailyIntake(
            @Field("entry.504471132") String email,
            @Field("entry.262224557") String date,
            @Field("entry.1261611035") String intake
    );

    @POST("1FAIpQLSdJbSmhTWGNkpkniZlt8lybdXBQr4Sdufsh9xUKv6MnUaYLng/formResponse")
    @FormUrlEncoded
    Call<Void> sendUserInfo(
            @Field("entry.552012012") String name,
            @Field("entry.1833083128") String surname,
            @Field("entry.932178961") String email,
            @Field("entry.1293719396") int streak,
            @Field("entry.917452373") int intake,
            @Field("entry.876880012") int recipes,
            @Field("entry.1173570779") int orders,
            @Field("entry.1621145241") int badges,
            @Field("entry.224183158") String notes
    );


    @POST("1FAIpQLSfY0xa_d8Iip2e7-5H0TbXip90mYfhaQUYi_unaYb5Pn0MqHg/formResponse")
    @FormUrlEncoded
    Call<Void> sendRecipes(
            @Field("entry.307570954") String email,
            @Field("entry.1022679419") String title,
            @Field("entry.732097508") String url,
            @Field("entry.20409953") String category
    );


}
