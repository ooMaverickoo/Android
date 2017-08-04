package com.example.android.reportcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReportCard reportCard = new ReportCard(ReportCard.COUNTRY.EU, ReportCard.DATE_STYLE.EU);

        TextView report = (TextView) findViewById(R.id.report_card);
        report.setText(reportCard.toString());


    }



}
