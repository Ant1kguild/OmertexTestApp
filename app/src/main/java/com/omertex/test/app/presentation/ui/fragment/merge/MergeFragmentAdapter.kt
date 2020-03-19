package com.omertex.test.app.presentation.ui.fragment.merge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.omertex.test.app.R
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.databinding.RvItemMergeBinding

class MergeFragmentAdapter(private val onViewClick: (MergeModel) -> Unit) :
    RecyclerView.Adapter<MergeFragmentAdapter.ViewHolder>() {

    private var data: List<MergeModel> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            RvItemMergeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = data[position]
        holder.bind(value)
        holder.itemView.setOnClickListener {
            onViewClick(value)
        }
    }

    fun setData(mergeData: List<MergeModel>) {
        this.data = mergeData
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "VocabularyRVAdapter"
    }

    class ViewHolder(private val view: RvItemMergeBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(model: MergeModel) {
            view.ivImageSmall.load(model.urls?.small){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
                memoryCachePolicy(CachePolicy.ENABLED)
            }
            view.tvUserName.text = model.username
            view.tvEmail.text = model.email
            view.executePendingBindings()
        }
    }
}