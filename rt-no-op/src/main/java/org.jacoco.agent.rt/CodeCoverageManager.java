package org.jacoco.agent.rt;

import android.content.Context;

/**
 * FileName: CodeCoverageManager
 * Author: zhihao.wu@ttpai.cn
 * Date: 2020/9/16
 * Description: 空代码实现，用于正式编译
 */
public class CodeCoverageManager {


    private CodeCoverageManager() {
    }


    public static void init(Context context,String serverHost){
        //Log.d("release init no op","init1111111111");
    }

    public static void generateCoverageFile() {
        //Log.d("release generateCoverageFile no op","generate");
    }

    public static void uploadData() {
        //Log.d("release uploadData no op","upload");
    }
}
