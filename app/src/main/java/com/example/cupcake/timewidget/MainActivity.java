package com.example.cupcake.timewidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private TimeWidgetLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeWidget widget = new TimeWidget(this);
        root = (TimeWidgetLayout) findViewById(R.id.timeWidgetLayout);
        root.addView(widget);
    }
}
