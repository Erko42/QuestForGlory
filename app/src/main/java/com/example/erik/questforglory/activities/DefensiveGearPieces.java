package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.classes.Monster;
import com.example.erik.questforglory.classes.Player;
import com.example.erik.questforglory.classes.Potion;
import com.example.erik.questforglory.classes.Skill;
import com.example.erik.questforglory.helpers.DatabaseHelper;
import com.example.erik.questforglory.classes.GearPiece;
import com.example.erik.questforglory.R;

public class DefensiveGearPieces extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences preferences;
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
    GearPiece theGearPiece;
    TextView level;
    TextView XP;
    TextView ores;
    TextView gold;
    TextView gearPieces;
    TextView commonGearPiece;
    TextView commonGearPieceBuy;
    TextView commonGearPieceCraft;
    TextView uncommonGearPiece;
    TextView uncommonGearPieceBuy;
    TextView uncommonGearPieceCraft;
    TextView rareGearPiece;
    TextView rareGearPieceBuy;
    TextView rareGearPieceCraft;
    TextView epicGearPiece;
    TextView epicGearPieceBuy;
    TextView epicGearPieceCraft;
    TextView legendaryGearPiece;
    TextView legendaryGearPieceBuy;
    TextView legendaryGearPieceCraft;
    String levelText;
    String XPText;
    String oresText;
    String goldText;
    String gearPiecesText;
    String commonGearPieceText;
    String commonGearPieceBuyText;
    String commonGearPieceCraftText;
    String uncommonGearPieceText;
    String uncommonGearPieceBuyText;
    String uncommonGearPieceCraftText;
    String rareGearPieceText;
    String rareGearPieceBuyText;
    String rareGearPieceCraftText;
    String epicGearPieceText;
    String epicGearPieceBuyText;
    String epicGearPieceCraftText;
    String legendaryGearPieceText;
    String legendaryGearPieceBuyText;
    String legendaryGearPieceCraftText;
    String gearPieceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defensive_gear_pieces);

        db = new DatabaseHelper(this);

        preferences = getSharedPreferences("Data", 0);

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
        gearPieceType = getIntent().getStringExtra("gearPieceType");

        level = findViewById(R.id.level);
        XP = findViewById(R.id.XP);
        ores = findViewById(R.id.ores);
        gold = findViewById(R.id.gold);
        gearPieces = findViewById(R.id.gearPieces);
        commonGearPiece = findViewById(R.id.commonGearPiece);
        commonGearPieceBuy = findViewById(R.id.commonGearPieceBuy);
        commonGearPieceCraft = findViewById(R.id.commonGearPieceCraft);
        uncommonGearPiece = findViewById(R.id.uncommonGearPiece);
        uncommonGearPieceBuy = findViewById(R.id.uncommonGearPieceBuy);
        uncommonGearPieceCraft = findViewById(R.id.uncommonGearPieceCraft);
        rareGearPiece = findViewById(R.id.rareGearPiece);
        rareGearPieceBuy = findViewById(R.id.rareGearPieceBuy);
        rareGearPieceCraft = findViewById(R.id.rareGearPieceCraft);
        epicGearPiece = findViewById(R.id.epicGearPiece);
        epicGearPieceBuy = findViewById(R.id.epicGearPieceBuy);
        epicGearPieceCraft = findViewById(R.id.epicGearPieceCraft);
        legendaryGearPiece = findViewById(R.id.legendaryGearPiece);
        legendaryGearPieceBuy = findViewById(R.id.legendaryGearPieceBuy);
        legendaryGearPieceCraft = findViewById(R.id.legendaryGearPieceCraft);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        oresText = " Ores: " + Math.round(player.getOres());
        goldText = " Gold: " + Math.round(player.getGold());
        commonGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Common\n" + gearPieceType;
        commonGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost()) + " Gold";
        commonGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost()) + " Ores";
        uncommonGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Uncommon\n" + gearPieceType;
        uncommonGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 2) + " Gold";
        uncommonGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 2) + " Ores";
        rareGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Rare\n" + gearPieceType;
        rareGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 3) + " Gold";
        rareGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 3) + " Ores";
        epicGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Epic\n" + gearPieceType;
        epicGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 4) + " Gold";
        epicGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 4) + " Ores";
        legendaryGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Legendary\n" + gearPieceType;
        legendaryGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 5) + " Gold";
        legendaryGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 5) + " Ores";

        switch (gearPieceType) {
            case "Helm":
                gearPiecesText = "Helms";
                break;
            case "Pauldrons":
                gearPiecesText = "Pauldrons";
                break;
            case "Chestplate":
                gearPiecesText = "Chestplate";
                break;
            case "Bracers":
                gearPiecesText = "Bracers";
                break;
            case "MainHand Sword":
                gearPiecesText = "MainHand Sword";
                break;
            case "OffHand Shield":
                gearPiecesText = "OffHand Shield";
                break;
            case "Gauntlets":
                gearPiecesText = "Gauntlets";
                break;
            case "Belt":
                gearPiecesText = "Belt";
                break;
            case "Legplates":
                gearPiecesText = "Legplates";
                break;
            case "Sabatons":
                gearPiecesText = "Sabatons";
                break;
        }
        gearPieces.setText(gearPiecesText);
        level.setText(levelText);
        XP.setText(XPText);
        ores.setText(oresText);
        gold.setText(goldText);
        commonGearPiece.setText(commonGearPieceText);
        commonGearPieceBuy.setText(commonGearPieceBuyText);
        commonGearPieceCraft.setText(commonGearPieceCraftText);
        uncommonGearPiece.setText(uncommonGearPieceText);
        uncommonGearPieceBuy.setText(uncommonGearPieceBuyText);
        uncommonGearPieceCraft.setText(uncommonGearPieceCraftText);
        rareGearPiece.setText(rareGearPieceText);
        rareGearPieceBuy.setText(rareGearPieceBuyText);
        rareGearPieceCraft.setText(rareGearPieceCraftText);
        epicGearPiece.setText(epicGearPieceText);
        epicGearPieceBuy.setText(epicGearPieceBuyText);
        epicGearPieceCraft.setText(epicGearPieceCraftText);
        legendaryGearPiece.setText(legendaryGearPieceText);
        legendaryGearPieceBuy.setText(legendaryGearPieceBuyText);
        legendaryGearPieceCraft.setText(legendaryGearPieceCraftText);
    }

    public void increaseGearPieceLevel(View view) {
        if (gearPiece.getLevel() < player.getLevel()) {
            gearPiece.levelUp();

            commonGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Common\n" + gearPieceType;
            commonGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost()) + " Gold";
            commonGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost()) + " Ores";
            uncommonGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Uncommon\n" + gearPieceType;
            uncommonGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 2) + " Gold";
            uncommonGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 2) + " Ores";
            rareGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Rare\n" + gearPieceType;
            rareGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 3) + " Gold";
            rareGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 3) + " Ores";
            epicGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Epic\n" + gearPieceType;
            epicGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 4) + " Gold";
            epicGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 4) + " Ores";
            legendaryGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Legendary\n" + gearPieceType;
            legendaryGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 5) + " Gold";
            legendaryGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 5) + " Ores";

            commonGearPiece.setText(commonGearPieceText);
            commonGearPieceBuy.setText(commonGearPieceBuyText);
            commonGearPieceCraft.setText(commonGearPieceCraftText);
            uncommonGearPiece.setText(uncommonGearPieceText);
            uncommonGearPieceBuy.setText(uncommonGearPieceBuyText);
            uncommonGearPieceCraft.setText(uncommonGearPieceCraftText);
            rareGearPiece.setText(rareGearPieceText);
            rareGearPieceBuy.setText(rareGearPieceBuyText);
            rareGearPieceCraft.setText(rareGearPieceCraftText);
            epicGearPiece.setText(epicGearPieceText);
            epicGearPieceBuy.setText(epicGearPieceBuyText);
            epicGearPieceCraft.setText(epicGearPieceCraftText);
            legendaryGearPiece.setText(legendaryGearPieceText);
            legendaryGearPieceBuy.setText(legendaryGearPieceBuyText);
            legendaryGearPieceCraft.setText(legendaryGearPieceCraftText);

            preferences.edit().putFloat("gearPieceLevel", gearPiece.getLevel()).apply();
            preferences.edit().putFloat("gearPieceHealth", gearPiece.getHealth()).apply();
            preferences.edit().putFloat("gearPieceDefense", gearPiece.getDefense()).apply();
            preferences.edit().putFloat("gearPieceDamage", gearPiece.getDamage()).apply();
            preferences.edit().putFloat("gearPieceGoldCost", gearPiece.getGoldCost()).apply();
            preferences.edit().putFloat("gearPieceGoldWorth", gearPiece.getGoldWorth()).apply();
            preferences.edit().putFloat("gearPieceOreCost", gearPiece.getOreCost()).apply();
        }
    }

    public void decreaseGearPieceLevel(View view) {
        if (gearPiece.getLevel() > 1) {
            gearPiece.levelDown();

            commonGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Common\n" + gearPieceType;
            commonGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost()) + " Gold";
            commonGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost()) + " Ores";
            uncommonGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Uncommon\n" + gearPieceType;
            uncommonGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 2) + " Gold";
            uncommonGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 2) + " Ores";
            rareGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Rare\n" + gearPieceType;
            rareGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 3) + " Gold";
            rareGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 3) + " Ores";
            epicGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Epic\n" + gearPieceType;
            epicGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 4) + " Gold";
            epicGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 4) + " Ores";
            legendaryGearPieceText = "Level " + Math.round(gearPiece.getLevel()) + " Legendary\n" + gearPieceType;
            legendaryGearPieceBuyText = "Buy for\n" + Math.round(gearPiece.getGoldCost() * 5) + " Gold";
            legendaryGearPieceCraftText = "Craft for\n" + Math.round(gearPiece.getOreCost() * 5) + " Ores";

            commonGearPiece.setText(commonGearPieceText);
            commonGearPieceBuy.setText(commonGearPieceBuyText);
            commonGearPieceCraft.setText(commonGearPieceCraftText);
            uncommonGearPiece.setText(uncommonGearPieceText);
            uncommonGearPieceBuy.setText(uncommonGearPieceBuyText);
            uncommonGearPieceCraft.setText(uncommonGearPieceCraftText);
            rareGearPiece.setText(rareGearPieceText);
            rareGearPieceBuy.setText(rareGearPieceBuyText);
            rareGearPieceCraft.setText(rareGearPieceCraftText);
            epicGearPiece.setText(epicGearPieceText);
            epicGearPieceBuy.setText(epicGearPieceBuyText);
            epicGearPieceCraft.setText(epicGearPieceCraftText);
            legendaryGearPiece.setText(legendaryGearPieceText);
            legendaryGearPieceBuy.setText(legendaryGearPieceBuyText);
            legendaryGearPieceCraft.setText(legendaryGearPieceCraftText);

            preferences.edit().putFloat("gearPieceLevel", gearPiece.getLevel()).apply();
            preferences.edit().putFloat("gearPieceHealth", gearPiece.getHealth()).apply();
            preferences.edit().putFloat("gearPieceDefense", gearPiece.getDefense()).apply();
            preferences.edit().putFloat("gearPieceDamage", gearPiece.getDamage()).apply();
            preferences.edit().putFloat("gearPieceGoldCost", gearPiece.getGoldCost()).apply();
            preferences.edit().putFloat("gearPieceGoldWorth", gearPiece.getGoldWorth()).apply();
            preferences.edit().putFloat("gearPieceOreCost", gearPiece.getOreCost()).apply();
        }
    }

    public void craftCommonGearPiece(View view) {
        if (Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost())) {
            if (gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                    gearPiece.getIsEquiped(),
                    gearPieceType,
                    gearPiece.getRarity(),
                    gearPiece.getStats(),
                    gearPiece.getLevel(),
                    0,
                    0,
                    gearPiece.getDamage() * 2,
                    gearPiece.getBlockChance(),
                    gearPiece.getCritChance(),
                    gearPiece.getGoldCost(),
                    gearPiece.getGoldWorth(),
                    gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if(gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 2,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth(),
                        gearPiece.getDefense(),
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseOres(Math.round(theGearPiece.getOreCost()));

            oresText = " Ores: " + Math.round(player.getOres());
            ores.setText(oresText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "common",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("ores", player.getOres()).apply();
        }
    }

    public void buyCommonGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost())) {
            if(gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 2,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if (gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 2,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth(),
                        gearPiece.getDefense(),
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseGold(Math.round(gearPiece.getGoldCost()));

            goldText = " Gold: " + Math.round(player.getGold());
            gold.setText(goldText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "common",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("gold", player.getGold()).apply();
        }
    }

    public void craftUncommonGearPiece(View view) {
        if (Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 2)) {
            if (gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 4,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if (gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 4,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 2,
                        gearPiece.getDefense() * 2,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseOres(Math.round(gearPiece.getOreCost() * 2));

            oresText = " Ores: " + Math.round(player.getOres());
            ores.setText(oresText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "uncommon",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("ores", player.getOres()).apply();
        }
    }

    public void buyUncommonGearPiece(View view) {
        if (Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 2)) {
            if (gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 4,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if (gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 4,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 2,
                        gearPiece.getDefense() * 2,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseGold(Math.round(gearPiece.getGoldCost() * 2));

            goldText = " Gold: " + Math.round(player.getGold());
            gold.setText(goldText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "uncommon",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("gold", player.getGold()).apply();
        }
    }

    public void craftRareGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 3)) {
            if(gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 6,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 3,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if(gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 6,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 3,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 3,
                        gearPiece.getDefense() * 3,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 3,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseOres(Math.round(gearPiece.getOreCost() * 3));

            oresText = " Ores: " + Math.round(player.getOres());
            ores.setText(oresText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "rare",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("ores", player.getOres()).apply();
        }
    }

    public void buyRareGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 3)) {
            if(gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 6,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 3,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if(gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 6,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 3,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 3,
                        gearPiece.getDefense() * 3,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 3,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseGold(Math.round(gearPiece.getGoldCost() * 3));

            goldText = " Gold: " + Math.round(player.getGold());
            gold.setText(goldText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "rare",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("gold", player.getGold()).apply();
        }
    }

    public void craftEpicGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 4)) {
            if(gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 8,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 4,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if(gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 8,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 4,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 4,
                        gearPiece.getDefense() * 4,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 4,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseOres(Math.round(gearPiece.getOreCost() * 4));

            oresText = " Ores: " + Math.round(player.getOres());
            ores.setText(oresText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "epic",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());

            preferences.edit().putFloat("ores", player.getOres()).apply();
        }
    }

    public void buyEpicGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 4)) {
            if(gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 8,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 4,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if(gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 8,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 4,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 4,
                        gearPiece.getDefense() * 4,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 4,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseGold(Math.round(gearPiece.getGoldCost() * 4));

            goldText = " Gold: " + Math.round(player.getGold());
            gold.setText(goldText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "epic",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("gold", player.getGold()).apply();
        }
    }

    public void craftLegendaryGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 5)) {
            if(gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 10,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 5,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if(gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 10,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 5,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 5,
                        gearPiece.getDefense() * 5,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 5,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseOres(Math.round(gearPiece.getOreCost() * 5));

            oresText = " Ores: " + Math.round(player.getOres());
            ores.setText(oresText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "legendary",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("ores", player.getOres()).apply();
        }
    }

    public void buyLegendaryGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 5)) {
            if(gearPieceType.equals("MainHand Sword")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        0,
                        gearPiece.getDamage() * 10,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 5,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else if(gearPieceType.equals("OffHand Shield")) {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        0,
                        gearPiece.getDefense() * 10,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 5,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 5,
                        gearPiece.getDefense() * 5,
                        0,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 5,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));
            }
            player.decreaseGold(Math.round(theGearPiece.getGoldCost() * 5));

            goldText = " Gold: " + Math.round(player.getGold());
            gold.setText(goldText);

            db.insertData(
                    theGearPiece.getIsEquiped(),
                    theGearPiece.getName(),
                    "legendary",
                    theGearPiece.getStats(),
                    theGearPiece.getLevel(),
                    theGearPiece.getHealth(),
                    theGearPiece.getDefense(),
                    theGearPiece.getDamage(),
                    theGearPiece.getBlockChance(),
                    theGearPiece.getCritChance(),
                    theGearPiece.getGoldWorth());
            preferences.edit().putFloat("gold", player.getGold()).apply();
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
        intent = new Intent(this, DefensiveGear.class);
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