package com.example.erik.questforglory.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.erik.questforglory.classes.Monster;
import com.example.erik.questforglory.classes.Player;
import com.example.erik.questforglory.classes.Potion;
import com.example.erik.questforglory.classes.Skill;
import com.example.erik.questforglory.helpers.DatabaseHelper;
import com.example.erik.questforglory.classes.GearPiece;
import com.example.erik.questforglory.R;

import java.util.ArrayList;

public class Gear extends AppCompatActivity {
    DatabaseHelper db;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Intent intent;
    Bundle bundle;
    ConstraintLayout mainConstraintLayout;
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
    ArrayList<TextView> gearEquipedViews;
    ArrayList<String> gearSorts;
    ArrayList<TextView> gearSlots;
    TextView level;
    TextView XP;
    TextView maxHealth;
    TextView defense;
    TextView damage;
    TextView blockChance;
    TextView critChance;
    TextView gearInfo;
    TextView equipOrUnequip;
    TextView mainHand;
    TextView offHand;
    TextView head;
    TextView shoulders;
    TextView chest;
    TextView wrists;
    TextView hands;
    TextView waist;
    TextView legs;
    TextView feet;
    String levelText;
    String XPText;
    String maxHealthText;
    String defenseText;
    String damageText;
    String blockChanceText;
    String critChanceText;
    String gearInfoText;
    String equipOrUnequipText;
    int gearInventoryId;
    int gearEquipedTempId;

    DisplayMetrics displayMetrics;
    WindowManager wm;
    int screenHeight;
    long gearPieceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayMetrics = new DisplayMetrics();
        wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        gearPieceHeight = Math.round(screenHeight * 0.077) - 7;

        gearSorts = new ArrayList<>();
        gearSorts.add("Helm");
        gearSorts.add("Pauldrons");
        gearSorts.add("Chestplate");
        gearSorts.add("Bracers");
        gearSorts.add("MainHand Sword");
        gearSorts.add("OffHand Sword");
        gearSorts.add("OffHand Shield");
        gearSorts.add("Gauntlets");
        gearSorts.add("Belt");
        gearSorts.add("Legplates");
        gearSorts.add("Sabatons");

