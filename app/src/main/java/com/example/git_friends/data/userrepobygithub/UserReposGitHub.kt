import com.google.gson.annotations.SerializedName



data class UserReposGitHub (

//	@SerializedName("id") val id : Int,
//	@SerializedName("node_id") val node_id : String,
	@SerializedName("name") val name : String,
//	@SerializedName("full_name") val full_name : String,
	@SerializedName("owner") val owner : Owner,

)