package com.example.finalproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FlightsFragment extends Fragment {
    private ViewModel viewModel;

    public FlightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(FlightsViewModel.class);
        }

        return inflater.inflate(R.layout.fragment_flights, container, false);
    }

}
