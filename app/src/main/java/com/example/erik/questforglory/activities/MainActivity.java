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

public class MainActivity extends AppCompatActivity {
    
    DatabaseHelper db;
    SharedPreferences preferences;
    TextView newGame;
    TextView start;
    TextView areYouSureGrid;
    TextView areYouSure;
    TextView areYouSureOkay;
    TextView areYouSureCancel;
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
    boolean isCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        bundle = new Bundle();

        newGame = findViewById(R.id.newGame);
        start = findViewById(R.id.start);
        areYouSureGrid = findViewById(R.id.areYouSureGrid);
        areYouSure = findViewById(R.id.areYouSure);
        areYouSureOkay = findViewById(R.id.areYouSureOkay);
        areYouSureCancel = findViewById(R.id.areYouSureCancel);

        preferences = getSharedPreferences("Data", 0);
    }

    public void newGame(View view) {
        if (preferences.getBoolean("isCreated", false)) {
            newGame.setVisibility(View.INVISIBLE);
            start.setVisibility(View.INVISIBLE);
            areYouSureGrid.setVisibility(View.VISIBLE);
            areYouSure.setVisibility(View.VISIBLE);
            areYouSureOkay.setVisibility(View.VISIBLE);
            areYouSureCancel.setVisibility(View.VISIBLE);
        } else {
            initNewGame();
        }
    }

    public void start(View view) {
        if (preferences.getBoolean("isCreated", false)) {
            player = new Player(
                    preferences.getBoolean("alive", false),
                    preferences.getFloat("requiredLevelToAscend", 0),
                    preferences.getFloat("level", 0),
                    preferences.getFloat("XP", 0),
                    preferences.getFloat("XPToNextLevel", 0),
                    preferences.getFloat("glory", 0),
                    preferences.getFloat("skillPoints", 0),
                    preferences.getFloat("maxHealth", 0),
                    preferences.getFloat("health", 0),
                    preferences.getFloat("defense", 0),
                    preferences.getFloat("damage", 0),
                    preferences.getFloat("blockChance", 0),
                    preferences.getFloat("critChance", 0),
                    preferences.getFloat("maxHealthGearBonus", 0),
                    preferences.getFloat("defenseGearBonus", 0),
                    preferences.getFloat("damageGearBonus", 0),
                    preferences.getFloat("baseHealth", 0),
                    preferences.getFloat("baseDefense", 0),
                    preferences.getFloat("baseDamage", 0),
                    preferences.getFloat("gold", 0),
                    preferences.getFloat("herbs", 0),
                    preferences.getFloat("ores", 0),
                    preferences.getFloat("soulDust", 0),
                    preferences.getFloat("bonusBaseHealth", 0),
                    preferences.getFloat("bonusBaseDefense", 0),
                    preferences.getFloat("bonusBaseDamage", 0),
                    preferences.getFloat("bonusXPYield", 0),
                    preferences.getFloat("bonusGoldYield", 0),
                    preferences.getFloat("bonusHerbYield", 0),
                    preferences.getFloat("bonusOreYield", 0),
                    preferences.getFloat("bonusSoulDustYield", 0));
            spriggan = new Monster(
                    true,
                    preferences.getFloat("sprigganMaxLevel", 0),
                    preferences.getFloat("sprigganLevel", 0),
                    preferences.getFloat("sprigganXPYield", 0),
                    preferences.getFloat("sprigganMaxHealth", 0),
                    preferences.getFloat("sprigganHealth", 0),
                    preferences.getFloat("sprigganDamage", 0),
                    preferences.getFloat("sprigganGoldYield", 0),
                    preferences.getFloat("sprigganHerbYield", 0),
                    0);
            golem = new Monster(
                    true,
                    preferences.getFloat("golemMaxLevel", 0),
                    preferences.getFloat("golemLevel", 0),
                    preferences.getFloat("golemXPYield", 0),
                    preferences.getFloat("golemMaxHealth", 0),
                    preferences.getFloat("golemHealth", 0),
                    preferences.getFloat("golemDamage", 0),
                    preferences.getFloat("golemGoldYield", 0),
                    0,
                    preferences.getFloat("golemOreYield", 0));
            healthPotion = new Potion(
                    preferences.getFloat("healthPotionLevel", 0),
                    preferences.getFloat("healthPotionAmount", 0),
                    preferences.getFloat("healthPotionUpgradeCost", 0),
                    preferences.getFloat("healthPotionGoldCost", 0),
                    preferences.getFloat("healthPotionGoldWorth", 0),
                    preferences.getFloat("healthPotionHerbsCost", 0),
                    preferences.getFloat("healthPotionEffect", 0),
                    1,
                    1,
                    0,
                    0,
                    false);
            damagePotion = new Potion(
                    preferences.getFloat("damagePotionLevel", 0),
                    preferences.getFloat("damagePotionAmount", 0),
                    preferences.getFloat("damagePotionUpgradeCost", 0),
                    preferences.getFloat("damagePotionGoldCost", 0),
                    preferences.getFloat("damagePotionGoldWorth", 0),
                    preferences.getFloat("damagePotionHerbsCost", 0),
                    preferences.getFloat("damagePotionEffect", 0),
                    5,
                    preferences.getFloat("damagePotionCurrentDuration", 0),
                    0,
                    preferences.getFloat("damagePotionCurrentCooldown", 0),
                    preferences.getBoolean("damagePotionIsOnDurationCooldown", false));
            strike = new Skill(
                    preferences.getFloat("strikeLevel", 0),
                    preferences.getFloat("strikeDamage", 0),
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    false);
            charge = new Skill(
                    preferences.getFloat("chargeLevel", 0),
                    preferences.getFloat("chargeDamage", 0),
                    0,
                    0,
                    0,
                    preferences.getFloat("chargeDuration", 0),
                    preferences.getFloat("chargeCurrentDuration", 0),
                    preferences.getFloat("chargeCooldown", 0),
                    preferences.getFloat("chargeCurrentCooldown", 0),
                    preferences.getBoolean("chargeIsOnDurationCooldown",
                            false));
            mend = new Skill(
                    preferences.getFloat("mendLevel", 0),
                    0,
                    0,
                    preferences.getFloat("mendHealing", 0),
                    0,
                    preferences.getFloat("mendDuration", 0),
                    preferences.getFloat("mendCurrentDuration", 0),
                    preferences.getFloat("mendCooldown", 0),
                    preferences.getFloat("mendCurrentCooldown", 0),
                    preferences.getBoolean("mendIsOnDurationCooldown",
                            false));
            defenseUp = new Skill(
                    preferences.getFloat("defenseUpLevel", 0),
                    0,
                    0,
                    0,
                    preferences.getFloat("defenseUpDefense", 0),
                    preferences.getFloat("defenseUpDuration", 0),
                    preferences.getFloat("defenseUpCurrentDuration", 0),
                    preferences.getFloat("defenseUpCooldown", 0),
                    preferences.getFloat("defenseUpCurrentCooldown", 0),
                    preferences.getBoolean("defenseUpIsOnDurationCooldown",
                            false));
            gearPiece = new GearPiece(
                    0,
                    "gear piece",
                    "common",
                    "stats",
                    preferences.getFloat("gearPieceLevel", 0),
                    preferences.getFloat("gearPieceHealth", 0),
                    preferences.getFloat("gearPieceDefense", 0),
                    preferences.getFloat("gearPieceDamage", 0),
                    0,
                    0,
                    preferences.getFloat("gearPieceGoldCost", 0),
                    preferences.getFloat("gearPieceGoldWorth", 0),
                    preferences.getFloat("gearPieceOreCost", 0));

            intent = new Intent(this, Quests.class);
            saveObjectsInBundle();
        } else {
            initNewGame();
        }
    }

    public void okay(View view) {
        initNewGame();
    }

    public void initNewGame() {
        db.deleteAllData();

        isCreated = true;

        player = new Player(true, 100,  1, 0, 200, 0, 0, 500, 500, 10, 50, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        spriggan = new Monster(true, 1, 1, 40, 750, 750, 50/1.5f, 30, 10, 0);
        golem = new Monster(true, 1, 1, 40, 500, 500, 50, 30, 0, 10);
        healthPotion = new Potion(1, 0, 100, 40, 30, 10, 250, 1, 1, 0, 0, false);
        damagePotion = new Potion(1, 0, 100, 40, 30, 10, 50, 5, 5, 0, 0, false);
        strike = new Skill(1, 100, 0, 0, 0, 0, 0, 0, 0, false);
        charge = new Skill(1, 50, 1, 0, 0, 1, 1, 5, 5, false);
        mend = new Skill(1, 0, 0, 5, 0, 3, 3, 4, 4, false);
        defenseUp = new Skill(1, 0, 0, 0, 20, 1, 1, 3, 3, false);
        gearPiece = new GearPiece(0, "gear piece", "rarity", "stats", 1, 50, 1, 5, 0, 0, 160, 120, 40);

        preferences.edit().putBoolean("isCreated", isCreated).apply();
        preferences.edit().putBoolean("alive", player.isAlive()).apply();
        preferences.edit().putFloat("requiredLevelToAscend", player.getRequiredLevelToAscend()).apply();
        preferences.edit().putFloat("level", player.getLevel()).apply();
        preferences.edit().putFloat("XP", player.getXP()).apply();
        preferences.edit().putFloat("XPToNextLevel", player.getXPToNextLevel()).apply();
        preferences.edit().putFloat("skillPoints", player.getSkillPoints()).apply();
        preferences.edit().putFloat("glory", player.getGlory()).apply();
        preferences.edit().putFloat("maxHealth", player.getMaxHealth()).apply();
        preferences.edit().putFloat("baseHealth", player.getBaseHealth()).apply();
        preferences.edit().putFloat("baseDefense", player.getBaseDefense()).apply();
        preferences.edit().putFloat("baseDamage", player.getBaseDamage()).apply();
        preferences.edit().putFloat("health", player.getHealth()).apply();
        preferences.edit().putFloat("defense", player.getDefense()).apply();
        preferences.edit().putFloat("damage", player.getDamage()).apply();
        preferences.edit().putFloat("blockChance", player.getBlockChance()).apply();
        preferences.edit().putFloat("critChance", player.getCritChance()).apply();
        preferences.edit().putFloat("maxHealthGearBonus", player.getMaxHealthGearBonus()).apply();
        preferences.edit().putFloat("defenseGearBonus", player.getDefenseGearBonus()).apply();
        preferences.edit().putFloat("damageGearBonus", player.getDamageGearBonus()).apply();

        preferences.edit().putFloat("gold", player.getGold()).apply();
        preferences.edit().putFloat("herbs", player.getHerbs()).apply();
        preferences.edit().putFloat("ores", player.getOres()).apply();

        preferences.edit().putFloat("bonusBaseHealth", player.getBonusBaseHealth()).apply();
        preferences.edit().putFloat("bonusBaseDefense", player.getBonusBaseDefense()).apply();
        preferences.edit().putFloat("bonusBaseDamage", player.getBonusBaseDamage()).apply();
        preferences.edit().putFloat("bonusXPYield", player.getBonusXPYield()).apply();
        preferences.edit().putFloat("bonusGoldYield", player.getBonusGoldYield()).apply();
        preferences.edit().putFloat("bonusHerbYield", player.getBonusHerbYield()).apply();
        preferences.edit().putFloat("bonusOreYield", player.getBonusOreYield()).apply();

        preferences.edit().putFloat("sprigganMaxLevel", spriggan.getMaxLevel()).apply();
        preferences.edit().putFloat("sprigganLevel", spriggan.getLevel()).apply();
        preferences.edit().putFloat("sprigganXPYield", spriggan.getXPYield()).apply();
        preferences.edit().putFloat("sprigganMaxHealth", spriggan.getMaxHealth()).apply();
        preferences.edit().putFloat("sprigganHealth", spriggan.getHealth()).apply();
        preferences.edit().putFloat("sprigganDamage", spriggan.getDamage()).apply();
        preferences.edit().putFloat("sprigganGoldYield", spriggan.getGoldYield()).apply();
        preferences.edit().putFloat("sprigganHerbYield", spriggan.getHerbYield()).apply();

        preferences.edit().putFloat("golemMaxLevel", golem.getMaxLevel()).apply();
        preferences.edit().putFloat("golemLevel", golem.getLevel()).apply();
        preferences.edit().putFloat("golemXPYield", golem.getXPYield()).apply();
        preferences.edit().putFloat("golemMaxHealth", golem.getMaxHealth()).apply();
        preferences.edit().putFloat("golemHealth", golem.getHealth()).apply();
        preferences.edit().putFloat("golemDamage", golem.getDamage()).apply();
        preferences.edit().putFloat("golemGoldYield", golem.getGoldYield()).apply();
        preferences.edit().putFloat("golemOreYield", golem.getOreYield()).apply();

        preferences.edit().putFloat("healthPotionLevel", healthPotion.getLevel()).apply();
        preferences.edit().putFloat("healthPotionAmount", healthPotion.getAmount()).apply();
        preferences.edit().putFloat("healthPotionUpgradeCost", healthPotion.getUpgradeCost()).apply();
        preferences.edit().putFloat("healthPotionGoldCost", healthPotion.getGoldCost()).apply();
        preferences.edit().putFloat("healthPotionGoldWorth", healthPotion.getGoldWorth()).apply();
        preferences.edit().putFloat("healthPotionHerbsCost", healthPotion.getHerbCost()).apply();
        preferences.edit().putFloat("healthPotionEffect", healthPotion.getEffect()).apply();

        preferences.edit().putFloat("damagePotionLevel", damagePotion.getLevel()).apply();
        preferences.edit().putFloat("damagePotionAmount", damagePotion.getAmount()).apply();
        preferences.edit().putFloat("damagePotionUpgradeCost", damagePotion.getUpgradeCost()).apply();
        preferences.edit().putFloat("damagePotionGoldCost", damagePotion.getGoldCost()).apply();
        preferences.edit().putFloat("damagePotionGoldWorth", damagePotion.getGoldWorth()).apply();
        preferences.edit().putFloat("damagePotionHerbsCost", damagePotion.getHerbCost()).apply();
        preferences.edit().putFloat("damagePotionEffect", damagePotion.getEffect()).apply();
        preferences.edit().putFloat("damagePotionDuration", damagePotion.getDuration()).apply();
        preferences.edit().putFloat("damagePotionCooldown", damagePotion.getCooldown()).apply();
        preferences.edit().putBoolean("damagePotionIsOnDurationCooldown", damagePotion.isOnDurationCooldown()).apply();

        preferences.edit().putFloat("strikeLevel", strike.getLevel()).apply();
        preferences.edit().putFloat("strikeDamage", strike.getDamage()).apply();

        preferences.edit().putFloat("chargeLevel", charge.getLevel()).apply();
        preferences.edit().putFloat("chargeDamage", charge.getDamage()).apply();
        preferences.edit().putFloat("chargeDuration", charge.getDuration()).apply();
        preferences.edit().putFloat("chargeCooldown", charge.getCooldown()).apply();
        preferences.edit().putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();

        preferences.edit().putFloat("mendLevel", mend.getLevel()).apply();
        preferences.edit().putFloat("mendHealing", mend.getHealing()).apply();
        preferences.edit().putFloat("mendDuration", mend.getDuration()).apply();
        preferences.edit().putFloat("mendCooldown", mend.getCooldown()).apply();
        preferences.edit().putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();

        preferences.edit().putFloat("defenseUpLevel", defenseUp.getLevel()).apply();
        preferences.edit().putFloat("defenseUpDefense", defenseUp.getDefense()).apply();
        preferences.edit().putFloat("defenseUpDuration", defenseUp.getDuration()).apply();
        preferences.edit().putFloat("defenseUpCooldown", defenseUp.getCooldown()).apply();
        preferences.edit().putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();

        preferences.edit().putFloat("gearPieceLevel", gearPiece.getLevel()).apply();
        preferences.edit().putFloat("gearPieceHealth", gearPiece.getHealth()).apply();
        preferences.edit().putFloat("gearPieceDefense", gearPiece.getDefense()).apply();
        preferences.edit().putFloat("gearPieceDamage", gearPiece.getDamage()).apply();
        preferences.edit().putFloat("gearPieceGoldCost", gearPiece.getGoldCost()).apply();
        preferences.edit().putFloat("gearPieceGoldWorth", gearPiece.getGoldWorth()).apply();
        preferences.edit().putFloat("gearPieceOreCost", gearPiece.getOreCost()).apply();

        preferences.edit().putFloat("monstersRequiredLevel", 2).apply();

        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
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
        finishAffinity();
    }

    public void cancel(View view) {
        newGame.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
        areYouSureGrid.setVisibility(View.INVISIBLE);
        areYouSure.setVisibility(View.INVISIBLE);
        areYouSureOkay.setVisibility(View.INVISIBLE);
        areYouSureCancel.setVisibility(View.INVISIBLE);
    }

    public void settings(View view) {
        intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void quit(View view) {
        finishAffinity();
    }
}