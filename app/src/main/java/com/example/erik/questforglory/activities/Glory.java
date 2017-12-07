package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.classes.GearPiece;
import com.example.erik.questforglory.classes.Monster;
import com.example.erik.questforglory.classes.Player;
import com.example.erik.questforglory.classes.Potion;
import com.example.erik.questforglory.classes.Skill;
import com.example.erik.questforglory.R;

public class Glory extends AppCompatActivity {
    
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
    TextView glory;
    TextView bonusBaseHealth;
    TextView bonusBaseDefense;
    TextView bonusBaseDamage;
    TextView bonusXPYield;
    TextView bonusGoldYield;
    TextView bonusHerbsYield;
    TextView bonusOreYield;
    String LevelText;
    String XPText;
    String gloryText;
    String bonusBaseHealthText;
    String bonusBaseDefenseText;
    String bonusBaseDamageText;
    String bonusXPYieldText;
    String bonusGoldYieldText;
    String bonusHerbYieldText;
    String bonusOreYieldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glory);

        preferences = getSharedPreferences("Data", 0);

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
        glory = findViewById(R.id.gloryAmount);
        bonusBaseHealth = findViewById(R.id.bonusBaseHealth);
        bonusBaseDefense = findViewById(R.id.bonusBaseDefense);
        bonusBaseDamage = findViewById(R.id.bonusBaseDamage);
        bonusXPYield = findViewById(R.id.bonusXP);
        bonusGoldYield = findViewById(R.id.bonusGold);
        bonusHerbsYield = findViewById(R.id.bonusHerbs);
        bonusOreYield = findViewById(R.id.bonusOres);

        LevelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        gloryText = " Glory: " + Math.round(player.getGlory());
        bonusBaseHealthText = "Bonus Base Health: " + Math.round(player.getBonusBaseHealth()) + "%";
        bonusBaseDefenseText = "Bonus Base Defense: " + Math.round(player.getBonusBaseDefense()) + "%";
        bonusBaseDamageText = "Bonus Base Damage: " + Math.round(player.getBonusBaseDamage()) + "%";
        bonusXPYieldText = "Bonus XP Yield: " + Math.round(player.getBonusXPYield()) + "%";
        bonusGoldYieldText = "Bonus Gold Yield: " + Math.round(player.getBonusGoldYield()) + "%";
        bonusHerbYieldText = "Bonus Herb Yield: " + Math.round(player.getBonusHerbYield()) + "%";
        bonusOreYieldText = "Bonus Ore Yield: " + Math.round(player.getBonusOreYield()) + "%";

        level.setText(LevelText);
        XP.setText(XPText);
        glory.setText(gloryText);
        bonusBaseHealth.setText(bonusBaseHealthText);
        bonusBaseDefense.setText(bonusBaseDefenseText);
        bonusBaseDamage.setText(bonusBaseDamageText);
        bonusXPYield.setText(bonusXPYieldText);
        bonusGoldYield.setText(bonusGoldYieldText);
        bonusHerbsYield.setText(bonusHerbYieldText);
        bonusOreYield.setText(bonusOreYieldText);
    }

    public void resetGlory(View view) {
        player.increaseGlory((player.getBonusBaseHealth() + player.getBonusBaseDefense() + player.getBonusBaseDamage() + player.getBonusXPYield() + player.getBonusGoldYield() + player.getBonusHerbYield() + player.getBonusOreYield()) / 5);

        player.decreaseMaxHealth(player.getMaxHealthGearBonus());
        player.decreaseDefense(player.getDefenseGearBonus());
        player.decreaseDamage(player.getDamageGearBonus());

        player.decreaseHealth(player.getMaxHealth() - (player.getMaxHealth() / (1 + (player.getBonusBaseHealth() / 100))));
        player.setBaseHealth(player.getBaseHealth() - (player.getBonusBaseHealth() / 100));
        player.setMaxHealth(player.getMaxHealth() / (1 + (player.getBonusBaseHealth() / 100)));

        player.setBaseDefense(player.getBaseDefense() - (player.getBonusBaseDefense() / 100));
        player.setDefense(player.getDefense() / (1 + (player.getBonusBaseDefense() / 100)));

        player.setBaseDamage(player.getBaseDamage() - (player.getBonusBaseDamage() / 100));
        player.setDamage(player.getDamage() / (1 + (player.getBonusBaseDamage() / 100)));

        player.increaseMaxHealth(player.getMaxHealthGearBonus());
        player.increaseDefense(player.getDefenseGearBonus());
        player.increaseDamage(player.getDamageGearBonus());

        spriggan.setXPYield(spriggan.getXPYield() / (1 + (player.getBonusXPYield() / 100)));

        spriggan.setGoldYield(spriggan.getGoldYield() / (1 + (player.getBonusGoldYield() / 100)));
        Log.d("tag", "Spriggan gold yield: " + spriggan.getGoldYield() + "");
        spriggan.setHerbYield(spriggan.getHerbYield() / (1 + (player.getBonusHerbYield() / 100)));
        golem.setXPYield(golem.getXPYield() / (1 + (player.getBonusXPYield() / 100)));
        golem.setGoldYield(golem.getGoldYield() / (1 + (player.getBonusGoldYield() / 100)));
        golem.setOreYield(golem.getOreYield() / (1 + (player.getBonusOreYield() / 100)));

        player.setBonusBaseHealth(0);
        player.setBonusBaseDefense(0);
        player.setBonusBaseDamage(0);
        player.setBonusXPYield(0);
        player.setBonusGoldYield(0);
        player.setBonusHerbYield(0);
        player.setBonusOreYield(0);

        gloryText = " Glory: " + Math.round(player.getGlory());
        bonusBaseHealthText = "Bonus Base Health: " + Math.round(player.getBonusBaseHealth()) + "%";
        bonusBaseDefenseText = "Bonus Base Defense: " + Math.round(player.getBonusBaseDefense()) + "%";
        bonusBaseDamageText = "Bonus Base Damage: " + Math.round(player.getBonusBaseDamage()) + "%";
        bonusXPYieldText = "Bonus XP Yield: " + Math.round(player.getBonusXPYield()) + "%";
        bonusGoldYieldText = "Bonus Gold Yield: " + Math.round(player.getBonusGoldYield()) + "%";
        bonusHerbYieldText = "Bonus Herb Yield: " + Math.round(player.getBonusHerbYield()) + "%";
        bonusOreYieldText = "Bonus Ore Yield: " + Math.round(player.getBonusOreYield()) + "%";

        glory.setText(gloryText);
        bonusBaseHealth.setText(bonusBaseHealthText);
        bonusBaseDefense.setText(bonusBaseDefenseText);
        bonusBaseDamage.setText(bonusBaseDamageText);
        bonusXPYield.setText(bonusXPYieldText);
        bonusGoldYield.setText(bonusGoldYieldText);
        bonusHerbsYield.setText(bonusHerbYieldText);
        bonusOreYield.setText(bonusOreYieldText);

        preferences.edit().putFloat("glory", player.getGlory()).apply();
        preferences.edit().putFloat("bonusBaseHealth", player.getBonusBaseHealth()).apply();
        preferences.edit().putFloat("baseHealth", player.getBaseHealth()).apply();
        preferences.edit().putFloat("maxHealth", player.getMaxHealth()).apply();
        preferences.edit().putFloat("health", player.getHealth()).apply();
        preferences.edit().putFloat("bonusBaseDefense", player.getBonusBaseDefense()).apply();
        preferences.edit().putFloat("baseDefense", player.getBaseDefense()).apply();
        preferences.edit().putFloat("defense", player.getDefense()).apply();
        preferences.edit().putFloat("bonusBaseDamage", player.getBonusBaseDamage()).apply();
        preferences.edit().putFloat("baseDamage", player.getBaseDamage()).apply();
        preferences.edit().putFloat("damage", player.getDamage()).apply();
        preferences.edit().putFloat("bonusXPYield", player.getBonusXPYield()).apply();
        preferences.edit().putFloat("bonusGoldYield", player.getBonusGoldYield()).apply();
        preferences.edit().putFloat("bonusHerbYield", player.getBonusHerbYield()).apply();
        preferences.edit().putFloat("bonusOreYield", player.getBonusOreYield()).apply();
        preferences.edit().putFloat("sprigganXPYield", spriggan.getXPYield()).apply();
        preferences.edit().putFloat("sprigganGoldYield", spriggan.getGoldYield()).apply();
        preferences.edit().putFloat("sprigganHerbYield", spriggan.getHerbYield()).apply();
        preferences.edit().putFloat("golemXPYield", golem.getXPYield()).apply();
        preferences.edit().putFloat("golemGoldYield", golem.getGoldYield()).apply();
        preferences.edit().putFloat("golemOreYield", golem.getOreYield()).apply();
    }

    public void upgradeBaseHealth(View view) {
        if (player.getGlory() >= 1) {
            player.decreaseMaxHealth(player.getMaxHealthGearBonus());
            player.decreaseDefense(player.getDefenseGearBonus());
            player.decreaseDamage(player.getDamageGearBonus());
            player.increaseBonusBaseHealth();
            player.increaseMaxHealth(player.getMaxHealthGearBonus());
            player.increaseDefense(player.getDefenseGearBonus());
            player.increaseDamage(player.getDamageGearBonus());

            gloryText = " Glory: " + Math.round(player.getGlory());
            bonusBaseHealthText = "Bonus Base Health: " + Math.round(player.getBonusBaseHealth()) + "%";
            glory.setText(gloryText);
            bonusBaseHealth.setText(bonusBaseHealthText);

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("bonusBaseHealth", player.getBonusBaseHealth()).apply();
            preferences.edit().putFloat("baseHealth", player.getBaseHealth()).apply();
            preferences.edit().putFloat("maxHealth", player.getMaxHealth()).apply();
            preferences.edit().putFloat("health", player.getHealth()).apply();
        }
    }

    public void upgradeBaseDefense(View view) {
        if (player.getGlory() >= 1) {
            player.decreaseMaxHealth(player.getMaxHealthGearBonus());
            player.decreaseDefense(player.getDefenseGearBonus());
            player.decreaseDamage(player.getDamageGearBonus());
            player.increaseBonusBaseDefense();
            player.increaseMaxHealth(player.getMaxHealthGearBonus());
            player.increaseDefense(player.getDefenseGearBonus());
            player.increaseDamage(player.getDamageGearBonus());

            gloryText = " Glory: " + Math.round(player.getGlory());
            bonusBaseDefenseText = "Bonus Base Defense: " + Math.round(player.getBonusBaseDefense()) + "%";
            glory.setText(gloryText);
            bonusBaseDefense.setText(bonusBaseDefenseText);

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("bonusBaseDefense", player.getBonusBaseDefense()).apply();
            preferences.edit().putFloat("baseDefense", player.getBaseDefense()).apply();
            preferences.edit().putFloat("defense", player.getDefense()).apply();
        }
    }

    public void upgradeBaseDamage(View view) {
        if (player.getGlory() >= 1) {
            player.decreaseMaxHealth(player.getMaxHealthGearBonus());
            player.decreaseDefense(player.getDefenseGearBonus());
            player.decreaseDamage(player.getDamageGearBonus());
            player.increaseBonusBaseDamage();
            player.increaseMaxHealth(player.getMaxHealthGearBonus());
            player.increaseDefense(player.getDefenseGearBonus());
            player.increaseDamage(player.getDamageGearBonus());

            gloryText = " Glory: " + Math.round(player.getGlory());
            bonusBaseDamageText = "Bonus Base Damage: " + Math.round(player.getBonusBaseDamage()) + "%";
            glory.setText(gloryText);
            bonusBaseDamage.setText(bonusBaseDamageText);

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("bonusBaseDamage", player.getBonusBaseDamage()).apply();
            preferences.edit().putFloat("baseDamage", player.getBaseDamage()).apply();
            preferences.edit().putFloat("damage", player.getDamage()).apply();
        }
    }

    public void upgradeXPYield(View view) {
        if (player.getGlory() >= 1) {
            spriggan.setXPYield(spriggan.getXPYield() / (1 + (player.getBonusXPYield() / 100)));
            player.increaseBonusXP();
            spriggan.setXPYield(spriggan.getXPYield() * (1 + (player.getBonusXPYield() / 100)));

            gloryText = " Glory: " + Math.round(player.getGlory());
            bonusXPYieldText = "Bonus XP Yield: " + Math.round(player.getBonusXPYield()) + "%";
            glory.setText(gloryText);
            bonusXPYield.setText(bonusXPYieldText);

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("bonusXPYield", player.getBonusXPYield()).apply();
            preferences.edit().putFloat("sprigganXPYield", spriggan.getXPYield()).apply();
            preferences.edit().putFloat("golemXPYield", golem.getXPYield()).apply();
        }
    }
    public void upgradeGoldYield(View view) {
        if (player.getGlory() >= 1) {
            Log.d("tag", "Spriggan gold yield: " + spriggan.getGoldYield() + "");
            spriggan.setGoldYield(spriggan.getGoldYield() / (1 + (player.getBonusGoldYield() / 100)));
            golem.setGoldYield(golem.getGoldYield() / (1 + (player.getBonusGoldYield() / 100)));
            player.increaseBonusGold();
            Log.d("tag", "Spriggan gold yield: " + spriggan.getGoldYield() + "");
            spriggan.setGoldYield(spriggan.getGoldYield() * (1 + (player.getBonusGoldYield() / 100)));
            golem.setGoldYield(golem.getGoldYield() * (1 + (player.getBonusGoldYield() / 100)));
            gloryText = " Glory: " + Math.round(player.getGlory());
            bonusGoldYieldText = "Bonus Gold Yield: " + Math.round(player.getBonusGoldYield()) + "%";
            glory.setText(gloryText);
            bonusGoldYield.setText(bonusGoldYieldText);

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("bonusGoldYield", player.getBonusGoldYield()).apply();
            preferences.edit().putFloat("sprigganGoldYield", spriggan.getGoldYield()).apply();
            preferences.edit().putFloat("golemGoldYield", golem.getGoldYield()).apply();
        }
    }

    public void upgradeHerbYield(View view) {
        if (player.getGlory() >= 1) {
            spriggan.setHerbYield(spriggan.getHerbYield() / (1 + (player.getBonusHerbYield() / 100)));
            player.increaseBonusHerbs();
            spriggan.setHerbYield(spriggan.getHerbYield() * (1 + (player.getBonusHerbYield() / 100)));

            gloryText = " Glory: " + Math.round(player.getGlory());
            bonusHerbYieldText = "Bonus Herb Yield: " + Math.round(player.getBonusHerbYield()) + "%";
            glory.setText(gloryText);
            bonusHerbsYield.setText(bonusHerbYieldText);

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("bonusHerbYield", player.getBonusHerbYield()).apply();
            preferences.edit().putFloat("sprigganHerbYield", spriggan.getHerbYield()).apply();
        }
    }

    public void upgradeOreYield(View view) {
        if (player.getGlory() >= 1) {
            golem.setOreYield(golem.getOreYield() / (1 + (player.getBonusOreYield() / 100)));
            player.increaseBonusOres();
            golem.setOreYield(golem.getOreYield() * (1 + (player.getBonusOreYield() / 100)));

            gloryText = " Glory: " + Math.round(player.getGlory());
            bonusOreYieldText = "Bonus Ore Yield: " + Math.round(player.getBonusOreYield()) + "%";
            glory.setText(gloryText);
            bonusOreYield.setText(bonusOreYieldText);

            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("bonusOreYield", player.getBonusOreYield()).apply();
            preferences.edit().putFloat("golemOreYield", golem.getOreYield()).apply();
        }
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
        intent = new Intent(this, MainActivity.class);
        saveObjectsInBundle();
    }

    public void skills(View view) {
        intent = new Intent(this, Skills.class);
        saveObjectsInBundle();
    }

    public void ascension(View view) {
        intent = new Intent(this, Ascension.class);
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