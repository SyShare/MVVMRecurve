package com.tangpj.recurve.dagger2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tangpj.recurve.databinding.FragmentRecurveRecyclerViewBinding
import com.tangpj.recurve.ui.creator.LoadingCreator
import com.tangpj.recurve.ui.creator.RecurveLoadingCreator
import com.tangpj.recurve.recyclerview.creator.Creator
import com.tangpj.recurve.recyclerview.adapter.ModulesAdapter
import com.tangpj.recurve.ui.creator.RecyclerViewInit
import dagger.android.support.DaggerFragment


open class RecurveDaggerListFragment
    : DaggerFragment(), LoadingCreator by RecurveLoadingCreator(), RecyclerViewInit {

    private val mAdapter = ModulesAdapter()
    private var lm: RecyclerView.LayoutManager? = null

    final override fun onCreateView(inflater: LayoutInflater,
                                    container: ViewGroup?,
                                    savedInstanceState: Bundle?): View? {
        val binding = onCreateBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    protected open fun initRecyclerView(rv: RecyclerView){
        lm?.let {
            rv.layoutManager = it
        }
        rv.adapter = mAdapter
    }

    open fun onCreateBinding(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): ViewDataBinding{
        val binding = FragmentRecurveRecyclerViewBinding.inflate(inflater, container, false)
        initRecyclerView(binding.rv)
        return binding
    }

    override fun addItemCreator(creator: Creator) {
        mAdapter.addCreator(creator)
    }

    override fun addItemCreator(index: Int, creator: Creator) {
        mAdapter.addCreator(index, creator)
    }

    override fun setLayoutManager(lm: RecyclerView.LayoutManager ){
        this.lm = lm
    }

}