package com.healtdiary.app.ui.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.healtdiary.app.R
import com.healtdiary.app.data.Data
import com.healtdiary.app.databinding.FragmentDataBinding
import com.healtdiary.app.util.HealthCalculator

class DataFragment : Fragment() {

    val TAG: String = "DataFragment"

    /**
     * Lazily initialize our [DataViewModel].
     */

    /*private val viewModel: DataViewModel by lazy {
        ViewModelProvider(this).get(DataViewModel::class.java)
    }*/

    private lateinit var viewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDataBinding.inflate(inflater, container, false)

        viewModel = activity?.run {
            ViewModelProvider(this)[DataViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.recyclerData.adapter = DataAdapter(DataAdapter.OnClickListener {
            Log.d(TAG, it.id.toString())
            //viewModel.setEditMode()
            findNavController().navigate(
                DataFragmentDirections.actionNavigationHomeToNavigationEntry(
                    it
                )
            )
        }, DataAdapter.OnClickListenerInfo { destination: String, data: Data ->

            val converter = HealthCalculator()

            when (destination) {
                "weight" -> {
                    // BMI Berechnen
                    val bmiColor = converter.calculateBMI(
                        data.gender!!,
                        data.height.toInt(),
                        data.weight.toInt(),
                        true
                    )
                    Toast.makeText(context, bmiColor, Toast.LENGTH_SHORT).show()
                }
                "systol" -> {
                    val systol = converter.getSystol(data.bloodSystol.toInt(), true)
                    Toast.makeText(context, systol, Toast.LENGTH_SHORT).show()
                }
                "diastol" -> {
                    val diastol = converter.getDiastol(data.bloodDiastol.toInt(), true)
                    Toast.makeText(context, diastol, Toast.LENGTH_SHORT).show()

                }
                "pulse" -> {
                    val pulse = converter.calculatePuls(
                        data.gender!!,
                        data.pulse.toInt(),
                        true
                    )
                    Toast.makeText(context, pulse, Toast.LENGTH_SHORT).show()
                }
                "sugar" -> {
                    val sugar =
                        converter.calculateBloodSugar(data.bloodSugar.toInt(), true)
                    Toast.makeText(context, sugar, Toast.LENGTH_SHORT).show()
                }
            }

            //Toast.makeText(context, s, Toast.LENGTH_LONG).show()

        }, DataAdapter.OnClickListenerOption {
            Log.d(TAG, it.id.toString())
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.delete_entry_title)
                .setMessage(R.string.delete_entry_body) // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(
                    R.string.yes
                ) { dialog, which ->
                    // Continue with delete operation
                    it.id?.let { it1 -> viewModel.deleteEntry(it1) }
                    viewModel.getData()
                    Toast.makeText(
                        requireContext(),
                        resources.getText(R.string.entry_deleted),
                        Toast.LENGTH_LONG
                    ).show()
                } // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.no, null)
                .setIcon(R.drawable.ic_delete)
                .show()
        })

        return binding.root
    }
}