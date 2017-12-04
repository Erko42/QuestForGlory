package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.classes.GearPiece;
import com.example.erik.questforglory.classes.Monster;
import com.example.erik.questforglory.classes.Player;
import com.example.erik.questforglory.classes.Potion;
import com.example.erik.questforglory.classes.Skill;
import com.example.erik.questforglory.R;

public class Kingdom extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    Player player;
    Monster spriggan;
    Monster golem;
    Potion healthPotion;
    Potion damagePotion;
    Skill strike;
    Skill charge;
    Skill mend;
    Skill defenseUp;
    GearPiece gearPiece;
    TextView level;
    TextView XP;
    TextView herbs;
    TextView ores;
    TextView soulDust;
    TextView gold;
    String levelText;
    String XPText;
    String goldText;
    String herbsText;
    String oresText;
    String soulDustText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingdom);

        player = (Player) getIntent().getSerializableExtra("player");
        spriggan = (Monster) getIntent().getSerializableExtra("spriggan");
        golem = (Monster) getIntent().getSerializableExtra("golem");
        healthPotion = (Potion) getIntent().getSerializableExtra("healthPotion");
        damagePotion = (Potion) getIntent().getSerializableExtra("damagePotion");
        strike = (Skill) getIntent().getSerializableExtra("strike");
        charge = (Skill) getIntent().getSerializableExtra("charge");
        mend = (Skill) getIntent().getSerializableExtra("mend");
        defenseUp = (Skill) getIntent().getSerializableExtra("defenseUp");
        gearPiece = (GearPiece) getIntent().getSerializableExtra("gearPiece");

        level = findViewById(R.id.level);
        XP = findViewById(R.id.XP);
        herbs = findViewById(R.id.herbs);
        ores = findViewById(R.id.ores);
        soulDust = findViewById(R.id.soulDust);
        gold = findViewById(R.id.gold);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());

        herbsText = " Herbs: " + Math.round(player.getHerbs());
        oresText = " Ores: " + Math.round(player.getOres());
        soulDustText = " Soul Dust: " + Math.round(player.getSoulDust());
        goldText = " Gold: " + Math.round(player.getGold());

        level.setText(levelText);
        XP.setText(XPText);
        herbs.setText(herbsText);
        ores.setText(oresText);
        soulDust.setText(soulDustText);
        gold.setText(goldText);
    }

    public void saveObjectsInBundle() {
        bundle = new Bundle();
        bundle.putSerializable("player", player);
        bundle.putSerializable("spriggan", spriggan);
        bundle.putSerializable("golem", golem);
        bundle.putSerializable("healthPotion", healthPotion);
        bundle.putSerializable("damagePotion", damagePotion);
        bundle.putSerializable("strike", strike);
        bundle.putSerializable("charge", charge);
        bundle.putSerializable("mend", mend);
        bundle.putSerializable("defenseUp", defenseUp);
        bundle.putSerializable("gearPiece", gearPiece);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBackPressed() {
        intent = new Intent(this, MainActivity.class);
        saveObjectsInBundle();
    }

    public void alchemy(View view) {
        intent = new Intent(this, Alchemy.class);
        saveObjectsInBundle();
    }

    public void blacksmithing(View view) {
        intent = new Intent(this, Blacksmithing.class);
        saveObjectsInBundle();
    }

    public void enchanting(View view) {
        intent = new Intent(this, Enchanting.class);
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

    public void quests(View view) {
        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
    }
}