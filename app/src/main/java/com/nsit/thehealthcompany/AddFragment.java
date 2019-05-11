package com.nsit.thehealthcompany;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nsit.thehealthcompany.DTO.Item;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item> items =new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        recyclerView=view.findViewById(R.id.rvbars);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        items.add(new Item(R.drawable.lean_bar_icon,"Add Lean Bar","Recommended: 552-737 kcal"));
        items.add(new Item(R.drawable.green_tea,"Add Lean Green Tea","Recommended: 394-526 kcal"));
        items.add(new Item(R.drawable.meal,"Add Meal","Recommended: 512-670 kcal"));
        items.add(new Item(R.drawable.exercise,"Add Exercise","Recommended: 0-236 kcal"));

        mAdapter= new AddAdapter(items,getActivity());
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}