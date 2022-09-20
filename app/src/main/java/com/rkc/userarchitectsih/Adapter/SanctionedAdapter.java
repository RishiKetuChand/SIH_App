package com.rkc.userarchitectsih.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.rkc.userarchitectsih.dto.PendingRecyclerData;
import com.rkc.userarchitectsih.dto.SanctionedRecyclerData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SanctionedAdapter extends RecyclerView.Adapter<SanctionedAdapter.SanctionedAdapterViewHolder> implements Filterable {
    List<SanctionedRecyclerData> data, dataAll;
    Context context;
    public SanctionedAdapter(List<SanctionedRecyclerData> data,Context context){
        this.data=data;
        this.context = context;
        this.dataAll = new ArrayList<>();
        this.dataAll.addAll(data);
    }

    @NonNull
    @Override
    public SanctionedAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.sanctioned_recycler_view_item,parent,false);
        return new SanctionedAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanctionedAdapterViewHolder holder, int position) {

        holder.applicationID.setText(context.getResources().getString(R.string.application_id)+": "+data.get(holder.getAdapterPosition()).getApplicationID().toString());
        holder.buildingName.setText(context.getResources().getString(R.string.building_name)+": "+data.get(holder.getAdapterPosition()).getBuildingName().toString());
        holder.buildingOwner.setText(context.getResources().getString(R.string.building_owner)+": "+data.get(holder.getAdapterPosition()).getBuildingOwner().toString());
        holder.completionDate.setText(context.getResources().getString(R.string.completion_date)+": "+data.get(holder.getAdapterPosition()).getCompletionDate().toString());
        holder.viewFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Opening File", Toast.LENGTH_SHORT).show();
            }
        });
        holder.viewLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webPage = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                context.startActivity(webIntent);
                Toast.makeText(context, "Opening License", Toast.LENGTH_SHORT).show();
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
                List<SanctionedRecyclerData> filteredList=new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    filteredList.addAll(dataAll);
                } else {
                    for (SanctionedRecyclerData searchedString: dataAll){
                        // First check what toString method is giving you
                        System.out.println(searchedString.toString());
                        // in this case subject is your user defined type ....it has various fields....user is going to enter some character strings
                        // most probably the name of the subject. So you need to check the users input against the name field of the Subject type
                        if(searchedString.getCompletionDate().toLowerCase().contains(charSequence.toString().toLowerCase())){
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
                data.addAll((Collection<? extends SanctionedRecyclerData>) results.values);
                notifyDataSetChanged();

            }
        };
    }


    public static class SanctionedAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView applicationID, buildingOwner, buildingName, completionDate;
        Button viewFile, viewLicense;
        View view;

        public SanctionedAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            applicationID = (TextView) itemView.findViewById(R.id.applicationId);
            buildingOwner = (TextView) itemView.findViewById(R.id.buildingOwner);
            buildingName = (TextView) itemView.findViewById(R.id.buildingName);
            completionDate = (TextView) itemView.findViewById(R.id.completionDate);
            viewFile = (Button) itemView.findViewById(R.id.viewFile);
            viewLicense = (Button) itemView.findViewById(R.id.viewLicense);
            view = itemView;
        }
    }
}
