package me.venko.tmdbclient.presentation.root

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import me.venko.tmdbclient.R
import me.venko.tmdbclient.di.Injector
import me.venko.tmdbclient.presentation.common.activity.BaseActivity

/**
 * @author Victor Kosenko
 *
 */
class RootActivity : BaseActivity() {

    private lateinit var rootViewModel: AppRootViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.appComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        rootViewModel = ViewModelProviders.of(this).get(AppRootViewModel::class.java)
        if (savedInstanceState == null) {
            rootViewModel.coldStart()
        }
    }

}
