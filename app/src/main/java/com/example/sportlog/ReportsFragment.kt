package com.example.sportlog// com.example.sportlog.ReportsFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ReportsFragment : Fragment() {

    private lateinit var btnViewReport: Button
    private lateinit var tvReport: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reports, container, false)

        btnViewReport = view.findViewById(R.id.btn_view_report)
        tvReport = view.findViewById(R.id.tv_report)

        // Ao clicar no botão, exibir um relatório fictício
        btnViewReport.setOnClickListener {
            val reportData = """
                Relatório Fictício:
                - Total de Treinos: 10
                - Melhor Desempenho: 85%
                - Média de Desempenho: 72%
            """.trimIndent()
            tvReport.text = reportData
        }

        return view
    }
}
