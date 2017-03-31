アイコンフォントを使って、ButtonのdrawableLeftに画像を設定する方法(Iconics編)

## 概要
アイコンフォント便利ですよね！
アイコンフォントを使うと、テキストの中でアイコン画像を表示することができます。
つまり、アイコン＋テキストのボタン等が簡単に作れます。
ただ、一つの要素として表示されるため、テキストとアイコンとで違うサイズを使えなかったり、アイコンとテキストでベースラインが揃ってしまう問題があります。

Androidのボタンは、drawableLeftやdrawableRight属性を使うことで、ボタン内にアイコンを表示できます。
この属性を使うと先の問題が解決できそうですが、イメージを指定する必要があるため、そのままでは使えません。

そこで、アイコンフォントをdrawable vectorに変換して、アイコンとして表示する方法について説明します。

アイコンフォントを使うためのライブラリとしては、[Iconics](https://github.com/mikepenz/Android-Iconics)を利用します。

## 結果
下記のようにdrawable_icon_font_left属性を指定すると、いい感じにアイコンを表示してくれます。

```xml
<Button
    android:text="{faw-android} android"
    />
<Button
    android:text="android"
    app:drawable_icon_font_left="@{`faw_android`}"
    />
```

![image1](https://github.com/ayakix/Drawable-Icon-Font/raw/master/images/image1.png)

## 仕組み
### IconicsのインストールとdataBindingの有効化
**build.gradle** で下記の設定を追加します。ここでは例として、AwesomeFontを使ってみます。

```
android {
    ...
    dataBinding {
        enabled = true
    }
}

dependencies {
    ...
    compile "com.mikepenz:iconics-core:2.8.2@aar"
    compile 'com.mikepenz:fontawesome-typeface:4.7.0.0@aar'
}
```

### Iconicsの有効化
利用するActivityやFragmentで下記の設定を追加します。

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
```

### BindingAdapterの定義
**drawable_icon_font_left** 属性を定義します。

メソッド内では、指定された文字からDrawableを生成します。
その際に、好みに合わせて、テキストと同じカラーを設定したり、アイコンサイズをテキストサイズと比べて少し大きいものを設定できます。

```java
public class BindingAdapterManager {

    @BindingAdapter("drawable_icon_font_left")
    public static void setDrawableIconFontLeft(final Button button, final String icon) {
        final Context context = button.getContext();
        final IconicsDrawable iconicsDrawable = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.valueOf(icon))
                .color(button.getCurrentTextColor())
                .sizePx((int)(button.getTextSize() * 1.25));
        button.setCompoundDrawables(iconicsDrawable, null, null, null);
    }
}
```

## サンプル
[Drawable-Icon-Font@github](https://github.com/ayakix/Drawable-Icon-Font)に動作するプロジェクトがあります。

## 謝辞
このTipsは、「スマホの写真素材が売買できるサイト[Snapmart](https://snapmart.jp/)」を開発する中で生まれました。
Android版は開発中なため、残念ながらまだ利用できません。
iPhoneをお持ちの方は、是非ダウンロードしてお使いください！
[Snapmartアプリ（iOS）](https://itunes.apple.com/jp/app/id1087206878)
