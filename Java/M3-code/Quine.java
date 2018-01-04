public class Quine {
  public static void main(String[] args) {
    String[] str = {
"public class Quine {",
"  public static void main(String[] args) {",
"    String[] str = {",
"    };",
"    for (int i=0; i<3; i++) { System.out.println(str[i]); }",
"    for (int i=0; i<10; i++) ",
"        { System.out.println((char)34 + str[i] + (char)34 + (char)44); }",
"    for (int i=3; i<10; i++) { System.out.println(str[i]); }",
"  }",
"}",
    };
    for (int i=0; i<3; i++) { System.out.println(str[i]); }
    for (int i=0; i<10; i++) 
        { System.out.println((char)34 + str[i] + (char)34 + (char)44); }
    for (int i=3; i<10; i++) { System.out.println(str[i]); }
  }
}
