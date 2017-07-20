import java.util.regex.*;
import com.decontextualize.a2z.TextFilter;

public class ContextFilter extends TextFilter {

  public static void main(String[] args) {
    new ContextFilter().run();
  }

  private ContextFree cfg;

  public void begin() {
    cfg = new ContextFree();
  }

  public void eachLine(String line) {

    // remove comments
    line = line.replaceAll("#.*$", "");

    // get production rules, store in given ContextFree object
    Pattern rulePattern = Pattern.compile("(\\w+) *-> *(.*)");
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

  public void end() {
//    cfg.dump();
    cfg.expand("S");
    println();
  }

}
