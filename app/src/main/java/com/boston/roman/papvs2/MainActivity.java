package com.boston.roman.papvs2;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAccept, btnCalc, btnAbout, btnLink;
    EditText dim;
    int MatrixDim;
    GridView gv;
    GridView gvitem;
    TextView tv, tvlink;
    BoxAdapter ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccept = (Button) findViewById(R.id.btnAccept);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        dim = (EditText) findViewById(R.id.dim);
        gv = (GridView) findViewById(R.id.gv);
        gvitem = (GridView) findViewById(R.id.gv);
        tv = (TextView) findViewById(R.id.tview);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        tvlink = (TextView) findViewById(R.id.tvlink);

        tvlink.setClickable(true);
        tvlink.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.google.com'> Google </a>";
        tvlink.setText(Html.fromHtml(text));

        btnCalc.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        tv.setText(ba.getsum()+"");
                    }
                });

        btnAbout.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //Intent Intent = new Intent(this, activity_about.class);
                        //startActivity(Intent);
                        startActivity(new Intent(getApplication(), ActAbout.class));
                    }
                });

        btnAccept.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        MatrixDim = Integer.parseInt(dim.getText().toString());
                        gv.setNumColumns(MatrixDim);

                        ba = new BoxAdapter(getApplication(), MatrixDim);
                        gv.setAdapter(ba);
                    }
                }
        );
    }

    public class BoxAdapter extends BaseAdapter
    {
        Context ctx;
        LayoutInflater lInflater;
        int size;
        ArrayList<EditText> edmas;

        // конструктор
        BoxAdapter(Context context, int sizet) {
            ctx = context;
            size = sizet * sizet;
            lInflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            edmas=new ArrayList<EditText>(sizet * sizet);
        }

        // кол-во элементов
        @Override
        public int getCount() {
            return size;
        }

        // элемент по позиции
        @Override
        public Object getItem(int position) {
            return 2;
        }

        // id по позиции
        @Override
        public long getItemId(int position) {
            return position;
        }

        // пункт списка
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                //первым параметром id layout для ячейки
                view = lInflater.inflate(R.layout.item, parent, false);
            }
            Log.d("flag", position + "");
            EditText ed = (EditText) view.findViewById(R.id.ed);
            edmas.add(ed);

            return view;
        }

        public double getsum()
        {
            double sum=0;
            for(int i = 0; i < size; i++)
            {
                sum+=(Double.parseDouble(edmas.get(i).getText().toString()));
            }
            return sum;
        }
    }
}

