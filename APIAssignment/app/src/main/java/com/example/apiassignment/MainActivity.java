package com.example.apiassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private RecViewAdapter adapter;
    private ArrayList<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = findViewById(R.id.postsRecView);



        Type postListType = new TypeToken<ArrayList<Post>>(){}.getType();


        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, getString(R.string.posts_url), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                postList = (ArrayList<Post>) gson.fromJson(response, postListType);
                //Toast.makeText(MainActivity.this, posts.get(1).toString(), Toast.LENGTH_SHORT).show();
                //setData(posts);

                adapter = new RecViewAdapter(MainActivity.this);
                adapter.setPosts(postList);

                //Toast.makeText(MainActivity.this, posts.toString(), Toast.LENGTH_SHORT).show();

                recView.setAdapter(adapter);
                recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);


        getPosts();

    }

    private void getPosts() {

    }

    private void setData(ArrayList<Post> posts){

    }
}