package com.example.sportlog

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PerformanceFragment : Fragment() {

    private lateinit var etPerformance: EditText
    private lateinit var btnSavePerformance: Button
    private lateinit var tvSavedPerformance: TextView
    private lateinit var btnTakePhoto: Button
    private lateinit var ivCapturedPhoto: ImageView

    private var photoUri: Uri? = null

    // Registrar o contrato para capturar a foto e salvar
    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
        if (success) {
            photoUri?.let {
                val bitmap = BitmapFactory.decodeStream(requireContext().contentResolver.openInputStream(it))
                ivCapturedPhoto.setImageBitmap(bitmap)
            }
        } else {
            Log.e("PerformanceFragment", "A captura da foto falhou.")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_performance, container, false)

        etPerformance = view.findViewById(R.id.et_performance)
        btnSavePerformance = view.findViewById(R.id.btn_save_performance)
        tvSavedPerformance = view.findViewById(R.id.tv_saved_performance)
        btnTakePhoto = view.findViewById(R.id.btn_take_photo)
        ivCapturedPhoto = view.findViewById(R.id.iv_captured_photo)

        // Inicialmente, ocultar o TextView
        tvSavedPerformance.visibility = View.GONE

        // Lógica para salvar desempenho
        btnSavePerformance.setOnClickListener {
            val performanceText = etPerformance.text.toString()
            if (performanceText.isNotEmpty()) {
                tvSavedPerformance.text = "Desempenho Salvo: $performanceText"
                tvSavedPerformance.visibility = View.VISIBLE
            }
        }

        // Lógica para capturar foto
        btnTakePhoto.setOnClickListener {
            if (checkCameraPermission()) {
                capturePhoto()
            } else {
                requestCameraPermission()
            }
        }

        return view
    }

    // Verificar permissão de câmera
    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Solicitar permissão de câmera
    private fun requestCameraPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    // Criar arquivo para salvar a foto
    private fun createImageFile(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir("Pictures")
        val file = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
        return FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", file)
    }

    // Capturar a foto
    private fun capturePhoto() {
        photoUri = createImageFile()
        takePicture.launch(photoUri)
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1001
    }
}
