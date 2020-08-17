package com.healtdiary.app.ui.entry

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.healtdiary.app.data.Data
import com.healtdiary.app.databinding.FragmentEntryBinding
import com.healtdiary.app.ui.data.DataViewModel


class EntryFragment : Fragment() {

    val TAG = "EntryFragment"

    /**
     * Lazily initialize our [DataViewModel].
     */

    private lateinit var viewModel: DataViewModel

    /*private val viewModel: DataViewModel by lazy {
        ViewModelProvider(this).get(DataViewModel::class.java)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEntryBinding.inflate(inflater, container, false)


        viewModel = activity?.run {
            ViewModelProvider(this)[DataViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        try {
            val item = EntryFragmentArgs.fromBundle(requireArguments())
            setData(binding, item.entry)
            //viewModel.setEditMode()
        } catch (e: Throwable) {
            Log.d(TAG, "No Entry selected")
        }

        binding.radioButtonMale.setOnClickListener {
            viewModel.setData("radioButtonMale", "1")
        }

        binding.radioButtonFemale.setOnClickListener {
            viewModel.setData("radioButtonFemale", "2")
        }

        binding.editTextHeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here
                // yourEditText...
                Log.d(TAG, s.toString())
                viewModel.setData("editTextHeight", s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        binding.editTextWeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here
                // yourEditText...
                Log.d(TAG, s.toString())
                viewModel.setData("editTextWeight", s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        binding.editTextSystol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here
                // yourEditText...
                Log.d(TAG, s.toString())
                viewModel.setData("editTextSystol", s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        binding.editTextDiastol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here
                // yourEditText...
                Log.d(TAG, s.toString())
                viewModel.setData("editTextDiastol", s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        binding.editTextPulse.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here
                // yourEditText...
                Log.d(TAG, s.toString())
                viewModel.setData("editTextPulse", s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        binding.editTextBloodSugar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here
                // yourEditText...
                Log.d(TAG, s.toString())
                viewModel.setData("editTextBloodSugar", s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    fun setData(binding: FragmentEntryBinding, data: Data?) {

        // Fields
        if (data?.gender == "1") {
            binding.radioButtonMale.isChecked = true
        } else {
            binding.radioButtonFemale.isChecked = true
        }
        binding.editTextHeight.setText(data?.height)
        binding.editTextWeight.setText(data!!.weight)
        binding.editTextSystol.setText(data.bloodSystol)
        binding.editTextDiastol.setText(data.bloodDiastol)
        binding.editTextPulse.setText(data.pulse)
        binding.editTextBloodSugar.setText(data.bloodSugar)

        // Edit Field
        if (data.gender == "1") {
            viewModel.setData("radioButtonMale", "1")
        } else {
            viewModel.setData("radioButtonFemale", "2")
        }

        viewModel.setData("editTextId", data.id.toString())
        viewModel.setData("editTextHeight", data.height)
        viewModel.setData("editTextWeight", data.weight)
        viewModel.setData("editTextSystol", data.bloodSystol)
        viewModel.setData("editTextDiastol", data.bloodDiastol)
        viewModel.setData("editTextPulse", data.pulse)
        viewModel.setData("editTextBloodSugar", data.bloodSugar)
    }

}