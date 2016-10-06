package com.score.lambda.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.score.lambda.R;
import com.score.lambda.pojo.Lambda;

import java.util.ArrayList;

public class LambdaListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Lambda> lambdaList;

    public LambdaListAdapter(Context context, ArrayList<Lambda> lambdaAList) {
        this.context = context;
        this.lambdaList = lambdaAList;
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
     * Create list row viewv
     *
     * @param i         index
     * @param view      current list item view
     * @param viewGroup parent
     * @return view
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        final Lambda lambda = (Lambda) getItem(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.lambda_list_row_layout, viewGroup, false);

            holder = new ViewHolder();
            holder.userImage = (com.github.siyamed.shapeimageview.RoundedImageView) view.findViewById(R.id.user_image);
            holder.selected = (ImageView) view.findViewById(R.id.selected);
            holder.id = (TextView) view.findViewById(R.id.lambda_id);
            holder.time = (TextView) view.findViewById(R.id.lambda_time);
            holder.message = (TextView) view.findViewById(R.id.message);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        setUpRow(lambda, holder);
        return view;
    }

    private void setUpRow(Lambda lambda, ViewHolder viewHolder) {
        viewHolder.id.setText(lambda.getId().substring(0, 5));
        viewHolder.time.setText("" + lambda.getTimestamp());

        // viewed flag
        if (lambda.isSelected()) {
            viewHolder.selected.setVisibility(View.VISIBLE);
        } else {
            viewHolder.selected.setVisibility(View.GONE);
        }

        // set text of url
        if (android.util.Patterns.WEB_URL.matcher(lambda.getText()).matches()) {
            viewHolder.message.setText("URL...");
        } else {
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
