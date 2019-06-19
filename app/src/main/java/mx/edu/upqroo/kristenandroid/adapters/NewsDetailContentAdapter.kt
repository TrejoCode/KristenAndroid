package mx.edu.upqroo.kristenandroid.adapters

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flipkart.youtubeview.YouTubePlayerView
import com.flipkart.youtubeview.models.YouTubePlayerType
import com.squareup.picasso.Picasso
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.models.*
import mx.edu.upqroo.kristenandroid.ui.fragments.YoutubeFragment

class NewsDetailContentAdapter(private val mContext: Context,
                               private val mContentList: List<Content>,
                               private val mFragment: Fragment)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    private val imageLoader = { imageView: ImageView, url: String, height: Int, width: Int ->
        Picasso.get()
                .load(url)
                .resize(width + 1, height + 1)
                .centerCrop()
                .into(imageView)
    }

    override fun getItemViewType(position: Int): Int {
        val id: Int
        if (mContentList[position] is ContentGallery) {
            id = 0
        } else if (mContentList[position] is ContentTitle) {
            id = 1
        } else if (mContentList[position] is ContentImage) {
            id = 2
        } else if (mContentList[position] is ContentLink) {
            id = 3
        } else if (mContentList[position] is ContentList) {
            id = 4
        } else if (mContentList[position] is ContentVideo) {
            id = 6
        } else {
            id = 5
        }
        return id
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            0 -> {
                view = mInflater.inflate(R.layout.item_content_gallery, parent, false)
                return GalleryViewHolder(view)
            }
            1 -> {
                view = mInflater.inflate(R.layout.item_content_header, parent, false)
                return HeaderViewHolder(view)
            }
            2 -> {
                view = mInflater.inflate(R.layout.item_content_image, parent, false)
                return ImageViewHolder(view)
            }
            3 -> {
                view = mInflater.inflate(R.layout.item_content_link, parent, false)
                return LinkViewHolder(view)
            }
            4 -> {
                view = mInflater.inflate(R.layout.item_content_list, parent, false)
                return ListViewHolder(view)
            }
            5 -> {
                view = mInflater.inflate(R.layout.item_content_text, parent, false)
                return TextViewHolder(view)
            }
            6 -> {
                view = mInflater.inflate(R.layout.item_content_video, parent, false)
                return VideoViewHolder(view)
            }
            else -> {
                view = mInflater.inflate(R.layout.item_content_text, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when (holder.itemViewType) {
            0 -> {
                val galleryHolder = holder as GalleryViewHolder
                val contentGallery = mContentList[position] as ContentGallery
                val abs = Math.abs(contentGallery.getImages().size / 2)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
                    galleryHolder.mRecycler.layoutManager = object : GridLayoutManager(mContext, abs) {
                        override fun canScrollHorizontally(): Boolean {
                            return false
                        }

                        override fun canScrollVertically(): Boolean {
                            return false
                        }
                    }
                } else {
                    galleryHolder.mRecycler.layoutManager = GridLayoutManager(mContext, abs)
                }
                galleryHolder.mRecycler.adapter = ImageItemAdapter(contentGallery.getImages(), mContext,
                        mFragment)
            }
            1 -> {
                val headerHolder = holder as HeaderViewHolder
                val contentTitle = mContentList[position] as ContentTitle
                headerHolder.mTextView.text = contentTitle.text
            }
            2 -> {
                val imageHolder = holder as ImageViewHolder
                val contentImage = mContentList[position] as ContentImage
                Picasso.get()
                        .load(contentImage.source)
                        .placeholder(R.drawable.side_nav_bar)
                        .error(R.drawable.android_menu)
                        .into(imageHolder.mImageView)
                imageHolder.mImageView.setOnClickListener {
                    val dialog = Dialog(mContext)
                    dialog.setContentView(R.layout.image_dialog_layout)
                    dialog.setTitle("Image")
                    val myImage = dialog.findViewById<ImageView>(R.id.image_dialog)
                    Picasso.get()
                            .load(contentImage.source)
                            .placeholder(R.drawable.side_nav_bar)
                            .error(R.drawable.android_menu)
                            .into(myImage)
                    dialog.show()
                }
            }
            3 -> {
                val linkHolder = holder as LinkViewHolder
                val contentLink = mContentList[position] as ContentLink
                val clickableText = SpannableString(contentLink.url)
                clickableText.setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View) {}
                }, 0, contentLink.url.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                linkHolder.mTextView.text = clickableText
                linkHolder.mTextView.setOnClickListener {
                    mContext.startActivity(Intent(
                            Intent.ACTION_VIEW).setData(Uri.parse(contentLink.text)))
                }
            }
            4 -> {
                val listHolder = holder as ListViewHolder
                val contentList = mContentList[position] as ContentList
                listHolder.mTextView.text = contentList.title
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
                    listHolder.mRecyclerView.layoutManager = object : LinearLayoutManager(mContext) {
                        override fun canScrollHorizontally(): Boolean {
                            return false
                        }

                        override fun canScrollVertically(): Boolean {
                            return false
                        }
                    }
                } else {
                    listHolder.mRecyclerView.layoutManager = LinearLayoutManager(mContext)
                }
                listHolder.mRecyclerView.adapter = ListElementItemAdapter(contentList.getElements(),
                        mContext)
            }
            5 -> {
                val textHolder = holder as TextViewHolder
                textHolder.mTextView.text = mContentList[position].text
            }
            6 -> {
                val videoHolder = holder as VideoViewHolder
                val contentVideo = mContentList[position] as ContentVideo

                val videoId = contentVideo.id

                videoHolder.mPlayerView.initPlayer(YoutubeFragment.API_KEY, videoId,
                        "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/" + "60bae1a1/youtube-android/youtube_iframe_player.html",
                        YouTubePlayerType.STRICT_NATIVE, null, mFragment, imageLoader)
            }
        }
    }


    internal inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mRecycler: RecyclerView = itemView.findViewById(R.id.recycler_gallery_item_content)

    }

    internal inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.text_header_item_content)

    }

    internal inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImageView: ImageView = itemView.findViewById(R.id.image_item_content)

    }

    internal inner class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.link_item_content)

    }

    internal inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.text_list_item_content)
        var mRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_list_item_content)

    }

    internal inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.text_link_item_content)

    }

    internal inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mPlayerView: YouTubePlayerView = itemView.findViewById(R.id.youtube_player_view_content)

    }
}
