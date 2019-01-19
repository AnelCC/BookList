package com.anelcalvo.www.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    RecyclerView rv_repositories;
    List<Repo> repositories;
    RepositoryAdapter repoAdapter;

    // Trailing slash is needed
    public static final String BASE_URL = "http://de-coding-test.s3.amazonaws.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositories        = new ArrayList<>();
        repoAdapter         = new RepositoryAdapter(repositories);
        rv_repositories     = (RecyclerView) findViewById(R.id.a_main_recycler);
        rv_repositories.setLayoutManager(new LinearLayoutManager(this));
        rv_repositories.setAdapter(repoAdapter);

    }



    public void getInfo(View view) {
        Log.d(TAG, "getInfo: ");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ManagerService service = retrofit.create(ManagerService.class);
        Call<List<Repo>> repos = service.listRepos();
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                List <Repo>  result = response.body();

                for(Repo repo: result){
                    Log.d(TAG,"response "+ repo);
                }

                repositories.clear();
                repositories.addAll(result);
                repoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG,"response "+ t.getMessage());
            }
        });
    }
}
