# PageIndexer
PageIndexer is an android component that enables quick page turning.

## Using PageIndexer in your application
If you are building with Gradle, simply add the following line to the `repositories` and `dependencies` section of your `build.gradle` file:

Use Gradle:

```gradle
repositories {
  jcenter()
}

dependencies {
  implementation 'com.github.songjiubin.pageindexer:library:0.0.1'
}
```

## Sample

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.github.songjiubin.pageindexer.widget.PageNumberIndexer
        android:id="@+id/page_number_indexer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:colorActive="@color/colorAccent"
        app:colorInActive="@color/colorPrimary"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

```kotlin
viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
    override fun onPageSelected(position: Int) {
        pageNumberIndexer.setActiveMarker(position)
    }
})
pageNumberIndexer.addOnPageIndexChangeListener(object : PageNumberIndexer.OnPageIndexChangeListener {
    override fun onPageIndexSelected(index: Int) {
        viewPager.setCurrentItem(index, false)
    }
})
```
