package com.example.git_friends.ui.userprofilefragment



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentUserProfileBinding
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class UserProfileFragment : Fragment() {

    private val TAG: String = "@@@"

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var recieveInfo: String
    private val viewModel by lazy { ViewModelProvider(this)[UserProfileViewModel::class.java] }


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
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        _binding = FragmentUserProfileBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginUserProfileTextView.text = recieveInfo

        viewModel.getListUserRepoGitHub(recieveInfo).observe(viewLifecycleOwner, Observer {
            val avatarUrl = it[0].owner.avatar_url

            Glide.with(requireContext())
                .load(avatarUrl)
                .placeholder(R.drawable.ic_baseline_attribution_24)
                .into(binding.avatarUserProfileImageView)

            val users = arrayListOf<String>()

            it.forEach {
                users.add(it.name)
            }

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1, users
            )
            binding.loginUserProfileListView.adapter = arrayAdapter
        })

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}