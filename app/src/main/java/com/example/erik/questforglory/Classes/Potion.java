package com.example.erik.questforglory.Classes;

import java.io.Serializable;

public class Potion implements Serializable {

    private float level;
    private float amount;
    private float upgradeCost;
    private float goldCost;
    private float goldWorth;
    private float herbCost;
    private float effect;
    private float duration;
    private float currentDuration;
    private float cooldown;
    private float currentCooldown;
    private boolean onDurationCooldown;

    public Potion(float level, float amount, float upgradeCost, float goldCost, float goldWorth, float herbCost, float effect, float duration, float currentDuration, float cooldown, float currentCooldown, boolean onDurationCooldown) {
        this.level = level;
        this.amount = amount;
        this.upgradeCost = upgradeCost;
        this.goldCost = goldCost;
        this.goldWorth = goldWorth;
        this.herbCost = herbCost;
        this.effect = effect;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(float upgradeCost) {
        this.upgradeCost = upgradeCost;
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

    public float getHerbCost() {
        return herbCost;
    }

    public void setHerbCost(float herbCost) {
        this.herbCost = herbCost;
    }

    public float getEffect() {
        return effect;
    }

    public void setEffect(float effect) {
        this.effect = effect;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getCurrentDuration() {
        return currentDuration;
    }

    public void setCurrentDuration(float currentDuration) {
        this.currentDuration = currentDuration;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
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

    public void refresh() {
        currentDuration = duration;
    }

    public void increaseAmount() {
        amount += 1;
    }

    public void decreaseAmount() {
        amount -= 1;
    }

    public void upgrade() {
        level += 1;
        upgradeCost *= 1.05;
        goldCost *= 1.05;
        goldWorth *= 1.05;
        herbCost *= 1.05;
        effect *= 1.06;
    }
}