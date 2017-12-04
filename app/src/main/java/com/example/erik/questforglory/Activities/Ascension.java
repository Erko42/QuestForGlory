package com.example.erik.questforglory.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.Classes.Monster;
import com.example.erik.questforglory.Classes.Player;
import com.example.erik.questforglory.Classes.Potion;
import com.example.erik.questforglory.Classes.Skill;
import com.example.erik.questforglory.Helpers.DatabaseHelper;
import com.example.erik.questforglory.Classes.GearPiece;
import com.example.erik.questforglory.R;

public class Ascension extends AppCompatActivity {

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
    TextView level;
    TextView XP;
    TextView ascend;
    TextView transcend;
    TextView ascendInfo;
    TextView areYouSureGrid;
    TextView areYouSure;
    TextView areYouSureYes;
    TextView areYouSureNo;
    String levelText;
    String XPText;
    String ascendInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascension);

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

        level = findViewById(R.id.level);
        XP = findViewById(R.id.XP);
        ascend = findViewById(R.id.ascend);
        transcend = findViewById(R.id.transcend);
        ascendInfo = findViewById(R.id.ascendInfo);
        areYouSureGrid = findViewById(R.id.areYouSureGrid);
        areYouSure = findViewById(R.id.areYouSure);
        areYouSureYes = findViewById(R.id.areYouSureYes);
        areYouSureNo = findViewById(R.id.areYouSureNo);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        ascendInfoText = "Start From The Beginning As A Stronger Being.\nYou Have To Spend All Your Glory As You Will\nKeep All Your Glory Spent\n\n" + "(" + "Requires Level " + Math.round(player.getRequiredLevelToAscend()) + ")";

        level.setText(levelText);
        XP.setText(XPText);
        ascendInfo.setText(ascendInfoText);
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

    public void ascend(View view) {
//        if(player.getGlory() > 0 && player.getLevel() >= player.getRequiredLevelToAscend())
//        {
//            intent = new Intent(this, Glory.class);
//            saveObjectsInBundle();
//        }
//        else if(player.getLevel() >= player.getRequiredLevelToAscend())
//        {
        areYouSureGrid.setVisibility(View.VISIBLE);
        areYouSure.setVisibility(View.VISIBLE);
        areYouSureYes.setVisibility(View.VISIBLE);
        areYouSureNo.setVisibility(View.VISIBLE);
        ascend.setVisibility(View.INVISIBLE);
        transcend.setVisibility(View.INVISIBLE);
//        }
    }

    public void yes(View view) {
        db.deleteAllData();

        player.increaseRequiredLevelToAscend(100);
        player.setLevel(1);

        player.setXP(0);
        player.setXPToNextLevel(200);

        player.setSkillPoints(0);

        player.setGold(0);
        player.setHerbs(0);
        player.setOres(0);

        player.setMaxHealth(500);
        player.setDefense(10);
        player.setDamage(50);

        player.setBaseHealth(1);
        player.setBaseDefense(1);
        player.setBaseDamage(1);

        player.setMaxHealthGearBonus(0);
        player.setDefenseGearBonus(0);
        player.setDamageGearBonus(0);

        spriggan = new Monster(true, 1, 1, 40, 750, 750, 50/1.5f, 30, 10, 0);
        golem = new Monster(true, 1, 1, 40, 500, 500, 50, 30, 0, 10);

        spriggan.setMaxLevel(1);
        spriggan.setLevel(1);
        spriggan.setMaxHealth(750);
        spriggan.setHealth(750);
        spriggan.setDamage(50/1.5f);
        spriggan.setXPYield(40);
        spriggan.setHerbYield(10);
        spriggan.setGoldYield(30);

        golem.setMaxLevel(1);
        golem.setLevel(1);
        golem.setMaxHealth(500);
        golem.setHealth(500);
        golem.setDamage(50);
        golem.setXPYield(40);
        golem.setOreYield(10);
        golem.setGoldYield(30);

        player.setBaseHealth(1 + (player.getBonusBaseHealth() / 100));
        player.setMaxHealth(player.getMaxHealth() * player.getBaseHealth());
        player.setHealth(player.getMaxHealth());

        player.setBaseDefense(1 + (player.getBonusBaseDefense() / 100));
        player.setDefense(player.getDefense() * player.getBaseDefense());

        player.setBaseDamage(1 + (player.getBonusBaseDamage() / 100));
        player.setDamage(player.getDamage() * player.getBaseDamage());

        spriggan.setXPYield(spriggan.getXPYield() * (1 + (player.getBonusXPYield() / 100)));
        spriggan.setHerbYield(spriggan.getHerbYield() * (1 + (player.getBonusHerbYield() / 100)));
        spriggan.setGoldYield(spriggan.getGoldYield() * (1 + (player.getBonusGoldYield() / 100)));

        golem.setXPYield(golem.getXPYield() * (1 + (player.getBonusXPYield() / 100)));
        golem.setOreYield(golem.getOreYield() * (1 + (player.getBonusOreYield() / 100)));
        golem.setGoldYield(golem.getGoldYield() * (1 + (player.getBonusGoldYield() / 100)));

        strike.setLevel(1);
        strike.setDamage(100);
        charge.setLevel(1);
        charge.setDamage(50);
        charge.setCooldown(5);
        charge.setDuration(1);
        mend.setLevel(1);
        mend.setHealing(5);
        mend.setCooldown(4);
        mend.setDuration(3);
        defenseUp.setLevel(1);
        defenseUp.setDefense(20);
        defenseUp.setCooldown(3);
        defenseUp.setDuration(1);

        healthPotion.setLevel(1);
        healthPotion.setAmount(0);
        healthPotion.setUpgradeCost(100);
        healthPotion.setHerbCost(10);
        healthPotion.setGoldCost(40);
        healthPotion.setGoldWorth(30);
        healthPotion.setEffect(250);

        damagePotion.setLevel(1);
        damagePotion.setAmount(0);
        damagePotion.setUpgradeCost(100);
        damagePotion.setHerbCost(10);
        damagePotion.setGoldCost(40);
        damagePotion.setGoldWorth(30);
        damagePotion.setEffect(50);

        preferences.edit().putFloat("requiredLevelToAscend", player.getRequiredLevelToAscend()).apply();
        preferences.edit().putFloat("level", player.getLevel()).apply();
        preferences.edit().putFloat("XP", player.getXP()).apply();
        preferences.edit().putFloat("XPToNextLevel", player.getXPToNextLevel()).apply();
        preferences.edit().putFloat("skillPoints", player.getSkillPoints()).apply();
        preferences.edit().putFloat("maxHealth", player.getMaxHealth()).apply();
        preferences.edit().putFloat("maxHealthGearBonus", 0).apply();
        preferences.edit().putFloat("defenseGearBonus", 0).apply();
        preferences.edit().putFloat("damageGearBonus", 0).apply();
        preferences.edit().putFloat("baseHealth", player.getBaseHealth()).apply();
        preferences.edit().putFloat("baseDefense", player.getBaseDefense()).apply();
        preferences.edit().putFloat("baseDamage", player.getBaseDamage()).apply();
        preferences.edit().putFloat("health", player.getHealth()).apply();
        preferences.edit().putFloat("defense", player.getDefense()).apply();
        preferences.edit().putFloat("damage", player.getDamage()).apply();
        preferences.edit().putFloat("critChance", player.getCritChance()).apply();
        preferences.edit().putFloat("gold", player.getGold()).apply();
        preferences.edit().putFloat("herbs", player.getHerbs()).apply();
        preferences.edit().putFloat("ores", player.getOres()).apply();

        preferences.edit().putFloat("sprigganMaxLevel", spriggan.getMaxLevel()).apply();
        preferences.edit().putFloat("sprigganLevel", spriggan.getLevel()).apply();
        preferences.edit().putFloat("sprigganXPYield", spriggan.getXPYield()).apply();
        preferences.edit().putFloat("sprigganMaxHealth", spriggan.getMaxHealth()).apply();
        preferences.edit().putFloat("sprigganDamage", spriggan.getDamage()).apply();
        preferences.edit().putFloat("sprigganGoldYield", spriggan.getGoldYield()).apply();
        preferences.edit().putFloat("sprigganHerbYield", spriggan.getHerbYield()).apply();

        preferences.edit().putFloat("golemMaxLevel", golem.getMaxLevel()).apply();
        preferences.edit().putFloat("golemLevel", golem.getLevel()).apply();
        preferences.edit().putFloat("golemXPYield", golem.getXPYield()).apply();
        preferences.edit().putFloat("golemMaxHealth", golem.getMaxHealth()).apply();
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

        preferences.edit().putFloat("strikeLevel", strike.getLevel()).apply();
        preferences.edit().putFloat("strikeDamage", strike.getDamage()).apply();

        preferences.edit().putFloat("chargeLevel", charge.getLevel()).apply();
        preferences.edit().putFloat("chargeDamage", charge.getDamage()).apply();
        preferences.edit().putFloat("chargeDuration", charge.getDuration()).apply();
        preferences.edit().putFloat("chargeCooldown", charge.getCooldown()).apply();

        preferences.edit().putFloat("mendLevel", mend.getLevel()).apply();
        preferences.edit().putFloat("mendHealing", mend.getHealing()).apply();
        preferences.edit().putFloat("mendDuration", mend.getDuration()).apply();
        preferences.edit().putFloat("mendCooldown", mend.getCooldown()).apply();

        preferences.edit().putFloat("defenseUpLevel", defenseUp.getLevel()).apply();
        preferences.edit().putFloat("defenseUpDefense", defenseUp.getDefense()).apply();
        preferences.edit().putFloat("defenseUpDuration", defenseUp.getDuration()).apply();
        preferences.edit().putFloat("defenseUpCooldown", defenseUp.getCooldown()).apply();

        preferences.edit().putFloat("monstersRequiredLevel", 2).apply();

        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
    }

    public void no(View view) {
        areYouSureGrid.setVisibility(View.INVISIBLE);
        areYouSure.setVisibility(View.INVISIBLE);
        areYouSureYes.setVisibility(View.INVISIBLE);
        areYouSureNo.setVisibility(View.INVISIBLE);
        ascend.setVisibility(View.VISIBLE);
        transcend.setVisibility(View.VISIBLE);
    }

    public void transcendence(View view) {

    }

    public void glory(View view) {
        intent = new Intent(this, Glory.class);
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