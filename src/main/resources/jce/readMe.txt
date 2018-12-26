因为某些国家的进口管制限制，Java发布的运行环境包中的加解密有一定的限制。
比如默认不允许256位密钥的AES加解密，解决方法就是修改策略文件， 
从官方网站下载JCE无限制权限策略文件，注意自己JDK的版本别下错了。
将local_policy.jar和US_export_policy.jar这两个文件替换%JRE_HOME%\lib\security和%JDK_HOME%\jre\lib\security下原来的文件，注意先备份原文件。