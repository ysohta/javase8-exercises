package ch08.ex13;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class is a annotation processor to create test runner in source code.
 * Created by yukiohta on 2015/10/26. <br>
 * This program is written based on the article "Scripting, Compiling, and Annotation Processing in Java",
 * the link described below.
 *
 * @see <a href="http://www.informit.com/articles/article.aspx?p=2027052&seqNum=6">http://www.informit.com/articles/article.aspx?p=2027052&seqNum=6</a>
 */
@SupportedAnnotationTypes("ch08.ex13.TestCases")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestCaseProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement t : annotations) {
            List<TestCase> testCases = new LinkedList<>();
            String className = null;

            // for each TestCases
            for (Element e : roundEnv.getElementsAnnotatedWith(t)) {
                // for each TestCase
                for (TestCase testCase : e.getAnnotationsByType(TestCase.class)) {
                    testCases.add(testCase);
                }

                className = ((TypeElement) e.getEnclosingElement()).getQualifiedName().toString();
            }

            try {
                if (className != null)
                    writeIntoFile(className, testCases);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Writes the source file for the test runner of {@link TestCase}.
     *
     * @param className the name of the class
     * @param testCases list of TestCases
     * @throws IOException if the file cannot be created.
     */
    private void writeIntoFile(String className, List<TestCase> testCases) throws IOException {
        int i = className.lastIndexOf(".");
        String packageName = className.substring(0, i);
        JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(packageName + ".TestRunner");
        try (PrintWriter out = new PrintWriter(sourceFile.openWriter())) {
            if (i > 0) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
            }

            out.print("public class ");
            out.println("TestRunner");
            out.println("{");
            out.println("   public static void main(String... args)");
            out.println("   {");
            out.println("       long expected, actual;");
            out.println("       int param;");
            for (TestCase e : testCases) {
                out.println("       expected = " + e.expected() + ";");
                out.println("       param = " + e.params() + ";");
                out.println("       actual = MyMathTest.testFactorial(param);");
                out.println("       System.out.print(\"expected=\" + expected + \" actual=\" + actual );");
                out.println("       System.out.println(actual == expected ? \"[SUCCESS]\" : \"[FAILED]\");\n");
            }
            out.println("   }");
            out.println("}");
        }
    }
}