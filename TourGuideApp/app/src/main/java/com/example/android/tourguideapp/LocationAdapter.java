/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.tourguideapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * {@link LocationAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Location} objects.
 */
public class LocationAdapter extends ArrayAdapter<Location> {

    /**
     * Resource ID for the background color for this list of words
     */
    private int mColorResourceId;

    /**
     * Create a new {@link LocationAdapter} object.
     *
     * @param context         is the current context (i.e. Activity) that the adapter is being created in.
     * @param words           is the list of {@link Location}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public LocationAdapter(Context context, ArrayList<Location> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Location currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID.
        TextView headlineTextView = (TextView) listItemView.findViewById(R.id.headline);

        headlineTextView.setText(currentWord.getName());


        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.description);

        descriptionTextView.setText(currentWord.getDescription());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        imageView.setImageResource(currentWord.getLocationImageId());


        //Address TextView
        TextView infoTextView = (TextView) listItemView.findViewById(R.id.info_headline);

        infoTextView.setText(currentWord.getName());

        //Address TextView
        TextView addressTextView = (TextView) listItemView.findViewById(R.id.address);

        addressTextView.setText(currentWord.getAddress());

        //Telephone TextView
        TextView telephoneTextView = (TextView) listItemView.findViewById(R.id.telephone);

        telephoneTextView.setText(currentWord.getTelephoneNumber());

        //PostCode TextView
        TextView postCodeTextView = (TextView) listItemView.findViewById(R.id.postcode);

        postCodeTextView.setText(currentWord.getPostCode());

        //Email TextView
        TextView emailTextView = (TextView) listItemView.findViewById(R.id.email);

        emailTextView.setText(currentWord.getmEmail());

        //Website TextView
        TextView websiteTextView = (TextView) listItemView.findViewById(R.id.website_link);

        websiteTextView.setText(currentWord.getmWebsite());


        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            //imageView.setImageResource(currentWord.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        infoTextView.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}