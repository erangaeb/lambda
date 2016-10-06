package com.score.lambda.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.score.lambda.R;
import com.score.lambda.asyn.LambdaFetcher;
import com.score.lambda.listener.ILambdaFetchListener;
import com.score.lambda.pojo.Lambda;
import com.score.lambda.util.ActivityUtils;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ILambdaFetchListener, AbsListView.OnScrollListener {

    private int nextPageToFetch = 0;

    private ListView lambdaListView;
    private LambdaListAdapter lambdaAdapter;
    private ArrayList<Lambda> lambdaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUi();
        initActionBar();
        initLambdaList();
        fetchNext(nextPageToFetch);
    }

    private void initUi() {
    }

    private void initLambdaList() {
        lambdaListView = (ListView) findViewById(R.id.lambda_list);
        lambdaListView.setOnScrollListener(this);

        lambdaAdapter = new LambdaListAdapter(this, lambdaList);
        lambdaListView.setAdapter(lambdaAdapter);
    }

    private void initActionBar() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setCustomView(getLayoutInflater().inflate(R.layout.home_action_bar, null));
//        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    private void fetchNext(int page) {
        ActivityUtils.showProgressDialog(this, "Please wait..");
        String uri = "https://rawgit.com/zbsz/test_app/master/" + page + ".json";
        new LambdaFetcher(HomeActivity.this).execute(uri);
    }

    @Override
    public void onFetchDone(ArrayList<Lambda> list) {
        // reload list
        ActivityUtils.cancelProgressDialog();

        lambdaList.addAll(list);
        lambdaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFetchError() {
        // display failed to fetch
        ActivityUtils.cancelProgressDialog();
        Toast.makeText(this, "Fetch error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFetchEnd() {
        // display failed to fetch
        ActivityUtils.cancelProgressDialog();
        Toast.makeText(this, "No more lambdas", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }
}
