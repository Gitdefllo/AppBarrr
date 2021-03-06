Docs
=======

**Full example**

Here's a full implementation in xml:  

```java
<com.fllo.bars.AppBarrr
    android:id="@+id/app_barrr"
    android:layout_width="match_parent"
    android:layout_height="180dp"
    android:theme="@style/AppTheme.AppBarOverlay"
    app:toolbarLayout="@layout/toolbar_layout"
    app:toolbarIconClose="@drawable/ic_close_white"
    app:expandLayout="@layout/content_expanding"
    app:expandMaxSize="450dp"
    app:animExpandDuration="150"
    app:animCollapseDuration="150"
    app:contentScrimBar="?attr/colorPrimary"
    app:toolbarTitleCollapseStyle="@style/CollapsedTitle"
    app:toolbarTitleExpandStyle="@style/ExpandedTitle"
    app:hideWhenTouchOutside="true"/>
```

**Show/Hide:**

Call `showExpandedLayout()` to show the expanded layout, whereas in order to hide it, call `hideExpandedLayout()`. At any time, you can know the current state of the expanded layout by calling `isExpanded()`. By default, when the expanded layout is showing, a custom view overlaps the nested views from the current layout, if the user clicks on it, this will call `hideExpandedLayout()`. If you don't want this behavior, you should declare `hideWhenTouchOutside(false)`, or the following attribute: 

```java
app:hideWhenTouchOutside="false"
```

**Requirements:**  

The Toolbar and the custom expanded layout must be declared. Otherwise, a `NullPointerException` will occur. The declaration have to be set with these attributes:  

```java
app:toolbarLayout="@layout/toolbar"
app:expandLayout="@layout/customlayout"
```

`setExpandMaxSize(float)` can set the maximum height of the expanded layout. Its default max size will be the parent's height ~less the toolbar's height. The value set in `setExpandMaxSize(float)` gets the default value if the max size is larger than the parent. This can also be set by xml with: 

```java
app:expandMaxSize="520dp"
```

**Animations:**  

The expanding and collapsing animations can be set separately. By default the two animations are setting to 300ms. `setExpandDuration(long)` sets the duration of the expanding animation of the expanded layout, whereas `setCollapseDuration(long)` is used for the duration of the collapsing animation of the expanded layout. It's also possible to declare it by using the following attributes:  

```java
app:animExpandDuration="150"
app:animCollapseDuration="800"
```

**CollapsingToolbarLayout:**  

This library uses the `CollapsingToolbarLayout` and in order to be customizable, some declarations can be handled (only) by xml.  
The `android:contentScrim` is handled by `app:contentScrimBar` to change the color of the `CollapsingToolbarLayout`.  
You can use a style to change the default expanding title color, size, etc. with `app:toolbarTitleExpandStyle`.  
The same can be used to the collapsing title of the layout as `app:toolbarTitleCollapseStyle`:  

```java
app:contentScrimBar="?attr/colorPrimary"
app:toolbarTitleCollapseStyle="@style/CollapsedTitle"
app:toolbarTitleExpandStyle="@style/ExpandedTitle"
```

**ToolbarIconClose:**  

It's possible to display a custom icon at the top start of the layout. It will replace the current navigation icon of the `Toolbar`. This icon can be set by `setCloseIcon(Drawable)` and is used to close the expanded layout. If not set, the layout will not display any icon. This icon can be declared by xml:  

```java
app:toolbarIconClose="@drawable/ic_close_white"
```

**WindowSoftInputMode:**

If you add `EditTexts` in the expanded layout, to avoid weird behavior with the `SoftKeyboard`, consider to set `adjustPan` in the Manifest.xml: `android:windowSoftInputMode="adjustPan"`  

**References:**  

```java
R.styleable#AppBarrr_toolbarLayout
R.styleable#AppBarrr_toolbarIconClose
R.styleable#AppBarrr_expandLayout
R.styleable#AppBarrr_expandMaxSize
R.styleable#AppBarrr_animExpandDuration
R.styleable#AppBarrr_animCollapseDuration
R.styleable#AppBarrr_contentScrimBar
R.styleable#AppBarrr_toolbarTitleCollapseStyle
R.styleable#AppBarrr_toolbarTitleExpandStyle
R.styleable#AppBarrr_hideWhenTouchOutside
```

This widget extends `android.support.design.widget.AppBarLayout`  
And implements a `android.support.design.widget.CollapsingToolbarLayout`  
