package com.example.git_friends.ui.listuserfragment

import com.example.git_friends.domain.UserEntity

interface OnClickItemListUsersFragmentRecyclerView {
    fun onClickItemUser(userEntity: UserEntity)
}