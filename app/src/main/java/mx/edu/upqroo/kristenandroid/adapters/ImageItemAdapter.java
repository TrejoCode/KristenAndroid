package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageViewHolder> {
    private final LayoutInflater mInflater;
    private List<String> mImagesList;

    ImageItemAdapter(List<String> images, Context context) {
        this.mImagesList = images;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImageItemAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View vista = mInflater.inflate(R.layout.item_content_image, parent,false);
        return new ImageViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageItemAdapter.ImageViewHolder holder, int position) {
        Picasso.get()
                .load(mImagesList.get(position))
                .fit()
                .placeholder(R.drawable.side_nav_bar)
                .error(R.drawable.side_nav_bar)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImagesList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_item_content);
        }
    }
}
