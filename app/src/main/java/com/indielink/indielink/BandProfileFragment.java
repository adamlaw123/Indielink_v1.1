package com.indielink.indielink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.indielink.indielink.Network.GetProfilePicture;
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

        // Set Profile Image TODO: Put correct Server URL into GetProfilePicture()
        ImageView ProfilePicture = (ImageView) view.findViewById(R.id.BandProfilePicture);
        new GetProfilePicture("Server URL",ProfilePicture).execute();

        Switch RoleSwitch = (Switch) view.findViewById(R.id.ChangeRole);
        RoleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                Log.v("Switch State=", "" + isChecked);
                if (isChecked) {
                    Toast.makeText(getActivity(), "Band Admin", Toast.LENGTH_SHORT);
                    UserRole.IsBand();
                }
                else {
                    UserRole.IsMusician();
                    Toast.makeText(getActivity(), "Musician", Toast.LENGTH_SHORT);
                }
            }
        });
        return view;
    }
}
