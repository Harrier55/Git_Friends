package com.example.git_friends.ui.listuserfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.git_friends.R
import com.example.git_friends.domain.UserEntity

class ListUserAdapterRecyclerView: RecyclerView.Adapter<ListUserAdapterRecyclerView.ListUserViewHolder>() {

    private var listUsers:List<UserEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshListRecyclerView(inputUserList: List<UserEntity>){
        this.listUsers = inputUserList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user,parent,false)
        return ListUserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.login.text = listUsers[position].login
        // todo add holder.avatar
    }





    class ListUserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val login: TextView = itemView.findViewById(R.id.item_list_user_login_text_view)
        val avatar: ImageView = itemView.findViewById(R.id.item_list_user_avatar_image_view)

    }

}