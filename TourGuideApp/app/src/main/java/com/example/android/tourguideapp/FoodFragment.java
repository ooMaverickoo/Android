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
public class FoodFragment extends Fragment {


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);


        final ArrayList<Location> locations = new ArrayList<>();

        Location location = new Location(R.string.aiola_upstairs_name, R.string.aiola_address,
                R.string.aiola_postcode, R.string.aiola_telephone, R.string.aiola_description,
                R.drawable.aiola_upstairs);

        location.setmWebsite(R.string.aiola_website);
        location.setmEmail(R.string.aiola_email);

        //Create Location
        locations.add(location);

        location = new Location(R.string.barista_names, R.string.baristas_address,
                R.string.baristas_postcode, R.string.baristas_telephone,
                R.string.baristas_description, R.drawable.dessert_kurbiskerntiramisu);

        location.setmEmail(R.string.baristas_email);
        location.setmWebsite(R.string.baristas_website);

        //Add Location
        locations.add(location);

        location = new Location(R.string.burger_factory_name, R.string.burger_factory_address,
                R.string.burger_factory_postcode, R.string.burger_factory_telephone,
                R.string.burger_factory_description, R.drawable.burger);

        location.setmEmail(R.string.burger_factory_email);
        location.setmWebsite(R.string.burger_factory_website);

        //Add Location
        locations.add(location);


        location = new Location(R.string.bur_name, R.string.bur_address,
                R.string.bur_postcode, R.string.bur_telephone,
                R.string.bur_description, R.drawable.bur_restaurant);

        location.setmEmail(R.string.bur_email);
        location.setmWebsite(R.string.bur_website);

        //Add Location
        locations.add(location);


        LocationAdapter adapter = new LocationAdapter(getActivity(), locations, R.color.colorRed);

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
