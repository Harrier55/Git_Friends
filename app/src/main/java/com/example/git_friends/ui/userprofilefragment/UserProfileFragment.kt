package com.example.git_friends.ui.userprofilefragment


import UserReposGitHub
import android.database.Observable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.git_friends.R
import com.example.git_friends.data.App
import com.example.git_friends.databinding.FragmentListUsersBinding
import com.example.git_friends.databinding.FragmentUserProfileBinding
import com.example.git_friends.domain.UserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path



import android.util.Log
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

interface RetrofitUserProfile {

    @GET("users/{user}/repos")
    fun loadUsers(@Path("user") user:String): Single<List<UserReposGitHub>>
}

class UserProfileFragment : Fragment() {

    private val TAG: String = "@@@"

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var recieveInfo: String

    private val baseURl: String = "https://api.github.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            recieveInfo = bundle.getString("KEY").toString()

            Toast.makeText(requireContext(), recieveInfo, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        _binding = FragmentUserProfileBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginUserProfileTextView.text = recieveInfo

        val retrofit = App.instance.retrofitInstanceRx(baseURl)
        val serviceApi = retrofit.create(RetrofitUserProfile::class.java)



         serviceApi.loadUsers(recieveInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                binding.loginUserProfileTextView.text = it.toString()
            }

            .doOnError{
                Log.d(TAG, "onError: $it")
            }
            .subscribe()


    }


    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }

}