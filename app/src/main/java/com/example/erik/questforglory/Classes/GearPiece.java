package com.example.erik.questforglory.Classes;

import java.io.Serializable;

public class GearPiece  implements Serializable {

    private int isEquiped;
    private String name;
    private String rarity;
    private String stats;
    private float level;
    private float health;
    private float defense;
    private float damage;
    private float blockChance;
    private float critChance;
    private float goldCost;
    private float goldWorth;
    private float oreCost;

    public GearPiece(int isEquiped, String name, String rarity, String stats, float level, float health, float defense, float damage, float blockChance, float critChance, float goldCost, float goldWorth, float oreCost) {
        this.isEquiped = isEquiped;
        this.name = name;
        this.rarity = rarity;
        this.stats = stats;
        this.level = level;
        this.health = health;
        this.defense = defense;
        this.damage = damage;
        this.blockChance = blockChance;
        this.critChance = critChance;
        this.goldCost = goldCost;
        this.goldWorth = goldWorth;
        this.oreCost = oreCost;
    }

    public int getIsEquiped() {
        return isEquiped;
    }

    public void setIsEquiped(int isEquiped) {
        this.isEquiped = isEquiped;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(float blockChance) {
        this.blockChance = blockChance;
    }

    public float getCritChance() {
        return critChance;
    }

    public void setCritChance(float critChance) {
        this.critChance = critChance;
    }

    public float getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(float goldCost) {
        this.goldCost = goldCost;
    }

    public float getGoldWorth() {
        return goldWorth;
    }

    public void setGoldWorth(float goldWorth) {
        this.goldWorth = goldWorth;
    }

    public float getOreCost() {
        return oreCost;
    }

    public void setOreCost(float oreCost) {
        this.oreCost = oreCost;
    }

    public void levelUp() {
        level += 1;
        health *= 1.05;
        defense *= 1.05;
        damage *= 1.05;
        goldCost *= 1.05;
        goldWorth *= 1.05;
        oreCost *= 1.05;
    }

    public void levelDown() {
        level -= 1;
        health /= 1.05;
        defense /= 1.05;
        damage /= 1.05;
        goldCost /= 1.05;
        goldWorth /= 1.05;
        oreCost /= 1.05;
    }
}
