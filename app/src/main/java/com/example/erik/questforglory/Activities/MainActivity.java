package com.example.erik.questforglory.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.Classes.GearPiece;
import com.example.erik.questforglory.Classes.Monster;
import com.example.erik.questforglory.Classes.Player;
import com.example.erik.questforglory.Classes.Potion;
import com.example.erik.questforglory.Classes.Skill;
import com.example.erik.questforglory.Helpers.DatabaseHelper;
import com.example.erik.questforglory.R;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
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

        newGame = (TextView) findViewById(R.id.newGame);
        start = (TextView) findViewById(R.id.start);
        areYouSureGrid = (TextView) findViewById(R.id.areYouSureGrid);
        areYouSure = (TextView) findViewById(R.id.areYouSure);
        areYouSureOkay = (TextView) findViewById(R.id.areYouSureOkay);
        areYouSureCancel = (TextView) findViewById(R.id.areYouSureCancel);

        preferences = getSharedPreferences("Data", 0);
        editor = preferences.edit();
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
        if(preferences.getBoolean("isCreated", false)) {
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
    public void okay(View view)
    {
        initNewGame();
    }
    public void initNewGame() {
        isCreated = true;

        db.deleteAllData();

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

        editor.putBoolean("isCreated", isCreated).apply();
        editor.putBoolean("alive", player.isAlive()).apply();
        editor.putFloat("requiredLevelToAscend", player.getRequiredLevelToAscend()).apply();
        editor.putFloat("level", player.getLevel()).apply();
        editor.putFloat("XP", player.getXP()).apply();
        editor.putFloat("XPToNextLevel", player.getXPToNextLevel()).apply();
        editor.putFloat("skillPoints", player.getSkillPoints()).apply();
        editor.putFloat("glory", player.getGlory()).apply();
        editor.putFloat("maxHealth", player.getMaxHealth()).apply();
        editor.putFloat("baseHealth", player.getBaseHealth()).apply();
        editor.putFloat("baseDefense", player.getBaseDefense()).apply();
        editor.putFloat("baseDamage", player.getBaseDamage()).apply();
        editor.putFloat("health", player.getHealth()).apply();
        editor.putFloat("defense", player.getDefense()).apply();
        editor.putFloat("damage", player.getDamage()).apply();
        editor.putFloat("blockChance", player.getBlockChance()).apply();
        editor.putFloat("critChance", player.getCritChance()).apply();
        editor.putFloat("maxHealthGearBonus", player.getMaxHealthGearBonus()).apply();
        editor.putFloat("defenseGearBonus", player.getDefenseGearBonus()).apply();
        editor.putFloat("damageGearBonus", player.getDamageGearBonus()).apply();

        editor.putFloat("gold", player.getGold()).apply();
        editor.putFloat("herbs", player.getHerbs()).apply();
        editor.putFloat("ores", player.getOres()).apply();

        editor.putFloat("bonusBaseHealth", player.getBonusBaseHealth()).apply();
        editor.putFloat("bonusBaseDefense", player.getBonusBaseDefense()).apply();
        editor.putFloat("bonusBaseDamage", player.getBonusBaseDamage()).apply();
        editor.putFloat("bonusXPYield", player.getBonusXPYield()).apply();
        editor.putFloat("bonusGoldYield", player.getBonusGoldYield()).apply();
        editor.putFloat("bonusHerbYield", player.getBonusHerbYield()).apply();
        editor.putFloat("bonusOreYield", player.getBonusOreYield()).apply();

        editor.putFloat("sprigganMaxLevel", spriggan.getMaxLevel()).apply();
        editor.putFloat("sprigganLevel", spriggan.getLevel()).apply();
        editor.putFloat("sprigganXPYield", spriggan.getXPYield()).apply();
        editor.putFloat("sprigganMaxHealth", spriggan.getMaxHealth()).apply();
        editor.putFloat("sprigganHealth", spriggan.getHealth()).apply();
        editor.putFloat("sprigganDamage", spriggan.getDamage()).apply();
        editor.putFloat("sprigganGoldYield", spriggan.getGoldYield()).apply();
        editor.putFloat("sprigganHerbYield", spriggan.getHerbYield()).apply();

        editor.putFloat("golemMaxLevel", golem.getMaxLevel()).apply();
        editor.putFloat("golemLevel", golem.getLevel()).apply();
        editor.putFloat("golemXPYield", golem.getXPYield()).apply();
        editor.putFloat("golemMaxHealth", golem.getMaxHealth()).apply();
        editor.putFloat("golemHealth", golem.getHealth()).apply();
        editor.putFloat("golemDamage", golem.getDamage()).apply();
        editor.putFloat("golemGoldYield", golem.getGoldYield()).apply();
        editor.putFloat("golemOreYield", golem.getOreYield()).apply();

        editor.putFloat("healthPotionLevel", healthPotion.getLevel()).apply();
        editor.putFloat("healthPotionAmount", healthPotion.getAmount());
        editor.putFloat("healthPotionUpgradeCost", healthPotion.getUpgradeCost()).apply();
        editor.putFloat("healthPotionGoldCost", healthPotion.getGoldCost()).apply();
        editor.putFloat("healthPotionGoldWorth", healthPotion.getGoldWorth()).apply();
        editor.putFloat("healthPotionHerbsCost", healthPotion.getHerbCost()).apply();
        editor.putFloat("healthPotionEffect", healthPotion.getEffect()).apply();

        editor.putFloat("damagePotionLevel", damagePotion.getLevel()).apply();
        editor.putFloat("damagePotionAmount", damagePotion.getAmount());
        editor.putFloat("damagePotionUpgradeCost", damagePotion.getUpgradeCost()).apply();
        editor.putFloat("damagePotionGoldCost", damagePotion.getGoldCost()).apply();
        editor.putFloat("damagePotionGoldWorth", damagePotion.getGoldWorth()).apply();
        editor.putFloat("damagePotionHerbsCost", damagePotion.getHerbCost()).apply();
        editor.putFloat("damagePotionEffect", damagePotion.getEffect()).apply();
        editor.putFloat("damagePotionDuration", damagePotion.getDuration()).apply();
        editor.putFloat("damagePotionCooldown", damagePotion.getCooldown()).apply();
        editor.putBoolean("damagePotionIsOnDurationCooldown", damagePotion.isOnDurationCooldown()).apply();

        editor.putFloat("strikeLevel", strike.getLevel()).apply();
        editor.putFloat("strikeDamage", strike.getDamage()).apply();

        editor.putFloat("chargeLevel", charge.getLevel()).apply();
        editor.putFloat("chargeDamage", charge.getDamage()).apply();
        editor.putFloat("chargeDuration", charge.getDuration()).apply();
        editor.putFloat("chargeCooldown", charge.getCooldown()).apply();
        editor.putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();

        editor.putFloat("mendLevel", mend.getLevel()).apply();
        editor.putFloat("mendHealing", mend.getHealing()).apply();
        editor.putFloat("mendDuration", mend.getDuration()).apply();
        editor.putFloat("mendCooldown", mend.getCooldown()).apply();
        editor.putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();

        editor.putFloat("defenseUpLevel", defenseUp.getLevel()).apply();
        editor.putFloat("defenseUpDefense", defenseUp.getDefense()).apply();
        editor.putFloat("defenseUpDuration", defenseUp.getDuration()).apply();
        editor.putFloat("defenseUpCooldown", defenseUp.getCooldown()).apply();
        editor.putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();

        editor.putFloat("gearPieceLevel", gearPiece.getLevel()).apply();
        editor.putFloat("gearPieceHealth", gearPiece.getHealth()).apply();
        editor.putFloat("gearPieceDefense", gearPiece.getDefense()).apply();
        editor.putFloat("gearPieceDamage", gearPiece.getDamage()).apply();
        editor.putFloat("gearPieceGoldCost", gearPiece.getGoldCost()).apply();
        editor.putFloat("gearPieceGoldWorth", gearPiece.getGoldWorth()).apply();
        editor.putFloat("gearPieceOreCost", gearPiece.getOreCost()).apply();

        editor.putFloat("monstersRequiredLevel", 2).apply();

        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
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
    public void onBackPressed()
    {
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
    public void quit(View view)
    {
        finishAffinity();
    }
    public void settings(View view) {
        intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}