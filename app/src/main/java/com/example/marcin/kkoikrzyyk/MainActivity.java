package com.example.marcin.kkoikrzyyk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img00, img01, img02, img10, img11, img12, img20, img21, img22;
    TextView resP1, resP2;
    Button reset;
    int c = 0;
    int countP1 = 0;
    int countP2 = 0;
    char [][] game_stat = new char [3][3];
    boolean cross = true;

    public boolean who_won(){
        if (game_stat[0][0] == 'X' && game_stat[0][1]=='X' && game_stat[0][2]=='X' || game_stat[0][0]=='O' && game_stat[0][1]=='O' && game_stat[0][2]=='O') return true;
        else if (game_stat[1][0] == 'X' && game_stat[1][1]=='X' && game_stat[1][2]=='X' || game_stat[1][0]=='O' && game_stat[1][1]=='O' && game_stat[1][2]=='O') return true;
        else if (game_stat[2][0] == 'X' && game_stat[2][1]=='X' && game_stat[2][2]=='X' || game_stat[2][0]=='O' && game_stat[2][1]=='O' && game_stat[2][2]=='O') return true;
        else if (game_stat[0][0] == 'X' && game_stat[1][0]=='X' && game_stat[2][0]=='X' || game_stat[0][0]=='O' && game_stat[1][0]=='O' && game_stat[2][0]=='O') return true;
        else if (game_stat[0][1] == 'X' && game_stat[1][1]=='X' && game_stat[2][1]=='X' || game_stat[0][1]=='O' && game_stat[1][1]=='O' && game_stat[2][1]=='O') return true;
        else if (game_stat[0][2] == 'X' && game_stat[1][2]=='X' && game_stat[2][2]=='X' || game_stat[0][2]=='O' && game_stat[1][2]=='O' && game_stat[2][2]=='O') return true;
        else if (game_stat[0][0] == 'X' && game_stat[1][1]=='X' && game_stat[2][2]=='X' || game_stat[0][0]=='O' && game_stat[1][1]=='O' && game_stat[2][2]=='O') return true;
        else if (game_stat[0][2] == 'X' && game_stat[1][1]=='X' && game_stat[2][0]=='X' || game_stat[0][2]=='O' && game_stat[1][1]=='O' && game_stat[2][0]=='O') return true;
        else  return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img00 = (ImageView) findViewById(R.id.image00);
        img01 = (ImageView) findViewById(R.id.image01);
        img02 = (ImageView) findViewById(R.id.image02);
        img10 = (ImageView) findViewById(R.id.image10);
        img11 = (ImageView) findViewById(R.id.image11);
        img12 = (ImageView) findViewById(R.id.image12);
        img20 = (ImageView) findViewById(R.id.image20);
        img21 = (ImageView) findViewById(R.id.image21);
        img22 = (ImageView) findViewById(R.id.image22);
        resP1 = (TextView) findViewById(R.id.res_p1);
        resP2 = (TextView) findViewById(R.id.res_p2);
        reset = (Button) findViewById(R.id.btReset);

        img00.setOnClickListener(this);
        img01.setOnClickListener(this);
        img02.setOnClickListener(this);
        img10.setOnClickListener(this);
        img11.setOnClickListener(this);
        img12.setOnClickListener(this);
        img20.setOnClickListener(this);
        img21.setOnClickListener(this);
        img22.setOnClickListener(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img00.setImageResource(R.drawable.puste);
                img00.setClickable(true);
                img01.setImageResource(R.drawable.puste);
                img01.setClickable(true);
                img02.setImageResource(R.drawable.puste);
                img02.setClickable(true);
                img10.setImageResource(R.drawable.puste);
                img10.setClickable(true);
                img11.setImageResource(R.drawable.puste);
                img11.setClickable(true);
                img12.setImageResource(R.drawable.puste);
                img12.setClickable(true);
                img20.setImageResource(R.drawable.puste);
                img20.setClickable(true);
                img21.setImageResource(R.drawable.puste);
                img21.setClickable(true);
                img22.setImageResource(R.drawable.puste);
                img22.setClickable(true);
                game_stat = new char [3][3];
                c = 0;
            }
        });
    }

    @Override
    public void onClick(View v){
        int field_number = v.getId();
        ImageView clicked_field = (ImageView) findViewById(field_number);
        String field_name = clicked_field.getResources().getResourceName(field_number);
        String column = field_name.substring(field_name.length()-1);
        String row = field_name.substring(field_name.length()-2, field_name.length()-1);
        int index_column = Integer.parseInt(column);
        int index_row = Integer.parseInt(row);

        if (cross == true){
            clicked_field.setImageResource(R.drawable.krzyzk);
            game_stat[index_row][index_column] = 'X';
            cross = false;
        }
        else{
            clicked_field.setImageResource(R.drawable.kolko);
            game_stat[index_row][index_column] = 'O';
            cross = true;
        }
        c+=1;
        clicked_field.setClickable(false);

        if (who_won() == true){
            if (cross == false){
                countP1+=1;
                Toast.makeText(getApplicationContext(),"Wygrał gracz 1", Toast.LENGTH_LONG).show();
            }
            else{
                countP2+=1;
                Toast.makeText(getApplicationContext(),"Wygrał gracz 2", Toast.LENGTH_LONG).show();
            }
            img00.setClickable(false);
            img01.setClickable(false);
            img02.setClickable(false);
            img10.setClickable(false);
            img11.setClickable(false);
            img12.setClickable(false);
            img20.setClickable(false);
            img21.setClickable(false);
            img22.setClickable(false);
            resP1.setText(Integer.toString(countP1));
            resP2.setText(Integer.toString(countP2));
        }

        if (c == 9 && who_won() == false){
            Toast.makeText(getApplicationContext(),"Remis!", Toast.LENGTH_LONG).show();
        }
    }
}
