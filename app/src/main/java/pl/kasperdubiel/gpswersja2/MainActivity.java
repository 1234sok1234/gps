package pl.kasperdubiel.gpswersja2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
TextView pn;
TextView pa;
ImageView pp;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pn=findViewById(R.id.textView);
		pa=findViewById(R.id.textView3);
		pp=findViewById(R.id.imageView);
		pn.setText("kaspi Dibi");
		pa.setText("fajnie");
		pp.setImageResource(R.mipmap.photo);
	}
}
