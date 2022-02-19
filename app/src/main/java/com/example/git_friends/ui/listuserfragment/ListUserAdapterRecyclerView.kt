package com.example.git_friends.ui.listuserfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.git_friends.R
import com.example.git_friends.domain.UserEntity

class ListUserAdapterRecyclerView(private var onClickItemListUsersFragment: OnClickItemListUsersFragmentRecyclerView) :
    RecyclerView.Adapter<ListUserAdapterRecyclerView.ListUserViewHolder>() {

    private var listUsers: List<UserEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshListRecyclerView(inputUserList: List<UserEntity>) {
        this.listUsers = inputUserList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return ListUserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.login.text = listUsers[position].login
        Glide.with(holder.itemView.context)
            .load(listUsers[position].avatar)
            .placeholder(R.drawable.ic_baseline_attribution_24)
            .into(holder.avatar)

        holder.login.setOnClickListener {

            onClickItemListUsersFragment.onClickItemUser(listUsers[position])
        }
        holder.deleteButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, listUsers[position].login, Toast.LENGTH_SHORT)
                .show()
        }
    }

    class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val login: TextView = itemView.findViewById(R.id.item_list_user_login_text_view)
        val avatar: ImageView = itemView.findViewById(R.id.item_list_user_avatar_image_view)
        val deleteButton: ImageButton = itemView.findViewById(R.id.item_list_user_delete_button)
    }

}