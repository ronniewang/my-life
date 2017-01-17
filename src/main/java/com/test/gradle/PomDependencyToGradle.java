package com.test.gradle;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronniewang on 16/5/29.
 */
public class PomDependencyToGradle {

    public static void mainA(String[] args) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("/Users/ronniewang/workspace/my-life/pom.xml"));
        List<Element> elements = document.getRootElement().elements();
        List<Element> dependencyList = new ArrayList<>();
        for (Element element : elements) {
            if (element.getName().equals("dependencies")) {
                dependencyList = element.elements();
            }
        }
        for (Element dependency : dependencyList) {
            String scope = dependency.elementText("scope");
            String dependencyString;
            if (scope == null) {
                scope = "default";
            }
            switch (scope) {
                case "test":
                    dependencyString = "testCompile('" + buildDependencyString(dependency) + "')";
                    break;
                case "provided":
                    dependencyString = "compileProvided('" + buildDependencyString(dependency) + "')";
                    break;
                case "runtime":
                    dependencyString = "runtime('" + buildDependencyString(dependency) + "')";
                    break;
                case "compile":
                    dependencyString = "compile('" + buildDependencyString(dependency) + "')";
                    break;
                default:
                    dependencyString = "compile('" + buildDependencyString(dependency) + "')";
                    break;
            }
            Element exclusions = dependency.element("exclusions");
            if (exclusions != null) {
                Element exclusion = exclusions.element("exclusion");
                String groudId = exclusion.elementText("groupId");
                String artifactId = exclusion.elementText("artifactId");
                dependencyString += "{\n\texclude group: '" + groudId + "', module: '" + artifactId + "'\n}";
            }
            System.out.println(dependencyString);
        }
    }

    private static String buildDependencyString(Element dependency) {

        String result = dependency.elementText("groupId");
        result += ":" + dependency.elementText("artifactId");
        String version = dependency.elementText("version");
        if (version != null) {
            return result + ":" + version;
        }
        return result;
    }
}
