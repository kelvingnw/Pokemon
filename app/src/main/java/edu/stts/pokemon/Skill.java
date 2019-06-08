package edu.stts.pokemon;

public class Skill {
    int idskill;
    String skillname;
    String element;
    String desk;
    int pp;
    int multiplier;
    static int counter;

    public Skill(int idskill, String skillname, String element, String desk, int pp, int multiplier) {
        this.idskill = idskill;
        this.skillname = skillname;
        this.element = element;
        this.desk = desk;
        this.pp = pp;
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return ", skillname='" + skillname ;
    }

    public int getIdskill() {
        return idskill;
    }

    public void setIdskill(int idskill) {
        this.idskill = idskill;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Skill.counter = counter;
    }
}
