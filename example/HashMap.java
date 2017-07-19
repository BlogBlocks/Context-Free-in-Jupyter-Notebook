private HashMap<String, ContextRule> rules;
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