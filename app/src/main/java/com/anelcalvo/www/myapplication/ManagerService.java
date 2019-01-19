package com.anelcalvo.www.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ManagerService {
    @GET("books.json")
    Call<List<Repo>> listRepos();
}