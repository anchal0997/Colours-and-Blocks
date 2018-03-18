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

public class MainActivity extends AppCompatActivity {
   // ArrayList<Integer> arr=new ArrayList<>();
    HashMap<Integer,Integer> arr=new HashMap<>();
    int present=0;
    int nomoves=0;
    public static int x=0;
   // int first =0;
    int firstw;
    boolean flag = false;
    public static boolean flag2 = false;
    int cnt=0;
    GridView gridview ;
    int visited[]=new int[49];
    //int parent[]=new int[26];
    private Integer[] mThumbIds = {
            R.drawable.bluesquare,R.drawable.green_sq,R.drawable.yellowsquare,R.drawable.pink_sq,R.drawable.redsquare
    };


    welcomeScreen mWelcome=new welcomeScreen();
    WinningMessage frag = new WinningMessage();
    FrameLayout fr ;
    FragmentManager mananger;
    FragmentTransaction trans;
    Button mChangeLevel;
    Button mRefresh;
    Button mExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arr.clear();
       // final View[] mView = new View[1];
        RelativeLayout mRelLayout=(RelativeLayout)findViewById(R.id.relLayout);
        ImageButton mImageBtn=(ImageButton)findViewById(R.id.imageView6);
        ImageButton mImageBtn1=(ImageButton)findViewById(R.id.imageView8);
        ImageButton mImageBtn2=(ImageButton)findViewById(R.id.imageView9);
        final TextView mTextView=(TextView)findViewById(R.id.movesLabel);
        ImageButton mImageBtn3=(ImageButton)findViewById(R.id.imageView10);
        ImageButton mImageBtn4=(ImageButton)findViewById(R.id.imageView12);
        mChangeLevel=(Button)findViewById(R.id.button2);
        mExit=(Button)findViewById(R.id.Exit);
        gridview = (GridView) findViewById(R.id.gridView);
        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);
        //mRelLayout.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mWelcome.inst = MainActivity.this;
        frag.p = MainActivity.this;
        trans = mananger.beginTransaction();
        trans.add(R.id.fragment_container, mWelcome);
        trans.commit();
        mRefresh = (Button)findViewById(R.id.button);
        //ft.add(frame.getId(),mWelcome.getTargetFragment());
            //ft.add(frame.getId(), mWelcome).commit();

        if(!flag) {
            mChangeLevel.setVisibility(View.INVISIBLE);
            mRefresh.setVisibility(View.INVISIBLE);
            mExit.setVisibility(View.INVISIBLE);
            fr.setVisibility(View.VISIBLE);
        }
        if(flag2){
            mChangeLevel.setVisibility(View.VISIBLE);
            mRefresh.setVisibility(View.VISIBLE);
            mExit.setVisibility(View.VISIBLE);
            fr.setVisibility(View.INVISIBLE);
        }
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 MainActivity.super.finish();
                 System.exit(0);
                //finish();
            }
        });




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
        mChangeLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
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

        gridview.setAdapter(new ImageAdapter1(this));
    }

    void printcolor(){

        System.out.println("Color chnge----------------"+" "+firstw);
        for(int i=0;i<25;i++)
        {
            System.out.println(arr.get(i));
        }
    }




    Boolean test()
    {int i,j;
        int value=arr.get(0);
        for(i=0;i<5;i++) {
            for (j = 0; j < 5; j++) {
                if (arr.get(i * 5 + j) != value)
                    return false;
            }
        }

        fr.setVisibility(View.VISIBLE);
        trans =mananger.beginTransaction();
        trans.replace(R.id.fragment_container,frag);
        trans.commit();
        mRefresh.setVisibility(View.INVISIBLE);

        return true;
    }

    void ffill(int present,int oc,int nc)
    {
        if(visited[present]==1)
            return;

        visited[present]=1;

        int x=present/5;
        int y=present%5;

        if(arr.get(present)!=oc)
        return;
        if(present==0)
        {
            firstw=nc;
            System.out.println("First---"+firstw);
        }


        arr.put(present,nc);

        ImageView viewItem =(ImageView) gridview.getChildAt(x*5+y);
        viewItem.setImageResource(mThumbIds[nc]);
        System.out.println("Change ---"+arr.get(0));


        if(x>0)
            ffill((x-1)*5+y,oc,nc);
        if(x<4)
            ffill((x+1)*5+y,oc,nc);
        if(y>0)
            ffill(x*5+y-1,oc,nc);
        if(y<4)
            ffill(x*5+y+1,oc,nc);
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
            return 25;
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
                imageView.setLayoutParams(new GridView.LayoutParams(Dpi*180/3, Dpi*209/3));
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

    void play(){
        flag = true;
        fr.setVisibility(View.INVISIBLE);
        mChangeLevel.setVisibility(View.VISIBLE);
        mRefresh.setVisibility(View.VISIBLE);
        mExit.setVisibility(View.VISIBLE);
    }

    void removerules(){
        trans = mananger.beginTransaction();
        trans.remove(frag);
        trans.commit();
    }

    void refresh(){
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter1(this));
        trans = mananger.beginTransaction();
        trans.remove( frag);
        trans.commit();
        fr.setVisibility(View.INVISIBLE);
        mRefresh.setVisibility(View.VISIBLE);
       // mChangeLevel.setVisibility(View.VISIBLE);
    }

}
