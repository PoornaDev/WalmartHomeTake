package walmart.com.hometake.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import walmart.com.hometake.FragmentCallback;
import walmart.com.hometake.R;
import walmart.com.hometake.model.pojos.Item;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ItemDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDetailsFragment extends Fragment {


    private FragmentCallback mListener;
    private Item itemDetails;

    public ItemDetailsFragment() {
        // Required empty public constructor
    }


    public static ItemDetailsFragment newInstance(Bundle bundle) {
        ItemDetailsFragment fragment = new ItemDetailsFragment();
        Bundle args = bundle;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            itemDetails = (Item) getArguments().getSerializable(BundleConstants.ITEM_DETAILS_KEY);
        }

    }

    private void loadData() {
        titleView.setText(itemDetails.getName());
        Glide.with(mListener.getActivityContext())
                .load(itemDetails.getMediumImage())
                .centerCrop()
                .crossFade()
                .into(imageView);
        priceView.setText("$"+String.valueOf(itemDetails.getSalePrice()));

        itemDescView.setText(Html.fromHtml(itemDetails.getShortDescription()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_details, container, false);
        init(view);
        if(itemDetails!=null) {
            loadData();
        }
        return view;
    }

    TextView titleView;
    ImageView imageView;
    TextView priceView;
    TextView itemDescView;

    private void init(View view) {
        titleView =  view.findViewById(R.id.item_details_title);
        imageView =  view.findViewById(R.id.item_details_image);

        priceView =  view.findViewById(R.id.item_details_price);
        itemDescView =  view.findViewById(R.id.item_details_desc);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallback) {
            mListener = (FragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
