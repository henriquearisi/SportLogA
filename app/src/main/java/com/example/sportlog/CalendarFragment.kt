// CalendarFragment.kt
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sportlog.R
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var btnAddTraining: Button
    private lateinit var tvTrainingMessage: TextView
    private var selectedDate: String = ""

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        calendarView = view.findViewById(R.id.calendarView)
        btnAddTraining = view.findViewById(R.id.btn_add_training)
        tvTrainingMessage = view.findViewById(R.id.tv_training_message)

        // Formatar data
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // Listener para capturar a data selecionada
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        }

        // Ao clicar no botão, exibir a mensagem
        btnAddTraining.setOnClickListener {
            if (selectedDate.isNotEmpty()) {
                tvTrainingMessage.text = "Treino para o dia $selectedDate marcado"
            } else {
                tvTrainingMessage.text = "Por favor, selecione uma data"
            }
        }

        return view
    }
}
