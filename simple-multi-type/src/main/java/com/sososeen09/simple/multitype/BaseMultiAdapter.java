package com.sososeen09.simple.multitype;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.TypePool;

/**
 * @author sososeen09
 */

public class BaseMultiAdapter extends MultiTypeAdapter {
    private OnItemClickListener mOnItemClickListener;
    private OnItemChildClickListener mOnItemChildClickListener;

    public BaseMultiAdapter() {
    }

    public BaseMultiAdapter(@NonNull List<?> items) {
        super(items);
    }

    public BaseMultiAdapter(@NonNull List<?> items, int initialCapacity) {
        super(items, initialCapacity);
    }

    public BaseMultiAdapter(@NonNull List<?> items, @NonNull TypePool pool) {
        super(items, pool);
    }

    /**
     * Register a callback to be invoked when an item in this RecyclerView has
     * been clicked.
     *
     * @param listener The callback that will be invoked.
     */
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * @return The callback to be invoked with an item in this RecyclerView has
     * been clicked and held, or null id no callback as been set.
     */
    public final OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    /**
     * Register a callback to be invoked when an itemchild in View has
     * been  clicked
     *
     * @param listener The callback that will run
     */
    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        mOnItemChildClickListener = listener;
    }

    /**
     * @return The callback to be invoked with an itemchild in this RecyclerView has
     * been clicked, or null id no callback has been set.
     */
    @Nullable
    public final OnItemChildClickListener getOnItemChildClickListener() {
        return mOnItemChildClickListener;
    }

    /**
     * override this method if you want to override click event logic
     *
     * @param v
     * @param position
     */
    protected void setOnItemClick(View v, int position) {
        getOnItemClickListener().onItemClick(BaseMultiAdapter.this, v, position);
    }

    public void bindViewClickListener(final RecyclerView.ViewHolder baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        final View view = baseViewHolder.itemView;
        if (view == null) {
            return;
        }
        if (getOnItemClickListener() != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setOnItemClick(v, baseViewHolder.getLayoutPosition());
                }
            });
        }
    }


    /**
     * Interface definition for a callback to be invoked when an itemchild in this
     * view has been clicked
     */
    public interface OnItemChildClickListener {
        /**
         * callback method to be invoked when an item in this view has been
         * click and held
         *
         * @param adapter  The adapter
         * @param view     The view whihin the ItemView that was clicked
         * @param position The position of the view int the adapter
         */
        void onItemChildClick(BaseMultiAdapter adapter, View view, int position);
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * RecyclerView itemView has been clicked.
     */
    public interface OnItemClickListener {

        /**
         * Callback method to be invoked when an item in this RecyclerView has
         * been clicked.
         *
         * @param adapter  the adpater
         * @param view     The itemView within the RecyclerView that was clicked (this
         *                 will be a view provided by the adapter)
         * @param position The position of the view in the adapter.
         */
        void onItemClick(BaseMultiAdapter adapter, View view, int position);
    }
}
