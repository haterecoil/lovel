/*
* Copyright (C) 2014 The Android Open Source Project
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
package lorem.lovel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lorem.lovel.R;
import lorem.lovel.adapters.CardCollectionAdapter;
import lorem.lovel.models.CardModel;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class CardCollectionFragment extends Fragment {

    private static final String KEY_COLLECTION_TITLE = "collectionTitle";
    protected String mCollectionTitle;

    protected RecyclerView mRecyclerView;
    protected CardCollectionAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<CardModel> mDataset;

    protected int cardType;

    public CardCollectionFragment() {
        cardType = CardModel.TYPE_COUNTRY;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pCardType = getArguments().getInt("cardType", 0);

        switch(pCardType) {
            case CardModel.TYPE_EVENT: cardType = CardModel.TYPE_EVENT;
                break;
            case CardModel.TYPE_COUNTRY: cardType = CardModel.TYPE_COUNTRY;
                break;
            default: cardType = CardModel.TYPE_COUNTRY;
        }

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cardcollection_fragment, container, false);

        Bundle args = getArguments();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardcollection_recyclerview);

        setRecyclerViewLayoutManager();

        mAdapter = new CardCollectionAdapter(mDataset, this.getContext());
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        TextView collectionTitleView = (TextView) rootView.findViewById(R.id.cardcollection_title);

        if (args != null) {
            mCollectionTitle = this.getArguments().getString("collectionTitle");
            collectionTitleView.setText(mCollectionTitle);
            int pCardType = args.getInt("cardType", 0);

            switch(pCardType) {
                case CardModel.TYPE_EVENT: cardType = CardModel.TYPE_EVENT;
                    break;
                case CardModel.TYPE_COUNTRY: cardType = CardModel.TYPE_COUNTRY;
                    break;
                default: cardType = CardModel.TYPE_COUNTRY;
            }
        }

        if (mCollectionTitle != null){
            if (collectionTitleView != null) {
                collectionTitleView.setText(mCollectionTitle);
            }
        } else {
            if (args != null) {
                mCollectionTitle = this.getArguments().getString("collectionTitle");
                collectionTitleView.setText(mCollectionTitle);
            }
        }

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        //mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //mRecyclerView.setHorizontalScrollBarEnabled(false);

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCollectionTitle = (String) savedInstanceState
                    .getSerializable(KEY_COLLECTION_TITLE);
            collectionTitleView.setText(mCollectionTitle);

        }


        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     */
    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_COLLECTION_TITLE, mCollectionTitle);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        mDataset = new ArrayList<>();
        if (cardType == CardModel.TYPE_EVENT){
            mDataset.add(new CardModel().
                    setCardType(CardModel.TYPE_EVENT).
                    setName("Concert de cornemuse").
                    setDate("Vendredi 16 Dec.").
                    setPlace("HETIC, Montreuil"));
            mDataset.add(new CardModel().
                    setCardType(CardModel.TYPE_EVENT).
                    setName("Dégustation de bières insipides.").
                    setDate("Vendredi 16 Dec.").
                    setPlace("Birdies, Montreuil"));
            mDataset.add(new CardModel().
                    setCardType(CardModel.TYPE_EVENT).
                    setName("Découverte des massages thailandais").
                    setDate("Vendredi 16 Dec.").
                    setPlace("Montreuil"));
        } else {
            mDataset.add(new CardModel().
                    setCardType(CardModel.TYPE_COUNTRY).
                    setName("Angleterre"));
            mDataset.add(new CardModel().
                    setCardType(CardModel.TYPE_COUNTRY).
                    setName("Italie"));
            mDataset.add(new CardModel().
                    setCardType(CardModel.TYPE_COUNTRY).
                    setName("Russie"));

        }
    }

    public void updateCollectionTitle(String string) {

        Log.e("== ll ll ==", "updateCollectionTitle");
        Log.e("== ll ll ==", string);
        mCollectionTitle = string;
        TextView collectionTitleView = (TextView) mRecyclerView.findViewById(R.id.cardcollection_title);
        if (collectionTitleView!=null){
            collectionTitleView.setText(string);
            Log.e("== ll ll ==", "collectionTitleView is not nul");
        } else {
            Log.e("== ll ll ==", "collectionTitleView IS NULL");
        }
    }
}