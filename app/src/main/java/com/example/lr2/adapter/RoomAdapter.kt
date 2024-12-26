package com.example.lr2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lr2.R
import com.example.lr2.model.Room

class RoomAdapter(
    private val rooms: MutableList<Room>,
    private val onItemClick: (Room) -> Unit,
    private val onDeleteClick: (Room) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        holder.bind(room)
    }

    override fun getItemCount(): Int = rooms.size

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.roomNameTextView)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

        fun bind(room: Room) {
            nameTextView.text = room.name
            itemView.setOnClickListener { onItemClick(room) }
            deleteButton.setOnClickListener { onDeleteClick(room) }
        }
    }
}