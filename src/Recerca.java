public class Recerca {
    private String name;
    private Missio[] quests;

    public Recerca(String name, Missio[] quests) {
        this.name = name;
        this.quests = quests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Missio[] getQuests() {
        return quests;
    }

    public void setQuests(Missio[] quests) {
        this.quests = quests;
    }
}
