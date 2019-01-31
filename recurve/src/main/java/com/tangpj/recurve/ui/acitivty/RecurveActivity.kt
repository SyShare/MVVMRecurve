package com.tangpj.recurve.ui.acitivty

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tangpj.recurve.R
import com.tangpj.recurve.databinding.ActivityRecurveBinding
import com.tangpj.recurve.databinding.FragmentNavigationBinding
import com.tangpj.recurve.ui.appbar
import com.tangpj.recurve.ui.creator.AppbarCreator
import com.tangpj.recurve.ui.creator.ContentCreate
import com.tangpj.recurve.ui.creator.RecurveAppbarCreator
import com.tangpj.recurve.ui.creator.RecurveContentCreate
import com.tangpj.recurve.ui.creator.ext.AppbarExt

abstract class RecurveActivity:
        AppCompatActivity(), ContentCreate {

    private lateinit var activityRecurveBinding: ActivityRecurveBinding

    private lateinit var appbarCreator: AppbarCreator
    private lateinit var contentCreate: ContentCreate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRecurveBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_recurve)
        contentCreate = RecurveContentCreate(activityRecurveBinding)
        appbarCreator = RecurveAppbarCreator(this, activityRecurveBinding)
    }

    override fun <Binding : ViewDataBinding> initContentBinding(@LayoutRes layoutId: Int): Binding
            = contentCreate.initContentBinding(layoutId)


    open fun <Binding : ViewDataBinding> initContentFragment(
            @NavigationRes graphResId: Int,
            @LayoutRes layoutId: Int = R.layout.fragment_navigation,
            @IdRes resId: Int = R.id.fragment_container): Binding{

        val binding: Binding = initContentBinding(layoutId)
        val view: View = ActivityCompat.requireViewById(this, resId)
        val navigationController = NavController(this)
        navigationController.setGraph(graphResId)
        view.setTag(androidx.navigation.R.id.nav_controller_view_tag, navigationController)
        return binding

    }

    fun appbar(init: AppbarExt.() -> Unit){
        appbar(appbarCreator, init)
    }
}