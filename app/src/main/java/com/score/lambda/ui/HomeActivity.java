package com.score.lambda.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.score.lambda.R;
import com.score.lambda.asyn.LambdaFetcher;
import com.score.lambda.listener.ILambdaFetchListener;
import com.score.lambda.pojo.Lambda;
import com.score.lambda.util.ActivityUtils;
import com.score.lambda.util.NetworkUtil;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ILambdaFetchListener, AbsListView.OnScrollListener,
        AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private int nextPageToFetch = 0;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private ImageView actionBarDelete;
    private ListView lambdaListView;
    private LambdaListAdapter lambdaAdapter;
    private ArrayList<Lambda> lambdaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUi();
        initToolbar();
        initActionBar();
        initLambdaList();
        fetchNext(nextPageToFetch);
    }

    private void initUi() {
    }

    private void initLambdaList() {
        lambdaListView = (ListView) findViewById(R.id.lambda_list);
        lambdaListView.setOnScrollListener(this);
        lambdaListView.setOnItemClickListener(this);
        lambdaListView.setOnItemLongClickListener(this);

        lambdaAdapter = new LambdaListAdapter(this, lambdaList);
        lambdaListView.setAdapter(lambdaAdapter);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setCollapsible(false);
        toolbar.setOverScrollMode(Toolbar.OVER_SCROLL_NEVER);
        setSupportActionBar(toolbar);
    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setCustomView(getLayoutInflater().inflate(R.layout.home_action_bar, null));
            actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);

            actionBarDelete = (ImageView) actionBar.getCustomView().findViewById(R.id.delete);
        }
    }

    private void fetchNext(int page) {
        if (NetworkUtil.isAvailableNetwork(this)) {
            ActivityUtils.showProgressDialog(this, "Fetching lambdas...");
            String uri = "https://rawgit.com/zbsz/test_app/master/" + page + ".json";
            new LambdaFetcher(HomeActivity.this).execute(uri);
        } else {
            Toast.makeText(this, "No network", Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Lambda lambda = lambdaList.get(position);
        if (lambda.isSelected()) {
            lambda.setSelected(false);
            lambdaAdapter.notifyDataSetChanged();
            actionBarDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Lambda lambda = lambdaList.get(position);
        lambda.setSelected(true);
        lambdaAdapter.notifyDataSetChanged();

        actionBarDelete.setVisibility(View.VISIBLE);
        actionBarDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete item
                lambdaList.remove(position);
                lambdaAdapter.notifyDataSetChanged();
                actionBarDelete.setVisibility(View.GONE);
            }
        });

        return true;
    }

}
