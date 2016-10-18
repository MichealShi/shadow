##shadow

```
tinker 初体验
```
##注意事项

```

 1. 在app的build.gradle文件中找到tinkerId = getTinkerIdValue()并将其替换成tinkerId = "tinkerId",
    其中后面的值可以随意设置.再替换ignoreWarning = false为ignoreWarning = true.
 2. 按照往常操作一样,编译打包debug apk并安装.此时Tinker会在工程的app/build/bakApk/目录下保存打包好的apk文件,
    找到刚才生成的apk文件,复制其完整文件名,在app的build.gradle文件找到tinkerOldApkPath这一项设置,并将其设置
    为tinkerOldApkPath = "${bakPath}/<刚才生成的apk文件名>"
    然后再Gradle脚本中找到’app:/tinker/tinkerPatchDebug’这条命令，双击运行,它将生成debug版的patch(补丁)apk文件.
 3. gradle暂不支持2.2.0，会有问题
 4. 6.0以上需要注意sd卡读写权限问题，安装的时候可能不授权，点击就会无反应，需要申请运行时权限
 5. 用retrofit时需要注意proguard文件
```

##License

```
Copyright 2013 Square, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
