package edu.stts.pokemon;

public class Party {
    int idParty;
    int idUser;
    int inParty;
    int idPokemon;
    String name;
    String element;
    String evoname;
    int exp=0;
    int maxexp=100;
    int lv=1;
    int hp;
    int maxhp;
    int def;
    int att;
    String gambar;
    int idSkill1;
    int idSkill2;
    int idSkill3;
    int idSkill4;
    int ppSkill1;
    int ppSkill2;
    int ppSkill3;
    int ppSkill4;
    int maxppSkill1;
    int maxppSkill2;
    int maxppSkill3;
    int maxppSkill4;
    static int counter;

    public Party(int idParty, int idUser, int inParty, int idPokemon, String name, String element, String evoname, int exp, int maxexp, int lv, int hp, int maxhp, int def, int att, String gambar, int idSkill1, int idSkill2, int idSkill3, int idSkill4) {
        this.idParty = idParty;
        this.idUser = idUser;
        this.inParty = inParty;
        this.idPokemon = idPokemon;
        this.name = name;
        this.element = element;
        this.evoname = evoname;
        this.exp = exp;
        this.maxexp = maxexp;
        this.lv = lv;
        this.hp = hp;
        this.maxhp = maxhp;
        this.def = def;
        this.att = att;
        this.gambar = gambar;
        this.idSkill1 = idSkill1;
        this.idSkill2 = idSkill2;
        this.idSkill3 = idSkill3;
        this.idSkill4 = idSkill4;
    }

    @Override
    public String toString() {
        return "Party{" +
                "idParty=" + idParty +
                ", idUser=" + idUser +
                ", inParty=" + inParty +
                ", idPokemon=" + idPokemon +
                ", name='" + name + '\'' +
                ", element='" + element + '\'' +
                ", evoname='" + evoname + '\'' +
                ", exp=" + exp +
                ", maxexp=" + maxexp +
                ", lv=" + lv +
                ", hp=" + hp +
                ", maxhp=" + maxhp +
                ", def=" + def +
                ", att=" + att +
                ", gambar='" + gambar + '\'' +
                ", idSkill1=" + idSkill1 +
                ", idSkill2=" + idSkill2 +
                ", idSkill3=" + idSkill3 +
                ", idSkill4=" + idSkill4 +
                ", ppSkill1=" + ppSkill1 +
                ", ppSkill2=" + ppSkill2 +
                ", ppSkill3=" + ppSkill3 +
                ", ppSkill4=" + ppSkill4 +
                ", maxppSkill1=" + maxppSkill1 +
                ", maxppSkill2=" + maxppSkill2 +
                ", maxppSkill3=" + maxppSkill3 +
                ", maxppSkill4=" + maxppSkill4 +
                '}';
    }

    public int getIdParty() {
        return idParty;
    }

    public void setIdParty(int idParty) {
        this.idParty = idParty;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getInParty() {
        return inParty;
    }

    public void setInParty(int inParty) {
        this.inParty = inParty;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getEvoname() {
        return evoname;
    }

    public void setEvoname(String evoname) {
        this.evoname = evoname;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMaxexp() {
        return maxexp;
    }

    public void setMaxexp(int maxexp) {
        this.maxexp = maxexp;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getIdSkill1() {
        return idSkill1;
    }

    public void setIdSkill1(int idSkill1) {
        this.idSkill1 = idSkill1;
    }

    public int getIdSkill2() {
        return idSkill2;
    }

    public void setIdSkill2(int idSkill2) {
        this.idSkill2 = idSkill2;
    }

    public int getIdSkill3() {
        return idSkill3;
    }

    public void setIdSkill3(int idSkill3) {
        this.idSkill3 = idSkill3;
    }

    public int getIdSkill4() {
        return idSkill4;
    }

    public void setIdSkill4(int idSkill4) {
        this.idSkill4 = idSkill4;
    }

    public int getPpSkill1() {
        return ppSkill1;
    }

    public void setPpSkill1(int ppSkill1) {
        this.ppSkill1 = ppSkill1;
    }

    public int getPpSkill2() {
        return ppSkill2;
    }

    public void setPpSkill2(int ppSkill2) {
        this.ppSkill2 = ppSkill2;
    }

    public int getPpSkill3() {
        return ppSkill3;
    }

    public void setPpSkill3(int ppSkill3) {
        this.ppSkill3 = ppSkill3;
    }

    public int getPpSkill4() {
        return ppSkill4;
    }

    public void setPpSkill4(int ppSkill4) {
        this.ppSkill4 = ppSkill4;
    }

    public int getMaxppSkill1() {
        return maxppSkill1;
    }

    public void setMaxppSkill1(int maxppSkill1) {
        this.maxppSkill1 = maxppSkill1;
    }

    public int getMaxppSkill2() {
        return maxppSkill2;
    }

    public void setMaxppSkill2(int maxppSkill2) {
        this.maxppSkill2 = maxppSkill2;
    }

    public int getMaxppSkill3() {
        return maxppSkill3;
    }

    public void setMaxppSkill3(int maxppSkill3) {
        this.maxppSkill3 = maxppSkill3;
    }

    public int getMaxppSkill4() {
        return maxppSkill4;
    }

    public void setMaxppSkill4(int maxppSkill4) {
        this.maxppSkill4 = maxppSkill4;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Party.counter = counter;
    }
}
