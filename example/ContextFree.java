import java.util.ArrayList;
import java.util.HashMap;

public class ContextFree {

  private HashMap<String, ContextRule> rules;
  private String expansion;

  public static void main(String[] args) {
    // if this class was run from the command line, put in some test rules and
    // run
    ContextFree cf = new ContextFree();
    cf.addRule("S", "NP VP");
    cf.addRule("NP", "the N");
    cf.addRule("N", "cat");
    cf.addRule("N", "dog");
    cf.addRule("N", "weinermobile");
    cf.addRule("N", "duchess");
    cf.addRule("VP", "V the N");
    cf.addRule("V", "sees");
    cf.addRule("V", "chases");
    cf.addRule("V", "lusts after");
    cf.addRule("V", "blames");
    cf.expand("S");
    System.out.println();
  }

  public ContextFree() {
    rules = new HashMap<String, ContextRule>();
    expansion = new String();
  }

  public void addRule(String rule, String expansion) {
    if (rules.containsKey(rule)) {
      ContextRule cr = rules.get(rule);
      cr.addExpansion(expansion);
    }
    else {
      ContextRule cr = new ContextRule(rule);
      cr.addExpansion(expansion);
      rules.put(rule, cr);
    }
  }

  public void expand(String current) {
    if (!rules.containsKey(current)) {
      renderExpansion(current);
    }
    else {
      ContextRule cr = rules.get(current);
      String[] toExpand = cr.getRandomExpansion().split(" ");
      for (String s: toExpand) {
        expand(s);
      }
    }
  }

  public void dump() {
    for (String rule: rules.keySet()) {
      ContextRule cr = rules.get(rule);
      cr.dump();
    }
  }

  // override this function in your derived class for custom behavior
  public void renderExpansion(String s) {
    System.out.print(s + " ");
  }

}
