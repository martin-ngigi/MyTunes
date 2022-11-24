package com.example.mytunes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytunes.R
import com.example.mytunes.models.BitModel
import kotlinx.android.synthetic.main.row_bits.view.*
import kotlinx.android.synthetic.main.row_users.view.*

class BitsAdapter(
    var items: List<BitModel>
) :RecyclerView.Adapter<BitsAdapter.BitViewHolder>(){


    inner class BitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_users, parent, false)
        return BitViewHolder(view)
    }

    override fun onBindViewHolder(holder: BitViewHolder, position: Int) {
        val currentBit = items[position]

        //set data
        holder.itemView.bitNameTv.text = currentBit.bitName
        holder.itemView.bitUrlTv.text = "${currentBit.bitUrl}"



        //handle on click listener
        holder.itemView.setOnClickListener{
            //navigates to play bit
        }
    }

    override fun getItemCount(): Int {
        return items.size

    }

}