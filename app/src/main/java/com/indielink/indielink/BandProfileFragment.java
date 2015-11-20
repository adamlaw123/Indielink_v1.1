package com.indielink.indielink;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.indielink.indielink.Profile.UserRole;

public class BandProfileFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_band_profile, container, false);
        Switch RoleSwitch = (Switch) view.findViewById(R.id.switch3);
        RoleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                Toast.makeText(getActivity(), "Go to Band", Toast.LENGTH_SHORT);
                if (isChecked) {
                    UserRole.IsBand();


                } else {
                    UserRole.IsMusician();
                    Toast.makeText(getActivity(), "Go to Player", Toast.LENGTH_SHORT);

                }
            }
        });
        return inflater.inflate(R.layout.fragment_band_profile, container, false);
    }
}
