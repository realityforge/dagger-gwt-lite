# Change Log

## 2.19-rf1:

* The source for the `dagger` artifact and the `dagger-gwt` artifact have been inlined into
  the `dagger-gwt-lite` artifact and the local modifications have been used to replace the
  files from the original artifacts. This was required as J2CL in it's current iteration does
  not gracefully handle super sourced projects in a way that is compatible with GWT. Rather
  than having two artifacts (i.e. `dagger-gwt-lite` and `dagger-gwt-lite-j2cl`) it was decided
  that it was simpler to merge the artifacts using the current technique.

## 1.0.2:

* Move to dagger `2.19`.
* Add dagger source as transitive dependencies to pom to align with the behaviour of the
  original `dagger-gwt` artifact.

## 1.0.1:

* Ensure `javax.inject` GWT module is included in the output package.

## 1.0.0:

* 🎉 Initial release.
