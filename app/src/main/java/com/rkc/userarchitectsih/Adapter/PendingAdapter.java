package com.rkc.userarchitectsih.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.PendingRecyclerData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.PendingAdapterViewHolder> implements Filterable {
    List<PendingRecyclerData> data , dataAll;
    Context context;

    public PendingAdapter(List<PendingRecyclerData> data,Context context){
        this.data = data;
        this.context =context;
        this.dataAll = new ArrayList<>();
        this.dataAll.addAll(data);
    }

    @NonNull
    @Override
    public PendingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.pending_recycler_view_item,parent,false);
        return new PendingAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAdapterViewHolder holder, int position) {
        holder.applicationID.setText(context.getResources().getString(R.string.application_id)+": "+data.get(holder.getAdapterPosition()).getApplicationID().toString());
        holder.buildingName.setText(context.getResources().getString(R.string.building_name)+": "+data.get(holder.getAdapterPosition()).getBuildingName().toString());
        holder.buildingOwner.setText(context.getResources().getString(R.string.building_owner)+": "+data.get(holder.getAdapterPosition()).getBuildingOwner().toString());
        holder.currentStage.setText(context.getResources().getString(R.string.current_stage)+": "+data.get(holder.getAdapterPosition()).getCurrentStage().toString());
        holder.estimatedDate.setText(context.getResources().getString(R.string.est_comp_date)+": "+data.get(holder.getAdapterPosition()).getEstimatedDate().toString());
        holder.allottedDate.setText(context.getResources().getString(R.string.allotted_date)+": "+data.get(holder.getAdapterPosition()).getAllottedDate().toString());
        holder.previousStage.setText(context.getResources().getString(R.string.previous_stage)+": "+data.get(holder.getAdapterPosition()).getPreviousStage().toString());
        holder.previousCompletionStage.setText(context.getResources().getString(R.string.previous_stage_comp_date)+": "+data.get(holder.getAdapterPosition()).getPreviousStageCompDate().toString());
        holder.linearProgressIndicator.setProgress(Integer.parseInt(data.get(holder.getAdapterPosition()).getProgressValue().toString()));
        holder.linearProgressIndicator.setIndicatorColor(Color.parseColor(data.get(holder.getAdapterPosition()).getProgressBarColor()));
        holder.expandingArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!expanded){
//                    expanded = true;
                    holder.expandingLayout.setVisibility(View.VISIBLE);
                    holder.expandingArrow.animate().rotation(180f);
                    TransitionManager.beginDelayedTransition(holder.projectCard, new AutoTransition());
//                }else{
//                    holder.expandingArrow.animate().rotation(0f);
//                    holder.expandingLayout.setVisibility(View.GONE);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<PendingRecyclerData> filteredList=new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    filteredList.addAll(dataAll);
                } else {
                    for (PendingRecyclerData searchedString: dataAll){
                        // First check what toString method is giving you
                        System.out.println(searchedString.toString());
                        // in this case subject is your user defined type ....it has various fields....user is going to enter some character strings
                        // most probably the name of the subject. So you need to check the users input against the name field of the Subject type
                        if(searchedString.getAllottedDate().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredList.add(searchedString);
                        } else if(searchedString.getApplicationID().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredList.add(searchedString);
                        } else if(searchedString.getBuildingName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredList.add(searchedString);
                        } else if(searchedString.getBuildingOwner().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredList.add(searchedString);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values=filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                data.clear();
                data.addAll((Collection<? extends PendingRecyclerData>) results.values);
                notifyDataSetChanged();

            }
        };
    }

    public static class PendingAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView applicationID,buildingOwner ,buildingName, currentStage, estimatedDate,
                allottedDate, previousStage, previousCompletionStage;
        ImageView expandingArrow;
        LinearLayout expandingLayout;
        LinearProgressIndicator linearProgressIndicator;
        CardView projectCard;
        View view;

        public PendingAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            applicationID=(TextView) itemView.findViewById(R.id.applicationId);
            buildingOwner =(TextView) itemView.findViewById(R.id.buildingOwner);
            buildingName=(TextView) itemView.findViewById(R.id.buildingName);
            currentStage=(TextView) itemView.findViewById(R.id.currentStage);
            estimatedDate=(TextView) itemView.findViewById(R.id.estimateDate);
            previousStage=(TextView) itemView.findViewById(R.id.previousStage);
            previousCompletionStage=(TextView) itemView.findViewById(R.id.previousCompDate);
            allottedDate=(TextView) itemView.findViewById(R.id.allottedDate);
            expandingArrow =(ImageView) itemView.findViewById(R.id.expandArrow);
            expandingLayout =(LinearLayout) itemView.findViewById(R.id.expandableLayout);
            linearProgressIndicator =(LinearProgressIndicator) itemView.findViewById(R.id.progressbar);
            projectCard = (CardView) itemView.findViewById(R.id.projectCard);
            linearProgressIndicator.setProgress(50);
            view = itemView;
        }
    }
}
