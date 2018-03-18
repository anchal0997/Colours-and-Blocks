package com.ex.anchalgarg.somegame;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    HashMap<Integer,Integer> arr=new HashMap<>();
    int present=0;
    int nomoves=0;
    // int first =0;
    int firstw;
    int cnt=0;
    GridView gridview ;
    int visited[]=new int[49];
    //int parent[]=new int[26];
    private Integer[] mThumbIds = {
            R.drawable.bluesquare,R.drawable.green_sq,R.drawable.yellowsquare,R.drawable.pink_sq,R.drawable.redsquare
    };
    WinningMessage frag = new WinningMessage();
    FrameLayout fr ;
    FragmentManager mananger;
    FragmentTransaction trans;
    Button mRefresh,mExit,mChangelevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        arr.clear();
        // final View[] mView = new View[1];
        RelativeLayout mRelLayout=(RelativeLayout)findViewById(R.id.relLayout);
        ImageButton mImageBtn=(ImageButton)findViewById(R.id.imageView6);
        ImageButton mImageBtn1=(ImageButton)findViewById(R.id.imageView8);
        ImageButton mImageBtn2=(ImageButton)findViewById(R.id.imageView9);
        final TextView mTextView=(TextView)findViewById(R.id.movesLabel);
        ImageButton mImageBtn3=(ImageButton)findViewById(R.id.imageView10);
        ImageButton mImageBtn4=(ImageButton)findViewById(R.id.imageView12);
        gridview = (GridView) findViewById(R.id.gridView);
        mananger=getSupportFragmentManager();
        frag.p2 = Main2Activity.this;
        fr = (FrameLayout) findViewById(R.id.fragment_container);
        //mRelLayout.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRefresh = (Button)findViewById(R.id.button);
        mExit =(Button)findViewById(R.id.Exit);
        mChangelevel = (Button)findViewById(R.id.button2);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                Log.d("heu",""+id);
                // int x=position+arr.size()-25;
                Log.d("heu",""+arr);
                //mView[0] =view;
                present=position;
                if ((int)id != id) {
                    throw new ArithmeticException("integer overflow");
                }
                switch (arr.get(position))
                {
                    case 4:
                        Log.d("smthing","red");
                        //present=4;
                        break;
                    case 2:
                        Log.d("smthing","yellow");
                        //present=2;
                        break;
                    case 1:
                        Log.d("smthing","green");
                        //present=1;
                        break;
                    case 3:
                        Log.d("smthing","pink");
                        //present=3;
                        break;
                    case 0:
                        Log.d("smthing","blue");
                        //present=0;
                        break;


                }

                printcolor();

            }
        });

        // arr.remove(0);
        //arr.put(0,0);

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        mChangelevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                MainActivity.flag2=true;
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.super.finish();
                System.exit(0);
            }
        });

        mImageBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                /*if(arr.containsKey(0)){
                    arr.remove(0);
                    arr.put(0,first);
                }*/


                if(present!=-1)
                {
                    nomoves++;
                    Arrays.fill(visited,0);
                    ffill(present,arr.get(present),0);

                }
                test();
                mTextView.setText(""+nomoves);

            }
        });

        mImageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(present!=-1)
                {

                    nomoves++;

                    Arrays.fill(visited,0);
                    ffill(present,arr.get(present),3);

                }
                test();
                mTextView.setText(""+nomoves);

            }
        });

        mImageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(present!=-1)
                {
                    nomoves++;

                    Arrays.fill(visited,0);

                    ffill(present,arr.get(present),1);

                }
                test();
                mTextView.setText(""+nomoves);

            }
        });
        mImageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(present!=-1)
                {
                    nomoves++;

                    Arrays.fill(visited,0);
                    ffill(present,arr.get(present),2);

                }
                test();

                mTextView.setText(""+nomoves);

            }
        });
        mImageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(present!=-1)
                {
                    nomoves++;

                    Arrays.fill(visited,0);
                    ffill(present,arr.get(present),4);

                }
                test();
                mTextView.setText(""+nomoves);

            }
        });

        gridview.setAdapter(new Main2Activity.ImageAdapter1(this));
    }
    void printcolor(){

        System.out.println("Color chnge----------------"+" "+firstw);
        for(int i=0;i<49;i++)
        {
            System.out.println(arr.get(i));
        }
    }




    Boolean test()
    {int i,j;
        int value=arr.get(0);
        for(i=0;i<7;i++) {
            for (j = 0; j < 7; j++) {
                if (arr.get(i * 7 + j) != value)
                    return false;
            }
        }

        fr.setVisibility(View.VISIBLE);
        trans =mananger.beginTransaction();
        trans.add(R.id.fragment_container,frag);
        trans.commit();
        mRefresh.setVisibility(View.INVISIBLE);
        return true;
    }

    void ffill(int present,int oc,int nc)
    {
        if(visited[present]==1)
            return;

        visited[present]=1;

        int x=present/7;
        int y=present%7;

        if(arr.get(present)!=oc)
            return;
        if(present==0)
        {
            firstw=nc;
            System.out.println("First---"+firstw);
        }


        arr.put(present,nc);

        ImageView viewItem =(ImageView) gridview.getChildAt(x*7+y);
        viewItem.setImageResource(mThumbIds[nc]);
        System.out.println("Change ---"+arr.get(0));


        if(x>0)
            ffill((x-1)*7+y,oc,nc);
        if(x<6)
            ffill((x+1)*7+y,oc,nc);
        if(y>0)
            ffill(x*7+y-1,oc,nc);
        if(y<6)
            ffill(x*7+y+1,oc,nc);
    }



    public class ImageAdapter1 extends BaseAdapter
    {
        private Context mContext;
        public ImageAdapter1(Context c)
        {
            mContext = c;
        }

        public int getCount()
        {
            return 49;
        }

        public Object getItem(int position)
        {
            return null;
        }

        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView;
            imageView = new ImageView(mContext);
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int Dpi = (int)(metrics.density);
            imageView.setLayoutParams(new GridView.LayoutParams(Dpi*130/3, Dpi*150/3));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
            Log.d("tadaa2",""+arr+ " "+arr.size());

            cnt++;
            int i;
            Random rand = new Random();
            i = rand.nextInt(5);
            System.out.println(position+" -" + i);
            if(arr.get(position)==null)
                arr.put(position,i);
            else {
                if(position==0&&arr.get(3)==null) {
                    firstw = i;
                    arr.put(0,i);
                }
                if(position!=0) {

                    arr.put(position, i);

                }
            }

            if(position==0&&arr.get(3)==null)
            {

                System.out.println("Zero again "+i);

            }


            imageView.setId(i);
            Log.d("haha", "" + position + " " + i);
            Log.d("hm", "hm " + position + " " + arr.size());



            imageView.setImageResource(mThumbIds[i]);
            //imageView.setImageResource(R.drawable.bluesquare);
            //}
            return imageView;
        }



    }

    void refresh(){
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new Main2Activity.ImageAdapter1(this));
        fr.setVisibility(View.INVISIBLE);
        trans = mananger.beginTransaction();
        trans.remove(frag);
        trans.commit();
        mRefresh.setVisibility(View.VISIBLE);
    }
}
