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
public class ParksFragment extends Fragment {


    public ParksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);


        final ArrayList<Location> locations = new ArrayList<>();

        Location location = new Location(R.string.stadtpark_name, R.string.stadtpark_address,
                R.string.stadtpark_postcode, R.string.stadtpark_description,
                R.drawable.stadtpark);

        location.setTelephoneNumber(R.string.no_telephone);
        location.setmWebsite(R.string.no_website);
        location.setmEmail(R.string.no_email_address);

        //Create Location
        locations.add(location);

        location = new Location(R.string.burggarten_names, R.string.burggarten_address,
                R.string.burggarten_postcode, R.string.burggarten_description,
                R.drawable.burggarten);

        location.setTelephoneNumber(R.string.no_telephone);
        location.setmWebsite(R.string.no_website);
        location.setmEmail(R.string.no_email_address);

        //Create Location
        locations.add(location);

        location = new Location(R.string.botanical_garden_name, R.string.botanical_garden_address,
                R.string.botanical_garden_postcode, R.string.botanical_garden_telephone,
                R.string.botanical_garden_description, R.drawable.botanical_garden);

        location.setmEmail(R.string.botanical_garden_email);
        location.setmWebsite(R.string.botanical_garden_website);

        //Add Location
        locations.add(location);


        location = new Location(R.string.hilmteich_name, R.string.hilmteich_address,
                R.string.hilmteich_postcode, R.string.hilmteich_description,
                R.drawable.hilmteich);

        location.setTelephoneNumber(R.string.no_telephone);
        location.setmWebsite(R.string.hilmteich_website);
        location.setmEmail(R.string.no_email_address);

        //Add Location
        locations.add(location);


        LocationAdapter adapter = new LocationAdapter(getActivity(), locations, R.color.colorGreen);

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
