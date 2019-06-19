package mx.edu.upqroo.kristenandroid.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.ContactItemAdapter.ContactViewHolder
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact

class ContactItemAdapter(private val mContext: Context,
                                 private var contactList: List<Contact>?)
    : RecyclerView.Adapter<ContactViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val vista = mInflater.inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.contact = contactList!![position]
        holder.name.text = contactList!![position].name
        holder.email.text = contactList!![position].email

        holder.cardView.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:" + holder.contact!!.email)
            mContext.startActivity(emailIntent)
        }
    }

    override fun getItemCount(): Int {
        return contactList!!.size
    }

    fun setData(data: List<Contact>) {
        contactList = data
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contact: Contact? = null
        var cardView: MaterialCardView = itemView.findViewById(R.id.card_contact)
        var name: TextView = itemView.findViewById(R.id.text_contact_name)
        var email: TextView = itemView.findViewById(R.id.text_contact_email)

    }
}