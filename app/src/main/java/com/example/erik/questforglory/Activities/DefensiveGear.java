package com.example.erik.questforglory.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.Classes.Monster;
import com.example.erik.questforglory.Classes.Player;
import com.example.erik.questforglory.Classes.Potion;
import com.example.erik.questforglory.Classes.Skill;
import com.example.erik.questforglory.Classes.GearPiece;
import com.example.erik.questforglory.R;

public class DefensiveGear extends AppCompatActivity {

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
    TextView ores;
    TextView gold;
    String levelText;
    String XPText;
    String oresText;
    String goldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defensive_gear);

        bundle = new Bundle();

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
        ores = findViewById(R.id.ores);
        gold = findViewById(R.id.gold);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        oresText = " Ores: " + Math.round(player.getOres());
        goldText = " Gold: " + Math.round(player.getGold());

        level.setText(levelText);
        XP.setText(XPText);
        ores.setText(oresText);
        gold.setText(goldText);
    }
    public void saveObjectsInBundle() {
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
        intent = new Intent(this, Blacksmithing.class);
        saveObjectsInBundle();
    }
    public void helms(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Helm");
        saveObjectsInBundle();
    }
    public void pauldrons(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Pauldrons");
        saveObjectsInBundle();
    }
    public void chestplates(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Chestplate");
        saveObjectsInBundle();
    }
    public void mainHandSwords(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "MainHand Sword");
        saveObjectsInBundle();
    }
    public void offHandShields(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "OffHand Shield");
        saveObjectsInBundle();
    }
    public void bracers(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Bracers");
        saveObjectsInBundle();
    }
    public void gauntlets(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Gauntlets");
        saveObjectsInBundle();
    }
    public void belts(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Belt");
        saveObjectsInBundle();
    }
    public void legplates(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Legplates");
        saveObjectsInBundle();
    }
    public void sabatons(View view) {
        intent = new Intent(this, DefensiveGearPieces.class);
        bundle.putString("gearPieceType", "Sabatons");
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