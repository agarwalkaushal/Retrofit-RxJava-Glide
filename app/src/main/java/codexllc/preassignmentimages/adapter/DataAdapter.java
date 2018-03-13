package codexllc.preassignmentimages.adapter;

/**
 * Created by HP on 11-03-2018.
 */

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import codexllc.preassignmentimages.fullImage;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import codexllc.preassignmentimages.R;
import codexllc.preassignmentimages.model.photo;

import java.net.URL;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {


    private ArrayList<photo> mAndroidList;
    private Context context;
    String url;
    public DataAdapter(ArrayList<photo> androidList) {
        mAndroidList = androidList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String imageUrl=mAndroidList.get(position).getUrl();
        Log.e("Url is: ",imageUrl);
        Glide.with(context).load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    url = mAndroidList.get(getAdapterPosition()).getUrl();
                    Intent intent = new Intent(context, fullImage.class);
                    intent.putExtra("url",url);
                    context.startActivity(intent);
                    //Toast.makeText(view.getContext(), "Item is clicked", Toast.LENGTH_SHORT).show();
                }

            });
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }

}