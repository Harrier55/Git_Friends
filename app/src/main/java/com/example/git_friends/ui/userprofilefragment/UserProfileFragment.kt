package com.example.git_friends.ui.userprofilefragment

import UserReposGitHub
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

interface RetrofitUserProfile {

    @GET("users/{user}/repos")
    fun loadUsers(@Path("user") user:String): Call<List<UserReposGitHub>>
}




class UserProfileFragment : Fragment() {

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

        val retrofit = App.instance.retrofitInstance(baseURl)
        val retoService = retrofit.create(RetrofitUserProfile::class.java)
        val call = retoService.loadUsers(recieveInfo)

        call.enqueue(object : Callback<List<UserReposGitHub>>{
            override fun onResponse(
                call: Call<List<UserReposGitHub>>,
                response: Response<List<UserReposGitHub>>
            ) {
                binding.loginUserProfileTextView.text = response.body().toString()
            }

            override fun onFailure(call: Call<List<UserReposGitHub>>, t: Throwable) {
              //  TODO("Not yet implemented")
            }


        })

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}