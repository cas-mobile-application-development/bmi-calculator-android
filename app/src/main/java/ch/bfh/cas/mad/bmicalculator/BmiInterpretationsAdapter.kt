package ch.bfh.cas.mad.bmicalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BmiInterpretationsAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<BmiInterpretationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BmiInterpretationViewHolder {
        val singleItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.bmi_interpretation_item, parent, false)
        return BmiInterpretationViewHolder(singleItemView)
    }

    override fun onBindViewHolder(holder: BmiInterpretationViewHolder, position: Int) {
        holder.setText(data[position])
    }

    override fun getItemCount(): Int =
        data.count()
}