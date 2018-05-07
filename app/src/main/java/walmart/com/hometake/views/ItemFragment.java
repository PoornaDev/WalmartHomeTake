package walmart.com.hometake.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import walmart.com.hometake.FragmentCallback;
import walmart.com.hometake.R;
import walmart.com.hometake.model.pojos.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link FragmentCallback}
 * interface.
 */
public class ItemFragment extends Fragment {

    public static final String TAG = "ItemFragment";
    private static final String LIST_DATA = "LIST_DATA" ;
    private static final String LIST_STATE =  "LIST_STATE";
    private FragmentCallback mListener;
    SearchResultsRecyclerViewAdapter mAdapter;
    Parcelable mParcelable;
    private ArrayList<Item> mItemsList;
    LinearLayoutManager mLinearLayoutManager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    public static ItemFragment newInstance(Bundle bundle) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = bundle;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        if(mParcelable!=null) {
            Log.i(TAG,"Restoring listview state...");
            if(mItemsList!=null) {
                Log.i(TAG,"Restoring listview data size...");
                refreshAdapter(mItemsList);
            }
            mLinearLayoutManager.onRestoreInstanceState(mParcelable);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle stateBundle) {
        super.onSaveInstanceState(stateBundle);
        Log.i(TAG,"onSaveInstanceState");
        Parcelable parcelable = mLinearLayoutManager.onSaveInstanceState();
        stateBundle.putParcelable(LIST_STATE , parcelable);
        stateBundle.putSerializable(LIST_DATA, mItemsList);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i(TAG,"onViewStateRestored");
        if(savedInstanceState!=null) {
            mParcelable =  savedInstanceState.getParcelable(LIST_STATE);
            mItemsList = (ArrayList<Item>) savedInstanceState.getSerializable(LIST_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            mLinearLayoutManager =  new LinearLayoutManager(context);
            recyclerView.setLayoutManager(mLinearLayoutManager);
            mAdapter = new SearchResultsRecyclerViewAdapter(new ArrayList<Item>(), mListener);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallback) {
            mListener = (FragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void refreshAdapter(List<Item> items){
        mItemsList = (ArrayList<Item>) items;
        mAdapter.onDataReceived(items);
    }
}
