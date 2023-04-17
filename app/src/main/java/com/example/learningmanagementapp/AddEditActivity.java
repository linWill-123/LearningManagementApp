package com.example.learningmanagementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learningmanagementapp.Model.Course;
import com.example.learningmanagementapp.databinding.ActivityAddEditBinding;

public class AddEditActivity extends AppCompatActivity {
    private Course course;
    public static final String COURSE_ID = "courseId";
    public static final String COURSE_NAME = "courseName";
    public static final String UNIT_PRICE = "unitPrice";
    private ActivityAddEditBinding activityAddEditBinding;
    private AddAndEditActivityClickHandlers clickHandlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit2);

        course = new Course();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_edit);
        activityAddEditBinding.setCourse(course);

        clickHandlers = new AddAndEditActivityClickHandlers(this);

        activityAddEditBinding.setClickHandlers(clickHandlers);

        Intent i = getIntent();
        if (i.hasExtra(COURSE_ID)) {
            setTitle("Edit  Course");
            course.setCourseName(i.getStringExtra(COURSE_NAME));
            course.setUnitPrice(i.getStringExtra(UNIT_PRICE));
        } else {
            // executed when FAB clicked
            setTitle("Create New Course");
        }
    }

    public class AddAndEditActivityClickHandlers {
        Context context;

        public AddAndEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view) {
            if (course.getCourseName() == null) {
                Toast.makeText(context, "Course name is empty", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent();
                i.putExtra(COURSE_NAME, course.getCourseName());
                i.putExtra(UNIT_PRICE,course.getUnitPrice());
                setResult(RESULT_OK,i);
                finish();
            }
        }
    }

}