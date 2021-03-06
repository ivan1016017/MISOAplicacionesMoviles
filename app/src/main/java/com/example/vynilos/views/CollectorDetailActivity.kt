package com.example.vynilos.views

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.databinding.ActivityCollectorDetailBinding
import com.example.vynilos.viewmodels.CollectorDetailViewModel
import com.squareup.picasso.Picasso

class CollectorDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val collectorId = intent.getStringExtra("collectorId")
        if (collectorId != null) {
            initViewModel(collectorId.toInt())
        }

        binding = ActivityCollectorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbarText()
        handleBackClick()
        binding.tvDescription.movementMethod = ScrollingMovementMethod()

    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = ""
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun initViewModel(albumId: Number ) {
        val viewModel = ViewModelProvider(this).get(CollectorDetailViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            binding.tvCollectorName.text = it.name
            binding.tvCollectorEmail.text = it.email
            binding.tvCollectorPhone.text = it.telephone
            Picasso.get().load("https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png").into(binding.imageView)
        })
        viewModel.makeApiCall(albumId)
    }

}