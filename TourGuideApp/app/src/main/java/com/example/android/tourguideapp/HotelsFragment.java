package com.example.android.tourguideapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelsFragment extends Fragment {


    public HotelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);


        final ArrayList<Location> locations = new ArrayList<>();

        Location location = new Location(R.string.hotel_weitzer_name, R.string.hotel_weitzer_address,
                R.string.hotel_weitzer_postcode, R.string.hotel_weitzer_telephone, R.string.hotel_weitzer_description,
                R.drawable.hotel_weitzer);

        location.setmWebsite(R.string.hotel_weitzer_website);
        location.setmEmail(R.string.hotel_weitzer_email);

        //Create Location
        locations.add(location);

        location = new Location(R.string.hotel_paradies_name, R.string.hotel_paradies_address,
                R.string.hotel_paradies_postcode, R.string.hotel_paradies_telephone,
                R.string.hotel_paradies_description, R.drawable.hotel_paradies);

        location.setmEmail(R.string.hotel_paradies_email);
        location.setmWebsite(R.string.hotel_paradies_website);

        //Add Location
        locations.add(location);

        location = new Location(R.string.parkhotel_graz_name, R.string.parkhotel_graz_address,
                R.string.parkhotel_graz_postcode, R.string.parkhotel_graz_telephone,
                R.string.parkhotel_graz_description, R.drawable.parkhotel_graz);

        location.setmEmail(R.string.parkhotel_graz_email);
        location.setmWebsite(R.string.parkhotel_graz_website);

        //Add Location
        locations.add(location);


        location = new Location(R.string.hotel_daniel_names, R.string.hotel_daniel_address,
                R.string.hotel_daniel_postcode, R.string.hotel_daniel_telephone,
                R.string.hotel_daniel_description, R.drawable.hotel_daniel);

        location.setmEmail(R.string.hotel_daniel_email);
        location.setmWebsite(R.string.hotel_daniel_website);

        //Add Location
        locations.add(location);


        LocationAdapter adapter = new LocationAdapter(getActivity(), locations, R.color.colorBlue);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location location = locations.get(position);
            }
        });


        return rootView;
    }

}
