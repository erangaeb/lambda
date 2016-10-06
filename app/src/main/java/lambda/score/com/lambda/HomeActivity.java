package lambda.score.com.lambda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

import lambda.score.com.lambda.asyn.LambdaFetcher;
import lambda.score.com.lambda.listeners.ILambdaFetchListener;
import lambda.score.com.lambda.pojo.Lambda;

public class HomeActivity extends AppCompatActivity implements ILambdaFetchListener, AbsListView.OnScrollListener {

    private int nextPage = 0;

    private ListView lambaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUi();
        initActionBar();
        initLambdaList();
        fetchNext(0);
    }

    private void initUi() {
    }

    private void initLambdaList() {
        lambaListView = (ListView) findViewById(R.id.lambda_list);
        lambaListView.setOnScrollListener(this);
    }

    private void initActionBar() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setCustomView(getLayoutInflater().inflate(R.layout.home_action_bar, null));
//        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    private void fetchNext(int page) {
        String uri = "https://rawgit.com/zbsz/test_app/master/" + page + ".json";
        new LambdaFetcher(HomeActivity.this).execute("https://rawgit.com/zbsz/test_app/master/0.json");
    }

    @Override
    public void onPostFetch(ArrayList<Lambda> lambdaList) {
        // reload list
    }

    @Override
    public void onError() {
        // display failed to fetch
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
