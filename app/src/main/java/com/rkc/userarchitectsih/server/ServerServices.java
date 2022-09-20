package com.rkc.userarchitectsih.server;

import com.rkc.userarchitectsih.dto.ArchitectRquest;
import com.rkc.userarchitectsih.dto.ArctitectResponse;
import com.rkc.userarchitectsih.dto.ClientDataJson;
import com.rkc.userarchitectsih.dto.ClientRequest;
import com.rkc.userarchitectsih.dto.ClientResponse;
import com.rkc.userarchitectsih.dto.GramResponse;
import com.rkc.userarchitectsih.dto.Login;
import com.rkc.userarchitectsih.dto.ProjectDeatilsResponse;
import com.rkc.userarchitectsih.dto.ProjectDetailsJosn;
import com.rkc.userarchitectsih.dto.StateDistrictJson;
import com.rkc.userarchitectsih.dto.StatusUpdate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerServices {

    @POST("getGramPans")
    Call<GramResponse> getGramPanchayat(@Body StateDistrictJson stateDistrictJson);

    @POST("getClients")
    Call<ClientResponse> getClient(@Body ClientRequest clientRequest);

    @POST("getEng")
    Call<ArctitectResponse> getArchitect(@Body ArchitectRquest architectRquest);

    @POST("")
    Call<String> getStructural(@Body ClientDataJson clientDataJson);

    @POST("putpdetails")
    Call<String> uploadProject(@Body ProjectDetailsJosn projectDetailsJosn);


    @POST("getpdetails")
    Call<ProjectDeatilsResponse> getProjects();

    @POST("updateStatus")
    Call<String> updateStatus (@Body StatusUpdate statusUpdate);

    @POST("loginClient")
    Call<String> login(@Body Login login);


}
