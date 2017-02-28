package com.fllo.bars;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * <h2>AppBarrr</h2>
 * <p>A widget which uses the CollapsingToolbarLayout design and displays a custom locked
 * scrollable layout in AppBarLayout</p>
 *
 * <p>Created by Fllo (Florent Blot - @Gitdefllo) on 23/02/2017</p>
 *
 * <h3>Usage:</h3>
 * <p>This is an example declaration to use this widget:
 * <pre>
 *     &lt;android.support.design.widget.CoordinatorLayout
 *         ...&gt;
 *         &lt;com.fllo.bars.AppBarrr
 *             .../&gt
 *         &lt;android.support.v4.widget.NestedScrollView
 *             ...&gt
 *             &ltView ...&gt;
 *         &lt;/android.support.v4.widget.NestedScrollView&gt;
 *         &lt;android.support.design.widget.FloatingActionButton
 *             .../&gt
 *     &lt/android.support.design.widget.CoordinatorLayout&gt;
 * </pre>
 * </p>
 * <p>Warning: to avoid behavior issues, make sure you declare the AppBarrr as first
 * child of the parent container and followed by a NestedScrollView widget</p>
 * <p>The declaration should be as follows:
 * <pre>
 *     &lt;com.fllo.bars.AppBarrr
 *         android:id="@+id/app_barrr"
 *         android:layout_height="180dp"
 *         android:layout_width="match_parent"
 *         android:theme="@style/AppTheme.AppBarOverlay"
 *         app:toolbarLayout="@layout/toolbar_layout"
 *         app:expandLayout="@layout/content_expanding"
 *         app:animExpandDuration="300"
 *         app:animCollapseDuration="300"/&gt;
 * </pre>
 * </p>
 *
 * <h3>Requirements:</h3>
 * <p>The Toolbar and the custom locked layout must be declared. Otherwise, a
 * NullPointerException will occur. The declaration can be set dynamically with
 * {@link #setToolbar(int)} and {@link #setExpandLayout(int)} or by XML attributes:
 * <pre>
 * toolbarLayout
 * expandLayout
 * </pre>
 * </p>
 *
 * <h3>Animations:</h3>
 * <p>The expanding and collapsing animations can be set separately. By default the
 * two animations are setting to 300ms. {@link #setExpandDuration(long)} sets the
 * duration of the expanding animation of the locked layout, whereas
 * {@link #setCollapseDuration(long)} is used for the duration of the collapsing
 * animation of the locked layout. It's also possible to declare it by using the
 * following attributes:
 * <pre>
 * animExpandDuration
 * animCollapseDuration
 * </pre>
 * </p>
 *
 * <h3>CollapsingToolbarLayout:</h3>
 * <p>This library uses the CollapsingToolbarLayout and in order to be customizable,
 * some declarations can be override (only) by XML</p>
 * <p>The {@link android.support.design.R.styleable#CollapsingToolbarLayout_contentScrim
 * android:contentScrim} is replaced by {@link R.styleable#AppBarrr_contentScrimBar
 * android:contentScrimBar} to change the color of the CollapsingToolbarLayout</p>
 * <p>You can use a style to change the default expanding title color, size, etc. with
 * {@link R.styleable#AppBarrr_toolbarTitleExpandStyle android:toolbarTitleExpandStyle}</p>
 * <p>The same can be used to the collapsing title of the layout as
 * {@link R.styleable#AppBarrr_toolbarTitleCollapseStyle android:toolbarTitleCollapseStyle}</p>
 * <pre>
 * contentScrimBar
 * toolbarTitleCollapseStyle
 * toolbarTitleExpandStyle
 * </pre>
 * </p>
 *
 * <h3>Close Icon:</h3>
 * <p>It's possible to display a custom icon at the top start of the layout. It will
 * replace the current navigation icon of the Toolbar. This icon can be set by
 * {@link #setCloseIcon(Drawable)} and is used to close the locked layout. If not set,
 * the layout will not display any icon. This icon can be declared by XML:
 * <pre>
 * closeExpandIcon
 * </pre>
 * </p>
 *
 * @attr ref R.styleable#AppBarrr_toolbarLayout
 * @attr ref R.styleable#AppBarrr_expandLayout
 * @attr ref R.styleable#AppBarrr_contentScrimBar
 * @attr ref R.styleable#AppBarrr_animExpandDuration
 * @attr ref R.styleable#AppBarrr_animCollapseDuration
 * @attr ref R.styleable#AppBarrr_closeExpandIcon
 * @attr ref R.styleable#AppBarrr_toolbarTitleCollapseStyle
 * @attr ref R.styleable#AppBarrr_toolbarTitleExpandStyle
 *
 * @see android.support.design.widget.AppBarLayout
 * @see android.support.design.widget.CollapsingToolbarLayout
 * @see android.support.v7.widget.Toolbar
 */
public class AppBarrr extends AppBarLayout {

    /**
     * Initial AppBarLayout's height
     */
    private static int APPBAR_START_HEIGHT;

    /**
     * Initial Toolbar's height
     */
    private static int TOOLBAR_START_HEIGHT;

    /**
     * Locked layout's minimum height
     */
    private static int LOCKED_LAYOUT_MIN_HEIGHT;

    /**
     * Default toolbar
     */
    private Toolbar mToolbar;

    /**
     * Default expanded layout
     */
    private View mExpandLayout;

    /**
     * Default close icon
     */
    private ImageView mCloseIcon;

    /**
     * Default navigation icon, null if not set
     */
    private Drawable mUpIcon;

    /**
     * Default collapsingtoolbar layout
     */
    private CollapsingToolbarLayout container;

    /**
     * Default duration of expanding animation
     */
    private long mExpandDuration;

    /**
     * Default duration of collapsing animation
     */
    private long mCollapseDuration;

    /**
     * True if the expanded layout is visible
     */
    private boolean mIsExpanded = false;

    /**
     * Current activity
     */
    private Activity activity = null;

    public AppBarrr(Context context) {
        this(context, null);
    }

    public AppBarrr(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppBarrr(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);

        // current activity context
        activity = getActivity(getContext());

        // set id and save state
        if (getId() == -1)
            setId(R.id.layout_default_appbarrr);
        setSaveEnabled(true);

        // parent container
        ViewGroup parent = inflating();
        // collapsingtoolbar container
        container = (CollapsingToolbarLayout)
                parent.findViewById(R.id.collapsing_barrr);

        // custom attributes
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AppBarrr, defStyle, 0);

        // toolbar
        if (a.hasValue(R.styleable.AppBarrr_toolbarLayout)) {
            setToolbar(a.getResourceId(R.styleable.AppBarrr_toolbarLayout, 0));
            container.addView(mToolbar, container.getChildCount());
            // associate toolbar with actionbar
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).setSupportActionBar(mToolbar);
            }
        }

        // close icon (if navigationIcon doesn't exist)
        if (a.hasValue(R.styleable.AppBarrr_closeExpandIcon)) {
            setCloseIcon(a.getDrawable(R.styleable.AppBarrr_closeExpandIcon));
            // add icon to layout
            container.addView(mCloseIcon, container.getChildCount());
            // close expanded layout when close icon is clicked
            mCloseIcon.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    hideExpandedLayout();
                }
            });
            // hide the close icon by default
            mCloseIcon.setVisibility(View.GONE);
        }

        // expanded layout
        if (a.hasValue(R.styleable.AppBarrr_expandLayout)) {
            setExpandLayout(a.getResourceId(R.styleable.AppBarrr_expandLayout, 0));
            container.addView(mExpandLayout, container.getChildCount());
            // hide the expanded layout by default
            mExpandLayout.setVisibility(View.GONE);
        }

        // anim durations
        setExpandDuration(a.getInteger(R.styleable.AppBarrr_animExpandDuration, 300));
        setCollapseDuration(a.getInteger(R.styleable.AppBarrr_animCollapseDuration, 300));

        // content scrim
        if (a.hasValue(R.styleable.AppBarrr_contentScrimBar)) {
            setContentScrim(a.getDrawable(R.styleable.AppBarrr_contentScrimBar));
        }

        // expanded title style
        if (a.hasValue(R.styleable.AppBarrr_toolbarTitleExpandStyle)) {
            container.setExpandedTitleTextAppearance(
                    a.getResourceId(R.styleable.AppBarrr_toolbarTitleExpandStyle, 0));
        }

        // collapsed title style
        if (a.hasValue(R.styleable.AppBarrr_toolbarTitleCollapseStyle)) {
            container.setCollapsedTitleTextAppearance(
                    a.getResourceId(R.styleable.AppBarrr_toolbarTitleCollapseStyle, 0));
        }

        // default scroll flags
        setScrollFlags(-1);

        // recycle
        a.recycle();

        // init behavior
        initialize();
    }

    /**
     * Sets the default toolbar's layout
     *
     * @param resId Id of layout resource
     */
    public void setToolbar(int resId) {
        mToolbar = (Toolbar) activity
                .getLayoutInflater()
                .inflate(resId, container, false);
        if (mToolbar.getId() == -1) {
            mToolbar.setId(R.id.layout_default_toolbar);
        }
    }

    /**
     * Returns the default toolbar
     */
    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    /**
     * Sets the default close icon
     */
    public void setCloseIcon(Drawable drawable) {
        mCloseIcon = new ImageView(activity);
        mCloseIcon.setId(R.id.icon_default_close_navigation);
        mCloseIcon.setImageDrawable(drawable);
        if (TOOLBAR_START_HEIGHT > 0) {
            setCloseIconParams();
        }
    }

    /**
     * Returns the default close icon
     */
    public ImageView getCloseIcon() {
        return this.mCloseIcon;
    }

    /**
     * Sets the custom layout to be displayed when the collapsing
     * effect is disable
     *
     * @param resId Id of layout resource
     */
    public void setExpandLayout(int resId) {
        mExpandLayout = activity
                .getLayoutInflater()
                .inflate(resId, container, false);
        if (mExpandLayout.getId() == -1) {
            mExpandLayout.setId(R.id.layout_default_expanded);
        }
    }

    /**
     * Returns the default expanded layout
     */
    public View getExpandLayout() {
        return this.mExpandLayout;
    }

    /**
     * Sets the default duration of expanding animation
     * If not set or less than/equal to 0ms, default is 300ms
     *
     * @param duration Milliseconds in Long
     */
    public void setExpandDuration(long duration) {
        mExpandDuration = duration;
    }

    /**
     * Sets the default duration of collapsing animation
     * If not set or less than/equal to 0ms, default is 300ms
     *
     * @param duration Milliseconds in Long
     */
    public void setCollapseDuration(long duration) {
        mCollapseDuration = duration;
    }

    /**
     * Sets scroll flags to collapsingtoolbar
     *
     * @param flags Scroll behavior flags
     */
    public void setScrollFlags(@LayoutParams.ScrollFlags int flags) {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams)
                container.getLayoutParams();
        // default flags if not set
        if (flags == -1) {
            flags = LayoutParams.SCROLL_FLAG_SCROLL
                    | LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED;
        }
        params.setScrollFlags(flags);
        container.setLayoutParams(params);
    }

    /**
     * Sets content scrim to collapsingtoolbar
     *
     * @param drawable Drawable of content scrim
     */
    public void setContentScrim(Drawable drawable) {
        container.setContentScrim(drawable);
    }

    /**
     * Sets the current state of expanded layout
     * True if visible, false otherwise
     */
    private void setExpandState(boolean isLocked) {
        this.mIsExpanded = isLocked;
    }

    /**
     * Gets the current state of expanded layout
     * True if visible, false otherwise
     *
     * @return boolean
     */
    public boolean isExpanded() {
        return this.mIsExpanded;
    }

    /**
     * Sets the initial heights, which will be used
     * to retrieve the starting states
     */
    private void setInitialHeights() {
        APPBAR_START_HEIGHT = this.getHeight();
        TOOLBAR_START_HEIGHT = mToolbar.getLayoutParams().height;
        LOCKED_LAYOUT_MIN_HEIGHT = ((ViewGroup)
                this.getParent()).getHeight() - TOOLBAR_START_HEIGHT;
    }

    /**
     * Sets layout params on locked view
     */
    private void setExpandedLayoutParams() {
        // preparing view parameters
        CollapsingToolbarLayout.LayoutParams params =
                (CollapsingToolbarLayout.LayoutParams)
                mExpandLayout.getLayoutParams();
        params.topMargin = TOOLBAR_START_HEIGHT;
        mExpandLayout.setLayoutParams(params);
        mExpandLayout.setFocusableInTouchMode(true);
    }

    /**
     * Sets layout params on close icon
     */
    private void setCloseIconParams() {
        int size = TOOLBAR_START_HEIGHT;
        int padding = 34; // strict padding
        CollapsingToolbarLayout.LayoutParams params =
                new CollapsingToolbarLayout.LayoutParams(size, size);
        params.gravity = Gravity.START | Gravity.TOP;
        mCloseIcon.setPadding(padding, padding, padding, padding);
        mCloseIcon.setLayoutParams(params);
    }

    /**
     * SCROLLABLE-HACK: Sets a default touch to allow
     * scrollable expanded content future usage
     */
    private void setDefaultTouchEvent() {
        if (((ViewGroup) getParent()).getChildAt(1) != null) {
            final View nestedSv = ((ViewGroup) getParent()).getChildAt(1);
            nestedSv.post(new Runnable() {
                @Override
                public void run() {
                    long downTime = SystemClock.uptimeMillis();
                    float x = 1.0f;
                    float y = 1.0f;
                    int state = 0;

                    MotionEvent evDown = MotionEvent.obtain(downTime, downTime,
                            MotionEvent.ACTION_DOWN, x, y, state);
                    MotionEvent evUp = MotionEvent.obtain(downTime, downTime,
                            MotionEvent.ACTION_UP, x, y, state);

                    nestedSv.dispatchTouchEvent(evDown);
                    nestedSv.dispatchTouchEvent(evUp);
                }
            });
        }
    }

    /**
     * Gets activity from context wrapper
     *
     * @param c Context wrapper
     * @return Activity if exists, otherwise null
     */
    private Activity getActivity(Context c) {
        if (c == null) {
            return null;
        } else if (c instanceof Activity) {
            return (Activity) c;
        } else if (c instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) c).getBaseContext());
        }
        return (Activity) getContext();
    }

    /**
     * Initializes the inner widgets: toolbar and expanded layout
     */
    private void initialize() {
        // we need to get the height of the widgets so we need
        // a thread to wait until the UI is displayed
        post(new Runnable() {
            @Override
            public void run() {
                setInitialHeights();
                setCloseIconParams();
                setExpandedLayoutParams();
                setDefaultTouchEvent();
            }
        });
    }

    /**
     * Inflates and attaches the widgets
     *
     * @return The view inflated
     */
    private ViewGroup inflating() {
        View contentView = activity
                .getLayoutInflater()
                .inflate(R.layout.appbarrrlayout, this, true);
        return (ViewGroup) contentView;
    }

    /**
     * Disables elements outside the appbarrr
     */
    private void disableOutsideViews() {
        // disable nested views
        ViewGroup parent = (ViewGroup) getParent();
        for (int i=0; i<parent.getChildCount()-1; ++i) {
            View nestedView = parent.getChildAt(1);
            if (!(nestedView instanceof AppBarrr)) {
                nestedView.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * Enables elements outside the appbarrr
     */
    private void enableOutsideViews() {
        // re-enable nested views
        ViewGroup parent = (ViewGroup) getParent();
        for (int i=0; i<parent.getChildCount()-1; ++i) {
            View nestedView = parent.getChildAt(1);
            if (!(nestedView instanceof AppBarrr)) {
                nestedView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Animates the appbar and toolbar height to expanded mode
     *
     * @param target View to expand with an animation
     * @param targetHeight Final height for the expanded view
     */
    private void setExpandedAndLocked(final View target, final int targetHeight) {
        // set a animation from current height to target
        final ValueAnimator slideAnimator = ValueAnimator
                .ofInt(target.getHeight(), targetHeight)
                .setDuration(mExpandDuration);

        // SCROLLABLE-HACK: force scrollable content for nested child
        final boolean forceScrollable = ((ViewGroup) getParent()).getChildAt(1) != null;

        // the animation expands the widgets
        slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                target.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                target.requestLayout();

                // SCROLLABLE-HACK: scroll up to 1px the nested child
                // to enable scrollable expanded content
                if (forceScrollable) {
                    ((ViewGroup) getParent()).getChildAt(1).setScrollY(1);
                }
            }
        });

        // start animation
        slideAnimator.start();
    }

    /**
     * Animates the widgets height to collapsed mode
     *
     * @param target View to collapse with an animation
     * @param targetHeight Final height for the collapsed view
     */
    private void setCollapsedAndUnexpanded(final View target, final int targetHeight) {
        // set a animation from current height to target
        final ValueAnimator slideAnimator = ValueAnimator
                .ofInt(target.getHeight(), targetHeight)
                .setDuration(mCollapseDuration);

        // the animation collapses the widgets
        slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                target.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                target.requestLayout();
            }
        });

        // start animation
        slideAnimator.start();
    }

    /**
     * Prepares expanded state elements
     */
    private void prepareShowing() {
        // save the current visible state
        setExpandState(true);

        // disable outside views after the animation
        postDelayed(new Runnable() {
            @Override
            public void run() {
                disableOutsideViews();
            }
        }, mExpandDuration);

        // hide toolbar's title from actionbar
        if (activity instanceof AppCompatActivity) {
            ActionBar ab = ((AppCompatActivity) activity).getSupportActionBar();
            if (ab != null) {
                ab.setDisplayShowTitleEnabled(false);
                // hide default navigation icon if exists
                if (mToolbar.getNavigationIcon() != null) {
                    mUpIcon = mToolbar.getNavigationIcon();
                    ab.setDisplayHomeAsUpEnabled(false);
                }
            }
        }

        // show the expanded layout
        mExpandLayout.setVisibility(View.VISIBLE);
        // hide the title
        container.setTitleEnabled(false);
        // show close icon
        if (mCloseIcon != null) {
            mCloseIcon.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Prepares collapsed state elements
     */
    private void prepareHiding() {
        // save the current visible state
        setExpandState(false);

        // enable outside views
        enableOutsideViews();

        // hide toolbar's title from actionbar
        if (activity instanceof AppCompatActivity) {
            ActionBar ab = ((AppCompatActivity) activity).getSupportActionBar();
            if (ab != null) {
                ab.setDisplayShowTitleEnabled(true);
                // show default navigation icon if exists
                if (mUpIcon != null) {
                    ab.setDisplayHomeAsUpEnabled(true);
                    mToolbar.setNavigationIcon(mUpIcon);
                    mUpIcon = null;
                }
            }
        }

        // hide the expanded layout
        mExpandLayout.setVisibility(View.GONE);
        // show the title
        container.setTitleEnabled(true);
        // hide close icon
        if (mCloseIcon != null) {
            mCloseIcon.setVisibility(View.GONE);
        }
    }

    /**
     * Forces to expanded mode and show the expanded layout
     */
    public void showExpandedLayout() {
        if (isExpanded())
            return;

        // prepare elements
        prepareShowing();

        // expand the widgets
        setExpandedAndLocked(this, LOCKED_LAYOUT_MIN_HEIGHT);
        setExpandedAndLocked(mToolbar, LOCKED_LAYOUT_MIN_HEIGHT);
    }

    /**
     * Restores expanded state
     */
    private void restoreExpandedLayout() {
        // temporary expanding duration
        long tempDuration = mExpandDuration;
        mExpandDuration = 1;

        // prepare elements
        prepareShowing();

        // expand the widgets
        setExpandedAndLocked(this, LOCKED_LAYOUT_MIN_HEIGHT);
        setExpandedAndLocked(mToolbar, LOCKED_LAYOUT_MIN_HEIGHT);

        // reset initial duration
        mExpandDuration = tempDuration;
    }

    /**
     * Forces to collaspe the widget and hide the expanded layout
     */
    public void hideExpandedLayout() {
        if (!isExpanded())
            return;

        // prepare elements
        prepareHiding();

        // animate the height from current to the initial height
        setCollapsedAndUnexpanded(this, APPBAR_START_HEIGHT);
        setCollapsedAndUnexpanded(mToolbar, TOOLBAR_START_HEIGHT);
    }

    /**
     * Saves current instance of class
     *
     * @return A parcelable state
     */
    @SuppressWarnings("unchecked")
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.childrenStates = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).saveHierarchyState(ss.childrenStates);
        }
        ss.expanded = isExpanded();
        return ss;
    }

    /**
     * Restores current instance of class
     *
     * @param state Datas from previous state
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).restoreHierarchyState(ss.childrenStates);
        }
        if (ss.expanded) {
            post(new Runnable() {
                @Override
                public void run() {
                    restoreExpandedLayout();
                }
            });
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchThawSelfOnly(container);
    }

    @SuppressWarnings("unchecked")
    public class SavedState extends BaseSavedState {
        SparseArray childrenStates;
        boolean expanded;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in, ClassLoader classLoader) {
            super(in);
            childrenStates = in.readSparseArray(classLoader);
            expanded = in.readInt() != 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeSparseArray(childrenStates);
            out.writeInt(isExpanded() ? 1 : 0);
        }

        public final ClassLoaderCreator<SavedState> CREATOR
                = new ClassLoaderCreator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel source, ClassLoader loader) {
                return new SavedState(source, loader);
            }

            @Override
            public SavedState createFromParcel(Parcel source) {
                return createFromParcel(null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
