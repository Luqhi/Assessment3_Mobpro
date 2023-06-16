package org.d3if0067.mobpro1.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if0067.mobpro1.R

class AboutFragment : Fragment(R.layout.fragment_about){
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("AboutFragment", "onAttach dijalankan")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("AboutFragment", "onCreate dijalankan")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("AboutFragment", "onCreateView dijalankan")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("AboutFragment", "onViewCreated dijalankan")
    }

    override fun onStart() {
        super.onStart()
        Log.i("AboutFragment", "onStart dijalankan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("AboutFragment", "onResume dijalankan")
    }

    override fun onPause() {
        Log.i("AboutFragment", "onPause dijalankan")
        super.onPause()
    }

    override fun onStop() {
        Log.i("AboutFragment", "onStop dijalankan")
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("AboutFragment", "onDestroyView dijalankan")
    }

    override fun onDestroy() {
        Log.i("AboutFragment", "onDestroy dijalankan")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("AboutFragment", "onDetach dijalankan")
        super.onDetach()
    }
}