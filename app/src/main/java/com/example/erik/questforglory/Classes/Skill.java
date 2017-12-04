package com.example.erik.questforglory.Classes;

import java.io.Serializable;

public class Skill implements Serializable {

    private float level;
    private float damage;
    private float critChance;
    private float healing;
    private float defense;
    private float duration;
    private float cooldown;
    private float currentDuration;
    private float currentCooldown;
    private boolean onDurationCooldown;

    public Skill(float level, float damage, float critChance, float healing, float defense, float duration, float currentDuration, float cooldown, float currentCooldown, boolean onDurationCooldown) {
        this.level = level;
        this.damage = damage;
        this.critChance = critChance;
        this.healing = healing;
        this.defense = defense;
        this.duration = duration;
        this.currentDuration = currentDuration;
        this.cooldown = cooldown;
        this.currentCooldown = currentCooldown;
        this.onDurationCooldown = onDurationCooldown;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getCritChance() {
        return critChance;
    }

    public void setCritChance(float critChance) {
        this.critChance = critChance;
    }

    public float getHealing() {
        return healing;
    }

    public void setHealing(float healing) {
        this.healing = healing;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getCurrentDuration() {
        return currentDuration;
    }

    public void setCurrentDuration(float currentDuration) {
        this.currentDuration = currentDuration;
    }

    public float getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(float currentCooldown) {
        this.currentCooldown = currentCooldown;
    }

    public boolean isOnDurationCooldown() {
        return onDurationCooldown;
    }

    public void setOnDurationCooldown(boolean onDurationCooldown) {
        this.onDurationCooldown = onDurationCooldown;
    }

    public void tickDuration() {
        currentDuration -= 1;
    }

    public void tickCooldown() {
        currentCooldown -= 1;
    }

    public void refresh() {
        currentDuration = duration;
        currentCooldown = cooldown;
    }

    public void levelUpStrike() {
        level += 1;
        damage += 5;
    }

    public void leveUpCharge() {
        level += 1;
        duration += 1;
        cooldown += 1;
    }

    public void levelUpMend() {
        level += 1;
        healing += 1;
    }

    public void levelUpDefenseUp() {
        level += 1;
        defense += 3;
    }
}