package com.acadgildbatch5.CustomList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.acadgildbatch5.R;

import java.util.ArrayList;

/**
 * Created by Divyanshu on 27-11-2016.
 */

public class CustomListActivty extends Activity {

    String[] title = new String[]{"Title1","Title2","Title3","Title4","Title5","Title1","Title2","Title3","Title4","Title5","Title1","Title2","Title3","Title4","Title5","Title1","Title2","Title3","Title4","Title5"};
    String[] description = new String[]{"Decr 1 kjsjdfsd fdshf dodisfsf oiddoggofig", "Desc 2jdfdsufuduudugudfo gufdoujg",
            "Desc 3 dsfkdkljjljdfsjfljdflffljldlkgjklfdklgljlkdfjgjldfjglkfjkglkhljgjhjhkljdfljgklskjgjgsdljffosdjf",
            "Desc kldskjfjsd hodssosifudsisf oissoifdsfd sdgdfkjlkjgl lfdludfg oo oii osoigigifdifo  ifdjg4", "Desc khdsukkuhfk dsfidsfdsdfiudfddfdfdfg goiufifgiofdudgi5",
            "Decr 1 kjsjdfsd fdshf dodisfsf oiddoggofig", "Desc 2jdfdsufuduudugudfo gufdoujg",
            "Desc 3 dsfkdkljjljdfsjfljdflffljldlkgjklfdklgljlkdfjgjldfjglkfjkglkhljgjhjhkljdfljgklskjgjgsdljffosdjf",
            "Desc kldskjfjsd hodssosifudsisf oissoifdsfd sdgdfkjlkjgl lfdludfg oo oii osoigigifdifo  ifdjg4", "Desc khdsukkuhfk dsfidsfdsdfiudfddfdfdfg goiufifgiofdudgi5",
            "Decr 1 kjsjdfsd fdshf dodisfsf oiddoggofig", "Desc 2jdfdsufuduudugudfo gufdoujg",
            "Desc 3 dsfkdkljjljdfsjfljdflffljldlkgjklfdklgljlkdfjgjldfjglkfjkglkhljgjhjhkljdfljgklskjgjgsdljffosdjf",
            "Desc kldskjfjsd hodssosifudsisf oissoifdsfd sdgdfkjlkjgl lfdludfg oo oii osoigigifdifo  ifdjg4", "Desc khdsukkuhfk dsfidsfdsdfiudfddfdfdfg goiufifgiofdudgi5",
            "Decr 1 kjsjdfsd fdshf dodisfsf oiddoggofig", "Desc 2jdfdsufuduudugudfo gufdoujg",
            "Desc 3 dsfkdkljjljdfsjfljdflffljldlkgjklfdklgljlkdfjgjldfjglkfjkglkhljgjhjhkljdfljgklskjgjgsdljffosdjf",
            "Desc kldskjfjsd hodssosifudsisf oissoifdsfd sdgdfkjlkjgl lfdludfg oo oii osoigigifdifo  ifdjg4", "Desc khdsukkuhfk dsfidsfdsdfiudfddfdfdfg goiufifgiofdudgi5"};
    int[] img= new int[]{
            R.drawable.greencheck,R.drawable.restart,R.drawable.greencheck,R.drawable.restart,R.drawable.moon,
            R.drawable.greencheck,R.drawable.restart,R.drawable.greencheck,R.drawable.restart,R.drawable.moon
            ,R.drawable.greencheck,R.drawable.restart,R.drawable.greencheck,R.drawable.restart,R.drawable.moon
            ,R.drawable.greencheck,R.drawable.restart,R.drawable.greencheck,R.drawable.restart,R.drawable.moon
    };

    ListView ivDetails;
    Context context = CustomListActivty.this;
    ArrayList myList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_listview);


        ivDetails = (ListView)findViewById(R.id.lvCustomList);
        getDataINList();
        ivDetails.setAdapter(new MyBaseAdapter(context,myList));
    }

    private void getDataINList(){
        for(int i=0; i<title.length;i++){
            ListData ld = new ListData();
            ld.setTitle(title[i]);
            ld.setDescription(description[i]);
            ld.setImgResID(img[i]);

            myList.add(ld);
        }
    }
}
