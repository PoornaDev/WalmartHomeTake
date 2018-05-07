package walmart.com.hometake.views;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import walmart.com.hometake.FragmentCallback;
import walmart.com.hometake.R;
import walmart.com.hometake.model.pojos.Item;


public class RecommendedItemsRecyclerViewAdapter extends RecyclerView.Adapter<RecommendedItemsRecyclerViewAdapter.ViewHolder> {

    private List<Item> mValues;
    private final FragmentCallback mListener;

    public RecommendedItemsRecyclerViewAdapter(List<Item> items, FragmentCallback listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recommended_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getName());
        holder.mPriceView.setText("$"+String.valueOf(mValues.get(position).getSalePrice()));

        Glide.with(mListener.getActivityContext())
                .load(holder.mItem.getMediumImage())
                .centerCrop()
                .crossFade()
                .fitCenter()
                .into(holder.mItemPhotoView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListItemClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void onDataReceived(List<Item> items) {
        Log.i("Data Received of Size::",items.size()+"");
        ArrayList<Item> tempList = new ArrayList<>(items);
        mValues = tempList;
        //notifyItemRangeInserted(currSize, mValues.size()-1);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final ImageView mItemPhotoView;
        public final TextView mPriceView;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mItemPhotoView = (ImageView) view.findViewById(R.id.item_photo);
            mPriceView = (TextView) view.findViewById(R.id.price);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
