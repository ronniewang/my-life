package com.test.gradle;

import java.util.List;

/**
 * Created by ronniewang on 16/5/29.
 *
 * @author ronniewang
 */
public class Dependency {

    List<Dependency> excludeDependencies;

    private String scope;

    private String groupId;

    private String artifactId;

    private boolean hasExclude;

    public String getScope() {

        return scope;
    }

    public void setScope(String scope) {

        this.scope = scope;
    }

    public String getGroupId() {

        return groupId;
    }

    public void setGroupId(String groupId) {

        this.groupId = groupId;
    }

    public String getArtifactId() {

        return artifactId;
    }

    public void setArtifactId(String artifactId) {

        this.artifactId = artifactId;
    }

    public boolean isHasExclude() {

        return hasExclude;
    }

    public void setHasExclude(boolean hasExclude) {

        this.hasExclude = hasExclude;
    }

    public List<Dependency> getExcludeDependencies() {

        return excludeDependencies;
    }

    public void setExcludeDependencies(List<Dependency> excludeDependencies) {

        this.excludeDependencies = excludeDependencies;
    }

    @Override
    public String toString() {

        return "Dependency{" +
                "scope='" + scope + '\'' +
                ", groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", hasExclude=" + hasExclude +
                ", excludeDependencies=" + excludeDependencies +
                '}';
    }
}
