package com.rkc.userarchitectsih.Arch.Acitivites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.rkc.userarchitectsih.Database.DraftProjectRoomDataBase;
import com.rkc.userarchitectsih.JsonClasses.BuildingProject;
import com.rkc.userarchitectsih.R;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BuildingDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private AutoCompleteTextView buildingCategoryAuto, projectTypeAuto,zoneAuto, classificationAuto,
            projectComponentsAuto, projectCategoryAuto, dwellingUnitAuto,
            basementFloorAuto,groundFloorAuto,upperFloorAuto,stiltAuto ;

    private TextInputLayout buildingCategoryLayout, projectTypeLayout,zoneLayout, classificationLayout,
            projectComponentsLayout, projectCategoryLayout, dwellingUnitLayout,
            basementFloorLayout,groundFloorLayout,upperFloorLayout,stiltLayout ;

    private String buildingCategory="", projectType="",zone="", classification="",
            projectComponents="", projectCategory="", dwellingUnit="",
            basementFloor="",groundFloor="",upperFloor="",stilt="";

    private ArrayAdapter<String> buildingCategoryAdapter, projectTypeAdapter,zoneAdapter,classificationAdapter,
            projectComponentAdapter, projectCategoryAdapter,dwellingUnitAdapter ;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);

        Intent intent = getIntent();
        intent.getStringExtra("projectID");

        //TODO get all info
        getAllTodos();

        //toolbar
        // toolbar
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.building_details));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //initalization
        buildingCategoryAuto = findViewById(R.id.building_category_auto);
        projectTypeAuto=findViewById(R.id.project_type_auto);
        zoneAuto= findViewById(R.id.zone_auto);
        classificationAuto = findViewById(R.id.classification_auto);
        projectCategoryAuto = findViewById(R.id.project_category_auto);
        projectComponentsAuto = findViewById(R.id.project_component_auto);
        dwellingUnitAuto = findViewById(R.id.dwelling_unit_auto);
        basementFloorAuto = findViewById(R.id.basement_floor_auto);
        groundFloorAuto = findViewById(R.id.ground_floor_auto);
        upperFloorAuto= findViewById(R.id.upper_floor_auto);
        stiltAuto = findViewById(R.id.stilt_auto);
        next= findViewById(R.id.next_Button);

        //initalization
        buildingCategoryLayout = findViewById(R.id.input_building_category_layout);
        projectTypeLayout=findViewById(R.id.input_project_type_layout);
        zoneLayout= findViewById(R.id.input_zone_layout);
        classificationLayout = findViewById(R.id.input_classification_layout);
        projectCategoryLayout = findViewById(R.id.input_project_category_layout);
        projectComponentsLayout = findViewById(R.id.input_project_component_layout);
        dwellingUnitLayout= findViewById(R.id.input_dwelling_unit_layout);
        basementFloorLayout = findViewById(R.id.input_basement_floor_layout);
        groundFloorLayout = findViewById(R.id.input_ground_floor_layout);
        upperFloorLayout= findViewById(R.id.input_upper_floor_layout);
        stiltLayout = findViewById(R.id.input_stilt_layout);

        //adapter
        buildingCategoryAdapter = new ArrayAdapter<>(this,R.layout.list_item,getResources().getStringArray(R.array.buildingCategory));
        projectTypeAdapter = new ArrayAdapter<>(this,R.layout.list_item,getResources().getStringArray(R.array.projectType));
        zoneAdapter= new ArrayAdapter<>(this,R.layout.list_item,getResources().getStringArray(R.array.zone));
        classificationAdapter = new ArrayAdapter<>(this,R.layout.list_item,getResources().getStringArray(R.array.classification));
        projectCategoryAdapter = new ArrayAdapter<>(this,R.layout.list_item,getResources().getStringArray(R.array.projectCategory));
        projectComponentAdapter = new ArrayAdapter<>(this,R.layout.list_item,getResources().getStringArray(R.array.projectComponents));
        dwellingUnitAdapter = new ArrayAdapter<>(this,R.layout.list_item,getResources().getStringArray(R.array.dwellingUnit));

        //Setting Adapter
        buildingCategoryAuto.setAdapter(buildingCategoryAdapter);
        projectTypeAuto.setAdapter(projectTypeAdapter);
        zoneAuto.setAdapter(zoneAdapter);
        classificationAuto.setAdapter(classificationAdapter);
        projectCategoryAuto.setAdapter(projectCategoryAdapter);
        projectComponentsAuto.setAdapter(projectComponentAdapter);
        dwellingUnitAuto.setAdapter(dwellingUnitAdapter);
        basementFloorAuto.setAdapter(dwellingUnitAdapter);
        groundFloorAuto.setAdapter(dwellingUnitAdapter);
        upperFloorAuto.setAdapter(dwellingUnitAdapter);
        stiltAuto.setAdapter(dwellingUnitAdapter);

        buildingCategoryAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                buildingCategory=adapterView.getItemAtPosition(i).toString();
            }
        });
        projectTypeAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                projectType=adapterView.getItemAtPosition(i).toString();
            }
        });
        projectCategoryAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                projectCategory=adapterView.getItemAtPosition(i).toString();
            }
        });
        projectComponentsAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                projectComponents=adapterView.getItemAtPosition(i).toString();
            }
        });
        zoneAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                zone=adapterView.getItemAtPosition(i).toString();
            }
        });
        classificationAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                classification=adapterView.getItemAtPosition(i).toString();
            }
        });
        dwellingUnitAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dwellingUnit=adapterView.getItemAtPosition(i).toString();
            }
        });
        basementFloorAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                basementFloor=adapterView.getItemAtPosition(i).toString();
            }
        });
        groundFloorAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                groundFloor=adapterView.getItemAtPosition(i).toString();
            }
        });

        upperFloorAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                upperFloor=adapterView.getItemAtPosition(i).toString();
            }
        });
        stiltAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stilt=adapterView.getItemAtPosition(i).toString();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(projectType.isEmpty()){
                    projectTypeLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    projectTypeLayout.setError(null);
                }
                if(projectComponents.isEmpty()){
                    projectComponentsLayout.setError(getString(R.string.invalidInfo));
                    return;
                }
                else {
                    projectComponentsLayout.setError(null);
                }
                if(projectCategory.isEmpty()){
                    projectCategoryLayout.setError(getString(R.string.invalidInfo));
                    return;
                }
                else {
                    projectCategoryLayout.setError(null);
                }
                if(buildingCategory.isEmpty()) {
                    buildingCategoryLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    buildingCategoryLayout.setError(null);
                }
                if(zone.isEmpty()){
                    zoneLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    zoneLayout.setError(null);
                }
                if(classification.isEmpty()){
                    classificationLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    classificationLayout.setError(null);
                }
                if(dwellingUnit.isEmpty()){
                    dwellingUnitLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    dwellingUnitLayout.setError(null);
                }
                if(basementFloor.isEmpty()){
                    basementFloorLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    basementFloorLayout.setError(null);
                }
                if(groundFloor.isEmpty()){
                    groundFloorLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    groundFloorLayout.setError(null);
                }
                if(upperFloor.isEmpty()){
                    upperFloorLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    upperFloorLayout.setError(null);
                }
                if(stilt.isEmpty()){
                    stiltLayout.setError(getString(R.string.invalidInfo));
                    return;
                }else {
                    stiltLayout.setError(null);
                }
//                buildingCategoryLayout.setError(null);
//                projectTypeLayout.setError(null);
//                zoneLayout.setError(null);
//                classificationLayout.setError(null);
//                projectComponentsLayout.setError(null);
//                projectCategoryLayout.setError(null);
//                dwellingUnitLayout.setError(null);
//                basementFloorLayout.setError(null);
//                groundFloorLayout.setError(null);
//                upperFloorLayout.setError(null);
//                stiltLayout.setError(null) ;
                Intent intent = new Intent(BuildingDetailsActivity.this, AddProjectActivity.class);
                startActivity(intent);
            }
        });



    }

    public void getAllTodos() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<BuildingProject> todoList = DraftProjectRoomDataBase.getInstance(getApplicationContext())
                        .draftProjectsDao()
                        .getAllBuildingProjects();

                Log.d("TAGMain", "run: " + todoList.get(todoList.size()-1).toString());
            }
        });
        thread.start();
    }
}