package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Quests extends AppCompatActivity {
    
    SharedPreferences preferences;
    InterstitialAd mInterstitialAd;
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
    TextView theQuest;
    TextView theForest;
    TextView theMountain;
    TextView theRuin;
    String levelText;
    String XPText;
    String theQuestText;
    String theForestText;
    String theMountainText;
    String theRuinText;
    float monstersRequiredLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        preferences = getSharedPreferences("Data", 0);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6115526968446578/1787476245");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                saveObjectsInBundle();
            }
        });

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
        monstersRequiredLevel = preferences.getFloat("monstersRequiredLevel", 0);

        level = findViewById(R.id.level);
        XP = findViewById(R.id.XP);
        theQuest = findViewById(R.id.theQuest);
        theForest = findViewById(R.id.theForest);
        theMountain = findViewById(R.id.theMountain);
        theRuin = findViewById(R.id.theRuin);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        theForestText = "The Forest\nLevel " + Math.round(spriggan.getLevel());
        theMountainText = "The Mountain\nLevel " + Math.round(golem.getLevel());
        theRuinText = "The Ruin\nLevel " + 0;
        theQuestText = "Kill The Level 1 Monster In The Forest, Mountain and Ruin";

        level.setText(levelText);
        XP.setText(XPText);
        theQuest.setText(theQuestText);
        theForest.setText(theForestText);
        theMountain.setText(theMountainText);
        theRuin.setText(theRuinText);

        if (spriggan.getMaxLevel() >= preferences.getFloat("monstersRequiredLevel", 0) && golem.getMaxLevel() >= preferences.getFloat("monstersRequiredLevel", 0)) {
            player.increaseGlory(1);
            monstersRequiredLevel += 1;

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("monstersRequiredLevel", monstersRequiredLevel).apply();
        }
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

    public void increaseTheForestLevel(View view) {
        if (spriggan.getLevel() < spriggan.getMaxLevel()) {
            spriggan.levelUp();
            charge.refresh();
            charge.setOnDurationCooldown(false);
            mend.refresh();
            mend.setOnDurationCooldown(false);
            defenseUp.refresh();
            defenseUp.setOnDurationCooldown(false);

            theForestText = "The Forest\nLevel " + Math.round(spriggan.getLevel());
            theForest.setText(theForestText);

            preferences.edit().putFloat("chargeCurrentDuration", charge.getCurrentDuration()).apply();
            preferences.edit().putFloat("chargeCurrentCooldown", charge.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("mendCurrentDuration", mend.getCurrentDuration()).apply();
            preferences.edit().putFloat("mendCurrentCooldown", mend.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("defenseUpCurrentDuration", defenseUp.getCurrentDuration()).apply();
            preferences.edit().putFloat("defenseUpCurrentCooldown", defenseUp.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("sprigganLevel", spriggan.getLevel()).apply();
            preferences.edit().putFloat("sprigganXPYield", spriggan.getXPYield()).apply();
            preferences.edit().putFloat("sprigganMaxHealth", spriggan.getMaxHealth()).apply();
            preferences.edit().putFloat("sprigganHealth", spriggan.getHealth()).apply();
            preferences.edit().putFloat("sprigganDamage", spriggan.getDamage()).apply();
            preferences.edit().putFloat("sprigganGoldYield", spriggan.getGoldYield()).apply();
            preferences.edit().putFloat("sprigganHerbYield", spriggan.getHerbYield()).apply();
        }
    }

    public void decreaseTheForestLevel(View view) {
        if (spriggan.getLevel() > 1) {
            spriggan.levelDown();
            charge.refresh();
            charge.setOnDurationCooldown(false);
            mend.refresh();
            mend.setOnDurationCooldown(false);
            defenseUp.refresh();
            defenseUp.setOnDurationCooldown(false);

            theForestText = "The Forest\nLevel " + Math.round(spriggan.getLevel());
            theForest.setText(theForestText);

            preferences.edit().putFloat("chargeCurrentDuration", charge.getCurrentDuration()).apply();
            preferences.edit().putFloat("chargeCurrentCooldown", charge.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("mendCurrentDuration", mend.getCurrentDuration()).apply();
            preferences.edit().putFloat("mendCurrentCooldown", mend.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("defenseUpCurrentDuration", defenseUp.getCurrentDuration()).apply();
            preferences.edit().putFloat("defenseUpCurrentCooldown", defenseUp.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("sprigganMaxLevel", spriggan.getMaxLevel()).apply();
            preferences.edit().putFloat("sprigganLevel", spriggan.getLevel()).apply();
            preferences.edit().putFloat("sprigganXPYield", spriggan.getXPYield()).apply();
            preferences.edit().putFloat("sprigganMaxHealth", spriggan.getMaxHealth()).apply();
            preferences.edit().putFloat("sprigganHealth", spriggan.getHealth()).apply();
            preferences.edit().putFloat("sprigganDamage", spriggan.getDamage()).apply();
            preferences.edit().putFloat("sprigganGoldYield", spriggan.getGoldYield()).apply();
            preferences.edit().putFloat("sprigganHerbYield", spriggan.getHerbYield()).apply();
        }
    }

    public void increaseTheMountainLevel(View view) {
        if (golem.getLevel() < golem.getMaxLevel()) {
            golem.levelUp();
            charge.refresh();
            charge.setOnDurationCooldown(false);
            mend.refresh();
            mend.setOnDurationCooldown(false);
            defenseUp.refresh();
            defenseUp.setOnDurationCooldown(false);

            theMountainText = "The Mountain\nLevel " + Math.round(golem.getLevel());
            theMountain.setText(theMountainText);

            preferences.edit().putFloat("chargeCurrentDuration", charge.getCurrentDuration()).apply();
            preferences.edit().putFloat("chargeCurrentCooldown", charge.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("mendCurrentDuration", mend.getCurrentDuration()).apply();
            preferences.edit().putFloat("mendCurrentCooldown", mend.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("defenseUpCurrentDuration", defenseUp.getCurrentDuration()).apply();
            preferences.edit().putFloat("defenseUpCurrentCooldown", defenseUp.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("golemLevel", golem.getLevel()).apply();
            preferences.edit().putFloat("golemXPYield", golem.getXPYield()).apply();
            preferences.edit().putFloat("golemMaxHealth", golem.getMaxHealth()).apply();
            preferences.edit().putFloat("golemHealth", golem.getHealth()).apply();
            preferences.edit().putFloat("golemDamage", golem.getDamage()).apply();
            preferences.edit().putFloat("golemGoldYield", golem.getGoldYield()).apply();
            preferences.edit().putFloat("golemOreYield", golem.getOreYield()).apply();
        }
    }

    public void decreaseTheMountainLevel(View view) {
        if (golem.getLevel() > 1) {
            golem.levelDown();
            charge.refresh();
            charge.setOnDurationCooldown(false);
            mend.refresh();
            mend.setOnDurationCooldown(false);
            defenseUp.refresh();
            defenseUp.setOnDurationCooldown(false);

            theMountainText = "The Mountain\nLevel " + Math.round(golem.getLevel());
            theMountain.setText(theMountainText);

            preferences.edit().putFloat("chargeCurrentDuration", charge.getCurrentDuration()).apply();
            preferences.edit().putFloat("chargeCurrentCooldown", charge.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("mendCurrentDuration", mend.getCurrentDuration()).apply();
            preferences.edit().putFloat("mendCurrentCooldown", mend.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("defenseUpCurrentDuration", defenseUp.getCurrentDuration()).apply();
            preferences.edit().putFloat("defenseUpCurrentCooldown", defenseUp.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();

            preferences.edit().putFloat("golemLevel", golem.getLevel()).apply();
            preferences.edit().putFloat("golemXPYield", golem.getXPYield()).apply();
            preferences.edit().putFloat("golemMaxHealth", golem.getMaxHealth()).apply();
            preferences.edit().putFloat("golemHealth", golem.getHealth()).apply();
            preferences.edit().putFloat("golemDamage", golem.getDamage()).apply();
            preferences.edit().putFloat("golemGoldYield", golem.getGoldYield()).apply();
            preferences.edit().putFloat("golemOreYield", golem.getOreYield()).apply();
        }
    }

    public void theForest(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        }
        intent = new Intent(this, TheForest.class);
        saveObjectsInBundle();
    }

    public void theMountain(View view) {
        intent = new Intent(this, TheMountain.class);
        saveObjectsInBundle();
    }

    public void theRuin(View view) {
        intent = new Intent(this, TheRuin.class);
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
}