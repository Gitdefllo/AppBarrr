AppBarrr
=======

A widget which uses the CollapsingToolbarLayout pattern and displays a custom expanded scrollable layout in AppBarLayout

<table>
<tr>
<td><img src="https://github.com/Gitdefllo/AppBarrr/blob/master/app/docs/sample-1.gif" /></td>
<td><img src="https://github.com/Gitdefllo/AppBarrr/blob/master/app/docs/sample-2.gif" /></td>
</tr>
</table>

Usage
------

This is a sample layout using this widget:  

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

Use `showExpandedLayout()` to show the expanded layout and `hideExpandedLayout()` to retrieve the initial AppBarrr.  
At any time, you can know the current state of the expanded layout by calling `isExpanded()`.  

Download
--------

Via gradle
```java
compile 'com.fllo.bars:appbarrr:0.0.3'
```
or maven
```xml
<dependency>
  <groupId>com.fllo.bars</groupId>
  <artifactId>appbarrr</artifactId>
  <version>0.0.3</version>
</dependency>
```

This library use `appcompat-v7` and `design` from Android Support Library.
You can exclude them, if you already use it in your project:

```java
compile('com.fllo.bars:appbarrr:0.0.3') {
    exclude group: 'com.android.support'
}
```

Warnings:
------

The `ExpandedLayout`, used to lock the screen and to be shown with an extending animation, can support the scrollable widgets as `ScrollView` and `NestedScrollView`.  
No need to do this following `setSupportActionBar(toolbar)` in your `Activity` since the implementation of the `Toolbar` in this library will take care of it for you.

Documentation
--------

See the [docs](https://github.com/Gitdefllo/AppBarrr/blob/master/DOCS.md) for more information.

License
--------

    Copyright 2017 Florent Blot
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
