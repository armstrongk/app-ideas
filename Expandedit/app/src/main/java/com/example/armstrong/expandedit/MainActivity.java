package com.example.armstrong.expandedit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    Button Save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  record = new RecordData();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        Save = (Button) findViewById(R.id.btn_save);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader,
                listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {


            class RecoverData {
                String name;
                String purpose;

                public String getPurpose() {
                    return purpose;
                }

                public String getName()
                {
                    return name;
                }

            }

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                RecoverData record1=new RecoverData();

                Toast.makeText(getApplicationContext(),
                        "Name is:" + record1.getName(), Toast.LENGTH_SHORT)
                        .show();

                Toast.makeText(getApplicationContext(),
                        "Purpose is:" + record1.getPurpose(), Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Basic Info");
        listDataHeader.add("Addtional Info");

        // Adding child data
        List<String> basic = new ArrayList<String>();
        basic.add("Name");
        basic.add("Purpose");

        List<String> additional = new ArrayList<String>();
        additional.add("Payment Date");
        additional.add("Payment Due Date");
        additional.add("Payment Due Time");

        listDataChild.put(listDataHeader.get(0), basic); // Header, Child data
        listDataChild.put(listDataHeader.get(1), additional);

    }
}