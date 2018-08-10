package jp.furyu.dagger_example.api


import jp.furyu.dagger_example.dto.GitHubProject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<GitHubProject>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") peoject: String): Call<GitHubProject>

    companion object {
        const val BASE_URL = "https://api.github.com"
        const val NAME = "for_github"
    }
}
