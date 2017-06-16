package mvp.view.androidui.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import franjam.mvpdemo.R
import franjam.mvpdemo.mvp.view.contract.EntryPointView
import mvp.model.GiphyData
import mvp.view.androidui.adapters.GiphyAdapter
import mvpdemo.mvp.presenter.EntryPointPresenter

class EntryPointActivity : AppCompatActivity(), EntryPointView {

    private val presenter: EntryPointPresenter by lazy { EntryPointPresenter(this) }
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.main_recycler_view) as RecyclerView
    }

    private var adapter: GiphyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_entry_point_activity)
    }

    override fun onResume() {
        super.onResume()
        presenter.onCreate()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun updateRecyclerViewData(giphyData: GiphyData) {
        val layoutManager = LinearLayoutManager(this as Context)
        recyclerView.layoutManager = layoutManager
        adapter = GiphyAdapter(giphyData)
        recyclerView.adapter = adapter
    }

    override fun displayInvalidDataMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}