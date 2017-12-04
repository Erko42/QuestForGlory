package com.example.erik.questforglory.Classes;

import java.io.Serializable;

public class Monster implements Serializable {

    private boolean alive;
    private float maxLevel;
    private float level;
    private float XPYield;
    private float maxHealth;
    private float health;
    private float damage;
    private float goldYield;
    private float herbYield;
    private float oreYield;

    public Monster(boolean alive, float maxLevel, float level, float XPYield, float maxHealth, float health, float damage, float goldYield, float herbYield, float oreYield) {
        this.alive = alive;
        this.maxLevel = maxLevel;
        this.level = level;
        this.XPYield = XPYield;
        this.maxHealth = maxHealth;
        this.health = health;
        this.damage = damage;
        this.goldYield = goldYield;
        this.herbYield = herbYield;
        this.oreYield = oreYield;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(float maxLevel) {
        this.maxLevel = maxLevel;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public float getXPYield() {
        return XPYield;
    }

    public void setXPYield(float XPYield) {
        this.XPYield = XPYield;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getGoldYield() {
        return goldYield;
    }

    public void setGoldYield(float goldYield) {
        this.goldYield = goldYield;
    }

    public float getHerbYield() {
        return herbYield;
    }

    public void setHerbYield(float herbYield) {
        this.herbYield = herbYield;
    }

    public float getOreYield() {
        return oreYield;
    }

    public void setOreYield(float oreYield) {
        this.oreYield = oreYield;
    }

    public void takeDamage(float damage) {
        health -= damage;
    }

    public void increaseMaxLevel() {
        maxLevel += 1;
    }

    public void levelUp() {
        level += 1;
        maxHealth *= 1.1;
        health = maxHealth;
        damage *= 1.1;
        XPYield *= 1.05;
        goldYield *= 1.05;
        herbYield *= 1.05;
        oreYield *= 1.05;
    }

    public void levelDown() {
        level -= 1;
        maxHealth /= 1.1;
        health = maxHealth;
        damage /= 1.1;
        XPYield /= 1.05;
        goldYield /= 1.05;
        herbYield /= 1.05;
        oreYield /= 1.05;
    }
}