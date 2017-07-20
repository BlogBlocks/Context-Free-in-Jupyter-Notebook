import java.util.ArrayList;

public class ContextRule {

  private ArrayList<String> expansions;
  private String rule;

  public ContextRule(String rule_) {
    expansions = new ArrayList<String>();
    rule = rule_;
  }

  public void addExpansion(String expansion) {
    expansions.add(expansion);
  }

  public String getRandomExpansion() {
    int randomIndex = (int)(Math.random() * expansions.size());
    return expansions.get(randomIndex);
  }

  public void dump() {
    System.out.print(rule + " -> ");
    System.out.println(expansions);
  }

}
