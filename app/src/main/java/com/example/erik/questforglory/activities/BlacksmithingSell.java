package com.example.erik.questforglory.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.erik.questforglory.classes.Monster;
import com.example.erik.questforglory.classes.Player;
import com.example.erik.questforglory.classes.Potion;
import com.example.erik.questforglory.classes.Skill;
import com.example.erik.questforglory.helpers.DatabaseHelper;
import com.example.erik.questforglory.classes.GearPiece;
import com.example.erik.questforglory.R;

import java.util.ArrayList;

public class BlacksmithingSell extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences preferences;
    Intent intent;
    Bundle bundle;
    ConstraintLayout scrollConstraintLayout;
    ConstraintSet constraintSet;
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
    ArrayList<GearPiece> gearInventory;
    ArrayList<TextView> gearInventoryViews;
    TextView level;
    TextView XP;
    TextView ores;
    TextView gold;
    TextView sellGear;
    TextView gearInfo;
    String levelText;
    String XPText;
    String gearInfoText;
    String sellGearText;
    String oresText;
    String goldText;
    int gearInventoryId;
    int gearEquipedTempId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGearInventory();
    }
    public void initGearInventory() {
        setContentView(R.layout.activity_blacksmithing_sell);

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

        scrollConstraintLayout = findViewById(R.id.constGearInventory);
        constraintSet = new ConstraintSet();

        gearInventory = new ArrayList <>();
        gearInventoryViews = new ArrayList<>();

        level = findViewById(R.id.level);
        XP = findViewById(R.id.XP);
        ores = findViewById(R.id.ores);
        gold = findViewById(R.id.gold);
        gearInfo = findViewById(R.id.gearInfo);
        sellGear = findViewById(R.id.sellGear);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        oresText = " Ores: " + Math.round(player.getOres());
        goldText = " Gold: " + Math.round(player.getGold());
        sellGearText = "Select A Gear Piece";

        level.setText(levelText);
        XP.setText(XPText);
        ores.setText(oresText);
        gold.setText(goldText);
        sellGear.setText(sellGearText);

        Cursor res = db.getAllData();
        scrollConstraintLayout.removeAllViews();
        gearInventoryViews.clear();
        gearInventory.clear();
        gearInventoryId = 0;
        gearEquipedTempId = 1;
        while (res.moveToNext()) {
            GearPiece gearPiece = new GearPiece(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getFloat(5), res.getFloat(6), res.getFloat(7), res.getFloat(8), res.getFloat(9), res.getFloat(10), 0, res.getFloat(11), 0);
            TextView gearPieceView = new TextView(this);
            String gearPieceString;

            if (gearPiece.getDefense() == 0) {
                gearPieceString = gearPiece.getName() + "\nLevel " + Math.round(gearPiece.getLevel()) + " (Off)";
            } else {
                gearPieceString = gearPiece.getName() + "\nLevel " + Math.round(gearPiece.getLevel()) + " (Def)";
            }
            gearPieceView.setText(gearPieceString);
            gearPieceView.setGravity(Gravity.CENTER);
            gearPieceView.setTextColor(Color.BLACK);
            gearPieceView.setTypeface(Typeface.create("serif", Typeface.NORMAL));
            gearPieceView.setId(gearInventoryId);

            switch (gearPiece.getRarity()) {
                case "common":
                    gearPieceView.setBackgroundResource(R.drawable.border_common_quality);
                    break;
                case "uncommon":
                    gearPieceView.setBackgroundResource(R.drawable.border_uncommon_quality);
                    break;
                case "rare":
                    gearPieceView.setBackgroundResource(R.drawable.border_rare_quality);
                    break;
                case "epic":
                    gearPieceView.setBackgroundResource(R.drawable.border_epic_quality);
                    break;
                case "legendary":
                    gearPieceView.setBackgroundResource(R.drawable.border_legendary_quality);
                    break;
            }
            gearInventory.add(gearPiece);
            gearInventoryViews.add(gearPieceView);

            if (gearPiece.getIsEquiped() == 0) {
                scrollConstraintLayout.addView(gearPieceView);
            }
            gearPieceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sellGear.setId(view.getId());

                    sellGearText = "Sell for\n" + Math.round(gearInventory.get(view.getId()).getGoldWorth()) + " Gold";

                    if (gearInventory.get(view.getId()).getHealth() == 0) {
                        gearInfoText = gearInventory.get(view.getId()).getName() + "\nLevel " + Math.round(gearInventory.get(view.getId()).getLevel()) + " (Off)" + "\n+" + Math.round(gearInventory.get(view.getId()).getDamage()) + " Damage";
                    } else if (gearInventory.get(view.getId()).getDefense() == 0) {
                        gearInfoText = gearInventory.get(view.getId()).getName() + "\nLevel " + Math.round(gearInventory.get(view.getId()).getLevel()) + " (Off)" + "\n+" + Math.round(gearInventory.get(view.getId()).getHealth()) + " Health" + "\n+" + Math.round(gearInventory.get(view.getId()).getDamage()) + " Damage";
                    } else {
                        gearInfoText = gearInventory.get(view.getId()).getName() + "\nLevel " + Math.round(gearInventory.get(view.getId()).getLevel()) + " (Def)" + "\n+" + Math.round(gearInventory.get(view.getId()).getHealth()) + " Health" + "\n+" + Math.round(gearInventory.get(view.getId()).getDefense()) + " Defense";
                    }
                    if (gearInventory.get(view.getId()).getRarity().equals("common")) {
                        gearInfo.setBackgroundResource(R.drawable.border_common_quality);
                    } else if (gearInventory.get(view.getId()).getRarity().equals("uncommon")) {
                        gearInfo.setBackgroundResource(R.drawable.border_uncommon_quality);
                    } else if (gearInventory.get(view.getId()).getRarity().equals("rare")) {
                        gearInfo.setBackgroundResource(R.drawable.border_rare_quality);
                    } else if (gearInventory.get(view.getId()).getRarity().equals("epic")) {
                        gearInfo.setBackgroundResource(R.drawable.border_epic_quality);
                    } else if (gearInventory.get(view.getId()).getRarity().equals("legendary")) {
                        gearInfo.setBackgroundResource(R.drawable.border_legendary_quality);
                    }
                    sellGear.setText(sellGearText);
                    gearInfo.setText(gearInfoText);
                }
            });
            if (gearPiece.getIsEquiped() == 0) {
                if (gearInventoryId == 0 || gearInventory.get(gearInventoryId - gearEquipedTempId).getIsEquiped() == 1) {
                    constraintSet.constrainHeight(gearInventoryViews.get(gearInventoryId).getId(), 150);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
                } else if (gearInventoryId > 0) {
                    constraintSet.constrainHeight(gearInventoryViews.get(gearInventoryId).getId(), 150);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), ConstraintSet.TOP, gearInventoryViews.get(gearInventoryId - gearEquipedTempId).getId(), ConstraintSet.TOP, 150);
                }
                constraintSet.applyTo(scrollConstraintLayout);
                gearEquipedTempId = 1;
            } else if (gearPiece.getIsEquiped() == 1) {
                if (gearInventoryId != 0) {
                    gearEquipedTempId++;
                }
            }
            gearInventoryId++;
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
        intent = new Intent(this, Blacksmithing.class);
        saveObjectsInBundle();
    }

    public void sell(View view) {
        if (view.getId() <= gearInventory.size()) {
            player.increaseGold(gearInventory.get(view.getId()).getGoldWorth());
            db.deleteData(view.getId() + 1 + "");
            goldText = " Gold: " + Math.round(player.getGold());
            sellGearText = "Sell for\n? Gold";
            gearInfoText = "";
            gold.setText(goldText);
            sellGear.setText(sellGearText);
            gearInfo.setText(gearInfoText);
            gearInfo.setBackgroundResource(R.drawable.border_the_forest);
            initGearInventory();
            preferences.edit().putFloat("gold", player.getGold()).apply();
        }
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