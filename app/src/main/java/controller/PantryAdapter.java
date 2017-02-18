package controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import model.Rows;
import shawn.c4q.nyc.bellycheer.R;
import view.PantryVH;

public class PantryAdapter extends RecyclerView.Adapter<PantryVH> {
    private List<Rows> pantriesList = new ArrayList<>();

    public PantryAdapter(List<Rows> pantriesList) {
        this.pantriesList = pantriesList;
    }

    @Override
    public PantryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View childview = LayoutInflater.from(parent.getContext()).inflate(R.layout.pantry_itemview, parent, false);
        return new PantryVH(childview);
    }

    @Override
    public void onBindViewHolder(PantryVH holder, int position) {
        holder.onBind(pantriesList.get(position));

    }

    @Override
    public int getItemCount() {
        return pantriesList.size();
    }
}
