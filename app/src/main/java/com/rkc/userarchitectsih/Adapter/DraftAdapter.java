package com.rkc.userarchitectsih.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.DraftRecyclerData;
import com.rkc.userarchitectsih.dto.PendingRecyclerData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.DraftAdapterViewHolder> implements Filterable {
    Context context;
    List<DraftRecyclerData> data, dataAll;

    public DraftAdapter(List<DraftRecyclerData> data,Context context){
        this.data=data;
        this.context = context;
        this.dataAll = new ArrayList<>();
        this.dataAll.addAll(data);
    }

    @NonNull
    @Override
    public DraftAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.case_worker_item,parent,false);
        return new DraftAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DraftAdapterViewHolder holder, int position) {
        holder.applicationID.setText(context.getResources().getString(R.string.temporary_id)+": "+data.get(holder.getAdapterPosition()).getTempID().toString());
        holder.buildingName.setText(context.getResources().getString(R.string.building_name)+": "+data.get(holder.getAdapterPosition()).getBuildingName().toString());
        holder.buildingOwner.setText(context.getResources().getString(R.string.building_owner)+": "+data.get(holder.getAdapterPosition()).getBuildingOwner().toString());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Editing File", Toast.LENGTH_SHORT).show();
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
                List<DraftRecyclerData> filteredList=new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    filteredList.addAll(dataAll);
                } else {
                    for (DraftRecyclerData searchedString: dataAll){
                        // First check what toString method is giving you
                        System.out.println(searchedString.toString());
                        // in this case subject is your user defined type ....it has various fields....user is going to enter some character strings
                        // most probably the name of the subject. So you need to check the users input against the name field of the Subject type
                        if(searchedString.getTempID().toLowerCase().contains(charSequence.toString().toLowerCase())){
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
                data.addAll((Collection<? extends DraftRecyclerData>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public static class DraftAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView applicationID, buildingOwner, buildingName;
        Button edit;
        View view;

        public DraftAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            applicationID = (TextView) itemView.findViewById(R.id.applicationId);
            buildingOwner = (TextView) itemView.findViewById(R.id.buildingOwner);
            buildingName = (TextView) itemView.findViewById(R.id.buildingName);
            edit = (Button) itemView.findViewById(R.id.editFile);
            view = itemView;
        }
    }
}
