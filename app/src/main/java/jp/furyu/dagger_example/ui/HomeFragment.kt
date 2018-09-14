package jp.furyu.dagger_example.ui


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import jp.furyu.dagger_example.R
import jp.furyu.dagger_example.databinding.FragmentHomeBinding
import javax.inject.Inject
import android.arch.lifecycle.Observer
import android.util.Log
import jp.furyu.dagger_example.dto.GitHubProject
import jp.furyu.dagger_example.util.Result


class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter: ProjectAdapter

    val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeBinding? = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        adapter = ProjectAdapter(projectClickCallback)
        binding?.projectList?.adapter = adapter
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.projectList.observe(this, Observer<Result<List<GitHubProject>>>{
            if (it != null) {
                when (it) {
                    is Result.Success -> {
                        adapter.projectList = it.data
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: mvvm-sample-appではonCreateViewでやっているが、違いは何か？
//        val binding: FragmentHomeBinding? = DataBindingUtil.bind<FragmentHomeBinding>(view)
//
//        lifecycle.addObserver(homeViewModel)
//        binding.let {
//            it!!.homeViewModel = homeViewModel
//            it.setLifecycleOwner(this)
//        }
    }

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: GitHubProject) {
            // TODO: Timberを使えと警告がでる。Timberの使い方
            Log.i("HomeFragment", project.description.toString())

            // TODO: 画面遷移
//            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//                (activity as MainActivity).show(project)
//            }
        }
    }
}