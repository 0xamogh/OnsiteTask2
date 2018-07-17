package amoghjapps.com.onsitetask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public List<TextView> textViewList;
    public ImageView imageView;
    public ListView listView;
    public ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout myLinear=new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        myLinear.setOrientation(LinearLayout.VERTICAL);
        myLinear.setLayoutParams(params);
        setContentView(myLinear);
        parseJson(getString(R.string.json));



    }
    public void parseJson(String json){
        Gson gson=new Gson();

        Example datumarray=gson.fromJson(json,Example.class);
            for(int i=0;i<datumarray.getData().size();i++){
                Datum data=datumarray.getData().get(i);
                if(data.getType()=="textview"){
                    String paramfont=data.getFontsize();
                    String paramtext=data.getValue().get(0);
                    TextView textview=new TextView(this);
                    textview.setText(paramtext);
                    textview.setTextSize(Float.valueOf(paramfont));
                    textViewList.add(textview);


                }else if(data.getType()=="imageview"){
                    String imageurl=data.getUrl();
                    imageView=new ImageView(this);
                    Picasso.get().load(imageurl).into(imageView);
                }else if(data.getType()=="listview"){
                    listView=new ListView(this);
                    List<String> stringList=data.getValue();
                    adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stringList);
                    listView.setAdapter(adapter);
                }
            }
    }
}
