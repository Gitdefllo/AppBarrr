Docs
=======

**Full example**

Here's an implementation in xml:  

```java
<com.fllo.bars.AppBarrr
    android:id="@+id/app_barrr"
    android:layout_height="@dimen/app_bar_height"
    android:layout_width="match_parent"
    android:theme="@style/AppTheme.AppBarOverlay"
    app:toolbarLayout="@layout/toolbar_layout"
    app:expandLayout="@layout/content_expanding"
    app:contentScrimBar="?attr/colorPrimary"
    app:animExpandDuration="300"
    app:animCollapseDuration="300"
    app:closeExpandIcon="@drawable/ic_close_white"
    app:toolbarTitleCollapseStyle="@style/CollapsedTitle"
    app:toolbarTitleExpandStyle="@style/ExpandedTitle"/>
```

**Requirements:**  

The Toolbar and the custom expanded layout must be declared. Otherwise, a `NullPointerException` will occur. The declaration can be set dynamically with `setToolbar(int)` and setExpandLayout(int)` with a resource id (as `R.layout.xxx`) in parameter, or by attributes:  

```java
android:toolbarLayout
android:expandLayout
```

**Animations:**  

The expanding and collapsing animations can be set separately. By default the two animations are setting to 300ms. `setExpandDuration(long)` sets the duration of the expanding animation of the expanded layout, whereas `setCollapseDuration(long)` is used for the duration of the collapsing animation of the expanded layout. It's also possible to declare it by using the following attributes:  

```java
android:animExpandDuration
android:animCollapseDuration
```

**CollapsingToolbarLayout:**  

This library uses the `CollapsingToolbarLayout` and in order to be customizable, some declarations can be override (only) by XML. The `android:contentScrim` is handled
by `android:contentScrimBar` to change the color of the `CollapsingToolbarLayout`. You can use a style to change the default expanding title color, size, etc. with `android:toolbarTitleExpandStyle`. The same can be used to the collapsing title of the layout as `android:toolbarTitleCollapseStyle`:  

```java
android:contentScrimBar
android:toolbarTitleCollapseStyle
android:toolbarTitleExpandStyle
```

**Close Icon:**  

It's possible to display a custom icon at the top start of the layout. It will replace the current navigation icon of the `Toolbar`. This icon can be set by `setCloseIcon(Drawable)` and is used to close the expanded layout. If not set, the layout will not display any icon. This icon can be declared by XML:  

```java
android:closeExpandIcon
```

**References:**  

```java
R.styleable#AppBarrr_toolbarLayout
R.styleable#AppBarrr_expandLayout
R.styleable#AppBarrr_contentScrimBar
R.styleable#AppBarrr_animExpandDuration
R.styleable#AppBarrr_animCollapseDuration
R.styleable#AppBarrr_closeExpandIcon
R.styleable#AppBarrr_toolbarTitleCollapseStyle
R.styleable#AppBarrr_toolbarTitleExpandStyle
```

This widget extends `android.support.design.widget.AppBarLayout`  
And implements a `android.support.design.widget.CollapsingToolbarLayout`  
