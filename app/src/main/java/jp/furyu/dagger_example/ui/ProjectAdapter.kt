package jp.furyu.dagger_example.ui

import android.databinding.DataBindingUtil
import android.support.annotation.Nullable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.furyu.dagger_example.R

import jp.furyu.dagger_example.databinding.ProjectListRowBinding
import jp.furyu.dagger_example.dto.GitHubProject
import kotlin.properties.Delegates


class ProjectAdapter: RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    var projectList: List<GitHubProject> by Delegates.observable(emptyList()) { _, old, new ->
        // TODO: dispatchUpdateToの役割
        calculateDiff(old, new).dispatchUpdatesTo(this)
    }
    private var projectClickCallback: ProjectClickCallback? = null

    constructor(projectClickCallback: ProjectClickCallback?) {
        this.projectClickCallback = projectClickCallback
    }

    // 必要なオーバーライドその1
    // ViewHolderの生成時のコールバック
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding: ProjectListRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.project_list_row, parent, false)

        // TODO: これを書かないとリークする？
        // https://qiita.com/nitakan/items/b57938702fe5bd964e3f
        // binding.setLifecycleOwner(context)

        // xmlのdataのところを書くと生えるメソッド
        binding.callback = projectClickCallback
        return ProjectViewHolder(binding)
    }

    // 必要なオーバーライドその2
    override fun getItemCount(): Int {
        return projectList.size
    }

    // 必要なオーバーライドその3
    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project = projectList[position]
        holder.binding.executePendingBindings()
    }

    // Kotlin function parameters are final. There is no val or final keyword because that's the default (and can't be changed).

    fun calculateDiff(
            old: List<GitHubProject>,
            new: List<GitHubProject>,
            detectMoves: Boolean = false
    ): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(DiffCallback(old, new), detectMoves)
    }

    // TODO: 目的
    class ProjectViewHolder(val binding: ProjectListRowBinding) : RecyclerView.ViewHolder(binding.getRoot())

    // https://qiita.com/kubode/items/92c1190a6421ba055cc0
    class DiffCallback(
            private val old: List<GitHubProject>,
            private val new: List<GitHubProject>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = old.size
        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].url == new[newItemPosition].url
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].url == new[newItemPosition].url
        }
    }
}
