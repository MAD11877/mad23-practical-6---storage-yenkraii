package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);



    }

    @Override
    protected void onStart(){
        super.onStart();

        Intent rec = getIntent();
        User user = (User) rec.getSerializableExtra("user");

        // attach listener to the button
        Button but = findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.followed == false){
                    but.setText("UNFOLLOW");
                    user.followed = true;

                    // toast
                    Toast.makeText(getApplicationContext(),"Unfollowed!",Toast.LENGTH_LONG);

                }
                else{
                    but.setText("FOLLOW");
                    user.followed = false;

                    Toast.makeText(getApplicationContext(),"Followed!",Toast.LENGTH_LONG);
                }
            }
        });

        // list activity

        Button b = findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        // changing the text value
        TextView title = findViewById(R.id.textView);
        TextView descr = findViewById(R.id.textView2);
        title.setText(user.name);
        descr.setText(user.description);

    }

}
