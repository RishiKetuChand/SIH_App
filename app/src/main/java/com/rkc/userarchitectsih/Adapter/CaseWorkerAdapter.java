package com.rkc.userarchitectsih.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.CaseWorkerData;
import com.rkc.userarchitectsih.dto.DraftRecyclerData;
import com.rkc.userarchitectsih.dto.ProjectDetailsJosn;
import com.rkc.userarchitectsih.dto.StatusUpdate;
import com.rkc.userarchitectsih.server.ServerRequestRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaseWorkerAdapter extends  RecyclerView.Adapter<CaseWorkerAdapter.CaseWorkerAdapterViewHolder> implements Filterable {

    Context context;
    ArrayList<CaseWorkerData> data = new ArrayList<>(), dataAll;

    public CaseWorkerAdapter(Context context, ArrayList<CaseWorkerData> data) {
        this.context = context;
        this.data = data;
        this.dataAll = new ArrayList<>();
        this.dataAll.addAll(data);
    }

    @NonNull
    @Override
    public CaseWorkerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.draft_recycler_view_item,parent,false);
        return new CaseWorkerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseWorkerAdapterViewHolder holder, int position) {
        holder.applicationID.setText("Project ID: "+ data.get(holder.getAdapterPosition()).getProject_id());
        holder.buildingOwner.setText("Name: "+ data.get(holder.getAdapterPosition()).getClient_name());
        holder.buildingName.setText("Aadhaar: " + data.get(holder.getAdapterPosition()).getClient_aadhar());

        if(holder.getAdapterPosition()==1){
        holder.linearProgressIndicator.setProgress(25);
        holder.linearProgressIndicator.setIndicatorColor(Color.parseColor("#FB3737"));}

        holder.viewFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webPage = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                context.startActivity(webIntent);
                Toast.makeText(context, "Opening a file", Toast.LENGTH_SHORT).show();
            }
        });
        holder.approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus(holder.getAdapterPosition(),"Verification","Sucessfull","next step");
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus(holder.getAdapterPosition(),"Verification","Falied","re-verify");
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<CaseWorkerData> filteredList=new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    filteredList.addAll(dataAll);
                } else {
                    for (CaseWorkerData searchedString: dataAll){
                        // First check what toString method is giving you
                        System.out.println(searchedString.toString());
                        // in this case subject is your user defined type ....it has various fields....user is going to enter some character strings
                        // most probably the name of the subject. So you need to check the users input against the name field of the Subject type
                        if(searchedString.getClient_aadhar().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredList.add(searchedString);
                        } else if(searchedString.getProject_id().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredList.add(searchedString);
                        } else if(searchedString.getClient_name().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filteredList.add(searchedString);
                        } else if(searchedString.getEng_email().toLowerCase().contains(charSequence.toString().toLowerCase())){
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
                data.addAll((Collection<? extends CaseWorkerData>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public void setData(ArrayList<CaseWorkerData> caseWorkerData) {
        this.data = caseWorkerData;
    }

    public static class CaseWorkerAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView applicationID, buildingOwner, buildingName;
        Button viewFile, reject,approval;
        LinearProgressIndicator linearProgressIndicator;
        View view;

        public CaseWorkerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            applicationID = (TextView) itemView.findViewById(R.id.applicationId);
            buildingOwner = (TextView) itemView.findViewById(R.id.buildingOwner);
            buildingName = (TextView) itemView.findViewById(R.id.buildingName);
            viewFile =(Button) itemView.findViewById(R.id.viewFile);
            reject = (Button) itemView.findViewById(R.id.rejection);
            approval = (Button) itemView .findViewById(R.id.apporoval);
            linearProgressIndicator =(LinearProgressIndicator) itemView.findViewById(R.id.progressbar);
            linearProgressIndicator.setProgress(50);
            view = itemView;
        }
    }

    public void updateStatus(int pos, String step,String status,String status_brief){
        StatusUpdate statusUpdate = new StatusUpdate(data.get(pos).getProject_id(),"empid001","26-08-2022",step,status,status_brief);
        ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
        serverRequestRepository.getServerService().updateStatus(statusUpdate).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(context,response.body().toString(), Toast.LENGTH_SHORT).show();
                data.remove(pos);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
