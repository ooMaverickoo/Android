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
public class SightsFragment extends Fragment {


    public SightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);


        final ArrayList<Location> locations = new ArrayList<>();

        Location location = new Location(R.string.schlossberg_name, R.string.schlossberg_address,
                R.string.schlossberg_postcode, R.string.schlossberg_description,
                R.drawable.schlossberg);

        location.setTelephoneNumber(R.string.no_telephone);
        location.setmWebsite(R.string.no_website);
        location.setmEmail(R.string.no_email_address);

        locations.add(location);

        location = new Location(R.string.island_in_the_mur_names, R.string.island_in_the_mur_address,
                R.string.island_in_the_mur_postcode, R.string.island_in_the_mur_telephone,
                R.string.island_in_the_mur_description, R.drawable.island_in_the_mur);

        location.setmEmail(R.string.island_in_the_mur_email);
        location.setmWebsite(R.string.island_in_the_mur_website);

        //Add Location
        locations.add(location);

        location = new Location(R.string.kunsthaus_name, R.string.kunsthaus_address,
                R.string.kunsthaus_postcode, R.string.kunsthaus_telephone,
                R.string.kunsthaus_description, R.drawable.kunsthaus);

        location.setmEmail(R.string.kunsthaus_email);
        location.setmWebsite(R.string.kunsthaus_website);

        //Add Location
        locations.add(location);


        location = new Location(R.string.eggenberg_palace_name, R.string.eggenberg_palace_address,
                R.string.eggenberg_palace_postcode, R.string.eggenberg_palace_telephone,
                R.string.eggenberg_palace_description, R.drawable.eggenberg_palace);

        location.setmEmail(R.string.eggenberg_palace_email);
        location.setmWebsite(R.string.eggenberg_palace_website);

        //Add Location
        locations.add(location);


        LocationAdapter adapter = new LocationAdapter(getActivity(), locations, R.color.colorOrange);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location location = locations.get(position);
            }
        });


        return rootView;
    }

}
