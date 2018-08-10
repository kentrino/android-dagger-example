package jp.furyu.dagger_example.dto

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
// TODO: data classの記法
// dateは使えるのか
// dto以下にあるのが妥当なのか
data class GitHubProject(
    var id: Long,
    var name: String?,
    var full_name: String?,
    var description: String?,
    var url: String?
)
