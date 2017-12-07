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
import com.example.erik.questforglory.classes.GearPiece;
import com.example.erik.questforglory.R;

public class Alchemy extends AppCompatActivity {
    
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
    TextView herbs;
    TextView gold;
    TextView healthPotionInfo;
    TextView healthPotionUpgrade;
    TextView healthPotionCraft;
    TextView healthPotionSell;
    TextView healthPotionBuy;
    TextView damagePotionInfo;
    TextView damagePotionUpgrade;
    TextView damagePotionCraft;
    TextView damagePotionSell;
    TextView damagePotionBuy;
    String levelText;
    String XPText;
    String herbsText;
    String goldText;
    String healthPotionText;
    String healthPotionUpgradeText;
    String healthPotionCraftText;
    String healthPotionSellText;
    String healthPotionBuyText;
    String damagePotionText;
    String damagePotionUpgradeText;
    String damagePotionCraftText;
    String damagePotionSellText;
    String damagePotionBuyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alchemy);

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
        herbs = findViewById(R.id.herbs);
        gold = findViewById(R.id.gold);
        healthPotionInfo = findViewById(R.id.healthPotionInfo);
        healthPotionUpgrade = findViewById(R.id.healthPotionUpgrade);
        healthPotionCraft = findViewById(R.id.healthPotionCraft);
        healthPotionSell = findViewById(R.id.healthPotionSell);
        healthPotionBuy = findViewById(R.id.healthPotionBuy);
        damagePotionInfo = findViewById(R.id.damagePotionInfo);
        damagePotionUpgrade = findViewById(R.id.damagePotionUpgrade);
        damagePotionCraft = findViewById(R.id.damagePotionCraft);
        damagePotionSell = findViewById(R.id.damagePotionSell);
        damagePotionBuy = findViewById(R.id.damagePotionBuy);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        herbsText = " Herbs: " + Math.round(player.getHerbs());
        goldText = " Gold: " + Math.round(player.getGold());
        healthPotionText = "Health Potion Level " + Math.round(healthPotion.getLevel()) + "\nRestore +" + Math.round(healthPotion.getEffect()) + " Health\nAmount: " + Math.round(healthPotion.getAmount());
        healthPotionUpgradeText = "Upgrade for\n" + Math.round(healthPotion.getUpgradeCost()) + " Gold";
        healthPotionCraftText = "Craft for\n" + Math.round(healthPotion.getHerbCost()) + " Herbs";
        healthPotionSellText = "Sell for\n" + Math.round(healthPotion.getGoldWorth()) + " Gold";
        healthPotionBuyText = "Buy for\n" + Math.round(healthPotion.getGoldCost()) + " Gold";

        damagePotionText = "Damage Potion Level " + Math.round(damagePotion.getLevel()) + "\nDeal +" + Math.round(damagePotion.getEffect()) + " Damage\nfor 5 turns\nAmount: " + Math.round(damagePotion.getAmount());
        damagePotionUpgradeText = "Upgrade for\n" + Math.round(damagePotion.getUpgradeCost()) + " Gold";
        damagePotionCraftText = "Craft for\n" + Math.round(damagePotion.getHerbCost()) + " Herbs";
        damagePotionSellText = "Sell for\n" + Math.round(damagePotion.getGoldWorth()) + " Gold";
        damagePotionBuyText = "Buy for\n" + Math.round(damagePotion.getGoldCost()) + " Gold";

        level.setText(levelText);
        XP.setText(XPText);
        herbs.setText(herbsText);
        gold.setText(goldText);
        healthPotionInfo.setText(healthPotionText);
        healthPotionUpgrade.setText(healthPotionUpgradeText);
        healthPotionCraft.setText(healthPotionCraftText);
        healthPotionSell.setText(healthPotionSellText);
        healthPotionBuy.setText(healthPotionBuyText);
        damagePotionInfo.setText(damagePotionText);
        damagePotionUpgrade.setText(damagePotionUpgradeText);
        damagePotionCraft.setText(damagePotionCraftText);
        damagePotionSell.setText(damagePotionSellText);
        damagePotionBuy.setText(damagePotionBuyText);
    }

    public void upgradeHealthPotion(View view) {
        if (Math.round(player.getGold()) >= Math.round(healthPotion.getUpgradeCost())) {
            player.decreaseGold(Math.round(healthPotion.getUpgradeCost()));
            healthPotion.upgrade();

            preferences.edit().putFloat("gold", player.getGold()).apply();
            preferences.edit().putFloat("healthPotionLevel", healthPotion.getLevel()).apply();
            preferences.edit().putFloat("healthPotionUpgradeCost", healthPotion.getUpgradeCost()).apply();
            preferences.edit().putFloat("healthPotionGoldCost", healthPotion.getGoldCost()).apply();
            preferences.edit().putFloat("healthPotionGoldWorth", healthPotion.getGoldWorth()).apply();
            preferences.edit().putFloat("healthPotionHerbsCost", healthPotion.getHerbCost()).apply();
            preferences.edit().putFloat("healthPotionEffect", healthPotion.getEffect()).apply();

            goldText = " Gold: " + Math.round(player.getGold());
            healthPotionText = "Health Potion Level " + Math.round(healthPotion.getLevel()) + "\nRestore +" + Math.round(healthPotion.getEffect()) + " Health\nAmount: " + Math.round(healthPotion.getAmount());
            healthPotionUpgradeText = "Upgrade for\n" + Math.round(healthPotion.getUpgradeCost()) + " Gold";
            healthPotionCraftText = "Craft for\n" + Math.round(healthPotion.getHerbCost()) + " Herbs";
            healthPotionSellText = "Sell for\n" + Math.round(healthPotion.getGoldWorth()) + " Gold";
            healthPotionBuyText = "Buy for\n" + Math.round(healthPotion.getGoldCost()) + " Gold";

            gold.setText(goldText);
            healthPotionInfo.setText(healthPotionText);
            healthPotionUpgrade.setText(healthPotionUpgradeText);
            healthPotionCraft.setText(healthPotionCraftText);
            healthPotionSell.setText(healthPotionSellText);
            healthPotionBuy.setText(healthPotionBuyText);
        }
    }

    public void buyHealthPotionForGold(View view) {
        if (Math.round(player.getGold()) >= Math.round(healthPotion.getGoldCost())) {
            player.decreaseGold(Math.round(healthPotion.getGoldCost()));
            healthPotion.increaseAmount();

            preferences.edit().putFloat("gold", player.getGold()).apply();
            preferences.edit().putFloat("healthPotionAmount", healthPotion.getAmount()).apply();

            goldText = " Gold: " + Math.round(player.getGold());
            healthPotionText = "Health Potion Level " + Math.round(healthPotion.getLevel()) + "\nRestore +" + Math.round(healthPotion.getEffect()) + " Health\nAmount: " + Math.round(healthPotion.getAmount());
            healthPotionUpgradeText = "Upgrade for\n" + Math.round(healthPotion.getUpgradeCost()) + " Gold";

            gold.setText(goldText);
            healthPotionInfo.setText(healthPotionText);
            healthPotionUpgrade.setText(healthPotionUpgradeText);
        }
    }

    public void sellHealthPotionForGold(View view) {
        if (Math.round(healthPotion.getAmount()) > 0) {
            player.increaseGold(Math.round(healthPotion.getGoldWorth()));
            healthPotion.decreaseAmount();

            preferences.edit().putFloat("gold", player.getGold()).apply();
            preferences.edit().putFloat("healthPotionAmount", healthPotion.getAmount()).apply();

            goldText = " Gold: " + Math.round(player.getGold());
            healthPotionText = "Health Potion Level " + Math.round(healthPotion.getLevel()) + "\nRestore +" + Math.round(healthPotion.getEffect()) + " Health\nAmount: " + Math.round(healthPotion.getAmount());
            healthPotionUpgradeText = "Upgrade for\n" + Math.round(healthPotion.getUpgradeCost()) + " Gold";

            gold.setText(goldText);
            healthPotionInfo.setText(healthPotionText);
            healthPotionUpgrade.setText(healthPotionUpgradeText);
        }
    }

    public void buyHealthPotionForHerbs(View view) {
        if (Math.round(player.getHerbs()) >= Math.round(healthPotion.getHerbCost())) {
            player.decreaseHerbs(Math.round(healthPotion.getHerbCost()));
            healthPotion.increaseAmount();

            preferences.edit().putFloat("herbs", player.getHerbs()).apply();
            preferences.edit().putFloat("healthPotionAmount", healthPotion.getAmount()).apply();

            herbsText = " Herbs: " + Math.round(player.getHerbs());
            healthPotionText = "Health Potion Level " + Math.round(healthPotion.getLevel()) + "\nRestore +" + Math.round(healthPotion.getEffect()) + " Health\nAmount: " + Math.round(healthPotion.getAmount());
            healthPotionUpgradeText = "Upgrade for\n" + Math.round(healthPotion.getUpgradeCost()) + " Gold";

            herbs.setText(herbsText);
            healthPotionInfo.setText(healthPotionText);
            healthPotionUpgrade.setText(healthPotionUpgradeText);
        }
    }

    public void upgradeDamagePotion(View view) {
        if (Math.round(player.getGold()) >= Math.round(damagePotion.getUpgradeCost())) {
            player.decreaseGold(Math.round(damagePotion.getUpgradeCost()));
            damagePotion.upgrade();

            preferences.edit().putFloat("gold", player.getGold()).apply();
            preferences.edit().putFloat("damagePotionLevel", damagePotion.getLevel()).apply();
            preferences.edit().putFloat("damagePotionUpgradeCost", damagePotion.getUpgradeCost()).apply();
            preferences.edit().putFloat("damagePotionGoldCost", damagePotion.getGoldCost()).apply();
            preferences.edit().putFloat("damagePotionGoldWorth", damagePotion.getGoldWorth()).apply();
            preferences.edit().putFloat("damagePotionHerbsCost", damagePotion.getHerbCost()).apply();
            preferences.edit().putFloat("damagePotionEffect", damagePotion.getEffect()).apply();

            goldText = " Gold: " + Math.round(player.getGold());
            damagePotionText = "Damage Potion Level " + Math.round(damagePotion.getLevel()) + "\nDeal +" + Math.round(damagePotion.getEffect()) + " Damage\nfor 5 turns\nAmount: " + Math.round(damagePotion.getAmount());
            damagePotionUpgradeText = "Upgrade for\n" + Math.round(damagePotion.getUpgradeCost()) + " Gold";
            damagePotionCraftText = "Craft for\n" + Math.round(damagePotion.getHerbCost()) + " Herbs";
            damagePotionSellText = "Sell for\n" + Math.round(damagePotion.getGoldWorth()) + " Gold";
            damagePotionBuyText = "Buy for\n" + Math.round(damagePotion.getGoldCost()) + " Gold";

            gold.setText(goldText);
            damagePotionInfo.setText(damagePotionText);
            damagePotionUpgrade.setText(damagePotionUpgradeText);
            damagePotionCraft.setText(damagePotionCraftText);
            damagePotionSell.setText(damagePotionSellText);
            damagePotionBuy.setText(damagePotionBuyText);
        }
    }

    public void buyDamagePotionForGold(View view) {
        if (Math.round(player.getGold()) >= Math.round(damagePotion.getGoldCost())) {
            player.decreaseGold(Math.round(damagePotion.getGoldCost()));
            damagePotion.increaseAmount();

            preferences.edit().putFloat("gold", player.getGold()).apply();
            preferences.edit().putFloat("damagePotionAmount", damagePotion.getAmount()).apply();

            goldText = " Gold: " + Math.round(player.getGold());
            damagePotionText = "Damage Potion Level " + Math.round(damagePotion.getLevel()) + "\nDeal +" + Math.round(damagePotion.getEffect()) + " Damage\nfor 5 turns\nAmount: " + Math.round(damagePotion.getAmount());
            damagePotionUpgradeText = "Upgrade for\n" + Math.round(damagePotion.getUpgradeCost()) + " Gold";

            gold.setText(goldText);
            damagePotionInfo.setText(damagePotionText);
            damagePotionUpgrade.setText(damagePotionUpgradeText);
        }
    }

    public void sellDamagePotionForGold(View view) {
        if (Math.round(damagePotion.getAmount()) > 0) {
            player.increaseGold(Math.round(damagePotion.getGoldWorth()));
            damagePotion.decreaseAmount();

            preferences.edit().putFloat("gold", player.getGold()).apply();
            preferences.edit().putFloat("damagePotionAmount", damagePotion.getAmount()).apply();

            goldText = " Gold: " + Math.round(player.getGold());
            damagePotionText = "Damage Potion Level " + Math.round(damagePotion.getLevel()) + "\nDeal +" + Math.round(damagePotion.getEffect()) + " Damage\nfor 5 turns\nAmount: " + Math.round(damagePotion.getAmount());
            damagePotionUpgradeText = "Upgrade for\n" + Math.round(damagePotion.getUpgradeCost()) + " Gold";

            gold.setText(goldText);
            damagePotionInfo.setText(damagePotionText);
            damagePotionUpgrade.setText(damagePotionUpgradeText);
        }
    }

    public void buyDamagePotionForHerbs(View view) {
        if (player.getHerbs() >= Math.round(damagePotion.getHerbCost())) {
            player.decreaseHerbs(Math.round(damagePotion.getHerbCost()));
            damagePotion.increaseAmount();

            preferences.edit().putFloat("herbs", player.getHerbs()).apply();
            preferences.edit().putFloat("damagePotionAmount", damagePotion.getAmount()).apply();

            herbsText = " Herbs: " + Math.round(player.getHerbs());
            damagePotionText = "Damage Potion Level " + Math.round(damagePotion.getLevel()) + "\nDeal +" + Math.round(damagePotion.getEffect()) + " Damage\nfor 5 turns\nAmount: " + Math.round(damagePotion.getAmount());
            damagePotionUpgradeText = "Upgrade for\n" + Math.round(damagePotion.getUpgradeCost()) + " Gold";

            herbs.setText(herbsText);
            damagePotionInfo.setText(damagePotionText);
            damagePotionUpgrade.setText(damagePotionUpgradeText);
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
        intent = new Intent(this, Kingdom.class);
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