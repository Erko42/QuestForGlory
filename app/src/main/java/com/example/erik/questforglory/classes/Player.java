package com.example.erik.questforglory.classes;

import java.io.Serializable;

public class Player implements Serializable {

    private boolean alive;
    private float requiredLevelToAscend;
    private float level;
    private float XP;
    private float XPToNextLevel;
    private float glory;
    private float skillPoints;
    private float maxHealth;
    private float baseHealth;
    private float health;
    private float defense;
    private float baseDefense;
    private float battleDefense;
    private float damage;
    private float baseDamage;
    private float battleDamage;
    private float blockChance;
    private float critChance;
    private float gold;
    private float herbs;
    private float ores;
    private float soulDust;
    private float bonusBaseHealth;
    private float bonusBaseDefense;
    private float bonusBaseDamage;
    private float bonusXPYield;
    private float bonusGoldYield;
    private float bonusHerbYield;
    private float bonusOreYield;
    private float bonusSoulDustYield;
    private float maxHealthGearBonus;
    private float defenseGearBonus;
    private float damageGearBonus;

    public Player(boolean alive, float requiredLevelToAscend, float level, float XP, float XPToNextLevel, float glory, float skillPoints, float maxHealth, float health, float defense, float damage, float blockChance, float critChance, float maxHealthGearBonus, float defenseGearBonus, float damageGearBonus, float baseHealth, float baseDefense, float baseDamage, float gold, float herbs, float ores, float soulDust, float bonusBaseHealth, float bonusBaseDefense, float bonusBaseDamage, float bonusXPYield, float bonusGoldYield, float bonusHerbYield, float bonusOreYield, float bonusSoulDustYield) {
        this.alive = alive;
        this.requiredLevelToAscend = requiredLevelToAscend;
        this.level = level;
        this.XP = XP;
        this.XPToNextLevel = XPToNextLevel;
        this.glory = glory;
        this.skillPoints = skillPoints;
        this.maxHealth = maxHealth;
        this.health = health;
        this.defense = defense;
        battleDefense = defense;
        this.damage = damage;
        battleDamage = damage;
        this.blockChance = blockChance;
        this.critChance = critChance;
        this.maxHealthGearBonus = maxHealthGearBonus;
        this.defenseGearBonus = defenseGearBonus;
        this.damageGearBonus = damageGearBonus;
        this.baseHealth = baseHealth;
        this.baseDefense = baseDefense;
        this.baseDamage = baseDamage;
        this.gold = gold;
        this.herbs = herbs;
        this.ores = ores;
        this.soulDust = soulDust;
        this.bonusBaseHealth = bonusBaseHealth;
        this.bonusBaseDefense = bonusBaseDefense;
        this.bonusBaseDamage = bonusBaseDamage;
        this.bonusXPYield = bonusXPYield;
        this.bonusGoldYield = bonusGoldYield;
        this.bonusHerbYield = bonusHerbYield;
        this.bonusOreYield = bonusOreYield;
        this.bonusSoulDustYield = bonusSoulDustYield;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getRequiredLevelToAscend() {
        return requiredLevelToAscend;
    }

    public void setRequiredLevelToAscend(float requiredLevelToAscend) {
        this.requiredLevelToAscend = requiredLevelToAscend;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public float getXP() {
        return XP;
    }

    public void setXP(float XP) {
        this.XP = XP;
    }

    public float getXPToNextLevel() {
        return XPToNextLevel;
    }

    public void setXPToNextLevel(float XPToNextLevel) {
        this.XPToNextLevel = XPToNextLevel;
    }

    public float getGlory() {
        return glory;
    }

    public void setGlory(float glory) {
        this.glory = glory;
    }

    public float getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(float skillPoints) {
        this.skillPoints = skillPoints;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(float baseHealth) {
        this.baseHealth = baseHealth;
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

    public float getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(float baseDefense) {
        this.baseDefense = baseDefense;
    }

    public float getBattleDefense() {
        return battleDefense;
    }

    public void setBattleDefense(float battleDefense) {
        this.battleDefense = battleDefense;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(float baseDamage) {
        this.baseDamage = baseDamage;
    }

    public float getBattleDamage() {
        return battleDamage;
    }

    public void setBattleDamage(float battleDamage) {
        this.battleDamage = battleDamage;
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

    public float getGold() {
        return gold;
    }

    public void setGold(float gold) {
        this.gold = gold;
    }

    public float getHerbs() {
        return herbs;
    }

    public void setHerbs(float herbs) {
        this.herbs = herbs;
    }

    public float getOres() {
        return ores;
    }

    public void setOres(float ores) {
        this.ores = ores;
    }

    public float getSoulDust() {
        return soulDust;
    }

    public void setSoulDust(float soulDust) {
        this.soulDust = soulDust;
    }

    public float getBonusBaseHealth() {
        return bonusBaseHealth;
    }

    public void setBonusBaseHealth(float bonusBaseHealth) {
        this.bonusBaseHealth = bonusBaseHealth;
    }

    public float getBonusBaseDefense() {
        return bonusBaseDefense;
    }

    public void setBonusBaseDefense(float bonusBaseDefense) {
        this.bonusBaseDefense = bonusBaseDefense;
    }

    public float getBonusBaseDamage() {
        return bonusBaseDamage;
    }

    public void setBonusBaseDamage(float bonusBaseDamage) {
        this.bonusBaseDamage = bonusBaseDamage;
    }

    public float getBonusXPYield() {
        return bonusXPYield;
    }

    public void setBonusXPYield(float bonusXPYield) {
        this.bonusXPYield = bonusXPYield;
    }

    public float getBonusGoldYield() {
        return bonusGoldYield;
    }

    public void setBonusGoldYield(float bonusGoldYield) {
        this.bonusGoldYield = bonusGoldYield;
    }

    public float getBonusHerbYield() {
        return bonusHerbYield;
    }

    public void setBonusHerbYield(float bonusHerbYield) {
        this.bonusHerbYield = bonusHerbYield;
    }

    public float getBonusOreYield() {
        return bonusOreYield;
    }

    public void setBonusOreYield(float bonusOreYield) {
        this.bonusOreYield = bonusOreYield;
    }

    public float getBonusSoulDustYield() {
        return bonusSoulDustYield;
    }

    public void setBonusSoulDustYield(float bonusSoulDustYield) {
        this.bonusSoulDustYield = bonusSoulDustYield;
    }

    public float getMaxHealthGearBonus() {
        return maxHealthGearBonus;
    }

    public void setMaxHealthGearBonus(float maxHealthGearBonus) {
        this.maxHealthGearBonus = maxHealthGearBonus;
    }

    public float getDefenseGearBonus() {
        return defenseGearBonus;
    }

    public void setDefenseGearBonus(float defenseGearBonus) {
        this.defenseGearBonus = defenseGearBonus;
    }

    public float getDamageGearBonus() {
        return damageGearBonus;
    }

    public void setDamageGearBonus(float damageGearBonus) {
        this.damageGearBonus = damageGearBonus;
    }

    public void takeDamage(float damage) {
        health -= damage;
    }

    public void increaseMaxHealth(float maxHealth) {
        this.maxHealth += maxHealth;
    }

    public void increaseHealth(float health) {
        this.health += health;
    }

    public void increaseDefense(float defense) {
        this.defense += defense;
    }

    public void increaseDamage(float damage) {
        this.damage += damage;
    }

    public void increaseBlockChance(float blockChance) {
        this.blockChance += blockChance;
    }

    public void increaseCritChance(float critChance) {
        this.critChance += critChance;
    }

    public void increaseMaxHealthGearBonus(float maxHealthGearBonus) {
        this.maxHealthGearBonus += maxHealthGearBonus;
    }

    public void increaseDefenseGearBonus(float defenseGearBonus) {
        this.defenseGearBonus += defenseGearBonus;
    }

    public void increaseDamageGearBonus(float damageGearBonus) {
        this.damageGearBonus += damageGearBonus;
    }

    public void increaseBattleDefense(float battleDefense) {
        this.battleDefense += battleDefense;
    }

    public void resetBattleDefense() {
        battleDefense = defense;
    }

    public void increaseRequiredLevelToAscend(float requiredLevelToAscend) {
        this.requiredLevelToAscend += requiredLevelToAscend;
    }

    public void increaseXP(float amount) {
        XP += amount;
    }

    public void increaseSkillPoints(float skillPoints) {
        this.skillPoints += skillPoints;
    }

    public void increaseGlory(float glory) {
        this.glory += glory;
    }

    public void increaseGold(float gold) {
        this.gold += gold;
    }

    public void increaseHerbs(float herbs) {
        this.herbs += herbs;
    }

    public void increaseOres(float ores){
        this.ores += ores;
    }

    public void decreaseMaxHealth(float maxHealth) {
        this.maxHealth -= maxHealth;
    }

    public void decreaseHealth(float health) {
        this.health -= health;
    }

    public void decreaseDefense(float defense) {
        this.defense -= defense;
    }

    public void decreaseDamage(float damage) {
        this.damage -= damage;
    }

    public void decreaseBlockChance(float blockChance) {
        this.blockChance -= blockChance;
    }

    public void decreaseCritChance(float critChance) {
        this.critChance -= critChance;
    }

    public void decreaseMaxHealthGearBonus(float maxHealthGearBonus) {
        this.maxHealthGearBonus -= maxHealthGearBonus;
    }

    public void decreaseDefenseGearBonus(float defenseGearBonus) {
        this.defenseGearBonus -= defenseGearBonus;
    }

    public void decreaseDamageGearBonus(float damageGearBonus) {
        this.damageGearBonus -= damageGearBonus;
    }

    public void decreaseSkillPoints(float skillPoints) {
        this.skillPoints -= skillPoints;
    }

    public void increaseBonusBaseHealth() {
        glory -= 1;
        maxHealth /= baseHealth;
        health -= (maxHealth * (bonusBaseHealth / 100));
        baseHealth /= (1 + (bonusBaseHealth / 100));
        bonusBaseHealth += 5;
        health += (maxHealth * (bonusBaseHealth / 100));
        baseHealth *= (1 + (bonusBaseHealth / 100));
        maxHealth *= baseHealth;
    }

    public void increaseBonusBaseDefense() {
        glory -= 1;
        defense /= baseDefense;
        baseDefense /= (1 + (bonusBaseDefense / 100));
        bonusBaseDefense += 5;
        baseDefense *= (1 + (bonusBaseDefense / 100));
        defense *= baseDefense;
    }

    public void increaseBonusBaseDamage() {
        glory -= 1;
        damage /= baseDamage;
        baseDamage /= (1 + (bonusBaseDamage / 100));
        bonusBaseDamage += 5;
        baseDamage *= (1 + (bonusBaseDamage / 100));
        damage *= baseDamage;
    }

    public void increaseBonusXP() {
        glory -= 1;
        bonusXPYield += 5;
    }

    public void increaseBonusGold() {
        glory -= 1;
        bonusGoldYield += 5;
    }

    public void increaseBonusHerbs() {
        glory -= 1;
        bonusHerbYield += 5;
    }

    public void increaseBonusOres() {
        glory -= 1;
        bonusOreYield += 5;
    }

    public void increaseBonusSoulDust() {
        glory -= 1;
        bonusSoulDustYield += 5;
    }

    public void decreaseGold(float gold) {
        this.gold -= gold;
    }

    public void decreaseHerbs(float herbs) {
        this.herbs -= herbs;
    }

    public void decreaseOres(float ores) {
        this.ores -= ores;
    }

    public void decreaseSoulDust(float soulDust) {
        this.soulDust -= soulDust;
    }

    public void levelUp() {
        level += 1;
        XP -= XPToNextLevel;
        XPToNextLevel *= 1.05;
        skillPoints += 1;

        maxHealth /= baseHealth;
        defense /= baseDefense;
        damage /= baseDamage;

        baseHealth *= 1.05;
        baseDefense *= 1.05;
        baseDamage *= 1.05;

        maxHealth *= baseHealth;
        health += (maxHealth - (maxHealth / 1.05));
        defense *= baseDefense;
        damage *= baseDamage;
    }
}