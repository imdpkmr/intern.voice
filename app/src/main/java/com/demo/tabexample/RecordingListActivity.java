package com.demo.tabexample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class RecordingListActivity extends Fragment {

    private Toolbar toolbar;
    private RecyclerView recyclerViewRecordings;
    private ArrayList<Recording> recordingArrayList;
    private  RecordingAdapter recordingAdapter;
    private TextView textViewNoRecordings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_recording_list, container, false);
        View viewRecycler =inflater.inflate(R.layout.recording_item_layout,container, false);
        recordingArrayList=new ArrayList<Recording>();
        initViews(view);
        fetchRecordings(viewRecycler);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("IsRefresh","Yes");
        }
    }

    private void initViews(View view){
        /*setting up the recyclerView*/
        recyclerViewRecordings= view.findViewById(R.id.recyclerViewRecordings);
        recyclerViewRecordings.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewRecordings.setHasFixedSize(false);
        textViewNoRecordings= view.findViewById(R.id.textViewNoRecordings);
    }

    private void fetchRecordings(View viewRecycler){
        File root= Environment.getExternalStorageDirectory();
        String path=root.getAbsolutePath()+"/VoiceRecorder/Audio";
        Log.d("Files","Path: "+path);
        File directory=new File(path);
        File[] files=directory.listFiles();
        //Log.d("Files","Size: "+files.length);
        if (files!=null){
            for (int i=0;i<files.length; i++){
                Log.d("Files", "FileName: "+files[i].getName());
                String fileName=files[i].getName();
                String recordingURI=root.getAbsolutePath()+"/VoiceRecorder/Audio/"+fileName;

                Recording recording=new Recording(recordingURI, fileName, false);
                recordingArrayList.add(recording);
            }
//            textViewNoRecordings.setVisibility(View.GONE);
            recyclerViewRecordings.setVisibility(View.VISIBLE);
            setAdaptertoRecyclerView();
        }else {
//            textViewNoRecordings.setVisibility(View.VISIBLE);
            recyclerViewRecordings.setVisibility(View.GONE);
        }
    }

    public void setAdaptertoRecyclerView(){
        recordingAdapter =new RecordingAdapter(getActivity(), recordingArrayList);
        recyclerViewRecordings.setAdapter(recordingAdapter);
    }
}
