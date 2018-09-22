package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.fragments.YoutubeFragment;
import mx.edu.upqroo.kristenandroid.models.Content;
import mx.edu.upqroo.kristenandroid.models.ContentGallery;
import mx.edu.upqroo.kristenandroid.models.ContentImage;
import mx.edu.upqroo.kristenandroid.models.ContentLink;
import mx.edu.upqroo.kristenandroid.models.ContentList;
import mx.edu.upqroo.kristenandroid.models.ContentTitle;
import mx.edu.upqroo.kristenandroid.models.ContentVideo;

public class NewsDetailContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Content> mContentList;
    private LayoutInflater mInflater;
    private FragmentManager mFragmentManager;

    public NewsDetailContentAdapter(Context context, List<Content> contents,
                                    FragmentManager fragmentManager) {
        this.mInflater = LayoutInflater.from(context);
        this.mContentList = contents;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        int id;
        if (mContentList.get(position) instanceof ContentGallery) {
            id = 0;
        } else if (mContentList.get(position) instanceof ContentTitle) {
            id = 1;
        } else if (mContentList.get(position) instanceof ContentImage) {
            id = 2;
        } else if (mContentList.get(position) instanceof ContentLink) {
            id = 3;
        } else if (mContentList.get(position) instanceof ContentList) {
            id = 4;
        } else if (mContentList.get(position) != null) {
            id = 5;
        } else if (mContentList.get(position) instanceof ContentVideo) {
            id = 6;
        } else {
            id = 5;
        }
        return id;
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = mInflater.inflate(R.layout.item_content_gallery, parent,false);
                return new GalleryViewHolder(view);
            case 1:
                view = mInflater.inflate(R.layout.item_content_header, parent,false);
                return new HeaderViewHolder(view);
            case 2:
                view = mInflater.inflate(R.layout.item_content_image, parent,false);
                return new ImageViewHolder(view);
            case 3:
                view = mInflater.inflate(R.layout.item_content_link, parent,false);
                return new LinkViewHolder(view);
            case 4:
                view = mInflater.inflate(R.layout.item_content_list, parent,false);
                return new ListViewHolder(view);
            case 5:
                view = mInflater.inflate(R.layout.item_content_text, parent,false);
                return new TextViewHolder(view);
            case 6:
                view = mInflater.inflate(R.layout.item_content_video, parent,false);
                return new VideoViewHolder(view);
            default:
                view = mInflater.inflate(R.layout.item_content_text, parent,false);
                return  new TextViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                GalleryViewHolder galleryHolder = (GalleryViewHolder) holder;
                ContentGallery contentGallery = (ContentGallery) mContentList.get(position);
                //galleryHolder.mRecycler.setAdapter();
                break;
            case 1:
                HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
                ContentTitle contentTitle = (ContentTitle) mContentList.get(position);
                headerHolder.mTextView.setText(contentTitle.getText());
                break;
            case 2:
                ImageViewHolder imageHolder = (ImageViewHolder) holder;
                ContentImage contentImage = (ContentImage) mContentList.get(position);
                Picasso.get()
                        .load(contentImage.getSource())
                        .fit()
                        .placeholder(R.drawable.side_nav_bar)
                        .error(R.drawable.side_nav_bar)
                        .into(imageHolder.mImageView);
                break;
            case 3:
                LinkViewHolder linkHolder = (LinkViewHolder) holder;
                ContentLink contentLink = (ContentLink) mContentList.get(position);
                linkHolder.mTextView.setText(contentLink.getText());
                linkHolder.mLinkTextView.setText(contentLink.getUrl());
                break;
            case 4:
                ListViewHolder listHolder = (ListViewHolder) holder;
                break;
            case 5:
                TextViewHolder textHolder = (TextViewHolder) holder;
                textHolder.mTextView.setText(mContentList.get(position).getText());
                break;
            case 6:
                VideoViewHolder videoHolder = (VideoViewHolder) holder;

                Bundle args = new Bundle();
                args.putString("videoId", mContentList.get(position).getText());
                videoHolder.youTubeNativeFragmentDemo.setArguments(args);
                FragmentTransaction fragmentTransaction =
                        mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.youtube_fragment_content,
                        videoHolder.youTubeNativeFragmentDemo);
                fragmentTransaction.commit();
                break;
        }
    }


    class GalleryViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecycler;
        GalleryViewHolder(View itemView) {
            super(itemView);
            mRecycler = itemView.findViewById(R.id.recycler_gallery_item_content);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        HeaderViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_header_item_content);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_item_content);
        }
    }

    class LinkViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextView mLinkTextView;
        LinkViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_link_item_content);
            mLinkTextView = itemView.findViewById(R.id.link_item_content);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        RecyclerView mRecyclerView;
        ListViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_list_item_content);
            mRecyclerView = itemView.findViewById(R.id.recycler_list_item_content);
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_link_item_content);
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        YoutubeFragment youTubeNativeFragmentDemo = new YoutubeFragment();
        VideoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
