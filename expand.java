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