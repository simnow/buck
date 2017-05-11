/*
 * Copyright 2014-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.jvm.kotlin;

import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.rules.AbstractNodeBuilderWithMutableArg;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.PathSourcePath;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.testutil.FakeProjectFilesystem;
import com.google.common.hash.HashCode;
import java.nio.file.Path;

public class KotlinLibraryBuilder
    extends AbstractNodeBuilderWithMutableArg<
        KotlinLibraryDescription.Arg, KotlinLibraryDescription, BuildRule> {

  private final ProjectFilesystem projectFilesystem;

  protected KotlinLibraryBuilder(
      BuildTarget target, ProjectFilesystem projectFilesystem, HashCode hashCode) {
    super(new KotlinLibraryDescription(null), target, projectFilesystem, hashCode);
    this.projectFilesystem = projectFilesystem;
  }

  public static KotlinLibraryBuilder createBuilder(BuildTarget target) {
    return new KotlinLibraryBuilder(target, new FakeProjectFilesystem(), null);
  }

  public KotlinLibraryBuilder addSrc(SourcePath path) {
    arg.srcs = amend(arg.srcs, path);
    return this;
  }

  public KotlinLibraryBuilder addSrc(Path path) {
    return addSrc(new PathSourcePath(projectFilesystem, path));
  }
}