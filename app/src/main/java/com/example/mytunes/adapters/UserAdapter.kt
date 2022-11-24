package com.example.mytunes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytunes.R
import com.example.mytunes.models.UserModel
import kotlinx.android.synthetic.main.row_users.view.*

class UserAdapter(
    var items: List<UserModel>,
): RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_users, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = items[position]

        //set data
        holder.itemView.nameTv.text = currentUser.name
        holder.itemView.phoneTv.text = "${currentUser.phone}"



        //handle on click listener
        holder.itemView.setOnClickListener{
            //navigates to bits
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}