package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mzelzoghbi.zgallery.ZGallery;
import com.mzelzoghbi.zgallery.entities.ZColor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageViewHolder> {
    private final LayoutInflater mInflater;
    private ArrayList<String> mImagesList;
    private Context mContext;
    private Fragment mFragment;

    ImageItemAdapter(List<String> images, Context context, Fragment fragment) {
        this.mImagesList = new ArrayList<>(images);
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mFragment = fragment;

    }

    @NonNull
    @Override
    public ImageItemAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View vista = mInflater.inflate(R.layout.item_content_image, parent,false);
        return new ImageViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageItemAdapter.ImageViewHolder holder,
                                 final int position) {
        Picasso.get()
                .load(mImagesList.get(position))
                .placeholder(R.drawable.side_nav_bar)
                .error(R.drawable.side_nav_bar)
                .into(holder.mImageView);
        int abs = Math.abs(mImagesList.size() / 2);
        holder.mImageView.requestLayout();
        holder.mImageView.getLayoutParams().height = Math.abs(600 / abs);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZGallery.with(mFragment.getActivity(), mImagesList)
                        .setToolbarTitleColor(ZColor.WHITE)
                        .setGalleryBackgroundColor(ZColor.BLACK)
                        .setToolbarColorResId(R.color.colorPrimary)
                        .setTitle("Gallery")
                        .show();
            }
        });
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
