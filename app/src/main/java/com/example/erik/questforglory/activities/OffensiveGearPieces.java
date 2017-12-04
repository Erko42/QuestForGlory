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
import com.example.erik.questforglory.helpers.DatabaseHelper;
import com.example.erik.questforglory.R;

public class OffensiveGearPieces extends AppCompatActivity {
    DatabaseHelper db;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
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
        setContentView(R.layout.activity_offensive_gear_pieces);

        db = new DatabaseHelper(this);

        preferences = getSharedPreferences("Data", 0);
        editor = preferences.edit();

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

        level = (TextView) findViewById(R.id.level);
        XP = (TextView) findViewById(R.id.XP);
        ores = (TextView) findViewById(R.id.ores);
        gold = (TextView) findViewById(R.id.gold);
        gearPieces = (TextView) findViewById(R.id.gearPieces);
        commonGearPiece = (TextView) findViewById(R.id.commonGearPiece);
        commonGearPieceBuy = (TextView) findViewById(R.id.commonGearPieceBuy);
        commonGearPieceCraft = (TextView) findViewById(R.id.commonGearPieceCraft);
        uncommonGearPiece = (TextView) findViewById(R.id.uncommonGearPiece);
        uncommonGearPieceBuy = (TextView) findViewById(R.id.uncommonGearPieceBuy);
        uncommonGearPieceCraft = (TextView) findViewById(R.id.uncommonGearPieceCraft);
        rareGearPiece = (TextView) findViewById(R.id.rareGearPiece);
        rareGearPieceBuy = (TextView) findViewById(R.id.rareGearPieceBuy);
        rareGearPieceCraft = (TextView) findViewById(R.id.rareGearPieceCraft);
        epicGearPiece = (TextView) findViewById(R.id.epicGearPiece);
        epicGearPieceBuy = (TextView) findViewById(R.id.epicGearPieceBuy);
        epicGearPieceCraft = (TextView) findViewById(R.id.epicGearPieceCraft);
        legendaryGearPiece = (TextView) findViewById(R.id.legendaryGearPiece);
        legendaryGearPieceBuy = (TextView) findViewById(R.id.legendaryGearPieceBuy);
        legendaryGearPieceCraft = (TextView) findViewById(R.id.legendaryGearPieceCraft);

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

        if(gearPieceType.equals("Helm")) {
            gearPiecesText = "Helms";
        }
        else if(gearPieceType.equals("Pauldrons")) {
            gearPiecesText = "Pauldrons";
        }
        else if(gearPieceType.equals("Chestplate")) {
            gearPiecesText = "Chestplates";
        }
        else if(gearPieceType.equals("Bracers")) {
            gearPiecesText = "Bracers";
        }
        else if(gearPieceType.equals("MainHand Sword")) {
            gearPiecesText = "MainHand Swords";
        }
        else if(gearPieceType.equals("OffHand Sword")) {
            gearPiecesText = "OffHand Swords";
        }
        else if(gearPieceType.equals("Gauntlets")) {
            gearPiecesText = "Gauntlets";
        }
        else if(gearPieceType.equals("Belt")) {
            gearPiecesText = "Belts";
        }
        else if(gearPieceType.equals("Legplates")) {
            gearPiecesText = "Legplates";
        }
        else if(gearPieceType.equals("Sabatons")) {
            gearPiecesText = "Sabatons";
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
        if(gearPiece.getLevel() < player.getLevel()) {
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

            editor.putFloat("gearPieceLevel", gearPiece.getLevel()).apply();
            editor.putFloat("gearPieceHealth", gearPiece.getHealth()).apply();
            editor.putFloat("gearPieceDefense", gearPiece.getDefense()).apply();
            editor.putFloat("gearPieceDamage", gearPiece.getDamage()).apply();
            editor.putFloat("gearPieceGoldCost", gearPiece.getGoldCost()).apply();
            editor.putFloat("gearPieceGoldWorth", gearPiece.getGoldWorth()).apply();
            editor.putFloat("gearPieceOreCost", gearPiece.getOreCost()).apply();
        }
    }
    public void decreaseGearPieceLevel(View view) {
        if(gearPiece.getLevel() > 1) {
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

            editor.putFloat("gearPieceLevel", gearPiece.getLevel()).apply();
            editor.putFloat("gearPieceHealth", gearPiece.getHealth()).apply();
            editor.putFloat("gearPieceDefense", gearPiece.getDefense()).apply();
            editor.putFloat("gearPieceDamage", gearPiece.getDamage()).apply();
            editor.putFloat("gearPieceGoldCost", gearPiece.getGoldCost()).apply();
            editor.putFloat("gearPieceGoldWorth", gearPiece.getGoldWorth()).apply();
            editor.putFloat("gearPieceOreCost", gearPiece.getOreCost()).apply();
        }
    }
    public void craftCommonGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost())) {
            if(gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth(),
                        0,
                        gearPiece.getDamage(),
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("ores", player.getOres()).apply();
        }
    }
    public void buyCommonGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost())) {
            if(gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth(),
                        0,
                        gearPiece.getDamage(),
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("gold", player.getGold()).apply();
        }
    }
    public void craftUncommonGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 2)) {
            if(gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 2,
                        0,
                        gearPiece.getDamage() * 2,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("ores", player.getOres()).apply();
        }
    }
    public void buyUncommonGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 2)) {
            if (gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 2,
                        0,
                        gearPiece.getDamage() * 2,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth() * 2,
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("gold", player.getGold()).apply();
        }
    }
    public void craftRareGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 3)) {
            if (gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 3,
                        0,
                        gearPiece.getDamage() * 3,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("ores", player.getOres()).apply();
        }
    }
    public void buyRareGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 3)) {
            if (gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 3,
                        0,
                        gearPiece.getDamage() * 3,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("gold", player.getGold()).apply();
        }
    }
    public void craftEpicGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 4)) {
            if (gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 4,
                        0,
                        gearPiece.getDamage() * 4,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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

            editor.putFloat("ores", player.getOres()).apply();
        }
    }
    public void buyEpicGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 4)) {
            if (gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 4,
                        0,
                        gearPiece.getDamage() * 4,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("gold", player.getGold()).apply();
        }
    }
    public void craftLegendaryGearPiece(View view) {
        if(Math.round(player.getOres()) >= Math.round(gearPiece.getOreCost() * 5)) {
            if (gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 5,
                        0,
                        gearPiece.getDamage() * 5,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("ores", player.getOres()).apply();
        }
    }
    public void buyLegendaryGearPiece(View view) {
        if(Math.round(player.getGold()) >= Math.round(gearPiece.getGoldCost() * 5)) {
            if (gearPieceType.equals("MainHand Sword") || gearPieceType.equals("OffHand Sword")) {
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
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
            } else {
                theGearPiece = new GearPiece(
                        gearPiece.getIsEquiped(),
                        gearPieceType,
                        gearPiece.getRarity(),
                        gearPiece.getStats(),
                        gearPiece.getLevel(),
                        gearPiece.getHealth() * 5,
                        0,
                        gearPiece.getDamage() * 5,
                        gearPiece.getBlockChance(),
                        gearPiece.getCritChance(),
                        gearPiece.getGoldCost(),
                        gearPiece.getGoldWorth(),
                        gearPiece.getOreCost());
                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));
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
            editor.putFloat("gold", player.getGold()).apply();
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
        intent = new Intent(this, OffensiveGear.class);
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