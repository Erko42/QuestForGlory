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

public class Skills extends AppCompatActivity {
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
    TextView level;
    TextView XP;
    TextView skillPoints;
    TextView strikeInfo;
    TextView chargeInfo;
    TextView mendInfo;
    TextView defenseUpInfo;
    String levelText;
    String XPText;
    String skillPointsText;
    String strikeInfoText;
    String chargeInfoText;
    String mendInfoText;
    String defenseUpInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

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

        level = (TextView) findViewById(R.id.level);
        XP = (TextView) findViewById(R.id.XP);
        skillPoints = (TextView) findViewById(R.id.skillPoints);
        strikeInfo = (TextView) findViewById(R.id.strikeInfo);
        chargeInfo = (TextView) findViewById(R.id.chargeInfo);
        mendInfo = (TextView) findViewById(R.id.mendInfo);
        defenseUpInfo = (TextView) findViewById(R.id.defenseUpInfo);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        skillPointsText = "Skill Points: " + Math.round(player.getSkillPoints());
        strikeInfoText = "Strike Level " + Math.round(strike.getLevel()) + "\nDamaging enemies\nfor " + Math.round(strike.getDamage()) + "% damage";
        chargeInfoText = "Charge Level " + Math.round(charge.getLevel()) + " (CD: " + Math.round(charge.getCooldown()) + ")" + "\nDamaging enemies\nfor " + Math.round(charge.getDamage()) + "% damage\nStun for " + Math.round(charge.getDuration()) + " turn";
        mendInfoText = "Mend Level " + Math.round(mend.getLevel()) + " (CD: " + Math.round(mend.getCooldown()) + ")" + "\nHeal over time\nfor " + Math.round(mend.getHealing()) + "% max health\nfor " + Math.round(mend.getDuration()) + " turns";
        defenseUpInfoText = "Defense Up Level " + Math.round(defenseUp.getLevel()) + " (CD: " + Math.round(defenseUp.getCooldown()) + ")" + "\nGain +" + Math.round(defenseUp.getDefense()) + "% defense";

