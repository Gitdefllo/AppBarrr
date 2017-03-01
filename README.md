AppBarrr
=======

A widget which uses the CollapsingToolbarLayout pattern and displays a custom expanded scrollable layout in AppBarLayout

Usage
------

This is an example declaration to use this widget:  

```java
<android.support.design.widget.CoordinatorLayout ...>
    <com.fllo.bars.AppBarrr .../>
    <android.support.v4.widget.NestedScrollView ...>
        <.../>
    </android.support.v4.widget.NestedScrollView>
    <android.support.design.widget.FloatingActionButton .../>
</android.support.design.widget.CoordinatorLayout>
```

The declaration should be as follows:  

```java
<com.fllo.bars.AppBarrr
    android:id="@+id/app_barrr"
    android:layout_height="180dp"
    android:layout_width="match_parent"
    android:theme="@style/AppTheme.AppBarOverlay"
    app:toolbarLayout="@layout/toolbar_layout"
    app:expandLayout="@layout/content_expanding"
    app:animExpandDuration="300"
    app:animCollapseDuration="300"/>
```

Implementation
------

The `ExpandedLayout`, used to lock the screen and to be shown with an extending animation, can support the scrollable widgets as:

```java
<ScrollView .../>
<android.support.v4.widget.NestedScrollView .../>
```

For the `Toolbar`, no need to do this following code in your `Activity`:

```java
Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
```

Since the implementation of the `Toolbar` will take care of it for you.

Download
--------

Via gradle
```java
compile 'com.fllo.bars:appbarrr:0.0.2'
```
or maven
```xml
<dependency>
  <groupId>com.fllo.bars</groupId>
  <artifactId>appbarrr</artifactId>
  <version>0.0.2</version>
</dependency>
```

Documentation
--------

See the [docs](https://github.com/Gitdefllo/AppBarrr/blob/master/DOCS.md) for more information.


License
--------

    Copyright 2016 Florent Blot
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
