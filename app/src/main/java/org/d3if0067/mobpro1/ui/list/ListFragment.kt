package org.d3if0067.mobpro1.ui.list

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0067.mobpro1.MainActivity
import org.d3if0067.mobpro1.databinding.FragmentListKonserBinding
import org.d3if0067.mobpro1.network.ApiStatus

class ListFragment : Fragment() {
    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    private lateinit var binding: FragmentListKonserBinding
    private lateinit var myAdapter: ListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("ListFragment", "onAttach dijalankan")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ListFragment", "onCreate dijalankan")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListKonserBinding.inflate(layoutInflater, container, false)
        myAdapter = ListAdapter()

        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }

        Log.i("ListFragment", "onCreateView dijalankan")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        viewModel.scheduleUpdater(requireActivity().application)

        Log.i("ListFragment", "onViewCreate dijalankan")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ListFragment", "onStart dijalankan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ListFragment", "onResume dijalankan")
    }

    override fun onPause() {
        Log.i("ListFragment", "onPause dijalankan")
        super.onPause()
    }

    override fun onStop() {
        Log.i("ListFragment", "onStop dijalankan")
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("ListFragment", "onDestroyView dijalankan")
    }

    override fun onDestroy() {
        Log.i("ListFragment", "onDestroy dijalankan")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("ListFragment", "onDetach dijalankan")
        super.onDetach()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}