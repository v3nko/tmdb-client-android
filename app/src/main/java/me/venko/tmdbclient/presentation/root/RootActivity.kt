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

        // This approach allows us detect if user was using multi-window mode with a small sized
        // window which is detected as phone layout and than returned to tablet layout in some way
        // (by returning app to full screen or stretching window to a larger size). In that case we
        // should re-init master screen fragment as it can contain more than one fragment in stack.
        val modeChangedToTablet = isModeChangedToTablet(savedInstanceState)

        if (savedInstanceState == null || modeChangedToTablet) {
            rootViewModel.coldStart(isTablet)
        }
    }

}
