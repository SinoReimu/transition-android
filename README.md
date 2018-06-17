# transition-android

[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)

用优雅的直接设置属性的方式完成动画
  起到和CSS中transition一样的效果，自动完成补间

## Tranition-Android的优势
  - 减少代码长度
  - 提高多动画执行效率

### 过去
做一个补间动画需要

```java
        ValueAnimator s = ValueAnimator.ofFloat(0.9f,1.2f);
           s.setDuration(300);
           s.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
              @Override
             public void onAnimationUpdate(ValueAnimator animation) {
                 mView.setScaleX((float)animation.getAnimatedValue());        
              }
           });
       s.start();
```
如果走到一半要回到原状态则需要

```java
        float current = 0f;
        ValueAnimator s = ValueAnimator.ofFloat(0.9f,1.2f);
        s.setDuration(300);
        s.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                current = (float)animation.getAnimatedValue();
                mView.setScaleX(current);
            }
        });
        s.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                ValueAnimator s2 = ValueAnimator.ofFloat(current, 0.9f);
                s2.setDuration(300);
                s2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mView.setScaleX((float)animation.getAnimatedValue());
                    }
                });
                s2.start();
            }
        });
        s.start();
```
如果要折返三次，四次。。。

        无数的回调嵌套，而我们发现很多这样的动画回调是毫无意义的，因此我们引入了在CSS中用到的自动补间Transition


### 现在
我们只需要
```java
        vd = new ViewContainer(t);
        vd.setProperty("scaleX",1.9f,3000);
```
如果需要在OnClick的时候让它到达另一个状态呢？
```java
        vd = new ViewContainer(t);
        vd.setProperty("scaleX",1.9f,3000);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vd.setProperty("scaleX",0.2f,3000);
            }
        });
```
它便会立刻从上一个动画当前状态转向0.2f进行变换 NO！need！回调！

## 集成步骤

在工程中添加依赖

```groovy
dependencies {
    implementation 'cn.tecotaku:transition_android:1.3.1'
}
```

### 当前支持变换属性
- scaleX
- scaleY
- TranslationX
- TranslationY
- RotationX
- RotationY
- Alpha
- X
- Y
- backgroundColor

And More in next version。。。

### TODO
- 直接解析xml应用动画
- 增加动画主线程稳定性以及进一步提升效率

### 版本号
1.3.1

### 版本日志

#### 1.3.1
将动画器的帧率调整到60帧，使动画更流畅。

#### 1.1.1
修复了多动画迭代时的ComodifyException，添加多种插值器，以及动画回调。

#### 1.1.0
修复了activity关闭时动画线程延时崩溃，添加BackgroundColor渐变，添加插值器

## 贡献
如果你对于这个组件的改进有任何意见可以直接提出ISSUE，我会以最快的速度进行回复。或者你有自己的想法也可以加入进来，直接Pull Request就可以啦~

## License
> Copyright (C) 2017-2018 Hoshikawa Shiro.
> Licensed under the MIT.
> (See the [LICENSE](https://github.com/SinoReimu/transition-android/blob/master/LICENSE) file for the whole license text.)
