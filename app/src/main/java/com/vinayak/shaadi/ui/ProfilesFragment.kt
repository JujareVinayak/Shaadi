package com.vinayak.shaadi.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.vinayak.shaadi.R
import com.vinayak.shaadi.databinding.ProfilesFragmentBinding
import com.vinayak.shaadi.utils.Status
import com.vinayak.shaadi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilesFragment: Fragment(R.layout.profiles_fragment) {
    private lateinit var  builder: AlertDialog
    private val mainViewModel: MainViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ProfilesFragmentBinding.bind(view)
        builder = setProgressDialog(requireContext(), "Loading...")
        mainViewModel.profiles.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    builder.dismiss()
                    it.data.let { res ->
                        mainViewModel.insertProfiles(res!!)
                        Log.d("Shaadi", "onViewCreated: "+res!!)
                    }
                }
                Status.LOADING -> {
                    builder.show()
                }
                Status.ERROR -> {
                    builder.dismiss()
                    val profiles = mainViewModel.getOfflineProfiles()
                    Log.d("Shaadi", "onViewCreated: "+profiles)
                    Snackbar.make(view, it.message!!, Snackbar.LENGTH_SHORT).show()
                }

            }
        }
        )
    }

    private fun setProgressDialog(context: Context, message: String):AlertDialog {
        val llPadding = 30
        val ll = LinearLayout(context)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam

        val progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam

        llParam = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(context)
        tvText.text = message
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20.toFloat()
        tvText.layoutParams = llParam

        ll.addView(progressBar)
        ll.addView(tvText)

        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        builder.setView(ll)

        val dialog = builder.create()
        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window?.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window?.attributes = layoutParams
        }
        return dialog
    }
}