package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.classes.Monster;
import com.example.erik.questforglory.classes.Player;
import com.example.erik.questforglory.classes.Potion;
import com.example.erik.questforglory.R;

public class Enchanting extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    Player player;
    Monster spriggan;
    Monster golem;
    Potion healthPotion;
    TextView level;
    TextView XP;
    TextView herbs;
    TextView gold;
    String levelText;
    String XPText;
    String herbsText;
    String goldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enchanting);

        player = (Player) getIntent().getSerializableExtra("player");
        spriggan = (Monster) getIntent().getSerializableExtra("spriggan");
        golem = (Monster) getIntent().getSerializableExtra("golem");
        healthPotion = (Potion) getIntent().getSerializableExtra("healthPotion");

        level = (TextView) findViewById(R.id.level);
        XP = (TextView) findViewById(R.id.XP);
        herbs = (TextView) findViewById(R.id.herbs);
        gold = (TextView) findViewById(R.id.gold);

        levelText = "Level " + (int) player.getLevel();
        XPText = "XP " + (int) player.getXP() + " / " + (int) player.getXPToNextLevel();
        herbsText = " Herbs: " + (int) player.getHerbs();
        goldText = " Gold: " + (int) player.getGold();

        level.setText(levelText);
        XP.setText(XPText);
        herbs.setText(herbsText);
        gold.setText(goldText);
    }
    public void saveObjectsInBundle() {
        bundle = new Bundle();
        bundle.putSerializable("player", player);
        bundle.putSerializable("spriggan", spriggan);
        bundle.putSerializable("golem", golem);
        bundle.putSerializable("healthPotion", healthPotion);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onBackPressed() {
        intent = new Intent(this, Kingdom.class);
        saveObjectsInBundle();
    }
    public void glory(View view) {
        intent = new Intent(this, Glory.class);
        saveObjectsInBundle();
    }
    public void ascension(View view) {
        intent = new Intent(this, Ascension.class);
        saveObjectsInBundle();
    }
    public void skills(View view) {
        intent = new Intent(this, Skills.class);
        saveObjectsInBundle();
    }
    public void gear(View view) {
        intent = new Intent(this, Gear.class);
        saveObjectsInBundle();
    }
    public void kingdom(View view) {
        intent = new Intent(this, Kingdom.class);
        saveObjectsInBundle();
    }
    public void quests(View view) {
        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
    }
}