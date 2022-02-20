package com.example.git_friends.data.userentityrepo

import com.example.git_friends.domain.UserEntity
import com.example.git_friends.domain.UserEntityUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class UserEntityRepo : UserEntityUseCase {

    private val listUsers: MutableList<UserEntity> =
        mutableListOf<UserEntity>() as ArrayList<UserEntity>


    // CRUD
    override fun createUser(userEntity: UserEntity) {
        listUsers.add(userEntity)
    }

    override fun readUsersList(): List<UserEntity> {
        return ArrayList<UserEntity>(listUsers)
    }

    override fun updateUser() {
        // todo
    }

    override fun deleteUser(userEntity: UserEntity): Boolean {
        for (user in listUsers) {
            if (user.id == userEntity.id) {
                listUsers.remove(user)
                return true
            }
        }
        return false
    }

    override val observableListUserEntity: Observable<List<UserEntity>>
        get() = Observable.empty()


    override val singleListUser: Single<List<UserEntity>>
        get() {
           return Single.just(listUsers)
        }

    fun generateTestListUser(){
        createUser(UserEntity(1,"kshalnov", "https://avatars.githubusercontent.com/u/90893975?v=4"))
        createUser(UserEntity(2,"Harrier55","https://avatars.githubusercontent.com/u/90504533?v=4"))
        createUser(UserEntity(4,"Rogoz208","https://avatars.githubusercontent.com/u/38566187?v=4"))
        createUser(UserEntity(6,"niqmarin","https://avatars.githubusercontent.com/u/87613607?v=4"))

    }



}