package org.d3if0067.mobpro1.ui.histori

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if0067.mobpro1.R
import org.d3if0067.mobpro1.databinding.FragmentHistoriBinding
import org.d3if0067.mobpro1.db.TiketDb

class HistoriFragment : Fragment(){
    private val viewModel: HistoriViewModel by lazy {
        val db = TiketDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("HistoriFragment", "onAttach dijalankan")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("HistoriFragment", "onCreate dijalankan")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHistoriBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        Log.i("HistoriFragment", "onCreateView dijalankan")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true) }

        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })

        Log.i("HistoriFragment", "onViewCreate dijalankan")
    }

    override fun onStart() {
        super.onStart()
        Log.i("HistoriFragment", "onStart dijalankan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("HistoriFragment", "onResume dijalankan")
    }

    override fun onPause() {
        Log.i("HistoriFragment", "onPause dijalankan")
        super.onPause()
    }

    override fun onStop() {
        Log.i("HistoriFragment", "onStop dijalankan")
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("HistoriFragment", "onDestroyView dijalankan")
    }

    override fun onDestroy() {
        Log.i("HistoriFragment", "onDestroy dijalankan")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("HistoriFragment", "onDetach dijalankan")
        super.onDetach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hapusData(){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                viewModel.hapusData()
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}