package org.packathon.android.rest;

import org.packathon.android.models.FeedListModel;
import org.packathon.android.models.FeedModel;
import org.packathon.android.models.LoginPostModel;
import org.packathon.android.models.LoginResponseModel;
import org.packathon.android.models.MeModel;
import org.packathon.android.models.ProjectListModel;
import org.packathon.android.models.ProjectModel;
import org.packathon.android.models.TeamListModel;
import org.packathon.android.models.TeamModel;
import org.packathon.android.models.UserListModel;
import org.packathon.android.models.UserModel;
import org.packathon.android.models.ResponseModel;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface RestService {

    @POST("/login/")
    void login(
            @Header("Content-Type") String contentType,
            @Body LoginPostModel body,
            Callback<LoginResponseModel> callback
    );

    @GET("/feeds/")
    void getFeeds(
            @Query("page_size") int pageSize,
            Callback<FeedModel> callback
    );

    @GET("/feeds/{id}/")
    void getFeed(
            @Path("id") int id,
            Callback<FeedListModel> callback
    );

    @GET("/users/")
    void getUsers(
            @Query("page_size") int pageSize,
            Callback<UserModel> callback
    );

    @GET("/users/{id}/")
    void getUser(
            @Path("id") int id,
            Callback<UserListModel> callback
    );

    @GET("/teams/")
    void getTeams(
            @Query("page_size") int pageSize,
            Callback<TeamModel> callback
    );

    @GET("/teams/{id}/")
    void getTeam(
            @Path("id") int id,
            Callback<TeamListModel> callback
    );

    @GET("/projects/")
    void getProjects(
            @Query("page_size") int pageSize,
            Callback<ProjectModel> callback
    );

    @GET("/projects/{id}/")
    void getProject(
            @Path("id") int id,
            Callback<ProjectListModel> callback
    );

    @POST("/projects/{id}/vote/")
    void vote(
            @Header("Authorization") String authorization,
            @Path("id") int id,
            Callback<ResponseModel> callback
    );

    @GET("/user/")
    void me(
            @Header("Authorization") String authorization,
            Callback<MeModel> callback
    );

}