        level.setText(levelText);
        XP.setText(XPText);
        skillPoints.setText(skillPointsText);
        strikeInfo.setText(strikeInfoText);
        chargeInfo.setText(chargeInfoText);
        mendInfo.setText(mendInfoText);
        defenseUpInfo.setText(defenseUpInfoText);
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
    public void resetSkills(View view) {
        if (strike.getLevel() > 1) {
            player.increaseSkillPoints(Math.round(strike.getLevel()) - 1);
            strike.setLevel(1);
            strike.setDamage(100);
            strikeInfoText = "Strike Level " + Math.round(strike.getLevel()) + "\nDamaging enemies\nfor " + Math.round(strike.getDamage()) + "% damage";
            strikeInfo.setText(strikeInfoText);

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("strikeLevel", strike.getLevel()).apply();
            editor.putFloat("strikeDamage", strike.getDamage()).apply();
        }
        if (charge.getLevel() > 1) {
            player.increaseSkillPoints(Math.round(charge.getLevel()) * 4 - 4);
            charge.setLevel(1);
            charge.setDamage(50);
            charge.setDuration(1);
            charge.setCooldown(3);
            chargeInfoText = "Charge Level " + Math.round(charge.getLevel()) + " (CD: " + Math.round(charge.getCooldown()) + ")" + "\nDamaging enemies\nfor " + Math.round(charge.getDamage()) + "% damage\nStun for " + Math.round(charge.getDuration()) + " turns";
            chargeInfo.setText(chargeInfoText);

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("chargeLevel", charge.getLevel()).apply();
            editor.putFloat("chargeDamage", charge.getDamage()).apply();
            editor.putFloat("chargeDuration", charge.getDuration()).apply();
            editor.putFloat("chargeCooldown", charge.getCooldown()).apply();
        }
        if (mend.getLevel() > 1) {
            player.increaseSkillPoints(Math.round(mend.getLevel()) * 3 - 3);
            mend.setLevel(1);
            mend.setHealing(5);
            mendInfoText = "Mend Level " + Math.round(mend.getLevel()) + " (CD: " + Math.round(mend.getCooldown()) + ")" + "\nHeal over time\nfor " + Math.round(mend.getHealing()) + "% max health\nfor " + Math.round(mend.getDuration()) + " turns";
            mendInfo.setText(mendInfoText);

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("mendLevel", mend.getLevel()).apply();
            editor.putFloat("mendHealing", mend.getHealing()).apply();
        }
        if (defenseUp.getLevel() > 1) {
            player.increaseSkillPoints(Math.round(defenseUp.getLevel()) * 2 - 2);
            defenseUp.setLevel(1);
            defenseUp.setDefense(20);
            defenseUpInfoText = "Defense Up Level " + Math.round(defenseUp.getLevel()) + " (CD: " + Math.round(defenseUp.getCooldown()) + ")" + "\nGain +" + Math.round(defenseUp.getDefense()) + "% defense";
            defenseUpInfo.setText(defenseUpInfoText);

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("defenseUpLevel", defenseUp.getLevel()).apply();
            editor.putFloat("defenseUpDefense", defenseUp.getDefense()).apply();
        }
        skillPointsText = "Skill Points: " + Math.round(player.getSkillPoints());
        skillPoints.setText(skillPointsText);
    }
    public void upgradeStrike(View view) {
        if(Math.round(player.getSkillPoints()) >= 1) {
            player.decreaseSkillPoints(1);
            strike.levelUpStrike();

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("strikeLevel", strike.getLevel());
            editor.putFloat("strikeDamage", strike.getDamage()).apply();

            skillPointsText = "Skill Points: " + Math.round(player.getSkillPoints());
            strikeInfoText = "Strike Level " + Math.round(strike.getLevel()) + "\nDamaging enemies\nfor " + Math.round(strike.getDamage()) + "% damage";

            skillPoints.setText(skillPointsText);
            strikeInfo.setText(strikeInfoText);
        }
    }
    public void upgradeCharge(View view) {
        if (Math.round(player.getSkillPoints()) >= 4) {
            player.decreaseSkillPoints(4);
            charge.leveUpCharge();

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("chargeLevel", charge.getLevel()).apply();
            editor.putFloat("chargeDamage", charge.getDamage()).apply();
            editor.putFloat("chargeDuration", charge.getDuration()).apply();
            editor.putFloat("chargeCooldown", charge.getCooldown()).apply();

            skillPointsText = "Skill Points: " + Math.round(player.getSkillPoints());
            chargeInfoText = "Charge Level " + Math.round(charge.getLevel()) + " (CD: " + Math.round(charge.getCooldown()) + ")" + "\nDamaging enemies\nfor " + Math.round(charge.getDamage()) + "% damage\nStun for " + Math.round(charge.getDuration()) + " turns";

            skillPoints.setText(skillPointsText);
            chargeInfo.setText(chargeInfoText);
        }
    }
    public void upgradeMend(View view) {
        if (Math.round(player.getSkillPoints()) >= 3) {
            player.decreaseSkillPoints(3);
            mend.levelUpMend();

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("mendLevel", mend.getLevel()).apply();
            editor.putFloat("mendHealing", mend.getHealing()).apply();
            editor.putFloat("mendDuration", mend.getDuration()).apply();
            editor.putFloat("mendCooldown", mend.getCooldown()).apply();

            skillPointsText = "Skill Points: " + Math.round(player.getSkillPoints());
            mendInfoText = "Mend Level " + Math.round(mend.getLevel()) + " (CD: " + Math.round(mend.getCooldown()) + ")" + "\nHeal over time\nfor " + Math.round(mend.getHealing()) + "% max health\nfor " + Math.round(mend.getDuration()) + " turns";

            skillPoints.setText(skillPointsText);
            mendInfo.setText(mendInfoText);
        }
    }
    public void upgradeDefenseUp(View view) {
        if (Math.round(player.getSkillPoints()) >= 2) {
            player.decreaseSkillPoints(2);
            defenseUp.levelUpDefenseUp();

            editor.putFloat("skillPoints", player.getSkillPoints()).apply();
            editor.putFloat("defenseUpLevel", defenseUp.getLevel()).apply();
            editor.putFloat("defenseUpDefense", defenseUp.getDefense()).apply();
            editor.putFloat("defenseUpDuration", defenseUp.getDuration()).apply();
            editor.putFloat("defenseUpCooldown", defenseUp.getCooldown()).apply();

            skillPointsText = "Skill Points: " + Math.round(player.getSkillPoints());
            defenseUpInfoText = "Defense Up Level " + Math.round(defenseUp.getLevel()) + " (CD: " + Math.round(defenseUp.getCooldown()) + ")" + "\nGain +" + Math.round(defenseUp.getDefense()) + "% defense";

            skillPoints.setText(skillPointsText);
            defenseUpInfo.setText(defenseUpInfoText);
        }
    }
    public void onBackPressed() {
        intent = new Intent(this, MainActivity.class);
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