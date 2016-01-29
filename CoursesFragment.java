package com.st.advancedtopics;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class CoursesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_courses, container, false);
        RadioGroup rgCourses = (RadioGroup) v.findViewById(R.id.rgCourses);
        rgCourses.setOnCheckedChangeListener(this);

        return v;

    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {

        String desc = "";
        switch (checkedId) {
            case R.id.rbAndroid:
                desc = "Android Programming ..";
                break;
            case R.id.rbJava:
                desc = "Java SE and EE ..";
                break;
            case R.id.rbOracle:
                desc = "Oracle Database ..";
                break;
        }

        Log.i("Course", "Description :  " + desc);

        FragmentsDemo fd = (FragmentsDemo) getActivity();
        Log.i("Course", "Obtained Activity " + fd.toString());
        fd.setCourseDescription(desc);

    }


}
