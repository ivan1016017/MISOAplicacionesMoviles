package com.example.vynilos.views

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityCreateAlbumBinding
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Handler
import android.os.Looper

import android.widget.DatePicker
import com.example.vynilos.models.Album
import com.example.vynilos.models.Collector
import com.example.vynilos.models.Track
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class CreateAlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAlbumBinding
    private var datePickerDialog: DatePickerDialog? = null
    private var selected_date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setGenreSpinner()
        setRecordLabelSpinner()

        setDatePicker()
        initDatePicker()
        binding.datePickerButton.setText(getTodaysDate());

        setToolbarText()
        handleBackClick()
        setSaveButton()
    }

    private fun setSaveButton() {
        binding.saveBtn.setOnClickListener {
            val is_valid = validateForm()
            if(is_valid) {
                processForm()
            }
        }
    }

    private fun getTodaysDate(): String? {
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        var month = cal[Calendar.MONTH]
        month = month + 1
        val day = cal[Calendar.DAY_OF_MONTH]
        selected_date = "${year.toString()}-${month.toString()}-${day.toString()}"
        return makeDateString(day, month, year)
    }

    private fun initDatePicker() {
        val dateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date: String? = makeDateString(day, month, year)
                selected_date = "${year.toString()}-${month.toString()}-${day.toString()}"
                binding.datePickerButton.setText(date)

                //????? here happens change

            }

        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        val style: Int = AlertDialog.THEME_HOLO_LIGHT

        datePickerDialog = DatePickerDialog(this, style, dateSetListener, year, month, day)
    }

    private fun setDatePicker() {
        binding.datePickerButton.setOnClickListener {
            datePickerDialog?.show();
        }
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.create_album)
    }

    private fun setGenreSpinner() {
        val spinner: Spinner = binding.genreSpinner

        ArrayAdapter.createFromResource(
            this,
            R.array.genre_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }

    private fun setRecordLabelSpinner() {
        val spinner: Spinner = binding.recordLabelSpinner

        ArrayAdapter.createFromResource(
            this,
            R.array.record_label_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String? {
        return getMonthFormat(month).toString() + " " + day + " " + year
    }

    private fun getMonthFormat(month: Int): String? {
        if (month == 1) return "ENE"
        if (month == 2) return "FEB"
        if (month == 3) return "MAR"
        if (month == 4) return "ABR"
        if (month == 5) return "MAY"
        if (month == 6) return "JUN"
        if (month == 7) return "JUL"
        if (month == 8) return "AGO"
        if (month == 9) return "SEP"
        if (month == 10) return "OCT"
        if (month == 11) return "NOV"
        return if (month == 12) "DIC" else "ENE"
    }

    private fun validateForm(): Boolean {
        var valid = true
        var error_text = ""
        binding.tvErrors.text = ""

        if(isInvalidName()) {
            valid = false
            error_text = error_text + "Nombre del álbum requerido \n"
        }

        if(isInvalidCover()) {
            valid = false
            error_text = error_text + "La imágen del cover debe ser una URL correcta \n"
        }

        if(isInvalidDescription()) {
            valid = false
            error_text = error_text + "Descripción requerida \n"
        }

        if(!valid) {
            binding.tvErrors.text = error_text
        }

        return valid
    }

    private fun isInvalidName():Boolean {
        return binding.etName.text.isNullOrEmpty()
    }

    private fun isInvalidDescription():Boolean {
        return binding.etDescription.text.isNullOrEmpty()
    }

    private fun isInvalidCover():Boolean {
        val cover = binding.etCover.text
        return cover.isNullOrEmpty() || !android.util.Patterns.WEB_URL.matcher(cover).matches()
    }

    private fun processForm() {
        val view= this
        val name = binding.etName.text.toString()
        val description = binding.etDescription.text.toString()
        val cover = binding.etCover.text.toString()
        val genre = binding.genreSpinner.getSelectedItem().toString()
        val recordLabel = binding.recordLabelSpinner.getSelectedItem().toString()
        val releaseDate = selected_date


        val album = Album(
            id = null,
            name=name,
            description = description,
            cover = cover,
            genre = genre,
            recordLabel = recordLabel,
            releaseDate = releaseDate,
            tracks = emptyArray(),
        )

        val call = album.create()

        call.enqueue(object : Callback<Album> {
            override fun onFailure(call: Call<Album>, t: Throwable) {
                //#Need to figureout how to handle error
            }

            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                view.finish()
                val intent = Intent(view, AlbumsDetailActivity::class.java)
                intent.putExtra("albumId", response.body()?.id?.toString())
                view.startActivity(intent)
            }
        })

    }


}