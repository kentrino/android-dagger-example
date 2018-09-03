package jp.furyu.dagger_example.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import jp.furyu.dagger_example.R
import jp.furyu.dagger_example.util.setFragment
import jp.furyu.dagger_example.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : DaggerAppCompatActivity() {
    // TODO: 必要？
//    companion object {
//        fun start(context: Context) {
//            context.startActivity(Intent(context, MainActivity::class.java))
//        }
//    }

    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding
        setFragment(R.id.frame_main_content, ::HomeFragment)
    }
}
