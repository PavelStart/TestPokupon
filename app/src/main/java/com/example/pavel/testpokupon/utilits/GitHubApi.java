package com.example.pavel.testpokupon.utilits;

import com.example.pavel.testpokupon.models.OrganResponse;
import com.example.pavel.testpokupon.models.repmodel.RepResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pavel on 12.05.2018.
 */

public interface GitHubApi {

    @GET("/orgs/{mOrg}")
    Observable<OrganResponse> getOrganization(@Path("mOrg") String mOrg);

    @GET("orgs/{mOrg}/repos")
    Observable<List<RepResponse>> getRepositories(@Path("mOrg") String mOrg,
                                                  @Query("page") String mPage);

}
