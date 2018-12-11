import com.google.gson.JsonArray;
import java.util.LinkedList;
public class Recerca {
    private String name;
    private LinkedList<Missio> quests;

    public Recerca(String name, JsonArray questss) {
        this.name = name;
        quests = new LinkedList<>();
        for(int k = 0; k < questss.size(); k++){
            Missio quest = new Missio (questss.get(k).getAsJsonObject().get("target").getAsInt(), questss.get(k).getAsJsonObject().get("quantity").getAsInt());
            quests.add(quest);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Missio> getQuests() {
        return quests;
    }

    public void setQuests(LinkedList<Missio> quests) {
        this.quests = quests;
    }
}
