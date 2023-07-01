package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;




public class ListActivity extends AppCompatActivity {

    static ArrayList<User> usL;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_list);
        if (usL == null){
            usL = createUSL();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        // set up recycler view

        RecyclerView recyclerView = findViewById(R.id.recycler);
        Adapter adapter = new Adapter(this,usL);
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        /*
        // image
        ImageView im = findViewById(R.id.alert);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog();
            }
        });
        */
    }
/*
    private void AlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage("MADness").setCancelable(true);
        Integer i = 0;
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog();
                Integer i = randomOTP();
                // go back to main activity
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("value",i);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


 */

    private int randomOTP(){
        Random ran = new Random();
        int myRan = ran.nextInt(1000000);
        return myRan;
    }

    private boolean randomBool(){
        Random ran = new Random();
        boolean myRan = ran.nextBoolean();
        return myRan;
    }

    public ArrayList<User> createUSL(){
        ArrayList<User> usL = new ArrayList<>();
        for (int i = 0; i<=20; i++){
            User u = new User();
            u.name = "Name " + randomOTP();
            u.description = "Description: " + randomOTP();
            u.followed = randomBool();
            usL.add(u);
        }
        return usL;
    }
}
