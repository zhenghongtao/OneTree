package com.example.hongtao.myapplicationtest;


import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by hongtao on 17/8/4.
 */

public class RealApplication extends TinkerApplication {
    protected RealApplication(int tinkerFlags) {
        super(
                //tinkerFlags, tinker支持的类型，dex,library，还是全部都支持！
                ShareConstants.TINKER_ENABLE_ALL,
                //ApplicationLike的实现类，只能传递字符串
                "com.example.hongtao.myapplicationtest.TestApplication",
                //Tinker的加载器，一般来说用默认的即可
                "com.tencent.tinker.loader.TinkerLoader",
                //tinkerLoadVerifyFlag, 运行加载时是否校验dex，lib与res的Md5
                false);
    }

}
