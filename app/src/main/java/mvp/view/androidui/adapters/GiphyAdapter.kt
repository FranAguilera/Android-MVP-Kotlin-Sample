package mvp.view.androidui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import franjam.mvpdemo.R
import mvp.model.GiphyData


class GiphyAdapter(val giphyData: GiphyData) : RecyclerView.Adapter<GiphyAdapter.GiphyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return GiphyViewHolder(view)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.setGiphyImage(giphyData.data[position].images.fixedHeightImage.url)
    }

    override fun getItemCount(): Int {
        return giphyData.data.size
    }

    class GiphyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbNail: ImageView = itemView.findViewById(R.id.grid_item_image_view) as ImageView

        fun setGiphyImage(url: String) {
            Glide.with(itemView.context).load(url).into(thumbNail)
        }
    }
}
