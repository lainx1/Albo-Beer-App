package com.lain.baseapp

import android.app.Application
import com.lain.baseapp.data.DatabaseRoom
import com.lain.baseapp.data.repository.ModelRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * This class is the application.
 */
@HiltAndroidApp
class BaseApp: Application()