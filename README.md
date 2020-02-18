> Project Archived: Project no longer used as moved to [Sting](https://sting-ioc.github.io/) for dependency injection which is already optimized.

---

# dagger-gwt-lite: SuperSourced elements to optimize Dagger for GWT/J2CL

[![Build Status](https://secure.travis-ci.org/realityforge/dagger-gwt-lite.png?branch=master)](http://travis-ci.org/realityforge/dagger-gwt-lite)
[<img src="https://img.shields.io/maven-central/v/org.realityforge.dagger/dagger-gwt-lite.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.realityforge.dagger%22%20a%3A%22dagger-gwt-lite%22)
![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)

The `dagger-gwt-lite` package provides GWT modules for dagger that match the official `dagger-gwt` artifacts.
It also super sources in some internal support classes to make it easier for the GWT2.x/J2CL compilers to
optimize out dead code.
