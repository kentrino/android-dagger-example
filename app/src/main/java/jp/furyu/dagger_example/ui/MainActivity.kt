package jp.furyu.dagger_example.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import jp.furyu.dagger_example.R
import jp.furyu.dagger_example.util.setFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
