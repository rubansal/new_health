package com.nsit.thehealthcompany;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Calendar;
import java.util.Objects;

public class MainFragment extends Fragment {

    private Calendar calendar=null;
    private LinearLayout mainFragmentLayout;
    private ImageView previous;
    private ImageView next;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragment, container, false);
        mainFragmentLayout = view.findViewById(R.id.mainFragmentLayout);

        assert this.getArguments() != null;
        String date = this.getArguments().getString("date");
        // Set the title view to show the page number.
        ((TextView) view.findViewById(R.id.date_textView)).setText(date);

        previous=view.findViewById(R.id.previous);
        next=view.findViewById(R.id.next);
        ImageView updateWeightGifImageView = view.findViewById(R.id.updateWeightGifImageView);
        ImageView seePlansGifImageView = view.findViewById(R.id.seePlansGifImageView);

        Glide.with(Objects.requireNonNull(getActivity())).load(R.drawable.temp_update_weight).into(updateWeightGifImageView);
        //Glide.with(Objects.requireNonNull(getActivity())).load(R.drawable.temp_plans).into(seePlansGifImageView);

        calendar=Calendar.getInstance();

        view.findViewById(R.id.date_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialog mDatePicker = new DatePickerDialog(Objects.requireNonNull(getActivity()), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        //updateLabel();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment=(HomeFragment)MainFragment.this.getParentFragment();
                assert homeFragment != null;
                homeFragment.swap(+1);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment=(HomeFragment)MainFragment.this.getParentFragment();
                assert homeFragment != null;
                homeFragment.swap(-1);
            }
        });
        return view;
    }
}
