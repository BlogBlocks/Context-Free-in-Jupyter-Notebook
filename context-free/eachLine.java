public void eachLine(String line) {
  line = line.replaceAll("#.*$", ""); // remove comments
  Pattern rulePattern = Pattern.compile("(\\w+) *-> *(.*)"); // Sym -> Expansion
  Matcher m = rulePattern.matcher(line);
  if (m.find()) {
    String rule = m.group(1);
    String expansion = m.group(2);
    String[] expansions = expansion.split("\\s*\\|\\s*");
    for (String s: expansions) {
      cfg.addRule(rule, s);
    }
  }
}