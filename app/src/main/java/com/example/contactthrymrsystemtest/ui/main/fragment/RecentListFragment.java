package com.example.contactthrymrsystemtest.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactthrymrsystemtest.Contact;
import com.example.contactthrymrsystemtest.R;
import com.example.contactthrymrsystemtest.ui.main.model.PageViewModel;
import com.example.contactthrymrsystemtest.ui.main.adapter.CustomAdapterForRecyclerView;
import com.example.contactthrymrsystemtest.ui.main.database.DBHelper;

import java.util.ArrayList;

public class RecentListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private RecyclerView recyclerView;

    private DBHelper dbHelper;

    private ArrayList<Contact> contacts = new ArrayList<>();

    private RecyclerView.LayoutManager mLayoutManager;

    public static RecentListFragment newInstance(int index) {
        RecentListFragment fragment = new RecentListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getActivity());
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact_list, container, false);
        recyclerView = root.findViewById(R.id.contact_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        contacts = dbHelper.getRecentList();
        CustomAdapterForRecyclerView customAdapterForRecyclerView = new CustomAdapterForRecyclerView(contacts, getActivity());
        recyclerView.setAdapter(customAdapterForRecyclerView);
        return root;
    }
}