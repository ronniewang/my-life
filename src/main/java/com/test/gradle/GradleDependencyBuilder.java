package com.test.gradle;

import org.dom4j.Element;

/**
 * Created by ronniewang on 16/5/29.
 *
 * @author ronniewang
 */
public interface GradleDependencyBuilder {
    Dependency build(Element dependency);
}
