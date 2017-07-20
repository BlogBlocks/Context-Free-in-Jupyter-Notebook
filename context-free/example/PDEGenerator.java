public class PDEGenerator extends ContextFree {

  public static void main(String[] args) {
    PDEGenerator pdeg = new PDEGenerator();
    pdeg.addRule("Drawing", "Square");
    pdeg.addRule("Square", "square");
    pdeg.addRule("Square", "square scale circle");
    pdeg.addRule("Square", "square scale Square");
    pdeg.addRule("Square",
      "square push left Square pop push right Square pop");
    System.out.println("size(512, 512); background(255); rectMode(CENTER);");
    System.out.println("translate(width/2,height/2);");
    System.out.println("fill(0, 0, 240, 32); noStroke();");
    pdeg.expand("Drawing");
  }

  public void renderExpansion(String s) {
    if (s.equals("push")) {
      System.out.println("pushMatrix();");
    }
    else if (s.equals("pop")) {
      System.out.println("popMatrix();");
    }
    else if (s.equals("left")) {
      System.out.println("translate(-125, 0);");
      System.out.println("scale(0.45);");
    }
    else if (s.equals("right")) {
      System.out.println("translate(125, 0);");
      System.out.println("scale(0.45);");
    }
    else if (s.equals("square")) {
      System.out.println("rect(0, 0, 500, 500);");
    }
    else if (s.equals("scale")) {
      System.out.println("scale(0.75);");
    }
    else if (s.equals("circle")) {
      System.out.println("ellipse(0, 0, 500, 500);");
    }
    
  }

}
