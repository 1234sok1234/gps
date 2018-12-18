package pl.kasperdubiel.gpswersja2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Chart extends AppCompatActivity
{
	float rainfall[] = {98.8f, 123.8f, 123.2f, 26.2f,123f,2f,123f,1345f};
	float monthNames[] = {1f, 2f, 3f, 4f,20f,23f,24f,56f};
	private LineChart chart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		chart = findViewById(R.id.chart);
		chart.setDragEnabled(true);
		chart.setScaleEnabled(true);

		ArrayList<Entry> entries = new ArrayList<>();
int z=monthNames.length;
		Log.e("ascsa", Integer.toString(z));
		for (int i = 0; i < z; i++)
		{
			entries.add(new Entry(monthNames[i],rainfall[i]));
		}
		//entries.add(new Entry(1f, 5f));
	//	entries.add(new Entry(2.5f, 10.7f));
		//entries.add(new Entry(3f, 12f));
		//entries.add(new Entry(4f, 2f));
		LineDataSet dataSet = new LineDataSet(entries, "Wykres");
		dataSet.setColor(Color.RED);
		dataSet.setDrawFilled(true);
		dataSet.setFillColor(Color.RED);
		dataSet.setDrawCircles(false);
		dataSet.setFillAlpha(500);
		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		ArrayList<ILineDataSet> datasets = new ArrayList<>();
		datasets.add(dataSet);
		LineData lineData = new LineData(datasets);


		chart.setData(lineData);
		chart.invalidate(); // refresh
	}
}
