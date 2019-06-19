package mx.edu.upqroo.kristenandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mzelzoghbi.zgallery.ZGallery
import com.mzelzoghbi.zgallery.entities.ZColor
import com.squareup.picasso.Picasso
import mx.edu.upqroo.kristenandroid.R
import java.util.*

class ImageItemAdapter internal constructor(images: List<String>,
                                                    private val mContext: Context,
                                                    private val mFragment: Fragment)
    : RecyclerView.Adapter<ImageItemAdapter.ImageViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)
    private val mImagesList: ArrayList<String> = ArrayList(images)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ImageViewHolder {
        val vista = mInflater.inflate(R.layout.item_content_image, parent, false)
        return ImageViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ImageViewHolder,
                                  position: Int) {
        Picasso.get()
                .load(mImagesList[position])
                .placeholder(R.drawable.side_nav_bar)
                .error(R.drawable.android_menu)
                .into(holder.mImageView)
        val abs = Math.abs(mImagesList.size / 2)
        holder.mImageView.requestLayout()
        holder.mImageView.layoutParams.height = Math.abs(600 / abs)
        holder.mImageView.setOnClickListener {
            ZGallery.with(mFragment.activity, mImagesList)
                    .setToolbarTitleColor(ZColor.WHITE)
                    .setGalleryBackgroundColor(ZColor.BLACK)
                    .setToolbarColorResId(R.color.colorPrimary)
                    .setTitle(mContext.getString(R.string.gallery_title))
                    .show()
        }
    }

    override fun getItemCount(): Int {
        return mImagesList.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImageView: ImageView = itemView.findViewById(R.id.image_item_content)

    }
}
