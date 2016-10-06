package lambda.score.com.lambda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

import lambda.score.com.lambda.asyn.LambdaFetcher;
import lambda.score.com.lambda.listeners.ILambdaFetchListener;
import lambda.score.com.lambda.pojo.Lambda;
import lambda.score.com.lambda.util.ActivityUtils;

public class HomeActivity extends AppCompatActivity implements ILambdaFetchListener, AbsListView.OnScrollListener {

    private int nextPageToFetch = 0;
    private ListView lambdaListView;

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
    public void onFetchDone(ArrayList<Lambda> lambdaList) {
        // reload list
        ActivityUtils.cancelProgressDialog();
    }

    @Override
    public void onFetchError() {
        // display failed to fetch
        ActivityUtils.cancelProgressDialog();
    }

    @Override
    public void onFetchEnd() {
        // display failed to fetch
        ActivityUtils.cancelProgressDialog();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final int lastItem = firstVisibleItem + visibleItemCount;
        if (lastItem == totalItemCount) {
            // Last item is fully visible.

        }
    }
}