        initGearInventory();
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
    public void initGearInventory() {
        setContentView(R.layout.activity_gear);

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

        mainConstraintLayout = (ConstraintLayout) findViewById(R.id.mainConstraint);
        scrollConstraintLayout = (ConstraintLayout) findViewById(R.id.constGearInventory);
        constraintSet = new ConstraintSet();

        gearInventory = new ArrayList<GearPiece>();
        gearInventoryViews = new ArrayList<TextView>();
        gearEquipedViews = new ArrayList<TextView>();

        level = (TextView) findViewById(R.id.level);
        XP = (TextView) findViewById(R.id.XP);
        maxHealth = (TextView) findViewById(R.id.maxHealth);
        defense = (TextView) findViewById(R.id.defense);
        damage = (TextView) findViewById(R.id.damage);
        blockChance = (TextView) findViewById(R.id.blockChance);
        critChance = (TextView) findViewById(R.id.critChance);
        gearInfo = (TextView) findViewById(R.id.gearInfo);
        equipOrUnequip = (TextView) findViewById(R.id.equipOrUnequip);
        head = (TextView) findViewById(R.id.head);
        shoulders = (TextView) findViewById(R.id.shoulders);
        chest = (TextView) findViewById(R.id.chest);
        wrists = (TextView) findViewById(R.id.wrists);
        mainHand = (TextView) findViewById(R.id.mainHand);
        offHand = (TextView) findViewById(R.id.offHand);
        hands = (TextView) findViewById(R.id.hands);
        waist = (TextView) findViewById(R.id.waist);
        legs = (TextView) findViewById(R.id.legs);
        feet = (TextView) findViewById(R.id.feet);

        gearSlots = new ArrayList<>();
        gearSlots.add(head);
        gearSlots.add(shoulders);
        gearSlots.add(chest);
        gearSlots.add(wrists);
        gearSlots.add(mainHand);
        gearSlots.add(offHand);
        gearSlots.add(hands);
        gearSlots.add(waist);
        gearSlots.add(legs);
        gearSlots.add(feet);

        levelText = "Level " + Math.round(player.getLevel());
        XPText = "XP " + Math.round(player.getXP()) + " / " + Math.round(player.getXPToNextLevel());
        maxHealthText = "Max Health\n" + Math.round(player.getMaxHealth());
        defenseText = "Defense\n" + Math.round(player.getDefense());
        damageText = "Damage\n" + Math.round(player.getDamage());
        blockChanceText = "Block Chance\n" + Math.round(player.getBlockChance()) + "%";
        critChanceText = "Crit Chance\n" + Math.round(player.getCritChance()) + "%";
        equipOrUnequipText = "Select A Gear Piece";

        level.setText(levelText);
        XP.setText(XPText);
        maxHealth.setText(maxHealthText);
        defense.setText(defenseText);
        damage.setText(damageText);
        blockChance.setText(blockChanceText);
        critChance.setText(critChanceText);
        equipOrUnequip.setText(equipOrUnequipText);

        Cursor res = db.getAllData();
        scrollConstraintLayout.removeAllViews();
        gearInventoryViews.clear();
        gearEquipedViews.clear();
        gearInventory.clear();
        gearInventoryId = 0;
        gearEquipedTempId = 1;

        while (res.moveToNext()) {
            GearPiece gearPiece = new GearPiece(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getFloat(5), res.getFloat(6), res.getFloat(7), res.getFloat(8), res.getFloat(9), res.getFloat(10), 0, res.getFloat(11), 0);
            TextView gearPieceView = new TextView(getApplicationContext());

            if (gearPiece.getDefense() == 0) {
                gearPieceView.setText(gearPiece.getName() + "\nLevel " + Math.round(gearPiece.getLevel()) + " (Off)");
            } else {
                gearPieceView.setText(gearPiece.getName() + "\nLevel " + Math.round(gearPiece.getLevel()) + " (Def)");
            }
            gearPieceView.setGravity(Gravity.CENTER);
            gearPieceView.setTextColor(Color.BLACK);
            gearPieceView.setTypeface(Typeface.create("serif", Typeface.NORMAL));
            gearPieceView.setId(gearInventoryId);

            if (gearPiece.getRarity().equals("common")) {
                gearPieceView.setBackgroundResource(R.drawable.border_common_quality);
            } else if (gearPiece.getRarity().equals("uncommon")) {
                gearPieceView.setBackgroundResource(R.drawable.border_uncommon_quality);
            } else if (gearPiece.getRarity().equals("rare")) {
                gearPieceView.setBackgroundResource(R.drawable.border_rare_quality);
            } else if (gearPiece.getRarity().equals("epic")) {
                gearPieceView.setBackgroundResource(R.drawable.border_epic_quality);
            } else if (gearPiece.getRarity().equals("legendary")) {
                gearPieceView.setBackgroundResource(R.drawable.border_legendary_quality);
            }
            gearInventory.add(gearPiece);
            gearInventoryViews.add(gearPieceView);
            if (gearPiece.getIsEquiped() == 0) {
                scrollConstraintLayout.addView(gearPieceView);
            } else if (gearPiece.getIsEquiped() == 1) {
                mainConstraintLayout.addView(gearPieceView);
            }
            gearPieceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    equipOrUnequip.setId(v.getId());

                    if (gearInventory.get(v.getId()).getIsEquiped() == 0) {
                        equipOrUnequipText = "Equip";
                    } else if (gearInventory.get(v.getId()).getIsEquiped() == 1) {
                        equipOrUnequipText = "Unequip";
                    }
                    gearInfoText = gearInventory.get(v.getId()).getStats();

                    if (gearInventory.get(v.getId()).getRarity().equals("common")) {
                        gearInfo.setBackgroundResource(R.drawable.border_common_quality);
                    } else if (gearInventory.get(v.getId()).getRarity().equals("uncommon")) {
                        gearInfo.setBackgroundResource(R.drawable.border_uncommon_quality);
                    } else if (gearInventory.get(v.getId()).getRarity().equals("rare")) {
                        gearInfo.setBackgroundResource(R.drawable.border_rare_quality);
                    } else if (gearInventory.get(v.getId()).getRarity().equals("epic")) {
                        gearInfo.setBackgroundResource(R.drawable.border_epic_quality);
                    } else if (gearInventory.get(v.getId()).getRarity().equals("legendary")) {
                        gearInfo.setBackgroundResource(R.drawable.border_legendary_quality);
                    }
                    equipOrUnequip.setText(equipOrUnequipText);
                    gearInfo.setText(gearInfoText);
                }
            });
            if (gearPiece.getIsEquiped() == 0)
            {
                if (gearInventoryId == 0 || gearInventory.get(gearInventoryId - gearEquipedTempId).getIsEquiped() == 1) {
                    constraintSet.constrainHeight(gearInventoryViews.get(gearInventoryId).getId(), (int) gearPieceHeight);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.LEFT, constraintSet.PARENT_ID, constraintSet.LEFT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.RIGHT, constraintSet.PARENT_ID, constraintSet.RIGHT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.TOP, constraintSet.PARENT_ID, constraintSet.TOP, 0);
                } else if (gearInventoryId > 0) {
                    constraintSet.constrainHeight(gearInventoryViews.get(gearInventoryId).getId(), (int) gearPieceHeight);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.LEFT, constraintSet.PARENT_ID, constraintSet.LEFT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.RIGHT, constraintSet.PARENT_ID, constraintSet.RIGHT, 0);
                    constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.TOP, gearInventoryViews.get(gearInventoryId - gearEquipedTempId).getId(), constraintSet.TOP, (int) gearPieceHeight);
                }
                constraintSet.applyTo(scrollConstraintLayout);
                gearEquipedTempId = 1;
            }
            else if (gearPiece.getIsEquiped() == 1) {
                for (int i = 0; i < gearSorts.size(); i++) {
                    if (gearPiece.getName().equals(gearSorts.get(i))) {
                        constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.LEFT, gearSlots.get(i).getId(), constraintSet.LEFT, 0);
                        constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.RIGHT, gearSlots.get(i).getId(), constraintSet.RIGHT, 0);
                        constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.TOP, gearSlots.get(i).getId(), constraintSet.TOP, 0);
                        constraintSet.connect(gearInventoryViews.get(gearInventoryId).getId(), constraintSet.BOTTOM, gearSlots.get(i).getId(), constraintSet.BOTTOM, 0);
                    }
                    constraintSet.applyTo(mainConstraintLayout);

                    if (gearInventoryId != 0) {
                        gearEquipedTempId++;
                    }
                }
                gearInventoryId++;
            }
        }
    }
    public void equipOrUnequip(View view) {
        if (view.getId() <= gearInventory.size()) {
            if (gearInventory.get(view.getId()).getIsEquiped() == 0) {
                for (int i = 0; i < gearInventory.size(); i++) {
                    for (int y = 0; y < gearSorts.size(); y++) {
                        if (gearInventory.get(view.getId()).getName().equals(gearSorts.get(y)) && gearInventory.get(i).getName().equals(gearSorts.get(y)) && gearInventory.get(i).getIsEquiped() == 1) {
                            gearInventory.get(i).setIsEquiped(0);
                            db.updateData(i + 1 + "",
                                    gearInventory.get(i).getIsEquiped(),
                                    gearInventory.get(i).getName(),
                                    gearInventory.get(i).getRarity(),
                                    gearInventory.get(i).getStats(),
                                    gearInventory.get(i).getLevel(),
                                    gearInventory.get(i).getHealth(),
                                    gearInventory.get(i).getDefense(),
                                    gearInventory.get(i).getDamage(),
                                    gearInventory.get(i).getBlockChance(),
                                    gearInventory.get(i).getCritChance(),
                                    gearInventory.get(i).getGoldWorth());

                            player.decreaseMaxHealthGearBonus(Math.round(gearInventory.get(i).getHealth()));
                            player.decreaseDefenseGearBonus(Math.round(gearInventory.get(i).getDefense()));
                            player.decreaseDamageGearBonus(Math.round(gearInventory.get(i).getDamage()));

                            player.decreaseMaxHealth(Math.round(gearInventory.get(i).getHealth()));
                            player.decreaseHealth(Math.round(gearInventory.get(i).getHealth()));
                            player.decreaseDefense(Math.round(gearInventory.get(i).getDefense()));
                            player.decreaseDamage(Math.round(gearInventory.get(i).getDamage()));
                            player.decreaseBlockChance(Math.round(gearInventory.get(i).getBlockChance()));
                            player.decreaseCritChance(Math.round(gearInventory.get(i).getCritChance()));
                        }
                    }
                }
                gearInventory.get(view.getId()).setIsEquiped(1);
                db.updateData(view.getId() + 1 + "",
                        gearInventory.get(view.getId()).getIsEquiped(),
                        gearInventory.get(view.getId()).getName(),
                        gearInventory.get(view.getId()).getRarity(),
                        gearInventory.get(view.getId()).getStats(),
                        gearInventory.get(view.getId()).getLevel(),
                        gearInventory.get(view.getId()).getHealth(),
                        gearInventory.get(view.getId()).getDefense(),
                        gearInventory.get(view.getId()).getDamage(),
                        gearInventory.get(view.getId()).getBlockChance(),
                        gearInventory.get(view.getId()).getCritChance(),
                        gearInventory.get(view.getId()).getGoldWorth());

                player.increaseMaxHealthGearBonus(Math.round(gearInventory.get(view.getId()).getHealth()));
                player.increaseDefenseGearBonus(Math.round(gearInventory.get(view.getId()).getDefense()));
                player.increaseDamageGearBonus(Math.round(gearInventory.get(view.getId()).getDamage()));

                player.increaseMaxHealth(Math.round(gearInventory.get(view.getId()).getHealth()));
                player.increaseHealth(Math.round(gearInventory.get(view.getId()).getHealth()));
                player.increaseDefense(Math.round(gearInventory.get(view.getId()).getDefense()));
                player.increaseDamage(Math.round(gearInventory.get(view.getId()).getDamage()));
                player.increaseBlockChance(Math.round(gearInventory.get(view.getId()).getBlockChance()));
                player.increaseCritChance(Math.round(gearInventory.get(view.getId()).getCritChance()));

                initGearInventory();
            } else if (gearInventory.get(view.getId()).getIsEquiped() == 1) {
                gearInventory.get(view.getId()).setIsEquiped(0);
                db.updateData(view.getId() + 1 + "",
                        gearInventory.get(view.getId()).getIsEquiped(),
                        gearInventory.get(view.getId()).getName(),
                        gearInventory.get(view.getId()).getRarity(),
                        gearInventory.get(view.getId()).getStats(),
                        gearInventory.get(view.getId()).getLevel(),
                        gearInventory.get(view.getId()).getHealth(),
                        gearInventory.get(view.getId()).getDefense(),
                        gearInventory.get(view.getId()).getDamage(),
                        gearInventory.get(view.getId()).getBlockChance(),
                        gearInventory.get(view.getId()).getCritChance(),
                        gearInventory.get(view.getId()).getGoldWorth());

                player.decreaseMaxHealthGearBonus(Math.round(gearInventory.get(view.getId()).getHealth()));
                player.decreaseDefenseGearBonus(Math.round(gearInventory.get(view.getId()).getDefense()));
                player.decreaseDamageGearBonus(Math.round(gearInventory.get(view.getId()).getDamage()));

                player.decreaseMaxHealth(Math.round(gearInventory.get(view.getId()).getHealth()));
                player.decreaseHealth(Math.round(gearInventory.get(view.getId()).getHealth()));
                player.decreaseDefense(Math.round(gearInventory.get(view.getId()).getDefense()));
                player.decreaseDamage(Math.round(gearInventory.get(view.getId()).getDamage()));
                player.decreaseBlockChance(Math.round(gearInventory.get(view.getId()).getBlockChance()));
                player.decreaseCritChance(Math.round(gearInventory.get(view.getId()).getCritChance()));

                initGearInventory();
            }
        }
        maxHealthText = "Max Health\n" + Math.round(player.getMaxHealth());
        defenseText = "Defense\n" +  Math.round(player.getDefense());
        damageText = "Damage\n" + Math.round(player.getDamage());
        blockChanceText = "Block Chance\n" + Math.round(player.getBlockChance()) + "%";
        critChanceText = "Crit Chance\n" + Math.round(player.getCritChance()) + "%";

        maxHealth.setText(maxHealthText);
        defense.setText(defenseText);
        damage.setText(damageText);
        blockChance.setText(blockChanceText);
        critChance.setText(critChanceText);

        editor.putFloat("maxHealth", player.getMaxHealth()).apply();
        editor.putFloat("health", player.getHealth()).apply();
        editor.putFloat("defense", player.getDefense()).apply();
        editor.putFloat("damage", player.getDamage()).apply();
        editor.putFloat("blockChance", player.getBlockChance()).apply();
        editor.putFloat("critChance", player.getCritChance()).apply();
        editor.putFloat("maxHealthGearBonus", player.getMaxHealthGearBonus()).apply();
        editor.putFloat("defenseGearBonus", player.getDefenseGearBonus()).apply();
        editor.putFloat("damageGearBonus", player.getDamageGearBonus()).apply();
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
    public void kingdom(View view) {
        intent = new Intent(this, Kingdom.class);
        saveObjectsInBundle();
    }
    public void quests(View view) {
        intent = new Intent(this, Quests.class);
        saveObjectsInBundle();
    }
}