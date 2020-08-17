package com.healtdiary.app.ui.data

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.healtdiary.app.R
import com.healtdiary.app.data.Data
import com.healtdiary.app.databinding.ListDataItemBinding
import com.healtdiary.app.util.HealthCalculator
import java.security.AccessController.getContext

class DataAdapter(
    val onClickListener: OnClickListener,
    val onClickListenerInfo: OnClickListenerInfo,
    val onClickListenerOption: OnClickListenerOption
) :
    ListAdapter<Data, DataAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder private constructor(private var binding: ListDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            clickListener: OnClickListener,
            clicklistenerInfo: OnClickListenerInfo,
            clicklistenerOption: OnClickListenerOption,
            data: Data
        ) {
            binding.property = data
            binding.clickListener = clickListener
            binding.clickListenerInfo = clicklistenerInfo
            binding.clickListenerOption = clicklistenerOption
            binding.executePendingBindings()

            /*Log.d("OMG", binding.txtViewHeight.text.toString())
            Log.d("OMG", binding.txtViewWeight.text.toString())*/

            val converter = HealthCalculator()

            // BMI Berechnen
            val bmiColor = converter.calculateBMI(
                binding.txtViewGender.text.toString(),
                binding.txtViewHeight.text.toString().toInt(),
                binding.txtViewWeight.text.toString().toInt()
            )

            ImageViewCompat.setImageTintMode(
                binding.imageOverViewWeight,
                PorterDuff.Mode.SRC_ATOP
            )
            ImageViewCompat.setImageTintList(
                binding.imageOverViewWeight,
                ColorStateList.valueOf(Color.parseColor(bmiColor))
            )

            // Systol
            val systol = converter.getSystol(binding.txtViewBloodSystol.text.toString().toInt())

            ImageViewCompat.setImageTintMode(
                binding.imageOverViewSystol,
                PorterDuff.Mode.SRC_ATOP
            )
            ImageViewCompat.setImageTintList(
                binding.imageOverViewSystol,
                ColorStateList.valueOf(Color.parseColor(systol))
            )

            // Diastol
            val diastol = converter.getDiastol(binding.txtViewBloodDiastol.text.toString().toInt())

            ImageViewCompat.setImageTintMode(
                binding.imageOverViewDiastol,
                PorterDuff.Mode.SRC_ATOP
            )
            ImageViewCompat.setImageTintList(
                binding.imageOverViewDiastol,
                ColorStateList.valueOf(Color.parseColor(diastol))
            )

            // Puls
            val pulse = converter.calculatePuls(
                binding.txtViewGender.text.toString(),
                binding.txtViewPulse.text.toString().toInt()
            )

            ImageViewCompat.setImageTintMode(
                binding.imageOverViewPulse,
                PorterDuff.Mode.SRC_ATOP
            )
            ImageViewCompat.setImageTintList(
                binding.imageOverViewPulse,
                ColorStateList.valueOf(Color.parseColor(pulse))
            )

            // Sugar
            val sugar =
                converter.calculateBloodSugar(binding.txtViewBloodSugar.text.toString().toInt())

            ImageViewCompat.setImageTintMode(
                binding.imageOverViewBloodSugar,
                PorterDuff.Mode.SRC_ATOP
            )
            ImageViewCompat.setImageTintList(
                binding.imageOverViewBloodSugar,
                ColorStateList.valueOf(Color.parseColor(sugar))
            )

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListDataItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val nightItem = getItem(position) as Data
        holder.bind(onClickListener, onClickListenerInfo, onClickListenerOption, nightItem)

    }

    class OnClickListener(val clicklistener: (placeProperty: Data) -> Unit) {
        fun onClick(placeProperty: Data) = clicklistener(placeProperty)
    }

    class OnClickListenerOption(val clicklistenerOption: (dataProperty: Data) -> Unit) {
        fun onClick(dataProperty: Data) = clicklistenerOption(dataProperty)
    }

    class OnClickListenerInfo(val clicklistenerInfo: (destination: String, dataProperty: Data) -> Unit) {
        fun onClick(destination: String, dataProperty: Data) =
            clicklistenerInfo(destination, dataProperty)
    }

}