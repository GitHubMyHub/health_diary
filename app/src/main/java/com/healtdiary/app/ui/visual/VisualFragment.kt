package com.healtdiary.app.ui.visual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healtdiary.app.R
import com.healtdiary.app.databinding.FragmentVisualBinding
import com.healtdiary.app.ui.data.DataViewModel

class VisualFragment : Fragment() {

    /**
     * Lazily initialize our [DataViewModel].
     */

    private val viewModel: VisualViewModel by lazy {
        ViewModelProvider(this).get(VisualViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVisualBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        return binding.root
    }
}