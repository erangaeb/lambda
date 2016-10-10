package com.score.lambda.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.score.lambda.R;
import com.score.lambda.asyn.ImageFetcher;
import com.score.lambda.pojo.Lambda;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Display lambdas in the list from here
 *
 * @author erangaeb@gmail.com (eranga herath)
 */
class LambdaListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Lambda> lambdaList;
    private Typeface typeface;

    // reusable fetchers
    private HashMap<String, ImageFetcher> fetcherMap;

    LambdaListAdapter(Context context, ArrayList<Lambda> lambdaAList) {
        this.context = context;
        this.lambdaList = lambdaAList;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/GeosansLight.ttf");

        this.fetcherMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return lambdaList.size();
    }

    @Override
    public Object getItem(int position) {
        return lambdaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Create list row view
     *
     * @param i         index
     * @param view      current list item view
     * @param viewGroup parent
     * @return view
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        final Lambda lambda = lambdaList.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.lambda_list_row_layout, viewGroup, false);

            holder = new ViewHolder();
            holder.userImage = (com.github.siyamed.shapeimageview.RoundedImageView) view.findViewById(R.id.user_image);
            holder.selected = (ImageView) view.findViewById(R.id.selected);
            holder.id = (TextView) view.findViewById(R.id.lambda_id);
            holder.time = (TextView) view.findViewById(R.id.lambda_time);
            holder.message = (TextView) view.findViewById(R.id.message);

            holder.id.setTypeface(typeface);
            holder.time.setTypeface(typeface);
            holder.message.setTypeface(typeface);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        setUpRow(lambda, holder);
        return view;
    }

    /**
     * Set up list view row
     *
     * @param lambda     lambda obj
     * @param viewHolder view holder
     */
    private void setUpRow(Lambda lambda, ViewHolder viewHolder) {
        viewHolder.id.setText(lambda.getId().substring(0, 5));
        viewHolder.time.setText(lambda.getTimestamp() + "");

        // viewed flag
        if (lambda.isSelected()) {
            viewHolder.selected.setVisibility(View.VISIBLE);
        } else {
            viewHolder.selected.setVisibility(View.GONE);
        }

        // set text/ url
        if (android.util.Patterns.WEB_URL.matcher(lambda.getText()).matches()) {
            // this is url
            viewHolder.message.setText("URL");

            // download image and load in image view,
            // TODO refactor this
            if (lambda.getImage() == null) {
                // set default image first
                viewHolder.userImage.setImageResource(R.drawable.default_user);

                // no image with lambda
                if (!fetcherMap.containsKey(lambda.getId())) {
                    // download image then
                    ImageFetcher fetcher = new ImageFetcher(viewHolder.userImage, lambda);
                    fetcherMap.put(lambda.getId(), fetcher);
                    fetcher.execute(lambda.getText().trim());
                }
            } else {
                // have image with lambda
                viewHolder.userImage.setImageBitmap(lambda.getImage());
            }
        } else {
            // not a url, set default image and text
            viewHolder.userImage.setImageResource(R.drawable.default_user);
            viewHolder.message.setText(lambda.getText());
        }
    }

    /**
     * Keep reference to children view to avoid unnecessary calls
     */
    private static class ViewHolder {
        com.github.siyamed.shapeimageview.RoundedImageView userImage;
        ImageView selected;
        TextView id;
        TextView time;
        TextView message;
    }

}
