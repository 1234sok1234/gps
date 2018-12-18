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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Chart extends AppCompatActivity
{
	float rainfall[] = {1f, 2f, 3f};
	float monthNames[] = {2f, 4f, 6f};
	private LineChart chart;
	public String Filename;
	ArrayList<Entry> entries;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		getintent();

	}

	private void getintent()
	{
		if (getIntent().hasExtra("Filename"))
		{
			Filename = getIntent().getStringExtra("Filename");
			load();
		}
	}

	private void load()
	{

		chart = findViewById(R.id.chart);
		chart.setDragEnabled(true);
		chart.setScaleEnabled(true);

		entries = new ArrayList<>();
		fileget();
		//entries.add(new Entry(1f, 5f));
		//entries.add(new Entry(2.5f, 10.7f));
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

	private void fileget()
	{
		FileInputStream fis = null;
		try
		{
			fis = openFileInput(Filename);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String text;
			int i = 0;
			int c = 0;
			long czasstartu = 0;
			long czas = 0;
			Double pren = 0d;
			float prrr = 0;

			while ((text = br.readLine()) != null)
			{
				Log.e("xd", "to co pobralismy");
				Log.e("xd", text);
				Log.e("xd", "*********************");
				Log.e("xd", Integer.toString(i));
				Log.e("xd", "*****************");
				if (i == 0)
				{
					czas = Long.parseLong(text);
					if (c == 0)
					{

						czasstartu = czas;
						Log.e("xd", "=============== wd");
						c++;
					}
					czas = (czas - czasstartu);
					prrr=czas;
					prrr=prrr/1000;
					Log.e("xd", "=============== czassssssssssssssssssssss");
					Log.e("xd", Long.toString(czas));
					Log.e("xd", Float.toString(prrr));

					i++;
				} else if (i == 1)
				{
					Log.e("xd", "prendosccccc");
					pren = Double.parseDouble(text);
					//prrr=pren.floatValue();
					entries.add(new Entry(prrr, pren.floatValue()));

					i = 0;
				}
			}


		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
