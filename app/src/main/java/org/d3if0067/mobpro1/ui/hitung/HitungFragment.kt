package org.d3if0067.mobpro1.ui.hitung

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.d3if0067.mobpro1.MainActivity
import org.d3if0067.mobpro1.R
import org.d3if0067.mobpro1.databinding.FragmentHitungBinding
import org.d3if0067.mobpro1.db.TiketDb
import org.d3if0067.mobpro1.model.HasilTiket
import org.d3if0067.mobpro1.model.KategoriTiket
import org.d3if0067.mobpro1.ui.list.ListFragment

class HitungFragment: Fragment(){
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = TiketDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("HitungFragment", "onAttach dijalankan")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("HitungFragment", "onCreate dijalankan")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

        Log.i("HitungFragment", "onCreateView dijalankan")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonHitung.setOnClickListener { hitungTiket() }
        binding.buttonClear.setOnClickListener { clear() }
        binding.shareButton.setOnClickListener { shareData() }
        binding.listButton.setOnClickListener {
            findNavController().navigate(R.id.action_hitungFragment_to_listFragment)
        }

        viewModel.getHasilTiket().observe(requireActivity(), { showResult(it) })

        Log.i("HitungFragment", "onViewCreate dijalankan")
    }

    override fun onStart() {
        super.onStart()
        Log.i("HitungFragment", "onStart dijalankan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("HitungFragment", "onResume dijalankan")
    }

    override fun onPause() {
        Log.i("HitungFragment", "onPause dijalankan")
        super.onPause()
    }

    override fun onStop() {
        Log.i("HitungFragment", "onStop dijalankan")
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("HitungFragment", "onDestroyView dijalankan")
    }

    override fun onDestroy() {
        Log.i("HitungFragment", "onDestroy dijalankan")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("HitungFragment", "onDetach dijalankan")
        super.onDetach()
    }

    private fun shareData() {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val seat = if (selectedId == R.id.button_cat1)
            getString(R.string.txtCat1)
        else
            getString(R.string.txtCat2)
        val message = getString(
            R.string.bagikan_template,
            binding.jmlhTiketInp.text,
            seat,
            binding.totalTextView.text,
            binding.kategoriTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    private fun hitungTiket() {
        val jumlah = binding.jmlhTiketInp.text.toString()
        if (TextUtils.isEmpty(jumlah)) {
            Toast.makeText(context, R.string.jumlah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, R.string.seat_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungTiket(
            jumlah.toFloat(),
            selectedId == R.id.button_cat1
        )
    }

    private fun clear(){
        val input1 = binding.jmlhTiketInp
        val selectedId = binding.radioGroup
        val kategori = binding.kategoriTextView
        val hasil = binding.totalTextView


        input1.text = null
        selectedId.clearCheck()
        kategori.text = "Kualitas Tempat Duduk: "
        hasil.text = "Total: 0"

        binding.buttonGroup.visibility = View.INVISIBLE
    }

    private fun getKategoriLabel(kategori: KategoriTiket): String {
        val stringRes = when (kategori) {
            KategoriTiket.BAIK -> R.string.baik
            KategoriTiket.BURUK -> R.string.buruk
        }
        return getString(stringRes)
    }

    private fun showResult(result: HasilTiket?) {
        if (result == null) return

        binding.totalTextView.text = getString(R.string.total_x, result.total)
        binding.kategoriTextView.text = getString(
            R.string.kategori_x,
            getKategoriLabel(result.kategoriTiket)
        )
        binding.buttonGroup.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}