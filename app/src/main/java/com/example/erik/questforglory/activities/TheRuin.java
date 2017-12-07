package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erik.questforglory.classes.GearPiece;
import com.example.erik.questforglory.classes.Monster;
import com.example.erik.questforglory.classes.Player;
import com.example.erik.questforglory.classes.Potion;
import com.example.erik.questforglory.classes.Skill;
import com.example.erik.questforglory.helpers.DatabaseHelper;
import com.example.erik.questforglory.R;

import java.util.ArrayList;
import java.util.Random;

public class TheRuin extends AppCompatActivity {
    
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
    GearPiece gearPieceReward;
    GearPiece theGearPiece;
    ArrayList<String> gearPieceType;
    ArrayList<String> gearPieceRarity;
    TextView playerHealth;
    TextView golemHealth;
    TextView youAreDeadGrid;
    TextView youAreDead;
    TextView accept;
    TextView golemDefeatedGrid;
    TextView golemDefeated;
    TextView gearPieceRewardView;
    TextView home;
    TextView Continue;
    TextView healthPotionAmount;
    TextView damagePotionAmount;
    TextView damagePotionDurationCooldown;
    TextView healthPotionDurationCooldown;
    TextView chargeDurationCooldown;
    TextView mendDurationCooldown;
    TextView defenseUpDurationCooldown;
    ImageView damagePotionImage;
    ImageView healthPotionImage;
    ImageView chargeImage;
    ImageView mendImage;
    ImageView defenseUpImage;
    String playerHealthText;
    String golemHealthText;
    String healthPotionAmountText;
    String damagePotionAmountText;
    String damagePotionDurationCooldownText;
    String healthPotionDurationCooldownText;
    String youAreDeadText;
    String golemDefeatedText;
    String chargeDurationCooldownText;
    String mendDurationCooldownText;
    String defenseUpDurationCooldownText;
    int gearPieceDropChance;
    int gearPieceTypeChance;
    int gearPieceOffOrDefChance;
    int whichGearPieceType;
    int whichGearPieceRarity;
    int gearPieceMaxRarityDropChance;
    int gearPieceMinRarityDropChance;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_ruin);

        db = new DatabaseHelper(this);

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

        gearPieceType = new ArrayList<>();
        gearPieceType.add("Helm");
        gearPieceType.add("Pauldrons");
        gearPieceType.add("Chestplate");
        gearPieceType.add("Bracers");
        gearPieceType.add("MainHand Sword");
        gearPieceType.add("OffHand Sword");
        gearPieceType.add("OffHand Shield");
        gearPieceType.add("Gauntlets");
        gearPieceType.add("Belt");
        gearPieceType.add("Legplates");
        gearPieceType.add("Sabatons");

        gearPieceRarity = new ArrayList<>();
        gearPieceRarity.add("common");
        gearPieceRarity.add("uncommon");
        gearPieceRarity.add("rare");
        gearPieceRarity.add("epic");
        gearPieceRarity.add("legendary");

        playerHealth = findViewById(R.id.playerHealth);
        golemHealth = findViewById(R.id.golemHealth);
        healthPotionAmount = findViewById(R.id.healthPotionAmount);
        damagePotionAmount = findViewById(R.id.damagePotionAmount);
        damagePotionDurationCooldown = findViewById(R.id.damagePotionDurationCooldown);
        healthPotionDurationCooldown = findViewById(R.id.healthPotionDurationCooldown);
        youAreDeadGrid = findViewById(R.id.youAreDeadGrid);
        youAreDead = findViewById(R.id.youAreDead);
        accept = findViewById(R.id.accept);
        golemDefeatedGrid = findViewById(R.id.golemDefeatedGrid);
        golemDefeated = findViewById(R.id.golemDefeated);
        gearPieceRewardView = findViewById(R.id.gearPieceRewardView);
        home = findViewById(R.id.home);
        Continue = findViewById(R.id.Continue);
        chargeDurationCooldown = findViewById(R.id.chargeDurationCooldown);
        mendDurationCooldown = findViewById(R.id.mendDurationCooldown);
        defenseUpDurationCooldown = findViewById(R.id.defenseUpDurationCooldown);
        damagePotionImage = findViewById(R.id.damagePotion);
        healthPotionImage = findViewById(R.id.healthPotion);
        chargeImage = findViewById(R.id.charge);
        mendImage = findViewById(R.id.mend);
        defenseUpImage = findViewById(R.id.defenseUp);

        playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
        golemHealthText = "  Health: " + Math.round(golem.getHealth()) + " / " + Math.round(golem.getMaxHealth()) + "  ";
        healthPotionAmountText = "Amount: " + Math.round(healthPotion.getAmount());
        damagePotionAmountText = "Amount: " + Math.round(damagePotion.getAmount());
        damagePotionDurationCooldownText = "Duration: " + Math.round(damagePotion.getDuration());
        healthPotionDurationCooldownText = "Duration: " + Math.round(healthPotion.getDuration());
        youAreDeadText = "You Are Dead!\n\nRevive for\n" + Math.round(player.getXPToNextLevel()) / 2  + " Gold\n";
        golemDefeatedText = "Golem Defeated!\nRewards:\n" + Math.round(golem.getXPYield()) + " XP\n" + Math.round(golem.getOreYield()) + " Ores\n" + Math.round(golem.getGoldYield()) + " Gold";
        chargeDurationCooldownText = "Duration: " + Math.round(charge.getCurrentDuration());
        mendDurationCooldownText = "Duration: " + Math.round(mend.getCurrentDuration());
        defenseUpDurationCooldownText = "Duration: " + Math.round(defenseUp.getCurrentDuration());

        playerHealth.setText(playerHealthText);
        golemHealth.setText(golemHealthText);
        healthPotionAmount.setText(healthPotionAmountText);
        damagePotionAmount.setText(damagePotionAmountText);
        damagePotionDurationCooldown.setText(damagePotionDurationCooldownText);
        healthPotionDurationCooldown.setText(healthPotionDurationCooldownText);
        golem.setAlive(true);
        youAreDead.setText(youAreDeadText);
        golemDefeated.setText(golemDefeatedText);
        chargeDurationCooldown.setText(chargeDurationCooldownText);

        chargeDurationCooldown.setVisibility(View.INVISIBLE);
        mendDurationCooldown.setVisibility(View.INVISIBLE);
        defenseUpDurationCooldown.setVisibility(View.INVISIBLE);
        damagePotionDurationCooldown.setVisibility(View.INVISIBLE);
        healthPotionDurationCooldown.setVisibility(View.INVISIBLE);

        player.setBattleDamage(player.getDamage());
        player.setBattleDefense(player.getDefense());

        checkPlayer();

        if (charge.isOnDurationCooldown()) {
            chargeImage.setImageAlpha(125);
            chargeDurationCooldown.setVisibility(View.VISIBLE);

            if (charge.getCurrentDuration() > 0) {
                chargeDurationCooldownText = "Duration: " + Math.round(charge.getCurrentDuration());
                chargeDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                chargeDurationCooldown.setText(chargeDurationCooldownText);
            } else if (charge.getCurrentDuration() < 1) {
                chargeDurationCooldownText = "Cooldown: " + Math.round(charge.getCurrentCooldown() + 1);
                chargeDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                chargeDurationCooldown.setText(chargeDurationCooldownText);
            }
        }
        if (mend.isOnDurationCooldown()) {
            mendImage.setImageAlpha(125);
            mendDurationCooldown.setVisibility(View.VISIBLE);

            if (mend.getCurrentDuration() > 0) {
                mendDurationCooldownText = "Duration: " + Math.round(mend.getCurrentDuration());
                mendDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                mendDurationCooldown.setText(mendDurationCooldownText);
            } else if(mend.getCurrentDuration() < 1) {
                mendDurationCooldownText = "Cooldown: " + Math.round(mend.getCurrentCooldown() + 1);
                mendDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                mendDurationCooldown.setText(mendDurationCooldownText);
            }
        }
        if (defenseUp.isOnDurationCooldown()) {
            defenseUpImage.setImageAlpha(125);
            defenseUpDurationCooldown.setVisibility(View.VISIBLE);

            if (defenseUp.getCurrentDuration() > 0) {
                defenseUpDurationCooldownText = "Duration: " + Math.round(defenseUp.getCurrentDuration());
                defenseUpDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                defenseUpDurationCooldown.setText(defenseUpDurationCooldownText);
            } else if (defenseUp.getCurrentDuration() < 1) {
                defenseUpDurationCooldownText = "Cooldown: " + Math.round(defenseUp.getCurrentCooldown() + 1);
                defenseUpDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                defenseUpDurationCooldown.setText(defenseUpDurationCooldownText);
            }
        }
        if (damagePotion.isOnDurationCooldown()) {
            damagePotionImage.setImageAlpha(125);
            damagePotionDurationCooldown.setVisibility(View.VISIBLE);

            if (damagePotion.getCurrentDuration() > 0) {
                damagePotionDurationCooldownText = "Duration: " + Math.round(damagePotion.getCurrentDuration());
                damagePotionDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                damagePotionDurationCooldown.setText(damagePotionDurationCooldownText);
            } else if (damagePotion.getCurrentDuration() < 1) {
                damagePotionDurationCooldownText = "Cooldown: " + Math.round(damagePotion.getCurrentCooldown() + 1);
                damagePotionDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                damagePotionDurationCooldown.setText(damagePotionDurationCooldownText);
            }
        }
        if (healthPotion.isOnDurationCooldown()) {
            healthPotionImage.setImageAlpha(125);
            healthPotionDurationCooldown.setVisibility(View.VISIBLE);

            if (healthPotion.getCurrentDuration() > 0) {
                healthPotionDurationCooldownText = "Duration: " + Math.round(healthPotion.getCurrentDuration());
                healthPotionDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                healthPotionDurationCooldown.setText(healthPotionDurationCooldownText);
            } else if (damagePotion.getCurrentDuration() < 1) {
                healthPotionDurationCooldownText = "Cooldown: " + Math.round(healthPotion.getCurrentCooldown() + 1);
                healthPotionDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                healthPotionDurationCooldown.setText(healthPotionDurationCooldownText);
            }
        }
        if (!player.isAlive()) {
            youAreDeadGrid.setVisibility(View.VISIBLE);
            youAreDead.setVisibility(View.VISIBLE);
            accept.setVisibility(View.VISIBLE);
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
        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
    }

    public void revive(View view) {
        youAreDeadGrid.setVisibility(View.INVISIBLE);
        youAreDead.setVisibility(View.INVISIBLE);
        accept.setVisibility(View.INVISIBLE);

        player.setAlive(true);
        player.setHealth(player.getMaxHealth());

        playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
        playerHealth.setText(playerHealthText);
        player.decreaseGold(Math.round(player.getXPToNextLevel()) / 2);

        preferences.edit().putFloat("gold", player.getGold()).apply();
        preferences.edit().putFloat("health", player.getHealth()).apply();

        newBattle();
    }

    public void checkDamagePotion() {
        if (damagePotion.getCurrentDuration() > 0 && player.isAlive() && golem.isAlive() && damagePotionDurationCooldown.getVisibility() == View.VISIBLE) {
            damagePotion.tickDuration();
            damagePotionDurationCooldownText = "Duration: " + Math.round(damagePotion.getCurrentDuration());
            damagePotionDurationCooldown.setText(damagePotionDurationCooldownText);
        }
        if (damagePotion.getCurrentDuration() < 1 && player.isAlive() && golem.isAlive()) {
            damagePotionDurationCooldown.setVisibility(View.INVISIBLE);
            damagePotionImage.setImageAlpha(255);
            damagePotion.refresh();
            player.setBattleDamage(player.getDamage());
        }
    }

    public void checkHealthPotion() {
        if (healthPotion.getCurrentDuration() > 0 && player.isAlive() && golem.isAlive() && healthPotionDurationCooldown.getVisibility() == View.VISIBLE) {
            healthPotion.tickDuration();
            healthPotionDurationCooldownText = "Duration: " + Math.round(healthPotion.getCurrentDuration());
            healthPotionDurationCooldown.setText(healthPotionDurationCooldownText);
        }
        if (healthPotion.getCurrentDuration() < 1 && player.isAlive() && golem.isAlive()) {
            healthPotionDurationCooldown.setVisibility(View.INVISIBLE);
            healthPotionImage.setImageAlpha(255);
            healthPotion.refresh();
            player.setBattleDamage(player.getDamage());
        }
    }

    public void checkgolem() {
        if (Math.round(golem.getHealth()) <= 0) {
            golem.setAlive(false);
            golem.setHealth(Math.round(golem.getMaxHealth()));
            player.resetBattleDefense();
            player.increaseXP(Math.round(golem.getXPYield()));
            player.increaseOres(Math.round(golem.getOreYield()));
            player.increaseGold(Math.round(golem.getGoldYield()));

            gearPieceReward = new GearPiece(0, "gear piece", "rarity", "stats", 1, 50, 1, 5, 0, 0, 160, 120, 40);

            if (golem.getLevel() > 1) {
                for (int i = 1; i < golem.getLevel(); i++) {
                    gearPieceReward.levelUp();
                }
            }
            rand = new Random();
            gearPieceDropChance = rand.nextInt(100) + 1;
            gearPieceTypeChance = rand.nextInt(99) + 1;
            gearPieceOffOrDefChance = rand.nextInt(100) + 1;

            gearPieceMaxRarityDropChance = 5;
            gearPieceMinRarityDropChance = 1;
            whichGearPieceType = 0;
            whichGearPieceRarity = 1;

            for (int y = 5; y <= 15; y += gearPieceMaxRarityDropChance) {
                if (gearPieceDropChance <= y && gearPieceDropChance >= (gearPieceMinRarityDropChance + y - gearPieceMaxRarityDropChance)) {
                    for (int i = 0; i <= 99; i += 9) {
                        if (gearPieceTypeChance > i && gearPieceTypeChance <= (i + 9) && (gearPieceType.get(whichGearPieceType).equals("MainHand Sword") || gearPieceType.get(whichGearPieceType).equals("OffHand Sword"))) {
                            theGearPiece = new GearPiece(
                                    gearPieceReward.getIsEquiped(),
                                    gearPieceType.get(whichGearPieceType),
                                    gearPieceRarity.get(whichGearPieceRarity - 1),
                                    gearPieceReward.getStats(),
                                    golem.getLevel(),
                                    0,
                                    0,
                                    gearPieceReward.getDamage() * whichGearPieceRarity * 2,
                                    gearPieceReward.getBlockChance(),
                                    gearPieceReward.getCritChance(),
                                    gearPieceReward.getGoldCost(),
                                    gearPieceReward.getGoldWorth() * whichGearPieceRarity,
                                    gearPieceReward.getOreCost());
                            theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));

                            db.insertData(
                                    theGearPiece.getIsEquiped(),
                                    theGearPiece.getName(),
                                    theGearPiece.getRarity(),
                                    theGearPiece.getStats(),
                                    theGearPiece.getLevel(),
                                    theGearPiece.getHealth(),
                                    theGearPiece.getDefense(),
                                    theGearPiece.getDamage(),
                                    theGearPiece.getBlockChance(),
                                    theGearPiece.getCritChance(),
                                    theGearPiece.getGoldWorth());

                            String theGearPieceString = theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)";
                            gearPieceRewardView.setText(theGearPieceString);
                            gearPieceRewardView.setVisibility(View.VISIBLE);

                            switch (gearPiece.getRarity()) {
                                case "common":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_common_quality);
                                    break;
                                case "uncommon":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_uncommon_quality);
                                    break;
                                case "rare":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_rare_quality);
                                    break;
                                case "epic":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_epic_quality);
                                    break;
                                case "legendary":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_legendary_quality);
                                    break;
                            }
                        } else if (gearPieceTypeChance > i && gearPieceTypeChance <= (i + 9) && gearPieceType.get(whichGearPieceType).equals("MainHand Shield")) {
                            theGearPiece = new GearPiece(
                                    gearPieceReward.getIsEquiped(),
                                    gearPieceType.get(whichGearPieceType),
                                    gearPieceRarity.get(whichGearPieceRarity - 1),
                                    gearPieceReward.getStats(),
                                    golem.getLevel(),
                                    0,
                                    gearPieceReward.getDefense() * whichGearPieceRarity * 2,
                                    0,
                                    gearPieceReward.getBlockChance(),
                                    gearPieceReward.getCritChance(),
                                    gearPieceReward.getGoldCost(),
                                    gearPieceReward.getGoldWorth() * whichGearPieceRarity,
                                    gearPieceReward.getOreCost());
                            theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));

                            db.insertData(
                                    theGearPiece.getIsEquiped(),
                                    theGearPiece.getName(),
                                    theGearPiece.getRarity(),
                                    theGearPiece.getStats(),
                                    theGearPiece.getLevel(),
                                    theGearPiece.getHealth(),
                                    theGearPiece.getDefense(),
                                    theGearPiece.getDamage(),
                                    theGearPiece.getBlockChance(),
                                    theGearPiece.getCritChance(),
                                    theGearPiece.getGoldWorth());

                            String theGearPieceString = theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)";
                            gearPieceRewardView.setText(theGearPieceString);
                            gearPieceRewardView.setVisibility(View.VISIBLE);

                            switch (gearPiece.getRarity()) {
                                case "common":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_common_quality);
                                    break;
                                case "uncommon":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_uncommon_quality);
                                    break;
                                case "rare":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_rare_quality);
                                    break;
                                case "epic":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_epic_quality);
                                    break;
                                case "legendary":
                                    gearPieceRewardView.setBackgroundResource(R.drawable.border_legendary_quality);
                                    break;
                            }
                        } else if (gearPieceTypeChance > i && gearPieceTypeChance <= (i + 9) && !gearPieceType.get(whichGearPieceType).equals("MainHand Sword") && !gearPieceType.get(whichGearPieceType).equals("OffHand Sword") && !gearPieceType.get(whichGearPieceType).equals("OffHand Shield")) {
                            if (gearPieceOffOrDefChance <= 50) {
                                theGearPiece = new GearPiece(
                                        gearPieceReward.getIsEquiped(),
                                        gearPieceType.get(whichGearPieceType),
                                        gearPieceRarity.get(whichGearPieceRarity - 1),
                                        gearPieceReward.getStats(),
                                        golem.getLevel(),
                                        gearPieceReward.getHealth() * whichGearPieceRarity,
                                        gearPieceReward.getDefense() * whichGearPieceRarity,
                                        0,
                                        gearPieceReward.getBlockChance(),
                                        gearPieceReward.getCritChance(),
                                        gearPieceReward.getGoldCost(),
                                        gearPieceReward.getGoldWorth() * whichGearPieceRarity,
                                        gearPieceReward.getOreCost());
                                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDefense\n+" + Math.round(theGearPiece.getDefense()));

                                db.insertData(
                                        theGearPiece.getIsEquiped(),
                                        theGearPiece.getName(),
                                        theGearPiece.getRarity(),
                                        theGearPiece.getStats(),
                                        theGearPiece.getLevel(),
                                        theGearPiece.getHealth(),
                                        theGearPiece.getDefense(),
                                        theGearPiece.getDamage(),
                                        theGearPiece.getBlockChance(),
                                        theGearPiece.getCritChance(),
                                        theGearPiece.getGoldWorth());

                                String theGearPieceString = theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Def)";
                                gearPieceRewardView.setText(theGearPieceString);
                                gearPieceRewardView.setVisibility(View.VISIBLE);

                                switch (gearPiece.getRarity()) {
                                    case "common":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_common_quality);
                                        break;
                                    case "uncommon":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_uncommon_quality);
                                        break;
                                    case "rare":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_rare_quality);
                                        break;
                                    case "epic":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_epic_quality);
                                        break;
                                    case "legendary":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_legendary_quality);
                                        break;
                                }
                            } else {
                                theGearPiece = new GearPiece(
                                        gearPieceReward.getIsEquiped(),
                                        gearPieceType.get(whichGearPieceType),
                                        gearPieceRarity.get(whichGearPieceRarity - 1),
                                        gearPieceReward.getStats(),
                                        golem.getLevel(),
                                        gearPieceReward.getHealth() * whichGearPieceRarity,
                                        0,
                                        gearPieceReward.getDamage() * whichGearPieceRarity,
                                        gearPieceReward.getBlockChance(),
                                        gearPieceReward.getCritChance(),
                                        gearPieceReward.getGoldCost(),
                                        gearPieceReward.getGoldWorth() * whichGearPieceRarity,
                                        gearPieceReward.getOreCost());
                                theGearPiece.setStats(theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)" + "\nHealth\n+" + Math.round(theGearPiece.getHealth()) + "\nDamage\n+" + Math.round(theGearPiece.getDamage()));

                                db.insertData(
                                        theGearPiece.getIsEquiped(),
                                        theGearPiece.getName(),
                                        theGearPiece.getRarity(),
                                        theGearPiece.getStats(),
                                        theGearPiece.getLevel(),
                                        theGearPiece.getHealth(),
                                        theGearPiece.getDefense(),
                                        theGearPiece.getDamage(),
                                        theGearPiece.getBlockChance(),
                                        theGearPiece.getCritChance(),
                                        theGearPiece.getGoldWorth());

                                String theGearPieceString = theGearPiece.getName() + "\nLevel " + Math.round(theGearPiece.getLevel()) + " (Off)";
                                gearPieceRewardView.setText(theGearPieceString);
                                gearPieceRewardView.setVisibility(View.VISIBLE);

                                switch (gearPiece.getRarity()) {
                                    case "common":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_common_quality);
                                        break;
                                    case "uncommon":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_uncommon_quality);
                                        break;
                                    case "rare":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_rare_quality);
                                        break;
                                    case "epic":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_epic_quality);
                                        break;
                                    case "legendary":
                                        gearPieceRewardView.setBackgroundResource(R.drawable.border_legendary_quality);
                                        break;
                                }
                            }
                        }
                        whichGearPieceType += 1;
                    }
                }
                gearPieceMaxRarityDropChance -= 1;
                whichGearPieceRarity += 1;
            }
            gearPieceRewardView.setGravity(Gravity.CENTER);
            gearPieceRewardView.setTextColor(Color.BLACK);
            gearPieceRewardView.setTypeface(Typeface.create("serif", Typeface.NORMAL));

            golemDefeatedGrid.setVisibility(View.VISIBLE);
            golemDefeated.setVisibility(View.VISIBLE);
            home.setVisibility(View.VISIBLE);
            Continue.setVisibility(View.VISIBLE);

            if (player.getXP() >= player.getXPToNextLevel()) {
                player.decreaseMaxHealth(player.getMaxHealthGearBonus());
                player.decreaseDefense(player.getDefenseGearBonus());
                player.decreaseDamage(player.getDamageGearBonus());
                player.levelUp();
                player.increaseMaxHealth(player.getMaxHealthGearBonus());
                player.increaseDefense(player.getDefenseGearBonus());
                player.increaseDamage(player.getDamageGearBonus());

                playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
                playerHealth.setText(playerHealthText);
            }
            if (golem.getLevel() == golem.getMaxLevel()) {
                golem.increaseMaxLevel();
                preferences.edit().putFloat("golemMaxLevel", golem.getMaxLevel()).apply();
            }
            player.setBattleDamage(player.getDamage());
            player.setBattleDefense(player.getDefense());

            chargeDurationCooldown.setVisibility(View.INVISIBLE);
            chargeImage.setImageAlpha(255);
            charge.setOnDurationCooldown(false);
            charge.refresh();

            mendDurationCooldown.setVisibility(View.INVISIBLE);
            mendImage.setImageAlpha(255);
            mend.setOnDurationCooldown(false);
            mend.refresh();

            defenseUpDurationCooldown.setVisibility(View.INVISIBLE);
            defenseUpImage.setImageAlpha(255);
            defenseUp.setOnDurationCooldown(false);
            defenseUp.refresh();

            damagePotionDurationCooldown.setVisibility(View.INVISIBLE);
            damagePotionImage.setImageAlpha(255);
            damagePotion.setOnDurationCooldown(false);
            damagePotion.refresh();

            healthPotionDurationCooldown.setVisibility(View.INVISIBLE);
            healthPotionImage.setImageAlpha(255);
            healthPotion.setOnDurationCooldown(false);
            healthPotion.refresh();

            preferences.edit().putFloat("level", player.getLevel()).apply();
            preferences.edit().putFloat("XP", player.getXP()).apply();
            preferences.edit().putFloat("XPToNextLevel", player.getXPToNextLevel()).apply();
            preferences.edit().putFloat("glory", player.getGlory()).apply();
            preferences.edit().putFloat("skillPoints", player.getSkillPoints()).apply();
            preferences.edit().putFloat("maxHealth", player.getMaxHealth()).apply();
            preferences.edit().putFloat("health", player.getHealth()).apply();
            preferences.edit().putFloat("defense", player.getDefense()).apply();
            preferences.edit().putFloat("damage", player.getDamage()).apply();
            preferences.edit().putFloat("critChance", player.getCritChance()).apply();
            preferences.edit().putFloat("gold", player.getGold()).apply();
            preferences.edit().putFloat("herbs", player.getHerbs()).apply();
            preferences.edit().putFloat("ores", player.getOres()).apply();
            preferences.edit().putFloat("soulDust", player.getSoulDust()).apply();
        }
    }

    public void home(View view) {
        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
    }

    public void Continue(View view) {
        golemDefeatedGrid.setVisibility(View.INVISIBLE);
        golemDefeated.setVisibility(View.INVISIBLE);
        home.setVisibility(View.INVISIBLE);
        Continue.setVisibility(View.INVISIBLE);
        gearPieceRewardView.setVisibility(View.INVISIBLE);

        newBattle();
    }

    public void checkPlayer() {
        if (Math.round(player.getHealth()) <= 0) {
            player.setAlive(false);
            player.resetBattleDefense();
            youAreDeadGrid.setVisibility(View.VISIBLE);
            youAreDead.setVisibility(View.VISIBLE);
            accept.setVisibility(View.VISIBLE);
        }
    }

    public void checkCharge() {
        if (player.isAlive() && golem.isAlive() && chargeDurationCooldown.getVisibility() == View.INVISIBLE) {
            if (player.getBattleDefense() >= golem.getDamage()) {
                player.setBattleDefense(golem.getDamage());
            }
            player.takeDamage(golem.getDamage() - player.getBattleDefense());
            playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
            playerHealth.setText(playerHealthText);
            preferences.edit().putFloat("health", player.getHealth()).apply();
        }
        if (player.isAlive() && golem.isAlive() && chargeDurationCooldown.getVisibility() == View.VISIBLE) {
            if (charge.getCurrentDuration() < 1 && player.isAlive() && golem.isAlive()) {
                if (player.getBattleDefense() >= golem.getDamage()) {
                    player.setBattleDefense(golem.getDamage());
                }
                player.takeDamage(golem.getDamage() - player.getBattleDefense());
                playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
                playerHealth.setText(playerHealthText);
            }
            if (charge.getCurrentDuration() > 0 && player.isAlive() && golem.isAlive()) {
                charge.tickDuration();
                chargeDurationCooldownText = "Duration: " + Math.round(charge.getCurrentDuration());
                chargeDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                chargeDurationCooldown.setText(chargeDurationCooldownText);
            }
            if (charge.getCurrentDuration() < 1 && player.isAlive() && golem.isAlive()) {
                charge.tickCooldown();
                chargeDurationCooldownText = "Cooldown: " + Math.round(charge.getCurrentCooldown() + 1);
                chargeDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                chargeDurationCooldown.setText(chargeDurationCooldownText);
            }
            if (charge.getCurrentCooldown() < 0 && player.isAlive() && golem.isAlive()) {
                chargeDurationCooldown.setVisibility(View.INVISIBLE);
                chargeImage.setImageAlpha(255);
                charge.setOnDurationCooldown(false);
                charge.refresh();
            }
        }
        preferences.edit().putFloat("health", player.getHealth()).apply();
        preferences.edit().putFloat("chargeCurrentDuration", charge.getCurrentDuration()).apply();
        preferences.edit().putFloat("chargeCurrentCooldown", charge.getCurrentCooldown()).apply();
        preferences.edit().putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();
    }

    public void checkMend() {
        if (mendDurationCooldown.getVisibility() == View.VISIBLE) {
            if (mend.getCurrentDuration() > 0 && player.isAlive() && golem.isAlive()) {
                mend.tickDuration();
                player.increaseHealth(player.getMaxHealth() * mend.getHealing() / 100);
                if (player.getHealth() > player.getMaxHealth()) {
                    player.setHealth(player.getMaxHealth());
                }
                playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
                mendDurationCooldownText = "Duration: " + Math.round(mend.getCurrentDuration());
                mendDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                mendDurationCooldown.setText(mendDurationCooldownText);
                playerHealth.setText(playerHealthText);
            }
            if (mend.getCurrentDuration() < 1 && player.isAlive() && golem.isAlive()) {
                mend.tickCooldown();
                mendDurationCooldownText = "Cooldown: " + Math.round(mend.getCurrentCooldown() + 1);
                mendDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                mendDurationCooldown.setText(mendDurationCooldownText);
            }
            if (mend.getCurrentCooldown() < 0 && player.isAlive() && golem.isAlive()) {
                mendDurationCooldown.setVisibility(View.INVISIBLE);
                mendImage.setImageAlpha(255);
                mend.setOnDurationCooldown(false);
                mend.refresh();
            }
            preferences.edit().putFloat("health", player.getHealth()).apply();
            preferences.edit().putFloat("mendCurrentDuration", mend.getCurrentDuration()).apply();
            preferences.edit().putFloat("mendCurrentCooldown", mend.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();
        }
    }

    public void checkDefenseUp() {
        if (defenseUpDurationCooldown.getVisibility() == View.VISIBLE) {
            if (defenseUp.getCurrentDuration() > 0 && player.isAlive() && golem.isAlive()) {
                defenseUp.tickDuration();
                player.increaseBattleDefense(player.getDefense() * (defenseUp.getDefense() / 100));

                playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
                defenseUpDurationCooldownText = "Duration: " + Math.round(defenseUp.getCurrentDuration());
                defenseUpDurationCooldown.setBackgroundResource(R.drawable.border_quest);
                defenseUpDurationCooldown.setText(defenseUpDurationCooldownText);
                playerHealth.setText(playerHealthText);
            }
            if (defenseUp.getCurrentDuration() < 1 && player.isAlive() && golem.isAlive()) {
                defenseUp.tickCooldown();
                defenseUpDurationCooldownText = "Cooldown: " + Math.round(defenseUp.getCurrentCooldown() + 1);
                defenseUpDurationCooldown.setBackgroundResource(R.drawable.border_level_xp);
                defenseUpDurationCooldown.setText(defenseUpDurationCooldownText);
            }
            if (defenseUp.getCurrentCooldown() < 0 && player.isAlive() && golem.isAlive()) {
                defenseUpDurationCooldown.setVisibility(View.INVISIBLE);
                defenseUpImage.setImageAlpha(255);
                defenseUp.setOnDurationCooldown(false);
                defenseUp.refresh();
            }
        }
        preferences.edit().putFloat("health", player.getHealth()).apply();
        preferences.edit().putFloat("defenseUpCurrentDuration", defenseUp.getCurrentDuration()).apply();
        preferences.edit().putFloat("defenseUpCurrentCooldown", defenseUp.getCurrentCooldown()).apply();
        preferences.edit().putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();
    }

    public void useStrike(View view) {
        if (player.isAlive() && golem.isAlive()) {
            golem.takeDamage(player.getBattleDamage() * strike.getDamage() / 100);
            golemHealthText = "  Health: " + Math.round(golem.getHealth()) + " / " + Math.round((golem.getMaxHealth())) + "  ";
            golemHealth.setText(golemHealthText);
            checkgolem();
            checkDamagePotion();
            checkHealthPotion();
            checkDefenseUp();
            checkCharge();
            checkMend();
            checkPlayer();
            preferences.edit().putFloat("golemHealth", golem.getHealth()).apply();
        }
    }

    public void useCharge(View view) {
        if (player.isAlive() && golem.isAlive() && chargeDurationCooldown.getVisibility() == View.INVISIBLE) {
            golem.takeDamage(player.getBattleDamage() * charge.getDamage() / 100);
            golemHealthText = "  Health: " + Math.round(golem.getHealth()) + " / " + Math.round(golem.getMaxHealth()) + "  ";
            golemHealth.setText(golemHealthText);
            chargeImage.setImageAlpha(125);
            chargeDurationCooldown.setVisibility(View.VISIBLE);
            charge.setOnDurationCooldown(true);
            checkgolem();
            checkDamagePotion();
            checkHealthPotion();
            checkDefenseUp();
            checkCharge();
            checkMend();
            checkPlayer();
            preferences.edit().putFloat("golemHealth", golem.getHealth()).apply();
            preferences.edit().putBoolean("chargeIsOnDurationCooldown", charge.isOnDurationCooldown()).apply();
        }
    }

    public void useMend(View view) {
        if (player.isAlive() && golem.isAlive() && mendDurationCooldown.getVisibility() == View.INVISIBLE) {
            mendImage.setImageAlpha(125);
            mendDurationCooldown.setVisibility(View.VISIBLE);
            mend.setOnDurationCooldown(true);
            checkgolem();
            checkDamagePotion();
            checkHealthPotion();
            checkDefenseUp();
            checkCharge();
            checkMend();
            checkPlayer();
            preferences.edit().putBoolean("mendIsOnDurationCooldown", mend.isOnDurationCooldown()).apply();
        }
    }

    public void useDefenseUp(View view) {
        if (player.isAlive() && golem.isAlive() && defenseUpDurationCooldown.getVisibility() == View.INVISIBLE) {
            defenseUpImage.setImageAlpha(125);
            defenseUpDurationCooldown.setVisibility(View.VISIBLE);
            defenseUp.setOnDurationCooldown(true);
            checkgolem();
            checkDamagePotion();
            checkHealthPotion();
            checkDefenseUp();
            checkCharge();
            checkMend();
            checkPlayer();
            preferences.edit().putBoolean("defenseUpIsOnDurationCooldown", defenseUp.isOnDurationCooldown()).apply();
        }
    }

    public void useHealthPotion(View view) {
        if (healthPotion.getAmount() > 0 && player.getHealth() != player.getMaxHealth() && player.isAlive() && golem.isAlive() && healthPotionDurationCooldown.getVisibility() == View.INVISIBLE) {
            healthPotionImage.setImageAlpha(125);
            healthPotionDurationCooldown.setVisibility(View.VISIBLE);
            healthPotion.setOnDurationCooldown(true);
            healthPotion.decreaseAmount();
            player.increaseHealth(Math.round(healthPotion.getEffect()));
            healthPotion.setOnDurationCooldown(true);
            if(player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
            playerHealthText = "  Health: " + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + "  ";
            healthPotionAmountText = "Amount: " + Math.round(healthPotion.getAmount());
            healthPotionDurationCooldownText = "Duration: " + Math.round(healthPotion.getCurrentDuration());

            playerHealth.setText(playerHealthText);
            healthPotionAmount.setText(healthPotionAmountText);
            healthPotionDurationCooldown.setText(healthPotionDurationCooldownText);

            preferences.edit().putFloat("health", player.getHealth()).apply();
            preferences.edit().putFloat("healthPotionAmount", healthPotion.getAmount()).apply();
            preferences.edit().putFloat("healthPotionDuration", charge.getDuration()).apply();
            preferences.edit().putFloat("healthPotionCooldown", charge.getCooldown()).apply();
            preferences.edit().putBoolean("healthPotionIsOnDurationCooldown", healthPotion.isOnDurationCooldown()).apply();
        }
    }

    public void useDamagePotion(View view) {
        if (damagePotion.getAmount() > 0 && player.isAlive() && golem.isAlive() && damagePotionDurationCooldown.getVisibility() == View.INVISIBLE) {
            damagePotionImage.setImageAlpha(125);
            damagePotionDurationCooldown.setVisibility(View.VISIBLE);
            damagePotion.setOnDurationCooldown(true);
            damagePotion.decreaseAmount();
            player.setBattleDamage(player.getDamage() + Math.round(damagePotion.getEffect()));

            damagePotionAmountText = "Amount: " + Math.round(damagePotion.getAmount());
            damagePotionDurationCooldownText = "Duration: " + Math.round(damagePotion.getCurrentDuration());

            damagePotionAmount.setText(damagePotionAmountText);
            damagePotionDurationCooldown.setText(damagePotionDurationCooldownText);

            preferences.edit().putFloat("damagePotionAmount", damagePotion.getAmount()).apply();
            preferences.edit().putFloat("damagePotionCurrentDuration", charge.getCurrentDuration()).apply();
            preferences.edit().putFloat("damagePotionCurrentCooldown", charge.getCurrentCooldown()).apply();
            preferences.edit().putBoolean("damagePotionIsOnDurationCooldown", damagePotion.isOnDurationCooldown()).apply();
        }
    }

    public void newBattle() {
        player.setBattleDamage(player.getDamage());
        player.setBattleDefense(player.getDefense());

        golem.setAlive(true);
        golem.setHealth(golem.getMaxHealth());

        chargeDurationCooldown.setVisibility(View.INVISIBLE);
        chargeImage.setImageAlpha(255);
        charge.setOnDurationCooldown(false);
        charge.refresh();

        mendDurationCooldown.setVisibility(View.INVISIBLE);
        mendImage.setImageAlpha(255);
        mend.setOnDurationCooldown(false);
        mend.refresh();

        defenseUpDurationCooldown.setVisibility(View.INVISIBLE);
        defenseUpImage.setImageAlpha(255);
        defenseUp.setOnDurationCooldown(false);
        defenseUp.refresh();

        damagePotionDurationCooldown.setVisibility(View.INVISIBLE);
        damagePotionImage.setImageAlpha(255);
        damagePotion.setOnDurationCooldown(false);
        damagePotion.refresh();

        healthPotionDurationCooldown.setVisibility(View.INVISIBLE);
        healthPotionImage.setImageAlpha(255);
        healthPotion.setOnDurationCooldown(false);
        healthPotion.refresh();

        golemHealthText = "  Health: " + Math.round(golem.getHealth()) + " / " + Math.round(golem.getMaxHealth()) + "  ";
        golemHealth.setText(golemHealthText);
    }
}